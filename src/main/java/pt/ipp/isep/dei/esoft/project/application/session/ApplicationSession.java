package pt.ipp.isep.dei.esoft.project.application.session;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The ApplicationSession class represents a session of the application.
 * It provides access to the current user session and manages the application configuration.
 */
public class ApplicationSession {

    private AuthenticationRepository authenticationRepository = null;
    private static final String CONFIGURATION_FILENAME = "config.properties";
    private static final String COMPANY_DESIGNATION = "Company.Designation";

    /**
     * Constructs a new ApplicationSession object.
     * Initializes the authentication repository and loads the application configuration properties.
     */
    public ApplicationSession() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        Properties props = getProperties();
    }

    /**
     * Retrieves the current user session.
     *
     * @return The current UserSession object.
     */
    public UserSession getCurrentSession() {
        pt.isep.lei.esoft.auth.UserSession userSession = this.authenticationRepository.getCurrentUserSession();
        return new UserSession(userSession);
    }

    /**
     * Retrieves the application configuration properties.
     *
     * @return The Properties object containing the application configuration.
     */
    private Properties getProperties() {
        Properties props = new Properties();

        // Set a default value for the COMPANY_DESIGNATION property
        props.setProperty(COMPANY_DESIGNATION, "Real Estate USA");

        try {
            InputStream in = new FileInputStream(CONFIGURATION_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return props;
    }

    /**
     * Holds the singleton instance of the ApplicationSession class.
     * This ensures that only one instance of ApplicationSession is created.
     */
    private static final class SingletonHolder {
        private static final ApplicationSession singleton = new ApplicationSession();
    }

    /**
     * Retrieves the singleton instance of the ApplicationSession class.
     *
     * @return The singleton instance of the ApplicationSession class.
     */
    public static ApplicationSession getInstance() {
        return SingletonHolder.singleton;
    }
}
