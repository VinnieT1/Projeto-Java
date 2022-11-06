<img src="/assets/java-logo.png" alt="drawing" width="200"/>

# Projeto-Java
Projeto Java da matéria de Projeto de Software, matéria do curso de Engenharia de Computação e Ciência da Computação, IC/UFAL.

## Informações:
**Aluno**:
* Vinícius Teixeira

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

