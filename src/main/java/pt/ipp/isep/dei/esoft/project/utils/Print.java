package pt.ipp.isep.dei.esoft.project.utils;

import java.util.List;

public class Print implements Runnable {
    String text;

    @Override
    public void run() {
        text(text);
    }

    public Print(String text) {
        this.text = text;
    }

    public static void text(String text) {
        System.out.println(text);
    }

    public static <t> void printList(List<t> list) {
        for (t e : list)
            text(e.toString());
    }
}