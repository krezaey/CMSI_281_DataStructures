/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Forneymonagerie.java
 *  Author        :  Keziah Camille Rezaey
 *  Due Date      :  2019-09-26
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package forneymonagerie;

public class Forneymonagerie implements ForneymonagerieInterface {

    // Private Classes
    // ----------------------------------------------------------
    private class ForneymonType {
        String type;
        int count;
        
        ForneymonType (String t, int c) {
            type = t;
            count = c;
        }
    }
    
    
    // Fields
    // ----------------------------------------------------------
    private ForneymonType[] collection;
    private int size;
    private int typeSize;
    private static final int START_SIZE = 16;
    
    
    // Constructor
    // ----------------------------------------------------------
    Forneymonagerie () {
        this.collection = new ForneymonType[START_SIZE];
        this.size = 0;
        this.typeSize = 0;
    }
    
    
    // Methods
    // ----------------------------------------------------------
    
    /**
     * Indicates if the collection is empty.
     * @return boolean 
     */
    
    public boolean empty() {
        return this.size == 0;
    }
    
    /**
     * Returns the size of the collection.
     * @return int size
     */
    
    public int size() {
        return this.size;
    }
    
    /**
     * Returns the number of types in the collection.
     * @return int typeSize
     */
    
    public int typeSize() {
        return this.typeSize;
    }
    
    /**
     * Adds the ForneymonType object to the collections array and sorts the array so that is ordered by rarity
     * every time something is added.
     * @param toAdd String to add to the collection
     * @returns boolean true / false whether a new type has been added to the collection
     */
    
    public boolean collect(String toAdd) {
    	int index = 0;
    	boolean result = newTypeAdded(toAdd);
    	
    	sortCollection();
    	if (newTypeAdded(toAdd)) {
    		this.collection[this.typeSize] = new ForneymonType(toAdd, 1);
    		this.typeSize++;
    		this.size++;
    	} else {
    		index = stringIndexCheck(toAdd);
    		this.size++;
    		this.collection[index].count++;
    	}
    	
    	if (this.typeSize > 1) {
    		sortCollection();
    	}
    	
    	return result;
    }
    
    /**
     * Releases a quantity of 1 for the indicated type to remove.
     * @param toRemove String type to remove
     * @return boolean true / false depending on if 1 item of indicated type was removed in this manner
     */
    
    public boolean release (String toRemove) {
        if (contains(toRemove)) {
        	int index = stringIndexCheck(toRemove);
        	this.collection[index].count--;
        	this.size--;
        	if (this.collection[index].count == 0) { this.typeSize--; }
        	sortCollection();
        	return true;
        }
        return false;
    }
    
    /**
     * Releases the entire quantity of the indicated type.
     * @param toNuke String type to remove entirely
     */
    
    public void releaseType (String toNuke) {
        if(contains(toNuke)) {
        	int index = stringIndexCheck(toNuke);
        	this.size = this.size - this.collection[index].count;
        	remove(toNuke);
        	this.typeSize--;
        }
        sortCollection();
    }
    
    /**
     * Returns the count for the indicated type. 
     * @param toCount String type to check for
     * @return int Count of quantity for given type
     */
    
    public int countType (String toCount) {
        int index = stringIndexCheck(toCount);
        
        if (!contains(toCount)) {
        	return 0;
        }
        return this.collection[index].count;
    }
    
    /**
     * Returns whether indicated type is contained in the collection or not.
     * @param toCheck String type to check for 
     * @return boolean true / false depending on type is in the collection
     */
    
    public boolean contains (String toCheck) {
        for (int i = 0; i < this.typeSize; i++) {
        	if (sameStringContent(toCheck, this.collection[i].type)) {
        		return true;
        	}
        }
        return false;
    }
    
    /**
     * Iterates through individual ForneymonType objects, taking into account overall size instead of typeSize.
     * @param n int indicating the nth ForneymonType object in the collection we are trying to reach
     * @return String type of the indicated nth for the collection
     */
    
    public String nth (int n) {
    	int nCount = -1;
    	if (n >= 0 || n < size) {
    		for (int i = 0; i < this.typeSize; i++) {
        		nCount += this.collection[i].count;
        		if (nCount >= n) {
        			return this.collection[i].type;
        		}
    		}
    	}
    	throw new IllegalArgumentException();
    }
    
    /**
     * Returns the Forneymon type (String representation) of the most recently modified 
     * ForneymonType at the given rarity.
     * @param count int of desired rarity
     * @return String type of most recent ForneymonType at indicated rarity, null if none exists at that point
     */
    
    public String mostRecentAtRarity (int count) {   	
        for (int i = this.typeSize-1; i >= 0; i--) {
        	if (this.collection[i].count == count) {
        		return this.collection[i].type;
        	}
        }
        return null;
    }
    
    /**
     * Trades the size, typeSize, and contents of another Forneymonagerie object with this instance.
     * @param other Forneymonagerie object to trade
     */
    
    public void trade (Forneymonagerie other) {
        Forneymonagerie temp = new Forneymonagerie(); 
        
        temp.size = other.size;
        temp.typeSize = other.typeSize;
        temp.collection = other.collection;   
        other.size = this.size;
        other.typeSize = this.typeSize;
        other.collection = this.collection;
        this.size = temp.size;
        this.typeSize = temp.typeSize;
        this.collection = temp.collection;
    }
    
    /**
     * Creates a new Forneymonagerie object with the same size, typeSize, and contents of this instance.
     */
    
    @Override
    public Forneymonagerie clone() {
        Forneymonagerie clone = new Forneymonagerie();
        
        clone.size = this.size;
        clone.typeSize = this.typeSize;
        
        for (int i = 0; i < clone.typeSize; i++) {
        	clone.collection[i] = new ForneymonType(this.collection[i].type, this.collection[i].count);
        }      
        return clone;
    }
    
