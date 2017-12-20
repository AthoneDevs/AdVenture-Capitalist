package es.projectalpha.ac.jobs.list;

import es.projectalpha.ac.jobs.Job;

public class Shrimp extends Job {

    public Shrimp(){
        super("Shrimp Boat", 1244160);

        setLevel(0);
        setProductionReward(622080);
        setProductionTime(96); //1:36

        setCoefficient(1.11);

        setUpgradeBase(getCost());
    }
}
