package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ManageRequestsController;
import pt.ipp.isep.dei.esoft.project.domain.Advertisement;
import pt.ipp.isep.dei.esoft.project.domain.Commission;
import pt.ipp.isep.dei.esoft.project.domain.Request;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.utils.*;

import java.util.Collections;
import java.util.List;

/**
 * The ManageRequestsUI class represents a user interface for managing requests made by agents.
 * It implements the Runnable interface to support execution in a separate thread.
 */
public class ManageRequestsUI implements Runnable {
    private final ManageRequestsController ctrl;

    /**
     * Constructs a new ManageRequestsUI object.
     * Initializes the ManageRequestsController.
     */
    public ManageRequestsUI() {
        ctrl = new ManageRequestsController();
    }

    /**
     * Runs the process of displaying and selecting an agent request,
     * and performing request validation.
     * Calls the necessary methods to display and select an agent request,
     * and perform request validation.
     */
    @Override
    public void run() {
        Request request = displayAndSelectAgentRequest();
        requestValidation(request);
    }

    /**
     * Displays the list of agent requests and allows the user to select a request.
     * Retrieves the list of agent requests using the getRequests method.
     * Sorts the list of requests by ascending date using the sortList method.
     * Calls the showAndSelectIndex method of the Utils class to display the list of requests
     * and select a request index.
     * Returns the selected request.
     */
    private Request displayAndSelectAgentRequest() {
        List<Request> requestList = getRequests();
        sortList(requestList);
        return requestList.get(Utils.showAndSelectIndex(requestList, "Select a request:"));
    }

    /**
     * Sorts the provided list of requests by ascending date.
     * Uses the Collections.sort method and the ComparatorDateAscending class for sorting.
     */
    private void sortList(List<Request> requestList) {
        Collections.sort(requestList, new ComparatorDateAscending());
    }

    /**
     * Retrieves the list of agent requests associated with the current user's email.
     * Retrieves the email of the current user from the authentication repository and user session.
     * Calls the getAgentRequests method of the controller to retrieve the list of agent requests.
     * Returns the list of agent requests.
     */
    private List<Request> getRequests() {
        String email = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().toString();
        return ctrl.getAgentRequests(email);
    }

    /**
     * Performs request validation based on user input.
     * Asks the user to validate the request.
     * If validated, creates an advertisement using the request and commission information,
     * and adds it to the controller using the addAdvertisement method.
     * Prints a success message and updates the request status as "Validated".
     * If not validated, updates the request status as "NotValidated".
     * Asks the user for a rejection justification.
     */
    private void requestValidation(Request request) {
        if (Input.getBooleanWithLabel("Validate Request? [Y/N]")) {
            addAdvertisement(new Advertisement(Time.getDateNow(), request, Commission.getInfo()));
        } else {
            ctrl.changeRequestStatus(request, "NotValidated");
            Input.getStringWithLabel("Rejection Justification:");
        }
    }

    /**
     * Adds the provided advertisement to the controller.
     * Calls the addAdvertisement method of the controller to add the advertisement.
     * Prints a success message and updates the request status as "Validated".
     * If not added, prints a message indicating that the request was not validated.
     */
    private void addAdvertisement(Advertisement advertisement) {
        if (ctrl.addAdvertisement(advertisement)) {
            Print.text("\nRequest was Validated!");
            ctrl.changeRequestStatus(advertisement.getRequest(), "Validated");
        } else {
            Print.text("\nRequest was not Validated!");
        }
    }
}
