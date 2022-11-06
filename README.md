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

### Middle Man:

#### (?) A classe Operation só serve para ser uma superclasse das subclasses da mesma, porém não possui atributos nem métodos. Foi necessário para conseguir armazenar todos os objetos das subclasses em um só ArrayList (done e undone, na classe Main)
```java
public abstract class Operation implements UndoRedo{
    
}
```
* [LINK](https://github.com/VinnieT1/Projeto-Java/blob/Exceptions/src/Operation.java)
