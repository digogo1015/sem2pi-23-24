package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Advertisement;
import pt.ipp.isep.dei.esoft.project.domain.Filters;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.FilterRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;

/**
 * This class is a controller responsible for managing the list of advertisements and applying filters.
 */
public class ListPropertyController {

    // Instance variables
    private Repositories repositories;
    private AdvertisementRepository advertisementRepository;
    private FilterRepository filterRepository;

    /**
     * Constructs a new ListPropertyController object.
     * Initializes the repositories and advertisementRepository and filterRepository instances.
     */
    public ListPropertyController() {
        this.repositories = Repositories.getInstance();
        this.advertisementRepository = repositories.getAdvertisementRepository();
        this.filterRepository = repositories.getFilterRepository();
    }

    /**
     * Retrieves a list of advertisements that are not sold.
     *
     * @return An ArrayList containing Advertisement objects.
     */
    public ArrayList<Advertisement> getListOfAdvertisements() {
        return advertisementRepository.getAdvertisementsNotSold();
    }

    /**
     * Retrieves a list of sorting criteria.
     *
     * @return An ArrayList containing the available sorting criteria as strings.
     */
    public ArrayList<String> getListOfSortingCriteria() {
        return filterRepository.getListOfSortingCriteria();
    }

    /**
     * Retrieves a list of property types.
     *
     * @return An ArrayList containing the available types of property as strings.
     */
    public ArrayList<String> getListOfTypeOfProperty() {
        return filterRepository.getListOfTypeOfProperty();
    }

    /**
     * Retrieves a list of business types.
     *
     * @return An ArrayList containing the available types of business as strings.
     */
    public ArrayList<String> getListOfTypeOfBusiness() {
        return filterRepository.getListOfTypeOfBusiness();
    }

    /**
     * Retrieves a list of the number of bedrooms.
     *
     * @return An ArrayList containing the available number of bedrooms as strings.
     */
    public ArrayList<String> getListOfNumberOfBedrooms() {
        return filterRepository.getListOfNumberOfBedrooms();
    }

    /**
     * Adds an advertisement to the advertisement repository.
     *
     * @param advertisement The Advertisement object to be added.
     */
    public void addAdvertisement(Advertisement advertisement) {
        advertisementRepository.addAdvertisement(advertisement);
    }

    /**
     * Retrieves a list of advertisements based on the specified filters.
     *
     * @param filters The Filters object containing the filter criteria.
     * @return An ArrayList containing Advertisement objects that match the specified filters.
     */
    public ArrayList<Advertisement> getListOfAdvertisementsByFilters(Filters filters) {
        return advertisementRepository.getListOfAdvertisementsByFilter(filters);
    }
}