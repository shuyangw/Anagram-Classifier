import java.io.IOException;

import structures.HashMapX;
import structures.LinkedList;
import structures.Node;
import structures.Word;
import support.FileIO;

public class Anagram {
	//String array of every input word
	static String[] all;
	
	public static void main(String[] args) throws IOException{
		//Start measuring begin time
		final long startTime = System.currentTimeMillis();
		
		/*	Takes two command line arguments:	
		 * 		args[0] : name of input file
		 * 		args[1] : name of output file
		 * 
		 * 	NB: Input file should be in same directory as Anagram.java
		 */
		FileIO parseFile = new FileIO(args[1]);
		all = parseFile.readFile(args[0]);
		
		
		
		//Initializes HashTable
		HashMapX hashmap = new HashMapX(all.length);
		
		//For every word, put into HashTable
		for(int i = 0; i < all.length; i++){
			Word curr = new Word(all[i]);
			hashmap.put(curr.uniqueInt, curr.word);
		}
		
		//For every value of the HashMap, write each LinkedList to the output file
		//One LinkedList/Anagram Class per line
		for(int i = 0; i < all.length; i++){
			LinkedList curr = hashmap.getInd(i);
			
			//Skip if current entry is null
			if(curr == null)
				continue;
			Node currH = curr.head;
			while(currH != null){
				parseFile.writeFile(currH.value);
				currH = currH.next;
			}
			parseFile.writeNewLine();
		}
		
		//End timer
		final long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		parseFile.writeEnd(hashmap.size_nonempty_cells, totalTime);
		
		//Record number of Anagram Classes, total number of values, and runtime
		System.out.println(hashmap.size_nonempty_cells);
		System.out.println(hashmap.size_num_values);
		System.out.println(totalTime/1000.0 + " secs");
	}
}
