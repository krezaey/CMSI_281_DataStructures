package forneymon.cardgame;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlippingForneymonTests {

	@Test
	public void test() {	
		FlippingForneymonCard testONE = new FlippingForneymonCard();
		assertEquals("?: ?", testONE.toString());
		assertEquals(false, testONE.flip());
		
		FlippingForneymonCard testTWO= new FlippingForneymonCard("Dimitri", "Dampymon", true);
		assertEquals("Dampymon: Dimitri",testTWO.toString());
		assertEquals(true, testTWO.flip());
		
		FlippingForneymonCard testTHREE = new FlippingForneymonCard();
		FlippingForneymonCard testFOUR = new FlippingForneymonCard("Slyvain", "Burnymon", true);
		assertEquals(2, testTHREE.match(testFOUR));
		testTHREE.flip();
		assertEquals(0, testTHREE.match(testFOUR));
		
		FlippingForneymonCard testFIVE = new FlippingForneymonCard("Felix", "Leafymon", true);
		FlippingForneymonCard testSIX = new FlippingForneymonCard("Felix", "Leafymon", false);
		assertEquals(2, testFIVE.match(testSIX));
		testSIX.flip();
		assertEquals(1, testFIVE.match(testSIX));
		
		FlippingForneymonCard testSEVEN = new FlippingForneymonCard("Ingrid", "Dampymon", true);
		FlippingForneymonCard testEIGHT = new FlippingForneymonCard("Dedue", "Dampymon", true);
		assertEquals(0, testSEVEN.match(testEIGHT));
	}

}
