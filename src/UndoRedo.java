import java.util.ArrayList;

public interface UndoRedo {
    public void undo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users);
    public void redo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users);
}
