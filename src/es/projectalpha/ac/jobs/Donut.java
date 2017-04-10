package es.projectalpha.ac.jobs;

public class Donut extends Job {

    public Donut(int level){
        super("Donut Shop", 103680, level);

        setProductionReward(51840);
        setProductionTime(24);

        setCoefficient(1.12);

        setUpgradeBase(getCost());
    }
}
