package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.MakeAdvertisementController;
import pt.ipp.isep.dei.esoft.project.database.Database;
import pt.ipp.isep.dei.esoft.project.domain.*;

import pt.ipp.isep.dei.esoft.project.utils.Input;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;
import pt.ipp.isep.dei.esoft.project.utils.Print;
import pt.ipp.isep.dei.esoft.project.utils.Time;

/**
 * The MakeAdvertisementUI class represents a user interface for creating and submitting advertisements.
 * It implements the Runnable interface to support execution in a separate thread.
 */
public class MakeAdvertisementUI implements Runnable {
    private final MakeAdvertisementController ctrl;

    /**
     * Constructs a new MakeAdvertisementUI object.
     * Initializes the MakeAdvertisementController.
     */
    public MakeAdvertisementUI() {
        ctrl = new MakeAdvertisementController();
    }

    /**
     * Runs the process of getting advertisement details, adding the advertisement to the repository,
     * and notifying the client about the advertisement.
     * Calls the necessary methods to retrieve advertisement details, register client info,
     * create an Advertisement object, add the advertisement to the repository,
     * and notify the client about the advertisement.
     */
    @Override
    public void run() {
        Advertisement advertisement = getAdvertisement();
        addAdvertisementToRepository(advertisement);
    }

    /**
     * Gets the details for creating an advertisement.
     * Uses input methods to gather client information, property information, price, and commission.
     * Creates a Client object, registers the client info, retrieves an agent from the controller,
     * and creates a Request object and an Advertisement object based on the gathered details.
     * Returns the created Advertisement object.
     */
    private Advertisement getAdvertisement() {
        do {
            try {
                Client client = Client.createClient(PersonInfo.getInfo());
                registerClientInfo(client);

                Employee agent = ctrl.getAgent();

                String typeOfBusiness = Input.requestTypeOfBusiness();
                String typeOfProperty = Input.requestTypeOfProperty();
                Property property = PropertyInfo.requestPropertyData(typeOfProperty);
                String price = Input.requestPrice();

                Commission commission = Commission.getInfo();

                Request request = Request.createRequest(property, client, agent, Time.getDateNow(), typeOfBusiness, price);

                return Advertisement.createAdvertisement(Time.getDateNow(), request, commission);
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    /**
     * Registers the client information by requesting a password from the user.
     * Calls the registerClient method of the controller to register the client.
     * Prints a success message if the client is registered successfully, or an error message otherwise.
     */
    private void registerClientInfo(Client client) {
        String password = Input.requestPassword();

        if (ctrl.registerClient(client, password))
            Print.text("Client successfully registered");
        else
            Print.text("Client not registered");
    }

    /**
     * Adds the advertisement to the repository and notifies the client about the advertisement.
     * Displays the advertisement details using the Input.displayAndConfirm method.
     * Calls the addAdvertisementToRepository method of the controller to add the advertisement to the repository.
     * Prints a success message and notifies the client if the advertisement is added successfully,
     * or an error message otherwise.
     */
    private void addAdvertisementToRepository(Advertisement advertisement) {
        Input.displayAndConfirm(advertisement.toStringV2());

        if (ctrl.addAdvertisementToRepository(advertisement)) {
            Print.text("\nYour Advertisement Has Been Submitted!");
            notifyClient(advertisement);
        } else
            Print.text("\nYour Advertisement Has Not Been Submitted!");
    }

    /**
     * Notifies the client about the advertisement by sending an email.
     * Retrieves the agent and property information from the advertisement's request.
     * Constructs a notification message and calls the Database.sendEmail method to send the email.
     */
    private void notifyClient(Advertisement advertisement) {
        Property property = advertisement.getRequest().getProperty();
        PersonInfo agent = advertisement.getRequest().getAgent().getPerson();

        String msg = "From: " + agent.getName() + "\nTelephone Number: " + agent.getTelephoneNumber() +
                "\nFor property: " + property.getAddressOfProperty() + "\nIn " + advertisement.getDateOfAdvertisement() +
                "\nYour property is listed";

        Database.sendEmail(msg);
    }
}
