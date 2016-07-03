package es.projectalpha.ac.shops;

public enum Shops {

	LEMONADE(2, 1), NEWS(60, 4), CAR(540, 6), PIZZA("Test", 10);

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
