/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  LinkedForneymonagerie.java
 *  Author        :  Keziah Camille Rezaey
 *  Due Date      :  2019-10-21
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package forneymonagerie;

import java.util.NoSuchElementException;

public class LinkedForneymonegerie implements ForneymonagerieInterface {

    // Fields
    // -----------------------------------------------------------
    private ForneymonType head, tail;
    private int size, typeSize, modCount;
    
    
    // Constructor
    // -----------------------------------------------------------
    LinkedForneymonegerie () {
        head = null;
        tail = null;
        size = 0;
        typeSize = 0;
        modCount = 0;
    }
    
    // Methods
    // -----------------------------------------------------------
    
    /**
     * Indicates if the collection is empty.
     * @return boolean 
     */
    
    public boolean empty () {
        return size == 0;
    }
    
    /**
     * Returns the size of the collection.
     * @return int size
     */
    
    public int size () {
        return size;
    }
    
    /**
     * Returns the number of types in the collection.
     * @return int typeSize
     */
    
    public int typeSize () {
        return typeSize;
    }
    
    /**
     * Adds the ForneymonType object to the linked list and sorts the linked list so that is 
     * ordered by rarity every time something is added. Increases modCount.
     * @param toAdd String to add to the collection
     * @returns boolean true / false whether a new type has been added to the collection
     */
    
    public boolean collect (String toAdd) {
    	boolean result = newTypeAdded(toAdd);

    	modCount++;
    	if (result) {
    		size++;
    		typeSize++;
    		add(toAdd, 1);
    	}
    	else {
    		size++;
    		ForneymonType current = walkToNode(nodeIndexCheck(toAdd));
    		current.count++;
    	}
    	reorder();
        return result;
    }
    
    /**
     * Releases a quantity of 1 for the indicated type to remove. Increases modCount. 
     * @param toRemove String type to remove
     * @return boolean true / false depending on if 1 item of indicated type was removed in this manner
     */
    
    public boolean release (String toRemove) {	
    	if (contains(toRemove)) {
    		modCount++;
    		int index = nodeIndexCheck(toRemove);
    		walkToNode(index).count--;
    		if (walkToNode(index).count == 0) { remove(index); }
    		reorder();
    		size--;
    		return true;
    	}
		return false;
    }
    
    /**
     * Releases the entire quantity of the indicated type. Increases modCount.
     * @param toNuke String type to remove entirely
     */
    
    public void releaseType (String toNuke) {
    	if (contains(toNuke)) {
    		modCount++;
    		int index = nodeIndexCheck(toNuke);
    		size -= walkToNode(index).count;
    		remove(index);
    		reorder();
    	}
    }
    
    /**
     * Returns the count for the indicated type. 
     * @param toCount String type to check for
     * @return int Count of quantity for given type
     */
    
    public int countType (String toCount) {
    	   int index = nodeIndexCheck(toCount);
           
           if (!contains(toCount)) {
           	return 0;
           }   
           return walkToNode(index).count;
    }
    
    /**
     * Returns whether indicated type is contained in the collection or not.
     * @param toCheck String type to check for 
     * @return boolean true / false depending on type is in the collection
     */
    
    public boolean contains (String toCheck) {
    	for (ForneymonType n = head; n != null; n = n.next) {
    		if (sameStringContent(n.type, toCheck)) {
    			return true;
    		}
        }
    	return false;
    }
    
    /**
     * Returns the Forneymon type (String representation) of the most recently modified 
     * ForneymonType at the given rarity.
     * @param count int of desired rarity
     * @return String type of most recent ForneymonType at indicated rarity, null if none exists at that point
     */
    
    public String mostRecentAtRarity (int count) {
    	for (ForneymonType n = tail; n != null; n = n.prev) {
    		if (n.count == count) {
    			return n.type;
    		}
    	}
    	return null;
    }
    
    /**
     * Creates a new LinkedForneymonegerie object with the same size, typeSize, and contents of this instance.
     */
    
    public LinkedForneymonegerie clone () {
    	LinkedForneymonegerie clone = new LinkedForneymonegerie();
        
        clone.size = size;
        clone.typeSize = typeSize;
        clone.modCount = modCount;
        
        for (ForneymonType n = head; n != null; n = n.next) {
        	clone.add(n.type, n.count);
        }       
        return clone;
    }
    
    /**
     * Trades the size, typeSize, and contents of another LinkedForneymonegerie object with this instance.
     * Increases modCount of both LinkedForneymonegerie objects.
     * @param other LinkedForneymonegerie object to trade
     */
    
    public void trade (LinkedForneymonegerie other) {
        LinkedForneymonegerie temp = new LinkedForneymonegerie();
         
        temp.head = head;
        temp.tail = tail;
        temp.size = size;
        temp.typeSize = typeSize;
        temp.modCount = modCount;
        
        head = other.head;
        tail = other.tail;
        size = other.size;
        typeSize = other.typeSize;
        modCount = other.modCount;
        
        other.head = temp.head;
        other.tail = temp.tail;
        other.size = temp.size;
        other.typeSize = temp.typeSize;
        other.modCount = temp.modCount;
        
        modCount++;
        other.modCount++;
    }
    
