package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class Advertisement implements Serializable {

    private static final long serialVersionUID = 1767065623482073236L;
    private String dateOfAdvertisement;
    private Request request;
    private Commission commission;
    private ArrayList<PurchaseOrder> purchaseOrderList;
    private ArrayList<VisitRequest> visitRequestList;
    private String status;
    private Store store;


    public Advertisement(String dateOfAdvertisement, Request request, Commission commission) {
        this.dateOfAdvertisement = dateOfAdvertisement;
        this.request = request;
        this.commission = commission;
        this.purchaseOrderList = new ArrayList<>();
        this.visitRequestList = new ArrayList<>();
        this.status = "ToBeValidated";
        this.store = request.getAgent().getStore();
    }

    public Advertisement(String dateOfAdvertisement, Request request, Commission commission, Store store) {
        this(dateOfAdvertisement, request, commission);
        this.store = store;
    }

    /**
     * Creates an Advertisement object based on the provided information.
     *
     * @param dateNow    The current date in the format "yyyy-MM-dd".
     * @param request    The Request object containing the advertisement details.
     * @param commission The Commission object specifying the commission details.
     * @return The created Advertisement object.
     * @throws InvalidArguments if the provided arguments fail validation.
     */
    public static Advertisement createAdvertisement(String dateNow, Request request, Commission commission) throws InvalidArguments {
        if (validateAdvertisement(dateNow, request, commission))
            return new Advertisement(dateNow, request, commission);
        throw new InvalidArguments();
    }

    /**
     * Creates an Advertisement object based on the provided information.
     *
     * @param dateNow    The current date in the format "yyyy-MM-dd".
     * @param request    The Request object containing the advertisement details.
     * @param commission The Commission object specifying the commission details.
     * @param store      The Store object representing the store associated with the advertisement.
     * @return The created Advertisement object.
     * @throws InvalidArguments if the provided arguments fail validation.
     */
    public static Advertisement createAdvertisement(String dateNow, Request request, Commission commission, Store store) throws InvalidArguments {
        if (validateAdvertisement(dateNow, request, commission))
            return new Advertisement(dateNow, request, commission, store);
        throw new InvalidArguments();
    }

    private static boolean validateAdvertisement(String dateNow, Request request, Commission commission) {
        return true;
    }

    /**
     * Adds a VisitRequest to the list of visit requests.
     *
     * @param visitRequest The VisitRequest object to be added.
     * @return true if the visit request is valid and successfully added.
     */
    public boolean addVisitRequest(VisitRequest visitRequest) {
        return validateVisitRequest(visitRequest) && visitRequestList.add(visitRequest);
    }

    /**
     * Validates a VisitRequest to ensure it does not overlap with existing visit requests.
     *
     * @param visitRequest The VisitRequest object to be validated.
     * @return true if the visit request is valid and does not overlap with existing visit requests.
     */
    private boolean validateVisitRequest(VisitRequest visitRequest) {
        for (VisitRequest vr : this.visitRequestList)
            if (overlap(visitRequest, vr))
                return false;
        return true;
    }


    /**
     * Checks if two VisitRequests overlap in terms of day and time slot.
     *
     * @param visitRequest  The first VisitRequest to compare.
     * @param visitRequest2 The second VisitRequest to compare.
     * @return true if the two VisitRequests overlap.
     */
    private boolean overlap(VisitRequest visitRequest, VisitRequest visitRequest2) {
        return sameDay(visitRequest, visitRequest2) && sameSlot(visitRequest, visitRequest2);
    }

    /**
     * Checks if two VisitRequests have the same time slot.
     *
     * @param visitRequest  The first VisitRequest to compare.
     * @param visitRequest2 The second VisitRequest to compare.
     * @return true if the two VisitRequests have the same time slot.
     */
    private boolean sameSlot(VisitRequest visitRequest, VisitRequest visitRequest2) {
        LocalTime vr1S = LocalTime.parse(visitRequest.getTimeSlotStart());
        LocalTime vr1E = LocalTime.parse(visitRequest.getTimeSlotEnd());
        LocalTime vr2S = LocalTime.parse(visitRequest2.getTimeSlotStart());
        LocalTime vr2E = LocalTime.parse(visitRequest2.getTimeSlotEnd());

        return !(vr1S.isBefore(vr2S) && vr1E.isBefore(vr2S) || vr2S.isBefore(vr1S) && vr2E.isBefore(vr1S));
    }

    /**
     * Checks if two VisitRequests have the same day of visit.
     *
     * @param visitRequest  The first VisitRequest to compare.
     * @param visitRequest2 The second VisitRequest to compare.
     * @return true if the two VisitRequests have the same day of visit.
     */
    private boolean sameDay(VisitRequest visitRequest, VisitRequest visitRequest2) {
        return visitRequest.getDateOfVisit().equals(visitRequest2.getDateOfVisit());
    }

    /**
     * Adds a PurchaseOrder to the list of purchase orders.
     *
     * @param purchaseOrder The PurchaseOrder object to be added.
     * @return true if the purchase order is valid and successfully added
     */
    public boolean addPurchaseOrder(PurchaseOrder purchaseOrder) {
        return validatePurchaseOrder(purchaseOrder) && purchaseOrderList.add(purchaseOrder);
    }

    private boolean validatePurchaseOrder(PurchaseOrder purchaseOrder) {
        return true;
    }

    /**
     * Clears the list of purchase orders and updates the status of the provided active purchase order.
     *
     * @param purchaseOrderActive The active PurchaseOrder object to be updated.
     */
    public void clearPurchaseOrders(PurchaseOrder purchaseOrderActive) {
        this.purchaseOrderList.forEach(purchaseOrder -> purchaseOrder.setStatus("NotValidated"));

        purchaseOrderActive.setStatus("Validated");
        setStatus("Validated");
    }

    public ArrayList<PurchaseOrder> getPurchaseOrderList() {
        return purchaseOrderList;
    }

    public Request getRequest() {
        return request;
    }

    public ArrayList<VisitRequest> getVisitRequestList() {
        return visitRequestList;
    }

    public String getDateOfAdvertisement() {
        return dateOfAdvertisement;
    }

    public String getStatus() {
        return this.status;
    }

    public String getPrice() {
        return request.getPrice();
    }

    public Address getAddressOfProperty() {
        return request.getAddressOfProperty();
    }

    public Store getStore() {
        return this.store;
    }

    public boolean hasBusinessType(String typeOfBusiness) {
        return this.request.hasBusinessType(typeOfBusiness);
    }

    public boolean hasPropertyType(String typeOfProperty) {
        return request.hasPropertyType(typeOfProperty);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean hasNumberOfBedrooms(String numberOfBedrooms) {
        return request.hasNumberOfBedrooms(numberOfBedrooms);
    }

    /**
     * Checks if the provided client can make a purchase order for this Advertisement.
     *
     * @param client The client to check.
     * @return true if the client can make a purchase order.
     */
    public boolean canMakePurchaseOrder(Client client) {
        for (PurchaseOrder purchaseOrder : this.purchaseOrderList)
            if (purchaseOrder.getClient().equals(client))
                return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return Objects.equals(request, that.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfAdvertisement, request, commission);
    }

    @Override
    public String toString() {
        return "\n" + request.toString();
    }

    public String toStringV2() {
        return "Request: " + request;
    }
}