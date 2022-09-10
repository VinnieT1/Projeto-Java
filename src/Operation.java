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
    }
}

//projectOperationType == 2:
class RemoveProject extends ProjectOperation{
    public Project projectBeforeRemoving;

    public RemoveProject(Project projectBeforeRemoving){
        this.projectBeforeRemoving = projectBeforeRemoving;
    }
}

//projectOperationType == 3:
class EditProject extends ProjectOperation{
    public Project editedProject;
    public Project projectBeforeEditing;

    public EditProject(Project editedProject, Project projectBeforeEditing){
        this.editedProject = editedProject;
        this.projectBeforeEditing = new Project(editedProject.id, editedProject.description, editedProject.start, editedProject.end, editedProject.coordinator);
    }
}

//operationType == 2:
class ActivityOperation extends Operation{
    public int activityOperationType;
}

//operationType == 3:
class UserOperation extends Operation{
    public int userOperationType;
}