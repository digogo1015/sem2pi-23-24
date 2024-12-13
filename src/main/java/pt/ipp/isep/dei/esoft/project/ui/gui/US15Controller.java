package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ListBookingRequestController;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.Advertisement;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The US15Controller class is responsible for controlling the user interface and handling actions
 * related to displaying a visit list based on selected date range.
 * It implements the Initializable interface to allow initialization of the controller.
 */
public class US15Controller implements Initializable {

    @FXML
    private DatePicker begDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Button showList;
    @FXML
    private ListView<VisitRequest> visitList;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public ListBookingRequestController ctrl;

    /**
     * Initializes a new instance of the US15Controller class.
     * It also creates an instance of ListBookingRequestController.
     */
    public US15Controller() {
        this.ctrl = new ListBookingRequestController();
    }

    /**
     * Displays the visit list based on the selected date range.
     * Retrieves advertisements and visit requests within the date range.
     * Adds the visit requests to the ListView.
     */
    public void showVisitList() {
        if (!(begDate.getValue() == null || endDate.getValue() == null)) {
            LocalDate beg = begDate.getValue();
            LocalDate end = endDate.getValue();
            String begString = beg.toString();
            String endString = end.toString();

            boolean display = (begString.isEmpty() || begString.trim().isEmpty() || endString.isEmpty() || endString.trim().isEmpty() || end.isBefore(beg));

            if (!display) {
                visitList.getItems().clear();

                ArrayList<Advertisement> advertisement = getAgentAdvertisements();
                ArrayList<VisitRequest> visitRequests = getVisitRequests(advertisement, beg, end);

                visitList.getItems().addAll(visitRequests);
            }
        }
    }

    /**
     * Switches to the US16 scene when a visit request is selected from the visit list.
     * Passes the selected VisitRequest object to the ListBookingRequestController.
     *
     * @param event The MouseEvent triggered by the selection.
     * @throws IOException If an I/O error occurs during scene switching.
     */
    public void switchScene(MouseEvent event) throws IOException {
        if (!(visitList.getItems().isEmpty())) {
            VisitRequest v = visitList.getSelectionModel().getSelectedItem();

            ctrl.setVisitRequest(v);
            root = FXMLLoader.load(getClass().getResource("US16.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Retrieves the agent's advertisements based on the current agent's email.
     *
     * @return The list of Advertisement objects belonging to the agent.
     */
    private ArrayList<Advertisement> getAgentAdvertisements() {
        String agentEmail = getCurrentAgentByEmail();

        return ctrl.getAgentAdvertisements(agentEmail);
    }

    /**
     * Retrieves the email of the current agent.
     *
     * @return The email of the current agent.
     */
    private String getCurrentAgentByEmail() {
        UserSession userSession = Repositories.getInstance().getUserSession();

        return userSession.getUserEmail();
    }

    /**
     * Retrieves the visit requests for the given list of advertisements within the specified date range.
     * Sorts the list of advertisements by date.
     *
     * @param list      The list of advertisements to retrieve visit requests from.
     * @param startDate The start date of the date range.
     * @param endDate   The end date of the date range.
     * @return The list of VisitRequest objects within the specified date range.
     */
    private ArrayList<VisitRequest> getVisitRequests(ArrayList<Advertisement> list, LocalDate startDate, LocalDate endDate) {
        ArrayList<VisitRequest> visitList = ctrl.getVisitRequest(list, startDate, endDate);

        sortByDate(list);

        return visitList;
    }

    /**
     * Sorts the list of advertisements by date in ascending order.
     *
     * @param list The list of advertisements to be sorted.
     */
    private void sortByDate(ArrayList<Advertisement> list) {
        for (int i = 0; i < list.size() - 1; i++)
            for (int j = 0; i < list.size() - i - 1; j++)
                if (list.get(j).getDateOfAdvertisement().compareTo(list.get(j + 1).getDateOfAdvertisement()) > 0)
                    swap(list, j);
    }

    /**
     * Swaps two advertisements in the given list at the specified indices.
     *
     * @param list The list in which the swap will be performed.
     * @param i    The index of the first advertisement.
     */
    private static void swap(ArrayList<Advertisement> list, int i) {
        Advertisement temp = list.get(i);
        list.set(i, list.get(i + 1));
        list.set(i + 1, temp);
    }

    /**
     * Initializes the controller by disabling/enabling certain UI elements.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showList.setDisable(false);
        begDate.getEditor().setDisable(true);
        endDate.getEditor().setDisable(true);
    }
}
