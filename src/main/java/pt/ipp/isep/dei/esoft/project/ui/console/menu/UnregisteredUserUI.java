package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.ListPropertyUI;
import pt.ipp.isep.dei.esoft.project.utils.Print;
import pt.ipp.isep.dei.esoft.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The UnregisteredUserUI class represents the user interface for an unregistered user in the application.
 * It provides a menu-driven interface with an option to list properties.
 * The class implements the Runnable interface, allowing it to be executed in a separate thread.
 */
public class UnregisteredUserUI implements Runnable {

    /**
     * The run() method is the entry point of the thread.
     * It displays a menu of options and executes the selected option.
     */
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();

        options.add(new MenuItem("List Properties", new ListPropertyUI()));

        int option = 0;
        do {
            Utils.showList(options, "\nUnregistered User Menu");
            Print.text("0. Go Back");

            option = Utils.selectsIndexWithZero(options);

            if ((option >= 0) && (option < options.size()))
                options.get(option).run();
        } while (option != -1);
    }
}
