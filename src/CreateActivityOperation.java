public class CreateActivityOperation extends Operation{
    private Activity createdActivity;

    public CreateActivityOperation(Activity createdActivity){
        this.createdActivity = createdActivity;
    }

    @Override
    public void undo(StorageState state) {
        this.createdActivity.getOwnerProject().getActivities().remove(this.createdActivity);
        this.createdActivity.getLeader().getActivitiesThatUserIsLeader().remove(this.createdActivity);
        state.getActivities().remove(this.createdActivity);
    }

    @Override
    public void redo(StorageState state) {
        state.getActivities().add(createdActivity);
        this.createdActivity.getLeader().getActivitiesThatUserIsLeader().add(this.createdActivity);
        this.createdActivity.getOwnerProject().getActivities().add(this.createdActivity);
    }
    
}
