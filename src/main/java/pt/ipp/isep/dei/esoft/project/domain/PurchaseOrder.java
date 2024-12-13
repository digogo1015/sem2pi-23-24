package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import java.io.Serializable;
import java.util.Objects;

/**
 * The PurchaseOrder class represents a purchase order.
 */
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = 7186576213096525022L;
    private Client client;
    private String dateOfPurchaseOrder;
    private String price;
    private String status;

    /**
     * Creates a new PurchaseOrder object with the specified client, date of purchase order, and price.
     *
     * @param client              The client associated with the purchase order.
     * @param dateOfPurchaseOrder The date of the purchase order.
     * @param price               The price of the purchase order.
     */
    public PurchaseOrder(Client client, String dateOfPurchaseOrder, String price) {
        this.client = client;
        this.dateOfPurchaseOrder = dateOfPurchaseOrder;
        this.price = price;
        this.status = "Active";
    }

    /**
     * Creates a new PurchaseOrder object with the specified client, date of purchase order, and price.
     *
     * @param client              The client associated with the purchase order.
     * @param dateOfPurchaseOrder The date of the purchase order.
     * @param price               The price of the purchase order.
     * @return The newly created PurchaseOrder object.
     * @throws InvalidArguments if the provided client, date of purchase order, or price is invalid.
     */
    public static PurchaseOrder createPurchaseOrder(Client client, String dateOfPurchaseOrder, String price) throws InvalidArguments {
        if (validatePO(client, dateOfPurchaseOrder, price))
            return new PurchaseOrder(client, dateOfPurchaseOrder, price);
        throw new InvalidArguments("Invalid Purchase Order");
    }

    /**
     * Validates the provided purchase order information.
     *
     * @param client              The client associated with the purchase order.
     * @param dateOfPurchaseOrder The date of the purchase order.
     * @param price               The price of the purchase order.
     * @return true if the purchase order information is valid.
     */
    private static boolean validatePO(Client client, String dateOfPurchaseOrder, String price) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrder that = (PurchaseOrder) o;
        return Objects.equals(client, that.client) && Objects.equals(dateOfPurchaseOrder, that.dateOfPurchaseOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, dateOfPurchaseOrder, price, status);
    }

    /**
     * Returns the status of the purchase order.
     *
     * @return The status of the purchase order.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the purchase order.
     *
     * @param status The new status of the purchase order.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the price of the purchase order.
     *
     * @return The price of the purchase order.
     */
    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return client + "Price: " + price;
    }

    /**
     * Returns the client associated with the purchase order.
     *
     * @return The client associated with the purchase order.
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * Returns a string representation of the PurchaseOrder object.
     *
     * @return A string representation of the PurchaseOrder object.
     */
    public String toStringV2() {
        return "\nClient: " + client +
                "\nPrice: " + price +
                "\nStatus: " + status +
                "\nPurchase Order Date: " + dateOfPurchaseOrder;
    }
}
