import java.util.ArrayList;
import java.util.Stack;

public class EditProject extends Operation{
    private Project editedProject;
    private Project projectBeforeEditing;
    
    public EditProject(Project editedProject, Project projectBeforeEditing){
        this.editedProject = editedProject;
        this.projectBeforeEditing = projectBeforeEditing;
    }

    @Override
    public void undo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users,
            Stack<Operation> undone, Stack<Operation> done) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void redo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users,
            Stack<Operation> undone, Stack<Operation> done) {
        // TODO Auto-generated method stub
        
    }


}
