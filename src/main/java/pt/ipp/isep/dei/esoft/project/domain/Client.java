package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a client.
 */
public class Client implements Person, Serializable {

    private static final long serialVersionUID = 510921993085023157L;
    private PersonInfo person;

    /**
     * Constructs a Client object with the provided client information.
     *
     * @param person The PersonInfo object containing the client details.
     */
    public Client(PersonInfo person) {
        this.person = person;
    }

    /**
     * Creates a Client object based on the provided PersonInfo.
     *
     * @param info The PersonInfo object containing the client details.
     * @return The created Client object.
     * @throws InvalidArguments if the provided client information is invalid.
     */
    public static Client createClient(PersonInfo info) throws InvalidArguments {
        if (validatePerson(info))
            return new Client(info);
        throw new InvalidArguments("Invalid Client");
    }

    private static boolean validatePerson(PersonInfo info) {
        return true;
    }

    /**
     * Retrieves the person information associated with the client.
     *
     * @return The PersonInfo object representing the client.
     */
    public PersonInfo getPerson() {
        return person;
    }

    @Override
    public String getRole() {
        return "CLIENT";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(person, client.person);
    }

    /**
     * Checks if the email associated with this object matches the specified email.
     *
     * @param email The email to check.
     * @return true if the email matches the specified email.
     */
    @Override
    public boolean hasEmail(String email) {
        return this.person.getEmail().equals(email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person);
    }

    @Override
    public String toString() {
        return "Client: " + person.toString();
    }
}
