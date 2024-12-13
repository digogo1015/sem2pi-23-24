package pt.ipp.isep.dei.esoft.project.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.utils.Validations;

import static org.junit.jupiter.api.Assertions.*;

class ValidationsTest {

    @Test
    @DisplayName("Test valid and invalid phone numbers")
    void validateTelephoneNumber() {
        String validPhoneNum1 = "889-236-5455";
        String validPhoneNum2 = "123-123-1234";
        String invalidPhoneNum1 = "123-123-123";
        String invalidPhoneNum2 = "123123123";
        String invalidPhoneNum3 = "123-23-1123";
        String invalidPhoneNum4 = "123-1231-123";

        Assertions.assertTrue(Validations.validateTelephoneNumber(validPhoneNum1));
        assertTrue(Validations.validateTelephoneNumber(validPhoneNum2));
        assertFalse(Validations.validateTelephoneNumber(invalidPhoneNum1));
        assertFalse(Validations.validateTelephoneNumber(invalidPhoneNum2));
        assertFalse(Validations.validateTelephoneNumber(invalidPhoneNum3));
        assertFalse(Validations.validateTelephoneNumber(invalidPhoneNum4));
    }

    @Test
    @DisplayName("Test valid and invalid email addresses")
    void validateEmail() {
        String validEmail1 = "aa@aa.aa";
        String validEmail2 = "a@a.aaaaaaa";
        String validEmail3 = "aa3@aa3.aaa";
        String validEmail4 = "33@33.aa";
        String invalidEmail1 = "@123.123";
        String invalidEmail2 = "123@.123";
        String invalidEmail3 = "123@123.";
        String invalidEmail4 = "123@123.132";
        String invalidEmail5 = "aa@aa.123";

        assertTrue(Validations.validateEmail(validEmail1));
        assertTrue(Validations.validateEmail(validEmail2));
        assertTrue(Validations.validateEmail(validEmail3));
        assertTrue(Validations.validateEmail(validEmail4));
        assertFalse(Validations.validateEmail(invalidEmail1));
        assertFalse(Validations.validateEmail(invalidEmail2));
        assertFalse(Validations.validateEmail(invalidEmail3));
        assertFalse(Validations.validateEmail(invalidEmail4));
        assertFalse(Validations.validateEmail(invalidEmail5));
    }
}