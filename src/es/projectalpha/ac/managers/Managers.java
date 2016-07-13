package es.projectalpha.ac.managers;

import org.bukkit.Material;

public enum Managers {

	LEMONADE("Lemonade", "Lemonade Stand", "Cabe Johnson", 1000, 4, 17, Material.WATCH), NEWS("News", "Newspaper", "Perry Black", 15000, -19, -10, Material.PAPER), CAR("Car", "Car Wash", "W.W. Heisenbird", 100000, -13, 1, Material.MINECART), PIZZA("Pizza", "Pizza Delivery", "Mama Sean", 500000, -20, -19, Material.PUMPKIN_PIE), DONUT("Donut", "Donut Shop", "SrJonh", 1200000, -8, 20, Material.ENDER_PEARL), BOAT("Boat", "Shrimp Boat", "Forest Trump", 10000000, -1, -19, Material.BOAT), HOCKEY("Hockey", "Hockey Team", "Dawn Cheri", 111111111, 17, -6, Material.STICK), MOVIE("Movie", "Movie Studio", "Stefani Speilburger", 555555555, 0, 0, Material.EYE_OF_ENDER), BANK("Bank", "Bank", "Cadox8", 10000000, 16, 17, Material.DIAMOND), OIL("Oil", "Oil Company", "Wikijito7", 1000000000, 20, 14, Material.LAVA);

	//Movie Loc = ?
	//All Locs must change to put Movie

	private String dataName;
	private String name;
	private String managerName;
	private double prize;
	private int distX;
	private int distZ;
	private Material mat;

	Managers(String dataName, String name, String managerName, double prize, int distX, int distZ, Material mat){
		this.dataName = dataName;
		this.name = name;
		this.managerName = managerName;
		this.prize = prize;
		this.distX = distX;
		this.distZ = distZ;
		this.mat = mat;
	}

	public String getdataName(){
		return this.dataName;
	}

	public String getName(){
		return this.name;
	}

	public String getManagerName(){
		return this.managerName;
	}

	public double getPrize(){
		return this.prize;
	}

	public int getDistX(){
		return this.distX;
	}

	public int getDistZ(){
		return this.distZ;
	}

	public Material getMaterial(){
		return this.mat;
	}
}
