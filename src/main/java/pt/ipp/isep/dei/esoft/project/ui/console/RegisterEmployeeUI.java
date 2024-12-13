package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.utils.Input;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;
import pt.ipp.isep.dei.esoft.project.utils.Print;
import pt.ipp.isep.dei.esoft.project.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * The RegisterEmployeeUI class represents a user interface for registering employees.
 * It implements the Runnable interface to support execution in a separate thread.
 */
public class RegisterEmployeeUI implements Runnable {
    private final RegisterEmployeeController ctrl;

    /**
     * Constructs a new RegisterEmployeeUI object.
     * Initializes the RegisterEmployeeController.
     */
    public RegisterEmployeeUI() {
        ctrl = new RegisterEmployeeController();
    }

    /**
     * Runs the process of registering an employee.
     * Calls the necessary methods to get employee information,
     * create an Employee object, and register the employee.
     */
    @Override
    public void run() {
        Employee employee = getEmployee();

        registerEmployee(employee);
    }

    /**
     * Retrieves employee information from the user by displaying and selecting the store and role.
     * Calls the necessary methods in the controller to create an EmployeeInfo object.
     * Uses reflection to dynamically create an Employee object based on the selected role.
     * Returns the created Employee object.
     * Prints an error message if an exception or invalid argument occurs.
     * @return The created Employee object.
     */
    private Employee getEmployee() {
        try {
            String storeDescription = displayAndSelectStore();
            String roleDescription = displayAndSelectRole();
            PersonInfo personInfo = PersonInfo.getInfo();

            EmployeeInfo employeeInfo = EmployeeInfo.createEmployee(personInfo,
                    ctrl.getStoreByDesignation(storeDescription),
                    ctrl.getRoleByDesignation(roleDescription));

            Class<?> className = Class.forName(Input.getDomainClassName(roleDescription));

            return (Employee) className.getMethod("createEmployee", EmployeeInfo.class)
                    .invoke(null, employeeInfo);
        } catch (InvocationTargetException | InvalidArguments ia) {
            Print.text(ia.getMessage());
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Displays a list of stores and allows the user to select a store.
     * Returns the designation of the selected store.
     * @return The designation of the selected store.
     */
    private String displayAndSelectStore() {
        List<Store> storeList = ctrl.getStores();

        return storeList.get(Utils.showAndSelectIndex(storeList, "Select a store:")).getDesignation();
    }

    /**
     * Displays a list of roles and allows the user to select a role.
     * Returns the designation of the selected role.
     * @return The designation of the selected role.
     */
    private String displayAndSelectRole() {
        List<Role> roleList = ctrl.getRoles();

        return roleList.get(Utils.showAndSelectIndex(roleList, "Select a role:")).getDesignation();
    }

    /**
     * Registers an employee with the provided Employee object.
     * Displays the employee information for confirmation using the displayAndConfirm method of the Input class.
     * Calls the registerEmployee method of the controller to register the employee.
     * Prints a success message if the employee is successfully registered,
     * or a failure message if the employee is not registered.
     */
    private void registerEmployee(Employee employee) {
        Input.displayAndConfirm(employee.toStringV2());

        if (ctrl.registerEmployee(employee)) {
            Print.text("Employee successfully registered");
        } else {
            System.out.println("Employee not registered");
        }
    }
}
