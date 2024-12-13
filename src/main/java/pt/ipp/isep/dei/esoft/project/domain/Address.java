package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.lang3.StringUtils;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;
import pt.ipp.isep.dei.esoft.project.utils.Print;

import java.io.Serializable;
import java.util.Objects;

import static pt.ipp.isep.dei.esoft.project.utils.Input.getStringWithLabel;

/**
 * The Address class represents an address with street, city, district, and state components.
 * It provides methods for creating, validating, and manipulating address objects.
 */
public class Address implements Serializable {

    private static final long serialVersionUID = 1191888213095711820L;
    private String street;
    private String city;
    private String district;
    private String state;

    /**
     * Creates an Address object with the provided parameters.
     *
     * @param street   The street name of the address.
     * @param city     The city name of the address.
     * @param district The district name of the address.
     * @param state    The state name of the address.
     */
    public Address(String street, String city, String district, String state) {
        this.street = street;
        this.city = city;
        this.district = district;
        this.state = state;
    }

    /**
     * Creates an Address object with the provided parameters.
     *
     * @param street   The street name of the address.
     * @param city     The city name of the address.
     * @param district The district name of the address.
     * @param state    The state name of the address.
     * @return The created Address object.
     * @throws InvalidArguments if any of the address parameters are invalid.
     */
    public static Address createAddress(String street, String city, String district, String state) throws InvalidArguments {
        if (validateAddress(street, city, district, state))
            return new Address(street, city, district, state);
        throw new InvalidArguments("Invalid Address");
    }

    /**
     * Validates the provided address parameters.
     *
     * @param state    The state name of the address.
     * @param district The district name of the address.
     * @param city     The city name of the address.
     * @param street   The street name of the address.
     * @return true if the address parameters are valid.
     */
    public static boolean validateAddress(String state, String district, String city, String street) {
        return !(StringUtils.isBlank(state) || StringUtils.isBlank(street) || StringUtils.isBlank(city));
    }

    /**
     * Requests user input to create an Address object.
     *
     * @return The created Address object.
     */
    public static Address requestAddress() {
        do {
            try {
                Print.text("Address:");
                String state = getStringWithLabel("State:");
                String district = getStringWithLabel("District:");
                String city = getStringWithLabel("City:");
                String street = getStringWithLabel("Street:");

                return Address.createAddress(state, district, city, street);
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    /**
     * Splits the provided store location string into address components and creates an Address object.
     *
     * @param storeLocation The store location string to be split.
     * @return The created Address object.
     */
    public static Address splitLocation(String storeLocation) {
        do {
            try {
                String[] address = storeLocation.split(",");

                return Address.createAddress(address[0], address[1], address[2], address[3]);
            } catch (Exception ignored) {
            }
        } while (true);
    }

    /**
     * Retrieves the state name of the address.
     *
     * @return The state name of the address.
     */
    public String getState() {
        return state;
    }

    /**
     * Retrieves the city name of the address.
     *
     * @return The city name of the address.
     */
    public String getCity() {
        return city;
    }

    /**
     * Checks if this Address object is equal to another object.
     *
     * @param o The object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(state, address.state) && Objects.equals(district, address.district) && Objects.equals(city, address.city) && Objects.equals(street, address.street);
    }

    /**
     * Generates a hash code for this Address object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(state, district, city, street);
    }

    /**
     * Converts this Address object to a string representation.
     *
     * @return The string representation of the Address object.
     */
    @Override
    public String toString() {
        return "Address: " + street + "," + city + "," + district + "," + state;
    }
}
