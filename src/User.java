import java.util.ArrayList;

public class User {
    public String name, type;
    public ArrayList<Project> projectsWorkedOn;
    public ArrayList<Project> projectsThatUserWasLentTo;
    public ArrayList<Project> projectsthatUserIsCoordinator;
    public ArrayList<Activity> activitiesWorkedOn;
    public ArrayList<Activity> activitiesThatUserIsLeader;
    public Account account;
    public boolean canBeCoordinator;

    public void copyUserInfoFrom(User user){
        this.name = user.name;
        this.type = user.type;
        this.canBeCoordinator = user.canBeCoordinator;
        this.account = user.account;

        this.projectsWorkedOn.clear();
        for (Project project : user.projectsWorkedOn) {
            this.projectsWorkedOn.add(project);
        }
        this.projectsThatUserWasLentTo.clear();
        for (Project project : user.projectsThatUserWasLentTo) {
            this.projectsThatUserWasLentTo.add(project);
        }
        this.projectsthatUserIsCoordinator.clear();
        for (Project project : user.projectsthatUserIsCoordinator) {
            this.projectsthatUserIsCoordinator.add(project);
        }
        this.activitiesWorkedOn.clear();
        for (Activity activity : user.activitiesWorkedOn) {
            this.activitiesWorkedOn.add(activity);
        }
        this.activitiesThatUserIsLeader.clear();
        for (Activity activity : user.activitiesThatUserIsLeader) {
            this.activitiesThatUserIsLeader.add(activity);
        }
    }

    public User(String name, String type){
        this.name = name;
        this.type = type;
        this.projectsWorkedOn = new ArrayList<Project>();
        this.projectsthatUserIsCoordinator = new ArrayList<Project>();
        this.activitiesWorkedOn = new ArrayList<Activity>();
        this.activitiesThatUserIsLeader = new ArrayList<Activity>();
        this.projectsThatUserWasLentTo = new ArrayList<Project>();
        this.account = null;
        
        if (type.equals("professor") || type.equals("pesquisador")){
            this.canBeCoordinator = true;
        }
        else{
            this.canBeCoordinator = false;
        }
    }
}