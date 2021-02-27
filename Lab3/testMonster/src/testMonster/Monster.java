package testMonster;

import java.util.Arrays;

public class Monster {
	// fields
	private String type;
	private int hitPoints;
	private String[] attacks;
	private int[] attacksPoints;

	// constructor
	public Monster(String type, int hitPoints, String[] attacks, int[] attacksPoints) {
		// super(); //Question: what is super();? what if there is no parent function?
		this.type = type;
		this.hitPoints = hitPoints;
		this.attacks = attacks;
		this.attacksPoints = attacksPoints;
	}

	// getter & setter
	public String getType() {
		return this.type;// Question: should you return type or this.type? return type is imported by
							// default
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
		return "Monster [type=" + type + ", hitPoints=" + hitPoints + ", attacks=" + Arrays.toString(attacks)
				+ ", attacksPoints=" + Arrays.toString(attacksPoints) + ", getType()=" + getType() + ", getHitPoints()="
				+ getHitPoints() + ", getAttacks()=" + Arrays.toString(getAttacks()) + ", getAttacksPoints()="
				+ Arrays.toString(getAttacksPoints()) + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	// main functions
	public void removeHitPoints(int pointsToRemove) {
		this.hitPoints -= pointsToRemove;
		if (this.hitPoints < 0) {
			this.hitPoints = 0;
		}
	}

	public boolean attack(String attack, Monster otherMonster) {
		boolean validAttack = false;
		for (String s : this.attacks) {
			//System.out.println("s-> "+s);//
			//System.out.println("attack-> "+attack);//
			if (s.equals(attack)) {
				validAttack = true;
				break;
			} else {
				validAttack = false;
			}
		}

		if (this == otherMonster) {
			//System.out.println("self");//
			return false;
		} else if ((this.getHitPoints() == 0) || (otherMonster.getHitPoints() == 0)) {
			//System.out.println("no hp");//
			return false;
		} else if (!validAttack) {
			//System.out.println("valid attack");//
			return false;
		} else {
			int index = Arrays.asList(this.attacks).indexOf(attack);
			int attackValue = this.attacksPoints[index];
			otherMonster.removeHitPoints(attackValue);
			return true;
		}
	}
}
