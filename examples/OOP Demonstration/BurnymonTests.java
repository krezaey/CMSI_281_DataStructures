package forneymon;

import static org.junit.Assert.*;

import org.junit.Test;

public class BurnymonTests {

	@Test
	public void test() {
		Burnymon burny = new Burnymon("Dave");
		assertEquals(burny.getHealth(), 15);
		burny.takeDamage(5, "dampy");
		assertEquals(burny.getHealth(), 10);
	}

}

