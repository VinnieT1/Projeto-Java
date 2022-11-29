import java.util.ArrayList;

public class StorageState {
    private ArrayList<Project> projects;
    private ArrayList<Activity> activities;
    private ArrayList<User> users;

    public StorageState(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users){
        this.projects = projects;
        this.activities = activities;
        this.users = users;
    }

    public ArrayList<Project> getProjects(){
        return this.projects;
    }

    public ArrayList<Activity> getActivities(){
        return this.activities;
    }

    public ArrayList<User> getUsers(){
        return this.users;
    }
}
