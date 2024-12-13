package pt.ipp.isep.dei.esoft.project.utils;

import org.apache.commons.lang3.StringUtils;
import pt.ipp.isep.dei.esoft.project.database.Database;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.Scanner;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class Input {
    public static String getStringWithLabel(String label) {
        do {
            try {
                Print.text(label);
                Scanner sc = new Scanner(System.in);

                return sc.nextLine();
            } catch (Exception ignored) {}
        } while (true);
    }

    public static boolean getBooleanWithLabel(String label) {
        String data;
        do {
            try {
                Print.text(label);
                Scanner sc = new Scanner(System.in);

                data = sc.nextLine();
                if (data.equalsIgnoreCase("Y"))
                    return true;
                if (data.equalsIgnoreCase("N"))
                    return false;
            } catch (Exception ignored) {}
        } while (true);
    }

    static public <T> T requestClass(Class<T> type, String label) {
        do {
            try {
                return type.getConstructor(String.class).newInstance(Input.getStringWithLabel(label));
            } catch (Exception ignored) {}
        } while (true);
    }

    static public String requestSunExposure() {
        ArrayList<String> typesOfSunExposure = Repositories.getInstance().getFilterRepository().getListOfSunExposure();

        int index = Utils.showAndSelectIndex(typesOfSunExposure, "Choose type of Sun Exposure");

        return typesOfSunExposure.get(index);
    }

    static public String getDomainClassName(String className) {
        String[] words = className.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();

        for (String word : words)
            sb.append(capitalize(word));

        return Database.domainPath + sb;
    }

    static public ArrayList<Photograph> requestPhotographs() {
        final int MAX_PHOTOGRAPHS = Photograph.getMaxLimit();
        ArrayList<Photograph> photographs = new ArrayList<>();

        do {
            try {
                int num = Integer.parseInt(Input.getStringWithLabel("How many Photographs? (Max " + MAX_PHOTOGRAPHS + ")"));

                if (num <= 0 || num > MAX_PHOTOGRAPHS)
                    throw new InvalidArguments("Invalid Number Of Photographs");

                for (int i = 0; i < num; i++) {
                    String linkURI = Input.getString("Photo " + (i + 1) + " Link URI:", "Invalid Link URI");

                    photographs.add(new Photograph(linkURI));
                }
                return photographs;
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    private static String getString(String label, String errorMsg) {
        do {
            try {
                String linkURI = Input.getStringWithLabel(label);

                if (linkURI.isBlank())
                    throw new InvalidArguments(errorMsg);

                return linkURI;
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    static public ArrayList<Equipment> requestEquipment() {
        final int MAX_EQUIPMENT = Equipment.getMaxLimit();
        ArrayList<Equipment> equipments = new ArrayList<>();

        do {
            try {
                int num = Integer.parseInt(Input.getStringWithLabel("How many Equipments? (Max " + MAX_EQUIPMENT + ")"));

                if (num <= 0 || num > MAX_EQUIPMENT)
                    throw new InvalidArguments("Invalid Number Of Equipment");

                for (int i = 0; i < num; i++) {
                    String description = Input.getString("Equipment " + (i + 1) + " description:", "Invalid Description");

                    equipments.add(new Equipment(description));
                }
                return equipments;
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    static public void displayAndConfirm(String s) {
        Print.text(s);
        Print.text("Press enter to continue");
        Utils.readLineFromConsole("");
    }

    static public String requestEmail() {
        do {
            try {
                String email = Input.getStringWithLabel("Email:");
                return (new Email(email)).toString();
            } catch (Exception e) {
                Print.text("Invalid Email");
            }
        } while (true);
    }

    static public String requestPassword() {
        do {
            try {
                PasswordValidator.printRules();
                String passwd = Input.getStringWithLabel("Password:");
                if (!PasswordValidator.checkPassword(passwd))
                    throw new Exception();
                return passwd;
            } catch (Exception ignored) {}
        } while (true);
    }

    static public String requestName() {
        do {
            try {
                String name = Input.getStringWithLabel("Name:");

                if (name.isBlank())
                    throw new InvalidArguments("Invalid Name");

                return name;
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    static public String requestPhoneNumber() {
        do {
            try {
                String phoneNum = Input.getStringWithLabel("Phone Number (xxx-xxx-xxxx):");

                if (!Validations.validateTelephoneNumber(phoneNum))
                    throw new InvalidArguments("Invalid Phone Number");

                return phoneNum;
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    static public String requestTypeOfBusiness() {
        ArrayList<String> typesOfBusiness = Repositories.getInstance().getFilterRepository().getListOfTypeOfBusiness();
        int index = Utils.showAndSelectIndex(typesOfBusiness, "Choose type of Business");

        return typesOfBusiness.get(index);
    }

    static public String requestTypeOfProperty() {
        ArrayList<String> typesOfProperty = Repositories.getInstance().getFilterRepository().getListOfTypeOfProperty();
        int index = Utils.showAndSelectIndex(typesOfProperty, "Choose type of Property");

        return typesOfProperty.get(index);
    }

    static public String requestPrice() {
        do {
            try {
                String price = Input.getStringWithLabel("Price:");
                if (!StringUtils.isNumeric(price) || price.isBlank() || Integer.parseInt(price) <= 0)
                    throw new InvalidArguments("Invalid Price");
                return price;
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }

    public static String requestNumber(String s) {
        do {
            try {
                String price = Input.getStringWithLabel(s);
                if (price.isBlank() || Integer.parseInt(price) <= 0)
                    throw new InvalidArguments("Invalid Number");

                return price;
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        } while (true);
    }
}