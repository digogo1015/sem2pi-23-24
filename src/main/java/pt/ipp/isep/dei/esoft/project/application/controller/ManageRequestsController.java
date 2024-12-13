package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Advertisement;
import pt.ipp.isep.dei.esoft.project.domain.Request;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.FilterRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.RequestRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a controller responsible for managing requests and advertisements.
 */
public class ManageRequestsController {

    // Instance variables
    private Repositories repositories;
    private AdvertisementRepository advertisementRepository;
    private FilterRepository filterRepository;
    private RequestRepository requestRepository;

    /**
     * Constructs a new ManageRequestsController object.
     * Initializes the repositories, advertisementRepository, requestRepository, and filterRepository instances.
     */
    public ManageRequestsController() {
        this.repositories = Repositories.getInstance();
        this.advertisementRepository = repositories.getAdvertisementRepository();
        this.requestRepository = repositories.getRequestRepository();
        this.filterRepository = repositories.getFilterRepository();
    }

    /**
     * Retrieves a list of requests associated with the agent's email.
     *
     * @param email The email of the agent.
     * @return A List of Request objects associated with the agent.
     */
    public List<Request> getAgentRequests(String email) {
        return requestRepository.getAgentRequest(email);
    }

    /**
     * Changes the status of the specified request.
     *
     * @param request The request of which the status will be changed.
     * @param status  A string representing the new status of the request.
     */
    public void changeRequestStatus(Request request, String status) {
        requestRepository.changeRequestStatus(request, status);
    }

    /**
     * Adds the given advertisement to the advertisement repository.
     *
     * @param advertisement The advertisement to be added.
     * @return true if the advertisement was added successfully, false otherwise.
     */
    public boolean addAdvertisement(Advertisement advertisement) {
        return advertisementRepository.addAdvertisement(advertisement);
    }
}
