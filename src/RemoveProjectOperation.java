public class RemoveProjectOperation extends Operation{
    private Project removedProject;

    public RemoveProjectOperation(Project removedProject){
        this.removedProject = removedProject;
    }
    
    public void undo(StorageState state){
        this.removedProject.undoRemove(state.getProjects());
    }
    public void redo(StorageState state){
        this.removedProject.remove(state.getProjects());
    }
}
