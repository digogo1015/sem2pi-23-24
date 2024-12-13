package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RequestPurchaseOrderController;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.Advertisement;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.utils.*;

import java.util.ArrayList;

/**
 * The RequestPurchaseOrderUI class is responsible for providing a user interface to request a purchase order.
 * It implements the Runnable interface to allow running the request process in a separate thread.
 */
public class RequestPurchaseOrderUI implements Runnable {
    private final RequestPurchaseOrderController ctrl;

    /**
     * Constructs a RequestPurchaseOrderUI object.
     * Initializes the RequestPurchaseOrderController.
     */
    public RequestPurchaseOrderUI() {
        ctrl = new RequestPurchaseOrderController();
    }

    /**
     * The run method that is executed when the RequestPurchaseOrderUI thread is started.
     * Creates a purchase order.
     */
    @Override
    public void run() {
        createPurchaseOrder();
    }

    /**
     * Creates a purchase order based on user input.
     */
    private void createPurchaseOrder() {
        Advertisement advertisement = getAdvertisement();
        Client client = requestClientData();

        if (advertisement.canMakePurchaseOrder(client))
            savePurchaseOrder(advertisement);
        else
            Print.text("Purchase Order already submitted!");
    }

    /**
     * Retrieves the selected advertisement from the list of advertisements.
     *
     * @return The selected advertisement.
     */
    private Advertisement getAdvertisement() {
        ArrayList<Advertisement> advertisementList = ctrl.getListOfAdvertisements();
        int i = Utils.showAndSelectIndex(advertisementList, "Advertisements List");

        return advertisementList.get(i);
    }

    /**
     * Saves the purchase order and displays the confirmation message.
     *
     * @param advertisement The advertisement for which the purchase order is created.
     */
    private void savePurchaseOrder(Advertisement advertisement) {
        PurchaseOrder purchaseOrder = getPurchaseOrder(advertisement);

        Input.displayAndConfirm(purchaseOrder.toStringV2());

        if (ctrl.addPurchaseOrder(purchaseOrder, advertisement))
            Print.text("Purchase Order Submitted");
        else
            Print.text("Purchase Order not Submitted");
    }

    /**
     * Retrieves the purchase order details based on user input.
     *
     * @param advertisement The advertisement for which the purchase order is created.
     * @return The created purchase order object.
     */
    private PurchaseOrder getPurchaseOrder(Advertisement advertisement) {
        Client client = requestClientData();
        String date = Time.getDateNow();
        String price = requestPrice(advertisement);

        return new PurchaseOrder(client, date, price);
    }

    /**
     * Requests the price for the purchase order from the user.
     *
     * @param advertisement The advertisement for which the purchase order is created.
     * @return The entered price for the purchase order.
     */
    private String requestPrice(Advertisement advertisement) {
        do {
            try {
                String price = Input.getStringWithLabel("Price: ");

                if (Integer.parseInt(price) > Integer.parseInt(advertisement.getPrice()))
                    throw new InvalidArguments("Price too high");

                return price;
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    /**
     * Retrieves the client data based on the user session.
     *
     * @return The client object.
     */
    private Client requestClientData() {
        UserSession userSession = Repositories.getInstance().getUserSession();

        return ctrl.getClientByEmail(userSession.getUserEmail());
    }
}
