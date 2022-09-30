import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main{
	public static final int NOT_FOUND = -1;

    public static int getProjectIndexByName(ArrayList<Project> projects, String name){
        for(int i = 0; i < projects.size(); i++){
            if (projects.get(i).getId().equals(name)) return i;
        }

        return NOT_FOUND;
    }

    public static int getActivityIndexByName(ArrayList<Activity> activities, String name){
        for(int i = 0; i < activities.size(); i++){
            if (activities.get(i).getId().equals(name)) return i;
        }

        return NOT_FOUND;
    }

    public static int getUserIndexByName(ArrayList<User> users, String name){
        for(int i = 0; i < users.size(); i++){
            if (users.get(i).getName().equals(name)) return i;
        }

        return NOT_FOUND;
    }

    public static int findAccountIndexByName(ArrayList<Account> accounts, String name){
        for(int i = 0; i < accounts.size(); i++){
            if (accounts.get(i).getUsername().equals(name)) return i;
        }

        return NOT_FOUND;
    }

    public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
        ArrayList<Project> projects = new ArrayList<Project>();
		ArrayList<Activity> activities = new ArrayList<Activity>();
		ArrayList<User> users = new ArrayList<User>();
        ArrayList<Account> accounts = new ArrayList<Account>();
		Account loggedAccount = null;
        User loggedUser = null;
        int command;
        Stack<Operation> done = new Stack<Operation>();
        Stack<Operation> undone = new Stack<Operation>();

		while(true){
            if (loggedAccount == null){
                done.clear();
                undone.clear();

                System.out.println("Faca o log-in, ou crie sua conta");
                System.out.println("1) Log in");
                System.out.println("2) Criar conta");
                System.out.println("3) Esqueci minha senha");
                System.out.println("4) Sair");

                command = input.nextInt();
                input.nextLine();

                if (command == 1){
                    String username, password;
                    Account accountLogIn;
                    int idx;

                    System.out.println("Digite o nome do usuario:");
                    System.out.println("(Digite 'SAIR' para voltar)");

                    username = input.nextLine();

                    if (username.equals("SAIR")) continue;

                    idx = findAccountIndexByName(accounts, username);

                    if (idx == NOT_FOUND){
                        System.out.println("Usuario nao encontrado!");
                        continue;
                    }

                    accountLogIn = accounts.get(idx);

                    System.out.println("Digite a senha:");
                    password = input.nextLine();

                    if (accountLogIn.getPassword().equals(password)){
                        System.out.println("Bem vindo, " + accountLogIn.getAccountOwner().getName());
                        loggedAccount = accountLogIn;
                        loggedUser = loggedAccount.getAccountOwner();
                    }
                    else System.out.println("Senha incorreta!");
                }
                else if (command == 2){
                    String username, password, passwordConfirmation;
                    Account newAccount;

                    System.out.println("Digite o nome do usuario:");
                    username = input.nextLine();
                    System.out.println("Digite sua senha:");
                    password = input.nextLine();
                    System.out.println("Confirme sua senha:");
                    passwordConfirmation = input.nextLine();
                    
                    if (!password.equals(passwordConfirmation)){
                        System.out.println("Senha nao confirmada, tente novamente");
                        continue;
                    }

                    System.out.println("Ja tem um usuario cadastrado no sistema?");
                    System.out.println("1) Sim");
                    System.err.println("2) Nao");
                    System.out.println("99) Voltar");
                    
                    command = input.nextInt();
                    input.nextLine();

                    if (command == 1){
                        String name;
                        int idx;
                        User user;

                        System.out.println("Informe seu nome como esta cadastrado");
                        name = input.nextLine();

                        idx = getUserIndexByName(users, name);

                        if (idx == NOT_FOUND){
                            System.out.println("Usuario com o nome digitado nao encontrado");
                            continue;
                        }

                        user = users.get(idx);
                        newAccount = new Account(username, password, user);

                        System.out.println("Usuario vinculado");
                    }
                    else if (command == 2){
                        String name, type;
                        User newUser;
                        newAccount = new Account(username, password);

                        System.out.println("Criando usuario...");
                        System.out.println("Diga o nome e o tipo do usuario");
                        System.out.println("(Digite 'SAIR' para sair)");
                        
                        name = input.nextLine();
        
                        if (name.equals("SAIR")) continue;
        
                        type = input.nextLine();
                        newUser = (type.equals("coordenador") || type.equals("professor")) ? 
                            (new Coordinator(name, type)) : new Student(name, type);

                        users.add(newUser);
                        newAccount.setAccountOwner(newUser);

                        System.out.println("Usuario vinculado");
                    }
                    else if (command == 99) continue;
                    else{
                        System.out.println("Opcao invalida, tente novamente");
                        continue;
                    }

                    accounts.add(newAccount);

                    System.out.println("Cadastro completo!");
                }
                else if (command == 3){
                    String name;
                    int idx;
                    Account account;

                    System.out.println("Digite o nome de sua conta");
                    name = input.nextLine();

                    idx = findAccountIndexByName(accounts, name);

                    if (idx == NOT_FOUND){
                        System.out.println("Conta nao encontrada");
                        continue;
                    }

                    account = accounts.get(idx);

                    System.out.println("Qual o nome do usuario vinculado com esta conta?");
                    name = input.nextLine();

                    if (account.getAccountOwner().getName().equals(name)){
                        System.out.println("A senha de sua conta e: " + account.getPassword());
                    }
                    else{
                        System.out.println("Errado!");
                    }
                }
                else if (command == 4) break;
                else System.out.println("Opcao invalida");
                continue;
            }
            
            System.out.println(loggedUser.displayMainMenu());
            command = input.nextInt();
            input.nextLine();

            if (command == 1){
                System.out.println(loggedUser.displayProjectMenu());
                command = input.nextInt();
                input.nextLine();

                if (command == 1){
                    String projectName;
                    int idx;

                    System.out.println("Procurando por qual projeto?");
                    projectName = input.nextLine();
                    idx = getProjectIndexByName(projects, projectName);

                    if (idx != NOT_FOUND) System.out.println(projects.get(idx));
                    else System.out.println("Projeto nao encontrado!");
                }
                else if (command == 2){
                    for (Project project : projects) {
                        System.out.println(project);
                    }
                }
                else if (command == 3){
                    for (Project project : projects) {
                        System.out.println(project.report());
                    }
                }
                else if (command == 4 && loggedUser.canBeCoordinator()){
                    String projectName, projectDescription;
                    Project newProject;
                    int paymentPeriod;

                    System.out.println("Digite a identificacao");
                    projectName = input.nextLine();
                    System.out.println("Digite a descricao");
                    projectDescription = input.nextLine();
                    System.out.println("Digite o periodo de pagamento de bolsas (em dias)");
                    paymentPeriod = input.nextInt();
                    input.nextLine();

                    newProject = new Project(projectName, projectDescription, (Coordinator)loggedUser, paymentPeriod);

                    projects.add(newProject);
                    ((Coordinator)loggedUser).getProjectsThatUserIsCoordinator().add(newProject);

                    CreateProject createProjectOperation = new CreateProject(newProject);
                    done.push(createProjectOperation);
                    undone.clear();
                }
                else if (command == 5 && loggedUser.canBeCoordinator()){
                    String projectName;
                    Project project;
                    int idx;

                    System.out.println("Digite a identificacao do projeto a remover");
                    projectName = input.nextLine();

                    idx = getProjectIndexByName(((Coordinator)loggedUser).getProjectsThatUserIsCoordinator(), projectName);
                    if (idx != NOT_FOUND){
                        System.out.println("Projeto nao encontrado");
                        continue;
                    }
                    else project = ((Coordinator)loggedUser).getProjectsThatUserIsCoordinator().get(idx);

                    RemoveProject removedProjectOperation = project.remove(projects);
                    if (removedProjectOperation != null){
                        done.push(removedProjectOperation);
                        undone.clear();
                    }
                }
                else if (command == 6 && loggedUser.canBeCoordinator()){
                    
                }
                else if (command == 7 && loggedUser.canBeCoordinator()){
                    // TODO
                }
            }
            else if (command == 2){
                // TODO
            }
            else if (command == 3){
                // TODO
            }
            else if (command == 4){
                loggedAccount = null;
                loggedUser = null;
            }
            else if (command == 5){
                break;
            }
            else if (command == 6 && loggedUser.canBeCoordinator()){
                // TODO
            }
            else if (command == 7 && loggedUser.canBeCoordinator()){
                // TODO
            }
            else if (command == 8 && loggedUser.canBeCoordinator()){
                // TODO
            }
            else{
                System.out.println("Opcao invalida!");
            }
            
        }

        input.close();
    }
}