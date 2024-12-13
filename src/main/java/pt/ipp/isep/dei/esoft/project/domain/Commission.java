package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.lang3.StringUtils;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.utils.Input;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;
import pt.ipp.isep.dei.esoft.project.utils.Print;
import pt.ipp.isep.dei.esoft.project.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.ArrayList;

/**
 * Represents a commission.
 */
public class Commission implements Serializable {

    private static final long serialVersionUID = 8871327209812550742L;

    private String typeOfCommission;
    private String value;

    /**
     * Constructs a Commission object with the provided type of commission and value.
     *
     * @param typeOfCommission The type of commission.
     * @param value            The value of the commission.
     */
    public Commission(String typeOfCommission, String value) {
        this.typeOfCommission = typeOfCommission;
        this.value = value;
    }

    /**
     * Creates a Commission object based on the provided type of commission and value.
     *
     * @param typeOfCommission The type of commission.
     * @param value            The value of the commission.
     * @return The created Commission object.
     * @throws InvalidArguments if the provided commission information is invalid.
     */
    public static Commission createCommission(String typeOfCommission, String value) throws InvalidArguments {
        if (validateCommission(typeOfCommission, value))
            return new Commission(typeOfCommission, value);
        throw new InvalidArguments("Invalid Commission");
    }

    private static boolean validateCommission(String typeOfCommission, String value) {
        return true;
    }

    /**
     * Requests the type of commission from the user.
     *
     * @return The selected type of commission.
     */
    static public String requestTypeOfCommission() {
        ArrayList<String> typesOfCommission = Repositories.getInstance().getFilterRepository().getListOfCommission();
        int index = Utils.showAndSelectIndex(typesOfCommission, "Choose type of Commission");

        return typesOfCommission.get(index);
    }

    /**
     * Requests the value of the commission from the user.
     *
     * @param typeOfCommission The type of commission.
     * @return The entered value of the commission.
     * @throws InvalidArguments if the entered commission value is invalid.
     */
    private static String requestCommissionValue(String typeOfCommission) {
        do {
            try {
                String value = Input.getStringWithLabel("Commission Value:");

                if (value.isBlank() || !StringUtils.isNumeric(value))
                    throw new InvalidArguments("Invalid Commission Value");

                switch (typeOfCommission) {
                    case "Percentage": {
                        if (Integer.parseInt(value) < 0 || Integer.parseInt(value) > 100)
                            throw new InvalidArguments("Invalid Commission Value");
                        break;
                    }
                    case "Fixed Value": {
                        if (Integer.parseInt(value) < 0)
                            throw new InvalidArguments("Invalid Commission Value");
                        break;
                    }
                }
                return value;
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    /**
     * Retrieves the information for creating a Commission object.
     *
     * @return The created Commission object.
     */
    public static Commission getInfo() {
        do {
            try {
                String typeOfCommission = requestTypeOfCommission();
                String commissionValue = requestCommissionValue(typeOfCommission);

                return createCommission(typeOfCommission, commissionValue);
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    @Override
    public String toString() {
        return "Commission Type: " + typeOfCommission + " Value: " + value;
    }
}
