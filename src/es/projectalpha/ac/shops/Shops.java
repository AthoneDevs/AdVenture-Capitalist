package es.projectalpha.ac.shops;

import org.bukkit.Material;

public enum Shops {

	LEMONADE(2, 1, Material.SOUL_SAND), NEWS(60, 4, Material.DIAMOND_ORE), CAR(540, 6, Material.REDSTONE_ORE), PIZZA(4320, 10, Material.LAPIS_ORE), DONUT(51840, 24, Material.NETHERRACK), BOATS(622080, 96, Material.COAL_ORE), HOCKEY(7464960, 384, Material.IRON_ORE), MOVIE(89579520, 1536, Material.ENDER_STONE), BANKS(1074954240, 6144, Material.GOLD_ORE), OIL(29668737024.0D, 36864, Material.EMERALD_ORE);

	private double reward;
	private double timer;
	private double price;
	private Material mat;

	Shops(double reward, double timer, Material mat){
		this.reward = reward;
		this.timer = timer;
		this.mat = mat;
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
}
