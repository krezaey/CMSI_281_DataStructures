package collections_ex;

import java.util.*;

public class JavaCollectionsExample {
	
	public static void main(String[] args) {
		ArrayList <String> arr = new ArrayList<>();
		ArrayList <Integer> arrOfInts = new ArrayList<>();
		LinkedList <String> linky = new LinkedList<>();
		//no actual queue class in jcf, typing is of interface to restrict access LinkedList methods
		//but inherently implemented just by using LinkedList methods
		Queue <String> qq = new LinkedList<>();
		Deque <String> notDeck = new LinkedList<>();
		
		//List interface allows for flexibility for either LinkedList or ArrayLists 
		//to allow either for parameters or restrict methods 
		//that are only shared with both list implementations
		List <String> listed = new LinkedList();
		List <String> listedAlso = new ArrayList();
	}

}

