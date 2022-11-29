public class EditUserOperation extends Operation{
    private User editedUser;
    private User userBeforeEditing;

    public EditUserOperation(User editedUser, User userBeforeEditing){
        this.editedUser = editedUser;
        this.userBeforeEditing = userBeforeEditing;
    }

    @Override
    public void undo(StorageState state) {
        if (this.editedUser instanceof Student){
            Student editedUserAux = new Student();
            editedUserAux.copyUserInfoFrom(this.editedUser);

            for (Activity activity : ((Student)this.editedUser).getActivitiesWorkedOn()) {
                activity.getWhoIsDoing().remove((Student)this.editedUser);
            }
            for (Activity activity : ((Student)this.editedUser).getActivitiesThatUserIsLeader()) {
                activity.setLeader(null);
            }
            
            this.editedUser.copyUserInfoFrom(userBeforeEditing);

            for (Activity activity : ((Student)this.editedUser).getActivitiesWorkedOn()) {
                activity.getWhoIsDoing().add((Student)this.editedUser);
            }
            for (Activity activity : ((Student)this.editedUser).getActivitiesThatUserIsLeader()) {
                activity.setLeader((Student)this.editedUser);
            }

            this.userBeforeEditing = this.editedUser;
            this.editedUser = editedUserAux;
        }
        else{
            Coordinator editedUserAux = new Coordinator();
            editedUserAux.copyUserInfoFrom(this.editedUser);

            this.editedUser.copyUserInfoFrom(this.userBeforeEditing);

            this.userBeforeEditing = this.editedUser;
            this.editedUser = editedUserAux;
        }
    }

    @Override
    public void redo(StorageState state) {
        if (this.userBeforeEditing instanceof Student){
            Student userBeforeEditingAux = new Student();
            userBeforeEditingAux.copyUserInfoFrom(this.userBeforeEditing);

            for (Activity activity : ((Student)this.userBeforeEditing).getActivitiesWorkedOn()) {
                activity.getWhoIsDoing().remove((Student)this.userBeforeEditing);
            }
            for (Activity activity : ((Student)this.userBeforeEditing).getActivitiesThatUserIsLeader()) {
                activity.setLeader(null);
            }
            
            this.userBeforeEditing.copyUserInfoFrom(editedUser);

            for (Activity activity : ((Student)this.userBeforeEditing).getActivitiesWorkedOn()) {
                activity.getWhoIsDoing().add((Student)this.userBeforeEditing);
            }
            for (Activity activity : ((Student)this.userBeforeEditing).getActivitiesThatUserIsLeader()) {
                activity.setLeader((Student)this.userBeforeEditing);
            }

            this.editedUser = this.userBeforeEditing;
            this.userBeforeEditing = userBeforeEditingAux;
        }
        else{
            Coordinator userBeforeEditingAux = new Coordinator();
            userBeforeEditingAux.copyUserInfoFrom(this.userBeforeEditing);

            this.userBeforeEditing.copyUserInfoFrom(this.editedUser);

            this.editedUser = this.userBeforeEditing;
            this.userBeforeEditing = userBeforeEditingAux;
        }
    }
}
