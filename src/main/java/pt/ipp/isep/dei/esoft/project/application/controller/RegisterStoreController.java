package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

/**
 * This class is a controller responsible for registering stores.
 */
public class RegisterStoreController {

    // Instance variables
    private Repositories repositories;
    private StoreRepository storeRepository;

    /**
     * Constructs a new RegisterStoreController object.
     * Initializes the repositories and storeRepository instances.
     */
    public RegisterStoreController() {
        this.repositories = Repositories.getInstance();
        this.storeRepository = repositories.getStoreRepository();
    }

    /**
     * Registers a store by adding it to the store repository.
     *
     * @param store The store to be registered.
     * @return true if the store was registered successfully, false otherwise.
     */
    public boolean registerStore(Store store) {
        return storeRepository.addStore(store);
    }
}
