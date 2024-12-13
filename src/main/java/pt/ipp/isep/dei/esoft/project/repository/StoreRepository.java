package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * The StoreRepository class represents a repository of stores.
 * It provides methods to manage and access the store data.
 */
public class StoreRepository {

    private List<Store> storeList = new ArrayList<>();

    /**
     * Creates a copy of the store list.
     *
     * @return A new ArrayList containing a copy of the store list, or null if the store list is empty.
     */
    public ArrayList<Store> copy() {
        if (storeList != null)
            return new ArrayList<>(storeList);
        return null;
    }

    /**
     * Adds a store to the repository if it is valid.
     *
     * @param store The store to add.
     * @return true if the store is valid and successfully added, false otherwise.
     */
    public boolean addStore(Store store) {
        return validateStore(store) && storeList.add(store);
    }

    /**
     * Retrieves a store from the repository based on its designation.
     *
     * @param designation The designation of the store to retrieve.
     * @return The store with the specified designation, or null if no matching store is found.
     */
    public Store getStoreByDesignation(String designation) {
        ArrayList<Store> list = copy();
        for (Store s : list)
            if (s.getDesignation().equals(designation))
                return s;
        return null;
    }

    private boolean validateStore(Store store) {
        ArrayList<Store> list = copy();
        if (list != null)
            for (Store s : list)
                if (s.equals(store))
                    return false;
        return true;
    }
}