    /**
     * Returns string representation of the Forneymonagerie collection.
     * @return String [type: count]
     */
    
    @Override
    public String toString () {
        String[] result = new String[this.typeSize];
        for (int i = 0; i < this.typeSize; i++) {
            result[i] = "\"" + this.collection[i].type + "\": " + this.collection[i].count;
        }
        return "[ " + String.join(", ", result) + " ]";
    }
    
    
    // Static methods
    // ----------------------------------------------------------
    
    /**
     * Returns a *new* Forneymonagerie object consisting of all Forneymon
     * from y1 that do NOT appear in y2.
     * @param y1 Forneymonagerie
     * @param y2 Forneymonagerie
     * @return Forneymonagerie object 
     */
    
    public static Forneymonagerie diffMon (Forneymonagerie y1, Forneymonagerie y2) {
        Forneymonagerie fm = new Forneymonagerie(); 
        int index;
        
        for (int i = 0; i < y1.typeSize; i++) {
        	if (!y2.contains(y1.collection[i].type)) {
        		fm.collectMultiple(y1.collection[i].type, y1.collection[i].count);
        	} 
        	else {
        		index = y2.stringIndexCheck(y1.collection[i].type);
        		fm.collectMultiple(y1.collection[i].type, y1.collection[i].count - y2.collection[index].count);
        	}
        }
        return fm;
    }
    
    /**
     * Returns true if y1 and y2 contain the exact same ForneymonTypes 
     * and number of Forneymon in each type, though in any modification order.
     * @param y1 Forneymonagerie
     * @param y2 Forneymonagerie
     * @return boolean true / false if the two objects are the same collection
     */
    
    public static boolean sameCollection (Forneymonagerie y1, Forneymonagerie y2) {
    	int count = 0;
    	// >> [DM] Make sure tabs are replaced with spaces.
        if (y1.size == y2.size) {
        	for (int i = 0; i < y1.typeSize; i++) {
        		for (int j = 0; j < y2.typeSize; j++) {
        			int firstIndex = y1.stringIndexCheck(y1.collection[i].type);
        			int secondIndex = y2.stringIndexCheck(y2.collection[j].type);
        			if (y1.collection[firstIndex].count == y2.collection[secondIndex].count) {
        				count++;
        			}
        		}
        	}
        }
        // >> [DM] Why not just do `return count == y1.typeSize`?
//        if (count == y1.typeSize) { return true; }
//        return false;
        return count == y1.typeSize;
    }
    
    
    // Private helper methods
    // ----------------------------------------------------------
        
    /**
     * Checks if the strings are the same content wise.
     * @param s String
     * @param t String
     * @return boolean true / false depending on if they have the same contents
     */
    
    private boolean sameStringContent(String s, String t) {
    	return s.contentEquals(t);
    }
    
    /**
     * Checks if new type that is not already in the collection has been added.
     * @param typeAdd, String to check
     * @return boolean true or false if a new type has been detected
     */
    
    private boolean newTypeAdded(String typeAdd) {
    	int count = 0;
    	
    	for (int i = 0; i < this.typeSize; i++) {
    		if (!sameStringContent(typeAdd, this.collection[i].type)) {
    			count++;
    		} 
    	}
    	if (count == this.typeSize) { return true; }
    	return false;
    }
    
    /**
     * Checks if there is a type in the array that has the same content and returns the index.
     * @param type String to check
     * @return index int of the inquired String
     */
    
    private int stringIndexCheck(String typeAdd) {
    	int index = -1;
    	
    	if (!newTypeAdded(typeAdd)) {
    		for (int i = 0; i < this.typeSize; i++) {
        		if (sameStringContent(typeAdd, this.collection[i].type)) {
        			index = i;
        		}
        	}
        }
        // >> [DM] If typeAdd is not found, index will still be 0 and 0 is a valid index.
        // Instantiate index as -1 or look into the find function for arrays.
    	return index;
    }  
    
    /** 
     * Sorts the collection array from least rare to most rare based on ForneymonType count
     */
    
    private void sortCollection() {
    	ForneymonType temp;
    	
    	for (int i = 0; i < this.typeSize; i++) {
    		for (int j = i; j > 0; j--) {
    			
    			if(this.collection[j].count > this.collection[j-1].count) {
    				temp = this.collection[j];
    				this.collection[j] = this.collection[j-1];
    				this.collection[j-1] = temp;
    			}   			
    		}
    	}
    }
    
    /**
     * Shifts ForneymonTypes at the right of the given index
     * @param index int
     */
    
    private void shiftLeft(int index) {
    	for (int i = index; i < this.typeSize - 1; i++) {
    		this.collection[i] = this.collection[i+1];
    	}
    }
    
    /**
     * Removes a ForneymonType from the collections array
     * @param toRemove String to remove from the array
     */
    
    private void remove(String toRemove) {
    	int index = stringIndexCheck(toRemove);
    	shiftLeft(index);
    }
    
    /**
     * Adds multiple ForneymonType objects by the amount indicated
     * @param type String of what to add to the collection
     * @param toAdd int of how many times to add
     */
    
    private void collectMultiple(String type, int timesToAdd) {
    	for (int i = timesToAdd; i > 0; i--) {
    		collect(type);
    	}
    }   
}

// ===================================================
// >> [DM] Summary
// ---------------------------------------------------
// Correctness:         50 / 60
// Style:               15 / 15
// Total:               65 / 75
// ---------------------------------------------------
// Amazing style and clean coding practices. Besides
// a few minor improvements, great job!
// ===================================================
