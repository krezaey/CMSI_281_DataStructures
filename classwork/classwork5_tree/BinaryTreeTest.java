package tree.binary;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinaryTreeTest {

	@Test
	public void testHeight() {
	      BinaryTreeNode t1 = new BinaryTreeNode("A");
	      assertEquals(1, t1.height(t1));
	      t1.add("B", "L");
	      assertEquals(2, t1.height(t1));
	      t1.add("C", "R");
	      assertEquals(2, t1.height(t1));
	      BinaryTreeNode ref = t1.getChild("L");
	      ref.add("D", "R");
	      assertEquals(3, t1.height(t1));
	      ref = ref.getChild("R");
	      ref.add("F", "L");
	      assertEquals(4, t1.height(t1));
	  }
}
