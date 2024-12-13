package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.utils.Utils;

/**
 * The US18UI class is the main entry point for the JavaFX application of US18.
 * It extends the Application class and implements the Runnable interface.
 */
public class US18UI extends Application implements Runnable {

    /**
     * A flag to keep track of whether the JavaFX application has been launched.
     */
    private static boolean javaFxLaunched = false;

    /**
     * The start method is called when the JavaFX application is starting.
     * It loads the US18.fxml file and sets it as the root of the scene.
     *
     * @param stage the primary stage for this application
     * @throws Exception if an error occurs during application startup
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("US18.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The run method is from the Runnable interface.
     * It launches the JavaFX application using the Utils.launchFxApp method.
     */
    @Override
    public void run() {
        javaFxLaunched = Utils.launchFxApp(javaFxLaunched, this.getClass());
    }
}
