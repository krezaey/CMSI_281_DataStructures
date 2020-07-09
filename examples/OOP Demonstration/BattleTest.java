package forneymon;

public class BattleTest {
	
	public static void main (String[] args) {
		
		//make a new Burnymon
		Burnymon burny = new Burnymon("Burny");
		burny.takeDamage(5, "dampy");
		System.out.println(burny);
		
		//burny.health = 10000;
		//user should not be able to this, thus, access restriction
		//compiler would fail if this was not commented out
		//since it is private, cannot be accessed
		
		//getter method to allow user to see it, better alternative
		System.out.println(burny.getHealth());
		
		
		//do not want user to allow this, hence use abstract class keyword to prevent user from instantiation
		//Dampymon ULTRAMON = new Forneymon("Bob", 100000000);
	}

}
