import java.util.Arrays;

public class Attack {
	// Initialising field
	private String name;
	private int Points;

	// Generating constructor
	public Attack(String name, int Points) {
		this.name = name;
		this.Points = Points;
	}

	// Generating getter methods
	public String getName() {
		return name;
	}

	public int getPoints() {
		return Points;
	}

	// Overriding toString() method
	public String toString() {
		return "Attack name=" + name + ", Points=" + Points;
	}
}
