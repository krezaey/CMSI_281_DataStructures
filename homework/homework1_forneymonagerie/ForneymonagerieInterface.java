/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ForneymonagerieInterface.java
 *  Author        :  Keziah Camille Rezaey
 *  Due Date      :  2019-10-21
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package forneymonagerie;

public interface ForneymonagerieInterface {

    boolean empty ();
    int size ();
    int typeSize ();
    boolean collect (String typeToAdd);
    boolean release (String typeToRelease);
    int countType (String typeToCount);
    void releaseType (String typeToNuke);
    boolean contains (String typeToCheck);
    String nth (int n);
    String mostRecentAtRarity (int count);
    ForneymonagerieInterface clone ();
    
}
