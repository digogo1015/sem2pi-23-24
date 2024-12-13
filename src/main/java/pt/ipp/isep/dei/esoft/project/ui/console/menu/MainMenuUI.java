package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.authorization.AuthenticationUI;
import pt.ipp.isep.dei.esoft.project.utils.Print;
import pt.ipp.isep.dei.esoft.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The MainMenuUI class represents the main user interface for the application.
 * It provides a menu-driven interface with options for login, registration,
 * entering as an unregistered user, and knowing the development team.
 * The class implements the Runnable interface, allowing it to be executed in a separate thread.
 */
public class MainMenuUI implements Runnable {

    /**
     * The run() method is the entry point of the thread.
     * It displays a menu of options and executes the selected option.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<>();

        options.add(new MenuItem("Do Login", new AuthenticationUI()));
        options.add(new MenuItem("Register", new RegisterClientUI()));
        options.add(new MenuItem("Enter as Unregistered User", new UnregisteredUserUI()));
        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));

        int option;
        do {
            Utils.showList(options, "\nMain Menu");
            Print.text("0. Exit");

            option = Utils.selectsIndexWithZero(options);

            if ((option >= 0) && (option < options.size()))
                options.get(option).run();
        } while (option != -1);
    }
}
