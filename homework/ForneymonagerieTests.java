/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ForneymonagerieTests.java
 *  Author        :  Keziah Camille Rezaey
 *  Due Date      :  2019-09-26
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package forneymonagerie;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ForneymonagerieTests {
    
    // =================================================
    // Test Configuration
    // =================================================
    
    // Used as the basic empty menagerie to test; the @Before
    // method is run before every @Test
    Forneymonagerie fm;
    @Before
    public void init () {
        fm = new Forneymonagerie();
    }

    
    // =================================================
    // Unit Tests
    // =================================================
    
    @Test
    public void testSize() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        assertEquals(2, fm.size());
        fm.collect("Burnymon");
        assertEquals(3, fm.size());
    }

    @Test
    public void testTypeSize() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        assertEquals(1, fm.typeSize());
        fm.collect("Burnymon");
        assertEquals(2, fm.typeSize());
    }

    @Test
    public void testCollect() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Zappymon");
        assertTrue(fm.contains("Dampymon"));
        assertTrue(fm.contains("Burnymon"));
        assertTrue(fm.contains("Burnymon"));
    }
    
    @Test
    public void testHelperNewTypedAdded() {
    	fm.collect("Dampymon");
    	fm.collect("Burnymon");
    	fm.collect("Leafymon");
    	assertFalse(fm.collect("Dampymon"));
    	assertFalse(fm.collect("Leafymon"));
    	assertFalse(fm.collect("Burnymon"));
    	assertTrue(fm.collect("Mysterymon"));
    	assertTrue(fm.collect("Fakemon"));
    	assertTrue(fm.collect("Moonmon"));
    }
    
    @Test 
    public void testHelperRemove() {
    	fm.collect("Dampymon");
    	fm.collect("Burnymon");
    	fm.collect("Burnymon");
    	fm.collect("Dampymon");
    	fm.releaseType("Burnymon");
    	assertFalse(fm.contains("Burnymon"));
    	assertEquals(0, fm.countType("Burnymon"));
    }
    
    @Test
    public void testRelease() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        assertEquals(2, fm.size());
        assertEquals(1, fm.typeSize());
        fm.release("Dampymon");
        assertEquals(1, fm.size());
        assertEquals(1, fm.typeSize());
    }

    @Test
    public void testReleaseType() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertEquals(3, fm.size());
        assertEquals(2, fm.typeSize());
        fm.releaseType("Dampymon");
        assertEquals(1, fm.size());
        assertEquals(1, fm.typeSize());
    }

    @Test
    public void testCountType() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertEquals(2, fm.countType("Dampymon"));
        assertEquals(1, fm.countType("Burnymon"));
        assertEquals(0, fm.countType("Forneymon"));
        System.out.println("Test");
        System.out.println("Test");
    }

    @Test
    public void testContains() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertTrue(fm.contains("Dampymon"));
        assertTrue(fm.contains("Burnymon"));
        assertFalse(fm.contains("forneymon"));
    }

    @Test
    public void testNth() {
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Zappymon");
        fm.collect("Dampymon");
        assertEquals("Dampymon", fm.nth(0));
        assertEquals("Dampymon", fm.nth(1));
        assertEquals("Burnymon", fm.nth(2));
        assertEquals("Zappymon", fm.nth(3));
        fm.release("Dampymon");
        assertEquals("Dampymon", fm.nth(0));
        assertEquals("Burnymon", fm.nth(1));
        assertEquals("Zappymon", fm.nth(2));
        
        fm.releaseType("Dampymon");
        fm.releaseType("Burnymon");
        fm.releaseType("Zappymon");
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Zappymon");
        fm.collect("Burnymon");
        assertEquals("Dampymon", fm.nth(0));
        assertEquals("Dampymon", fm.nth(1));
        assertEquals("Dampymon", fm.nth(2));
        assertEquals("Dampymon", fm.nth(3));
        assertEquals("Zappymon", fm.nth(4));
        assertEquals("Burnymon", fm.nth(5));
    }

    @Test
    public void testMostRecentAtRarity() {
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        assertEquals("Dampymon", fm.mostRecentAtRarity(2));
        assertEquals(null, fm.mostRecentAtRarity(1));
        fm.releaseType("Dampymon");
        fm.releaseType("Burnymon");
        fm.collect("Randomon");
        fm.collect("Leafymon");
        assertEquals("Leafymon", fm.mostRecentAtRarity(1));
        fm.collect("Impostermon");
        fm.collect("Randomon");
        fm.collect("Impostermon");
        assertEquals("Impostermon", fm.mostRecentAtRarity(2));
        fm.collect("Sneakymon");
        fm.collect("Sneakymon");
        fm.collect("Sneakymon");
        assertEquals("Sneakymon", fm.mostRecentAtRarity(3));       
    }

    @Test
    public void testClone() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        Forneymonagerie dolly = fm.clone();
        assertEquals(dolly.countType("Dampymon"), 2);
        assertEquals(dolly.countType("Burnymon"), 1);
        dolly.collect("Zappymon");
        assertFalse(fm.contains("Zappymon"));
        
        dolly.release("Dampymon");
        dolly.releaseType("Burnymon");
        dolly.releaseType("Zappymon");
        dolly.collect("Burnymon");
        dolly.collect("Moonmon");
        dolly.collect("Sunmon");
        Forneymonagerie clowny = dolly.clone();
        assertEquals(clowny.countType("Dampymon"), 1);
        assertEquals(clowny.countType("Burnymon"), 1);
        assertEquals(clowny.countType("Moonmon"), 1);
        assertEquals(clowny.countType("Sunmon"), 1);
        clowny.collect("Sneakymon");
        assertFalse(dolly.contains("Sneakymon"));
    }

    @Test
    public void testTrade() {
        Forneymonagerie fm1 = new Forneymonagerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        Forneymonagerie fm2 = new Forneymonagerie();
        fm2.collect("Zappymon");
        fm2.collect("Leafymon");
        fm1.trade(fm2);
        assertTrue(fm1.contains("Zappymon"));
        assertTrue(fm1.contains("Leafymon"));
        assertTrue(fm2.contains("Dampymon"));
        assertTrue(fm2.contains("Burnymon"));
        assertFalse(fm1.contains("Dampymon"));
    }

    @Test
    public void testDiffMon() {
        Forneymonagerie fm1 = new Forneymonagerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        Forneymonagerie fm2 = new Forneymonagerie();
        fm2.collect("Dampymon");
        fm2.collect("Zappymon");
        Forneymonagerie fm3 = Forneymonagerie.diffMon(fm1, fm2);
        assertEquals(fm3.countType("Dampymon"), 1);
        assertEquals(fm3.countType("Burnymon"), 1);
        assertFalse(fm3.contains("Zappymon"));
        fm3.collect("Leafymon");
        assertFalse(fm1.contains("Leafymon"));
        assertFalse(fm2.contains("Leafymon"));
        
        Forneymonagerie fm4 = new Forneymonagerie();
        fm4.collect("Leafymon");
        fm4.collect("Leafymon");
        fm4.collect("Leafymon");
        fm4.collect("Dampymon");
        fm4.collect("Dampymon");
        fm4.collect("Burnymon");
        Forneymonagerie fm5 = new Forneymonagerie();
        fm5.collect("Leafymon");
        fm5.collect("Dampymon");
        fm5.collect("Dampymon");
        Forneymonagerie fm6 = Forneymonagerie.diffMon(fm4, fm5);
        assertEquals(fm6.countType("Leafymon"), 2);
        assertEquals(fm6.countType("Burnymon"), 1);
        fm6.collect("Impostermon");
        assertFalse(fm4.contains("Impostermon"));
        assertFalse(fm5.contains("Impostermon"));
    }

    @Test
    public void testSameForneymonegerie() {
        Forneymonagerie fm1 = new Forneymonagerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        Forneymonagerie fm2 = new Forneymonagerie();
        fm2.collect("Burnymon");
        fm2.collect("Dampymon");
        fm2.collect("Dampymon");
        assertTrue(Forneymonagerie.sameCollection(fm1, fm2));
        assertTrue(Forneymonagerie.sameCollection(fm2, fm1));
        fm2.collect("Leafymon");
        assertFalse(Forneymonagerie.sameCollection(fm1, fm2));
        
        Forneymonagerie fm3 = new Forneymonagerie();
        fm3.collect("Leafymon");
        fm3.collect("Burnymon");
        fm3.collect("Leafymon");
        fm3.collect("Leafymon");
        fm3.collect("Burnymon");
        fm3.collect("Dampymon");
        Forneymonagerie fm4 = new Forneymonagerie();
        fm4.collect("Leafymon");
        fm4.collect("Dampymon");
        fm4.collect("Leafymon");
        fm4.collect("Leafymon");
        fm4.collect("Burnymon");
        fm4.collect("Burnymon");
        assertTrue(Forneymonagerie.sameCollection(fm3, fm4));
        assertTrue(Forneymonagerie.sameCollection(fm4, fm3));
        fm4.collect("Intrudermon");
        assertFalse(Forneymonagerie.sameCollection(fm3, fm4));
        assertFalse(Forneymonagerie.sameCollection(fm4, fm3));
    }
}
