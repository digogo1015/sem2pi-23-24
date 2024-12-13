package pt.ipp.isep.dei.esoft.project.domain;

/**
 * The Property interface represents a property with various attributes.
 */
public interface Property {

    /**
     * Checks if the property has the specified type.
     *
     * @param typeOfProperty The type of property to check.
     * @return true if the property has the specified type, false otherwise.
     */
    boolean hasPropertyType(String typeOfProperty);

    /**
     * Returns the address of the property.
     *
     * @return The address of the property.
     */
    Address getAddressOfProperty();

    /**
     * Returns the area of the property.
     *
     * @return The area of the property.
     */
    String getArea();

    /**
     * Returns the distance from the property to the city center.
     *
     * @return The distance from the property to the city center.
     */
    String getCityCenter();

    /**
     * Returns a string representation of the property.
     *
     * @return The string representation of the property.
     */
    String toString();
}
