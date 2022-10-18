import java.util.ArrayList;

public class RemoveProjectOperation extends Operation{
    private Project removedProject;

    public RemoveProjectOperation(Project removedProject){
        this.removedProject = removedProject;
    }
    
    public void undo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users){
        this.removedProject.undoRemove(projects);
    }
    public void redo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users){
        this.removedProject.remove(projects);
    }
}
