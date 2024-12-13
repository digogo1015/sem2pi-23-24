package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class MenuItem {
    private final String description;
    private final Runnable ui;

    /**
     * Constructs a MenuItem with the specified description and UI.
     * Throws an IllegalArgumentException if the description is blank or UI is null.
     *
     * @param description The description of the menu item.
     * @param ui          The UI associated with the menu item.
     */
    public MenuItem(String description, Runnable ui) {
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("MenuItem description cannot be null or empty.");
        if (Objects.isNull(ui))
            throw new IllegalArgumentException("MenuItem does not support a null UI.");
        this.description = description;
        this.ui = ui;
    }

    /**
     * Runs the associated UI when the menu item is selected.
     */
    public void run() {
        this.ui.run();
    }

    /**
     * Checks if the menu item has the specified description.
     *
     * @param description The description to compare.
     * @return True if the menu item has the specified description, false otherwise.
     */
    public boolean hasDescription(String description) {
        return this.description.equals(description);
    }

    /**
     * Returns the description of the menu item.
     *
     * @return The description of the menu item.
     */
    public String toString() {
        return this.description;
    }
}
