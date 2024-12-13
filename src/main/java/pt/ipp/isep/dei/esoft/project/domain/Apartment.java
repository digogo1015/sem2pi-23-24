package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents an apartment property.
 */
public class Apartment implements Property, Residence, Serializable {
    private static final long serialVersionUID = 6549710963028323533L;
    private PropertyInfo property;
    private ResidenceInfo residence;

    /**
     * Constructs an Apartment object with the provided property and residence information.
     *
     * @param property  The PropertyInfo object containing the property details.
     * @param residence The ResidenceInfo object containing the residence details.
     */
    public Apartment(PropertyInfo property, ResidenceInfo residence) {
        this.property = property;
        this.residence = residence;
    }

    /**
     * Creates an Apartment object based on the provided PropertyInfo and ResidenceInfo.
     *
     * @param property  The PropertyInfo object containing the property details.
     * @param residence The ResidenceInfo object containing the residence details.
     * @return The created Apartment object.
     * @throws InvalidArguments if the provided property or residence information is invalid.
     */
    public static Apartment createProperty(PropertyInfo property, ResidenceInfo residence) throws InvalidArguments {
        if (validateApartment(property, residence))
            return new Apartment(property, residence);
        throw new InvalidArguments("Invalid Apartment");
    }

    private static boolean validateApartment(PropertyInfo property, ResidenceInfo residence) {
        return true;
    }

    @Override
    public boolean hasPropertyType(String typeOfProperty) {
        return this.getClass().getSimpleName().equals(typeOfProperty);
    }

    @Override
    public Address getAddressOfProperty() {
        return property.getAddress();
    }

    @Override
    public String getArea() {
        return property.getArea();
    }

    @Override
    public String getCityCenter() {
        return property.getCityCenter();
    }

    @Override
    public boolean hasNumberOfBedrooms(String numberOfBedrooms) {
        return this.residence.hasNumberOfBedrooms(numberOfBedrooms);
    }

    @Override
    public String getNumberOfBathrooms() {
        return residence.getNumBathrooms();
    }

    @Override
    public String getNumberOfBedrooms() {
        return residence.getNumBedrooms();
    }

    @Override
    public String getNumberOfParkingSpaces() {
        return residence.getNumParkingSpaces();
    }

    /**
     * Retrieves property and residence information for the apartment.
     *
     * @return A Property object containing the apartment information.
     */
    public static Property getInfo() {
        return new Apartment(PropertyInfo.getInfo(), ResidenceInfo.getInfo());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return Objects.equals(property, apartment.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(property, residence);
    }

    @Override
    public String toString() {
        return "Apartment\n" + property.toString() + residence.toString();
    }
}
