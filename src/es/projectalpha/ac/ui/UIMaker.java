package es.projectalpha.ac.ui;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.api.AVCUser;
import es.projectalpha.ac.jobs.Job;
import es.projectalpha.ac.utils.Utils;
import lombok.Getter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class UIMaker {

    private AVC plugin = AVC.getInstance();

    private Inventory inv;
    @Getter private String name;
    @Getter private int size;

    public UIMaker(String name, int size) {
        this.name = Utils.colorize(name);
        this.size = size;

        inv = plugin.getServer().createInventory(null, size, this.name);
    }

    public UIMaker addItems(ItemStack... items) {
        inv.addItem(items);
        return this;
    }

    public UIMaker addItems(HashMap<Integer, ItemStack> items) {
        items.keySet().forEach(i -> inv.setItem(i, items.get(i)));
        return this;
    }

    public UIMaker basedOnJobs(AVCUser user) {
        List<Job> jobs = user.getUserData().getJobs();



        return this;
    }



    public Inventory getInventory() {
        return inv;
    }
}
