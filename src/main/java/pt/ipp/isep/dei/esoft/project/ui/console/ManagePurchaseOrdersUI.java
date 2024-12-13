package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ManagePurchaseOrderController;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.Advertisement;
import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.utils.ComparatorDescendingPrice;
import pt.ipp.isep.dei.esoft.project.utils.Input;
import pt.ipp.isep.dei.esoft.project.utils.Print;
import pt.ipp.isep.dei.esoft.project.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The ManagePurchaseOrdersUI class represents a user interface for managing purchase orders related to advertisements.
 * It implements the Runnable interface to support execution in a separate thread.
 */
public class ManagePurchaseOrdersUI implements Runnable {
    private final ManagePurchaseOrderController ctrl;

    /**
     * Constructs a new ManagePurchaseOrdersUI object.
     * Initializes the ManagePurchaseOrderController.
     */
    public ManagePurchaseOrdersUI() {
        ctrl = new ManagePurchaseOrderController();
    }

    /**
     * Runs the process of getting an advertisement, selecting a purchase order,
     * and managing the purchase order.
     * Calls the necessary methods to retrieve an advertisement, select a purchase order,
     * and manage the purchase order.
     */
    @Override
    public void run() {
        Advertisement advertisement = getAdvertisement();
        choosePurchaseOrder(advertisement);
    }

    /**
     * Gets an advertisement associated with the current agent's email.
     * Retrieves the email of the current agent from the user session.
     * Calls the getListOfAdvertisementByAgent method of the controller to retrieve the list of advertisements
     * associated with the agent's email.
     * Returns the selected advertisement.
     */
    private Advertisement getAdvertisement() {
        String agentEmail = getCurrentAgentByEmail();
        ArrayList<Advertisement> advertisementList = ctrl.getListOfAdvertisementByAgent(agentEmail);
        return selectAdvertisement(advertisementList);
    }

    /**
     * Selects an advertisement from the provided list of advertisements.
     * Sorts the list of advertisements in descending order based on price.
     * Calls the showAndSelectIndex method of the Utils class to display the list of advertisements
     * and select an advertisement index.
     * Returns the selected advertisement.
     */
    private Advertisement selectAdvertisement(ArrayList<Advertisement> advertisementList) {
        Collections.sort(advertisementList, new ComparatorDescendingPrice());
        return advertisementList.get(Utils.showAndSelectIndex(advertisementList, "Select an advertisement"));
    }

    /**
     * Retrieves the email of the current agent from the user session.
     * Returns the email of the current agent.
     */
    private String getCurrentAgentByEmail() {
        UserSession userSession = Repositories.getInstance().getUserSession();
        return userSession.getUserEmail();
    }

    /**
     * Allows the user to choose a purchase order associated with the selected advertisement.
     * Calls the selectPurchaseOrder method to select a purchase order from the advertisement's purchase order list.
     * Calls the managePurchaseOrder method to manage the selected purchase order.
     */
    private void choosePurchaseOrder(Advertisement advertisement) {
        PurchaseOrder purchaseOrder = selectPurchaseOrder(advertisement);
        managePurchaseOrder(advertisement, purchaseOrder);
    }

    /**
     * Manages the selected purchase order associated with the advertisement.
     * Asks the user to approve the purchase order.
     * If approved, removes the purchase order from the advertisement's purchase order list.
     * Prints a success message.
     * If not approved, sets the purchase order status as "NotValidated".
     * Prints a message indicating the purchase order was not approved.
     */
    private void managePurchaseOrder(Advertisement advertisement, PurchaseOrder purchaseOrder) {
        if (Input.getBooleanWithLabel("Approve Purchase Order? [Y/N]")) {
            advertisement.clearPurchaseOrders(purchaseOrder);
            Print.text("Purchase Order Approved!");
        } else {
            purchaseOrder.setStatus("NotValidated");
            Print.text("Purchase Order Not Approved!");
        }
    }

    /**
     * Selects a purchase order from the purchase order list associated with the advertisement.
     * Calls the showAndSelectIndex method of the Utils class to display the list of purchase orders
     * and select a purchase order index.
     * Returns the selected purchase order.
     */
    public PurchaseOrder selectPurchaseOrder(Advertisement advertisement) {
        ArrayList<PurchaseOrder> purchaseOrders = advertisement.getPurchaseOrderList();
        return purchaseOrders.get(Utils.showAndSelectIndex(purchaseOrders, "Select a Purchase Order"));
    }
}
