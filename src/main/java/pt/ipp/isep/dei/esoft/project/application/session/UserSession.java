package pt.ipp.isep.dei.esoft.project.application.session;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 * The UserSession class represents a user session in the application.
 * It provides access to user information and session-related functionalities.
 */
public class UserSession {

    private pt.isep.lei.esoft.auth.UserSession userSession;

    /**
     * Constructs a new UserSession object with the specified UserSession instance.
     *
     * @param userSession The UserSession instance.
     */
    public UserSession(pt.isep.lei.esoft.auth.UserSession userSession) {
        this.userSession = userSession;
    }

    /**
     * Retrieves the email of the user associated with the session.
     * Updates the user session before retrieving the email.
     *
     * @return The email of the user.
     */
    public String getUserEmail() {
        updateUserSession();
        return userSession.getUserId().getEmail();
    }

    /**
     * Updates the user session with the current user session from the authentication repository.
     */
    public void updateUserSession() {
        this.userSession = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession();
    }

    /**
     * Retrieves the name of the user associated with the session.
     * Updates the user session before retrieving the name.
     *
     * @return The name of the user.
     */
    public String getUserName() {
        updateUserSession();
        return this.userSession.getUserName();
    }

    /**
     * Retrieves a list of user roles associated with the session.
     *
     * @return The list of UserRoleDTO objects representing user roles.
     */
    public List<UserRoleDTO> getUserRoles() {
        return this.userSession.getUserRoles();
    }

    /**
     * Performs a logout action for the user session.
     */
    public void doLogout() {
        this.userSession.doLogout();
    }

    /**
     * Checks if the user is currently logged in.
     *
     * @return True if the user is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return this.userSession.isLoggedIn();
    }

    /**
     * Checks if the user is currently logged in with the specified role.
     *
     * @param roleId The ID of the role to check.
     * @return True if the user is logged in with the specified role, false otherwise.
     */
    public boolean isLoggedInWithRole(String roleId) {
        return this.userSession.isLoggedInWithRole(roleId);
    }
}
