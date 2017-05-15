package es.projectalpha.ac.jobs.list;

import es.projectalpha.ac.jobs.Job;

public class Movie extends Job {

    public Movie(int level){
        super("Movie Studio", 179159040, level);

        setProductionReward(89579520);
        setProductionTime(1536); //25:36

        setCoefficient(1.09);

        setUpgradeBase(getCost());
    }
}
