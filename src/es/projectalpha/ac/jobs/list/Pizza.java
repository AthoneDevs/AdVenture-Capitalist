package es.projectalpha.ac.jobs.list;

import es.projectalpha.ac.jobs.Job;

public class Pizza extends Job {

    public Pizza(int level){
        super("Pizza Delivery", 8640, level);

        setProductionReward(4320);
        setProductionTime(12);

        setCoefficient(1.13);

        setUpgradeBase(getCost());
    }
}
