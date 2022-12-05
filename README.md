<img src="/assets/java-logo.png" alt="drawing" width="200"/>

# Projeto-Java
Projeto Java da matéria de Projeto de Software, matéria do curso de Engenharia de Computação e Ciência da Computação, IC/UFAL.

## Informações:
**Aluno**:
* Vinícius Teixeira Pereira Ramos

**Professor**:
* Baldoino Fonseca dos Santos Neto

## Code Smells:
Identificação de Code Smells no código do projeto.

### Primitive Obsession:

#### Utilização da variável 'private String type' para controlar o retorno do método 'public boolean canBeCoordinator()':
#### Ocorre na classe User.
```java
public abstract class User implements Menu{
	private String name;
	private String type;
	private Account account;
	
	public boolean canBeCoordinator(){
		return (this.type.equals("professor") || this.type.equals("coordenador"));
	}
}
```
* [LINK](https://github.com/VinnieT1/Projeto-Java/blob/Exceptions/src/User.java)

---

### Speculative Generality:

#### Métodos requerem 3 parâmetros, porém, nem todos são usados:
#### Ocorre na interface UndoRedo e em todas as sub-classes de Operation, pois Operation implementa UndoRedo.
```java
public interface UndoRedo {
	public void undo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users);
	public void redo(ArrayList<Project> projects, ArrayList<Activity> activities, ArrayList<User> users);
}
```
* [LINK](https://github.com/VinnieT1/Projeto-Java/blob/Exceptions/src/UndoRedo.java)

---

### Duplicated Code:

#### Na classe Account, há duplicação de código no método construtor:
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
        this.username = username;
        this.password = password;
        this.accountOwner = accountOwner;
    }
}
```
* [LINK](https://github.com/VinnieT1/Projeto-Java/blob/Exceptions/src/Account.java)
