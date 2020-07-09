package intlist;

public class IntList {

    // Fields
    private int[] items;
    private int   size;
    private static final int START_SIZE = 8;
    
    /**
     * Initializes a new IntList with the given class constant
     * START_SIZE, which can be expanded through dynamic
     * allocation as need arises
     */
    IntList () {
        items = new int[START_SIZE];
        size  = 0;
    }

    /**
     * Returns the int at the specified index
     * @param The index of the IntList expected
     * @return The value at that index
     */
    public int getAt(int index) {
        indexValidityCheck(index);
        return items[index];
    }

    /**
     * Adds the given item toAdd to the end of the
     * IntList, expanding its capacity as needed
     * @param toAdd The int to add
     */
    public void append(int toAdd) {
        checkAndGrow();
        items[size] = toAdd;
        size++;
    }
    
    /**
     * Adds the given item toAdd to the start of the
     * IntList, expanding its capacity as needed
     * @param toAdd The int to add
     */
    public void prepend(int toAdd) {
    	size++;
    	shiftRight(0); 
    	items[0] = toAdd;  
    }

    /**
     * Adds the given item toAdd to the index specified
     * in the IntList, expanding its capacity as needed
     * @param toAdd The int to add
     * @param index The index at which to add it
     */
    public void insertAt (int toAdd, int index) {
    	indexValidityCheck(index);
        size++;
        shiftRight(index);   
        items[index] = toAdd;
    }
    
    /**
     * Removes all instances of the given int toRemove,
     * retaining the relative ordering of remaining
     * ints in the IntList.
     * @param toRemove The int value to purge
     */
    public void removeAll (int toRemove) {
        for (int i = 0; i < size; i++) {
        	if (items[i] == toRemove) {
        		removeAt(i);
        		i--;
        	}
        }
    }

    /**
     * Removes the value at the given index in the
     * IntList, retaining relative orderings of remaining
     * ints.
     * @param index The index to remove
     */
    public void removeAt(int index) {
        indexValidityCheck(index);
        shiftLeft(index);
        size--;
    }
    
    /**
     * Checks to make sure the requested index (for either
     * manipulation or access) is within the legal bounds
     * @param index The index being requested
     */
    private void indexValidityCheck (int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
    
    /**
     * Expands the size of the list whenever it is at
     * capacity
     */
    private void checkAndGrow () {
        // Case: big enough to fit another item, so no
        // need to grow
        if (size < items.length) {
            return;
        }
        
        // Case: we're at capacity and need to grow
        // Step 1: create new, bigger array; we'll
        // double the size of the old one
        int[] newItems = new int[items.length * 2];
        
        // Step 2: copy the items from the old array
        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }
        
        // Step 3: update IntList reference
        items = newItems;
    }
    
    /**
     * Shifts all elements to the right of the given
     * index one left
     */
    private void shiftLeft (int index) {
        for (int i = index; i < size-1; i++) {
            items[i] = items[i+1];
        }
    }
    
    /**
     * Shifts all elements to the left of the given
     * index one right
     */
    
    private void shiftRight (int index) {
    	for (int i = size; i > index; i--) {
    		items[i] = items[i-1];
    	}
    } 
}


