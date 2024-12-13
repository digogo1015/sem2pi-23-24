package pt.ipp.isep.dei.esoft.project.domain;

/**
 * The Residence interface represents a residential property.
 * It provides methods to access information about the number of bedrooms, bathrooms, and parking spaces in the property.
 */
public interface Residence {

    /**
     * Checks if the residence has a specific number of bedrooms.
     *
     * @param numberOfBedrooms The number of bedrooms to check.
     * @return true if the residence has the specified number of bedrooms, false otherwise.
     */
    boolean hasNumberOfBedrooms(String numberOfBedrooms);

    /**
     * Retrieves the number of bathrooms in the residence.
     *
     * @return The number of bathrooms as a string.
     */
    String getNumberOfBathrooms();

    /**
     * Retrieves the number of parking spaces in the residence.
     *
     * @return The number of parking spaces as a string.
     */
    String getNumberOfParkingSpaces();

    /**
     * Retrieves the number of bedrooms in the residence.
     *
     * @return The number of bedrooms as a string.
     */
    String getNumberOfBedrooms();
}
