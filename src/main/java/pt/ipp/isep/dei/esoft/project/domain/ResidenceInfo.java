package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.utils.Input;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import static pt.ipp.isep.dei.esoft.project.utils.Utils.checkNBedrooms;

public class ResidenceInfo implements Serializable {

    private static final long serialVersionUID = 5069199181685085358L;

    private String numBedrooms;
    private String numBathrooms;
    private String numParkingSpaces;
    private ArrayList<Equipment> equipment;

    public ResidenceInfo(String numBedrooms, String numBathrooms, String numParkingSpaces, ArrayList<Equipment> equipment) {
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.numParkingSpaces = numParkingSpaces;
        this.equipment = equipment;
    }

    /**
     * Creates a new instance of ResidenceInfo based on the provided parameters.
     *
     * @param numBedrooms      The number of bedrooms in the residence.
     * @param numBathrooms     The number of bathrooms in the residence.
     * @param numParkingSpaces The number of parking spaces available for the residence.
     * @param equipment        The list of equipment available in the residence.
     * @return A new ResidenceInfo object.
     * @throws InvalidArguments If the provided arguments are invalid or null.
     */
    public ResidenceInfo createResidenceInfo(String numBedrooms, String numBathrooms, String numParkingSpaces, ArrayList<Equipment> equipment) throws InvalidArguments {
        if (validateResidenceInfo(numBathrooms, numBathrooms, numParkingSpaces, equipment))
            return new ResidenceInfo(numBedrooms, numBathrooms, numParkingSpaces, equipment);
        throw new InvalidArguments("Invalid Residence Info");
    }

    private boolean validateResidenceInfo(String numBathrooms, String numBathrooms1, String numParkingSpaces, ArrayList<Equipment> equipment) {
        return true;
    }

    public static ResidenceInfo getInfo() {
        return new ResidenceInfo(Input.requestNumber("Number of Bedrooms:"),
                Input.requestNumber("Number of Bathrooms:"), Input.requestNumber("Number of Parking Spaces:"),
                Input.requestEquipment());
    }

    public String getNumBedrooms() {
        return numBedrooms;
    }

    public String getNumBathrooms() {
        return numBathrooms;
    }

    public String getNumParkingSpaces() {
        return numParkingSpaces;
    }

    public boolean hasNumberOfBedrooms(String numBedrooms) {
        return checkNBedrooms(numBedrooms, this.numBedrooms);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResidenceInfo residence = (ResidenceInfo) o;
        return Objects.equals(numBedrooms, residence.numBedrooms) && Objects.equals(numBathrooms, residence.numBathrooms) && Objects.equals(numParkingSpaces, residence.numParkingSpaces) && Objects.equals(equipment, residence.equipment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numBedrooms, numBathrooms, numParkingSpaces, equipment);
    }

    @Override
    public String toString() {
        return "\nBedrooms: " + numBedrooms + "; Bathrooms: " + numBathrooms + "; Parking Spaces: " + numParkingSpaces + ";\nEquipment: " + equipment;
    }
}