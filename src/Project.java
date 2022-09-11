import java.util.ArrayList;

public class Project {
    public String id, description, start, end, status;
    public User coordinator;
    public ArrayList<User> peopleOnProject;
    public ArrayList<Activity> activities;
    public ArrayList<Double> salary;
    public ArrayList<User> borrowedUsers;
    public int paymentPeriod;

    public Project(String id, String description, String start, String end, User coordinator, int paymentPeriod){
        this.id = id;
        this.description = description;
        this.start = start;
        this.end = end;
        this.status = "Em processo de criacao";
        this.coordinator = coordinator;
        this.peopleOnProject = new ArrayList<User>();
        this.activities = new ArrayList<Activity>();
        this.salary = new ArrayList<Double>();
        this.borrowedUsers = new ArrayList<User>();
        this.paymentPeriod = paymentPeriod;
    }

    public boolean isReadyToInitiate(){
        return (!this.start.equals("none") && this.end.equals("none") && this.status.equals("Em processo de criacao"));
    }

    public void initiateProject(){
        this.status = "Iniciado";
    }

    public void goForward(){
        this.status = "Em andamento";
    }

    public boolean canBeCompleted(){
        return !this.activities.isEmpty();
    }

    public void complete(){
        this.status = "Concluido";
    }

    public void copyProjectInfoFrom(Project project){
        this.id = project.id;
        this.description = project.description;
        this.start = project.start;
        this.end = project.end;
        this.status = project.status;
        this.coordinator = project.coordinator;
        this.paymentPeriod = project.paymentPeriod;

        this.activities.clear();
        for (Activity activity : project.activities) {
            this.activities.add(activity);
        }
        this.peopleOnProject.clear();
        for (User user : project.peopleOnProject) {
            this.peopleOnProject.add(user);
        }
        this.salary.clear();
        for (Double salary : project.salary) {
            this.salary.add(salary);
        }
        this.borrowedUsers.clear();
        for (User user : project.borrowedUsers) {
            this.borrowedUsers.add(user);
        }
    }
}