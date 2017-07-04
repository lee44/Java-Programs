import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author YOUR NAME HERE
 * @SMC_ID YOUR SMC ID HERE
 * 
 * CS 20B Programming Project 5
 *
 */

public class WordMatcher implements IWordMatcher {
	
	private final int TABLE_SIZE = 11117;
	private List<String>[] table = new LinkedList[TABLE_SIZE];
	
	/**
	 * Constructs a word matcher based on the given dictionary.
	 * 
	 * @param filename The dictionary file name
	 */
	public WordMatcher(String filename) {
		// instantiate all lists expecting them to be needed
		for (int i = 0; i < TABLE_SIZE; i++) {
			table[i] = new LinkedList<String>();
		}
		// hash each dictionary word
		try (Scanner in = new Scanner(new File(filename))) {
			while (in.hasNextLine()) {
				String word = in.nextLine().toLowerCase();
				table[hash(word)].add(word);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Calculates the hash value for a given word. Matching words have the same hash.
	 * 
	 * @param word The word
	 * @return The hash value
	 */
	private int hash(String word) {
		char[] w = word.toCharArray();
		Arrays.sort(w);
		int h=17;
		for (int i=0; i<w.length; i++) {
			h = h * 31 + w[i];
		}
		return Math.abs(h) % TABLE_SIZE;
	}
	
	/**
	 * Checks if two words match.
	 * 
	 * @param word1
	 * @param word2
	 * @return True if the words match, false otherwise
	 */
	private boolean matches(String word1, String word2) {
		char[] w1 = word1.toCharArray();
		Arrays.sort(w1);
		char[] w2 = word2.toCharArray();
		Arrays.sort(w2);
		return Arrays.equals(w1, w2);
	}
	
	/**
	 * Return a list of dictionary words that have the same letters as the given word.
	 * Differences in letter cases are ignored.
	 * 
	 * @param word The word to find matches for. May or may not be in the dictionary.
	 * 
	 * @return The list of matching words from the dictionary, all in lower case.
	 * 		   The word itself is not included in the returned list.
	 * 		   e.g.: 	NAME -> [amen, mane, mean]
	 * 					VeronicaOnts -> [conservation, conversation]
	 */
	@Override
	public List<String> getMatches(String word) {
		word = word.toLowerCase();
		List<String> matches = new LinkedList<String>();
		for (String w : table[hash(word)]) {
			if (!word.equals(w) && matches(word, w)) {
				matches.add(w);
			}
		}
		return matches;
	}
}
