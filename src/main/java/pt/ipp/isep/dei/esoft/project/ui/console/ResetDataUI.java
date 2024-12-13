package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.database.Database;
import pt.ipp.isep.dei.esoft.project.utils.Input;

import static pt.ipp.isep.dei.esoft.project.utils.Files.clearFile;

/**
 * The ResetDataUI class is responsible for providing a user interface to reset the application data.
 * It implements the Runnable interface to allow running the reset process in a separate thread.
 */
public class ResetDataUI implements Runnable {
    /**
     * The run method that is executed when the ResetDataUI thread is started.
     * Displays a reset data prompt.
     */
    @Override
    public void run() {
        resetDataPrompt();
    }

    /**
     * Displays the reset data prompt and performs the reset if confirmed.
     */
    private void resetDataPrompt() {
        if (reset())
            resetApp();
    }

    /**
     * Prompts the user to confirm the reset.
     *
     * @return true if the reset is confirmed, false otherwise.
     */
    private boolean reset() {
        return Input.getBooleanWithLabel("Reset App? [Y/N]");
    }

    /**
     * Resets the application data by clearing all the relevant files and exiting the application.
     */
    private void resetApp() {
        clearFile(Database.advertisementsSER);
        clearFile(Database.clientsSER);
        clearFile(Database.employeesSER);
        clearFile(Database.requestsSER);
        clearFile(Database.storesSER);
        clearFile(Database.usersCSV);
        clearFile(Database.emailTXT);
        System.exit(1);
    }
}