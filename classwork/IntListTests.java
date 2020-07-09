package intlist;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntListTests {

	@Test
	public void test() {
		IntList arrONE = new IntList();
		arrONE.append(2);
		assertEquals(2, arrONE.getAt(0));
		arrONE.append(4);
		assertEquals(4, arrONE.getAt(1));
		arrONE.append(5);
		assertEquals(5, arrONE.getAt(2));
		arrONE.insertAt(3, 1);
		assertEquals(3, arrONE.getAt(1));
		arrONE.prepend(1);
		assertEquals(1, arrONE.getAt(0));
		assertEquals(2, arrONE.getAt(1));
		assertEquals(3, arrONE.getAt(2));
		assertEquals(4, arrONE.getAt(3));
		assertEquals(5, arrONE.getAt(4));
		
		IntList arrTWO = new IntList();
		arrTWO.append(6);
		assertEquals(6, arrTWO.getAt(0));
		arrTWO.append(8);
		assertEquals(8, arrTWO.getAt(1));
		arrTWO.insertAt(7, 1);
		assertEquals(7, arrTWO.getAt(1));
		arrTWO.prepend(6);
		assertEquals(6, arrTWO.getAt(0));
		assertEquals(6, arrTWO.getAt(1));
		arrTWO.removeAll(6);
		assertEquals(7, arrTWO.getAt(0));
		assertEquals(8, arrTWO.getAt(1));
	}

}
