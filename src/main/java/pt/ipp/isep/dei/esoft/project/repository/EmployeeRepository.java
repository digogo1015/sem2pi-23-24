package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.database.Database;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.PersonInfo;
import pt.ipp.isep.dei.esoft.project.utils.PasswordValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * The EmployeeRepository class provides methods to interact with the employee data and perform employee-related operations.
 */
public class EmployeeRepository {
    private List<Employee> employeeList = new ArrayList<>();

    /**
     * Adds a new employee to the repository.
     *
     * @param employee The employee to be added.
     * @return true if the employee is successfully added, false otherwise.
     */
    public boolean addEmployee(Employee employee) {
        return validateEmployee(employee) && employeeList.add(employee);
    }

    /**
     * Creates a copy of the employee list.
     *
     * @return A new ArrayList containing the employees, or null if the list is null.
     */
    public ArrayList<Employee> copy() {
        if (employeeList != null)
            return new ArrayList<>(employeeList);
        return null;
    }

    /**
     * Validates an employee to ensure it is not already in the repository.
     *
     * @param employee The employee to be validated.
     * @return true if the employee is valid (not already in the repository), false otherwise.
     */
    public boolean validateEmployee(Employee employee) {
        for (Employee e : this.copy())
            if (employee.equals(e))
                return false;
        return true;
    }

    /**
     * Retrieves a list of employees with a specific role.
     *
     * @param role The role to filter the employees.
     * @return An ArrayList containing the employees with the specified role.
     */
    public ArrayList<Employee> getEmployeesByRole(String role) {
        ArrayList<Employee> newList = new ArrayList<>();

        for (Employee e : this.copy())
            if (e.hasRole(role))
                newList.add(e);
        return newList;
    }

    /**
     * Retrieves a list of employees who work in a specific store.
     *
     * @param store The store to filter the employees.
     * @return An ArrayList containing the employees who work in the specified store.
     */
    public ArrayList<Employee> getEmployeesByStore(String store) {
        ArrayList<Employee> newList = new ArrayList<>();

        for (Employee e : this.copy())
            if (e.worksInStore(store))
                newList.add(e);
        return newList;
    }

    /**
     * Retrieves an employee by email.
     *
     * @param email The email of the employee to retrieve.
     * @return The Employee object with the specified email, or null if not found.
     */
    public Employee getEmployeeByEmail(String email) {
        for (Employee e : this.copy())
            if (e.hasEmail(email))
                return e;
        return null;
    }

    /**
     * Registers an employee by adding them to the authentication repository and the employee repository.
     * Generates a password for the employee, sends an email with the password, and saves the user in the database.
     *
     * @param employee The employee to be registered.
     * @return true if the employee is successfully registered, false otherwise.
     */
    public boolean registerEmployee(Employee employee) {
        String password = PasswordValidator.generatePassword();
        PersonInfo person = employee.getPerson();
        String email = person.getEmail();

        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();

        if (authenticationRepository.addUserWithRole(person.getName(), email, password, employee.getRole()) &&
                (addEmployee(employee))) {
            Database.sendEmail(email, password);
            Database.saveUsers(email, password);
            return true;
        }
        return false;
    }
}
