package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import java.io.Serializable;
import java.util.Objects;

import java.io.Serializable;
import java.util.Objects;

/**
 * The SystemAdministrator class represents a system administrator who is an employee and a person.
 */
public class SystemAdministrator implements Employee, Person, Serializable {

    private static final long serialVersionUID = 8797308384519098860L;
    private EmployeeInfo employee;

    /**
     * Constructs a new SystemAdministrator object with the provided EmployeeInfo.
     *
     * @param employee The EmployeeInfo object containing the system administrator's information.
     */
    public SystemAdministrator(EmployeeInfo employee) {
        this.employee = employee;
    }

    /**
     * Creates a new instance of Employee based on the provided EmployeeInfo object.
     *
     * @param employee The EmployeeInfo object containing the employee's information.
     * @return A new Employee object.
     * @throws InvalidArguments If the provided EmployeeInfo object is invalid or null.
     */
    public static Employee createEmployee(EmployeeInfo employee) throws InvalidArguments {
        if (validateEmployee(employee))
            return new SystemAdministrator(employee);
        throw new InvalidArguments("Invalid Agent");
    }

    /**
     * Validates the EmployeeInfo object.
     *
     * @param employee The EmployeeInfo object to validate.
     * @return True if the EmployeeInfo object is valid.
     */
    private static boolean validateEmployee(EmployeeInfo employee) {
        return true;
    }

    @Override
    public boolean hasRole(String role) {
        return employee.hasRole(role);
    }

    @Override
    public boolean worksInStore(String store) {
        return employee.worksInStore(store);
    }

    @Override
    public PersonInfo getPerson() {
        return employee.getPerson();
    }

    @Override
    public boolean hasEmail(String email) {
        return this.getPerson().getEmail().equals(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemAdministrator that = (SystemAdministrator) o;
        return Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee);
    }

    @Override
    public String getRole() {
        return employee.getRole().getDesignation();
    }

    @Override
    public Store getStore() {
        return employee.getStore();
    }

    @Override
    public String toString() {
        return "System Administrator: " + employee.toString();
    }

    /**
     * Returns a string representation of the SystemAdministrator object with detailed information.
     *
     * @return The string representation.
     */
    public String toStringV2() {
        return "System Administrator: " + employee.toString();
    }
}
