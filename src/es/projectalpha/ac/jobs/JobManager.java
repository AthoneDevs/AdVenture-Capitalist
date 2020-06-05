package es.projectalpha.ac.jobs;

import es.projectalpha.ac.excs.JobNotLoadedException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class JobManager {

    @Getter private ArrayList<Job> jobs;

    @Getter @Setter private final int MAX_JOBS = 10;

    public JobManager() {
        jobs = new ArrayList<>();
    }

    public void registerJobs(Job... job) throws JobNotLoadedException {
        for (Job j : job) if (!jobs.contains(j)) jobs.add(j);

        if (jobs.size() != MAX_JOBS) throw new JobNotLoadedException();
    }
}
