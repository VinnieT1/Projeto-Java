import java.util.ArrayList;

public class EditProjectOperation extends Operation{
    private Project editedProject;
    private Project projectBeforeEditing;
    
    public EditProjectOperation(Project editedProject, Project projectBeforeEditing){
        this.editedProject = editedProject;
        this.projectBeforeEditing = projectBeforeEditing;
    }

    @Override
    public void undo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users) {
        Project editedProjectAux = new Project();
        editedProjectAux.copyProjectInfoFrom(this.editedProject);

        for (Student student : this.editedProject.getBorrowedUsers()) {
            student.getProjectsThatUserWasLentTo().remove(this.editedProject);
            this.editedProject.getBorrowedUsers().remove(student);
        }
        for (Student student : this.editedProject.getPeopleOnProject()) {
            int idx = student.getProjectsWorkedOn().indexOf(this.editedProject);
            student.getProjectsWorkedOn().remove(idx);
            student.getSalaries().remove(idx);
            idx = this.editedProject.getPeopleOnProject().indexOf(student);
            this.editedProject.getPeopleOnProject().remove(idx);
            this.editedProject.getSalary().remove(idx);
        }
        this.editedProject.copyProjectInfoFrom(this.projectBeforeEditing);
        for (Student student : this.editedProject.getPeopleOnProject()) {
            int idx = this.editedProject.getPeopleOnProject().indexOf(student);
            student.getProjectsWorkedOn().add(this.editedProject);
            student.getSalaries().add(this.editedProject.getSalary().get(idx));
        }
        for (Student student : this.editedProject.getBorrowedUsers()) {
            student.getProjectsThatUserWasLentTo().add(this.editedProject);
        }
        this.projectBeforeEditing = this.editedProject;
        this.editedProject = editedProjectAux;
    }
    @Override
    public void redo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users) {
        Project projectBeforeEditingAux = new Project();
        projectBeforeEditingAux.copyProjectInfoFrom(this.projectBeforeEditing);

        for (Student student : this.projectBeforeEditing.getBorrowedUsers()) {
            student.getProjectsThatUserWasLentTo().remove(this.projectBeforeEditing);
            this.projectBeforeEditing.getBorrowedUsers().remove(student);
        }
        for (Student student : this.projectBeforeEditing.getPeopleOnProject()) {
            int idx = student.getProjectsWorkedOn().indexOf(this.projectBeforeEditing);
            student.getProjectsWorkedOn().remove(idx);
            student.getSalaries().remove(idx);
            idx = this.projectBeforeEditing.getPeopleOnProject().indexOf(student);
            this.projectBeforeEditing.getPeopleOnProject().remove(idx);
            this.projectBeforeEditing.getSalary().remove(idx);
        }
        this.projectBeforeEditing.copyProjectInfoFrom(this.editedProject);
        for (Student student : this.projectBeforeEditing.getPeopleOnProject()) {
            int idx = this.projectBeforeEditing.getPeopleOnProject().indexOf(student);
            student.getProjectsWorkedOn().add(this.projectBeforeEditing);
            student.getSalaries().add(this.projectBeforeEditing.getSalary().get(idx));
        }
        for (Student student : this.projectBeforeEditing.getBorrowedUsers()) {
            student.getProjectsThatUserWasLentTo().add(this.projectBeforeEditing);
        }

        this.editedProject = this.projectBeforeEditing;
        this.projectBeforeEditing = projectBeforeEditingAux;
    }
}
