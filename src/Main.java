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

    public static int getStudentIndexByName(ArrayList<Student> students, String name){
        for(int i = 0; i < students.size(); i++){
            if (students.get(i).getName().equals(name)) return i;
        }

        return NOT_FOUND;
    }

    public static int getAccountIndexByName(ArrayList<Account> accounts, String name){
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

                    idx = getAccountIndexByName(accounts, username);

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

                    idx = getAccountIndexByName(accounts, name);

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
                    String projectName;
                    int projectIdx;
                    Project project;
                    Project projectBeforeEditing;
                    System.out.println("Que projeto?");
                    System.out.println("(Digite 'SAIR' para sair)");
                    
                    projectName = input.nextLine();
                    if (projectName.equals("SAIR")) continue;
                    projectIdx = getProjectIndexByName(((Coordinator)loggedUser).getProjectsThatUserIsCoordinator(), projectName);
                    
                    if (projectIdx == NOT_FOUND){
                        System.out.println("Nao encontrado");
                        continue;
                    }
                    
                    project = ((Coordinator)loggedUser).getProjectsThatUserIsCoordinator().get(projectIdx);
                    projectBeforeEditing = new Project();
                    projectBeforeEditing.copyProjectInfoFrom(project);

                    System.out.println(project.displayEditingMenu());
                    command = input.nextInt();
                    input.nextLine();
                    
                    if (command == 1){
                        String newProjectName;

                        System.out.println("Digite a nova identificacao");
                        newProjectName = input.nextLine();

                        project.setId(newProjectName);
                    }
                    else if (command == 2){
                        String newProjectDescription;

                        System.out.println("Digite a nova descricao");
                        newProjectDescription = input.nextLine();

                        project.setDescription(newProjectDescription);
                    }
                    else if (command == 3){
                        String newProjectStart;

                        System.out.println("Digite a nova data de inicio");
                        newProjectStart = input.nextLine();

                        project.setStart(newProjectStart);
                    }
                    else if (command == 4){
                        String newProjectEnd;

                        System.out.println("Digite a nova data de fim");
                        newProjectEnd = input.nextLine();

                        project.setEnd(newProjectEnd);
                    }
                    else if (command == 5){
                        System.out.println("Status atual: '" + project.getStatus() + "'");
                        if (project.getStatus().equals("Em processo de criacao")){
                            System.out.println("Deseja iniciar o projeto?");
                            System.out.println("1) Sim");
                            System.out.println("2) Nao");
                            command = input.nextInt();
                            input.nextLine();

                            if (command == 1) project.initiateProject();
                            else continue;
                        }
                        else if (project.getStatus().equals("Iniciado")){
                            System.out.println("Deseja dar andamento ao projeto?");
                            System.out.println("1) Sim");
                            System.out.println("2) Nao");
                            command = input.nextInt();
                            input.nextLine();

                            if (command == 1){
                                System.out.println("Tem certeza que quer dar andamento ao projeto?");
                                System.out.println("1) Sim");
                                System.out.println("2) Nao");
                                command = input.nextInt();
                                input.nextLine();

                                if (command == 1) project.goForward();
                                else continue;
                            }
                            else continue;
                        }
                        else if (project.getStatus().equals("Em andamento")){
                            System.out.println("Deseja concluir o projeto?");
                            System.out.println("1) Sim");
                            System.out.println("2) Nao");
                            command = input.nextInt();
                            input.nextLine();

                            if (command == 1) project.complete();
                            else continue;
                        }
                        else{
                            System.out.println("O projeto ja esta concluido!");
                            continue;
                        }

                    }
                    else if (command == 6){
                        String newProjectCoordinatorName;
                        User newProjectCoordinator;

                        System.out.println("Digite o nome do novo coordenador");
                        newProjectCoordinatorName = input.nextLine();

                        newProjectCoordinator = users.get(getUserIndexByName(users, newProjectCoordinatorName));

                        if (!(newProjectCoordinator instanceof Coordinator)){
                            System.out.println("Usuario nao e coordenador");
                            continue;
                        }

                        ((Coordinator)loggedUser).getProjectsThatUserIsCoordinator().remove(project);
                        project.setCoordinator((Coordinator)newProjectCoordinator);
                        project.getCoordinator().getProjectsThatUserIsCoordinator().add(project);
                    }
                    else if (command == 7){
                        String userName;
                        int userIdx;
                        double userSalary;
                        User user;

                        System.out.println("Que usuario?");

                        userName = input.nextLine();
                        userIdx = getUserIndexByName(users, userName);
                        if (userIdx == NOT_FOUND){
                            System.out.println("Usuario nao encontrado");
                            continue;
                        }
                        user = users.get(userIdx);
                        if (!(user instanceof Student)){
                            System.out.println("Usuario nao e aluno");
                            continue;
                        }

                        System.out.println("Qual e o salario do usuario?");

                        userSalary = input.nextDouble();
                        input.nextLine();

                        project.getPeopleOnProject().add((Student)user);
                        project.getSalary().add(userSalary);
                        ((Student)user).getProjectsWorkedOn().add(project);
                    }
                    else if (command == 8){
                        String userName;
                        int userIdx;
                        User user;

                        System.out.println("Que usuario?");
                        userName = input.nextLine();
                        userIdx = getStudentIndexByName(project.getPeopleOnProject(), userName);
                        if (userIdx == NOT_FOUND){
                            userIdx = getStudentIndexByName(project.getBorrowedUsers(), userName);
                            if (userIdx == NOT_FOUND){
                                System.out.println("Usuario nao encontrado");
                                continue;
                            }
                            user = project.getBorrowedUsers().get(userIdx);
                            project.getBorrowedUsers().remove(user);
                        }
                        else user = project.getPeopleOnProject().get(userIdx);

                        project.getPeopleOnProject().remove(user);
                        project.getSalary().remove(userIdx);
                    }
                    else if (command == 9){
                        String userName;
                        int userIdx;
                        double newSalary;

                        System.out.println("Que usuario?");
                        userName = input.nextLine();
                        userIdx = getStudentIndexByName(project.getPeopleOnProject(), userName);
                        if (userIdx == NOT_FOUND){
                            System.out.println("Usuario nao esta no projeto ou nao recebe salario");
                            continue;
                        }

                        System.out.println("Qual e o novo salario?");
                        newSalary = input.nextDouble();
                        input.nextLine();

                        project.getSalary().set(userIdx, newSalary);
                    }
                    else continue;
                
                    Operation editedProjectOperation = new EditProject(project, projectBeforeEditing);
                    done.push(editedProjectOperation);
                    undone.clear();
                }
                else if (command == 7 && loggedUser.canBeCoordinator()){
                    String projectName, userName, targetProjectName;
                    Student student;
                    Project project, targetProject, targetProjectBeforeEditing;
                    int projectIdx, userIdx, targetProjectIdx;

                    System.out.println("Usuario de que projeto?");
                    projectName = input.nextLine();
                    projectIdx = getProjectIndexByName(((Coordinator)loggedUser).getProjectsThatUserIsCoordinator(), projectName);
                    if (projectIdx == NOT_FOUND){
                        System.out.println("Projeto nao encontrado");
                        continue;
                    }
                    project = ((Coordinator)loggedUser).getProjectsThatUserIsCoordinator().get(projectIdx);

                    System.out.println("Que usuario?");
                    userName = input.nextLine();
                    userIdx = getStudentIndexByName(project.getPeopleOnProject(), userName);
                    if (userIdx == NOT_FOUND){
                        System.out.println("Usuario nao encontrado");
                        continue;
                    }
                    student = project.getPeopleOnProject().get(userIdx);

                    System.out.println("Para que projeto o usuario sera enviado?");
                    targetProjectName = input.nextLine();
                    targetProjectIdx = getProjectIndexByName(projects, targetProjectName);
                    if (targetProjectIdx == NOT_FOUND){
                        System.out.println("Projeto nao encontrado");
                        continue;
                    }
                    targetProject = projects.get(targetProjectIdx);

                    targetProjectBeforeEditing = new Project();
                    targetProjectBeforeEditing.copyProjectInfoFrom(targetProject);

                    targetProject.getBorrowedUsers().add(student);
                    student.getProjectsThatUserWasLentTo().add(targetProject);

                    Operation editedProjectOperation = new EditProject(targetProject, targetProjectBeforeEditing);
                    done.push(editedProjectOperation);
                    undone.clear();
                }
            }
            else if (command == 2){
                System.out.println(loggedUser.displayActivityMenu());
                command = input.nextInt();
                input.nextLine();

                if (command == 1){
                    String activityName;
                    int activityIdx;
                    Activity activity;
                    System.out.println("Qual o nome da atividade?");
                    activityName = input.nextLine();
                    activityIdx = getActivityIndexByName(activities, activityName);
                    if (activityIdx == NOT_FOUND){
                        System.out.println("Nao encontrada");
                        continue;
                    }
                    activity = activities.get(activityIdx);
                    System.out.println(activity);
                }
                else if (command == 2){
                    for (Activity activity : activities) {
                        System.out.println(activity);
                    }
                }
                else if (command == 3){
                    for (Activity activity : activities) {
                        System.out.println(activity.report());
                    }
                }
                else if (command == 4){
                    // TODO
                }
                else if (command == 5){
                    // TODO
                }
                else if (command == 6){
                    // TODO
                }
            
            
            
            
            
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