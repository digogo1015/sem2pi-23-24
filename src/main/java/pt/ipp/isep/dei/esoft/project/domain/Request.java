package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Request class represents a request made by a client for a property.
 */
public class Request implements Serializable {

    private static final long serialVersionUID = 504220829766995788L;

    private Property property;
    private Client client;
    private Employee agent;
    private String dateOfRequest;
    private String typeOfBusiness;
    private String price;
    private String status;

    /**
     * Creates a new Request object with the specified property, client, agent, date of request, type of business, and price.
     *
     * @param property       The property associated with the request.
     * @param client         The client making the request.
     * @param agent          The employee handling the request.
     * @param dateOfRequest  The date of the request.
     * @param typeOfBusiness The type of business for the request.
     * @param price          The price of the request.
     */
    public Request(Property property, Client client, Employee agent, String dateOfRequest, String typeOfBusiness, String price) {
        this.property = property;
        this.client = client;
        this.agent = agent;
        this.dateOfRequest = dateOfRequest;
        this.typeOfBusiness = typeOfBusiness;
        this.price = price;
        this.status = "ToBeValidated";
    }

    /**
     * Creates a new Request object with the specified property, client, agent, date of request, type of business, and price.
     *
     * @param property       The property associated with the request.
     * @param client         The client making the request.
     * @param agent          The employee handling the request.
     * @param dateOfRequest  The date of the request.
     * @param typeOfBusiness The type of business for the request.
     * @param price          The price of the request.
     * @return The newly created Request object.
     * @throws InvalidArguments if the provided date of request, type of business, or price is invalid.
     */
    public static Request createRequest(Property property, Client client, Employee agent, String dateOfRequest, String typeOfBusiness, String price) throws InvalidArguments {
        if (validateRequest(dateOfRequest, typeOfBusiness, price))
            return new Request(property, client, agent, dateOfRequest, typeOfBusiness, price);
        throw new InvalidArguments("Invalid Request");
    }

    /**
     * Validates the provided request information.
     *
     * @param dateOfRequest  The date of the request.
     * @param typeOfBusiness The type of business for the request.
     * @param price          The price of the request.
     * @return true if the request information is valid.
     */
    private static boolean validateRequest(String dateOfRequest, String typeOfBusiness, String price) {
        return true;
    }

    /**
     * Checks if the request has the specified type of business.
     *
     * @param typeOfBusiness The type of business to check.
     * @return true if the request has the specified type of business.
     */
    public boolean hasBusinessType(String typeOfBusiness) {
        return this.typeOfBusiness.equals(typeOfBusiness);
    }

    /**
     * Returns the address of the property associated with the request.
     *
     * @return The address of the property.
     */
    public Address getAddressOfProperty() {
        return property.getAddressOfProperty();
    }

    /**
     * Returns the property associated with the request.
     *
     * @return The property.
     */
    public Property getProperty() {
        return property;
    }

    /**
     * Returns the price of the request.
     *
     * @return The price of the request.
     */
    public String getPrice() {
        return price;
    }

    /**
     * Returns the date of the request.
     *
     * @return The date of the request.
     */
    public String getDateOfRequest() {
        return dateOfRequest;
    }

    /**
     * Checks if the property has the specified type.
     *
     * @param typeOfProperty The type of property to check.
     * @return true if the property has the specified type.
     */
    public boolean hasPropertyType(String typeOfProperty) {
        return this.property.hasPropertyType(typeOfProperty);
    }

    /**
     * Checks if the property has the specified number of bedrooms.
     *
     * @param numberOfBedrooms The number of bedrooms to check.
     * @return true if the property has the specified number of bedrooms.
     */
    public boolean hasNumberOfBedrooms(String numberOfBedrooms) {
        return ((Residence) property).hasNumberOfBedrooms(numberOfBedrooms);
    }

    /**
     * Returns the number of bedrooms of the property.
     *
     * @return The number of bedrooms.
     */
    public String getNumOfBedrooms() {
        return ((Residence) property).getNumberOfBedrooms();
    }

    /**
     * Returns the number of bathrooms of the property.
     *
     * @return The number of bathrooms.
     */
    public String getNumOfBathrooms() {
        return ((Residence) property).getNumberOfBathrooms();
    }

    /**
     * Returns the number of parking spaces of the property.
     *
     * @return The number of parking spaces.
     */
    public String getNumOfParkingSpaces() {
        return ((Residence) property).getNumberOfParkingSpaces();
    }

    /**
     * Compares this Request to the specified object.
     *
     * @param o The object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(property, request.property) && Objects.equals(dateOfRequest, request.dateOfRequest);
    }

    /**
     * Returns the agent handling the request.
     *
     * @return The agent.
     */
    public Employee getAgent() {
        return agent;
    }

    /**
     * Sets the status of the request.
     *
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the status of the request.
     *
     * @return The status of the request.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Computes the hash code of the Request.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(property, dateOfRequest);
    }

    /**
     * Returns a string representation of the Request object.
     *
     * @return A string representation of the Request object.
     */
    @Override
    public String toString() {
        return property.toString() +
                "\nBusiness Type: " + typeOfBusiness + "; Price: " + price + ";";
    }

    /**
     * Returns an enhanced string representation of the Request object.
     *
     * @return An enhanced string representation of the Request object.
     */
    public String toStringV2() {
        return "\nProperty: " + property +
                "\nClient: " + client +
                "\nAgent: " + agent +
                "\nRequest Date: " + dateOfRequest +
                "\nBusiness Type: " + typeOfBusiness +
                "\nPrice: " + price +
                "\nStatus: " + status;
    }
}