    /**
     * Iterates through individual ForneymonType objects, taking into account overall size instead of
     * individual ForneymonType nodes.
     * @param n int indicating the nth ForneymonType object in the collection we are trying to reach
     * @return String type of the indicated nth for the collection
     * @throws IllegalArgumentException if n is invalid
     */
    
    public String nth (int n) { 	
    	int nCount = -1;
    	if (n >= 0 || n < size) {
    		for (ForneymonType m = head; m != null; m = m.next) {
    			nCount += m.count;
    			if (nCount >= n) {
    				return m.type;
    			}
    		}
    	}
    	throw new IllegalArgumentException();
    }
    
    /**
	 * Returns new iterator starting at the head
	 */
    
    public LinkedForneymonegerie.Iterator getIterator () {
        return new Iterator(this);
    }
    
    /**
     * Returns string representation of the LinkedForneymonegerie collection.
     * @return String [type: count]
     */
    
    @Override
    public String toString () {
        String[] result = new String[typeSize];
        int i = 0;
        for (ForneymonType n = head; n != null; n = n.next) {
            result[i] = "\"" + n.type + "\": " + n.count;
            i++;
        }
        return "[ " + String.join(", ", result) + " ]";
    }
    
    
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------
    
    /**
     * Returns a *new* LinkedForneymonegerie object consisting of all Forneymon
     * from y1 that do NOT appear in y2.
     * @param y1 LinkedForneymonegerie
     * @param y2 LinkedForneymonegerie
     * @return LinkedForneymonegerie object 
     */
    
    public static LinkedForneymonegerie diffMon (LinkedForneymonegerie y1, LinkedForneymonegerie y2) {
    	LinkedForneymonegerie flm = new LinkedForneymonegerie();

    	for (ForneymonType n = y1.head; n != null; n = n.next) {
    		if (!y2.contains(n.type)) {
    			flm.collectMultiple(n.type, n.count);
    		}
    		else {
    			ForneymonType other = y2.walkToNode(y2.nodeIndexCheck(n.type));
    			if (other.count < n.count) {
    				flm.collectMultiple(n.type, n.count - other.count);
    			}
    		}
    	}
    	return flm;
    }
    
    /**
     * Returns true if y1 and y2 contain the exact same ForneymonTypes 
     * and number of Forneymon in each type, though in any modification order.
     * @param y1 LinkedForneymonegerie
     * @param y2 LinkedForneymonegerie
     * @return boolean true / false if the two objects are the same collection
     */
    
    public static boolean sameCollection (LinkedForneymonegerie y1, LinkedForneymonegerie y2) {
    	int count = 0;
    	if (y1.size != y2.size || y1.typeSize != y2.typeSize) { return false; }
    		for (ForneymonType n = y1.head; n != null; n = n.next) {
    			for (ForneymonType m = y2.head; m != null; m = m.next) {
    				if (n.count == m.count && y1.sameStringContent(m.type, n.type)) {
    					count++;
    				}
    			}
    		}
    
    	return count == y1.typeSize;
    }
    
    
    // Private helper methods
    // -----------------------------------------------------------

    /**
     * Checks if the strings are the same content wise.
     * @param s, String
     * @param t, String
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
    	
    	for (ForneymonType n = head; n != null; n = n.next) {
    		if (!sameStringContent(typeAdd, n.type)) {
    			count++;
    		} 
    	}
    	if (count == typeSize) { return true; }
    	return false;
    }
    
    /**
     * Checks if there is a node in the linked list that has the same type and returns the index.
     * @param type, String to check
     * @return index int of the inquired type
     */
    
    private int nodeIndexCheck(String typeAdd) {
    	int index = -1;
    	int count = 0;
    	
    	if (!newTypeAdded(typeAdd) && contains(typeAdd)) {
    		for (ForneymonType n = head; n != null; n = n.next) {
    			count++;
        		if (sameStringContent(typeAdd, n.type)) {
        			index = count-1;
        		}
        	}
        }
    	return index;
    }
    
    /**
     * Adds item to the end of the linked list, allows to specify how many items are added.
     * This is only used when a new type is added completely.
     * @param toAdd, String
     * @param addTimes, int
     */
    
    private void add(String toAdd, int addTimes) {
    	ForneymonType newItem = new ForneymonType(toAdd, addTimes);
    	if (head == null) {
    		head = newItem;
    		return;
    	}
    	ForneymonType current = head;
		while (current.next != null) {
			current = current.next;
		}
		current.next = newItem;
    }
    
    /**
     * Adds multiple ForneymonType objects by the amount indicated.
     * @param type String of what to add to the collection
     * @param toAdd int of how many times to add
     */
    
    private void collectMultiple(String type, int timesToAdd) {
    	for (int i = timesToAdd; i > 0; i--) {
    		collect(type);
    	}
    } 
    
