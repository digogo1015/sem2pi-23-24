package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnovaTableTest {
    @Test
    void testCreateAddress() {
        AnovaTable anovaTable= new AnovaTable("test","test","test","test","test","test");
        assertNotNull(anovaTable);
    }
    @Test
    void testGetters() {
        AnovaTable anovaTable= new AnovaTable("test","test","test","test","test","test");
        assertEquals("test", anovaTable.getFonteDeVariacao());
        assertEquals("test", anovaTable.getSomaQuad());
        assertEquals("test", anovaTable.getGrausLib());
        assertEquals("test", anovaTable.getMediaQuad());
        assertEquals("test", anovaTable.getEstatisticaTeste());
        assertEquals("test", anovaTable.getfSignifica());
    }
}