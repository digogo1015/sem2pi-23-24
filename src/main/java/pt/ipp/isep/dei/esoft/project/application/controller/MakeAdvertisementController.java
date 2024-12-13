package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

/**
 * This class is a controller responsible for managing the creation and registration of advertisements and clients.
 */
public class MakeAdvertisementController {

    // Instance variables
    private Repositories repositories;
    private AdvertisementRepository advertisementRepository;
    private ClientRepository clientRepository;
    private AuthenticationRepository authenticationRepository;
    private EmployeeRepository employeeRepository;

    /**
     * Constructs a new MakeAdvertisementController object.
     * Initializes the repositories, advertisementRepository, clientRepository, authenticationRepository, and employeeRepository instances.
     */
    public MakeAdvertisementController() {
        this.repositories = Repositories.getInstance();
        this.advertisementRepository = repositories.getAdvertisementRepository();
        this.clientRepository = repositories.getClientRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
        this.employeeRepository = repositories.getEmployeeRepository();
    }

    /**
     * Retrieves the agent (employee) associated with the current user session.
     *
     * @return The Employee object representing the agent.
     */
    public Employee getAgent() {
        UserSession userSession = Repositories.getInstance().getUserSession();
        String email = userSession.getUserEmail();

        return employeeRepository.getEmployeeByEmail(email);
    }

    /**
     * Adds the given advertisement to the advertisement repository.
     *
     * @param advertisement The advertisement to be added.
     * @return true if the advertisement was added successfully, false otherwise.
     */
    public boolean addAdvertisementToRepository(Advertisement advertisement) {
        return advertisementRepository.addAdvertisement(advertisement);
    }

    /**
     * Registers a client by adding them to the client repository and setting up their authentication credentials.
     *
     * @param client   The client to be registered.
     * @param password The password for the client's authentication.
     * @return true if the client was registered successfully, false otherwise.
     */
    public boolean registerClient(Client client, String password) {
        return clientRepository.registerClient(client, password, authenticationRepository);
    }
}
