public class RemoveUserOperation extends Operation{
    private User removedUser;

    public RemoveUserOperation(User removedUser){
        this.removedUser = removedUser;
    } 

    @Override
    public void undo(StorageState state) {
        this.removedUser.undoRemove(state.getUsers());
    }

    @Override
    public void redo(StorageState state) {
        this.removedUser.remove(state.getUsers());
    }
}
