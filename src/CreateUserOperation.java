import java.util.ArrayList;

public class CreateUserOperation extends Operation{
    private User createdUser;

    public CreateUserOperation(User createdUser){
        this.createdUser = createdUser;
    }

    @Override
    public void undo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users) {
        users.remove(this.createdUser);
    }

    @Override
    public void redo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users) {
        users.add(this.createdUser);
    }
    
}
