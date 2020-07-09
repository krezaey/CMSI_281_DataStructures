package webnav;

import java.util.*;

/**
 * Web Navigator used to track which URLs a user is currently
 * or was previously browsing, as well as tools for updating the
 * currently viewed based on their session history.
 */
public class WebNavigator {

	public LinkedList<String> dirkwoob = new LinkedList<>();
	private String current;
    
    WebNavigator () {
        this.current = null;
    }
    
    /**
     *  Visits the current site, clears the forward history,
     *  and records the visited site in the back history
     *  @param site The new site being visited
     */
    public void visit (String site) {
        if (this.current != null) { dirkwoob.offerFirst(this.current); }
    	this.current = site;
        dirkwoob.add(site);        
    }
    
    /**
     *  Changes the current site to the one that was last
     *  visited in the order on which visit was called on it
     */
    public void back () {
    	int index = dirkwoob.indexOf(this.current);   
        if (index <= 0) { this.current = dirkwoob.get(index);}
        else { this.current = dirkwoob.get(index-1); }
    }
    
    /**
     * Changes the current site to the one that was last
     * returned to via back()
     */
    public void forw () {
    	int index = dirkwoob.indexOf(this.current);
        if (index >= dirkwoob.size()-1) { this.current = dirkwoob.get(index); }
        else { this.current = dirkwoob.getLast(); }
    }
    
    /**
     * Returns the String representing the site that the navigator
     * is currently at
     * @return The current site's URL
     */
    public String getCurrent () {
        return this.current;
    }
}