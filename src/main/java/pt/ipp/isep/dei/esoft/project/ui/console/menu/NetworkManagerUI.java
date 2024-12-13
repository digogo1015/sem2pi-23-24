package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.ListEmployeesUI;
import pt.ipp.isep.dei.esoft.project.utils.Print;
import pt.ipp.isep.dei.esoft.project.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.gui.US17UI;
import pt.ipp.isep.dei.esoft.project.ui.gui.US19UI;

import java.util.ArrayList;
import java.util.List;

/**
 * The NetworkManagerUI class represents the user interface for a network manager in the application.
 * It provides a menu-driven interface with options for listing employees, listing deals,
 * and even storing information.
 * The class implements the Runnable interface, allowing it to be executed in a separate thread.
 */
public class NetworkManagerUI implements Runnable {

    /**
     * The run() method is the entry point of the thread.
     * It displays a menu of options and executes the selected option.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("List Employees", new ListEmployeesUI()));
        options.add(new MenuItem("List Deals", new US17UI()));
        options.add(new MenuItem("Even Stores", new US19UI()));

        int option = 0;
        do {
            Utils.showList(options, "\nNetwork Manager Menu");
            Print.text("0. Log Out");

            option = Utils.selectsIndexWithZero(options);

            if ((option >= 0) && (option < options.size()))
                options.get(option).run();
        } while (option != -1);
    }
}
