package sentutil;
import java.util.ArrayList;

/**
 * Simple library which might (outside of this assignment) contain
 * various functions related to some sentence utilities.
 * @author Keziah Rezaey
 * @author Moriah Scott
 * @author Leilani Davis
 */

public class SentUtils {

    /**
     * Returns the number of unique, repeated words that are found
     * in the given sentence sent
     * @param sent The sentence in which to count repeated words
     * @return The number of unique, repeated words
     */
	
    public static int repeats (String sent) {
    	int count = 0;
    	String[] words = sent.split(" ");
    	ArrayList<String> repeated = new ArrayList<String>();
    	
    	for (int i = 0; i < words.length - 1; i++) {
    		for (int j = i + 1; j < words.length; j++) {
    			if (words[i].contentEquals(words[j]) & !repeated.contains(words[j])) {
    				count++;
					repeated.add(words[i]);		
    			}
    		}
    	}    
    	return count;
    }
    
    public static void main (String[] args) {
  
    }

}
