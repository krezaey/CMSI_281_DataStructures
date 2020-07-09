package intList;

/**
 * To implement as a LinkedList could simply
 * use inheritance...but should we?
 *
 */

public class IntStack extends IntLinkedList implements TrueIntStack {

	IntStack() {
		super();
	}
	
	public void push(int toPush) {
		super.prepend(toPush);
	}
	
	public void pop() {
		super.removeAt(0);
	}
	
	public int peek() {
		return super.getAt(0);
	}
	
	public static void main(String[] args) {
		IntStack stacky = new IntStack();
		stacky.push(1);
		stacky.push(2);
		stacky.push(3);
		//legally allowed to do this, since has access to super class methods
		//how can we prevent this?
		stacky.append(5);
		System.out.println(stacky.peek()); //3
		stacky.pop();
		System.out.println(stacky.peek()); //2
		stacky.pop();
		System.out.println(stacky.peek()); //1
		
		//instantiate through interface instead, allows us to restrict methods from superclass
		TrueIntStack trueStacky = new IntStack();
		trueStacky.push(1);
		trueStacky.push(2);
		trueStacky.push(3);
		//now this throws an exception
		//trueStacky.append(5);
		System.out.println(trueStacky.peek()); //3
		trueStacky.pop();
		System.out.println(trueStacky.peek()); //2
		trueStacky.pop();
		System.out.println(trueStacky.peek()); //1
	}
}
