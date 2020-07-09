package forneymon.cardgame;

/**
 * Basic card class for the Forneymon Card Matching game
 * @author Keziah Rezaey
 * @author Moriah Scott
 * @author Leilani Davis
 */

public class FlippingForneymonCard extends ForneymonCard {
	
	private boolean faceup;
	
	/**
	 * Constructor with no parameters, creates default instance with name, type, and face down properties
	 */
    
    public FlippingForneymonCard() {
    	super();
    	this.faceup = false;
    }
    
    /**
     * Parameterized constructor to specify name, type, and whether it is face up or down
     * @param String name
     * @param String type
     * @param boolean faceup, true if face up, false if face down
     */
    
    public FlippingForneymonCard(String name, String type, boolean faceup) {
    	super(name, type);
    	this.faceup = faceup;
    }
    
    /**
	 * String representation of the FlippingForneymonCard instance
	 * @return String "type: name" if face up and "?: ?" if face down
	 */
    
    public String toString() {
    	if(!faceup) {
    		return "?: ?";
    	}
    	return super.toString();
    }
    
    /**
     * Flips the card onto the other side, i.e if the card is face up it will now be face down and vice versa
     * @return boolean facedown, true if face down, false if face up
     */
    
    public boolean flip() {
    	faceup = !faceup;
    	boolean facedown = !faceup;
    	return facedown;
    }
    
    /**
     * Indicates whether any two cards are face up, one of them is face down, or both are face up and have the same name and type
     * @param FlippingForneymonCard another
     * @return int result (0, 1, 2)
     *         0 indicates that the two cards are both face up but are not necessarily matching in name or type
     *         1 indicates that the two cards have the same size, name and are both face up
     *         2 indicates that either one of the cards are face down
     */
    
    public int match(FlippingForneymonCard another) {
    	int eitherDown = 2;
    	int sameTypeNameSide = 1;
    	int sameSide = 0;
    	
    	int result = 3;
    	
    	boolean sameName = this.getName().contentEquals(another.getName());
    	boolean sameType = this.getType().contentEquals(another.getType());
    	
    	if (!this.faceup || !another.faceup) {
    		result = eitherDown;
    	}
    	else if ((this.faceup && another.faceup) && (sameName && sameType)) {
    		result = sameTypeNameSide;
    	}
    	else {
    		result = sameSide;
    	}
    	return result;
    }
}