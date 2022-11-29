public class CreateProjectOperation extends Operation{
    private Project createdProject;

    public CreateProjectOperation(Project createdProject){
        this.createdProject = createdProject;
    }

    public void undo(StorageState state){
        createdProject.getCoordinator().getProjectsThatUserIsCoordinator().remove(this.createdProject);
        state.getProjects().remove(this.createdProject);
    }

    public void redo(StorageState state){
        createdProject.getCoordinator().getProjectsThatUserIsCoordinator().add(this.createdProject);
        state.getProjects().add(this.createdProject);
    }
}