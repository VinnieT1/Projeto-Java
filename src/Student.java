import java.util.ArrayList;

public class Student extends User{
    private ArrayList<Project> projectsWorkedOn; //Student
    private ArrayList<Project> projectsThatUserWasLentTo; //Student
    private ArrayList<Activity> activitiesWorkedOn; //Student
    private ArrayList<Activity> activitiesThatUserIsLeader; //Student
    private ArrayList<Double> salaries;

    public Student(String name, String type){
        super(name, type);
        this.projectsWorkedOn = new ArrayList<Project>();
        this.projectsThatUserWasLentTo = new ArrayList<Project>();
        this.activitiesWorkedOn = new ArrayList<Activity>();
        this.activitiesThatUserIsLeader = new ArrayList<Activity>();
        this.salaries = new ArrayList<Double>();
    }

    public Student(){
        super();
    }

    public ArrayList<Double> getSalaries(){
        return this.salaries;
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

    @Override
    public void remove(ArrayList<User> users) {
        for (Project project : this.projectsWorkedOn) {
            int idx = project.getPeopleOnProject().indexOf(this);
            project.getPeopleOnProject().remove(idx);
            project.getSalary().remove(idx);
        }
        for (Project project : this.projectsThatUserWasLentTo) {
            project.getBorrowedUsers().remove(this);
        }
        for (Activity activity : this.activitiesThatUserIsLeader) {
            activity.setLeader(null);
        }
        for (Activity activity : this.activitiesWorkedOn) {
            activity.getWhoIsDoing().remove(this);
        }
        users.remove(this);
    }

    @Override
    public void undoRemove(ArrayList<User> users) {
        for (Project project : this.projectsWorkedOn) {
            int idx = this.projectsWorkedOn.indexOf(project);
            project.getPeopleOnProject().add(this);
            project.getSalary().add(this.salaries.get(idx));
        }
        for (Project project : this.projectsThatUserWasLentTo) {
            project.getBorrowedUsers().add(this);
        }
        for (Activity activity : this.activitiesThatUserIsLeader) {
            activity.setLeader(this);
        }
        for (Activity activity : this.activitiesWorkedOn) {
            activity.getWhoIsDoing().add(this);
        }
        users.add(this);
    }

    public void copyUserInfoFrom(User student){
        super.setName(student.getName());
        super.setType(student.getType());
        super.setAccount(student.getAccount());

        this.projectsWorkedOn.clear();
        for (Project project : ((Student)student).projectsWorkedOn) {
            this.projectsWorkedOn.add(project);
        }
        this.projectsThatUserWasLentTo.clear();
        for (Project project : ((Student)student).projectsThatUserWasLentTo) {
            this.projectsThatUserWasLentTo.add(project);
        }
        this.activitiesWorkedOn.clear();
        for (Activity activity : ((Student)student).activitiesWorkedOn) {
            this.activitiesWorkedOn.add(activity);
        }
        this.activitiesThatUserIsLeader.clear();
        for (Activity activity : ((Student)student).activitiesThatUserIsLeader) {
            this.activitiesThatUserIsLeader.add(activity);
        }
    }

    @Override
    public String toString(){
        return (
            this.getName() +
            ", " + this.getType()
        );
    }
}
