package trainer;

import monster.Monster;
import monster.MonsterException;

import java.util.*;

public class Trade {

	// Generating field
	private Trainer trainer1;
	private Trainer trainer2;
	private Monster monster1;
	private Monster monster2;

	// Initialising constructor
	// Throw exception if trainer does not have its monster
	public Trade(Trainer trainer1, Monster monster1, Trainer trainer2, Monster monster2) throws MonsterException {
		this.trainer1 = trainer1;
		this.trainer2 = trainer2;
		this.monster1 = monster1;
		this.monster2 = monster2;

		if (!(trainer1.hasMonster(monster1))) {
			throw new MonsterException("trainer1 does not have this monster");
		}
		if (!(trainer2.hasMonster(monster2))) {
			throw new MonsterException("trainer2 does not have this monster");
		}

	}

	// Transfer monster between trainers
	public void doTrade() {
		trainer1.addMonster(monster2);
		trainer2.addMonster(monster1);
		trainer2.removeMonster(monster2);
		trainer1.removeMonster(monster1);
	}

}
