package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Person;

/**
 * The ManageUsers class is responsible for managing users and retrieving person information by email.
 */
public class ManageUsers {
    private Repositories repositories;
    private ClientRepository clientRepository;
    private EmployeeRepository employeeRepository;

    /**
     * Initializes a new instance of the ManageUsers class.
     */
    public ManageUsers() {
        this.repositories = Repositories.getInstance();
        this.clientRepository = repositories.getClientRepository();
        this.employeeRepository = repositories.getEmployeeRepository();
    }

    /**
     * Retrieves the person information associated with the given email.
     *
     * @param email The email address of the person.
     * @return The Person object associated with the email, or null if not found.
     */
    public Person getPersonByEmail(String email) {
        Person person = clientRepository.getClientByEmail(email);

        if (person != null)
            return person;

        Employee employee = employeeRepository.getEmployeeByEmail(email);

        return (Person) employee;
    }
}
