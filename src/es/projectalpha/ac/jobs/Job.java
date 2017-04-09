package es.projectalpha.ac.jobs;

import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

public class Job {

    //From http://adventure-capitalist.wikia.com/wiki/Businesses

    @Getter private String name;
    @Getter private double cost;

    @Getter @Setter private int level;
    @Getter @Setter private double productionTime;
    @Getter @Setter private double productionReward;

    @Getter @Setter private double coefficient; //Coefficient to know next upgrade to next level

    @Getter @Setter private double upgradeBase;  //Money to upgrade to next level

    public Job(String name, double cost){
        this.name = name;
        this.cost = cost;

        setLevel(0);
        setProductionTime(0);
        setProductionReward(0);
        setCoefficient(0);
        setUpgradeBase(0);
    }

    public double ajustTime(){
        if (level == 1) return productionTime;
        return productionTime - (level * 0.6);
    }

    public double moneyToUpgrade(){
        DecimalFormat df = new DecimalFormat("#.##");
        double money = getUpgradeBase();

        for (int x = 0; x < getLevel(); x++){
            money = Double.parseDouble(df.format(money * getCoefficient()));
        }
        return money;
    }
}
