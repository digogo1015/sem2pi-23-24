package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterStoreController;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.utils.Input;
import pt.ipp.isep.dei.esoft.project.utils.Print;

/**
 * The RegisterStoreUI class is responsible for providing a user interface to register a store.
 * It implements the Runnable interface to allow running the registration process in a separate thread.
 */
public class RegisterStoreUI implements Runnable {
    private final RegisterStoreController ctrl;

    /**
     * Constructs a RegisterStoreUI object.
     * Initializes the RegisterStoreController.
     */
    public RegisterStoreUI() {
        this.ctrl = new RegisterStoreController();
    }

    /**
     * The run method that is executed when the RegisterStoreUI thread is started.
     * Retrieves store information and adds it to the repository.
     */
    @Override
    public void run() {
        Store store = Store.getInfo();
        addToRepository(store);
    }

    /**
     * Adds the given store to the repository.
     *
     * @param store The store object to be added.
     */
    private void addToRepository(Store store) {
        Input.displayAndConfirm(store.toStringV2());

        if (ctrl.registerStore(store))
            Print.text("Store successfully registered");
        else
            Print.text("Store not registered");
    }
}
