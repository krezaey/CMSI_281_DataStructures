package autocompleter;

import java.util.ArrayList;

/**
 * A ternary-search-tree implementation of a text-autocompletion trie, a
 * simplified version of some autocomplete software.
 * 
 * @author Keziah Rezaey
 */
public class AutoCompleter implements AutoCompleterInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------

    TTNode root;

    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------

    AutoCompleter() {
        root = null;
    }

    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------

    /**
     * Returns true if the Autocompleter has no search terms stored, false
     * otherwise.
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Adds the given search term to the Autocompleter.
     * 
     * @param String toAdd
     */
    public void addTerm(String toAdd) {
        if (hasTerm(toAdd)) { return; }
        root = add(root, toAdd);
    }

    /**
     * Returns whether the query exists in the Autocompleter or not.
     * 
     * @param String query
     * @return boolean true / false
     */
    public boolean hasTerm(String query) {
        return hasTerm(root, query);
    }

    /**
     * Returns the first search term contained in the Autocompleter that possesses
     * the query as a prefix. If there is no term with the given prefix query,
     * returns null.
     * 
     * @param String query
     * @return String
     */
    public String getSuggestedTerm(String query) {
        if (hasTerm(query)) { return query; }
        String suggested = "";
        return getSuggestedTerm(root, query, suggested);
    }

    /**
     * Returns an ArrayList of Strings consisting of the alphabetically sorted
     * search terms within this Autocompleter.
     * 
     * @return ArrayList<String>
     */
    public ArrayList<String> getSortedTerms() {
        ArrayList<String> result = new ArrayList<String>();
        String term = "";
        return getSortedTerm(root, term, result);
    }

    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------

    /**
     * Normalizes a term to either add or search for in the tree, since we do not
     * want to allow the addition of either null or empty strings within, including
     * empty spaces at the beginning or end of the string (spaces in the middle are
     * fine, as they allow our tree to also store multi-word phrases).
     * 
     * @param s The string to sanitize
     * @return The sanitized version of s
     */
    private String normalizeTerm(String s) {
        // Edge case handling: empty Strings illegal
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.trim().toLowerCase();
    }

    /**
     * Given two characters, c1 and c2, determines whether c1 is alphabetically less
     * than, greater than, or equal to c2
     * 
     * @param c1 The first character
     * @param c2 The second character
     * @return - some int less than 0 if c1 is alphabetically less than c2 - 0 if c1
     *         is equal to c2 - some int greater than 0 if c1 is alphabetically
     *         greater than c2
     */
    private int compareChars(char c1, char c2) {
        return Character.toLowerCase(c1) - Character.toLowerCase(c2);
    }

    /**
     * Builds a middle child at the indicated TTNode. Will handle word endings.
     * 
     * @param TTNode node
     * @param String toAdd
     */
    private void buildDownString(TTNode node, String toAdd) {
        char[] termArray = toAdd.toCharArray();
        TTNode current = node;
        for (char c : termArray) {
            current.mid = new TTNode(c, false);
            current = current.mid;
        }
        current.wordEnd = true;
    }

    /**
     * Traverses and adds nodes based on chars from toAdd with reference to the node
     * passed.
     * 
     * @param TTNode node
     * @param String toAdd
     * @return TTNode
     */
    private TTNode add(TTNode node, String toAdd) {
        String term = normalizeTerm(toAdd);
        TTNode current = node;

        if (current == null) {
            current = new TTNode(term.charAt(0), false);
            buildDownString(current, term.substring(1));
            return current;
        }
        if (compareChars(current.letter, term.charAt(0)) == 0 && toAdd.length() > 1) {
            current.mid = add(current.mid, term.substring(1));
        } else if (compareChars(current.letter, term.charAt(0)) > 0) {
            current.left = add(current.left, term);
        } else if (compareChars(current.letter, term.charAt(0)) < 0) {
            current.right = add(current.right, term);
        }
        if (compareChars(current.letter, term.charAt(term.length() - 1)) == 0) {
            current.wordEnd = true;
        }
        return current;
    }

    /**
     * Traverses the trie and searches whether the query is inside the tree.
     * 
     * @param TTNode node
     * @param String query
     * @return boolean true / false
     */
    private boolean hasTerm(TTNode node, String query) {
        String term = normalizeTerm(query);
        TTNode current = node;

        if (current == null || (query.length() == 1 && current.wordEnd == false)) {
            return false;
        }
        if (compareChars(current.letter, term.charAt(0)) == 0) {
            if (query.length() == 1 && current.wordEnd == true) {
                return true;
            }
            return hasTerm(current.mid, term.substring(1));
        } else if (compareChars(current.letter, term.charAt(0)) > 0) {
            return hasTerm(current.left, normalizeTerm(query));
        } else if (compareChars(current.letter, term.charAt(0)) < 0) {
            return hasTerm(current.right, normalizeTerm(query));
        }
        return false;
    }

    /**
     * Traverses the trie and finds the term that best completes the prefix as per
     * the given query.
     * 
     * @param TTNode node
     * @param String query
     * @return String
     */
    private String getSuggestedTerm(TTNode node, String query, String result) {
        TTNode current = node;

        if (current == null && query.length() > 0) { return null; }
        if (query.length() == 0 && !current.wordEnd) {
            for (TTNode n = current; n != null && n.wordEnd != true; n = n.mid) {
                result += n.letter;
                current = n.mid;
            }
            result += current.letter;
            return result;
        }
        if (query.length() == 0 && current.wordEnd) {
            result += current.letter;
            return result;
        }
        if (compareChars(current.letter, normalizeTerm(query).charAt(0)) == 0) {
            result += current.letter;
            return getSuggestedTerm(current.mid, normalizeTerm(query).substring(1), result);
        }
        if (compareChars(current.letter, normalizeTerm(query).charAt(0)) > 0) {
            return getSuggestedTerm(current.left, normalizeTerm(query), result);
        }
        if (compareChars(current.letter, normalizeTerm(query).charAt(0)) < 0) {
            return getSuggestedTerm(current.right, normalizeTerm(query), result);
        }
        return null;
    }

    /**
     * Gets all the terms in alphabetical order in the trie.
     * 
     * @param TTNode            node
     * @param String            term
     * @param ArrayList<String> result
     * @return ArrayList<String>
     */
    private ArrayList<String> getSortedTerm(TTNode node, String term, ArrayList<String> result) {
        if (node == null) { return result; }
        result = getSortedTerm(node.left, term, result);
        if (node.wordEnd) {
            term = term + node.letter;
            result.add(term);
            term = term.substring(0, term.length() - 1);
        }
        result = getSortedTerm(node.mid, term + node.letter, result);
        result = getSortedTerm(node.right, term, result);
        return result;
    }

    // -----------------------------------------------------------
    // TTNode Internal Storage
    // -----------------------------------------------------------

    /**
     * Internal storage of autocompleter search terms as represented using a Ternary
     * Tree with TTNodes
     */
    private class TTNode {

        boolean wordEnd;
        char letter;
        TTNode left, mid, right;

        /**
         * Constructs a new TTNode containing the given character and whether or not it
         * represents a word-end, which can then be added to the existing tree.
         * 
         * @param c Letter to store at this node
         * @param w Whether or not this is a word-end
         */
        TTNode(char c, boolean w) {
            letter = c;
            wordEnd = w;
        }

    }
}
