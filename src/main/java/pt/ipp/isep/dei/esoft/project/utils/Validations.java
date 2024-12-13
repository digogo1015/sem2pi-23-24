package pt.ipp.isep.dei.esoft.project.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class Validations {

    public static boolean validateTelephoneNumber(String telephoneNumber) {
        return (!StringUtils.isBlank(telephoneNumber) && checkPhoneFormat(telephoneNumber));
    }

    public static boolean validateEmail(String email) {
        return !StringUtils.isBlank(email) && checkEmailFormat(email);
    }

    public static boolean checkPhoneFormat(String number) {
        String numberRegex = "^[0-9]{3}-[0-9]{3}-[0-9]{4}$";
        Pattern pat = Pattern.compile(numberRegex);
        return pat.matcher(number).matches();
    }

    private static boolean checkEmailFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }
}
