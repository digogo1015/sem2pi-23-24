package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.VisitRequestController;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.Advertisement;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.utils.Input;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;
import pt.ipp.isep.dei.esoft.project.utils.Print;
import pt.ipp.isep.dei.esoft.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The VisitRequestUI class is responsible for providing a user interface to create a visit request for an advertisement.
 * It implements the Runnable interface to allow running the visit request process in a separate thread.
 */
public class VisitRequestUI implements Runnable {
    private final VisitRequestController ctrl;

    /**
     * Constructs a VisitRequestUI object.
     * Initializes the VisitRequestController.
     */
    public VisitRequestUI() {
        ctrl = new VisitRequestController();
    }

    /**
     * The run method that is executed when the VisitRequestUI thread is started.
     * Retrieves the advertisement and creates a visit request.
     */
    @Override
    public void run() {
        Advertisement advertisement = getAdvertisement();
        createVisitRequest(advertisement);
    }

    /**
     * Retrieves the selected advertisement from the list of advertisements.
     *
     * @return The selected advertisement.
     */
    private Advertisement getAdvertisement() {
        ArrayList<Advertisement> list = ctrl.getListOfAdvertisementsNotSold();
        return list.get(Utils.showAndSelectIndex(list, "List of Advertisement, sorted by most recent"));
    }

    /**
     * Creates a visit request based on user input.
     *
     * @param advertisement The advertisement for which the visit request is created.
     */
    private void createVisitRequest(Advertisement advertisement) {
        VisitRequest visitRequest = getVisitRequest();

        Input.displayAndConfirm(visitRequest.toStringV2());

        if (advertisement.addVisitRequest(visitRequest))
            Print.text("Visit Request Submitted");
        else
            Print.text("Visit Request not Submitted");
    }

    /**
     * Retrieves the visit request details based on user input.
     *
     * @return The created visit request object.
     */
    private VisitRequest getVisitRequest() {
        String dateOfVisit = requestDateOfVisit();
        String timeSlotStart = requestTimeSlot("\nInsert the time to start the visit (hh:mm): ");
        String timeSlotEnd;

        do
            timeSlotEnd = requestTimeSlot("\nInsert the time to end the visit (hh:mm): ");
        while (LocalTime.parse(timeSlotEnd).isBefore(LocalTime.parse(timeSlotStart)));

        String message = requestVisitMessage();
        Client client = getClient();

        return new VisitRequest(client.getPerson().getName(), client.getPerson().getTelephoneNumber(), dateOfVisit, timeSlotStart, timeSlotEnd, message);
    }

    /**
     * Retrieves the client based on the user session.
     *
     * @return The client object.
     */
    private Client getClient() {
        UserSession userSession = Repositories.getInstance().getUserSession();
        String email = userSession.getUserEmail();
        return ctrl.getClientByEmail(email);
    }

    /**
     * Requests the date of the visit from the user.
     *
     * @return The entered date of the visit.
     */
    private String requestDateOfVisit() {
        do {
            try {
                String dateOfVisit = Input.getStringWithLabel("Date of Visit (yyyy-mm-dd): ");
                parseDate(dateOfVisit);
                return dateOfVisit;
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    /**
     * Parses and validates the date of the visit.
     *
     * @param dateOfVisit The date of the visit to be parsed and validated.
     * @throws InvalidArguments If the date is invalid or in the past.
     */
    private void parseDate(String dateOfVisit) throws InvalidArguments {
        try {
            LocalDate date = LocalDate.parse(dateOfVisit);

            if (date.isBefore(LocalDate.now()))
                throw new Exception();
        } catch (Exception e) {
            throw new InvalidArguments("Invalid Date");
        }
    }

    /**
     * Requests a time slot from the user.
     *
     * @param label The label to display when requesting the time slot.
     * @return The entered time slot.
     */
    private String requestTimeSlot(String label) {
        do {
            try {
                String time = Input.getStringWithLabel(label);
                parseTime(time);
                return time;
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    /**
     * Parses and validates the time slot.
     *
     * @param time The time slot to be parsed and validated.
     * @throws InvalidArguments If the time is invalid.
     */
    private void parseTime(String time) throws InvalidArguments {
        try {
            LocalTime.parse(time);
        } catch (Exception e) {
            throw new InvalidArguments("Invalid Time");
        }
    }

    /**
     * Requests the visit message from the user.
     *
     * @return The entered visit message.
     */
    private String requestVisitMessage() {
        return Input.getStringWithLabel("Insert message: ");
    }
}
