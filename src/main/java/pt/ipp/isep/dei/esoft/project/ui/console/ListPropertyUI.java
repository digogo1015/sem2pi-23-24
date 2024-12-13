package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListPropertyController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.utils.Input;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;
import pt.ipp.isep.dei.esoft.project.utils.Print;
import pt.ipp.isep.dei.esoft.project.utils.Utils;

import java.util.ArrayList;

/**
 * The ListPropertyUI class represents a user interface for listing properties and applying filters.
 * It implements the Runnable interface to support execution in a separate thread.
 */
public class ListPropertyUI implements Runnable {
    private final ListPropertyController ctrl;

    /**
     * Constructs a new ListPropertyUI object.
     * Initializes the ListPropertyController.
     */
    public ListPropertyUI() {
        ctrl = new ListPropertyController();
    }

    /**
     * Runs the process of displaying the list of advertisements and applying filters if desired.
     * Calls the necessary methods to show the list, retrieve filter inputs, apply filters,
     * and display the filtered list along with the applied filters.
     */
    @Override
    public void run() {
        displayListOfAdvertisements();
    }

    /**
     * Displays the list of advertisements and applies filters if desired.
     * Calls the showListOfAdvertisements method to display the list of advertisements.
     * Checks if filters are wanted using the checkIfFiltersAreWanted method.
     * If filters are wanted, retrieves filter inputs, creates a Filters object,
     * applies the filters to the advertisement list using the getListOfAdvertisementsByFilters method,
     * and displays the filtered list and the applied filters using the displayFilters method.
     */
    private void displayListOfAdvertisements() {
        showListOfAdvertisements();

        if (checkIfFiltersAreWanted()) {
            try {
                String numberOfBedrooms = null;
                String sortingCriteria = getSortingCriteria();
                String typeOfProperty = getTypeOfProperty();
                String typeOfBusiness = getTypeOfBusiness();

                if (!typeOfProperty.equalsIgnoreCase("Land"))
                    numberOfBedrooms = getNumberOfBedrooms();

                Filters filters = Filters.createFilters(typeOfProperty, typeOfBusiness, numberOfBedrooms, sortingCriteria);

                getListOfAdvertisementsByFilters(filters);
                displayFilters(filters);
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        }
    }

    /**
     * Asks the user if filters are wanted for the property list.
     * Returns true if filters are wanted, false otherwise.
     */
    private boolean checkIfFiltersAreWanted() {
        return Input.getBooleanWithLabel("Filter list? [Y/N]");
    }

    /**
     * Retrieves the list of advertisements from the ListPropertyController and displays it using the Utils.showList method.
     */
    private void showListOfAdvertisements() {
        ArrayList<Advertisement> list = ctrl.getListOfAdvertisements();
        Utils.showList(list, "List of Advertisement, sorted by most recent");
    }

    /**
     * Retrieves the sorting criteria options from the ListPropertyController and asks the user to select a sorting method.
     * Returns the selected sorting criteria.
     */
    private String getSortingCriteria() {
        ArrayList<String> sortingCriteria = ctrl.getListOfSortingCriteria();
        int index = Utils.showAndSelectIndex(sortingCriteria, "Choose sorting method");
        return sortingCriteria.get(index);
    }

    /**
     * Retrieves the type of property options from the ListPropertyController and asks the user to select a type of property.
     * Returns the selected type of property.
     */
    private String getTypeOfProperty() {
        ArrayList<String> typesOfProperty = ctrl.getListOfTypeOfProperty();
        int index = Utils.showAndSelectIndex(typesOfProperty, "Choose Type of Property");
        return typesOfProperty.get(index);
    }

    /**
     * Retrieves the type of business options from the ListPropertyController and asks the user to select a type of business.
     * Returns the selected type of business.
     */
    private String getTypeOfBusiness() {
        ArrayList<String> typesOfBusiness = ctrl.getListOfTypeOfBusiness();
        int index = Utils.showAndSelectIndex(typesOfBusiness, "Choose Type of Business");
        return typesOfBusiness.get(index);
    }

    /**
     * Retrieves the number of bedrooms options from the ListPropertyController and asks the user to select the number of bedrooms.
     * Returns the selected number of bedrooms.
     */
    private String getNumberOfBedrooms() {
        ArrayList<String> numberOfBedrooms = ctrl.getListOfNumberOfBedrooms();
        int index = Utils.showAndSelectIndex(numberOfBedrooms, "Choose Number of Bedrooms");
        return numberOfBedrooms.get(index);
    }

    /**
     * Displays the filters used for filtering the property list.
     * Prints the Filters object and the number of bedrooms if the type of property is not "Land".
     */
    public void displayFilters(Filters filters) {
        Print.text("Filters used:\n " + filters);
        if (!filters.getTypeOfProperty().equalsIgnoreCase("Land"))
            Print.text("Number Of Bedrooms: " + filters.getNumberOfBedrooms());
    }

    /**
     * Applies the filters to the advertisement list using the ListPropertyController.
     * Retrieves the filtered list using the getListOfAdvertisementsByFilters method.
     * Displays the filtered list using the Utils.showList method.
     */
    private void getListOfAdvertisementsByFilters(Filters filters) {
        ArrayList<Advertisement> list = ctrl.getListOfAdvertisementsByFilters(filters);
        Utils.showList(list, "\nList of Advertisement Filtered");
    }
}
