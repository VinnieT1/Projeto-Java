import java.util.ArrayList;

public class User {
    public String name, type;
    public ArrayList<Project> projectsWorkedOn;
    public ArrayList<Activity> activitiesWorkedOn;
    public Account account;
    public boolean canBeCoordinator;

    public User(String name, String type){
        this.name = name;
        this.type = type;
        this.projectsWorkedOn = new ArrayList<Project>();
        this.activitiesWorkedOn = new ArrayList<Activity>();
        this.account = null;
        
        if (type.equals("professor") || type.equals("pesquisador")){
            this.canBeCoordinator = true;
        }
        else{
            this.canBeCoordinator = false;
        }
    }
}