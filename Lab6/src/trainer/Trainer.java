package trainer;

import java.util.Collection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.nio.*;
import java.io.*;

import monster.Attack;
import monster.ElectricMonster;
import monster.FireMonster;
import monster.Monster;
import monster.WaterMonster;

public class Trainer {
	/** The name of the trainer */
	private String name;
	/** The set of monsters */
	private Set<Monster> monsters;

	/** Constructor */
	public Trainer(String name) {
		this.name = name;
		this.monsters = new HashSet<>();
	}

	/**
	 * Count each type of monster that the trainer has
	 */
	public Map<String, Integer> countMonstersByType() {
		Map<String, Integer> monsterMap = new HashMap<>();
		for (Monster m : monsters) {
			if (monsterMap.get(m.getType()) == null) {
				monsterMap.put(m.getType(), 1);
			} else {
				monsterMap.put(m.getType(), monsterMap.get(m.getType()) + 1);
			}
		}
		return monsterMap;
	}

	public boolean hasMonster(Monster monster) {
		return monsters.contains(monster);
	}

	public String getName() {
		return name;
	}

	public boolean removeMonster(Monster monster) {
		return monsters.remove(monster);
	}

	public boolean addMonster(Monster monster) {
		return monsters.add(monster);
	}

	public String toString() {
		return name + ": " + monsters;
	}

	public Collection<Monster> getMonsters() {
		return monsters;
	}

	/*
	 * This instance method save the current Trainer to a file at a given location
	 * and if the action is not possible it throws an exception.
	 * 
	 * This function write the name of the trainer to the file first and then it
	 * creates a list that contains the type of monster and hitPoints then calls the
	 * getAttackList function in Monster class and in the end join them together to
	 * a string separated with space and write to the file
	 * 
	 * @param the name of the file to store the trainer object
	 */
	public void saveToFile(String filename) throws IOException {
		Path p1 = Paths.get(filename);
		try {
			Files.createFile(p1);
			PrintWriter pw = new PrintWriter(Files.newBufferedWriter(p1));

			pw.println(this.name);
			for (Monster m : monsters) {
				List<String> li = new ArrayList<>();
				li.add(m.getType());
				li.add(String.valueOf(m.getHitPoints()));
				li.add(m.getAttackList());
				String str = String.join(" ", li);
				pw.println(str);
			}
			pw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * This static method load a Trainer object from a given file that was created
	 * with the saveToFile() method and return the loaded Trainer and if an error
	 * occurs an exception is thrown
	 * 
	 * This function first load the name of the trainer from file and then it split
	 * the remaining lines by space and store them in a array of string.
	 * 
	 * Then the getAttackSet method is called to return a array of Attack, in the
	 * end monsters were generated and filled with data from the given file
	 * 
	 * @return the initialised trainer filled with monsters
	 */
	public static Trainer loadFromFile(String filename) throws IOException {
		Path p1 = Paths.get(filename);

		try {
			List<String> f = Files.readAllLines(p1);

			// Get the first item of the list which is the name of the trainer
			String firstElement = null;
			if (!f.isEmpty() && f.size() > 0) {
				firstElement = f.get(0);
			}

			// Remove the first item from the list which is the name of the trainer
			f.remove(0);

			// Initialise a trainer object and pass the name of the trainer as argument
			Trainer person = new Trainer(firstElement);

			for (String trainerString : f) {
				// Convert string to array of strings which contain type, hitPoints, and array
				// of Attack
				String[] fields = trainerString.split(" ");

				// Convert the array to array list
				List<String> fieldList = Arrays.asList(fields);

				// Create a sub array list of strings that contains the array of attacks
				ArrayList<String> attackList = new ArrayList<String>(fieldList.subList(2, fieldList.size()));

				// Joins the array of attacks to a single string, each attack object is
				// separated by space character
				String str = String.join(" ", attackList);

				// Call the getAttackSet method which recovers the Attack array
				Attack[] attackArray = getAttackSet(str);

				// Create corresponding monster base on the type (i.e. fields[0]) and initialise
				// them with corresponding hitPoints and array of attacks that was loaded from
				// the given file.
				String type = fields[0];
				int hitPoints = Integer.parseInt(fields[1]);
				Monster monster;
				if (type.equals("Fire")) {
					monster = new FireMonster(hitPoints, attackArray);
					person.addMonster(monster);
				} else if (type.equals("Water")) {
					monster = new WaterMonster(hitPoints, attackArray);
					person.addMonster(monster);
				} else if (type.equals("Electric")) {
					monster = new ElectricMonster(hitPoints, attackArray);
					person.addMonster(monster);
				}
			}
			return person;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/*
	 * This function take a single string of attackName and attackPoints and then
	 * split by space character and store them in an array of string. In the end, it
	 * convert the array of string to an array of Attack object by parsing through
	 * the string array.
	 * 
	 * @return an array of Attack object split from a single string
	 */
	private static Attack[] getAttackSet(String str) {
		String[] li = str.split(" ");
		ArrayList<Attack> set = new ArrayList<Attack>();

		for (int i = 0; i < li.length; i = i + 2) {
			Attack a = new Attack(li[i], Integer.parseInt(li[i + 1]));
			set.add(a);
		}
		Attack[] attackList = set.toArray(new Attack[set.size()]);
		return attackList;
	}

}
