import java.util.ArrayList;

public class Activity {
    public String id, description, start, end;
    public ArrayList<User> whoIsDoing;
    public ArrayList<String> duties;
    public User leader;
    public Project ownerProject;

    public Activity(String id, String description, String start, String end, User leader, Project ownerProject){
        this.id = id;
        this.description = description;
        this.start = start;
        this.end = end;
        this.leader = leader;
        this.duties = new ArrayList<String>();
        this.whoIsDoing = new ArrayList<User>();
        this.ownerProject = ownerProject;
    }
}