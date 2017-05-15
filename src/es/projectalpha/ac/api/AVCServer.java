package es.projectalpha.ac.api;

import lombok.Getter;

import java.util.ArrayList;

public class AVCServer {

    @Getter private ArrayList<AVCUser> users;

    public AVCServer(){
        users = new ArrayList<>();
    }


}
