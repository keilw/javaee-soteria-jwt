package id.swhp.javaee.jwt.application.security.control;

import id.swhp.javaee.jwt.application.security.entity.JWTCredential;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.text.MessageFormat;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Sukma Wardana
 * @author Werner Keil
 * @since 1.0
 */
@ApplicationScoped
public class JWTIdentityStore implements IdentityStore {
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof JWTCredential) {
            // this means we had a valid token
            JWTCredential jwtCredential = (JWTCredential) credential;
            logger.info( () -> MessageFormat.format("Caller={0} ({1})", jwtCredential.getCaller(), log(jwtCredential.getGroups())));
            return new CredentialValidationResult(jwtCredential.getCaller(), jwtCredential.getGroups());
        }

        return CredentialValidationResult.INVALID_RESULT;
    }

    private String log(Set<String> groups){
        return groups.stream()
                .map(i -> i.toString())
                .collect(Collectors.joining(", "));
    }
}
