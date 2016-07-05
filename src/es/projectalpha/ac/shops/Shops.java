package es.projectalpha.ac.shops;

public enum Shops {

	LEMONADE(2, 1), NEWS(60, 4), CAR(540, 6), PIZZA(4320, 10), DONUT(51840, 24), SHIP(622080, 96), HOCKEY(7464960,384), CINEMA(89579520, 1536), BANK(1074954240, 6144), OIL(29668737024.0D, 36864);

	private double rewardD;
	private double timer;
	private String rewardS;

	Shops(double reward, double timer){
		this.rewardD = reward;
		this.timer = timer;
	}

	Shops(String reward, double timer){
		this.rewardS = reward;
		this.timer = timer;
	}

	public double getReward(){
		return this.rewardD;
	}

	public double getRewardS(){
		return Double.parseDouble(this.rewardS);
	}

	public double getTimer(){
		return this.timer;
	}
}
