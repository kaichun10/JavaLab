package trainer;

import monster.Monster;
import monster.MonsterException;

import java.util.*;

public class Trainer {

	// Generating field
	private String name;
	private Set<Monster> monsters;

	// Initialising constructor
	public Trainer(String name) {
		this.name = name;
		this.monsters = new HashSet<>();
	}

	// Generating getter
	public String getName() {
		return this.name;
	}

	// Add monster to the set of monsters
	public boolean addMonster(Monster monster) {
		if (hasMonster(monster)) {
			return false;
		} else {
			this.monsters.add(monster);
			return true;
		}
	}

	// Remove monster to the set of monsters
	public boolean removeMonster(Monster monster) {
		if (hasMonster(monster)) {
			this.monsters.remove(monster);
			return true;
		} else {
			return false;
		}
	}

	// Check if a monster is present in the set of monsters
	public boolean hasMonster(Monster monster) {
		return (monsters.contains(monster));
	}

	// Returns the count of monsters in each category.
	public Map<String, Integer> countMonstersByType() {
		Map<String, Integer> table = new HashMap<String, Integer>();

		for (Monster item : monsters) {
			Integer num = table.get(item.getType());
			if (num == null) {
				num = 0;
			}
			table.put(item.getType(), num + 1);
		}
		System.out.println("Trainer [name=" + name + ", monsters=" + monsters + "]");

		return table;
	}

	// Override the built-in toString() method
	public String toString() {
		return "Trainer [name=" + name + ", monsters=" + monsters + "]";
	}
}
