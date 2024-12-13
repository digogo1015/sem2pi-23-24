package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;

/**
 * The ListEmployeesController class is a controller responsible for handling employee-related operations
 * in a divide stores application.
 */
public class ListEmployeesController {
    Repositories repositories;
    EmployeeRepository employeeRepository;
    StoreRepository storeRepository;

    /**
     * Constructs a new instance of ListEmployeesController.
     * Initializes the repositories and sets the employeeRepository and storeRepository
     * based on the repositories instance.
     */
    public ListEmployeesController() {
        this.repositories = Repositories.getInstance();
        this.employeeRepository = repositories.getEmployeeRepository();
        this.storeRepository = repositories.getStoreRepository();
    }

    /**
     * Returns a list of all stores.
     *
     * @return An ArrayList of Store objects representing the stores.
     */
    public ArrayList<Store> getStores() {
        return storeRepository.copy();
    }

    /**
     * Returns a list of employees based on the specified store designation.
     *
     * @param designation The designation of the store to filter employees.
     * @return An ArrayList of Employee objects representing the employees.
     */
    public ArrayList<Employee> getEmployeesByStore(String designation) {
        return employeeRepository.getEmployeesByStore(designation);
    }
}