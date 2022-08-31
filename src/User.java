import java.util.ArrayList;

public class User {
    public String name, type;
    public ArrayList<Project> projectsWorkedOn;

    public User(String name, String type){
        this.name = name;
        this.type = type;
        this.projectsWorkedOn = new ArrayList<Project>();
    }
}