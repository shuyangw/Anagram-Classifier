package structures;

public class Word {
	static int[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
	public long uniqueInt;
	public String word;
	public String sortedW;
	
	public Word(String w){
		word = w;
		uniqueInt = convertToNumber(w);
	}
	
	/* Counting sort to sort a word's characters in alphabetical order */
	public static String sort(String word){
		char[] arr = new char[word.length()];
		int[] freq = new int[26];
		for(int i = 0; i < word.length(); i++){
			freq[word.charAt(i) - 97]++;
		}
		for(int i = 1; i <= 25; i++){
			freq[i] += freq[i-1];
		}
		for(int i = 0; i < word.length(); i++){
			arr[freq[word.charAt(i) - 97] - 1] = word.charAt(i);
			freq[word.charAt(i)-97]--;
		}
		String sortWord = "";
		for(int i = 0; i < arr.length; i++){
			sortWord += arr[i];
		}
		return sortWord;
	}
	
	/*	Converts a given String to a unique representation of it given by a product of primes
	 * 	associated with each character
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
