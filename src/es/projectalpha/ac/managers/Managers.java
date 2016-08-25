package es.projectalpha.ac.managers;

import org.bukkit.Material;

import es.projectalpha.ac.shops.Shops;

public enum Managers {

	LEMONADE("Lemonade", "Lemonade Stand", "Cabe Johnson", 1000, 4, 17, Material.WATCH, Shops.LEMONADE), NEWS("News", "Newspaper", "Perry Black", 15000, -17, -9, Material.PAPER, Shops.NEWS), CAR("Car", "Car Wash", "W.W. Heisenbird", 100000, -14, 1, Material.MINECART, Shops.CAR), PIZZA("Pizza", "Pizza Delivery", "Mama Sean", 500000, -19, -18, Material.PUMPKIN_PIE, Shops.PIZZA), DONUT("Donut", "Donut Shop", "SrJonh", 1200000, -3, 20, Material.ENDER_PEARL, Shops.DONUT), BOAT("Boat", "Shrimp Boat", "Forest Trump", 10000000, 0, -18, Material.BOAT, Shops.BOAT), HOCKEY("Hockey", "Hockey Team", "Dawn Cheri", 111111111, 17, -5, Material.STICK, Shops.HOCKEY), MOVIE("Movie", "Movie Studio", "Stefani Speilburger", 555555555, 21, -16, Material.EYE_OF_ENDER, Shops.MOVIE), BANK("Bank", "Bank", "Cadox8", 10000000, 16, 17, Material.DIAMOND, Shops.BANK), OIL("Oil", "Oil Company", "Wikijito7", 1000000000, -19, 14, Material.LAVA, Shops.OIL);

	private String dataName;
	private String name;
	private String managerName;
	private double price;
	private int distX;
	private int distZ;
	private Material mat;
	private Shops s;

	Managers(String dataName, String name, String managerName, double price, int distX, int distZ, Material mat, Shops s) {
		this.dataName = dataName;
		this.name = name;
		this.managerName = managerName;
		this.price = price;
		this.distX = distX;
		this.distZ = distZ;
		this.mat = mat;
		this.s = s;
	}

	public String getdataName() {
		return this.dataName;
	}

	public String getName() {
		return this.name;
	}

	public String getManagerName() {
		return this.managerName;
	}

	public double getPrice() {
		return this.price;
	}

	public int getDistX() {
		return this.distX;
	}

	public int getDistZ() {
		return this.distZ;
	}

	public Material getMaterial() {
		return this.mat;
	}

	public Shops getShop() {
		return this.s;
	}
}
