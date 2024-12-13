package pt.ipp.isep.dei.esoft.project.utils;

import java.text.SimpleDateFormat;

public class Time {
    public static String getDateNow() {
        return new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(new java.util.Date());
    }

    public static String getDayNow() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
    }
}