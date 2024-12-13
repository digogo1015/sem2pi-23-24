package pt.ipp.isep.dei.esoft.project.repository;

import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;

import java.util.List;

/**
 * The AuthenticationRepository class provides methods to interact with the AuthFacade component for authentication and user management.
 */
public class AuthenticationRepository {
    private final AuthFacade authenticationFacade = new AuthFacade();

    /**
     * Performs user login with the specified email and password.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return true if the login is successful and the user is logged in, false otherwise.
     */
    public boolean doLogin(String email, String password) {
        return authenticationFacade.doLogin(email, password).isLoggedIn();
    }

    /**
     * Performs user logout.
     */
    public void doLogout() {
        authenticationFacade.doLogout();
    }

    /**
     * Retrieves the current user session.
     *
     * @return The UserSession object representing the current user session.
     */
    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    /**
     * Adds a new user role with the specified ID and description.
     *
     * @param id          The ID of the role.
     * @param description The description of the role.
     * @return true if the role is successfully added, false otherwise.
     */
    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    /**
     * Adds a new user with the specified name, email, password, and role ID.
     *
     * @param name     The name of the user.
     * @param email    The email of the user.
     * @param password The password of the user.
     * @param roleId   The ID of the role assigned to the user.
     * @return true if the user is successfully added, false otherwise.
     */
    public boolean addUserWithRole(String name, String email, String password, String roleId) {
        return authenticationFacade.addUserWithRole(name, email, password, roleId);
    }

    /**
     * Adds a new user with the specified name, email, password, and multiple role IDs.
     *
     * @param name      The name of the user.
     * @param email     The email of the user.
     * @param password  The password of the user.
     * @param rolesId   The array of role IDs assigned to the user.
     * @return true if the user is successfully added, false otherwise.
     */
    public boolean addUserWithRoles(String name, String email, String password, String[] rolesId) {
        return authenticationFacade.addUserWithRoles(name, email, password, rolesId);
    }

    /**
     * Retrieves the list of users.
     *
     * @return The list of UserDTO objects representing the users.
     */
    public List<UserDTO> getUsers() {
        return authenticationFacade.getUsers();
    }
}
