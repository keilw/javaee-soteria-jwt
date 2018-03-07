package id.swhp.javaee.jwt.application.exception;

import javax.ejb.ApplicationException;

/**
 * RuntimeException is wrapped by EJBException, it won't rollback the transaction unless you
 * specify {@link ApplicationException} with parameter rollback = true.
 *
 * @author Sukma Wardana
 * @author Werner Keil
 * @since 1.0
 */
@ApplicationException(rollback = true)
public abstract class EnterpriseException extends RuntimeException {

    public EnterpriseException() {
        super();
    }

    public EnterpriseException(String message) {
        super(message);
    }

    public EnterpriseException(Throwable cause) {
        super(cause);
    }

    public EnterpriseException(String message, Throwable cause) {
        super(message, cause);
    }
}
