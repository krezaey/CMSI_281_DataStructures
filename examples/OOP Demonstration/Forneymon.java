package forneymon;

abstract public class Forneymon implements MinForneymon {
	
	//Fields
	private String name;
	private int health;
	
	//Superclass Constructor
	Forneymon (String n, int h) {
		this.name = n;
		this.health = h;
	}
	
	//Methods
	public int takeDamage(int dmg, String type) {
		this.health -= dmg;
		return this.health;
	}
	public int getHealth() {
		return health;
	}
	
	public String toString() {
		return name + " " + name;
	}
	
}


