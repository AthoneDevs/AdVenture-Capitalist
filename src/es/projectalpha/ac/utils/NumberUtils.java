package es.projectalpha.ac.utils;

public class NumberUtils {

	public static double getMillions(String amount){
		return Double.parseDouble(amount) * 1000000;
	}

	public static double getBillions(String amount){
		return getMillions(amount) * 1000;
	}

	public static double getTrillions(String amount){
		return getBillions(amount) * 1000;
	}
}
