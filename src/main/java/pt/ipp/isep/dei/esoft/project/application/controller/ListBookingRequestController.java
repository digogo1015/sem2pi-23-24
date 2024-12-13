package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.database.Database;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Advertisement;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The ListBookingRequestController class is a controller responsible for handling booking request-related operations
 * in a divide stores application.
 */
public class ListBookingRequestController {
    private Repositories repositories;
    private AdvertisementRepository advertisementRepository;
    private EmployeeRepository employeeRepository;

    public static VisitRequest visitRequest;

    /**
     * Constructs a new instance of ListBookingRequestController.
     * Initializes the repositories and sets the advertisementRepository and employeeRepository
     * based on the repositories instance.
     */
    public ListBookingRequestController() {
        this.repositories = Repositories.getInstance();
        this.advertisementRepository = repositories.getAdvertisementRepository();
        this.employeeRepository = repositories.getEmployeeRepository();
    }

    /**
     * Returns a list of advertisements associated with a specific agent's email.
     *
     * @param email The email of the agent.
     * @return An ArrayList of Advertisement objects.
     */
    public ArrayList<Advertisement> getAgentAdvertisements(String email) {
        return advertisementRepository.getAgentAdvertisements(email);
    }

    /**
     * Returns a list of visit requests based on the provided list of advertisements and date range.
     *
     * @param list   The list of advertisements to filter visit requests from.
     * @param dStart The start date of the visit request.
     * @param dEnd   The end date of the visit request.
     * @return An ArrayList of VisitRequest objects.
     */
    public ArrayList<VisitRequest> getVisitRequest(ArrayList<Advertisement> list, LocalDate dStart, LocalDate dEnd) {
        return advertisementRepository.getVisitRequestList(list, dStart, dEnd);
    }


    /**
     * Responds to a visit request by sending an email to the requester.
     *
     * @param message The response message to be sent.
     */
    public void respondToVisitRequest(String message) {
        UserSession userSession = Repositories.getInstance().getUserSession();
        String email = userSession.getUserEmail();

        Advertisement advertisement = advertisementRepository.getVisitRequestAdvertisement(visitRequest);

        visitRequest.setStatus("Validated");

        Address propertyAddress = advertisement.getRequest().getProperty().getAddressOfProperty();

        Employee employee = employeeRepository.getEmployeeByEmail(email);
        String employeePhone = employee.getPerson().getTelephoneNumber();
        String employeeName = employee.getPerson().getName();

        String response = "From : " + employeeName + "Telephone Number : " + employeePhone +
                "\n For property : " + propertyAddress + "\n" + message;

        Database.sendEmail(response);
    }

    /**
     * Sets the current visit request.
     *
     * @param visitRequest The visit request to set.
     */
    public void setVisitRequest(VisitRequest visitRequest) {
        ListBookingRequestController.visitRequest = visitRequest;
    }
}