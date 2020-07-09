package tree.binary;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.rules.TestWatcher;

public class BinaryTreeNodeTester {
    
    // =================================================
    // Test Configuration
    // =================================================
    
    // Global timeout to prevent infinite loops from
    // crashing the test suite
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);
    
    // Each time you pass a test, you get a point! Yay!
    // [!] Requires JUnit 4+ to run
    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            passed++;
        }
    };
    
    // Grade record-keeping
    static int possible = 0, passed = 0;
    
    @Before
    public void init () {
        possible++;
    }
    
    // Used for grading, reports the total number of tests
    // passed over the total possible
    @AfterClass
    public static void gradeReport () {
        System.out.println("============================");
        System.out.println("Tests Complete");
        System.out.println(passed + " / " + possible + " passed!");
        if ((1.0 * passed / possible) >= 0.9) {
            System.out.println("[!] Nice job!"); // Automated acclaim!
        }
        System.out.println("============================");
    }
    
    /**
     * Basically the solution for sameTree, but used to actually validate
     * your implementations
     */
    public static boolean treequal (BinaryTreeNode n1, BinaryTreeNode n2) {
        if (n1 == null || n2 == null) {
            return n1 == n2;
        }
        
        return n1.getString().equals(n2.getString()) &&
               treequal(n1.getChild("L"), n2.getChild("L")) &&
               treequal(n1.getChild("R"), n2.getChild("R"));
    }
    
    // =================================================
    // Unit Tests
    // =================================================

    
    @Test
    public void testDoubleTree_t0() {
        BinaryTreeNode t1 = new BinaryTreeNode("a");
        BinaryTreeNode sln = new BinaryTreeNode("a");
        sln.add("a", "L");
        t1.doubleTree();
        assertTrue(treequal(t1, sln));
    }
    
    @Test
    public void testDoubleTree_t1() {
        BinaryTreeNode t1 = new BinaryTreeNode("a");
        BinaryTreeNode sln = new BinaryTreeNode("a");
        t1.add("b", "L");
        sln.add("a", "L");
        BinaryTreeNode addingTo = sln.getChild("L");
        addingTo.add("b", "L");
        addingTo = addingTo.getChild("L");
        addingTo.add("b", "L");
        t1.doubleTree();
        assertTrue(treequal(t1, sln));
    }
    
    @Test
    public void testDoubleTree_t2() {
        BinaryTreeNode t1 = new BinaryTreeNode("a");
        BinaryTreeNode sln = new BinaryTreeNode("a");
        t1.add("b", "L");
        sln.add("a", "L");
        t1.add("c", "R");
        sln.add("c", "R");
        sln.getChild("R").add("c", "L");
        sln.getChild("L").add("b", "L");
        sln.getChild("L").getChild("L").add("b", "L");
        t1.doubleTree();
        assertTrue(treequal(t1, sln));
    }
    
    @Test
    public void testDoubleTree_t3() {
        BinaryTreeNode t1 = new BinaryTreeNode("a");
        BinaryTreeNode sln = new BinaryTreeNode("a");
        t1.add("b", "L");
        t1.add("c", "R");
        t1.getChild("R").add("d", "L");
        t1.doubleTree();
        sln.add("a", "L");
        sln.add("c", "R");
        sln.getChild("R").add("c", "L");
        sln.getChild("L").add("b", "L");
        sln.getChild("L").getChild("L").add("b", "L");
        BinaryTreeNode addingTo = sln.getChild("R").getChild("L");
        addingTo.add("d", "L");
        addingTo = addingTo.getChild("L");
        addingTo.add("d", "L");
        assertTrue(treequal(t1, sln));
    }
    
    @Test
    public void testSameTree_t0() {
        BinaryTreeNode t1 = null;
        BinaryTreeNode t2 = null;
        assertTrue(BinaryTreeNode.sameTree(t1, t2));
    }

    @Test
    public void testSameTree_t1() {
        BinaryTreeNode t1 = new BinaryTreeNode("a");
        BinaryTreeNode t2 = new BinaryTreeNode("a");
        assertTrue(BinaryTreeNode.sameTree(t1, t2));
        t1.add("b", "L");
        assertFalse(BinaryTreeNode.sameTree(t1, t2));
        t2.add("b", "R");
        assertFalse(BinaryTreeNode.sameTree(t1, t2));
    }
    
    @Test
    public void testSameTree_t2() {
        BinaryTreeNode t1 = new BinaryTreeNode("a");
        BinaryTreeNode t2 = new BinaryTreeNode("a");
        t1.add("b", "L");
        t2.add("b", "L");
        t1.add("c", "R");
        t2.add("c", "R");
        t1.getChild("L").add("d", "R");
        t2.getChild("L").add("d", "R");
        assertTrue(BinaryTreeNode.sameTree(t1, t2));
        t1.getChild("R").add("e", "L");
        t2.getChild("R").add("e", "R");
        assertFalse(BinaryTreeNode.sameTree(t1, t2));
    }
    
    @Test
    public void testSameTree_t3() {
        BinaryTreeNode t1 = new BinaryTreeNode("a");
        BinaryTreeNode t2 = new BinaryTreeNode("a");
        t1.add("b", "L");
        t2.add("b", "L");
        t1.add("c", "R");
        t2.add("c", "R");
        t1.getChild("L").add("d", "R");
        t2.getChild("L").add("d", "R");
        assertTrue(BinaryTreeNode.sameTree(t1, t2));
        t1.getChild("R").add("e", "L");
        t2.getChild("R").add("e", "R");
        assertFalse(BinaryTreeNode.sameTree(t1, t2));
    }
    
}