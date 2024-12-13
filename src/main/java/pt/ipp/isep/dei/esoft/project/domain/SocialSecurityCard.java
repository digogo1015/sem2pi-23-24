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
 * The SocialSecurityCard class represents a social security card with a tax number.
 */
public class SocialSecurityCard implements Serializable {

    private static final long serialVersionUID = 786125291874417884L;
    private String taxNumber;

    /**
     * Constructs a new SocialSecurityCard object with the specified tax number.
     *
     * @param taxNumber The tax number associated with the social security card.
     */
    public SocialSecurityCard(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    /**
     * Creates a new instance of SocialSecurityCard based on the provided tax number.
     *
     * @param taxNumber The tax number associated with the social security card.
     * @return A new SocialSecurityCard object.
     * @throws InvalidArguments If the provided tax number is invalid or null.
     */
    public static SocialSecurityCard createCard(String taxNumber) throws InvalidArguments {
        if (validate(taxNumber))
            return new SocialSecurityCard(taxNumber);
        throw new InvalidArguments("Invalid Tax Number");
    }

    /**
     * Validates a tax number based on a specific format.
     *
     * @param taxNumber The tax number to be validated.
     * @return True if the tax number is valid.
     */
    private static boolean validate(String taxNumber) {
        String numberRegex = "^[0-9]{3}-[0-9]{2}-[0-9]{4}$";

        return Utils.checkPattern(numberRegex, taxNumber);
    }

    /**
     * Requests user input for creating a SocialSecurityCard.
     *
     * @return The created SocialSecurityCard object.
     */
    public static SocialSecurityCard requestSSCard() {
        do {
            try {
                String taxNumber = Input.getStringWithLabel("Tax Number (xxx-xx-xxxx):");
                return createCard(taxNumber);
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    /**
     * Gets the tax number associated with the social security card.
     *
     * @return The tax number.
     */
    public String getTaxNumber() {
        return taxNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialSecurityCard that = (SocialSecurityCard) o;
        return Objects.equals(taxNumber, that.taxNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxNumber);
    }

    @Override
    public String toString() {
        return "SocialSecurityCard: " + taxNumber;
    }
}
