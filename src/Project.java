import java.util.ArrayList;

public class Project {
    public String id, description, start, end, status;
    public User coordinator;
    public ArrayList<User> peopleOnProject;
    public ArrayList<Activity> activities;
    public ArrayList<Double> salary;

    public Project(String id, String description, String start, String end, User coordinator){
        this.id = id;
        this.description = description;
        this.start = start;
        this.end = end;
        this.status = "Em processo de criacao";
        this.coordinator = coordinator;
        this.peopleOnProject = new ArrayList<User>();
        this.activities = new ArrayList<Activity>();
        this.salary = new ArrayList<Double>();
    }
}