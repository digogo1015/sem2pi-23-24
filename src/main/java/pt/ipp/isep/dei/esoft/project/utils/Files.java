package pt.ipp.isep.dei.esoft.project.utils;

import java.io.*;
import java.util.*;

public class Files {

    /**
     * Reads a full csv files
     *
     * @param filename name of the file
     * @return the whole file in an array list of type String[]
     */
    public static ArrayList<String[]> readCSVFile(String filename) throws IOException {
        File file = new File(filename);
        ArrayList<String[]> arr = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine())
                arr.add(sc.nextLine().split(";"));
            return arr;
        } catch (IOException io) {
            throw new IOException("Invalid CSV File");
        }
    }

    /**
     * Function to append text to file
     *
     * @param filename the name of the file
     * @param text     the text to write in file
     */
    public static void appendToFile(String filename, String text) {
        try {
            FileWriter fw = new FileWriter(filename, true);

            fw.write(text);
            fw.flush();
            fw.close();
        } catch (IOException ignored) {
        }
    }

    public static boolean sendInfoCSV(String filename, String text) {
        Files.appendToFile(filename, text);
        Files.updateFile(filename);
        return true;
    }

    public static boolean sendInfo(String filename, String text) {
        Files.appendToFile(filename, text);
        return true;
    }

    /**
     * Function to update a file, keeps only non-repeated lines
     *
     * @param filename the name of the file used
     */
    public static void updateFile(String filename) {
        ArrayList<String[]> data;
        Hashtable<String, String> login = new Hashtable<>();

        try {
            data = Files.readCSVFile(filename);
            for (String[] line : data)
                login.put(line[0], line[1]);
            try {
                Enumeration<String> keys = login.keys();
                PrintWriter pw = new PrintWriter(filename);

                while (keys.hasMoreElements()) {
                    String key = keys.nextElement();
                    pw.printf("%s;%s\n", key, login.get(key));
                }
                pw.flush();
                pw.close();
            } catch (Exception e) {
                ExceptionHandler.ioException(e);
            }
        } catch (Exception e) {
            ExceptionHandler.ioException(e);
        }
    }

    /**
     * Saves an object in a file
     *
     * @param object   the object
     * @param filename the file in which to save
     */
    public static void save(Object object, String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);

            oos.writeObject(object);
            oos.close();
            fileOutputStream.close();
        } catch (Exception ignored) {}
    }

    /**
     * Reads an object from a file
     *
     * @param filename the file
     * @return the object
     */
    public static Object read(String filename) {
        Object object = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);

            object = ois.readObject();
            ois.close();
            fileInputStream.close();
        } catch (Exception ignored) {}
        return object;
    }

    public static void clearFile(String filename) {
        try {
            PrintWriter pw = new PrintWriter(filename);

            pw.flush();
            pw.close();
        } catch (Exception ignored) {}
    }

    public static void writeToFile(String text, String filename) {
        try {
            PrintWriter pw = new PrintWriter(filename);

            pw.println(text);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            ExceptionHandler.ioException(e);
        }
    }

    public static boolean exitsFile(String filename) {
        File file = new File(filename);

        return file.exists() && !file.isDirectory();
    }

    public static boolean validCSV(String filename) {
        try {
            ArrayList<String[]> data = readCSVFile(filename);

            int len = data.remove(0).length;

            for (String[] line : data)
                if (line.length != len)
                    return false;
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public static Properties readProperties(String filename) {
        Properties properties = new Properties();
        try {
            InputStream in = new FileInputStream(filename);

            properties.load(in);
            in.close();
        } catch (Exception ignored) {}
        return properties;
    }

    public static String getPropertiesByKey(String filename, String key) {
        Properties properties = readProperties(filename);

        return properties.getProperty(key);
    }

    public static ArrayList<String> getFilesInDir(String legacyPath) {
        ArrayList<String> files = new ArrayList<>();
        File dir = new File(legacyPath);

        File[] filesDir = dir.listFiles();

        if (filesDir != null)
            for (File file : filesDir)
                if (file.isFile() && file.getName().contains(".csv"))
                    files.add(file.getName());
        return files;
    }
}