import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Jumble.
 * @see <a href="http://en.wikipedia.org/wiki/Jumble">Jumble</a>
 * @author John B. Matthews
 */
 
public class Jumble {

    private static final int MAX = 250000;
    private static final String NAME = "words.txt";
    private static final Map<String, Set<String>> map =
        new HashMap<String, Set<String>>();
    static {
        try {
            File file = new File(NAME);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(file)));
            String s;
            while ((s = in.readLine()) != null) {
                String sorted = sort(s);
                Set<String> words = map.get(sorted);
                if (words == null) {
                    words = new TreeSet<String>();
                    words.add(s);
                    map.put(sorted, words);
                } else {
                    words.add(s);
                }
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    // Sort the letters of a word
    private static String sort(String s) {
        byte[] ba = s.toLowerCase().getBytes();
        Arrays.sort(ba);
        return new String(ba);
    }

    public static void main(String... args) {
        if (args.length < 1) {
            showHelp();
            print();
        } else {
            for (String word : args) {
                System.out.print(word + ": ");
                Set<String> words = map.get(sort(word));
                if (words != null) {
                    for (String s : words) {
                        System.out.print(s + " ");
                    }
                    System.out.println();
                } else {
                    System.out.println("no match.");
                }
            }
        }
    }

    private static void showHelp() {
        System.out.println(
            "Usage: java -jar Jumble.jar <word> [<word>]");
    }

    // Print all entries with more than one permutation
    private static void print() {
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            Set<String> words = entry.getValue();
            if (words.size() > 0) {
                System.out.print(words.size() + " ");
                for (String s : words) {
                    System.out.print(s + " ");
                }
                System.out.println();
            }
        }
    }
}