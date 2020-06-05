package es.projectalpha.ac.ui;

import es.projectalpha.ac.api.AVCUser;

public class DefUI {

    private UIMaker uimaker;
    private AVCUser user;

    public DefUI(AVCUser user) {
        this.user = user;

        uimaker = new UIMaker("AdVenture-Capitalist", 45).basedOnJobs(user);


    }
}
