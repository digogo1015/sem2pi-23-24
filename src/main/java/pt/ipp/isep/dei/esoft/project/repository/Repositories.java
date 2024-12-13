package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.session.UserSession;

/**
 * The Repositories class serves as a central repository for accessing various repositories in the system.
 * It follows the Singleton design pattern to provide a single instance of the repositories.
 */
public class Repositories {
    private static final Repositories instance = new Repositories();
    private AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    private ClientRepository clientRepository = new ClientRepository();
    private EmployeeRepository employeeRepository = new EmployeeRepository();
    private RequestRepository requestRepository = new RequestRepository();
    private StoreRepository storeRepository = new StoreRepository();
    private RoleRepository roleRepository = new RoleRepository();
    private AdvertisementRepository advertisementRepository = new AdvertisementRepository();
    private FilterRepository filterRepository = new FilterRepository();
    private UserSession userSession = new UserSession(getAuthenticationRepository().getCurrentUserSession());

    /**
     * Private constructor to prevent external instantiation of the class.
     */
    private Repositories() {}

    /**
     * Returns the singleton instance of the Repositories class.
     *
     * @return The Repositories instance.
     */
    public static Repositories getInstance() {
        return instance;
    }

    /**
     * Returns the client repository.
     *
     * @return The ClientRepository instance.
     */
    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    /**
     * Returns the employee repository.
     *
     * @return The EmployeeRepository instance.
     */
    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    /**
     * Returns the request repository.
     *
     * @return The RequestRepository instance.
     */
    public RequestRepository getRequestRepository() {
        return requestRepository;
    }

    /**
     * Returns the store repository.
     *
     * @return The StoreRepository instance.
     */
    public StoreRepository getStoreRepository() {
        return storeRepository;
    }

    /**
     * Returns the role repository.
     *
     * @return The RoleRepository instance.
     */
    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    /**
     * Returns the authentication repository.
     *
     * @return The AuthenticationRepository instance.
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    /**
     * Returns the advertisement repository.
     *
     * @return The AdvertisementRepository instance.
     */
    public AdvertisementRepository getAdvertisementRepository() {
        return advertisementRepository;
    }

    /**
     * Returns the filter repository.
     *
     * @return The FilterRepository instance.
     */
    public FilterRepository getFilterRepository() {
        return filterRepository;
    }

    /**
     * Returns the user session.
     *
     * @return The UserSession instance.
     */
    public UserSession getUserSession() {
        return userSession;
    }
}
