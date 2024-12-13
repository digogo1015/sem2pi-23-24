package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.LinearMultipleRegression;
import pt.ipp.isep.dei.esoft.project.domain.LinearSimpleRegression;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

/**
 * The LinearRegressionController class is a controller responsible for handling linear regression operations
 * in a divide stores application.
 */
public class LinearRegressionController {
    private Repositories repositories;
    private AdvertisementRepository advertisementRepository;
    public static int comBox;

    /**
     * Constructs a new instance of LinearRegressionController.
     * Initializes the repositories and sets the advertisementRepository
     * based on the repositories instance.
     */
    public LinearRegressionController() {
        this.repositories = Repositories.getInstance();
        this.advertisementRepository = repositories.getAdvertisementRepository();
    }

    /**
     * Creates an instance of LinearSimpleRegression based on the simple regression data obtained from the advertisement repository.
     *
     * @param choice The choice of simple regression model.
     * @return An instance of LinearSimpleRegression.
     */
    public LinearSimpleRegression SimpleRegression(int choice) {
        return new LinearSimpleRegression(advertisementRepository.getSimpleRegression(choice));
    }

    /**
     * Creates an instance of LinearMultipleRegression based on the multiple regression data obtained from the advertisement repository.
     *
     * @return An instance of LinearMultipleRegression.
     */
    public LinearMultipleRegression MultipleRegression() {
        return new LinearMultipleRegression(advertisementRepository.getMultipleRegression());
    }

    /**
     * Returns the value of the comBox variable.
     *
     * @return The value of the comBox variable.
     */
    public int getComBox() {
        return comBox;
    }

    /**
     * Sets the value of the comBox variable.
     *
     * @param comBox The new value to set for the comBox variable.
     */
    public void setComBox(int comBox) {
        LinearRegressionController.comBox = comBox;
    }
}