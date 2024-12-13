package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents an agent who is both an employee and a person.
 */
public class Agent implements Employee, Person, Serializable {

    private static final long serialVersionUID = 6634933581884788155L;
    private EmployeeInfo employee;

    /**
     * Constructs an Agent object with the provided EmployeeInfo.
     *
     * @param employee The EmployeeInfo object containing the employee details.
     */
    public Agent(EmployeeInfo employee) {
        this.employee = employee;
    }

    /**
     * Creates an Employee object based on the provided EmployeeInfo.
     *
     * @param employee The EmployeeInfo object containing the employee details.
     * @return The created Employee object.
     * @throws InvalidArguments if the provided employee information is invalid.
     */
    public static Employee createEmployee(EmployeeInfo employee) throws InvalidArguments {
        if (validateEmployee(employee))
            return new Agent(employee);
        throw new InvalidArguments("Invalid Agent");
    }

    private static boolean validateEmployee(EmployeeInfo employee) {
        // Add validation logic here
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
    public boolean hasEmail(String email) {
        return this.getPerson().getEmail().equals(email);
    }

    @Override
    public PersonInfo getPerson() {
        return employee.getPerson();
    }

    /**
     * Retrieves the store associated with the agent.
     *
     * @return The Store object representing the store associated with the agent.
     */
    public Store getStore() {
        return employee.getStore();
    }

    @Override
    public String toString() {
        return "Agent: " + employee;
    }

    @Override
    public String getRole() {
        return employee.getRole().getDesignation();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return Objects.equals(employee, agent.employee);
    }

    /**
     * Returns a string representation of the Agent object.
     *
     * @return A string representation of the Agent object.
     */
    @Override
    public String toStringV2() {
        return "\nAgent: " + employee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee);
    }
}