package es.projectalpha.ac.jobs;

public class Oil extends Job {

    public Oil(int level){
        super("Oil Company", 25798901760D, level);

        setProductionReward(29668737024D);
        setProductionTime(36864); //10:14:24

        setCoefficient(1.07);

        setUpgradeBase(getCost());
    }
}
