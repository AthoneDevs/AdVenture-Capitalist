package es.projectalpha.ac.managers;

import lombok.Getter;
import lombok.Setter;

public class Manager {

    @Getter private String name;

    @Getter @Setter private boolean hired;

    public Manager(String name){
        this.name = name;

        setHired(false);
    }
}
