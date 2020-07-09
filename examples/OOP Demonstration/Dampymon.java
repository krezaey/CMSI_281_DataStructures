package forneymon;

public class Dampymon extends Forneymon implements MinForneymon {
	
	//Constructor
	public Dampymon (String name) {
		super(name, 25);
	}
	
	//Methods 
	public int takeDamage(int dmg, String type) {
		if (type.equals("burny")) {
			dmg += 5;
		}
		return super.takeDamage(dmg, type);
	}
}


