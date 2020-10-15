package by.balashevich.finalproject.exception;

public class ServiceProjectException extends Exception {
    public ServiceProjectException() {
        super();
    }

    public ServiceProjectException(String message) {
        super(message);
    }

    public ServiceProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceProjectException(Throwable cause) {
        super(cause);
    }
}
