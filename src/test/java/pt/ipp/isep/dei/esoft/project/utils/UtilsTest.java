package pt.ipp.isep.dei.esoft.project.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    @DisplayName("Check Invalid and Valid Dates")
    void transformStringToDate() {
        Date date = new Date();

        try {
            date = (new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss")).parse("12/12/2030, 22:12:12");
        } catch (Exception ignored) {}

        Date test1 = Utils.transformStringToDate("12/12/2030, 22:12:12");
        Date test2 = Utils.transformStringToDate("12/12/2020, 22:12:12");

        assertEquals(date.toString(), test1.toString());
        assertNotEquals(date.toString(), test2.toString());
    }

    @Test
    void checkPattern() {
        String regex1 = "^[0-9]{3}-[0-9]{3}-[0-9]{4}$";
        String regex2 = "^[0-9]{3}-[0-9]{2}-[0-9]{4}$";

        String test1 = "999-999-9999";
        String test2 = "921-999-9999";
        String test3 = "991-99-9999";
        String test4 = "999-99-8999";

        assertTrue(Utils.checkPattern(regex1, test1));
        assertTrue(Utils.checkPattern(regex1, test2));
        assertTrue(Utils.checkPattern(regex2, test3));
        assertTrue(Utils.checkPattern(regex2, test4));
        assertFalse(Utils.checkPattern(regex1, test3));
        assertFalse(Utils.checkPattern(regex1, test4));
        assertFalse(Utils.checkPattern(regex2, test1));
        assertFalse(Utils.checkPattern(regex2, test2));
    }

    @Test
    void formatNBedrooms() {
        String test1 = "T0";
        String test2 = "T1";
        String test3 = "1";
        String test4 = "0";

        String num0 = "0";
        String num1 = "1";

        assertEquals(Utils.formatNBedrooms(test1), num0);
        assertEquals(Utils.formatNBedrooms(test2), num1);
        assertEquals(Utils.formatNBedrooms(test3), num0);
        assertEquals(Utils.formatNBedrooms(test4), num0);
        assertNotEquals(Utils.formatNBedrooms(test1), num1);
        assertNotEquals(Utils.formatNBedrooms(test2), num0);
        assertNotEquals(Utils.formatNBedrooms(test3), num1);
        assertNotEquals(Utils.formatNBedrooms(test4), num1);
    }

    @Test
    void checkNBedrooms() {
        String test1 = "0";
        String test2 = "1";
        String test3 = "5";
        String test4 = "7";

        String num0 = "0";
        String num1 = "1";
        String num2 = "5";
        String num3 = "7";

        assertTrue(Utils.checkNBedrooms(test1, num0));
        assertTrue(Utils.checkNBedrooms(test2, num1));
        assertTrue(Utils.checkNBedrooms(test3, num2));
        assertTrue(Utils.checkNBedrooms(test4, num3));
        assertFalse(Utils.checkNBedrooms(test1, num1));
        assertFalse(Utils.checkNBedrooms(test2, num0));
        assertFalse(Utils.checkNBedrooms(test3, num0));
        assertFalse(Utils.checkNBedrooms(test4, num0));
    }

    @Test
    void revertList() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] arr2 = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] arr3 = new int[]{7, 6, 5, 4, 3, 2, 1};

        assertEquals(Arrays.toString(arr), Arrays.toString(arr2));

        Utils.revertList(arr);

        assertNotEquals(Arrays.toString(arr), Arrays.toString(arr2));

        assertEquals(Arrays.toString(arr), Arrays.toString(arr3));
    }
}