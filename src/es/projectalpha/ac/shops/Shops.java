package es.projectalpha.ac.shops;

public enum Shops {

	LEMONADE(2, 1), NEWS(60, 4), CAR(540, 6);

	private double reward;
	private double timer;

	Shops(double reward, double timer){
		this.reward = reward;
		this.timer = timer;
	}

	public double getReward(){
		return this.reward;
	}

	public double getTimer(){
		return this.timer;
	}
}
