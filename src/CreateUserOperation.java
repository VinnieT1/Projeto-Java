public class CreateUserOperation extends Operation{
    private User createdUser;

    public CreateUserOperation(User createdUser){
        this.createdUser = createdUser;
    }

    @Override
    public void undo(StorageState state) {
        state.getUsers().remove(this.createdUser);
    }

    @Override
    public void redo(StorageState state) {
        state.getUsers().add(this.createdUser);
    }
    
}
