//TODO: Complete java docs and code in missing spots.
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 *  A stack of windows within the window.
 *  
 *  <p>Adapterion of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated Fall
 *  2022 by K. Raven Russell.</p>
 */
public class WindowStack {
	/**
	 * Defines ThreeTenLinkedList with the object window.
	 */
	//You'll need some instance variables probably...
	//< YOUR_CODE_HERE >
	ThreeTenLinkedList<Window> list;
	/**
	 * Constructor that initializes the linked list.
	 */
	public WindowStack() {
		//Any initialization code you need.
		
		//O(1)
		
		//< YOUR_CODE_HERE >
		
		list = new ThreeTenLinkedList<Window>();
	}
	/**
	 * Calls the get method for head from the ThreeTenLinkedList class.
	 * @return the head of the linked list.
	 */
	public Node<Window> getHead() {
		//Returns the head (top) of the stack of windows.
		
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
	public Node<Window> getTail() {
		//Returns the tail (bottom) of the stack of windows.
		
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
	 * Calls the get method for the amount of windows from the ThreeTenLinkedList class.
	 * @return the size of the linked list
	 */
	public int numWindows() {
		//Gets the number of windows in the stack.
		
		//O(1)
		
		return list.getSize();
	}
	/**
	 * Calls the push method from ThreeTenLinkedList,
	 * everytime a window is pushed, it is selected and
	 * the previous is deselected.
	 * @param r the list that is being added to
	 */
	public void add(Window r) {
		//Add a window at the top of the stack.
		
		//O(1)
		
		//throw IllegalArgumentException for invalid windows
		
		//Note: the "top" of the stack should
		//be the head of your linked list.
		
		//< YOUR_CODE_HERE >
		
		list.push(r);
		if (list.getHead().next == null) {
			list.getHead().data.setSelected(true);
		}
		
		else {
			list.getHead().next.data.setSelected(false);
			list.getHead().data.setSelected(true);
		}
	}
	/**
	 * HandleClick method that determines what to do whether if not leftClick
	 * is true of false. If leftClick is true, then it determines whether or not
	 * if it is selected. If it is selected, than it starts drawing squares within the window.
	 * If not then it selects the window. If leftClick is false and if the window is already selected, then
	 * it deletes the window.
	 *  
	 * @param x the x position
	 * @param y the y position
	 * @param leftClick true if left click was activated, false if not
	 * @return true if the window was handle, false if not
	 */
	public boolean handleClick (int x, int y, boolean leftClick) {
		Node<Window> current = list.getHead();
		//The mouse has been clicked at position (x,y).
		//Left clicks are move windows to the top of the
		//stack or pass the click on to the window at the
		//top. Right clicks remove windows.
		
		//Returns true if the click was handled, false
		//if no window was found.
		
		//O(n) where n is the number of windows in the stack
		
		
		//Details:
		
		//Find the top-most window on the stack (if any)
		//that contains the given coordinate.
		
		
		//For a left click:
		
		//If the window is not at the top of the stack,
		//move it to the top of the stack without
		//disturbing the order of the other windows.
		//Mark this window as the "selected" window (and
		//ensure the previous selected window is no longer
		//selected).
		
		//If the window is at the top of the stack already,
		//ask the window to handle a click-event (using the
		//Window's handleClick() method).
		
		//If none of the windows on the stack were clicked
		//on, just return.
		
		
		//For a right click:
		
		//Remove the window from the stack completely. The
		//window at the top of the stack should be the 
		//selected window.
		
		
		//Hint #1: This would be a great time to use helper
		//methods! If you just write one giant method...
		//it'll be much harder to debug...
		
		//Hint #2: Make sure to use the methods you wrote
		//in the Window class. Don't write those again!

		
		//< YOUR_CODE_HERE >
		if (leftClick == true) {
			
			if (current == null) {
				return false;
			}
			if(current.data.contains(x,y) == true && current.data.getSelected() == true) {
				current.data.handleClick(x, y);
				return true;
			}
			
			else {
				while(current != null) {
					if(current.data.contains(x,y) == true) {
						list.getHead().data.setSelected(false);
						list.push(current);
						list.getHead().data.setSelected(true);
						return true;
					}
					current = current.next;
				}
			}
			return false;
		}
		
		else {
			if (current == null) {
				return false;
			}
			
			if (list.getHead().data.contains(x,y) == true && list.getHead().data.getSelected() == true) {
				
				list.delete(list.getHead());
				if (list.getHead() != null) {
					list.getHead().data.setSelected(true);
				}
				return true;
			}
			
			else {
				return false;
			}
		}


		
		//dummy return, replace this!
	}

	/**
	 *  Gets an iterator for the stack of windows.
	 *  Windows are returned from bottom to top.
	 *  
	 *  @return the iterator requested
	 */
	public Iterator<Window> windows() {
		//Note that this method uses your linked list!
		//so if the iterator doesn't work, that's on you...
		
		return new Iterator<>() {
			/**
			 *  The current node pointed to by the
			 *  iterator (containing the next value
			 *  to be returned).
			 */
			private Node<Window> current = getTail();
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Window next() {
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				Window ret = current.data;
				current = current.prev;
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
	 * Sort method that sorts the list by comparing area(s) and sorts to an ascending order,
	 * creates a new comparator object and is overridden by using area(s) as a 
	 * comparison.
	 */
	public void sortSize() {
		//Sorts the windows in the stack by their area (length x width).
		//MOST of this is done for you, but you still need to assign
		//the returned head and tail back.
		
		//unselect the top window
		this.getHead().data.setSelected(false);
		
		//create a way to compare windows by area
		Comparator<Window> comp = new Comparator<>() {
			public int compare(Window w1, Window w2) {
				return (w1.getWidth()*w1.getHeight())-(w2.getWidth()*w2.getHeight());
			}
		};
		
		//create a pair of nodes to pass into the sort function
		ThreeTenLinkedList.NodePair<Window> pair = new ThreeTenLinkedList.NodePair<>(getHead(), getTail());
		
		//call the sort function with the comparator
		ThreeTenLinkedList.NodePair<Window> ret = ThreeTenLinkedList.sort(pair, comp);
		
		//make the returned list the head and tail of this list
		//this is for PART 5 of the project... don't try to do this
		//before you complete ThreeTenLinkedList.sort()!
		//< YOUR_CODE_HERE >
		
		list.setHead(ret.head);
		list.setTail(ret.tail);
		
		//re-select the top of the stack
		this.getHead().data.setSelected(true);
		

	}
	/**
	 * Sort method that sorts the list by comparing locations(s) and sorts to an ascending order,
	 * creates a new comparator object and is overridden by using id(s) as a 
	 * comparison. This is similar to the square sortLoc() method.
	 */
	public void sortLoc() {
		//Sorts the windows in the stack by their upper left
		//corner loction. Left things (bigger-X) are on top
		//of right things (smaller-X). Tie-breaker: lower
		//things (bigger-Y) top of  higher things (smaller-Y).

		//This should use your ThreeTenLinkedList.sort() method you
		//write in Part 5, so don't do this until you have (a) read
		//part 5, (b) looked at the example in sortSize() above, and
		//(c) are sure you understand comparators.
		
		//O(n^2)
		
		//< YOUR_CODE_HERE >
		
		this.getHead().data.setSelected(false);
		
		Comparator<Window> comp = new Comparator<>() {

			@Override
			public int compare(Window i, Window j) {
				
				if (i.getUpperLeftX() == j.getUpperLeftX()) {
					return j.getUpperLeftY() - i.getUpperLeftY();
				}
				
				else {
					return j.getUpperLeftX() - i.getUpperLeftX();
				}
			}
		};
		ThreeTenLinkedList.NodePair<Window> pair = new ThreeTenLinkedList.NodePair<Window>(list.getHead(), list.getTail());
		ThreeTenLinkedList.NodePair<Window> sort = ThreeTenLinkedList.sort(pair, comp);
		
		list.setHead(sort.head);
		list.setTail(sort.tail);
		
		this.getHead().data.setSelected(true);
		
	
	}
}

