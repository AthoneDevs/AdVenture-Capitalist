package es.projectalpha.ac.jobs;

import es.projectalpha.ac.api.AVCUser;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JobManager {

    @Getter private HashMap<AVCUser, List<Job>> jobsPerUser;

    public JobManager(){
        jobsPerUser = new HashMap<>();
    }

    //Storage Utils
    public void addJobToUser(AVCUser user, Job... jobs){
        if (!existUser(user)) jobsPerUser.put(user, new ArrayList<>());
        List<Job> list = getUserJobs(user);
        for (Job job : jobs){
            if (!list.contains(job)) list.add(job);
        }
        jobsPerUser.put(user, list);
    }

    public void removeJobToUser(AVCUser user, Job... jobs){
        if (!existUser(user)) return;
        List<Job> list = getUserJobs(user);
        for (Job job : jobs){
            if (list.contains(job)) list.remove(job);
        }
        if (list.isEmpty()) {
            removeUser(user);
            return;
        }
        jobsPerUser.put(user, list);
    }

    public void removeUser(AVCUser user){
        if (existUser(user)) {
            jobsPerUser.remove(user);
            removeUser(user); //Security
        }
    }

    public List<Job> getUserJobs(AVCUser user){
        return jobsPerUser.get(user);
    }

    public boolean existUser(AVCUser user){
        return jobsPerUser.containsKey(user);
    }
}
