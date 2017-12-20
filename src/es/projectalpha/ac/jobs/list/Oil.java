package es.projectalpha.ac.jobs.list;

import es.projectalpha.ac.jobs.Job;

public class Oil extends Job {

    public Oil(){
        super("Oil Company", 25798901760D);

        setProductionReward(29668737024D);
        setProductionTime(36864); //10:14:24

        setCoefficient(1.07);

        setUpgradeBase(getCost());
    }
}
