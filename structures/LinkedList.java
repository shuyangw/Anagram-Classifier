package structures;

/*	Class for a LinkedList, represents a single Anagram Class
 * 	Stores the size, the unique long signature associated with the current Anagram Class
 * 	Stores the unique String of the Anagram Class
 * 	Stores a list of the first 26 primes to compute the unique representation of any given Anagram Class
 */

public class LinkedList {
	public Node head;
	public int size;
	public long unique;
	public String str;
	static int[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
	
	/*	Constructor
	 * 	Initializes fields
	 */
	public LinkedList(String value, Node next, long unique) {
		str = Word.sort(value);
		this.head = new Node(value, next);
		this.unique = unique;
		this.size = 1;
	}
	
	/*	Add method
	 * 	Creates a new Linked List Node and appends it to the head of the Linked List
	 * 	Takes O(1) time
	 */
	public void add(String str){
		Node newNode = new Node(str, null);
		newNode.next = head;
		head = newNode;
		size++;
	}

	/*	Converts the entire Anagram Class to a String array of every word in the Class
	 *	I don't think I ever use it but whatever
	 *	Takes O(n) time where n is the size of the current Anagram Class 
	 */
	public String[] toArray(){
		String[] arr = new String[size];
		Node curr = head;
		int count = 0;
		while(curr != null){
			arr[count] = curr.value;
			curr = curr.next;
			count++;
		}
		return arr;
	}
	
	/*	Converts a given string to the unique integer representing its Anagram Class by
	 * 	Mapping a unique prime to each letter of the alphabet and then multiply the respective
	 * 	primes together based on the current character of the string
	 * 	Takes O(n) time based on the size of the word
	 */
	public static long convertToNumber(String w){
		long unique = 1;
		for(int i = 0; i < w.length(); i++){
			unique *= primes[w.charAt(i) - 97];
		}
		if(unique < 0){
			unique *= -1;
		}
		return unique;
	}
}
