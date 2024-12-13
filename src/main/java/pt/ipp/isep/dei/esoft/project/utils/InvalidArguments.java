package pt.ipp.isep.dei.esoft.project.utils;

public class InvalidArguments extends Exception {
    public InvalidArguments() {
        super();
    }

    public InvalidArguments(String message) {
        super(message);
    }

    public InvalidArguments(Throwable cause, String message) {
        super(message, cause);
    }

    public InvalidArguments(Throwable cause) {
        super(cause);
    }
}