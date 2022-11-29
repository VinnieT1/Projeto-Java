public class RemoveActivityOperation extends Operation{
    private Activity removedActivity;

    public RemoveActivityOperation(Activity removedActivity){
        this.removedActivity = removedActivity;
    }

    @Override
    public void undo(StorageState state) {
        state.getActivities().add(this.removedActivity);
        this.removedActivity.getLeader().getActivitiesThatUserIsLeader().add(this.removedActivity);
        this.removedActivity.getOwnerProject().getActivities().add(this.removedActivity);

        for (Student student : this.removedActivity.getWhoIsDoing()) {
            student.getActivitiesWorkedOn().add(this.removedActivity);
        }

        this.removedActivity.getLeader().getActivitiesThatUserIsLeader().add(this.removedActivity);
    }

    @Override
    public void redo(StorageState state) {
        state.getActivities().remove(this.removedActivity);
        this.removedActivity.getLeader().getActivitiesThatUserIsLeader().remove(this.removedActivity);
        this.removedActivity.getOwnerProject().getActivities().remove(this.removedActivity);

        for (Student student : this.removedActivity.getWhoIsDoing()) {
            student.getActivitiesWorkedOn().remove(removedActivity);
        }

        this.removedActivity.getLeader().getActivitiesThatUserIsLeader().remove(this.removedActivity);
    }
}