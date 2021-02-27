public class WaterMonster extends Monster {
	// Generating constructor
	public WaterMonster(int hitPoints, Attack[] attackObject) {
		super("Water", hitPoints, attackObject);
	}

	// Return true if hitPints are at least 100, return false otherwise
	protected boolean dodge() {
		if (hitPoints >= 100) {
			return true;
		} else {
			return false;
		}
	}
}