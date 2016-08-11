package es.projectalpha.ac.shops;

import org.bukkit.Material;

public enum Shops {

	LEMONADE(4, 1, 0.6, Material.SOUL_SAND, 1.07, 3.738), NEWS(60, 60, 3, Material.DIAMOND_ORE, 1.15, 0), CAR(720, 540, 6, Material.REDSTONE_ORE, 1.14, 0), PIZZA(8640, 4320, 12, Material.LAPIS_ORE, 1.13, 0), DONUT(103680, 51840, 24, Material.NETHERRACK, 1.12, 0), BOAT(1244160, 622080, 96, Material.COAL_ORE, 1.11, 0), HOCKEY(14929920, 7464960, 384, Material.IRON_ORE, 1.10, 0), MOVIE(179159040, 89579520, 1536, Material.ENDER_STONE, 1.09, 0), BANK(2149908480D, 1074954240, 6144, Material.GOLD_ORE, 1.08, 0), OIL(25798901760D, 29668737024D, 36864, Material.EMERALD_ORE, 1.07, 0);

	private double reward;
	private double timer;
	private double price;
	private Material mat;
	private double coefficient;
	private double buyBase;

	Shops(double price, double reward, double timer, Material mat, double coefficient, double buyBase){
		this.price = price;
		this.reward = reward;
		this.timer = timer;
		this.mat = mat;
		this.coefficient = coefficient;
		this.buyBase = buyBase;
	}

	public double getReward(){
		return this.reward;
	}

	public double getTimer(){
		return this.timer;
	}

	public double getPrice(){
		return this.price;
	}

	public Material getMaterial(){
		return this.mat;
	}

	public double getCoefficient(){
		return this.coefficient;
	}

	public double getBuyBase(){
		return this.buyBase;
	}
}
