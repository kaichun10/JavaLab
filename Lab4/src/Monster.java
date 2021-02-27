import java.util.Arrays;

public abstract class Monster {
	// Initialising field
	protected String type;
	protected int hitPoints;
	protected Attack[] attackObject;

	// Generating constructor
	public Monster(String type, int hitPoints, Attack[] attackObject) {
		this.type = type;
		this.hitPoints = hitPoints;
		this.attackObject = attackObject;
	}

	// Generating getter methods
	public int getHitPoints() {
		return hitPoints;
	}

	public String getType() {
		return this.type;
	}

	public Attack[] getAttacks() {
		return attackObject;
	}

	public void attack(String attack, Monster otherMonster) throws MonsterException {
		// A monster cannot attack itself
		if (otherMonster == this) {
			throw new MonsterException("A monster cannot attack itself");
		}
		// A monster cannot attack or be attacked if it is knocked out
		if (this.hitPoints <= 0 || otherMonster.getHitPoints() <= 0) {
			throw new MonsterException("A monster cannot attack or be attacked if it is knocked out");
		}

		// If the result is true 10 hit points are removed from the monster doing the
		// attacking
		// Else remove the corresponding hitPoints from the otherMonster
		if (otherMonster.dodge()) {
			this.removeHitPoints(10);
		} else {
			for (int i = 0; i < attackObject.length; i++) {
				if (attack == (attackObject[i].getName())) {
					otherMonster.removeHitPoints(attackObject[i].getPoints());
					break;
				}
			}
		}
	}

	// Remove hitPoints during an attack
	public void removeHitPoints(int points) {
		this.hitPoints -= points;
		if (hitPoints <= 0) {
			// Monster is knocked out
			hitPoints = 0;
		}
	}

	// Generating abstract dodge() method
	protected abstract boolean dodge();

	// Overriding toString() method
	public String toString() {
		return "Monster [type=" + type + ", hitPoints=" + hitPoints + ", attackObject=" + Arrays.toString(attackObject)
				+ "]";
	}
}
