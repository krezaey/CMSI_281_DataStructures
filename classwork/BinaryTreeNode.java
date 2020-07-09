package tree.binary;

public class BinaryTreeNode {
    
    private String data;
    private BinaryTreeNode left, right;
    
    /**
     * Creates a new BinaryTreeNode that can be linked to
     * others to form a tree of arbitrary depth
     * @param s The data to store at this tree node
     */
    BinaryTreeNode (String s) {
        data = s;
    }
    
    /**
     * Creates a new BinaryTreeNode storing data String s
     * at the left or right child of the current one.
     * @param s The data to store
     * @param child String "L" or "R" indicating desired child
     */
    public void add (String s, String child) {
        if (child.equals("L")) {
            left = new BinaryTreeNode(s);
        } else if (child.equals("R")) {
            right = new BinaryTreeNode(s);
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Returns the BinaryTreeNode located at the desired
     * location relative to this one.
     * @param child String "L" or "R" indicating desired child
     * @return The BinaryTreeNode at that position
     */
    public BinaryTreeNode getChild (String child) {
        return (child.equals("L")) ? left : right;
    }
    
    /**
     * Returns this node's String data
     * @return The data stored
     */
    public String getString () {
        return data;
    }
    
    /**
     * Starts double tree at node
     * @param node, BinaryTreeNode
     */
    private void doubleTree(BinaryTreeNode node) {
    	if (data == null) { return; }
    	
    	BinaryTreeNode temp;
    	
    	if (node.getChild("L") != null) { doubleTree(node.getChild("L")); }
    	if (node.getChild("R") != null) { doubleTree(node.getChild("R")); }
    	
    	temp = node.getChild("L");
    	node.add(node.data, "L");
        node.left.left = temp;
    }
    
    /**
     * Doubles the tree rooted at the node on which this method
     * is called, creating a duplicate of each node, storing the
     * duplicate at the left reference of the original, and then
     * moving any previous left-child from the original to the
     * left child of the duplicate.
     */
    
    public void doubleTree () {
       doubleTree(this);
    }
        
    /**
     * Given two Binary Trees rooted at the provided BinaryTreeNodes
     * n1 and n2, determines whether or not the two trees are
     * equivalent (i.e., have the same nodes with the same values in
     * the same locations in the tree).
     * @param n1 The root of tree 1
     * @param n2 The root of tree 2
     * @return Whether or not n1 and n2 represent the same tree
     */
    
    public static boolean sameTree (BinaryTreeNode n1, BinaryTreeNode n2) {
    	if (n1 == null && n2 == null) { return true; }
    	else if (n1 == null || n2 == null) { return false; }
    	else { 
    		return ((n1.data == n2.data && (sameTree(n1.left, n2.left)) && (sameTree(n1.right, n2.right)))); 
    	}
    }  
    
    public int height(BinaryTreeNode n) {    	
    	if (n != null) {
    		int left = height(n.left);
        	int right = height(n.right);
        	if (left > right) { return left + 1; }
        	else { return right + 1; }
    	}
    	return 0;
    }
}
