package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {
    private static final int maxLimit=30;
    private String designation;
    @BeforeEach
    public void setUp() {
        designation = "testDesignation";
    }

    @Test
    void getMaxLimit() {
        assertEquals(maxLimit, Equipment.getMaxLimit());
    }

    @Test
    void testEquals() {
        String designation2 = "fakeDesignation";
        String designation3 = "testDesignation";
        assertFalse(designation.equals(designation2));
        assertFalse(designation2.equals(designation));
        assertTrue(designation.equals(designation3));
        assertTrue(designation3.equals(designation));
    }

    @Test
    void testHashCode() {
        String designation1 = designation;
        String designation2 = designation;
        assertEquals(designation1.hashCode(),designation2.hashCode());
    }

    @Test
    void testToString() {
        Equipment equipment = new Equipment(designation);
        String expectedString = "Equipment: testDesignation";
        assertEquals(expectedString, equipment.toString());
    }
}