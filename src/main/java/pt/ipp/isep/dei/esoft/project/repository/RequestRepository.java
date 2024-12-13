package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * The RequestRepository class is responsible for managing requests in the system.
 */
public class RequestRepository {
    private List<Request> requestList = new ArrayList<>();

    /**
     * Adds a request to the repository if it passes the validation.
     *
     * @param request The request to be added.
     * @return true if the request is added successfully, false otherwise.
     */
    public boolean addRequest(Request request) {
        return validateRequest(request) && requestList.add(request);
    }

    /**
     * Creates a copy of the request list.
     *
     * @return An ArrayList containing the copied requests, or null if the original list is null.
     */
    public ArrayList<Request> copy() {
        if (requestList != null)
            return new ArrayList<>(requestList);
        return null;
    }

    /**
     * Validates a request by checking if it is already present in the repository.
     *
     * @param request The request to be validated.
     * @return true if the request is valid (not present in the repository), false otherwise.
     */
    private boolean validateRequest(Request request) {
        return !copy().contains(request);
    }

    /**
     * Retrieves a list of requests associated with a specific agent email and "ToBeValidated" status.
     *
     * @param email The email of the agent.
     * @return An ArrayList of requests matching the criteria.
     */
    public ArrayList<Request> getAgentRequest(String email) {
        ArrayList<Request> newRequestList = new ArrayList<>();

        for (Request r : this.copy())
            if (r.getAgent().hasEmail(email) && r.getStatus().equals("ToBeValidated"))
                newRequestList.add(r);
        return newRequestList;
    }

    /**
     * Changes the status of a request.
     *
     * @param request The request to update.
     * @param status  The new status of the request.
     */
    public void changeRequestStatus(Request request, String status) {
        request.setStatus(status);
    }
}
