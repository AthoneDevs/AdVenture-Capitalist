package es.projectalpha.ac.achievements;

public enum Achievements {

	START("The Begining", "Congrats, you created your own Company :D"), LEMONADE("Lemonade for all"), BREAK("Rebel", "You can't break blocks :D"), DONUTS("Do you want a donut's box, Policeman?", 15), PC("New Computer?", "You can buy a new computer with that money :D"), LIFE("What are you doing with your life...?", "Plase, stop playing this");

	private final String dispName;
	private final String message;
	private final double reward;

	Achievements(String dispName, String message, double reward){
		this.dispName = dispName;
		this.message = message;
		this.reward = reward;
	}

	Achievements(String dispName, String message){
		this.dispName = dispName;
		this.message = message;
		this.reward = 0.0D;
	}

	Achievements(String dispName){
		this.dispName = dispName;
		this.message = "";
		this.reward = 0.0D;
	}

	Achievements(String dispName, double reward){
		this.dispName = dispName;
		this.message = "";
		this.reward = reward;
	}

	public String getDispName(){
		return dispName;
	}

	public String getMessage(){
		return message;
	}

	public double getReward(){
		return reward;
	}
}
