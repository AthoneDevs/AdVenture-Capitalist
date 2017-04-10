package es.projectalpha.ac.api;

public class AVCEco {

    private AVCUser user;
    private AVCUser.UserData userData;

    public AVCEco(AVCUser user){
        this.user = user;
        this.userData = user.getUserData();
    }

    public void addMoney(double amount){
        setMoney(getMoney() + amount);
    }

    public void remMoney(double amount){
        setMoney(getMoney() - amount);
    }

    public void setMoney(double amount){
        userData.setMoney(amount);
        user.save();
        if (getMoney() < 0) setMoney(0);
    }

    public double getMoney(){
        return userData.getMoney();
    }
}
