package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;

public class Main {
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();

        try {
            MainMenuUI menu = new MainMenuUI();
            menu.run();

            SaveSER saveSER = new SaveSER();
            saveSER.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}