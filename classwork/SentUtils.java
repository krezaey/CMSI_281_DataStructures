package sentutil;
import java.util.*;

/**
 * Simple library which might (outside of this assignment) contain
 * various functions related to some sentence utilities.
 */
public class SentUtils {

    /**
     * Returns the number of unique, repeated words that are found
     * in the given sentence sent
     * @param sent The sentence in which to count repeated words
     * @return The number of unique, repeated words
     */
    public static int repeats (String sent) {
        String[] words = sent.split(" ");
        int count = 0;
        Map <String, Integer> dictionary = new HashMap<String, Integer>();
        
        for (String word : words) {
            Integer occurence = dictionary.get(word);
            if (occurence == null) { dictionary.put(word, 1); }
            else { dictionary.put(word, occurence + 1); }
        }
        for (Map.Entry<String, Integer> map : dictionary.entrySet()) {
            if (map.getValue() > 1) {count++;}
        }

        return count;
    }
}
