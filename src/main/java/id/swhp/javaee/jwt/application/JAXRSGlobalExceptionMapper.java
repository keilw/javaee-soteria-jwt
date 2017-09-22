package id.swhp.javaee.jwt.application;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * ExceptionMapper for all uncaught exceptions. The returned response contains no data as the
 * exception message is transferred via extended HTTP header.
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@Provider
public class JAXRSGlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return Response.status(BAD_REQUEST).header("X-Message", exception.getMessage()).build();
    }

}
