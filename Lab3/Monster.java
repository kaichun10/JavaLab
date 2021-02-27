import java.util.Arrays;

public class Monster {
	// Defining fields
	private String type;
	private int hitPoints;
	private String[] attacks;
	private int[] attacksPoints;

	// Defining Constructor
	public Monster(String type, int hitPoints, String[] attacks, int[] attacksPoints) {
		this.type = type;
		this.hitPoints = hitPoints;
		this.attacks = attacks;
		this.attacksPoints = attacksPoints;
	}

	// Defining Getter & Setter
	public String getType() {
		return this.type;
	}

	public int getHitPoints() {
		return this.hitPoints;
	}

	public String[] getAttacks() {
		return this.attacks;
	}

	public int[] getAttacksPoints() {
		return this.attacksPoints;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public void setAttacks(String[] attacks) {
		this.attacks = attacks;
	}

	public void setAttacksPoints(int[] attacksPoints) {
		this.attacksPoints = attacksPoints;
	}

	// Override toString()
	public String toString() {
		return "Monster type=" + type + ", hitPoints=" + hitPoints + ", attacks=" + Arrays.toString(attacks)
				+ ", attacksPoints=" + Arrays.toString(attacksPoints) + ", getType()=" + getType() + ", getHitPoints()="
				+ getHitPoints() + ", getAttacks()=" + Arrays.toString(getAttacks()) + ", getAttacksPoints()="
				+ Arrays.toString(getAttacksPoints()) + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	// Defining removeHitPoints functions
	public void removeHitPoints(int pointsToRemove) {
		this.hitPoints -= pointsToRemove;
		if (this.hitPoints < 0) {	// if hitPoints becomes negative then set to zero.
			this.hitPoints = 0;
		}
	}

	// Defining attack functions
	public boolean attack(String attack, Monster otherMonster) {
		boolean validAttack = false;
		for (String s : this.attacks) {
			if (s.equals(attack)) {
				validAttack = true;
				break;
			} else {
				validAttack = false;
			}
		}

		if (this == otherMonster) {
			return false;
		} else if ((this.getHitPoints() == 0) || (otherMonster.getHitPoints() == 0)) {
			return false;
		} else if (!validAttack) {
			return false;
		} else {
			int index = Arrays.asList(this.attacks).indexOf(attack);
			int attackValue = this.attacksPoints[index];
			otherMonster.removeHitPoints(attackValue);	// Remove hitPoints by calling removeHitPoints()
			return true;
		}
	}
}

