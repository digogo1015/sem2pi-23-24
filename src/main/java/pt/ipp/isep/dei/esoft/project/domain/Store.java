package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.Input;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;
import pt.ipp.isep.dei.esoft.project.utils.Print;

import java.io.Serializable;
import java.util.Objects;

import static pt.ipp.isep.dei.esoft.project.utils.Validations.validateEmail;
import static pt.ipp.isep.dei.esoft.project.utils.Validations.validateTelephoneNumber;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Store class represents a store with address, designation, email, and telephone number.
 */
public class Store implements Serializable {

    private static final long serialVersionUID = 6672557515004455743L;

    private Address address;
    private String designation;
    private String email;
    private String telephoneNumber;

    /**
     * Constructs a new Store object with the specified address, designation, email, and telephone number.
     *
     * @param address         The address of the store.
     * @param designation     The designation or title of the store.
     * @param email           The email address of the store.
     * @param telephoneNumber The telephone number of the store.
     */
    public Store(Address address, String designation, String email, String telephoneNumber) {
        this.address = address;
        this.designation = designation;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Creates a new instance of Store based on the provided parameters.
     *
     * @param address         The address of the store.
     * @param designation     The designation or title of the store.
     * @param email           The email address of the store.
     * @param telephoneNumber The telephone number of the store.
     * @return A new Store object.
     * @throws InvalidArguments If the provided arguments are invalid or null.
     */
    public static Store createStore(Address address, String designation, String email, String telephoneNumber) throws InvalidArguments {
        if (validateStore(designation, email, telephoneNumber))
            return new Store(address, designation, email, telephoneNumber);
        throw new InvalidArguments("Invalid Store");
    }

    /**
     * Validates the parameters of a store.
     *
     * @param designation     The designation or title of the store.
     * @param email           The email address of the store.
     * @param telephoneNumber The telephone number of the store.
     * @return True if the store parameters are valid.
     */
    private static boolean validateStore(String designation, String email, String telephoneNumber) {
        return !designation.isBlank() && validateEmail(email) && validateTelephoneNumber(telephoneNumber);
    }

    /**
     * Retrieves store information from user input and creates a new Store object.
     *
     * @return A new Store object with the provided information.
     */
    public static Store getInfo() {
        do {
            try {
                Address address = Address.requestAddress();
                String designation = Input.requestName();
                String email = Input.requestEmail();
                String phoneNumber = Input.requestPhoneNumber();

                return Store.createStore(address, designation, email, phoneNumber);
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    /**
     * Checks if the store has the same designation as the provided designation.
     *
     * @param designation The designation to compare.
     * @return True if the store has the same designation.
     */
    public boolean hasSameDesignation(String designation) {
        return this.designation.equals(designation);
    }

    /**
     * Gets the designation or title of the store.
     *
     * @return The designation.
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Gets the email address of the store.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the telephone number of the store.
     *
     * @return The telephone number.
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(designation, store.designation) || Objects.equals(email, store.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, designation, email, telephoneNumber);
    }

    @Override
    public String toString() {
        return "Store: " + designation + "; " + address.toString();
    }

    /**
     * Returns a string representation of the Store object with detailed information.
     *
     * @return The string representation.
     */
    public String toStringV2() {
        return "\nAddress: " + address +
                "\nDesignation: " + designation +
                "\nEmail: " + email +
                "\nPhone Number: " + telephoneNumber;
    }
}
