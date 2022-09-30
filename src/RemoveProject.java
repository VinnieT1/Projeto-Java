import java.util.ArrayList;
import java.util.Stack;

public class RemoveProject extends Operation{
    private Project removedProject;

    public RemoveProject(Project removedProject){
        this.removedProject = removedProject;
    }
    
    public void undo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users, Stack<Operation> undone, Stack<Operation> done){
        // TODO
    }
    public void redo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users, Stack<Operation> undone, Stack<Operation> done){
        done.push(this.removedProject.remove(projects));
    }
}
