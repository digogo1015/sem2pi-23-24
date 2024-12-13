package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.Input;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;
import pt.ipp.isep.dei.esoft.project.utils.Print;
import pt.ipp.isep.dei.esoft.project.utils.Utils;

import java.io.Serializable;
import java.util.Objects;

import java.io.Serializable;
import java.util.Objects;

/**
 * The `PassportCard` class represents a passport card with a specific card number.
 * It provides methods to create a new passport card, validate a card number, and retrieve the card number.
 */
public class PassportCard implements Serializable {

    private static final long serialVersionUID = 1742801839050933976L;
    private String number;

    /**
     * Creates a new PassportCard object with the given passport card number.
     *
     * @param number The passport card number to be assigned to the PassportCard object.
     */
    public PassportCard(String number) {
        this.number = number;
    }

    /**
     * Creates a new PassportCard object with the provided passport card number.
     *
     * @param passportCardNum The passport card number to be assigned to the PassportCard object.
     * @return A new PassportCard object with the provided passport card number.
     * @throws InvalidArguments If the provided passport card number is invalid.
     */
    public static PassportCard createCard(String passportCardNum) throws InvalidArguments {
        if (validate(passportCardNum))
            return new PassportCard(passportCardNum);
        throw new InvalidArguments("Invalid Passport Card Number");
    }

    /**
     * Validates a passport card number.
     *
     * @param number The passport card number to be validated.
     * @return true if the passport card number is valid.
     */
    public static boolean validate(String number) {
        String numberRegex = "^[0-9]{9}$";

        return Utils.checkPattern(numberRegex, number);
    }

    /**
     * Requests a passport card number from the user.
     * Continuously prompts the user to enter a valid passport card number until a valid one is provided.
     *
     * @return The created PassportCard object with the valid passport card number.
     */
    public static PassportCard requestPassportCard() {
        do {
            try {
                String passportNumber = Input.getStringWithLabel("Passport Card Number (9 digits):");
                return createCard(passportNumber);
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    /**
     * Returns the passport card number.
     *
     * @return The passport card number.
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Checks if two PassportCard objects are equal based on their card numbers.
     *
     * @param o The object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassportCard that = (PassportCard) o;
        return Objects.equals(number, that.number);
    }

    /**
     * Returns the hash code of the PassportCard object based on its card number.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    /**
     * Returns the string representation of the PassportCard object.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "PassportCard: " + number;
    }
}
