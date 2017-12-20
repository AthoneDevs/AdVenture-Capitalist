package es.projectalpha.ac.jobs.list;

import es.projectalpha.ac.jobs.Job;

public class Donut extends Job {

    public Donut(){
        super("Donut Shop", 103680);

        setProductionReward(51840);
        setProductionTime(24);

        setCoefficient(1.12);

        setUpgradeBase(getCost());
    }
}
