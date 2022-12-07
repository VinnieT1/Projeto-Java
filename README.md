<img src="/assets/java-logo.png" alt="drawing" width="200"/>

# Projeto-Java
Projeto Java da matéria de Projeto de Software, matéria do curso de Engenharia de Computação e Ciência da Computação, IC/UFAL.

## Informações:
**Aluno**:
* Vinícius Teixeira Pereira Ramos

**Professor**:
* Baldoino Fonseca dos Santos Neto

## Patterns:
Implementação dos Code Patterns a fim de resolver Code Smells. É possível verificar as diferenças dessa branch com a "Exceptions".

### Introduce Parameter Object:

#### Antes, era necessário passar sempre 3 objetos que representavam o estado do projeto. Logo, foi possível implementar o padrão Introduce Parameter Object, de modo a agrupar esses 3 objetos em um só Objeto da classe StorageState:
```java
public class StorageState {
    private ArrayList<Project> projects;
    private ArrayList<Activity> activities;
    private ArrayList<User> users;

    public StorageState(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users){
        this.projects = projects;
        this.activities = activities;
        this.users = users;
    }

    public ArrayList<Project> getProjects(){
        return this.projects;
    }

    public ArrayList<Activity> getActivities(){
        return this.activities;
    }

    public ArrayList<User> getUsers(){
        return this.users;
    }
}
```
* [LINK](https://github.com/VinnieT1/Projeto-Java/blob/Patterns/src/StorageState.java)

---

### Chain Constructor:

#### Na classe Account, foi utilizado o padrão Chain Constructor para evitar duplicação de código.
```java
public class Account {
    private String username;
    private String password;
    private User accountOwner;

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, User accountOwner){
        this(username, password);
        this.accountOwner = accountOwner;
    }
}
```
* [LINK](https://github.com/VinnieT1/Projeto-Java/blob/Patterns/src/Account.java)

---

### Replace Data Value with Object:

#### Na classe Project, o pagamento das bolsas era feito somente com o atributo ``paymentPeriod``, de modo que, caso o coordenador tivesse mais de um projeto, o pagamento de bolsas não aconteceria de modo independente entre os projetos. Nessa perspectiva, o atributo ``lastPaymentRecieved``, da classe Date, foi introduzido a fim de operar com mais facilidade o pagamento das bolsas.
```java
public class Project {
	private int paymentPeriod;
	private Date lastPaymentRecieved;

	public void makePayments(Date now){
		long nowInMiliseconds = now.getTime();
		long lastPaymentRecievedInMiliseconds = this.lastPaymentRecieved.getTime();
		int daysSinceLastPayment = (int)(nowInMiliseconds/(1000*60*60*24) - lastPaymentRecievedInMiliseconds/(1000*60*60*24));

		if (daysSinceLastPayment >= this.getPaymentPeriod()){
			this.setLastPaymentRecieved(now);
		}

		for (int i = 0; i < this.peopleOnProject.size(); i++) {
			System.out.println("Pagando R$" + this.salary.get(i) + " para " + this.peopleOnProject.get(i));
		}
	}

}
```
* [LINK](https://github.com/VinnieT1/Projeto-Java/blob/Patterns/src/Project.java)

---

### Bridge:

#### Foram criadas 9 subclasses da classe abstrata Operation, de modo a especificar cada tipo de Operation que pode ser realizada e aplicar o Undo/Redo por polimorfismo.

```java
public abstract class Operation implements UndoRedo {}
```

---

```java
public class CreateProjectOperation extends Operation{
    private Project createdProject;

    public CreateProjectOperation(Project createdProject){
        this.createdProject = createdProject;
    }

    public void undo(StorageState state){
        createdProject.getCoordinator().getProjectsThatUserIsCoordinator().remove(this.createdProject);
        state.getProjects().remove(this.createdProject);
    }

    public void redo(StorageState state){
        createdProject.getCoordinator().getProjectsThatUserIsCoordinator().add(this.createdProject);
        state.getProjects().add(this.createdProject);
    }
}
```

---

```java
public class EditActivityOperation extends Operation{
    private Activity editedActivity;
    private Activity activityBeforeEditing;

    public EditActivityOperation(Activity editedActivity, Activity activityBeforeEditing){
        this.editedActivity = editedActivity;
        this.activityBeforeEditing = activityBeforeEditing;
    }

    @Override
    public void undo(StorageState state) {
        Activity editedActivityAux = new Activity();
        editedActivityAux.copyActivityInfoFrom(this.editedActivity);

        for (Student student : this.editedActivity.getWhoIsDoing()) {
            student.getActivitiesWorkedOn().remove(this.editedActivity);
        }
        this.editedActivity.copyActivityInfoFrom(this.activityBeforeEditing);
        for (Student student : this.editedActivity.getWhoIsDoing()) {
            student.getActivitiesWorkedOn().add(this.editedActivity);
        }
        
        this.activityBeforeEditing = this.editedActivity;
        this.editedActivity = editedActivityAux;
    }

    @Override
    public void redo(StorageState state) {
        Activity activityBeforeEditingAux = new Activity();
        activityBeforeEditingAux.copyActivityInfoFrom(this.activityBeforeEditing);

        for (Student student : this.activityBeforeEditing.getWhoIsDoing()) {
            student.getActivitiesWorkedOn().remove(this.activityBeforeEditing);
        }
        this.activityBeforeEditing.copyActivityInfoFrom(this.editedActivity);
        for (Student student : this.activityBeforeEditing.getWhoIsDoing()) {
            student.getActivitiesWorkedOn().add(this.activityBeforeEditing);
        }

        this.editedActivity = this.activityBeforeEditing;
        this.activityBeforeEditing = activityBeforeEditingAux;
    }
    
}
```

---

```java
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
```

---
