package es.projectalpha.ac.jobs.list;

import es.projectalpha.ac.jobs.Job;

public class Bank extends Job {

    public Bank(int level){
        super("Bank", 2149908480D, level);

        setProductionReward(1074954240);
        setProductionTime(6144); //1:42:24

        setCoefficient(1.08);

        setUpgradeBase(getCost());
    }
}
