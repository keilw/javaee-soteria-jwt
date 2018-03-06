package id.swhp.javaee.jwt.business.security.boundary;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author Sukma Wardana
 * @author Werner Keil
 * @since 1.0
 */
@Stateless
@Path("tokens")
public class AuthResource {
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Inject
    JWTStore jwtStore;

    /**
     *
     * @param credential in json should be {"username": "...", "password": "..."}
     * @return JWT token
     */
    @POST
    public Response authenticate(JsonObject credential) {
        // TODO: Should compare user credentials on the database.
        String username = credential.getString("username");
        String password = credential.getString("password");

        // TODO: Groups should retrieve from database based on authenticate user.
        String token = this.jwtStore.generateToken(username, Arrays.asList("ADMIN", "MEMBER"));
        logger.info( () -> MessageFormat.format("Token={0}", token));

        return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
    }
}
