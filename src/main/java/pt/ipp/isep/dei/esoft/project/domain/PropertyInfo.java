package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.database.Database;
import pt.ipp.isep.dei.esoft.project.utils.Input;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The PropertyInfo class represents information about a property.
 */
public class PropertyInfo implements Serializable {

    private static final long serialVersionUID = 8747832070375616588L;

    private Address address;
    private String area;
    private String distCityCenter;
    private ArrayList<Photograph> photographs;

    /**
     * Creates a new PropertyInfo object with the provided address, area, distance to city center,
     * and list of photographs.
     *
     * @param address        The address of the property.
     * @param area           The area of the property.
     * @param distCityCenter The distance of the property to the city center.
     * @param photographs    The list of photographs associated with the property.
     */
    public PropertyInfo(Address address, String area, String distCityCenter, ArrayList<Photograph> photographs) {
        this.address = address;
        this.area = area;
        this.distCityCenter = distCityCenter;
        this.photographs = photographs;
    }

    /**
     * Creates a new PropertyInfo object with the provided address, area, distance to city center,
     * and list of photographs.
     *
     * @param address        The address of the property.
     * @param area           The area of the property.
     * @param distCityCenter The distance of the property to the city center.
     * @param photographs    The list of photographs associated with the property.
     * @return A new PropertyInfo object.
     * @throws InvalidArguments If the provided arguments fail validation.
     */
    public PropertyInfo createPropertyInfo(Address address, String area, String distCityCenter, ArrayList<Photograph> photographs) throws InvalidArguments {
        if (validatePropertyInfo(address, area, distCityCenter, photographs))
            return new PropertyInfo(address, area, distCityCenter, photographs);
        throw new InvalidArguments("Invalid Property Info");
    }

    /**
     * Validates the provided property information.
     *
     * @param address        The address of the property.
     * @param area           The area of the property.
     * @param distCityCenter The distance of the property to the city center.
     * @param photographs    The list of photographs associated with the property.
     * @return true if the property information is valid.
     */
    private boolean validatePropertyInfo(Address address, String area, String distCityCenter, ArrayList<Photograph> photographs) {
        return true;
    }

    /**
     * Retrieves property information by gathering data from the user.
     *
     * @return A new PropertyInfo object with user-provided information.
     */
    public static PropertyInfo getInfo() {
        return new PropertyInfo(Address.requestAddress(), Input.requestNumber("Area:"),
                Input.requestNumber("Distance to City Center:"), Input.requestPhotographs());
    }

    /**
     * Retrieves property data based on the given property type.
     *
     * @param propertyType The type of property for which data is requested.
     * @return An instance of the Property class containing the requested data if the retrieval is successful.
     */
    static public Property requestPropertyData(String propertyType) {
        try {
            Class<?> className = Class.forName(Database.domainPath + propertyType);

            return (Property) className.getMethod("getInfo").invoke(null);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Returns the address of the property.
     *
     * @return The address of the property.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns the area of the property.
     *
     * @return The area of the property.
     */
    public String getArea() {
        return area;
    }

    /**
     * Returns the distance from the property to the city center.
     *
     * @return The distance from the property to the city center.
     */
    public String getCityCenter() {
        return distCityCenter;
    }

    /**
     * Returns the distance from the property to the city center.
     *
     * @return The distance from the property to the city center.
     */
    public String getDistCityCenter() {
        return distCityCenter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyInfo property = (PropertyInfo) o;
        return Objects.equals(address, property.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, area, distCityCenter);
    }

    @Override
    public String toString() {
        return "Area: " + area + "; Distance from City Center: " + distCityCenter + "; " + address.toString();
    }
}
