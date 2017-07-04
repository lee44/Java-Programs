public class TestCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		IWordMatcher matcher = new WordMatcher("words.txt");
		
		// Part 3 extra credit
		// Both implementations should produce the same result and have the same efficiency.
		//IWordMatcher matcher = new WordMatcher2("words.txt");   

		// test code for functionality
		
		long start, end;

		start = System.currentTimeMillis();
				
		String[] words = { "name", "hug", "cafe", "node", "canoe","accordNotInIt" ,
				"friend", "silent", "meteor", "markers", "aStewSir",
				"dirtyRoom", "stampStore", "moonStarers", "theClassroom",
				"dusty" };
		for (String word : words) {
			System.out.println(word + " -> " + matcher.getMatches(word));
		}
 		// Stress the application to ensure it runs efficiently under load.
		// All runs below should complete practically in an instant.
		final int RUNS = 100000;
		for (int i = 0; i <= RUNS; i++) {
			matcher.getMatches("noMoreStars");
			if (i % 1000 == 0) {
				System.out.print(".");
			}
		}
		System.out.println();
		System.out.println("noMoreStars" + " -> "
				+ matcher.getMatches("noMoreStars"));
		
		end = System.currentTimeMillis();
		System.out.println("Time took " + (end - start) / 1000.0
				+ " seconds.");
	}
}
