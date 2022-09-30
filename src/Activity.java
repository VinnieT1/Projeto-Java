import java.util.ArrayList;

public class Activity {
    private String id;
    private String description;
    private String start;
    private String end;
    private ArrayList<User> whoIsDoing;
    private ArrayList<String> duties;
    private User leader;
    private Project ownerProject;

    public String getId() {
        return this.id;
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

    public ArrayList<User> getWhoIsDoing() {
        return this.whoIsDoing;
    }

    public void setWhoIsDoing(ArrayList<User> whoIsDoing) {
        this.whoIsDoing = whoIsDoing;
    }

    public ArrayList<String> getDuties() {
        return this.duties;
    }

    public void setDuties(ArrayList<String> duties) {
        this.duties = duties;
    }

    public User getLeader() {
        return this.leader;
    }

    public void setLeader(User leader) {
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
            this.id + ": " + this.description
        );
    }
}
