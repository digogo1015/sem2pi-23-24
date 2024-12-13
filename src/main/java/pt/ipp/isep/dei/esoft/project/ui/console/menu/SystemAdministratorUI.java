package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.utils.Print;
import pt.ipp.isep.dei.esoft.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The SystemAdministratorUI class represents the user interface for a system administrator in the application.
 * It provides a menu-driven interface with options for registering new employees, stores, locations,
 * importing files, and resetting data.
 * The class implements the Runnable interface, allowing it to be executed in a separate thread.
 */
public class SystemAdministratorUI implements Runnable {

    /**
     * The run() method is the entry point of the thread.
     * It displays a menu of options and executes the selected option.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("Register new Employee", new RegisterEmployeeUI()));
        options.add(new MenuItem("Register new Store", new RegisterStoreUI()));
        options.add(new MenuItem("Register new Location", new RegisterLocationUI()));
        options.add(new MenuItem("Import file", new ImportFileUI()));
        options.add(new MenuItem("Reset Data", new ResetDataUI()));

        int option = 0;
        do {
            Utils.showList(options, "\nSystem Administrator Menu");
            Print.text("0. Log Out");

            option = Utils.selectsIndexWithZero(options);

            if ((option >= 0) && (option < options.size()))
                options.get(option).run();
        } while (option != -1);
    }
}
