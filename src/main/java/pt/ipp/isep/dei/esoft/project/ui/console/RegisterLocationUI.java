package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.utils.Print;

/**
 * The RegisterLocationUI class is responsible for displaying a message indicating that the application
 * is currently under maintenance.
 */
public class RegisterLocationUI implements Runnable {

    /**
     * This method is called when the RegisterLocationUI object is executed as a thread.
     * It displays a message indicating that the application is under maintenance.
     */
    @Override
    public void run() {
        Print.text("Under maintenance!");
    }
}
