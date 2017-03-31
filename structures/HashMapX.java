package structures;

/*	Some kind of HashTable implementation
 * 	Fields:
 * 		MAX_SIZE:				Initialized to be the size of the input file.
 * 								For example: MAX_SIZE for dict1 = 71,884
 * 		size_nonempty_cells:	Size of non-null cells in the Hash Table
 * 								Effectively equal to the number of Anagram Classes stored in the table
 *		size_num_values:		Number of total words stored in the table
 *		entries:				Array of LinkedLists and is the entire table
 *	Hashing Mechanism:	We use the division method, we can have non-distinct keys for different Anagram Class signatures where each
 *						key is simply the multiplication of unique primes based off the characters of an anagram class
 *						So to prevent the issue of grouping together words that don't belong in the same anagram class,
 *						We combine chaining and linear probing. If we encounter a non-distinct key, we simply move on to the next
 *						available hash value. Searching is not required with regards to the purpose of this program.
 */

public class HashMapX {
	public int MAX_SIZE;
	public int size_nonempty_cells;
	public int size_num_values;
	private LinkedList[] entries;
	
	/* 	Constructor
	 * 	Initializes fields
	 */
	public HashMapX(int size){
		this.MAX_SIZE = size;
		this.size_nonempty_cells = 0;
		this.size_num_values = 0;
		entries = new LinkedList[size];
	}
	
	/*	Returns the LinkedList/Anagram Class associated with the given index of the table
	 * 	Runs in O(1) time
	 */
	public LinkedList getInd(int ind){
		return entries[ind];
	}
	
	/*	Returns the LinkedList/Anagram Class associated with a given key
	 * 	TODO: COMPUTE RUNTIME
	 */
	public LinkedList get(long key){
		int hash = (int)(key % MAX_SIZE);
		if(entries[hash] == null){
			return null;
		}
		else{
			if(entries[hash].unique == key)
				return entries[hash];
			else{
				while(entries[hash].unique != key){
					hash = (hash + 1) % MAX_SIZE;
				}
				return entries[hash];
			}
			
		}
	}
	
	/*	Insertion method
	 * 	Our hash function is simply key % size, where size is the size of the input array
	 * 	There may not represent a unique hash value determined by our hash function so we 
	 * 	simply implement linear probing to fix this issue and we move on to the next empty
	 *  node
	 */
	public void put(long key, String value){
		//Hash Function
		int hash = (int) (key % MAX_SIZE);
		
		//Finds the next empty node if the current node is occupied by an Anagram class
		//that's not the one we're looking for
		while(entries[hash] != null && entries[hash].unique != key){
			hash = (hash + 1) % MAX_SIZE;
		}
		
		//If we found the correct Anagram Class associated with the key and value
		//Code is kind of redundant but it gets the job done
		if(entries[hash] != null && entries[hash].unique == key){
			if(entries[hash].unique == key){
				
				//Adds the current value to the hash table
				entries[hash].add(value);
				
				//Increments the total number of values
				size_num_values++;
				
				//Ends
				return;
			}
		}
		
		//If we have an empty node, i.e. we found a new Anagram Class
		//Create new Linked List associated with the anagram class and add it to the table
		entries[hash] = new LinkedList(value, null, key);
		
		//Increment number of Anagram classes and number of values
		size_nonempty_cells++;
		size_num_values++;
	}
	
	/* Simply prints the Anagram Class associated with a given hash code */
	public void printCurr(int hash){
		if(entries[hash] == null)
			return;
		
		Node curr = entries[hash].head;
		while(curr != null){
			System.out.print(curr.value + " ");
			curr = curr.next;
		}
	}
	
	/* Simply prints the Anagram Class associated with a given key */
	public void printKey(long key){
		int hash = (int)(key % MAX_SIZE);
		Node curr = entries[hash].head;
		while(curr != null){
			System.out.println(curr.value);
			curr = curr.next;
		}
	}
}
