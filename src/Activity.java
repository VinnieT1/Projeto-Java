import java.util.ArrayList;

public class Activity {
    private String id;
    private String description;
    private String start;
    private String end;
    private ArrayList<Student> whoIsDoing;
    private ArrayList<String> duties;
    private Student leader;
    private Project ownerProject;

    public Activity(String id, String description, String start, String end, Student leader, Project project){
        this.id = id;
        this.description = description;
        this.start = start;
        this.end = end;
        this.leader = leader;
        this.ownerProject = project;
    }

    public Activity(){
        this.whoIsDoing = new ArrayList<Student>();
        this.duties = new ArrayList<String>();
    }

    public String getId() {
        return this.id;
    }

    public void copyActivityInfoFrom(Activity activity){
        this.id = activity.id;
        this.description = activity.description;
        this.start = activity.start;
        this.end = activity.end;

        this.whoIsDoing.clear();
        for (Student student : activity.whoIsDoing) {
            this.whoIsDoing.add(student);
        }
        
        this.duties.clear();
        for (String duty : activity.duties) {
            this.duties.add(duty);
        }
        
        this.leader = activity.leader;
        this.ownerProject = activity.ownerProject;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public ArrayList<Student> getWhoIsDoing() {
        return this.whoIsDoing;
    }

    public void setWhoIsDoing(ArrayList<Student> whoIsDoing) {
        this.whoIsDoing = whoIsDoing;
    }

    public ArrayList<String> getDuties() {
        return this.duties;
    }

    public void setDuties(ArrayList<String> duties) {
        this.duties = duties;
    }

    public Student getLeader() {
        return this.leader;
    }

    public void setLeader(Student leader) {
        this.leader = leader;
    }

    public Project getOwnerProject() {
        return this.ownerProject;
    }

    public void setOwnerProject(Project ownerProject) {
        this.ownerProject = ownerProject;
    }

    @Override
    public String toString(){
        return (
            "Identificacao: " + this.id +
            "\nDescricao: " + this.description +
            "\nResponsavel: " + this.leader.getName()
        );
    }

    public String report(){
        String result = (
            this.toString() +
            "\nInicio: " + this.start +
            "\nFim: " + this.end +
            "\nAlunos participando:"
        );
        for (Student student : this.whoIsDoing) {
            result.concat("\n\t" + student.toString());
        }
        result.concat("\nTarefas:");
        for (String duty : duties) {
            result.concat("\n\t" + duty);
        }
        result.concat("\nPertence ao projeto: " + this.ownerProject.getId());

        return result;
    }

    public RemoveActivityOperation remove(ArrayList<Activity> activities){
        for (Student student : this.whoIsDoing) {
            student.getActivitiesWorkedOn().remove(this);
        }
        this.leader.getActivitiesThatUserIsLeader().remove(this);
        this.ownerProject.getActivities().remove(this);

        RemoveActivityOperation removedActivityOperation = new RemoveActivityOperation(this);
        return removedActivityOperation;
    }

}
