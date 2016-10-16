package es.projectalpha.ac.modifiers;

public enum Upgrades{

	LEMONADE(250000, "Litte Umbrellas", 3), NEWS(500000, "Funny Pages", 3), CAR(1000000, "Drive Through Wash", 3), PIZZA(5000000, "Robot Cars", 3), DONUT(10000000, "Pre-Packaged Pastries", 3), BOAT(25000000, "Shrimp Satellite", 3), HOCKEY(500000000, "Team Jet", 3), MOVIE("10 Billion", "3D Cameras", 3), BANK("50 Billion", "Gold Plated Vaults", 3), OIL("250 Billion", "Spill Proof Tankers", 3), MONOPOLY("1 Trillon", "Monopoly", 3);

	private double cost;
	private String costS;
	private String name;
	private double increase; //x<number>

	Upgrades(String cost, String name, double increase){
		this.costS = cost;
		this.name = name;
		this.increase = increase;
	}

	Upgrades(double cost, String name, double increase){
		this.cost = cost;
		this.name = name;
		this.increase = increase;
	}

	public double getCost(){
		return this.cost;
	}

	public String getCostS(){
		return this.costS;
	}

	public String getName(){
		return this.name;
	}

	public double getIncrease(){
		return this.increase;
	}
}
