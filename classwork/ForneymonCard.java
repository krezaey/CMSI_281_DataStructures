package forneymon.cardgame;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Basic card class for the Forneymon Trading Card game
 * @author Keziah Rezaey
 * @author Moriah Scott
 * @author Leilani Davis
 */

abstract public class ForneymonCard {
	
	private ArrayList<String> types = new ArrayList<String>(Arrays.asList("Burnymon", "Dampymon", "Leafymon"));
	private String name;
	private String type;
	
	/**
	 * Constructor with no parameters, created instance with default name and type
	 */
	
	public ForneymonCard () {
		name = "MissingNu";
		type = types.get(0);
	}
	
	/**
	 * Parameterized constructor to specify name and type
	 * @param String name
	 * @param String type
	 * @throws IllegalArgumentException() if name is blank or type is invalid
	 */
	
	public ForneymonCard (String name, String type) {
		if (!types.contains(type)) {
			throw new IllegalArgumentException();
		}
		if (name.contentEquals("") || name.contentEquals(" ")) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.type = type;
	}
	
	/**
	 * String representation of the ForneymonCard instance
	 * @return String "type: name"
	 */
	
	public String toString() {
		return type + ": " + name;
	}
	
	/**
	 * Gets the name of the ForneymonCard instance
	 * @return String name
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the type of the ForneymonCard instance
	 * @return String type
	 */
	
	public String getType() {
		return type;
	}
}
