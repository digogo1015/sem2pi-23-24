package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;

/**
 * The FilterRepository class provides access to various filters and their corresponding lists of values.
 * It follows the singleton design pattern.
 */
public class FilterRepository {
    private static final FilterRepository instance = new FilterRepository();
    private TypesOfProperty typesOfProperty = new TypesOfProperty();
    private TypesOfBusiness typesOfBusiness = new TypesOfBusiness();
    private NumberOfBedrooms numberOfBedrooms = new NumberOfBedrooms();
    private TypeOfSortingCriteria sortingCriteria = new TypeOfSortingCriteria();
    private TypeOfCommission typeOfCommission = new TypeOfCommission();
    private TypeOfSunExposure typeOfSunExposure = new TypeOfSunExposure();

    /**
     * Retrieves the instance of the FilterRepository.
     *
     * @return The instance of the FilterRepository.
     */
    public static FilterRepository getInstance() {
        return instance;
    }

    /**
     * Retrieves the TypesOfProperty object containing the list of property types.
     *
     * @return The TypesOfProperty object.
     */
    public TypesOfProperty getTypesOfProperty() {
        return typesOfProperty;
    }

    /**
     * Retrieves the TypesOfBusiness object containing the list of business types.
     *
     * @return The TypesOfBusiness object.
     */
    public TypesOfBusiness getTypesOfBusiness() {
        return typesOfBusiness;
    }

    /**
     * Retrieves the NumberOfBedrooms object containing the list of number of bedrooms options.
     *
     * @return The NumberOfBedrooms object.
     */
    public NumberOfBedrooms getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    /**
     * Retrieves the TypeOfSortingCriteria object containing the list of sorting criteria options.
     *
     * @return The TypeOfSortingCriteria object.
     */
    public TypeOfSortingCriteria getSortingCriteria() {
        return sortingCriteria;
    }

    /**
     * Retrieves the TypeOfCommission object containing the list of commission types.
     *
     * @return The TypeOfCommission object.
     */
    public TypeOfCommission getTypeOfCommission() {
        return typeOfCommission;
    }

    /**
     * Retrieves the TypeOfSunExposure object containing the list of sun exposure types.
     *
     * @return The TypeOfSunExposure object.
     */
    public TypeOfSunExposure getTypesOfSunExposure() {
        return typeOfSunExposure;
    }

    /**
     * Retrieves a copy of the list of sorting criteria values.
     *
     * @return An ArrayList containing the sorting criteria values.
     */
    public ArrayList<String> getListOfSortingCriteria() {
        return getSortingCriteria().copy();
    }

    /**
     * Retrieves a copy of the list of property types.
     *
     * @return An ArrayList containing the property types.
     */
    public ArrayList<String> getListOfTypeOfProperty() {
        return getTypesOfProperty().copy();
    }

    /**
     * Retrieves a copy of the list of business types.
     *
     * @return An ArrayList containing the business types.
     */
    public ArrayList<String> getListOfTypeOfBusiness() {
        return getTypesOfBusiness().copy();
    }

    /**
     * Retrieves a copy of the list of number of bedrooms options.
     *
     * @return An ArrayList containing the number of bedrooms options.
     */
    public ArrayList<String> getListOfNumberOfBedrooms() {
        return getNumberOfBedrooms().copy();
    }

    /**
     * Retrieves a copy of the list of sun exposure types.
     *
     * @return An ArrayList containing the sun exposure types.
     */
    public ArrayList<String> getListOfSunExposure() {
        return getTypesOfSunExposure().copy();
    }

    /**
     * Retrieves a copy of the list of commission types.
     *
     * @return An ArrayList containing the commission types.
     */
    public ArrayList<String> getListOfCommission() {
        return getTypeOfCommission().copy();
    }
}
