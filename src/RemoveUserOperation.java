import java.util.ArrayList;

public class RemoveUserOperation extends Operation{
    private User removedUser;

    public RemoveUserOperation(User removedUser){
        this.removedUser = removedUser;
    } 

    @Override
    public void undo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users) {
        this.removedUser.undoRemove(users);
    }

    @Override
    public void redo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users) {
        this.removedUser.remove(users);
    }
}
