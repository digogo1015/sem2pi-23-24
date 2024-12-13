package pt.ipp.isep.dei.esoft.project.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class PasswordValidator {
    public static void printRules() {
        Print.text("Rules:" + "\n" +
                "Only 7 characters allowed" + "\n" + "" +
                "At least 2 digits (0 - 9)" + "\n" +
                "At least 3 Upper Letters (A - Z)");
    }

    public static String generatePassword() {
        String components = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        List<Character> charList = new ArrayList<>();

        for (int i = 0; i < 2; i++)
            charList.add((char) (random.nextInt(10) + '0'));
        for (int i = 0; i < 3; i++)
            charList.add(components.charAt(random.nextInt(26)));
        for (int i = 0; i < 2; i++)
            charList.add(components.charAt(random.nextInt(components.length())));

        Collections.shuffle(charList);

        for (Character c : charList)
            password.append(c);

        return password.toString();
    }

    public static boolean checkPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z].*[A-Z].*[A-Z])(?=.*[0-9].*[0-9])(?=.*[a-zA-Z0-9].*[a-zA-Z0-9]).{7}$";
        Pattern pat = Pattern.compile(passwordRegex);

        return pat.matcher(password).matches();
    }
}