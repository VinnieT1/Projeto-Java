
import java.util.ArrayList;

public class Student extends User{
    private ArrayList<Project> projectsWorkedOn; //Student
    private ArrayList<Project> projectsThatUserWasLentTo; //Student
    private ArrayList<Activity> activitiesWorkedOn; //Student
    private ArrayList<Activity> activitiesThatUserIsLeader; //Student

    public Student(String name, String type){
        super(name, type);
        this.projectsWorkedOn = new ArrayList<Project>();
        this.projectsThatUserWasLentTo = new ArrayList<Project>();
        this.activitiesWorkedOn = new ArrayList<Activity>();
        this.activitiesThatUserIsLeader = new ArrayList<Activity>();
    }

    public ArrayList<Project> getProjectsWorkedOn() {
        return this.projectsWorkedOn;
    }

    public void setProjectsWorkedOn(ArrayList<Project> projectsWorkedOn) {
        this.projectsWorkedOn = projectsWorkedOn;
    }

    public ArrayList<Project> getProjectsThatUserWasLentTo() {
        return this.projectsThatUserWasLentTo;
    }

    public void setProjectsThatUserWasLentTo(ArrayList<Project> projectsThatUserWasLentTo) {
        this.projectsThatUserWasLentTo = projectsThatUserWasLentTo;
    }

    public ArrayList<Activity> getActivitiesWorkedOn() {
        return this.activitiesWorkedOn;
    }

    public void setActivitiesWorkedOn(ArrayList<Activity> activitiesWorkedOn) {
        this.activitiesWorkedOn = activitiesWorkedOn;
    }

    public ArrayList<Activity> getActivitiesThatUserIsLeader() {
        return this.activitiesThatUserIsLeader;
    }

    public void setActivitiesThatUserIsLeader(ArrayList<Activity> activitiesThatUserIsLeader) {
        this.activitiesThatUserIsLeader = activitiesThatUserIsLeader;
    }
}
