package wiki;

import java.util.*;

public class WikiWalker {

    HashMap<String, HashMap<String, Integer>> sitemap;

    WikiWalker() {
        sitemap = new HashMap<String, HashMap<String, Integer>>();
    }

    /**
     * Adds an article with the given name to the site map and associates the given
     * linked articles found on the page. Duplicate links in that list are ignored,
     * as should an article's links to itself.
     * 
     * @param articleName  The name of the page's article
     * @param articleLinks List of names for those articles linked on the page
     */
    public void addArticle(String articleName, List<String> articleLinks) {
        sitemap.put(articleName, new HashMap<String, Integer>());
        for (String item : articleLinks) { sitemap.get(articleName).put(item, 0); }
    }
    
    /**
     * Determines whether or not, based on the added articles with their links,
     * there is *some* sequence of links that could be followed to take the user
     * from the source article to the destination.
     * 
     * @param src  The beginning article of the possible path
     * @param dest The end article along a possible path
     * @return boolean representing whether or not that path exists
     */
    public boolean hasPath(String src, String dest) {
        ArrayList<String> visited = new ArrayList<String>();
        return hasPath(src, dest, visited);
    }

    /**
     * Helper method for recursion. Determines whether or not, based on the added
     * articles with their links, there is *some* sequence of links that could be
     * followed to take the user from the source article to the destination.
     * 
     * @param src     String
     * @param dest    String
     * @param visited ArrayList<String>
     * @return boolean whether or not path exists
     */

    private boolean hasPath(String src, String dest, ArrayList<String> visited) {
        if (src == dest) { return true; }
        if (visited.contains(src)) { return false; }
        visited.add(src);
        if (sitemap.get(src) != null) {
            for (Map.Entry<String, Integer> map : sitemap.get(src).entrySet()) {
                String current = map.getKey();
                if (current == dest) { return true; }
                if (hasPath(current, dest, visited)) { return true; }
            }
        }
        return false;
    }

    /**
     * Increments the click counts of each link along some trajectory. For instance,
     * a trajectory of ["A", "B", "C"] will increment the click count of the "B"
     * link on the "A" page, and the count of the "C" link on the "B" page. Assume
     * that all given trajectories are valid, meaning that a link exists from page i
     * to i+1 for each index i
     * 
     * @param traj A sequence of a user's page clicks; must be at least 2 article
     *             names in length
     */
    public void logTrajectory(List<String> traj) {
        for (int i = 0; i < traj.size() - 1; i++) {
            String currentLink = traj.get(i);
            String nextLink = traj.get(i + 1);
            HashMap<String, Integer> innerMap = sitemap.get(currentLink);
            innerMap.replace(nextLink, innerMap.get(nextLink) + 1);
        }
    }

    /**
     * Returns the number of clickthroughs recorded from the src article to the
     * destination article. If the destination article is not a link directly
     * reachable from the src, returns -1.
     * 
     * @param src  The article on which the clickthrough occurs.
     * @param dest The article requested by the clickthrough.
     * @throws IllegalArgumentException if src isn't in site map
     * @return The number of times the destination has been requested from the
     *         source.
     */
    public int clickthroughs(String src, String dest) {
        if (!sitemap.containsKey(src)) { throw new IllegalArgumentException(); }
        if (!sitemap.get(src).containsKey(dest)) { return -1; }
        return sitemap.get(src).get(dest);
    }

    /**
     * Based on the pattern of clickthrough trajectories recorded by this
     * WikiWalker, returns the most likely trajectory of k clickthroughs starting at
     * (but not including in the output) the given src article. Duplicates and
     * cycles are valid output along a most likely trajectory. In the event of a tie
     * in max clickthrough "weight," this method will choose the link earliest in
     * the ascending alphabetic order of those tied.
     * 
     * @param src The starting article of the trajectory (which will not be included
     *            in the output)
     * @param k   The maximum length of the desired trajectory (though may be
     *            shorter in the case that the trajectory ends with a terminal
     *            article).
     * @return A List containing the ordered article names of the most likely
     *         trajectory starting at src.
     */
    public List<String> mostLikelyTrajectory(String src, int k) {
        List<String> traj = new ArrayList<String>();
        String temp = null;
        while (k > 0) {
            if (sitemap.containsKey(src)) {
                for (Map.Entry<String, Integer> map : sitemap.get(src).entrySet()) {
                    String currentKey = map.getKey();
                    if (temp == null || map.getValue() > sitemap.get(src).get(temp)) {
                        temp = currentKey;
                    } 
                    else if (map.getValue() == sitemap.get(src).get(temp)) {
                        temp = (currentKey.compareToIgnoreCase(temp) < 0) ? currentKey : temp;
                    }
                }
                traj.add(temp);
                src = temp;
                temp = null;
                k--;
            } 
            else { k = 0; }
        }
        return traj;
    }

    /**
     * Shows a String representation of the site map with respect to each site, and
     * connections with distances at each node.
     * 
     * @return String
     */

    private String mapToString() {
        String result = "";
        for (Map.Entry<String, HashMap<String, Integer>> map : sitemap.entrySet()) {
            result += "\n CURRENT NODE: " + map.getKey() + "\n---Connections---\n";
            for (Map.Entry<String, Integer> innerMap : map.getValue().entrySet()) {
                result += "     " + innerMap.getKey() + ":" + innerMap.getValue();
            }
            result += "\n-----------------\n";
        }
        return result;
    }
}
