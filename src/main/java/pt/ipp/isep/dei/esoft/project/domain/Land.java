package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a land property.
 */
public class Land implements Property, Serializable {

    private static final long serialVersionUID = 7232948142771072815L;

    private PropertyInfo property;

    /**
     * Creates a Land object based on the provided PropertyInfo.
     *
     * @param property The PropertyInfo object containing the property details.
     */
    public Land(PropertyInfo property) {
        this.property = property;
    }

    /**
     * Creates a Land object based on the provided PropertyInfo.
     *
     * @param property The PropertyInfo object containing the property details.
     * @return The created Land object.
     * @throws InvalidArguments if the provided property information is invalid.
     */
    public static Land createProperty(PropertyInfo property) throws InvalidArguments {
        if (validateLand(property))
            return new Land(property);
        throw new InvalidArguments("Invalid Land");
    }

    /**
     * Validates the provided land details.
     *
     * @param property The PropertyInfo object containing the property details.
     * @return true if the land details are valid, false otherwise.
     */
    private static boolean validateLand(PropertyInfo property) {
        // Perform validation logic here
        return true;
    }

    /**
     * Retrieves the city center of the land property.
     *
     * @return The city center of the land property.
     */
    public String getCityCenter() {
        return property.getCityCenter();
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
        return this.property.getArea();
    }

    /**
     * Retrieves the Land object with user-specified information.
     *
     * @return The Land object with user-specified information.
     */
    public static Land getInfo() {
        return new Land(PropertyInfo.getInfo());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Land land = (Land) o;
        return Objects.equals(property, land.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(property);
    }

    @Override
    public String toString() {
        return "Land\n" + property.toString();
    }
}