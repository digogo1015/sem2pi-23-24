package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

import java.util.ArrayList;

/**
 * The DivideStoresController class is a controller responsible for handling store-related operations
 * in a divide stores application.
 */
public class DivideStoresController {

    private Repositories repositories;
    private StoreRepository storeRepository;
    private AdvertisementRepository advertisementRepository;

    /**
     * Constructs a new instance of DivideStoresController.
     * Initializes the repositories and sets the storeRepository and advertisementRepository
     * based on the repositories instance.
     */
    public DivideStoresController() {
        this.repositories = Repositories.getInstance();
        this.storeRepository = repositories.getStoreRepository();
        this.advertisementRepository = repositories.getAdvertisementRepository();
    }

    /**
     * Returns a list of Store objects.
     *
     * @return An ArrayList of Store objects.
     */
    public ArrayList<Store> getStores() {
        return storeRepository.copy();
    }

    /**
     * Returns the number of properties associated with a specific store designation.
     *
     * @param designation The designation of the store to retrieve the number of properties for.
     * @return The number of properties associated with the store designation.
     */
    public int getNumPropertiesByStoreDesignation(String designation) {
        return advertisementRepository.getNumPropertiesByDesignation(designation);
    }
}