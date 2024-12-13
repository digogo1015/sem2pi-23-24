package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.utils.Utils;

public class US19UI extends Application implements Runnable {
    private static boolean javaFxLaunched = false;

    /**
     * The entry point for the JavaFX application.
     *
     * @param stage the primary stage for this application
     * @throws Exception if an error occurs during application startup
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("US19.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Runs the JavaFX application.
     */
    @Override
    public void run() {
        javaFxLaunched = Utils.launchFxApp(javaFxLaunched, this.getClass());
    }
}
