package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

import java.io.Serializable;

/**
 * The VisitRequest class represents a visit request made by a person.
 */
public class VisitRequest implements Serializable {

    private static final long serialVersionUID = 3139702972753898373L;
    private String name;
    private String phoneNumber;
    private String dateOfVisit;
    private String timeSlotStart;
    private String timeSlotEnd;
    private String message;
    private String status;

    /**
     * Constructs a VisitRequest object with the provided information.
     *
     * @param name          The name of the person making the visit request.
     * @param phoneNumber   The phone number of the person making the visit request.
     * @param dateOfVisit   The date of the visit.
     * @param timeSlotStart The start time of the visit.
     * @param timeSlotEnd   The end time of the visit.
     * @param message       An optional message accompanying the visit request.
     */
    public VisitRequest(String name, String phoneNumber, String dateOfVisit, String timeSlotStart, String timeSlotEnd, String message) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOfVisit = dateOfVisit;
        this.timeSlotStart = timeSlotStart;
        this.timeSlotEnd = timeSlotEnd;
        this.message = message;
        this.status = "ToBeValidated";
    }

    /**
     * Returns the date of the visit.
     *
     * @return The date of the visit.
     */
    public String getDateOfVisit() {
        return this.dateOfVisit;
    }

    /**
     * Returns the start time of the visit.
     *
     * @return The start time of the visit.
     */
    public String getTimeSlotStart() {
        return this.timeSlotStart;
    }

    /**
     * Returns the end time of the visit.
     *
     * @return The end time of the visit.
     */
    public String getTimeSlotEnd() {
        return this.timeSlotEnd;
    }

    /**
     * Returns the status of the visit request.
     *
     * @return The status of the visit request.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the visit request.
     *
     * @param status The status to set for the visit request.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\nName: " + name +
                "\nVisit Date: " + dateOfVisit;
    }

    /**
     * Returns a string representation of the VisitRequest object.
     *
     * @return A string representation of the VisitRequest object.
     */
    public String toStringV2() {
        return "\nName: " + name +
                "\nPhone Number: " + phoneNumber +
                "\nVisit Date: " + dateOfVisit +
                "\nStart Time: " + timeSlotStart +
                "\nEnd Time: " + timeSlotEnd +
                "\nMessage: " + message;
    }
}
