package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Photograph class represents a photograph with a link URL.
 */
public class Photograph implements Serializable {

    private static final long serialVersionUID = 818493703629988657L;

    private static final int maxLimit = 30;
    private String linkURL;

    /**
     * Constructs a Photograph object with the provided link URL.
     *
     * @param linkURL The link URL of the photograph.
     */
    public Photograph(String linkURL) {
        this.linkURL = linkURL;
    }

    /**
     * Returns the maximum limit for photographs.
     *
     * @return The maximum limit.
     */
    public static int getMaxLimit() {
        return maxLimit;
    }

    /**
     * Checks if the provided object is equal to this Photograph object.
     *
     * @param o The object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photograph that = (Photograph) o;
        return Objects.equals(linkURL, that.linkURL);
    }

    /**
     * Computes the hash code of this Photograph object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(linkURL);
    }
}
