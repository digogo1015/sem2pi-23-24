package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.MakeAdvertisementUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ManagePurchaseOrdersUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ManageRequestsUI;
import pt.ipp.isep.dei.esoft.project.utils.Print;
import pt.ipp.isep.dei.esoft.project.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.gui.US15UI;

import java.util.ArrayList;
import java.util.List;

/**
 * The AgentUI class represents the user interface for an agent in the application.
 * It provides a menu-driven interface with options for making advertisements,
 * managing requests, managing purchase orders, and listing visit requests.
 * The class implements the Runnable interface, allowing it to be executed in a separate thread.
 */
public class AgentUI implements Runnable {

    /**
     * The run() method is the entry point of the thread.
     * It displays a menu of options and executes the selected option.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("Make an Advertisement", new MakeAdvertisementUI()));
        options.add(new MenuItem("Manage Requests", new ManageRequestsUI()));
        options.add(new MenuItem("Manage Purchase Orders", new ManagePurchaseOrdersUI()));
        options.add(new MenuItem("List Visit Request", new US15UI()));

        int option = 0;
        do {
            Utils.showList(options, "\nAgent Menu");
            Print.text("0. Log Out");

            option = Utils.selectsIndexWithZero(options);

            if ((option >= 0) && (option < options.size()))
                options.get(option).run();
        } while (option != -1);
    }
}
