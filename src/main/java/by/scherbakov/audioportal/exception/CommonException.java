package by.scherbakov.audioportal.exception;

/**
 * {@code CommonException} is used to store Common level exceptions.
 *
 * @author ScherbakovIlia
 * @see Exception
 */
public class CommonException extends Exception {
    public CommonException() {
        super();
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }
}
