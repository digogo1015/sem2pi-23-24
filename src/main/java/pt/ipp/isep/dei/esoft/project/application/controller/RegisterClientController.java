package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.database.Database;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.ClientRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

/**
 * This class is a controller responsible for registering clients and setting up their authentication credentials.
 */
public class RegisterClientController {

    // Instance variables
    private Repositories repositories;
    private AuthenticationRepository authenticationRepository;
    private ClientRepository clientRepository;

    /**
     * Constructs a new RegisterClientController object.
     * Initializes the repositories, authenticationRepository, and clientRepository instances.
     */
    public RegisterClientController() {
        this.repositories = Repositories.getInstance();
        this.authenticationRepository = repositories.getAuthenticationRepository();
        this.clientRepository = repositories.getClientRepository();
    }

    /**
     * Registers a client by adding them to the client repository and setting up their authentication credentials.
     *
     * @param client The client to be registered.
     * @param passwd The password for the client's authentication.
     * @return true if the client was registered successfully, false otherwise.
     */
    public boolean registerClient(Client client, String passwd) {
        if (authenticationRepository.addUserWithRole(client.getPerson().getName(),
                client.getPerson().getEmail(), passwd, "CLIENT") && clientRepository.addClient(client)) {
            Database.saveUsers(client.getPerson().getEmail(), passwd);
            return true;
        }
        return false;
    }
}
