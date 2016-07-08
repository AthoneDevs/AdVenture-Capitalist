package es.projectalpha.ac.managers;

import org.bukkit.Material;

public enum Managers {

	LEMONADE("Lemonade", "Lemonade Stand", "Cabe Johnson", 1000, Material.WATCH), NEWS("News", "Newspaper", "Perry Black", 15000, Material.PAPER), CAR("Car", "Car Wash", "W.W. Heisenbird", 100000, Material.MINECART), PIZZA("Pizza", "Pizza Delivery", "Mama Sean", 500000, Material.PUMPKIN_PIE), DONUT("Donut", "Donut Shop", "SrJonh", 1200000, Material.ENDER_PEARL), BOAT("Boat", "Shrimp Boat", "Forest Trump", 10000000, Material.BOAT), HOCKEY("Hockey", "Hockey Team", "Dawn Cheri", 111111111, Material.STICK), MOVIE("Movie", "Movie Studio", "Stefani Speilburger", 555555555, Material.EYE_OF_ENDER), BANK("Bank", "Bank", "Cadox8", 10000000, Material.DIAMOND), OIL("Oil", "Oil Company", "Wikijito7", 1000000000, Material.LAVA);

	private String dataName;
	private String name;
	private String managerName;
	private double prize;
	private Material mat;

	Managers(String dataName, String name, String managerName, double prize, Material mat){
		this.dataName = dataName;
		this.name = name;
		this.managerName = managerName;
		this.prize = prize;
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

	public Material getMaterial(){
		return this.mat;
	}
}
