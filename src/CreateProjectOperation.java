import java.util.ArrayList;

public class CreateProjectOperation extends Operation{
    private Project createdProject;

    public CreateProjectOperation(Project createdProject){
        this.createdProject = createdProject;
    }

    public void undo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users){
        createdProject.getCoordinator().getProjectsThatUserIsCoordinator().remove(this.createdProject);
        projects.remove(this.createdProject);
    }

    public void redo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users){
        createdProject.getCoordinator().getProjectsThatUserIsCoordinator().add(this.createdProject);
        projects.add(this.createdProject);
    }
}