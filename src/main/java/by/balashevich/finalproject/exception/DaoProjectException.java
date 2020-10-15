package by.balashevich.finalproject.exception;

public class DaoProjectException extends Exception {
    public DaoProjectException() {
        super();
    }

    public DaoProjectException(String message) {
        super(message);
    }

    public DaoProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoProjectException(Throwable cause) {
        super(cause);
    }
}
