public class ElectricMonster extends Monster {
	// Generating constructor
	public ElectricMonster(int hitPoints, Attack[] attackObject) {
		super("Electric", hitPoints, attackObject);
	}

	// Always return false
	protected boolean dodge() {
		return false;
	}

}