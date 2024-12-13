package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Advertisement;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.ClientRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;

/**
 * This class is a controller responsible for managing purchase order requests.
 */
public class RequestPurchaseOrderController {

    // Instance variables
    private Repositories repositories;
    private AdvertisementRepository advertisementRepository;
    private ClientRepository clientRepository;

    /**
     * Constructs a new RequestPurchaseOrderController object.
     * Initializes the repositories, advertisementRepository, and clientRepository instances.
     */
    public RequestPurchaseOrderController() {
        this.repositories = Repositories.getInstance();
        this.advertisementRepository = repositories.getAdvertisementRepository();
        this.clientRepository = repositories.getClientRepository();
    }

    /**
     * Retrieves a client from the client repository based on their email.
     *
     * @param userEmail The email of the client.
     * @return The Client object if found, or null if not found.
     */
    public Client getClientByEmail(String userEmail) {
        return clientRepository.getClientByEmail(userEmail);
    }

    /**
     * Retrieves a list of advertisements that are not sold.
     *
     * @return An ArrayList of Advertisement objects representing the advertisements not sold.
     */
    public ArrayList<Advertisement> getListOfAdvertisements() {
        return advertisementRepository.getAdvertisementsNotSold();
    }

    /**
     * Adds a purchase order to the specified advertisement.
     *
     * @param purchaseOrder The purchase order to be added.
     * @param advertisement The advertisement to which the purchase order will be added.
     * @return true if the purchase order was added successfully, false otherwise.
     */
    public boolean addPurchaseOrder(PurchaseOrder purchaseOrder, Advertisement advertisement) {
        return advertisementRepository.addPurchaseOrder(purchaseOrder, advertisement);
    }
}
