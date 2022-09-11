public class Operation {
    public int operationType;
}

//operationType == 1:
class ProjectOperation extends Operation{
    public int projectOperationType;
}

//projectOperationType == 1:
class CreateProject extends ProjectOperation{
    public Project createdProject;

    public CreateProject(Project createdProject){
        this.createdProject = createdProject;
        this.operationType = 1;
        this.projectOperationType = 1;
    }
}

//projectOperationType == 2:
class RemoveProject extends ProjectOperation{
    public Project projectBeforeRemoving;

    public RemoveProject(Project projectBeforeRemoving){
        this.projectBeforeRemoving = projectBeforeRemoving;
        this.operationType = 1;
        this.projectOperationType = 2;
    }
}

//projectOperationType == 3:
class EditProject extends ProjectOperation{
    public Project editedProject;
    public Project projectBeforeEditing;

    public EditProject(Project editedProject, Project projectBeforeEditing){
        this.editedProject = editedProject;
        this.projectBeforeEditing = projectBeforeEditing;
        this.operationType = 1;
        this.projectOperationType = 3;
    }
}

//operationType == 2:
class ActivityOperation extends Operation{
    public int activityOperationType;
}

//activityOperationType == 1:
class CreateActivity extends ActivityOperation{
    public Activity createdActivity;

    public CreateActivity(Activity createdActivity){
        this.createdActivity = createdActivity;
        this.operationType = 2;
        this.activityOperationType = 1;
    }
}

//activityOperationType == 2:
class RemoveActivity extends ActivityOperation{
    public Activity activityBeforeRemoving;

    public RemoveActivity(Activity activityBeforeRemoving){
        this.activityBeforeRemoving = activityBeforeRemoving;
        this.operationType = 2;
        this.activityOperationType = 2;
    }
}

//activityOperationType == 3:
class EditActivity extends ActivityOperation{
    public Activity activityBeforeEditing;
    public Activity editedActivity;

    public EditActivity(Activity editedActivity, Activity activityBeforeEditing){
        this.activityBeforeEditing = activityBeforeEditing;
        this.editedActivity = editedActivity;
        this.operationType = 2;
        this.activityOperationType = 3;
    }
}

//operationType == 3:
class UserOperation extends Operation{
    public int userOperationType;
}

//userOperationType == 1:
class CreateUser extends UserOperation{
    public User createdUser;

    public CreateUser(User createdUser){
        this.createdUser = createdUser;
        this.operationType = 3;
        this.userOperationType = 1;
    }
}

//userOperationType == 2:
class RemoveUser extends UserOperation{
    public User removedUser;

    public RemoveUser(User removedUser){
        this.removedUser = removedUser;
        this.operationType = 3;
        this.userOperationType = 2;
    }
}

//userOperationType == 3:
class EditUser extends UserOperation{
    public User userBeforeEditing;
    public User editedUser;

    public EditUser(User editedUser, User userBeforeEditing){
        this.userBeforeEditing = userBeforeEditing;
        this.editedUser = editedUser;
        this.operationType = 3;
        this.userOperationType = 3;
    }
}