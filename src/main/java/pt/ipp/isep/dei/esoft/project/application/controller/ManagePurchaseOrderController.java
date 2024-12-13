package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Advertisement;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;

/**
 * This class is a controller responsible for managing the purchase orders and advertisements.
 */
public class ManagePurchaseOrderController {

    // Instance variables
    private Repositories repositories;
    private AdvertisementRepository advertisementRepository;
    private EmployeeRepository employeeRepository;

    /**
     * Constructs a new ManagePurchaseOrderController object.
     * Initializes the repositories, advertisementRepository, and employeeRepository instances.
     */
    public ManagePurchaseOrderController() {
        this.repositories = Repositories.getInstance();
        this.advertisementRepository = repositories.getAdvertisementRepository();
        this.employeeRepository = repositories.getEmployeeRepository();
    }

    /**
     * Retrieves the agent (employee) with the specified email.
     *
     * @param email The email of the agent.
     * @return The Employee object representing the agent.
     */
    public Employee getAgent(String email) {
        return employeeRepository.getEmployeeByEmail(email);
    }

    /**
     * Retrieves a list of all advertisements.
     *
     * @return An ArrayList containing all the advertisements.
     */
    public ArrayList<Advertisement> getListOfAdvertisements() {
        return advertisementRepository.copy();
    }

    /**
     * Retrieves a list of advertisements associated with the specified agent's email.
     *
     * @param email The email of the agent.
     * @return An ArrayList containing the advertisements associated with the agent.
     */
    public ArrayList<Advertisement> getListOfAdvertisementByAgent(String email) {
        return advertisementRepository.getAgentAdvertisements(email);
    }
}
