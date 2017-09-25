package id.swhp.javaee.jwt.application;

import id.swhp.javaee.jwt.business.security.entity.JWTCredential;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@ApplicationScoped
public class JWTIdentityStore implements IdentityStore {

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof JWTCredential) {
            // this means we had a valid token
            JWTCredential jwtCredential = (JWTCredential) credential;

            return new CredentialValidationResult(jwtCredential.getCaller(), jwtCredential.getGroups());
        }

        return CredentialValidationResult.INVALID_RESULT;
    }

}
