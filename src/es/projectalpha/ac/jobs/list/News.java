package es.projectalpha.ac.jobs.list;

import es.projectalpha.ac.jobs.Job;

public class News extends Job {

    public News(){
        super("Newspaper Delivery", 60);

        setProductionReward(getCost());
        setProductionTime(3);

        setCoefficient(1.15);

        setUpgradeBase(getCost());
    }
}
