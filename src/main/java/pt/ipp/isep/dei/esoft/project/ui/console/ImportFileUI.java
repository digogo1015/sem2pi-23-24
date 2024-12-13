package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ImportFileController;
import pt.ipp.isep.dei.esoft.project.database.Database;
import pt.ipp.isep.dei.esoft.project.utils.Files;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;
import pt.ipp.isep.dei.esoft.project.utils.Print;
import pt.ipp.isep.dei.esoft.project.utils.Utils;

import java.util.ArrayList;

/**
 * The ImportFileUI class represents a user interface for importing files and retrieving data from them.
 * It implements the Runnable interface to support execution in a separate thread.
 */
public class ImportFileUI implements Runnable {
    private ImportFileController ctrl;

    /**
     * Constructs a new ImportFileUI object.
     * Initializes the ImportFileController.
     */
    public ImportFileUI() {
        this.ctrl = new ImportFileController();
    }

    /**
     * Runs the file import process.
     * Allows the user to select a file and retrieves data from it using the ImportFileController.
     */
    @Override
    public void run() {
        String name = selectFile();

        if (name != null)
            ctrl.getDataFromFile(name);
    }

    /**
     * Allows the user to select a file from the specified directory.
     * Validates the selected file and checks if it is a valid CSV file.
     *
     * @return The name of the selected file, or null if no file is selected or if the file is invalid.
     */
    private String selectFile() {
        ArrayList<String> files = Files.getFilesInDir(Database.legacyPath);

        if (files.size() != 0) {
            int i = Utils.showAndSelectIndex(files, "Legacy files:");

            String name = files.get(i);

            try {
                if (!Files.validCSV(Database.legacyPath + name))
                    throw new InvalidArguments("Invalid CSV File");
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
            return name;
        }
        return null;
    }
}
