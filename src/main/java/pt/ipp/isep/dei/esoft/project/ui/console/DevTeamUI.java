package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.utils.Print;

/**
 * The DevTeamUI class represents a user interface that displays information about a development team.
 * It implements the Runnable interface to support execution in a separate thread.
 */
public class DevTeamUI implements Runnable {

    /**
     * Displays information about the development team.
     * Prints the names and email addresses of team members.
     */
    @Override
    public void run() {
        Print.text("\nDevelopment Team:");
        Print.text("\t Diogo Araújo\t1221219@isep.ipp.pt");
        Print.text("\t Simão Lopes\t1220779@isep.ipp.pt");
        Print.text("\t Tiago Alves\t1220780@isep.ipp.pt");
        Print.text("\t Tiago Santos\t1221003@isep.ipp.pt");
    }
}