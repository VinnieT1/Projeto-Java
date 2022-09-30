import java.util.ArrayList;
import java.util.Stack;

public class CreateProject extends Operation{
    private Project createdProject;

    public CreateProject(Project createdProject){
        this.createdProject = createdProject;
    }

    public void undo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users, Stack<Operation> undone, Stack<Operation> done){
        createdProject.getCoordinator().getProjectsThatUserIsCoordinator().remove(createdProject);
        projects.remove(createdProject);
        undone.push(this);
    }

    public void redo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users, Stack<Operation> undone, Stack<Operation> done){
        // TODO
    }
}