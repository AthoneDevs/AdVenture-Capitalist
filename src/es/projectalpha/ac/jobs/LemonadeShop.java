package es.projectalpha.ac.jobs;

public class LemonadeShop extends Job {

    public LemonadeShop(){
        super("Lemonade Stand", 0);

        setLevel(1);
        setProductionReward(1);
        setProductionTime(0.6);

        setCoefficient(1.07);

        setUpgradeBase(4);
    }
}
