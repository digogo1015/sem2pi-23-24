package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;

/**
 * This class is a controller responsible for managing advertisement requests.
 */
public class RequestAdvertisementController {

    // Instance variables
    private Repositories repositories;
    private RequestRepository requestRepository;
    private ClientRepository clientRepository;
    private EmployeeRepository employeeRepository;
    private FilterRepository filterRepository;
    private StoreRepository storeRepository;

    /**
     * Constructs a new RequestAdvertisementController object.
     * Initializes the repositories, requestRepository, clientRepository, employeeRepository,
     * filterRepository, and storeRepository instances.
     */
    public RequestAdvertisementController() {
        this.repositories = Repositories.getInstance();
        this.requestRepository = repositories.getRequestRepository();
        this.clientRepository = repositories.getClientRepository();
        this.employeeRepository = repositories.getEmployeeRepository();
        this.filterRepository = repositories.getFilterRepository();
        this.storeRepository = repositories.getStoreRepository();
    }

    /**
     * Adds a request to the request repository.
     *
     * @param request The request to be added.
     * @return true if the request was added successfully, false otherwise.
     */
    public boolean addRequest(Request request) {
        return requestRepository.addRequest(request);
    }

    /**
     * Retrieves a list of employees based on their role.
     *
     * @param role The role of the employees.
     * @return An ArrayList of Employee objects representing the employees with the specified role.
     */
    public ArrayList<Employee> getEmployeesByRole(String role) {
        return employeeRepository.getEmployeesByRole(role);
    }

    /**
     * Retrieves a list of employees based on the store they belong to.
     *
     * @param store The store of the employees.
     * @return An ArrayList of Employee objects representing the employees in the specified store.
     */
    public ArrayList<Employee> getEmployeesByStore(String store) {
        return employeeRepository.getEmployeesByStore(store);
    }

    /**
     * Retrieves a list of all stores.
     *
     * @return An ArrayList of Store objects representing all the stores.
     */
    public ArrayList<Store> getStores() {
        return storeRepository.copy();
    }

    /**
     * Retrieves a client from the client repository based on their email.
     *
     * @param userEmail The email of the client.
     * @return The Client object if found, or null if not found.
     */
    public Client getClientByEmail(String userEmail) {
        return clientRepository.getClientByEmail(userEmail);
    }
}
