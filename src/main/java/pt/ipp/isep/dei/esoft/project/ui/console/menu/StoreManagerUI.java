package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.utils.Print;
import pt.ipp.isep.dei.esoft.project.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.gui.US18UI;

import java.util.ArrayList;
import java.util.List;

/**
 * The StoreManagerUI class represents the user interface for a store manager in the application.
 * It provides a menu-driven interface with an option for linear regression.
 * The class implements the Runnable interface, allowing it to be executed in a separate thread.
 */
public class StoreManagerUI implements Runnable {

    /**
     * The run() method is the entry point of the thread.
     * It displays a menu of options and executes the selected option.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("Linear Regression", new US18UI()));

        int option = 0;
        do {
            Utils.showList(options, "\nStore Manager Menu");
            Print.text("0. Log Out");

            option = Utils.selectsIndexWithZero(options);

            if ((option >= 0) && (option < options.size()))
                options.get(option).run();
        } while (option != -1);
    }
}
