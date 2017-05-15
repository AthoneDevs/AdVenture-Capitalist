package es.projectalpha.ac.jobs.list;

import es.projectalpha.ac.jobs.Job;

public class Hockey extends Job {

    public Hockey(int level){
        super("Hockey Team", 14929920, level);

        setProductionReward(7464960);
        setProductionTime(384); //6:24

        setCoefficient(1.1);

        setUpgradeBase(getCost());
    }
}
