package es.projectalpha.ac.cooldowns;

public enum UTime {

	BEST, DAYS, HOURS, MINUTES, SECONDS;

	public static double trim(double untrimmeded, int decimal){
		return Double.parseDouble(Double.toString(untrimmeded).substring(0, Double.toString(untrimmeded).indexOf('.') + 2));
	}

	public static double convert(long time, UTime unit, int decPoint){
		if (unit == UTime.BEST) {
			if (time < 60000L) {
				unit = UTime.SECONDS;
			} else {
				if (time < 3600000L) {
					unit = UTime.MINUTES;
				} else {
					if (time < 86400000L) {
						unit = UTime.HOURS;
					} else {
						unit = UTime.DAYS;
					}
				}
			}
		}
		if (unit == UTime.SECONDS) {
			return trim(time / 1000.0D, decPoint);
		}
		if (unit == UTime.MINUTES) {
			return trim(time / 60000.0D, decPoint);
		}
		if (unit == UTime.HOURS) {
			return trim(time / 3600000.0D, decPoint);
		}
		if (unit == UTime.DAYS) {
			return trim(time / 86400000.0D, decPoint);
		}
		return trim(time, decPoint);
	}
}
