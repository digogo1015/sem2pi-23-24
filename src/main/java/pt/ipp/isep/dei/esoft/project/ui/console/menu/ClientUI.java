package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.ListPropertyUI;
import pt.ipp.isep.dei.esoft.project.ui.console.VisitRequestUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RequestAdvertisementUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RequestPurchaseOrderUI;
import pt.ipp.isep.dei.esoft.project.utils.Print;
import pt.ipp.isep.dei.esoft.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The ClientUI class represents the user interface for a client in the application.
 * It provides a menu-driven interface with options for listing properties,
 * making visit requests, making purchase orders, and making requests for advertisements.
 * The class implements the Runnable interface, allowing it to be executed in a separate thread.
 */
public class ClientUI implements Runnable {

    /**
     * The run() method is the entry point of the thread.
     * It displays a menu of options and executes the selected option.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("List Properties", new ListPropertyUI()));
        options.add(new MenuItem("Make a Visit Request", new VisitRequestUI()));
        options.add(new MenuItem("Make a Purchase Order", new RequestPurchaseOrderUI()));
        options.add(new MenuItem("Make a Request", new RequestAdvertisementUI()));

        int option = 0;
        do {
            Utils.showList(options, "\nClient Menu");
            Print.text("0. Log Out");

            option = Utils.selectsIndexWithZero(options);

            if ((option >= 0) && (option < options.size()))
                options.get(option).run();
        } while (option != -1);
    }
}
