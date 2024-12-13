package pt.ipp.isep.dei.esoft.project.domain;

/**
 * The Person interface represents a person with common functionalities.
 */
public interface Person {

    /**
     * Returns the role of the person.
     *
     * @return The role of the person as a string.
     */
    String getRole();

    /**
     * Returns information about the person.
     *
     * @return A PersonInfo object containing information about the person.
     */
    PersonInfo getPerson();

    /**
     * Checks if the person has the specified email address.
     *
     * @param email The email address to check.
     * @return true if the person has the specified email address, false otherwise.
     */
    boolean hasEmail(String email);
}
