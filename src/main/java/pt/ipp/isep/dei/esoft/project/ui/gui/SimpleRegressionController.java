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
import pt.ipp.isep.dei.esoft.project.domain.LinearSimpleRegression;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SimpleRegressionController implements Initializable {
    @FXML
    private ComboBox<String> comb1;
    @FXML
    private TextField xbox;
    @FXML
    private TextField ICBox;
    @FXML
    private TextField a;
    @FXML
    private TextField b;
    @FXML
    private Label regressionInfo;
    @FXML
    private Label label;
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

    private LinearSimpleRegression regression;
    private final LinearRegressionController ctrl = new LinearRegressionController();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button goBack;

    @FXML
    void Select(ActionEvent event) {
        String s = comb1.getSelectionModel().getSelectedItem().toString();
    }

    public void getRegression() {
        int i = ctrl.getComBox();

        this.regression = ctrl.SimpleRegression(i);
    }

    @FXML
    public void displayInfo() {
        boolean validation = (xbox.getText().isBlank() || ICBox.getText().isBlank() || a.getText().isBlank() || b.getText().isBlank() ||
                StringUtils.isAlphaSpace(xbox.getText()) || StringUtils.isAlphaSpace(ICBox.getText()) || StringUtils.isAlphaSpace(a.getText()) ||
                StringUtils.isAlphaSpace(b.getText()));

        if (!(validation)) {
            double x = Double.parseDouble(xbox.getText());
            double IC = Double.parseDouble(ICBox.getText());
            double a0 = Double.parseDouble(a.getText());
            double b0 = Double.parseDouble(b.getText());

            getRegression();
            display(label, regression.displaySimpleRegression());

            int selected = comb1.getSelectionModel().getSelectedIndex();

            switch (selected) {
                case 0:
                    Anova.setVisible(false);
                    display(regressionInfo, regression.simpleRegressionInfo());
                    break;
                case 1:
                    Anova.setVisible(false);
                    display(regressionInfo, regression.simpleRegressionPrediction(x));
                    break;
                case 2:
                    Anova.setVisible(false);
                    display(regressionInfo, regression.simpleRegressionConfidence(IC));
                    break;
                case 3:
                    Anova.setVisible(false);
                    display(regressionInfo, regression.simpleRegressionHypothesisTest(a0, b0, IC));
                    break;
                case 4:

                    ArrayList<AnovaTable> anovaValues = regression.SimpleRegressionANOVA(IC);

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
        } else display(regressionInfo, "Invalid Information");
    }

    @FXML
    public void display(Label label, String string) {
        label.setText(string);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Get more info",
                "Make a prediction", "Confidence Intervals", "Hypothesis tests", "ANOVA table");
        comb1.setItems(list);

        Anova.setVisible(false);

    }

    public void switchScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("US18.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}