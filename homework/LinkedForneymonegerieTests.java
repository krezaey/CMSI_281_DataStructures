/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  LinkedForneymonagerieTests.java
 *  Author        :  Keziah Camille Rezaey
 *  Due Date      :  2019-10-21
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package forneymonagerie;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LinkedForneymonegerieTests {
    
    // =================================================
    // Test Configuration
    // =================================================
    
    // Used as the basic empty menagerie to test; the @Before
    // method is run before every @Test
    LinkedForneymonegerie fm;
    @Before
    public void init () {
        fm = new LinkedForneymonegerie();
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
        fm.collect("Hydromon");
        fm.collect("Hydromon");
        assertTrue(fm.contains("Dampymon"));
        assertTrue(fm.contains("Burnymon"));
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
        assertEquals(0, fm.countType("forneymon"));
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
        assertEquals("Burnymon", fm.nth(0));
        assertEquals("Zappymon", fm.nth(1));
        assertEquals("Dampymon", fm.nth(2));
    }

    @Test
    public void testMostRecentAtRarity() {
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        assertEquals("Dampymon", fm.mostRecentAtRarity(2));
        assertEquals(null, fm.mostRecentAtRarity(1));
    }

    @Test
    public void testClone() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        LinkedForneymonegerie dolly = fm.clone();
        assertEquals(dolly.countType("Dampymon"), 2);
        assertEquals(dolly.countType("Burnymon"), 1);
        dolly.collect("Zappymon");
        assertFalse(fm.contains("Zappymon"));
    }

    @Test
    public void testTrade() {
        LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
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
        LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
        fm2.collect("Dampymon");
        fm2.collect("Zappymon");
        LinkedForneymonegerie fm3 = LinkedForneymonegerie.diffMon(fm1, fm2);
        assertEquals(fm3.countType("Dampymon"), 1);
        assertEquals(fm3.countType("Burnymon"), 1);
        assertFalse(fm3.contains("Zappymon"));
        fm3.collect("Leafymon");
        assertFalse(fm1.contains("Leafymon"));
        assertFalse(fm2.contains("Leafymon"));
    }

    @Test
    public void testSameForneymonegerie() {
        LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
        fm2.collect("Burnymon");
        fm2.collect("Dampymon");
        fm2.collect("Dampymon");
        assertTrue(LinkedForneymonegerie.sameCollection(fm1, fm2));
        assertTrue(LinkedForneymonegerie.sameCollection(fm2, fm1));
        fm2.collect("Leafymon");
        assertFalse(LinkedForneymonegerie.sameCollection(fm1, fm2));
    }
    
    @Test
    public void testIteratorBasics() {
        fm.collect("Andrewmon");
        fm.collect("Andrewmon");
        fm.collect("Baddymon");
        fm.collect("Cooliomon");
        LinkedForneymonegerie.Iterator it = fm.getIterator();

        // Test next()
        assertEquals("Andrewmon", it.getType());
        it.next();
        assertEquals("Andrewmon", it.getType());
        it.next();
        assertEquals("Baddymon", it.getType());
        it.next();
        assertEquals("Cooliomon", it.getType());
        assertFalse(it.hasNext());
        
        // Test prev()
        assertEquals("Cooliomon", it.getType());
        it.prev();
        assertEquals("Baddymon", it.getType());
        it.prev();
        assertEquals("Andrewmon", it.getType());
        it.prev();
        assertEquals("Andrewmon", it.getType());
        assertFalse(it.hasPrev());
        
        it.replaceAll("Mimicmon");
        assertEquals(2, fm.countType("Mimicmon"));
        assertTrue(it.isValid());
        
        fm.collect("Cooliomon");
        assertFalse(it.isValid());
    }

}
