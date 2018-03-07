package id.swhp.javaee.jwt.application.exception;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
public class SystemException extends EnterpriseException {

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
