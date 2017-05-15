package es.projectalpha.ac.ui;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.api.AVCUser;
import es.projectalpha.ac.jobs.Job;
import lombok.NonNull;
import org.bukkit.inventory.Inventory;

public class JobUI {

    private AVC plugin = AVC.getInstance();

    private Job job;
    private AVCUser user;

    public JobUI(@NonNull Job job, @NonNull AVCUser user){
        this.job = job;
        this.user = user;
    }

    public void openUI(){
        Inventory inv = plugin.getServer().createInventory(null, 9, job.getName());
        /* ITEMS
        Owned / to buy
        Quantity, Reward
        Buy more
        Manager
        */




        user.openInventory(inv);
    }
}
