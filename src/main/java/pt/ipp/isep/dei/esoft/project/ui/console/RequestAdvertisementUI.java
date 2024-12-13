package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RequestAdvertisementController;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import pt.ipp.isep.dei.esoft.project.utils.*;

import java.util.ArrayList;

/**
 * The RequestAdvertisementUI class is responsible for providing a user interface to request an advertisement.
 * It implements the Runnable interface to allow running the request process in a separate thread.
 */
public class RequestAdvertisementUI implements Runnable {
    private final RequestAdvertisementController ctrl;

    /**
     * Constructs a RequestAdvertisementUI object.
     * Initializes the RequestAdvertisementController.
     */
    public RequestAdvertisementUI() {
        ctrl = new RequestAdvertisementController();
    }

    /**
     * The run method that is executed when the RequestAdvertisementUI thread is started.
     * Retrieves the request from the user and adds it to the repository.
     */
    @Override
    public void run() {
        Request request = getRequest();

        addToRepository(request);
    }

    /**
     * Adds the given request to the repository.
     *
     * @param request The request object to be added.
     */
    private void addToRepository(Request request) {
        Input.displayAndConfirm(request.toStringV2());

        if (ctrl.addRequest(request))
            Print.text("\nYour Request Has Been Submitted!");
        else
            Print.text("\nYour Request Has Not Been Submitted!");
    }

    /**
     * Retrieves the request details from the user.
     *
     * @return The created request object.
     */
    private Request getRequest() {
        do {
            try {
                Agent responsibleAgent = (Agent) displayAndSelectAgentFromStore();
                Client client = requestClientData();

                String typeOfBusiness = Input.requestTypeOfBusiness();
                String typeOfProperty = Input.requestTypeOfProperty();
                Property property = PropertyInfo.requestPropertyData(typeOfProperty);
                String price = Input.requestPrice();

                return Request.createRequest(property, client, responsibleAgent, Time.getDateNow(), typeOfBusiness, price);
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    /**
     * Displays and allows the user to select an agent from the store.
     *
     * @return The selected agent.
     */
    private Employee displayAndSelectAgentFromStore() {
        ArrayList<Employee> agentByStoreList = getListOfAgentsFromStore();

        return getAgentFromList(agentByStoreList);
    }

    /**
     * Retrieves the selected agent from the list of agents.
     *
     * @param list The list of agents to choose from.
     * @return The selected agent.
     */
    private Employee getAgentFromList(ArrayList<Employee> list) {
        return list.get(Utils.showAndSelectIndex(list, "Select an agent:"));
    }

    /**
     * Retrieves the list of agents associated with the store.
     *
     * @return The list of agents.
     */
    private ArrayList<Employee> getListOfAgentsFromStore() {
        ArrayList<Employee> employeesByStoreList = getEmployeesByStore();
        ArrayList<Employee> employeesByRoleList = ctrl.getEmployeesByRole("AGENT");

        return getMutualEmployeesBetweenLists(employeesByStoreList, employeesByRoleList);
    }

    /**
     * Retrieves the common employees between the list of employees by store and employees by role.
     *
     * @param employeesByStoreList The list of employees associated with the store.
     * @param employeesByRoleList  The list of employees with the role "AGENT".
     * @return The list of mutual employees.
     */
    private static ArrayList<Employee> getMutualEmployeesBetweenLists(ArrayList<Employee> employeesByStoreList, ArrayList<Employee> employeesByRoleList) {
        ArrayList<Employee> agentByStoreList = new ArrayList<>(employeesByRoleList);
        agentByStoreList.retainAll(employeesByStoreList);

        return agentByStoreList;
    }

    /**
     * Retrieves the list of employees associated with the store.
     *
     * @return The list of employees.
     */
    private ArrayList<Employee> getEmployeesByStore() {
        ArrayList<Store> storeList = ctrl.getStores();
        String store = getStore(storeList);

        return ctrl.getEmployeesByStore(store);
    }

    /**
     * Retrieves the selected store from the list of stores.
     *
     * @param storeList The list of stores to choose from.
     * @return The selected store.
     */
    private static String getStore(ArrayList<Store> storeList) {
        int index = getStoreIndex(storeList);
        return storeList.get(index).getDesignation();
    }

    /**
     * Retrieves the index of the selected store from the list of stores.
     *
     * @param storeList The list of stores to choose from.
     * @return The index of the selected store.
     */
    private static int getStoreIndex(ArrayList<Store> storeList) {
        int index = -1;
        while (index == -1)
            index = Utils.showAndSelectIndex(storeList, "Select a store:");
        return index;
    }

    /**
     * Retrieves the client data based on the user session.
     *
     * @return The client object.
     */
    private Client requestClientData() {
        UserSession userSession = Repositories.getInstance().getUserSession();

        return ctrl.getClientByEmail(userSession.getUserEmail());
    }
}
