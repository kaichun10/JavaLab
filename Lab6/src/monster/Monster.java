package monster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

/**
 * Sample solution to JP2 lab 5 2019. Represents a monster in a battling game.
 * 
 * @author mefoster
 *
 */
public abstract class Monster implements Comparable<Monster> {

	/** The type */
	protected String type;
	/** Current hit points */
	protected int hitPoints;
	/** List of attacks */
	protected Attack[] attacks;

	/**
	 * Creates a new Monster with the given properties.
	 * 
	 * @param type      The type to use
	 * @param hitPoints The initial hit points
	 * @param attacks   The list of attacks
	 */
	public Monster(String type, int hitPoints, Attack[] attacks) {
		this.type = type;
		this.hitPoints = hitPoints;
		this.attacks = attacks;
	}

	/**
	 * Returns the current hit points of this Monster.
	 * 
	 * @return The current hit points
	 */
	public int getHitPoints() {
		return hitPoints;
	}

	/**
	 * Returns the type of this Monster.
	 * 
	 * @return The monster type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Returns the list of attacks available to this Monster.
	 * 
	 * @return The list of attacks
	 */
	public Attack[] getAttacks() {
		return attacks;
	}

	/**
	 * Helper method to find the points for the given attack.
	 * 
	 * @param attack The attack to look for
	 * @return The corresponding points, or -1 if the attack is not found
	 */
	private int getAttackPoints(String attackName) throws MonsterException {
		for (Attack attack : attacks) {
			if (attack.getName().equals(attackName)) {
				return attack.getPoints();
			}
		}
		throw new MonsterException("Invalid attack name: " + attackName);
	}

	/**
	 * Attacks the given other monster. An attack fails if otherMonster is equal to
	 * this monster, if either this Monster or otherMonster is knocked out, or if
	 * the given attack name is not valid. If the attack succeeds, the corresponding
	 * hit points are removed from otherMonster; if it fails, no changes are made to
	 * either Monster.
	 * 
	 * @param attack       The attack to use
	 * @param otherMonster The Monster to attack
	 */
	public void attack(String attack, Monster otherMonster) throws MonsterException {
		// A monster cannot attack itself
		if (otherMonster == this) {
			throw new MonsterException("A monster cannot attack itself");
		}

		// A monster cannot attack or be attacked if it is knocked out
		if (this.hitPoints <= 0) {
			throw new MonsterException("Attacking monster is knocked out");
		}
		if (otherMonster.hitPoints <= 0) {
			throw new MonsterException("Attacked monster is knocked out");
		}

		int pointsToRemove = getAttackPoints(attack);
		// Check if the other monster has dodged
		if (!otherMonster.dodge()) {
			// Find the attack -- if it exists, use it and return true, otherwise
			// do nothing and return false
			otherMonster.removeHitPoints(pointsToRemove);
		} else {
			this.removeHitPoints(10);
		}
	}

	/**
	 * An abstract method used when a monster might dodge in battle.
	 * 
	 * @return Whether the monster dodges
	 */
	protected abstract boolean dodge();

	/**
	 * Removes the given hit points from the current monster. If the hit points
	 * would go below zero, it is set to zero.
	 * 
	 * @param points The number of points to remove
	 */
	public void removeHitPoints(int points) {
		this.hitPoints -= points;
		if (hitPoints <= 0) {
			// Monster is knocked out
			hitPoints = 0;
		}
	}

	/**
	 * Returns a nice String representation of this Monster.
	 */
	public String toString() {
		return "Monster [type=" + type + ", hitPoints=" + hitPoints + ", attacks=" + Arrays.toString(attacks);
	}

	/**
	 * Returns a string containing the name of the attack and the point of attack
	 * for all attack inside a monster separated by space
	 * 
	 * @return a string of the attackName and attackPoints separated by space
	 */
	public String getAttackList() {
		List<String> li = new ArrayList<>();
		for (Attack a : attacks) {
			li.add(a.getName());
			li.add(String.valueOf(a.getPoints()));
		}
		return String.join(" ", li);
	}

	/**
	 * Compare the current monster with another monster from input parameter. First
	 * compare hitPoints, if two monsters have the same hit points points then sort
	 * in alphabetical order based on type
	 * 
	 * @param otherMonster the other monster to compare with the current monster
	 */
	public int compareTo(Monster otherMonster) {
		int value = Integer.compare(otherMonster.hitPoints, this.hitPoints);

		if (value == 0) {
			return this.type.compareTo(otherMonster.type);
		} else {
			return value;
		}

	}

}
