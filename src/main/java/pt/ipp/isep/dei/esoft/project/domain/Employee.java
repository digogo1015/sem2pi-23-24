package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Represents an employee.
 */
public interface Employee {

    /**
     * Checks if the employee has the specified role.
     *
     * @param role The role to check.
     * @return true if the employee has the specified role, false otherwise.
     */
    boolean hasRole(String role);

    /**
     * Checks if the employee works in the specified store.
     *
     * @param store The store to check.
     * @return true if the employee works in the specified store, false otherwise.
     */
    boolean worksInStore(String store);

    /**
     * Retrieves the person information of the employee.
     *
     * @return The person information of the employee.
     */
    PersonInfo getPerson();

    /**
     * Retrieves the role of the employee.
     *
     * @return The role of the employee.
     */
    String getRole();

    /**
     * Retrieves the store of the employee.
     *
     * @return The store of the employee.
     */
    Store getStore();

    /**
     * Checks if the employee has the specified email.
     *
     * @param email The email to check.
     * @return true if the employee has the specified email, false otherwise.
     */
    boolean hasEmail(String email);

    /**
     * Compares the employee with the specified object for equality.
     *
     * @param o The object to compare.
     * @return true if the employee is equal to the specified object, false otherwise.
     */
    boolean equals(Object o);

    /**
     * Retrieves a string representation of the employee.
     *
     * @return A string representation of the employee.
     */
    String toStringV2();
}
