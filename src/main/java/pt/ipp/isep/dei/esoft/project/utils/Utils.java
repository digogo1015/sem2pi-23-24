package pt.ipp.isep.dei.esoft.project.utils;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class Utils {
    static public String readLineFromConsole(String prompt) {
        try {
            System.out.println("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("s");
    }

    static public Object showAndSelectOne(List list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    static public int showAndSelectIndex(List list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    static public int showAndSelectIndexWithZero(List list, String header) {
        showList(list, header);
        return selectsIndexWithZero(list);
    }

    static public void showList(List list, String header) {
        System.out.println(header);

        int index = 0;
        if (list != null) {
            for (Object o : list) {
                index++;
                System.out.println(index + ". " + o.toString());
            }
            System.out.println();
        }
    }

    static public Object selectsObject(List list) {
        String input;
        Integer value;

        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0)
            return null;
        else
            return list.get(value - 1);
    }

    static public int selectsIndexWithZero(List list) {
        String input;
        Integer value;

        do {
            input = Utils.readLineFromConsole("Type your option: ");
            try {
                value = Integer.valueOf(input);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    static public int selectsIndex(List list) {
        String input;
        Integer value;

        do {
            input = Utils.readLineFromConsole("Type your option: ");
            try {
                value = Integer.valueOf(input);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value <= 0 || value > list.size());

        return value - 1;
    }

    static public Date transformStringToDate(String string) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");

        try {
            date = dateFormat.parse(string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public static boolean checkPattern(String regex, String test) {
        Pattern pat = Pattern.compile(regex);

        return pat.matcher(test).matches();
    }

    public static String formatNBedrooms(String num) {
        switch (num) {
            case "T0":
                return "0";
            case "T1":
                return "1";
            case "T2":
                return "2";
            case "T3":
                return "3";
            case "T4":
                return "4";
            case "T5":
                return "5";
            case "More":
                return "6";
        }
        return "0";
    }

    public static boolean checkNBedrooms(String n1, String n2) {
        return n1.equals(n2) || n1.equals("6") && Integer.parseInt(n2) > 5;
    }

    public static void revertList(int[] areaList) {
        int[] tmp = new int[areaList.length];

        for (int i = areaList.length - 1, j = 0; i >= 0; i--, j++)
            tmp[j] = areaList[i];

        for (int i = 0; i < areaList.length; i++)
            areaList[i] = tmp[i];
    }


    static public boolean launchFxApp(boolean javaFxlanched, Class<? extends Application> app) {
        if (!javaFxlanched) {
            Platform.setImplicitExit(false);

            try {
                new Thread(() -> {
                    try {
                        Application.launch(app);
                    } catch (Exception e) {
                        launchFxApp(true, app);
                    }

                }).start()

                ;
            } catch (Exception e) {

            }
            javaFxlanched = true;
        } else {
            Platform.runLater(() -> {
                try {
                    Application application = app.newInstance();
                    Stage primaryStage = new Stage();
                    application.start(primaryStage);
                } catch (Exception ignored) {}
            });
        }
        return javaFxlanched;
    }
}