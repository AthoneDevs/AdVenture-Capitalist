package es.projectalpha.ac.utils;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class MoneyUtils {

    public static String parseMoney(double money){ //Display
        if (getDigits(money).size() < 15 && getDigits(money).size() >= 10){
            return Messages.CURRENCY + new DecimalFormat("#.###").format(money) + " Millions";
        }
        if (getDigits(money).size() >= 15){
            return Messages.CURRENCY + new DecimalFormat("#.###").format(money) + " Billions";
        }
        return Messages.CURRENCY + String.valueOf(money);
    }


    private static LinkedList<Double> getDigits(double number){
        LinkedList<Double> stack = new LinkedList<>();

        while (number > 0) {
            stack.push(number % 10 );
            number = number / 10;
        }
        return stack;
    }
}
