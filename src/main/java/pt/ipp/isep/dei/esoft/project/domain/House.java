package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.Input;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a house property
 */
public class House implements Property, Residence, Serializable {

    private static final long serialVersionUID = 1501354573211776992L;

    private PropertyInfo property;
    private ResidenceInfo residence;
    private boolean loft;
    private boolean basement;
    private String sunExposure;

    /**
     * Creates a House object based on the provided PropertyInfo, ResidenceInfo, and additional parameters.
     *
     * @param property    The PropertyInfo object containing the property details.
     * @param residence   The ResidenceInfo object containing the residence details.
     * @param loft        Indicates if the house has a loft.
     * @param basement    Indicates if the house has a basement.
     * @param sunExposure The sun exposure of the house.
     */
    public House(PropertyInfo property, ResidenceInfo residence, boolean loft, boolean basement, String sunExposure) {
        this.property = property;
        this.residence = residence;
        this.loft = loft;
        this.basement = basement;
        this.sunExposure = sunExposure;
    }

    /**
     * Creates a House object based on the provided PropertyInfo, ResidenceInfo, and additional parameters.
     *
     * @param property    The PropertyInfo object containing the property details.
     * @param residence   The ResidenceInfo object containing the residence details.
     * @param loft        Indicates if the house has a loft.
     * @param basement    Indicates if the house has a basement.
     * @param sunExposure The sun exposure of the house.
     * @return The created House object.
     * @throws InvalidArguments if the provided property or residence information is invalid.
     */
    public static House createProperty(PropertyInfo property, ResidenceInfo residence, boolean loft, boolean basement, String sunExposure) throws InvalidArguments {
        if (validateHouse(property, residence, loft, basement, sunExposure))
            return new House(property, residence, loft, basement, sunExposure);
        throw new InvalidArguments("Invalid House");
    }

    /**
     * Validates the provided house details.
     *
     * @param property    The PropertyInfo object containing the property details.
     * @param residence   The ResidenceInfo object containing the residence details.
     * @param loft        Indicates if the house has a loft.
     * @param basement    Indicates if the house has a basement.
     * @param sunExposure The sun exposure of the house.
     * @return true if the house details are valid, false otherwise.
     */
    private static boolean validateHouse(PropertyInfo property, ResidenceInfo residence, boolean loft, boolean basement, String sunExposure) {
        // Perform validation logic here
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
    public String getNumberOfBedrooms() {
        return residence.getNumBedrooms();
    }

    @Override
    public String getNumberOfBathrooms() {
        return residence.getNumBathrooms();
    }

    @Override
    public String getNumberOfParkingSpaces() {
        return residence.getNumParkingSpaces();
    }

    /**
     * Retrieves the House object with user-specified information.
     *
     * @return The House object with user-specified information.
     */
    public static House getInfo() {
        return new House(PropertyInfo.getInfo(), ResidenceInfo.getInfo(), Input.getBooleanWithLabel("Loft? [Y/N]"),
                Input.getBooleanWithLabel("Basement? [Y/N]"), Input.requestSunExposure());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(property, house.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(property, residence, loft, basement, sunExposure);
    }

    @Override
    public String toString() {
        return "House\n" + property.toString() + "; " + residence.toString() + "; Loft: " + loft + "; Basement: "
                + basement + "; Sun Exposure: " + sunExposure;
    }
}


