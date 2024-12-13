package pt.ipp.isep.dei.esoft.project.utils;

public class ExceptionHandler {

    public static void ioException(Exception e) {
        Print.text("I/O Exception\n" + e);
    }

    public static void invalidInput() {
        Print.text("Invalid Input!\n");
    }

    public static void error(Exception e, String text) {
        Print.text("Error: " + text);
    }
}