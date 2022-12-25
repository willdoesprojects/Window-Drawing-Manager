//TODO: Complete java docs and code in missing spots.
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 *  A list of squares within a single window.
 *  
 *  <p>Adapterion of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated Fall
 *  2022 by K. Raven Russell.</p>
 */
public class SquareList {
	//You'll need some instance variables probably...
	//< YOUR_CODE_HERE >
	/**
	 * Declaring our instance variable list of the object "Square"
	 * in our ThreeTenLinkedList class.
	 */
	public ThreeTenLinkedList<Square> list;
	/**
	 *  Initialize an empty list of squares.
	 */
	public SquareList() {
		//Any initialization code you need.
		
		//O(1)
		
		//< YOUR_CODE_HERE >
		
		list = new ThreeTenLinkedList<Square>();
	}
	/**
	 * Calls the get method for head from the ThreeTenLinkedList class.
	 * @return the head of the linked list.
	 */
	public Node<Square> getHead() {
		//Returns the head of the list of squares.
		
		//O(1)
		
		//We will use this method to test your
		//linked list implementaiton of this
		//class, so whether or not you are using
		//the generic linked list class or bare
		//nodes, you must still be able to return
		//the appropriate head of the list.
		
		//< YOUR_CODE_HERE >
		
		return list.getHead(); //dummy return, replace this!
	}
	/**
	 * Calls the get method for tail from the ThreeTenLinkedList class.
	 * @return the tail of the linked list.
	 */
	public Node<Square> getTail() {
		//Returns the tail of the list of squares.
		
		//O(1)
		
		//We will use this method to test your
		//linked list implementaiton of this
		//class, so whether or not you are using
		//the generic linked list class or bare
		//nodes, you must still be able to return
		//the appropriate tail of the list.
		
		//< YOUR_CODE_HERE >
		
		return list.getTail(); //dummy return, replace this!
	}
	/**
	 * Calls the get method for the amount of squares from the ThreeTenLinkedList class.
	 * @return the size of the linked list
	 */
	public int numSquares() {
		//Gets the number of squares in the list.
		
		//O(1)
		
		//< YOUR_CODE_HERE >
		
		return list.getSize();
	}
	/**
	 * Calls the add method from ThreeTenLinkedList.
	 * @param sq the list that is being added to
	 */
	public void add(Square sq) {
		//Add a square to the list. Newly added squares
		//should be at the back end of the list.
		
		//O(1)
		
		//throw IllegalArgumentException for invalid sqares
		
		//< YOUR_CODE_HERE >
		
		if (sq == null) {
			throw new IllegalArgumentException();
		}
		
		list.add(sq);
	}
	/**
	 * This sees if the x and y input is within the square coordinate plane.
	 * @param x the x position
	 * @param y the y position
	 * @return true if x and y coordinate is within the square plane, false if not
	 */
	public boolean handleClick (int x, int y) {
		//Deletes all squares from the list that contain the 
		//position (x,y). Returns true if any squares get
		//deleted and returns false otherwise.
		
		//Returns true if any squares were deleted.
		
		//O(n) where n is the size of the list of squares
		
		//< YOUR_CODE_HERE >
		
		Node<Square> current = list.getHead();
		while (current != null) {
			
			if (current.data.contains(x,y) == true) {
				list.delete(current);
				return true;
			}
			
			current = current.next;
		}
		
		
		return false;
	}

	/**
	 *  Gets an iterator for the list of squares.
	 *  Squares are returned in the order added.
	 *  
	 *  @return the iterator requested
	 */
	public Iterator<Square> elements() {
		//Note that this method uses your linked list!
		//so if the iterator doesn't work, that's on you...
		
		return new Iterator<>() {
			/**
			 *  The current node pointed to by the
			 *  iterator (containing the next value
			 *  to be returned).
			 */
			private Node<Square> current = getHead();
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Square next() {
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				Square ret = current.data;
				current = current.next;
				return ret;
			}
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {
				return (current != null);
			}
		};
	}
	
	/**
	 * Sort method that sorts the list by comparing id(s) and sorts to an ascending order,
	 * creates a new comparator object and is overridden by using id(s) as a 
	 * comparison.
	 */
	public void sortCreation() {
		//Sorts the squares in the window by their creation time
		//(lower ids were created first). This should use your
		//ThreeTenLinkedList.sort() method you write in Part 5,
		//so don't do this until you have (a) read part 5,
		//(b) looked at the example in WindowStack, and (c) are 
		//sure you understand comparators.
		
		//O(n^2)
		
		//< YOUR_CODE_HERE >
		
		Comparator<Square> comp = new Comparator<>() {

			@Override
			public int compare(Square i, Square j) {
				System.out.println(i.id() + "" + j.id());
				// TODO Auto-generated method stub
				return i.id() - j.id();
			}
		};
		ThreeTenLinkedList.NodePair<Square> pair = new ThreeTenLinkedList.NodePair<Square>(list.getHead(), list.getTail());
		ThreeTenLinkedList.NodePair<Square> sort = ThreeTenLinkedList.sort(pair, comp);
		
		list.setHead(sort.head);
		list.setTail(sort.tail);
	
	}
	/**
	 * Sort method that sorts the list by comparing the UpperLeftX position and sorts to an ascending order,
	 * creates a new comparator object and is overridden by using UpperLeftX(s) as a 
	 * comparison. If both upperLeftX(s) are equal, they compare upperLeftY(s).
	 */
	public void sortLoc() {
		//Sorts the squares in the window by their location
		//in the window. Same rules as sorting the windows
		//in WindowStack. This should use your
		//ThreeTenLinkedList.sort() method you write in Part 5,
		//so don't do this until you have (a) read part 5,
		//(b) looked at the example in WindowStack, and (c) are 
		//sure you understand comparators
		
		//O(n^2)
		
		//< YOUR_CODE_HERE >
		
		Comparator<Square> comp = new Comparator<>() {
			@Override
			public int compare(Square i, Square j) {
				
				if (i.getUpperLeftX() == j.getUpperLeftX()) {
					return i.getUpperLeftY() - j.getUpperLeftY();
				}
				
				else {
					return i.getUpperLeftX() - j.getUpperLeftX();
				}
				
			}
		};
		
		
		ThreeTenLinkedList.NodePair<Square> pair = new ThreeTenLinkedList.NodePair<Square>(list.getHead(), list.getTail());
		ThreeTenLinkedList.NodePair<Square> sort = ThreeTenLinkedList.sort(pair, comp);
		
		list.setHead(sort.head);
		list.setTail(sort.tail);
		
		
	}
	
	
}
