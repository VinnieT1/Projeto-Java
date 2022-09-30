import java.util.ArrayList;
import java.util.Stack;

public interface UndoRedo {
    public void undo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users, Stack<Operation> undone, Stack<Operation> done);
    public void redo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users, Stack<Operation> undone, Stack<Operation> done);
}
