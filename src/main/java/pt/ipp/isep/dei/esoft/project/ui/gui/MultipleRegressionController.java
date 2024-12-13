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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import pt.ipp.isep.dei.esoft.project.application.controller.LinearRegressionController;
import pt.ipp.isep.dei.esoft.project.domain.AnovaTable;
import pt.ipp.isep.dei.esoft.project.domain.LinearMultipleRegression;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The MultipleRegressionController class is responsible for controlling the user interface and handling actions
 * related to multiple regression analysis.
 * It implements the Initializable interface to allow initialization of the controller.
 */
public class MultipleRegressionController implements Initializable {
    @FXML
    private ComboBox<String> comb1;
    @FXML
    private TextField beta;
    @FXML
    private TextField ICBox;
    @FXML
    private TextField x1;
    @FXML
    private TextField x2;
    @FXML
    private TextField x3;
    @FXML
    private TextField x4;
    @FXML
    private TextField x5;
    @FXML
    private Label regressionInfo;
    @FXML
    private Label label;
    private LinearMultipleRegression regression;
    private final LinearRegressionController ctrl = new LinearRegressionController();
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button goBack;
    @FXML
    private TableView<AnovaTable> Anova;
    @FXML
    private TableColumn<AnovaTable, ?> col1;
    @FXML
    private TableColumn<AnovaTable, ?> col2;
    @FXML
    private TableColumn<AnovaTable, ?> col3;
    @FXML
    private TableColumn<AnovaTable, ?> col4;
    @FXML
    private TableColumn<AnovaTable, ?> col5;

    /**
     * Handles the selection event of the ComboBox.
     *
     * @param event The ActionEvent triggered by the selection.
     */
    @FXML
    void Select(ActionEvent event) {
        String s = comb1.getSelectionModel().getSelectedItem();
    }

    /**
     * Displays information based on user input and selected options.
     */
    @FXML
    public void displayInfo() {
        boolean validation = (beta.getText().isBlank() || ICBox.getText().isBlank() || x1.getText().isBlank() || x2.getText().isBlank()
                || x3.getText().isBlank() || x4.getText().isBlank() || x5.getText().isBlank() || StringUtils.isAlphaSpace(beta.getText()) ||
                StringUtils.isAlphaSpace(ICBox.getText()) || StringUtils.isAlphaSpace(x1.getText()) || StringUtils.isAlphaSpace(x2.getText())
                || StringUtils.isAlphaSpace(x3.getText()) || StringUtils.isAlphaSpace(x4.getText()) || StringUtils.isAlphaSpace(x5.getText()));

        if (!(validation)) {
            int Beta = Integer.parseInt(beta.getText());
            double IC = Double.parseDouble(ICBox.getText());
            double X1 = Double.parseDouble(x1.getText());
            double X2 = Double.parseDouble(x2.getText());
            double X3 = Double.parseDouble(x3.getText());
            double X4 = Double.parseDouble(x4.getText());
            double X5 = Double.parseDouble(x5.getText());
            this.regression = ctrl.MultipleRegression();
            display(label, regression.displayMultipleRegression());
            int selected = comb1.getSelectionModel().getSelectedIndex();
            switch (selected) {
                case 0:
                    Anova.setVisible(false);
                    display(regressionInfo, regression.multipleRegressionInfo());
                    break;
                case 1:
                    Anova.setVisible(false);
                    display(regressionInfo, regression.multipleRegressionPrediction(X1, X2, X3, X4, X5));
                    break;
                case 2:
                    Anova.setVisible(false);
                    display(regressionInfo, regression.multipleRegressionHypothesisTest(IC, Beta));
                    break;
                case 3:
                    Anova.setVisible(false);
                    display(regressionInfo, regression.multipleRegressionBetaConfidence(IC, Beta));
                    break;
                case 4:
                    ArrayList<AnovaTable> anovaValues = regression.multipleRegressionAnova(IC);

                    display(regressionInfo, anovaValues.get(3).getFonteDeVariacao());

                    col1 = Anova.getColumns().get(0);
                    col2 = Anova.getColumns().get(1);
                    col3 = Anova.getColumns().get(2);
                    col4 = Anova.getColumns().get(3);
                    col5 = Anova.getColumns().get(4);

                    col1.setCellValueFactory(new PropertyValueFactory<>("fonteDeVariacao"));
                    col2.setCellValueFactory(new PropertyValueFactory<>("somaQuad"));
                    col3.setCellValueFactory(new PropertyValueFactory<>("grausLib"));
                    col4.setCellValueFactory(new PropertyValueFactory<>("mediaQuad"));
                    col5.setCellValueFactory(new PropertyValueFactory<>("estatisticaTeste"));

                    Anova.setVisible(true);

                    ObservableList<AnovaTable> infoANOVA = FXCollections.observableArrayList();

                    infoANOVA.add(anovaValues.get(0));
                    infoANOVA.add(anovaValues.get(1));
                    infoANOVA.add(anovaValues.get(2));

                    Anova.setItems(infoANOVA);
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * Displays text in the given label.
     *
     * @param label  The Label where the text will be displayed.
     * @param string The string to be displayed.
     */
    @FXML
    public void display(Label label, String string) {
        label.setText(string);
    }

    /**
     * Initializes the controller by setting up the ComboBox and hiding the Anova TableView.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Get more info",
                "Make a prediction", "Hypothesis tests", "Confidence Interval", "ANOVA table");
        comb1.setItems(list);

        Anova.setVisible(false);
    }

    /**
     * Switches to another scene when the corresponding button is clicked.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If an I/O error occurs during scene switching.
     */
    public void switchScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("US18.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
