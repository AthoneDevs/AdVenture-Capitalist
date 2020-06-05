package es.projectalpha.ac.excs;

public class JobNotLoadedException extends Exception {

    public JobNotLoadedException() {
        super("One or more jobs have not been loaded");
    }

    public JobNotLoadedException(String msg) {
        super(msg);
    }
}
