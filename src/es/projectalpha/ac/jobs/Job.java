package es.projectalpha.ac.jobs;

import es.projectalpha.ac.api.AVCEco;
import es.projectalpha.ac.api.AVCUser;
import es.projectalpha.ac.managers.Manager;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

public class Job {

    //From http://adventure-capitalist.wikia.com/wiki/Businesses

    @Getter private String name;
    @Getter private double cost;

    @Getter @Setter private int level;
    @Getter @Setter private double productionTime; //Seconds
    @Getter @Setter private double productionReward;

    @Getter @Setter private double coefficient; //Coefficient to know next upgrade to next level

    @Getter @Setter private double upgradeBase;  //Money to upgrade to next level

    @Getter @Setter private Manager manager; //Manager who controls the job (when hired)

    @Setter private boolean working; //Checks if it's producting

    /**
     * Info: to upgrade shop to level 2, it costs the base * coefficient
     */

    public Job(String name, double cost){
        this.name = name;
        this.cost = cost;

        setLevel(0);
        setProductionTime(0);
        setProductionReward(getCost());

        setCoefficient(0);

        setUpgradeBase(getCost());

        setManager(null);

        setWorking(false);
    }

    public void pay(AVCUser u){
        if (!isWorking()) return;
        new AVCEco(u).addMoney(getProductionReward() * level);
        setWorking(false);

        if (manager != null && manager.isHired()){
            activeJob();
        }
    }

    public void upgrade(AVCUser u){
        AVCEco eco = new AVCEco(u);
        if (eco.getMoney() >= getUpgradeBase() * getCoefficient()){
            setLevel(getLevel() + 1);
            eco.remMoney(getUpgradeBase() * getCoefficient());
        }
    }

    public void activeJob(){
        setWorking(true);
    }


    public boolean isWorking() {
        return working && level != 0;
    }


    public double ajustTime(){
        if (level == 1) return productionTime;
        return productionTime - (level * 0.6);
    }

    public double moneyToUpgrade(){
        DecimalFormat df = new DecimalFormat("#.##");
        double money = getUpgradeBase();

        for (int x = 1; x <= getLevel(); x++){
            money = Double.parseDouble(df.format(money * getCoefficient()));
        }
        return money;
    }
}
