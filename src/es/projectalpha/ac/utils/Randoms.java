package es.projectalpha.ac.utils;

import java.util.Random;

public class Randoms {

	public static long getVillagerID(){
		Random r = new Random();

		long n = r.nextLong();

		return n;
	}
}
