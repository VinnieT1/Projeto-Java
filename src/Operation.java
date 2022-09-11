public class Operation {
    public int operationType;
    public int secondaryOperationType;

    public Project createdProject;
    public Project removedProject;
    public Project editedProject;
    public Project projectBeforeEditing;

    public Activity createdActivity;
    public Activity removedActivity;
    public Activity activityBeforeEditing;
    public Activity editedActivity;

    public User createdUser;
    public User removedUser;
    public User userBeforeEditing;
    public User editedUser;

    public Operation(int secondaryOperationType, Project project){
        if (secondaryOperationType == 1){
            this.createdProject = project;
            this.operationType = 1;
            this.secondaryOperationType = 1;
        }
        else if (secondaryOperationType == 2){
            this.removedProject = project;
            this.operationType = 1;
            this.secondaryOperationType = 2;
        }
    }

    public Operation(Project editedProject, Project projectBeforeEditing){
        this.editedProject = editedProject;
        this.projectBeforeEditing = projectBeforeEditing;
        this.operationType = 1;
        this.secondaryOperationType = 3;
    }

    public Operation(int secondaryOperationType, Activity activity){
        if (secondaryOperationType == 1){
            this.createdActivity = activity;
            this.operationType = 2;
            this.secondaryOperationType = 1;
        }
        else if (secondaryOperationType == 2){
            this.removedActivity = activity;
            this.operationType = 2;
            this.secondaryOperationType = 2;
        }
    }

    public Operation(Activity editedActivity, Activity activityBeforeEditing){
        this.activityBeforeEditing = activityBeforeEditing;
        this.editedActivity = editedActivity;
        this.operationType = 2;
        this.secondaryOperationType = 3;
    }

    public Operation(int secondaryOperationType, User user){
        if (secondaryOperationType == 1){
            this.createdUser = user;
            this.operationType = 3;
            this.secondaryOperationType = 1;
        }
        else if (secondaryOperationType == 2){
            this.removedUser = user;
            this.operationType = 3;
            this.secondaryOperationType = 2;
        }
    }

    public Operation(User editedUser, User userBeforeEditing){
        this.userBeforeEditing = userBeforeEditing;
        this.editedUser = editedUser;
        this.operationType = 3;
        this.secondaryOperationType = 3;
    }
}