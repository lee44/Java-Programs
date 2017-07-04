import java.util.List;

public interface IWordMatcher 
{
	
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
	public List<String> getMatches(String word);
}
