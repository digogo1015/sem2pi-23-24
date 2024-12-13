package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ListBookingRequestController;

import java.io.IOException;

/**
 * The US16Controller class is responsible for controlling the user interface and handling actions
 * related to "US16".
 */
public class US16Controller {
    @FXML
    private TextArea response;
    @FXML
    private Button submit;
    @FXML
    private Button goBack;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ListBookingRequestController ctrl;

    /**
     * Constructs a new instance of the US16Controller.
     * Initializes the ListBookingRequestController.
     */
    public US16Controller() {
        this.ctrl = new ListBookingRequestController();
    }

    /**
     * Handles the scene switching when the "Submit" button is clicked.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If an I/O error occurs during scene switching.
     */
    public void switchScene(ActionEvent event) throws IOException {
        String resp = response.getText();

        if (!(resp.isEmpty() || resp.trim().isEmpty())) {
            ctrl.respondToVisitRequest(resp);

            root = FXMLLoader.load(getClass().getResource("US15.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Handles the scene switching when the "Go Back" button is clicked.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If an I/O error occurs during scene switching.
     */
    public void goBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("US15.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}