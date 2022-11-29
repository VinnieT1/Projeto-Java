public class EditActivityOperation extends Operation{
    private Activity editedActivity;
    private Activity activityBeforeEditing;

    public EditActivityOperation(Activity editedActivity, Activity activityBeforeEditing){
        this.editedActivity = editedActivity;
        this.activityBeforeEditing = activityBeforeEditing;
    }

    @Override
    public void undo(StorageState state) {
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
    public void redo(StorageState state) {
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
