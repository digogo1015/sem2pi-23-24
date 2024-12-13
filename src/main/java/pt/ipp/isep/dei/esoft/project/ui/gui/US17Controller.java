package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsController;
import pt.ipp.isep.dei.esoft.project.domain.Advertisement;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The US17Controller class is responsible for controlling the user interface and handling actions
 * related to "US17".
 */
public class US17Controller implements Initializable {
    @FXML
    private ComboBox comb;
    @FXML
    private ComboBox comb1;
    @FXML
    private Button showList;
    @FXML
    private ListView<Advertisement> dealsList;
    private ListDealsController ctrl;

    /**
     * Constructs a new instance of the US17Controller.
     * Initializes the ListDealsController.
     */
    public US17Controller() {
        this.ctrl = new ListDealsController();
    }

    /**
     * Displays the list of deals based on the selected options.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If an I/O error occurs during the process.
     */
    public void displayDeals(ActionEvent event) throws IOException {
        int i = comb.getSelectionModel().getSelectedIndex();
        int l = comb1.getSelectionModel().getSelectedIndex();

        boolean display = (i == -1 || l == -1);

        if (!display) {
            dealsList.getItems().clear();
            dealsList.getItems().addAll(listAllDeals(i, l));
        }
    }

    private ArrayList<Advertisement> listAllDeals(int i, int l) {
        ArrayList<Advertisement> dealList = ctrl.getDeals();

        int[] areaList = ctrl.getAreaList(dealList, l, i);

        return ctrl.getDeals(areaList, dealList);
    }

    /**
     * Handles the selection event of the first ComboBox.
     *
     * @param event The ActionEvent triggered by the selection.
     */
    @FXML
    void Select(ActionEvent event) {
        String s = comb.getSelectionModel().getSelectedItem().toString();
    }

    /**
     * Initializes the controller and sets up the ComboBoxes.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Ascending", "Descending");
        comb.setItems(list);

        ObservableList<String> list1 = FXCollections.observableArrayList("Selection Sort", "Bubble Sort");
        comb1.setItems(list1);
    }
}