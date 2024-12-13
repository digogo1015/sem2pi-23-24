package pt.ipp.isep.dei.esoft.project.database;

import pt.ipp.isep.dei.esoft.project.utils.Files;

/**
 * The Database class contains static variables and utility methods for managing database-related operations.
 */
public class Database {
    public static String txtExtension = ".txt";
    public static String csvExtension = ".csv";
    public static String jsonExtension = ".json";
    public static String serExtension = ".ser";
    public static String propertiesExtension = ".properties";

    public static String resources = "src/main/resources/";
    public static String path = "src/main/java/pt/ipp/isep/dei/esoft/project/database/";
    public static String pathSER = path + "ser/";
    public static String pathJSON = path + "json/";
    public static String legacyPath = path + "legacy/";
    public static String domainPath = "pt.ipp.isep.dei.esoft.project.domain.";
    public static String uiPath = "pt.ipp.isep.dei.esoft.project.ui.";
    public static String menuPath = uiPath + "console.menu.";
    public static String utilsPath = uiPath + "console.utils.";

    public static String config = resources + "config" + propertiesExtension;

    public static String emailTXT = path + "email" + txtExtension;
    public static String usersCSV = path + "users" + csvExtension;

    public static String clientsJSON = pathJSON + "clients" + jsonExtension;
    public static String employeesJSON = pathJSON + "employees" + jsonExtension;
    public static String advertisementsJSON = pathJSON + "advertisements" + jsonExtension;
    public static String requestsJSON = pathJSON + "requests" + jsonExtension;
    public static String storesJSON = pathJSON + "stores" + jsonExtension;

    public static String clientsSER = pathSER + "clients" + serExtension;
    public static String employeesSER = pathSER + "employees" + serExtension;
    public static String advertisementsSER = pathSER + "advertisements" + serExtension;
    public static String requestsSER = pathSER + "requests" + serExtension;
    public static String storesSER = pathSER + "stores" + serExtension;

    /**
     * Sends an email with the provided message.
     *
     * @param emailMsg The message to be sent in the email.
     * @return True if the email was successfully sent.
     */
    public static boolean sendEmail(String emailMsg) {
        return Files.sendInfo(emailTXT, emailMsg);
    }

    /**
     * Sends an email with the provided email address and password.
     *
     * @param emailAddress The email address to be sent in the email.
     * @param passwd       The password to be sent in the email.
     * @return True if the email was successfully sent.
     */
    public static boolean sendEmail(String emailAddress, String passwd) {
        return Files.sendInfo(emailTXT, emailAddress + ";" + passwd + '\n');
    }

    /**
     * Saves user information (email and password) to a CSV file.
     *
     * @param email  The email address of the user.
     * @param passwd The password of the user.
     * @return True if the user information was successfully saved.
     */
    public static boolean saveUsers(String email, String passwd) {
        return Files.sendInfoCSV(usersCSV, email + ";" + passwd + '\n');
    }
}
