public interface UndoRedo {
    public void undo(StorageState state);
    public void redo(StorageState state);
}
