//TODO: Linked list implementation (optional)
//      JavaDocs (not optional)
//      Static sorting methods (not optional)

import java.util.Comparator;
/**
 * Linked List class that provides many tools such as: adding, deleting, and pushing.
 * It also contains a sorting method for a linked list.
 * @author William Nguyen
 * @version 1.0
 * @param <T> defining the class a generics
 */
class ThreeTenLinkedList<T> {
	//You may, but are not required to, implement some or
	//all of this generic linked list class to help you with
	//this project. If you do, you MUST use the provided
	//Node class for this linked list class. This means
	//that it must be a doubly linked list (and links in
	//both directions MUST work).
	
	//Alternatively, you may implement this project using
	//only the Node class itself (i.e. use "bare nodes"
	//in the classes that require linked lists).
	
	//Either way, you MUST do all your own work. Any other
	//implementations you have done in the past, anything
	//from the book, etc. should not be in front of you,
	//and you certainly shouldn't copy and paste anything
	//from any other source.
	
	//This is the only class where you are allowed to
	//implement public methods.
	
	//In "Part 5" of the project, you will also be implementing
	//the following static methods:
	/**
	 * Checks to see if the method is sorted using a simple while loop.
	 * @param <X> type of generics 
	 * @param pairs the nodepair of head and tail being defined
	 * @param comp the type of comparator used for various types of comparisons
	 * @return returns true if sorted, false if not
	 */
	static <X> boolean isSorted(NodePair<X> pairs, Comparator<X> comp) {
		//Determine if the provided list is sorted based on the comparator.
		
		//For an empty linked list (e.g. the head-tail pair contains two nulls)
		//return true (an empty list is sorted).
		
		//For a null comparator or null pairs, throw an IllegalArgumentException.
		
		//O(n)
		
		//< YOUR_CODE_HERE >
		
		Node<X> head = pairs.head;
		Node<X> tail = pairs.tail;
		Node<X> current = head;
		
		
		if (comp == null || (head == null && tail == null)) {
			throw new IllegalArgumentException();
		}
		
		while (current != null) {
			if (current.next != null) {
				if (comp.compare(current.data, current.next.data) > 0) {
					return false;
				}
			}
			current = current.next;
		}
		
		return true; //replace this!
	}
	/**
	 * Sorting method using selection sort from the head being the lowest priority, and tail being the highest.
	 * Uses a nested while loop, having a comparative value with the lowest value associated with the highest value
	 * it being compared too. Replaces the data within the nodes, so that nodes are not physically handled.
	 * @param <X> the type of generics
	 * @param pairs the nodepair of head and tail being defined
	 * @param comp the type of comparator used for various types of comparisons
	 * @return new linked list that is sorted
	 */
	static <X> NodePair<X> sort(NodePair<X> pairs, Comparator<X> comp) {
		
		//Using the comparator, sort the linked list. It is recommended that
		//you sort by moving *values* around rather than moving nodes.
		//Two simple sorting algorithms which will work well here (and
		//meet the big-O requirements if implemented correctly) are the
		//insertion sort (see textbook Ch8.3) and the selection sort.

		//Insertion sort quick summary: Go to each element in the linked list,
		//shift it "left" into the correct position.
		//Example: 1,3,0,2
		// 1 is at the start of the list, leave it alone
		// 3 is bigger than 1, leave it alone
		// 0 is smaller than 3, move it left: 1,0,3,2
		// 0 is smaller than 1, move it to the left: 0,1,3,2
		// 0 is at the start of the list, stop moving it left
		// 2 is smaller than 3, move it to the left: 0,1,2,3
		// 2 is bigger than 1, stop moving it to the left

		//Selection sort quick summary: Go to each index in the linked list,
		//find the smallest thing from that index and to the "right",
		//and swap it into that index.
		//Example: 1,3,0,2
		// index 0: the smallest thing from index 0 to the end is 0, swap it into the right place: 0,3,1,2
		// index 1: the smallest thing from index 1 to the end is 1, swap it into the right place: 0,1,3,2
		// index 2: the smallest thing from index 2 to the end is 2, swap it into the right place: 0,1,2,3
		// index 3: there is only one item from index 3 to the end, so this is in the right places
		
		//Regardless of the method you choose, your sort should be a stable sort.
		//This means that if there are two equal values, they do not change their
		//order relative to each other.
		//Example: 1, 2, 1
		//The first "1" (which I'll call "1a") should be sorted before
		//the second "1" (1b), so that the output is "1a, 1b, 2" and
		//never "1b, 1a, 2". The easiest way to test this is to put two
		//equal items in the list, sort, and confirm using == that the
		//correct object is in the correct place.
		
		//For an empty linked list (e.g. the head-tail pair contains two nulls)
		//return the original pairs back to the user.
		
		//For a null comparator or null pairs, throw an IllegalArgumentException.
		
		//O(n^2)
		
		//< YOUR_CODE_HERE >
		
		Node<X> head = pairs.head;
		Node<X> tail = pairs.tail;
		
		
		if ((head == null && tail == null)) {
			return pairs;
		}
		
		if (comp == null || pairs == null) {
			throw new IllegalArgumentException();
		}
		
		Node<X> current = head;
		Node<X> currentplus = head.next;
		Node<X> temp = null;
		
		
		while (current != null) {
			int compVal = 0;
			while (currentplus != null) {
				
				
				
				if(comp.compare(current.data, currentplus.data) > compVal && comp.compare(current.data, currentplus.data) > 0) {
					compVal = comp.compare(current.data, currentplus.data);
					temp = currentplus;
				}
				currentplus = currentplus.next;
			}
			
			
			
			if (temp != null) {
				X tempData = current.data;
				
				current.data = temp.data;
				temp.data = tempData;
				temp = null;
				
			}
			

			current = current.next;
			if (current != null) {
				currentplus = current.next;
			}
		}
		
		return pairs; //replace this!
	}
	
