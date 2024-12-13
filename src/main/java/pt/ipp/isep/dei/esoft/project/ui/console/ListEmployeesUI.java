package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListEmployeesController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.utils.Utils;

import java.util.ArrayList;

/**
 * The ListEmployeesUI class represents a user interface for listing employees.
 * It implements the Runnable interface to support execution in a separate thread.
 */
public class ListEmployeesUI implements Runnable {
    public ListEmployeesController ctrl;

    /**
     * Constructs a new ListEmployeesUI object.
     * Initializes the ListEmployeesController.
     */
    public ListEmployeesUI() {
        this.ctrl = new ListEmployeesController();
    }

    /**
     * Runs the process of listing all employees.
     * Retrieves the list of stores and iterates over them to retrieve the employees for each store.
     * Displays the employee list for each store using the Utils.showList method.
     */
    @Override
    public void run() {
        listAllEmployees();
    }

    /**
     * Retrieves the list of stores and iterates over them to retrieve the employees for each store.
     * Displays the employee list for each store using the Utils.showList method.
     */
    private void listAllEmployees() {
        ArrayList<Store> storeList = ctrl.getStores();

        for (Store store : storeList) {
            ArrayList<Employee> employeeList = ctrl.getEmployeesByStore(store.getDesignation());
            Utils.showList(employeeList, "Store: " + store.getDesignation());
        }
    }
}
