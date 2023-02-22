

import java.util.Comparator;
/**
 * Linked List class that provides many tools such as: adding, deleting, and pushing.
 * It also contains a sorting method for a linked list.
 * @author William Nguyen
 * @version 1.0
 * @param <T> defining the class a generics
 */
class ThreeTenLinkedList<T> {
	/**
	 * Checks to see if the method is sorted using a simple while loop.
	 * @param <X> type of generics 
	 * @param pairs the nodepair of head and tail being defined
	 * @param comp the type of comparator used for various types of comparisons
	 * @return returns true if sorted, false if not
	 */
	static <X> boolean isSorted(NodePair<X> pairs, Comparator<X> comp) {
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
	
}
