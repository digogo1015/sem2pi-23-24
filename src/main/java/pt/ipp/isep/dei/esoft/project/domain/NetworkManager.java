package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import java.io.Serializable;
import java.util.Objects;

/**
 * The `NetworkManager` class represents a network manager employee.
 * It implements the `Employee` and `Person` interfaces and is serializable.
 */
public class NetworkManager implements Employee, Person, Serializable {

    private static final long serialVersionUID = 6275301892700272739L;
    private EmployeeInfo employee;

    /**
     * Constructs a `NetworkManager` object with the provided employee information.
     *
     * @param employee The employee information.
     */
    public NetworkManager(EmployeeInfo employee) {
        this.employee = employee;
    }

    /**
     * Creates a Network Manager employee based on the provided employee information.
     *
     * @param employee The employee information.
     * @return The created Network Manager employee.
     * @throws InvalidArguments If the employee information is invalid.
     */
    public static Employee createEmployee(EmployeeInfo employee) throws InvalidArguments {
        if (validateEmployee(employee))
            return new NetworkManager(employee);
        throw new InvalidArguments("Invalid Network Manager");
    }

    /**
     * Validates the provided employee information.
     *
     * @param employee The employee information to validate.
     * @return `true` if the employee information is valid, `false` otherwise.
     */
    private static boolean validateEmployee(EmployeeInfo employee) {
        return true;
    }

    /**
     * Checks if the employee has a specific role.
     *
     * @param role The role to check.
     * @return `true` if the employee has the specified role, `false` otherwise.
     */
    @Override
    public boolean hasRole(String role) {
        return employee.hasRole(role);
    }

    /**
     * Checks if the employee works in a specific store.
     *
     * @param store The store to check.
     * @return `true` if the employee works in the specified store, `false` otherwise.
     */
    @Override
    public boolean worksInStore(String store) {
        return employee.worksInStore(store);
    }

    /**
     * Gets the person information associated with the employee.
     *
     * @return The person information.
     */
    @Override
    public PersonInfo getPerson() {
        return employee.getPerson();
    }

    /**
     * Checks if the employee has a specific email address.
     *
     * @param email The email address to check.
     * @return `true` if the employee has the specified email address, `false` otherwise.
     */
    @Override
    public boolean hasEmail(String email) {
        return this.getPerson().getEmail().toString().equals(email);
    }

    /**
     * Checks if this `NetworkManager` is equal to another object.
     *
     * @param o The object to compare.
     * @return `true` if the objects are equal, `false` otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetworkManager that = (NetworkManager) o;
        return Objects.equals(employee, that.employee);
    }

    /**
     * Generates the hash code for this `NetworkManager` object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(employee);
    }

    /**
     * Gets the role of the employee.
     *
     * @return The role of the employee.
     */
    @Override
    public String getRole() {
        return employee.getRole().getDesignation();
    }

    /**
     * Gets the store associated with the employee.
     *
     * @return The store associated with the employee.
     */
    @Override
    public Store getStore() {
        return employee.getStore();
    }

    /**
     * Returns a string representation of the `NetworkManager` object.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "Network Manager: " + employee.toString();
    }

    /**
     * Returns an alternative string representation of the `NetworkManager` object.
     *
     * @return The alternative string representation.
     */
    @Override
    public String toStringV2() {
        return "\nNetworkManager: " + employee.toString();
    }
}
