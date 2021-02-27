public class FireMonster extends Monster {
	// Generating constructor
	public FireMonster(int hitPoints, Attack[] attackObject) {
		super("Fire", hitPoints, attackObject);
	}

	// Alternatively return true and false
	private boolean firstTime = true;

	protected boolean dodge() {
		if (firstTime) {
			firstTime = false;
			return true;
		} else {
			firstTime = true;
			return false;
		}
	}

}