package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Advertisement;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.ClientRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;

/**
 * This class is a controller responsible for managing visit requests.
 */
public class VisitRequestController {

    // Instance variables
    private Repositories repositories;
    private AdvertisementRepository advertisementRepository;
    private ClientRepository clientRepository;

    /**
     * Constructs a new VisitRequestController object.
     * Initializes the repositories, advertisementRepository, and clientRepository instances.
     */
    public VisitRequestController() {
        this.repositories = Repositories.getInstance();
        this.advertisementRepository = repositories.getAdvertisementRepository();
        this.clientRepository = repositories.getClientRepository();
    }

    /**
     * Retrieves a list of advertisements that are not sold.
     *
     * @return An ArrayList of Advertisement objects representing the advertisements not sold.
     */
    public ArrayList<Advertisement> getListOfAdvertisementsNotSold() {
        return advertisementRepository.getAdvertisementsNotSold();
    }

    /**
     * Retrieves a client from the client repository based on their email.
     *
     * @param email The email of the client.
     * @return The Client object if found, or null if not found.
     */
    public Client getClientByEmail(String email) {
        return clientRepository.getClientByEmail(email);
    }
}
