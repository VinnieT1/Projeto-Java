import java.util.ArrayList;

public class EditActivityOperation extends Operation{
    private Activity editedActivity;
    private Activity activityBeforeEditing;

    public EditActivityOperation(Activity editedActivity, Activity activityBeforeEditing){
        this.editedActivity = editedActivity;
        this.activityBeforeEditing = activityBeforeEditing;
    }

    @Override
    public void undo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users) {
        Activity editedActivityAux = new Activity();
        editedActivityAux.copyActivityInfoFrom(this.editedActivity);

        for (Student student : this.editedActivity.getWhoIsDoing()) {
            student.getActivitiesWorkedOn().remove(this.editedActivity);
        }
        this.editedActivity.copyActivityInfoFrom(this.activityBeforeEditing);
        for (Student student : this.editedActivity.getWhoIsDoing()) {
            student.getActivitiesWorkedOn().add(this.editedActivity);
        }
        
        this.activityBeforeEditing = this.editedActivity;
        this.editedActivity = editedActivityAux;
    }

    @Override
    public void redo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users) {
        Activity activityBeforeEditingAux = new Activity();
        activityBeforeEditingAux.copyActivityInfoFrom(this.activityBeforeEditing);

        for (Student student : this.activityBeforeEditing.getWhoIsDoing()) {
            student.getActivitiesWorkedOn().remove(this.activityBeforeEditing);
        }
        this.activityBeforeEditing.copyActivityInfoFrom(this.editedActivity);
        for (Student student : this.activityBeforeEditing.getWhoIsDoing()) {
            student.getActivitiesWorkedOn().add(this.activityBeforeEditing);
        }

        this.editedActivity = this.activityBeforeEditing;
        this.activityBeforeEditing = activityBeforeEditingAux;
    }
    
}
