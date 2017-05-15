package es.projectalpha.ac.jobs.list;

import es.projectalpha.ac.jobs.Job;

public class Lemonade extends Job {

    public Lemonade(int level){
        super("Lemonade Stand", 0, level);

        setProductionReward(1);
        setProductionTime(0.6);

        setCoefficient(1.07);

        setUpgradeBase(4);
    }
}
