package support;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*	Driver class for file reading and writing
 * 	Employs FileWriter and BufferedWriter
 * 	NB:	Reading and writing file significantly increases overall runtime, perhaps
 * 		by 3 or 4 times the overall runtime for the main computation script
 */

public class FileIO {
	public FileWriter fw;
	public BufferedWriter bw;
	
	/*	Constructor
	 */
	public FileIO(String str) throws IOException{
		File file = new File(str + ".txt");
		fw = new FileWriter(file);
		bw = new BufferedWriter(fw);
	}
	
	/*	Reads an input file and throws an exception if it does not exist
	 * 	Returns a String array of the values contained in the input file
	 */
	
	public String[] readFile(String file) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(file));
		
		//Forgive usage please, only using an ArrayList to convert an input file to a usable String array
		List<String> lines = new ArrayList<String>();
		while(scan.hasNext()){
			lines.add(scan.next());
		}
		String[] ret = new String[lines.size()];
		scan.close();
		return lines.toArray(ret);
	}

	/*	Writes a String plus a space to the file */
	public void writeFile(String str) throws IOException{
		bw.write(str + " ");
	}
	
	/*	Writes a new line to the file */
	public void writeNewLine() throws IOException{
		bw.newLine();
	}
	
	/*	Writes an ending to the file consisting of
	 * 	the total number if anagram classes and
	 * 	the total runtime for the program excluding the file reading time
	 */
	public void writeEnd(int size, double time) throws IOException{
		bw.write("Total anagram classes: " + size);
		bw.newLine();
		bw.write("Total time for main anagram script: " + time/1000.0 + " seconds");
		bw.close();
	}
}