	/**
	 * NodePair class that contains a pair of head and tails, coming in a pair instead of being singular.
	 * @author William Nguyen
	 * @version 1.0
	 * @param <Y> generics being defined in the class 
	 */
	//Which uses the following nested class:
	public static class NodePair<Y> {
		/**
		 * Node of the beginning of the linked list.
		 */
		public Node<Y> head;
		/**
		 * Node of the end of the linked list.
		 */
		public Node<Y> tail;
		/**
		 * Constructor that defines our head and tails.
		 * @param head beginning of the linked list
		 * @param tail end of the linked list
		 */
		public NodePair(Node<Y> head, Node<Y> tail) {
			this.head = head;
			this.tail = tail;
		}
	}
	
	/**
	 * Instance variable size of the linked list.
	 */
	private int size = 0;
	/**
	 * Defined node that will be the beginning of our linked list.
	 */
	Node<T> head;
	/**
	 * Defined node that will be the end of our linked list.
	 */
	Node<T> tail;
	/**
	 * Add method that uses nodes to point to each other.
	 * @param newElement element that is intended to be added in the linked list
	 */
	public void add(T newElement) {
		Node<T> node = new Node<T>(newElement);
		
		if (head == null) {
			head = node;
			tail = node;
			
			head.prev = null;
			tail.next = null;
		}
		
		else {
			tail.next = node;
			node.prev = tail;
			tail = node;
			tail.next = null;
			
		}
		
		size++;
		
	}
	
	/**
	 * Serves linked list to be a stack, inserting nodes in the front of list instead of the back.
	 * @param node being pushed in the linked list stack
	 */
	public void push(Node<T> node) {
		if (head == node) {
			return;
		}
		
		else {
			Node<T> temp = node;
			
			delete(node);
			
			temp.next = head;
			head.prev = temp;
			head = temp;
			head.prev = null;
			
			size++;
		}
	}
	/**
	 * Overriding previous class if data instead of node wants to be pushed in front of the linked list.
	 * @param element data being pushed in the linked list stack
	 */
	public void push(T element) {
		Node<T> newNode = new Node<T>(element);
		if (head == null) {
			head = newNode;
			tail = newNode;
							
			head.prev = null;
			tail.next = null;
							
			size++;
		}
		else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
			head.prev = null;
							
			size++;
		}
	}
	/**
	 * Deletes a node from a linked list by unlinking nodes.
	 * @param node being unlinked in the linked list
	 */
	public void delete(Node<T> node) {
		
		if (head == node) {
			if (head.next == null) {
				head = null;
				tail = null;
				size--;
				return;
			}
			
			else {
				head.next.prev = null;
				head = head.next;
				head.prev = null;
				size--;
				return;
			}
		}
		
		if (tail == node) {
			tail.prev.next = null;
			tail = tail.prev;
			tail.next = null;
			size--;
			return;
		}
		
		else {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			size--;
			return;
		}
		
		
	}
	/**
	 * Override previous delete method if data wants to be removed instead of node in the linked list.
	 * @param element data being removed 
	 */
	public void delete(T element) {
		Node<T> current = head;
		if (head.data == element) {
			if (head.next.data == null) {
				head = null;
				tail = null;
				size--;
				
			}
			
			else {
				head.next.prev = null;
				head = head.next;
				head.prev = null;
				size--;
				
			}
		}
		
		if (tail.data  == element) {
			tail.prev.next = null;
			tail = tail.prev;
			tail.next = null;
			size--;
			
		}
		
		
		while (current != null) {
			if (current.data == element) {
				current.prev.next = current.next;
				current.next.prev = current.prev;
				size--;
				break;
			}
			current = current.next;
			
		}
		
	}
	/**
	 * Set method that sets the head of the linked list.
	 * @param head being set
	 */
	public void setHead(Node<T> head) {
		this.head = head;
	}
	/**
	 * Set method that sets the tail of the linked list.
	 * @param tail being set
	 */
	public void setTail(Node<T> tail) {
		this.tail = tail;
	}
	/**
	 * Get method for head.
	 * @return head of the linked list
	 */
	public Node<T> getHead() {
		return head;
	}
	/**
	 * Get method for tail.
	 * @return tail of the linked list
	 */
	public Node<T> getTail() {
		return tail;
	}
	/**
	 * Gets size of the linked list, will not go under zero if no windows are trying to be removed.
	 * @return size of the linked list
	 */
	public int getSize() {
		if (size < 0) {
			size = 0;
		}
		return size;
	}
	
	//You may also use the above nested class elsewhere in your
	//project if you'd find that helpful.
}