package id.swhp.javaee.jwt.application.security;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

import id.swhp.javaee.jwt.application.exception.EnterpriseException;
import id.swhp.javaee.jwt.business.security.boundary.JWTStore;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 *
 * @author Sukma Wardana
 * @author Werner Keil
 * @since 1.0
 */
@ApplicationScoped
public class JWTAuthenticationMechanism implements HttpAuthenticationMechanism {
    private static final String BEARER = "Bearer ";

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Inject
    IdentityStore identityStore;

    @Inject
    JWTStore jwtStore;


    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest req, HttpServletResponse res, HttpMessageContext context) throws AuthenticationException {
        logger.info( () -> "Validating...");

        String authorizationHeader = req.getHeader(AUTHORIZATION);
        Credential credential = null;

        //JsonWebToken principal = securityContext.getCallerPrincipal();

        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            try {
                String token = authorizationHeader.substring(BEARER.length());
                credential = this.jwtStore.getCredential(token);
            } catch (EnterpriseException ee) {
                logger.warning( () -> "Error: " + ee.getMessage());
                throw new AuthenticationException(ee);
            }
        }

        if (credential != null) {
            return context.notifyContainerAboutLogin(this.identityStore.validate(credential));
        } else {
            return context.doNothing();
        }
    }

}
