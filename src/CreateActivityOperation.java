import java.util.ArrayList;

public class CreateActivityOperation extends Operation{
    private Activity createdActivity;

    public CreateActivityOperation(Activity createdActivity){
        this.createdActivity = createdActivity;
    }

    @Override
    public void undo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users) {
        this.createdActivity.getOwnerProject().getActivities().remove(this.createdActivity);
        this.createdActivity.getLeader().getActivitiesThatUserIsLeader().remove(this.createdActivity);
        activities.remove(this.createdActivity);
    }

    @Override
    public void redo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users) {
        activities.add(createdActivity);
        this.createdActivity.getLeader().getActivitiesThatUserIsLeader().add(this.createdActivity);
        this.createdActivity.getOwnerProject().getActivities().add(this.createdActivity);
    }
    
}
