package intList;

public class IntArrayList implements IntListInterface {
	
	//Class constant
	private static final int START_SIZE = 8;
	
	//Fields
	private int[] items;
	private int size; //Might have more space reserved than size
	
	//Constructor
	IntArrayList() {
		this.size = 0;
		this.items = new int[START_SIZE];
	}
	
	/**
	 * Returns the int stored at the given index
	 * @param int index, the index required
	 * @return int, at that index
	 */
	
	public int getAt(int index) {
		if (index >= this.size || index < 0) {
			throw new IllegalArgumentException();
		}
		return this.items[index];
	}
	
	/**
	 * Adds the given int toAdd to the end of the IntArrayList
	 * @param toAdd, the int to append
	 */
	
	public void append(int toAdd) {
		checkAndGrow();
		this.items[this.size] = toAdd;
		this.size++;
	}
	
	/**
	 * Inserts the given element at the given index
	 * @param int toAdd element
	 * @param int index 
	 */
	
	public void insertAt(int toAdd, int index) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Removes the element at the given index
	 * maintain relative item ordering after
	 * @param int index
	 */
	
	public void removeAt(int index) {
	    shiftLeft(index);
	    size--;
	}
	
	/** 
	 * Expand the size of the items array when it is full and another item is being added
	 */
	
	private void checkAndGrow() {
		//Case: big enough to fit another item, so no need to grow
		if (this.size < this.items.length) { return; }
		//Case: max capacity and need to grow
		//make a new array double the capacity
		int[] newItems = new int[this.items.length * 2];
		//then, copy all the previous contents over 
		for (int i = 0; i < this.items.length; i++) {
			newItems[i] = this.items[i];
		}
		this.items = newItems;
	}
	
	private void shiftLeft(int index) {
		for (int i = index; i < size - 1; i++) {
			items[i]= items[i+1];
		}
	}
	
//	public static void main(String[] args) {
//		IntArrayList arr = new IntArrayList();
//		arr.append(1);
//		arr.append(2);
//		arr.append(3);
//		arr.append(4);
//		arr.append(5);
//		arr.removeAt(1);
//		System.out.println(arr.getAt(0));
//	}

}
