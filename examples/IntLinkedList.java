package intList;

public class IntLinkedList implements IntListInterface {
	
	//Fields
	private Node head;
	private int size;
	
	//Constructor
	IntLinkedList() {
		head = null;
		size = 0;
	}
	
	//Inner Classes
	private class Node {	
		public Node next;
		public int data;
		
		Node(int d) {
			this.data = d;
			this.next = null;
		}
	}
	
	public class Iterator {
		
		private Node currentNode;
		
		Iterator(IntLinkedList host) {
			this.currentNode = host.head;
		}
		
		/**
		 * Checks whether or not there is another Node
		 * after this one
		 * @return That boolean
		 */
		
		public boolean hasNext() {
			return this.currentNode != null && this.currentNode.next != null;
		}
		
		/**
		 * Advances the iterator to the next item
		 */
		
		public void next() {
			if (this.hasNext()) {
				this.currentNode = this.currentNode.next;
			}
		}
		
		/**
		 * Returns the int of the Node currently pointed at
		 * @return the int data
		 */
		
		public int getCurrentInt() {
			return this.currentNode.data;		
		}
		
	}
	
	//Methods
	
	/**
	 * Add the given value toAdd to the end of the 
	 * LinkedList
	 * @param int toAdd to append
	 */

	public void append(int toAdd) {
		Node newNode = new Node(toAdd);
		this.size++;
		
		// Case 1: Node being added is the only one
		if (this.head == null) {
			this.head = newNode;
			return;
		}
		
		//Case 2: Node already in LinkedList
		Node current = this.head; //this is like a for loop, equivalent of int i = 0, iterating to end of list
		while (current.next != null) {
			current = current.next;
		}
		current.next = newNode;

	}
	
	/**
	 * Returns the value located at the nth node
	 * indicated by the index
	 * @param index, index of the Node
	 * @return the value at that node
	 */

	public int getAt(int index) {
		if (index >= size || index < 0) {
			throw new IllegalArgumentException();
		}
		
		//now, walk list until index is found
		Node current = head;
		while (index > 0) {
			current = current.next;
			index--;
		}
		
		return current.data;
	}
	
	/**
	 * Add the int toAdd to the beginning of the LL
	 * @param toAdd Element to add
	 */
	
	public void prepend(int toAdd) {
		//Create a new node with toAdd
		//Update the next reference of the existing head
		//Update head reference
		
		Node currentHead = this.head;
		this.head = new Node(toAdd);	
		this.head.next = currentHead;
		this.size++;
	}
	
	public void insertAt(int toAdd, int index) {
		throw new UnsupportedOperationException();

	}
	
	/**
	 * Remove the value at the given index, repairing
	 * any necessary references
	 */

	public void removeAt(int index) {
		if (index >= size || index < 0) {
			throw new IllegalArgumentException();
		}
		
		// current starts at the beginning
		// prev will always point to prev of current
		Node current = head, 
				prev = null;
		
		while (index > 0) {
			prev = current;
			current = current.next;
			index--;
		}
		
		//At the end of this loop, current refers to 
		//Node we want to delete, and prev to Node before it
		
		if (prev == null) {
			this.head = current.next;
		} else {
			prev.next = current.next;
		}
		
		this.size--;
	}
	
	/**
	 * Returns new iterator starting at the head
	 */
	
	IntLinkedList.Iterator getIterator() {
		return new Iterator(this);
	}
	
	public static void main(String[] args) {
		IntLinkedList listy = new IntLinkedList();
		listy.append(1);
		listy.append(2);
		listy.append(3);
//		IntLinkedList.Iterator it = listy.getIterator(); relies on constructor of LinkedList, 
//		thus get method inside LinkedList class
//		System.out.println(it.getCurrentInt());
//		it.next();
//		System.out.println(it.getCurrentInt());
//		it.next();
//		System.out.println(it.getCurrentInt());
		IntLinkedList.Iterator it = listy.getIterator();
		do {
			System.out.println(it.getCurrentInt());
			it.next();
		} while (it.hasNext());
		
		//need to fix hasNext
		
		IntListInterface listboy = new IntLinkedList();
		listboy.append(1);
		listboy.append(3);
		//this next line will cause an error, since method is not outlined in the Interface
//		listboy.prepend(4);
		
		//Why is this desirable? Example: sample method that returns the sum of all the ints in the given list
//		public static int sum (IntLinkedListInterface arr) {
//			return 0;
//		}
		//because the parameter is of type object IntLinkedListInterface we can put an arraylist or linked list
		//as parameter
		
//		IntArrayList arr = new IntArrayList();
//		sum(arr);
//		sum(listboy);
		//both work!
	}

}
