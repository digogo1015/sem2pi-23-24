package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;
/**
 * This class is a controller responsible for registering employees and retrieving store and role information.
 */
public class RegisterEmployeeController {

    // Instance variables
    private Repositories repositories;
    private RoleRepository roleRepository;
    private EmployeeRepository employeeRepository;
    private StoreRepository storeRepository;

    /**
     * Constructs a new RegisterEmployeeController object.
     * Initializes the repositories, roleRepository, employeeRepository, and storeRepository instances.
     */
    public RegisterEmployeeController() {
        this.repositories = Repositories.getInstance();
        this.roleRepository = repositories.getRoleRepository();
        this.employeeRepository = repositories.getEmployeeRepository();
        this.storeRepository = repositories.getStoreRepository();
    }

    /**
     * Retrieves a list of all stores.
     *
     * @return A List of Store objects representing all the stores.
     */
    public List<Store> getStores() {
        return storeRepository.copy();
    }

    /**
     * Retrieves a list of all roles.
     *
     * @return A List of Role objects representing all the roles.
     */
    public List<Role> getRoles() {
        return roleRepository.copy();
    }

    /**
     * Registers an employee by adding them to the employee repository.
     *
     * @param employee The employee to be registered.
     * @return true if the employee was registered successfully, false otherwise.
     */
    public boolean registerEmployee(Employee employee) {
        return employeeRepository.registerEmployee(employee);
    }

    /**
     * Retrieves a role from the role repository based on its designation.
     *
     * @param roleDescription The designation of the role.
     * @return The Role object if found, or null if not found.
     */
    public Role getRoleByDesignation(String roleDescription) {
        return roleRepository.getRoleByDesignation(roleDescription);
    }

    /**
     * Retrieves a store from the store repository based on its designation.
     *
     * @param storeDescription The designation of the store.
     * @return The Store object if found, or null if not found.
     */
    public Store getStoreByDesignation(String storeDescription) {
        return storeRepository.getStoreByDesignation(storeDescription);
    }
}
