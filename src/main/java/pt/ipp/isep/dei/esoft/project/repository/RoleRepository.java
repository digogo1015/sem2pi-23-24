package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * The RoleRepository class is responsible for managing roles in the system.
 */
public class RoleRepository {
    private List<Role> roleList = new ArrayList<>();

    /**
     * Creates a copy of the role list.
     *
     * @return An ArrayList containing the copied roles, or null if the original list is null.
     */
    public ArrayList<Role> copy() {
        if (roleList != null)
            return new ArrayList<>(roleList);
        return null;
    }

    /**
     * Adds a role to the repository if it passes the validation.
     *
     * @param role The role to be added.
     */
    public void addRole(Role role) {
        if (validateRole(role))
            roleList.add(role);
    }

    /**
     * Retrieves a role based on its designation.
     *
     * @param designation The designation of the role.
     * @return The role matching the designation, or null if no matching role is found.
     */
    public Role getRoleByDesignation(String designation) {
        ArrayList<Role> list = copy();
        for (Role r : list)
            if (r.getDesignation().equals(designation))
                return r;
        return null;
    }

    /**
     * Validates a role by checking if a role with the same designation already exists in the repository.
     *
     * @param role The role to be validated.
     * @return true if the role is valid (no existing role with the same designation), false otherwise.
     */
    private boolean validateRole(Role role) {
        for (Role r : this.copy())
            if (r.getDesignation().equals(role.getDesignation()))
                return false;
        return true;
    }
}
