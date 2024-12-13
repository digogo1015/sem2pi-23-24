package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterClientController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.utils.Input;
import pt.ipp.isep.dei.esoft.project.utils.Print;

/**
 * The RegisterClientUI class represents a user interface for registering clients.
 * It implements the Runnable interface to support execution in a separate thread.
 */
public class RegisterClientUI implements Runnable {
    private final RegisterClientController ctrl;

    /**
     * Constructs a new RegisterClientUI object.
     * Initializes the RegisterClientController.
     */
    public RegisterClientUI() {
        ctrl = new RegisterClientController();
    }

    /**
     * Runs the process of registering a client.
     * Calls the necessary methods to get client information and password,
     * and register the client.
     */
    @Override
    public void run() {
        PersonInfo clientInfo = PersonInfo.getInfo();
        String password = Input.requestPassword();

        registerClient(clientInfo, password);
    }

    /**
     * Registers a client with the provided client information and password.
     * Displays the client information and password for confirmation using the displayAndConfirm method of the Input class.
     * Calls the registerClient method of the controller to register the client.
     * Prints a success message if the client is successfully registered,
     * or a failure message if the client is not registered.
     */
    private void registerClient(PersonInfo clientInfo, String password) {
        Input.displayAndConfirm(clientInfo.toStringV2());
        Input.displayAndConfirm("Password: " + password);

        if (ctrl.registerClient(new Client(clientInfo), password)) {
            Print.text("Client successfully registered");
        } else {
            Print.text("Client not registered");
        }
    }
}