    /**
     * Resets the head and tail references.
     */
    
    private void resetReferences() {
    	if (typeSize > 1) {
    		head = walkToNode(0);
        	tail = walkToNode(typeSize-1);
    	}
    	else if (typeSize == 0 || size == 0) {
    		head = null;
    		tail = null;
    	}
    	else {
    		tail = head;
    		head = tail;
    	}
    }
        
    /**
     * Reorders the LinkedList according to the collection count standards.
     */
    
    private void reorder() { 
    	String tempType;
    	int tempCount;
    	
    	for (ForneymonType current = head; current != null; current = current.next) {
    		for (ForneymonType next = current.next; next != null; next = next.next) {
    			if (current.count < next.count) {
    				tempCount = current.count;
        			tempType = current.type;
        			current.count = next.count;
        			current.type = next.type;
        			next.count = tempCount;
        			next.type = tempType;
    			}
    		}
    	}
    	resetReferences();
    }
    
    /**
     * Walks to the ForneymonType node of the linked list and returns the node at the index.
     * @param index, int
     * @return ForneymonType
     */
    
    private ForneymonType walkToNode(int index) {
    	ForneymonType current = head;
        while (index > 0 && current.next != null) {
			current = current.next;
			index--;
		}
        return current;
    }
    
    /**
     * Removes a ForneymonType from the linked list.
     * @param index of ForneymonType to remove
     */
    
    private void remove(int index) {
    	if (index >= typeSize || index < 0) {
			return;
		}
		
		ForneymonType toDelete = walkToNode(index);

		if (head == toDelete) {
			head = toDelete.next; 
		}	
		if (toDelete.next != null) {
			toDelete.next.prev = toDelete.prev;
		}	
		if (toDelete.prev != null) {
			toDelete.prev.next = toDelete.next;
		}
		if (tail == toDelete) {
			tail = toDelete.prev;
		}
		toDelete = null;
		typeSize--;
    }
       
    // Inner Classes
    // -----------------------------------------------------------
    
    public class Iterator implements LinkedForneymonegerieIteratorInterface {
        LinkedForneymonegerie owner;
        ForneymonType current;
        int itModCount;
        int nodeCount;
        
        Iterator (LinkedForneymonegerie y) {
            owner = y;
            current = y.head;
            itModCount = modCount;
            nodeCount = current.count - 1;
        }
        
        /**
         * Indicates whether the next ForneymonType node has a next reference.
         * @return boolean true / false
         */
       
        public boolean hasNext () {
            return ((nodeCount >= 0) || (current.next != null)) && isValid();
        }
        
        /**
         * Indicates whether the next ForneymonType node has a previous reference.
         * @return boolean true / false
         */
        
        public boolean hasPrev () {
            return ((nodeCount < current.count - 1) || (current.prev != null)) && isValid();
        }
        
        /**
         * Checks whether the iterator is valid; that is, if the modCount is equal to that of 
         * the linked list collection.
         * @return boolean true / false
         */
        
        public boolean isValid () {
            return itModCount == owner.modCount;
        }
        
        /**
         * Returns the type of the current ForneymonType node.
         * @return String
         */
        
        public String getType () {
        	if (!isValid()) { throw new IllegalStateException(); } 
            return current.type;
        }
        
        /**
         * Goes to the next ForneymonType node in the linked list, if possible.
         */

        public void next () {
        	if (!isValid()) { throw new IllegalStateException(); }
	        if (hasNext()) {
	        	if (nodeCount == 0) { 
	        		current = current.next; 
	        		nodeCount = current.count - 1;
	        	}
	            else { 
	            	nodeCount--; 
	            }   
	        }
	        else { throw new NoSuchElementException(); }
        }
        
        /**
         * Goes to the previous ForneymonType node in the linked list, if possible.
         */
        
        public void prev () {
        	if (!isValid()) { throw new IllegalStateException();}
            if (hasPrev()) {
            	if (nodeCount != current.count - 1) { nodeCount++; }
                else { 
                	current = current.prev;
                	nodeCount = 0;
                }
            }
            else { throw new NoSuchElementException(); }
        }
        
        /**
         * Replaces *all* Forneymon of the ForneymonType that this Iterator currently points to with 
         * that given in the parameter toReplaceWith.
         * @param toReplaceWith, String
         */
        
        public void replaceAll (String toReplaceWith) {
        	if (!isValid()) { throw new IllegalStateException();}
        	itModCount++;
        	modCount++;
        	if (contains(toReplaceWith)) { 
        		walkToNode(nodeIndexCheck(toReplaceWith)).count += current.count; 
        	}
        	else {
        		ForneymonType tempNode = walkToNode(nodeIndexCheck(current.type));
        		tempNode.type = toReplaceWith;
        	}
        	reorder();
        }  
    }
    
    private class ForneymonType {
        ForneymonType next, prev;
        String type;
        int count;
        
        ForneymonType (String t, int c) {
            type = t;
            count = c;
        }
    }
}