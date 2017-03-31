package structures;

//Pretty simple. Very basic linked list. Nothing fancy.
public class Node {
	public String value;
	public Node next;
	
	//Constructor
	public Node(String value, Node next){
		this.value = value;
		this.next = next;
	}
}
