/**
 * Node class that defines multiple objects, which gives us our linked list formation.
 * @author William Nguyen
 *
 * @param <T> generics defined class
 */
class Node<T> {
	/**
	 * Instances class of data being stored in object.
	 */
	public T data;
	/**
	 * A pointer that points to the next node.
	 */
	public Node<T> next;
	/**
	 * A pointer that points to the previous node.
	 */
	public Node<T> prev;
	/**
	 * If no element is defined within a new creation of a node, nothing is initialized which leads to 
	 * the data being null.
	 */
	public Node() {
		
	}
	/**
	 * Constructor that initializes data to equal the instance variable from the creation of the node.
	 * @param data being used to store in node
	 */
	public Node(T data) {
		this.data = data;
	}
}
