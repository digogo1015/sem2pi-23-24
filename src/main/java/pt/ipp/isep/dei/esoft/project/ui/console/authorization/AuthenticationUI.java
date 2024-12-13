package pt.ipp.isep.dei.esoft.project.ui.console.authorization;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.database.Database;
import pt.ipp.isep.dei.esoft.project.domain.Role;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.RoleRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.utils.ExceptionHandler;
import pt.ipp.isep.dei.esoft.project.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class AuthenticationUI implements Runnable {
    private final AuthenticationController ctrl;

    public AuthenticationUI() {
        ctrl = new AuthenticationController();
    }

    public void run() {
        boolean success = doLogin();

        if (success) {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                System.out.println("No role assigned to user.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI, role);
                } else
                    System.out.println("No role selected.");
            }
        }
        this.logout();
    }

    private List<MenuItem> getMenuItemForRoles() {
        List<MenuItem> rolesUI = new ArrayList<>();
        Repositories repositories = Repositories.getInstance();
        RoleRepository roleRepository = repositories.getRoleRepository();
        ArrayList<Role> rolesRepository = roleRepository.copy();

        for (Role role : rolesRepository) {
            Runnable classUI = null;

            try {
                classUI = (Runnable) Class.forName(getClassName(role.getDesignation())).getConstructors()[0].newInstance();
            } catch (Exception e) {
                ExceptionHandler.error(e, "Invalid Role");
            }
            rolesUI.add(new MenuItem(role.getDesignation(), classUI));
        }
        return rolesUI;
    }

    public String getClassName(String className) {
        String[] words = className.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();

        for (String word : words)
            sb.append(capitalize(word));

        return Database.menuPath + sb + "UI";
    }

    private boolean doLogin() {
        System.out.println("\nLogin:");

        int maxAttempts = 3;
        boolean success = false;

        do {
            maxAttempts--;
            String id = Utils.readLineFromConsole("Enter UserId/Email: ");
            String pwd = Utils.readLineFromConsole("Enter Password: ");

            success = ctrl.doLogin(id, pwd);
            if (!success)
                System.out.println("Invalid UserId and/or Password. \n You have  " + maxAttempts + " more attempt(s).");
        } while (!success && maxAttempts > 0);
        return success;
    }

    private void logout() {
        ctrl.doLogout();
    }

    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role) {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();

        while (it.hasNext() && !found) {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found)
                item.run();
        }
        if (!found)
            System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1)
            return roles.get(0);
        else
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
    }
}