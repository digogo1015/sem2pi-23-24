package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

/**
 * Represents filter criteria for property searches
 */
public class Filters {

    private String typeOfProperty;
    private String typeOfBusiness;
    private String numberOfBedrooms;
    private String sortingCriteria;

    /**
     * Creates a Filters object based on the provided filter criteria.
     *
     * @param typeOfProperty   The type of property.
     * @param typeOfBusiness   The type of business.
     * @param numberOfBedrooms The number of bedrooms.
     * @param sortingCriteria  The sorting criteria.
     */
    public Filters(String typeOfProperty, String typeOfBusiness, String numberOfBedrooms, String sortingCriteria) {
        this.typeOfProperty = typeOfProperty;
        this.typeOfBusiness = typeOfBusiness;
        this.numberOfBedrooms = numberOfBedrooms;
        this.sortingCriteria = sortingCriteria;
    }

    /**
     * Creates a Filters object based on the provided filter criteria.
     *
     * @param typeOfProperty   The type of property.
     * @param typeOfBusiness   The type of business.
     * @param numberOfBedrooms The number of bedrooms.
     * @param sortingCriteria  The sorting criteria.
     * @return The created Filters object.
     * @throws InvalidArguments if the provided filter criteria are invalid.
     */
    public static Filters createFilters(String typeOfProperty, String typeOfBusiness, String numberOfBedrooms, String sortingCriteria) throws InvalidArguments {
        if (validateFilters(typeOfProperty, typeOfBusiness, numberOfBedrooms, sortingCriteria))
            return new Filters(typeOfProperty, typeOfBusiness, numberOfBedrooms, sortingCriteria);
        throw new InvalidArguments("Invalid Filters");
    }

    /**
     * Validates the filter criteria.
     *
     * @param typeOfProperty   The type of property.
     * @param typeOfBusiness   The type of business.
     * @param numberOfBedrooms The number of bedrooms.
     * @param sortingCriteria  The sorting criteria.
     * @return true if the filter criteria are valid, false otherwise.
     */
    private static boolean validateFilters(String typeOfProperty, String typeOfBusiness, String numberOfBedrooms, String sortingCriteria) {
        // Perform validation logic here
        return true;
    }

    /**
     * Retrieves the type of property.
     *
     * @return The type of property.
     */
    public String getTypeOfProperty() {
        return typeOfProperty;
    }

    /**
     * Retrieves the type of business.
     *
     * @return The type of business.
     */
    public String getTypeOfBusiness() {
        return typeOfBusiness;
    }

    /**
     * Retrieves the number of bedrooms.
     *
     * @return The number of bedrooms.
     */
    public String getNumberOfBedrooms() {
        if (numberOfBedrooms != null)
            return numberOfBedrooms;
        return "";
    }

    /**
     * Retrieves the sorting criteria.
     *
     * @return The sorting criteria.
     */
    public String getSortingCriteria() {
        return sortingCriteria;
    }

    /**
     * Retrieves a string representation of the filters.
     *
     * @return A string representation of the filters.
     */
    @Override
    public String toString() {
        return "\nProperty Type: " + typeOfProperty +
                "\nBusiness Type: " + typeOfBusiness +
                "\nSorting Criteria: " + sortingCriteria;
    }
}
