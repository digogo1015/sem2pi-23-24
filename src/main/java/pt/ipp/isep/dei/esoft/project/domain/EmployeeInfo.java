package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import java.io.Serializable;
import java.util.Objects;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the information of an employee.
 */
public class EmployeeInfo implements Serializable {

    private static final long serialVersionUID = 559720447912099328L;

    private PersonInfo person;
    private Store store;
    private Role role;

    /**
     * Creates an EmployeeInfo object based on the provided PersonInfo, Store, and Role.
     *
     * @param personInfo         The PersonInfo object containing the employee's personal information.
     * @param storeByDesignation The Store object representing the store where the employee works.
     * @param roleByDesignation  The Role object representing the role of the employee.
     * @return The created EmployeeInfo object.
     * @throws InvalidArguments if the provided employee information is invalid.
     */
    public static EmployeeInfo createEmployee(PersonInfo personInfo, Store storeByDesignation, Role roleByDesignation) throws InvalidArguments {
        if (validateInfo(personInfo, storeByDesignation, roleByDesignation))
            return new EmployeeInfo(personInfo, storeByDesignation, roleByDesignation);
        throw new InvalidArguments("Invalid Employee Info");
    }

    private static boolean validateInfo(PersonInfo personInfo, Store storeByDesignation, Role roleByDesignation) {
        return true;
    }

    /**
     * Constructs an EmployeeInfo object with the provided information.
     *
     * @param personInfo         The PersonInfo object containing the employee's personal information.
     * @param storeByDesignation The Store object representing the store where the employee works.
     * @param roleByDesignation  The Role object representing the role of the employee.
     */
    public EmployeeInfo(PersonInfo personInfo, Store storeByDesignation, Role roleByDesignation) {
        this.person = personInfo;
        this.store = storeByDesignation;
        this.role = roleByDesignation;
    }

    /**
     * Retrieves the store where the employee works.
     *
     * @return The store where the employee works.
     */
    public Store getStore() {
        return store;
    }

    /**
     * Retrieves the personal information of the employee.
     *
     * @return The personal information of the employee.
     */
    public PersonInfo getPerson() {
        return person;
    }

    /**
     * Retrieves the role of the employee.
     *
     * @return The role of the employee.
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Checks if the employee has the specified role.
     *
     * @param designation The designation of the role to check.
     * @return true if the employee has the specified role, false otherwise.
     */
    public boolean hasRole(String designation) {
        return this.role.equals(new Role(designation));
    }

    /**
     * Checks if the employee works in the specified store.
     *
     * @param store The store to check.
     * @return true if the employee works in the specified store, false otherwise.
     */
    public boolean worksInStore(String store) {
        return this.store.hasSameDesignation(store);
    }

    /**
     * Compares the employee information with the specified object for equality.
     *
     * @param o The object to compare.
     * @return true if the employee information is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeInfo employee = (EmployeeInfo) o;
        return Objects.equals(person, employee.person);
    }

     @Override
    public int hashCode() {
        return Objects.hash(person, store, role);
    }

    @Override
    public String toString() {
        return person.toStringV2();
    }
}
