package es.projectalpha.ac.jobs.list;

import es.projectalpha.ac.jobs.Job;

public class News extends Job {

    public News(int level){
        super("Newspaper Delivery", 60, level);

        setProductionReward(getCost());
        setProductionTime(3);

        setCoefficient(1.15);

        setUpgradeBase(getCost());
    }
}
