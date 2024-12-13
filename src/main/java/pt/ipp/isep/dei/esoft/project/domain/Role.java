package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Role class represents a role or designation.
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 3349437526693196930L;

    private String designation;

    /**
     * Constructs a new Role object with the specified designation.
     *
     * @param designation The designation or title of the role.
     */
    public Role(String designation) {
        this.designation = designation;
    }

    /**
     * Creates a new instance of Role based on the provided designation.
     *
     * @param designation The designation or title of the role.
     * @return A new Role object.
     * @throws IllegalArgumentException If the provided designation is invalid or null.
     */
    public static Role createRole(String designation) throws IllegalArgumentException {
        if (validateRole(designation))
            return new Role(designation);
        throw new IllegalArgumentException();
    }

    private static boolean validateRole(String designation) {
        return !(designation.isBlank());
    }

    /**
     * Gets the designation of the role.
     *
     * @return The designation.
     */
    public String getDesignation() {
        return designation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(designation, role.designation);
    }

    @Override
    public String toString() {
        return "Role: " + designation;
    }
}
