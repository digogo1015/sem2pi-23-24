package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.LinearRegressionController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The US18Controller class is the controller for the user interface of US18.
 * It implements the Initializable interface to initialize the controller.
 */
public class US18Controller implements Initializable {
    @FXML
    private ComboBox comb;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private LinearRegressionController ctrl;

    /**
     * Constructs a new instance of the US18Controller.
     * It initializes the LinearRegressionController.
     */
    public US18Controller() {
        this.ctrl = new LinearRegressionController();
    }

    /**
     * Switches the scene based on the selected item in the ComboBox.
     *
     * @param event the action event
     * @throws IOException if an error occurs while loading the scene
     */
    public void switchScene(ActionEvent event) throws IOException {
        int i = comb.getSelectionModel().getSelectedIndex();

        ctrl.setComBox(i);

        if (i == 5)
            root = FXMLLoader.load(getClass().getResource("MultipleRegressionMenu.fxml"));
        else
            root = FXMLLoader.load(getClass().getResource("SimpleRegressionMenu.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles the selection event of the ComboBox.
     *
     * @param event the action event
     */
    @FXML
    void Select(ActionEvent event) {
        String s = comb.getSelectionModel().getSelectedItem().toString();
    }

    /**
     * Initializes the controller.
     *
     * @param url            the location used to resolve relative paths for the root object
     * @param resourceBundle the resource bundle used to localize the root object
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList(
                "Simple Regression For Area",
                "Simple Regression For Distance To City Center",
                "Simple Regression For Number Of Bedrooms",
                "Simple Regression For Number Of Bathrooms",
                "Simple Regression For Number Of Parking Spaces",
                "Multiple Regression"
        );
        comb.setItems(list);
    }
}
