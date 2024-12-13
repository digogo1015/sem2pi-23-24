package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.Input;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;
import pt.ipp.isep.dei.esoft.project.utils.Print;

import java.io.Serializable;
import java.util.Objects;

import static pt.ipp.isep.dei.esoft.project.utils.Validations.checkPhoneFormat;
import static pt.ipp.isep.dei.esoft.project.utils.Validations.validateEmail;

/**
 * The PersonInfo class represents information about a person.
 */
public class PersonInfo implements Serializable {

    private static final long serialVersionUID = 768482293956343719L;
    private String name;
    private String telephoneNumber;
    private String email;
    private Address address;
    private PassportCard passportCard;
    private SocialSecurityCard socialSecurityCard;

    /**
     * Constructs a PersonInfo object with the provided information.
     *
     * @param name               The name of the person.
     * @param telephoneNumber    The telephone number of the person.
     * @param email              The email address of the person.
     * @param address            The address of the person.
     * @param passportCard       The passport card of the person.
     * @param socialSecurityCard The social security card of the person.
     */
    public PersonInfo(String name, String telephoneNumber, String email, Address address, PassportCard passportCard, SocialSecurityCard socialSecurityCard) {
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.passportCard = passportCard;
        this.socialSecurityCard = socialSecurityCard;
    }

    /**
     * Creates a PersonInfo object with the provided information.
     *
     * @param name               The name of the person.
     * @param telephoneNumber    The telephone number of the person.
     * @param email              The email address of the person.
     * @param address            The address of the person.
     * @param passportCard       The passport card of the person.
     * @param socialSecurityCard The social security card of the person.
     * @return The created PersonInfo object.
     * @throws InvalidArguments if the provided person information is invalid.
     */
    public static PersonInfo createPerson(String name, String telephoneNumber, String email, Address address, PassportCard passportCard, SocialSecurityCard socialSecurityCard) throws InvalidArguments {
        if (validateInfo(name, telephoneNumber, email))
            return new PersonInfo(name, telephoneNumber, email, address, passportCard, socialSecurityCard);
        throw new InvalidArguments("Invalid Person Information");
    }

    /**
     * Validates the provided person information.
     *
     * @param name            The name of the person.
     * @param telephoneNumber The telephone number of the person.
     * @param email           The email address of the person.
     * @return true if the person information is valid.
     */
    private static boolean validateInfo(String name, String telephoneNumber, String email) {
        return !name.isBlank() && checkPhoneFormat(telephoneNumber) && validateEmail(email);
    }

    /**
     * Requests and creates a PersonInfo object by gathering information from the user.
     *
     * @return The created PersonInfo object.
     */
    public static PersonInfo getInfo() {
        do {
            try {
                String name = Input.requestName();
                String telephoneNumber = Input.requestPhoneNumber();
                String email = Input.requestEmail();
                PassportCard passportCard = PassportCard.requestPassportCard();
                SocialSecurityCard socialSecurityCard = SocialSecurityCard.requestSSCard();
                Address address = Address.requestAddress();

                return PersonInfo.createPerson(name, telephoneNumber, email, address, passportCard, socialSecurityCard);
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    /**
     * Returns the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the telephone number of the person.
     *
     * @return The telephone number of the person.
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Returns the email address of the person.
     *
     * @return The email address of the person.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the address of the person.
     *
     * @return The address of the person.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns the passport card of the person.
     *
     * @return The passport card of the person.
     */
    public PassportCard getPassportCard() {
        return passportCard;
    }

    /**
     * Returns the social security card of the person.
     *
     * @return The social security card of the person.
     */
    public SocialSecurityCard getSocialSecurityCard() {
        return socialSecurityCard;
    }

    /**
     * Checks if the provided object is equal to this PersonInfo object.
     *
     * @param o The object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonInfo person = (PersonInfo) o;
        return Objects.equals(telephoneNumber, person.telephoneNumber) || Objects.equals(email, person.email) || Objects.equals(passportCard, person.passportCard) || Objects.equals(socialSecurityCard, person.socialSecurityCard);
    }

    /**
     * Computes the hash code of this PersonInfo object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, telephoneNumber, email, address, passportCard, socialSecurityCard);
    }

    /**
     * Returns a string representation of this PersonInfo object.
     *
     * @return The string representation of the object.
     */
    @Override
    public String toString() {
        return (name + " ;" + telephoneNumber + " ;" + email);
    }

    /**
     * Returns an extended string representation of this PersonInfo object.
     *
     * @return The extended string representation of the object.
     */
    public String toStringV2() {
        return "\nName: " + name +
                "\nPhone Number: " + telephoneNumber +
                "\nEmail: " + email +
                "\nAddress: " + address +
                "\nPassport Card: " + passportCard +
                "\nSocial Security Card: " + socialSecurityCard;
    }
}
