package testMonster;

public class testMonster {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Monster moltres = new Monster("Fire", 114, new String[] { "Fire Spin", "Overheat" }, new int[] { 14, 160 });
		Monster articuno = new Monster("Ice", 112, new String[] { "Frost Breath", "Ice Beam" }, new int[] { 10, 90 });
		Monster zapdos = new Monster("Electric", 119, new String[] { "Charge Beam", "Thunder" }, new int[] { 8, 100 });
		System.out.println(moltres);
		System.out.println(articuno);
		System.out.println(zapdos);
		System.out.println(moltres.attack("Fire Spin", articuno)); // true
		System.out.println(moltres.getHitPoints()); // 114
		System.out.println(articuno.getHitPoints()); // 98
		System.out.println(moltres.attack("Overheat", zapdos)); // true
		System.out.println(zapdos.getHitPoints()); // 0
		System.out.println(moltres.attack("Bad Attack", articuno)); // false
		System.out.println(articuno.getHitPoints()); // 98
		System.out.println(articuno.attack("Ice Beam", articuno)); // false
		System.out.println(articuno.getHitPoints()); // 98
		System.out.println(zapdos.attack("Thunder", moltres)); // false
	}

}
