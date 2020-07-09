/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  LinkedForneymonegerieIteratorInterface.java
 *  Author        :  Keziah Camille Rezaey
 *  Due Date      :  2019-10-21
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package forneymonagerie;

public interface LinkedForneymonegerieIteratorInterface {
    
    boolean isValid ();
    boolean hasNext ();
    boolean hasPrev ();
    String getType ();
    void next ();
    void prev ();
    void replaceAll (String typeToReplaceWith);
    
}