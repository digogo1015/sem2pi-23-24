package pt.ipp.isep.dei.esoft.project.application.controller.authorization;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 * The AuthenticationController class is responsible for managing authentication and user roles.
 */
public class AuthenticationController {

    /**
     * Represents the role of a system administrator.
     */
    public static final String ROLE_SYSTEM_ADMINISTRATOR = "SYSTEM ADMINISTRATOR";

    /**
     * Represents the role of a network manager.
     */
    public static final String ROLE_NETWORK_MANAGER = "NETWORK MANAGER";

    /**
     * Represents the role of a store manager.
     */
    public static final String ROLE_STORE_MANAGER = "STORE MANAGER";

    /**
     * Represents the role of an agent.
     */
    public static final String ROLE_AGENT = "AGENT";

    /**
     * Represents the role of a client.
     */
    public static final String ROLE_CLIENT = "CLIENT";

    private final AuthenticationRepository authenticationRepository;

    /**
     * Constructs an instance of the AuthenticationController class.
     * It initializes the authenticationRepository using the AuthenticationRepository instance obtained from Repositories.
     */
    public AuthenticationController() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    /**
     * Attempts to log in the user with the specified email and password.
     *
     * @param email The email of the user.
     * @param pwd   The password of the user.
     * @return true if the login is successful, false otherwise.
     */
    public boolean doLogin(String email, String pwd) {
        try {
            return authenticationRepository.doLogin(email, pwd);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Retrieves the roles of the currently logged-in user.
     *
     * @return A list of UserRoleDTO objects representing the user roles,
     * or null if the current user session is not logged in.
     */
    public List<UserRoleDTO> getUserRoles() {
        if (authenticationRepository.getCurrentUserSession().isLoggedIn()) {
            return authenticationRepository.getCurrentUserSession().getUserRoles();
        }
        return null;
    }

    /**
     * Logs out the current user.
     */
    public void doLogout() {
        authenticationRepository.doLogout();
    }
}