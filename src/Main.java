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

                    CreateProjectOperation createProjectOperation = new CreateProjectOperation(newProject);
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
                    if (idx == NOT_FOUND){
                        System.out.println("Projeto nao encontrado");
                        continue;
                    }
                    else project = ((Coordinator)loggedUser).getProjectsThatUserIsCoordinator().get(idx);

                    if (project.getActivities().size() != 0){
                        System.out.println("Nao e possivel remover projetos com atividades! Remova as atividades primeiro");;
                        continue;
                    }

                    RemoveProjectOperation removedProjectOperation = new RemoveProjectOperation(project);
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
                        ((Student)user).getSalaries().add(userSalary);
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
                
                    Operation editedProjectOperation = new EditProjectOperation(project, projectBeforeEditing);
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

                    Operation editedProjectOperation = new EditProjectOperation(targetProject, targetProjectBeforeEditing);
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
                else if (command == 4 && loggedUser.canBeCoordinator()){
                    String id, description, start, end, leaderName, projectName;
                    int projectIdx, userIdx;
                    Activity newActivity;
                    Project project;
                    Student leader = null;

                    System.out.println("Diga o id do projeto da atividade");
                    projectName = input.nextLine();
                    projectIdx = getProjectIndexByName(projects, projectName);
                    if (projectIdx == NOT_FOUND){
                        System.out.println("Projeto nao encontrado");
                        continue;
                    }
                    project = projects.get(projectIdx);

                    System.out.println("Diga o id, descricao, inicio, fim e nome do responsavel pela atividade");
                    System.out.println("(Digite 'SAIR' para sair)");
                    id = input.nextLine();
    
                    if (id.equals("SAIR")) continue;
    
                    description = input.nextLine();
                    start = input.nextLine();
                    end = input.nextLine();
                    leaderName = input.nextLine();
    
                    userIdx = getStudentIndexByName(project.getPeopleOnProject(), leaderName);
                    if (userIdx != NOT_FOUND){
                        leader = project.getPeopleOnProject().get(userIdx);
                    }
                    else{
                        userIdx = getStudentIndexByName(project.getBorrowedUsers(), leaderName);
                        if (userIdx != NOT_FOUND){
                            leader = project.getBorrowedUsers().get(userIdx);
                        }
                    }

                    if (userIdx == NOT_FOUND){
                        System.out.println("Usuario nao encontrado");
                        continue;
                    }
    
                    newActivity = new Activity(id, description, start, end, leader, project);
                    activities.add(newActivity);
                    leader.getActivitiesThatUserIsLeader().add(newActivity);
                    project.getActivities().add(newActivity);

                    CreateActivityOperation createActivityOperation = new CreateActivityOperation(newActivity);
                    done.push(createActivityOperation);
                    undone.clear();
                }
                else if (command == 5 && loggedUser.canBeCoordinator()){
                    String projectName, activityName;
                    Project project;
                    Activity activity;
                    int idx;

                    System.out.println("Digite a identificacao do projeto dono da atividade a remover");
                    projectName = input.nextLine();

                    idx = getProjectIndexByName(((Coordinator)loggedUser).getProjectsThatUserIsCoordinator(), projectName);
                    if (idx == NOT_FOUND){
                        System.out.println("Projeto nao encontrado");
                        continue;
                    }
                    else project = ((Coordinator)loggedUser).getProjectsThatUserIsCoordinator().get(idx);

                    System.out.println("Digite a identificacao da atividade a remover");
                    activityName = input.nextLine();

                    idx = getActivityIndexByName(project.getActivities(), activityName);
                    if (idx == NOT_FOUND){
                        System.out.println("Atividade nao encontrada");
                        continue;
                    }
                    else activity = project.getActivities().get(idx);

                    RemoveActivityOperation removedActivityOperation = activity.remove(activities);
                    if (removedActivityOperation != null){
                        done.push(removedActivityOperation);
                        undone.clear();
                    }
                }
                else if (command == 6 && loggedUser.canBeCoordinator()){
                    String projectName, activityName;
                    Project project;
                    Activity activity, activityBeforeEditing;
                    int idx;

                    System.out.println("Digite a identificacao do projeto dono da atividade a editar");
                    projectName = input.nextLine();

                    idx = getProjectIndexByName(((Coordinator)loggedUser).getProjectsThatUserIsCoordinator(), projectName);
                    if (idx == NOT_FOUND){
                        System.out.println("Projeto nao encontrado");
                        continue;
                    }
                    else project = ((Coordinator)loggedUser).getProjectsThatUserIsCoordinator().get(idx);

                    System.out.println("Digite a identificacao da atividade a editar");
                    activityName = input.nextLine();

                    idx = getActivityIndexByName(project.getActivities(), activityName);
                    if (idx == NOT_FOUND){
                        System.out.println("Atividade nao encontrada");
                        continue;
                    }
                    else activity = project.getActivities().get(idx);

                    activityBeforeEditing = new Activity();
                    activityBeforeEditing.copyActivityInfoFrom(activity);

                    System.out.println("1) Identificacao");
                    System.out.println("2) Descricao");
                    System.out.println("3) Inicio");
                    System.out.println("4) Fim");
                    System.out.println("5) Responsavel");
                    System.out.println("6) Adicionar usuario a atividade");
                    System.out.println("7) Remover usuario da atividade");
                    System.out.println("99) Sair");

                    command = input.nextInt();
                    input.nextLine();

                    if (command == 1){
                        String newActivityId;

                        System.out.println("Digite a nova identificacao");
                        System.out.println("(Digite 'SAIR' para sair)");
                        newActivityId = input.nextLine();

                        if (newActivityId.equals("SAIR")) continue;

                        activity.setId(newActivityId);
                    }
                    else if (command == 2){
                        String newActivityDescription;

                        System.out.println("Digite a nova descricao");
                        System.out.println("(Digite 'SAIR' para sair)");
                        newActivityDescription = input.nextLine();

                        if (newActivityDescription.equals("SAIR")) continue;

                        activity.setDescription(newActivityDescription);
                    }
                    else if (command == 3){
                        String newActivityStart;

                        System.out.println("Digite a nova data de inicio");
                        System.out.println("(Digite 'SAIR' para sair)");
                        newActivityStart = input.nextLine();

                        if (newActivityStart.equals("SAIR")) continue;

                        activity.setStart(newActivityStart);
                    }
                    else if (command == 4){
                        String newActivityEnd;

                        System.out.println("Digite a nova data de fim");
                        newActivityEnd = input.nextLine();

                        if (newActivityEnd.equals("SAIR")) continue;

                        activity.setEnd(newActivityEnd);
                    }
                    else if (command == 5){
                        String newActivityLeaderName;
                        Student newActivityLeader;
                        int userIdx;

                        System.out.println("Digite o nome do novo responsavel");
                        newActivityLeaderName = input.nextLine();
                        if (newActivityLeaderName.equals("SAIR")) continue;
                        
                        userIdx = getStudentIndexByName(activity.getWhoIsDoing(), newActivityLeaderName);
                        if (userIdx == NOT_FOUND){
                            System.out.println("Usuario nao encontrado");
                            continue;
                        }
                        newActivityLeader = activity.getWhoIsDoing().get(userIdx);
                        activity.setLeader(newActivityLeader);
                    }
                    else if (command == 6){
                        String userName, duty;
                        int userIdx;
                        Student user;
                        System.out.println("Que usuario?");
                        userName = input.nextLine();
                        userIdx = getStudentIndexByName(activity.getOwnerProject().getPeopleOnProject(), userName);
                        if (userIdx == NOT_FOUND){
                            userIdx = getStudentIndexByName(activity.getOwnerProject().getBorrowedUsers(), userName);
                            if (userIdx == NOT_FOUND){
                                System.out.println("Usuario nao encontrado no projeto");
                                continue;
                            }
                            else{
                                user = activity.getOwnerProject().getBorrowedUsers().get(userIdx);
                            }
                        }
                        else user = activity.getOwnerProject().getPeopleOnProject().get(userIdx);

                        System.out.println("Qual dever do usuario?");
                        duty = input.nextLine();
                        activity.getDuties().add(duty);

                        user.getActivitiesWorkedOn().add(activity);
                        activity.getWhoIsDoing().add(user);
                    }
                    else if (command == 7){
                        int userIdx;
                        String userName;
                        Student user;

                        System.out.println("Que usuario?");

                        userName = input.nextLine();

                        userIdx = getStudentIndexByName(activity.getWhoIsDoing(), userName);
                        if (userIdx == NOT_FOUND){
                            System.out.println("Usuario nao encontrado na atividade");
                            continue;
                        }
                        user = activity.getWhoIsDoing().get(userIdx);

                        user.getActivitiesWorkedOn().remove(activity);
                        activity.getDuties().remove(userIdx);
                        activity.getWhoIsDoing().remove(userIdx);
                    }
                    else if (command == 99) continue;
                
                    EditActivityOperation editActivityOperation = new EditActivityOperation(activity, activityBeforeEditing);
                    done.push(editActivityOperation);
                    undone.clear();
                }
            }
            else if (command == 3){
                System.out.println(loggedUser.displayUserMenu());
                command = input.nextInt();
                input.nextLine();

                if (command == 1){
                    String userName;
                    int userIdx;
                    User user;

                    System.out.println("Que usuario?");
                    userName = input.nextLine();
                    userIdx = getUserIndexByName(users, userName);
                    
                    if (userIdx != NOT_FOUND){
                        user = users.get(userIdx);
                        System.out.println(user);
                    }
                    else System.out.println("Nao existe");
                }
                else if (command == 2){
                    for (User user : users) {
                        System.out.println(user);
                    }
                }
                else if (command == 3 && loggedUser.canBeCoordinator()){
                    String name, type;
                    User newUser;

                    System.out.println("Diga o nome e o tipo do usuario");
                    System.out.println("(Digite 'SAIR' para sair)");
                    name = input.nextLine();
                    if (name.equals("SAIR")) continue;
                    type = input.nextLine();

                    if (type.equals("professor") || type.equals("coordenador")){
                        newUser = new Coordinator(name, type);
                    }
                    else{
                        newUser = new Student(name, type);
                    }

                    users.add(newUser);

                    CreateUserOperation createUserOperation = new CreateUserOperation(newUser);
                    done.push(createUserOperation);
                    undone.clear();
                }
                else if (command == 4 && loggedUser.canBeCoordinator()){
                    String userName;
                    User userToRemove;
                    int idx;
    
                    System.out.println("Que usuario quer remover?");
                    userName = input.nextLine();
                    if (userName.equals("SAIR")) continue;
                    idx = getUserIndexByName(users, userName);
                    if (idx == NOT_FOUND){
                        System.out.println("Usuario nao encontrado!");
                        continue;
                    }
                    else userToRemove = users.get(idx);

                    userToRemove.remove(users);

                    RemoveUserOperation removeUserOperation = new RemoveUserOperation(userToRemove);
                    done.push(removeUserOperation);
                    undone.clear();
                }
                else if (command == 5 && loggedUser.canBeCoordinator()){
                    String userName;
                    int userIdx;
                    User user, userBeforeEditing;
    
                    System.out.println("Que usuario?");
                    userName = input.nextLine();
                    if (userName.equals("SAIR")) continue;
                    userIdx = getUserIndexByName(users, userName);
                    if (userIdx == NOT_FOUND){
                        System.out.println("Usuario nao encontrado");
                        continue;
                    }
                    user = users.get(userIdx);
                
                    if (user.canBeCoordinator()){
                        userBeforeEditing = new Coordinator();
                    }
                    else{
                        userBeforeEditing = new Student();
                    }
                    userBeforeEditing.copyUserInfoFrom(user);

                    System.out.println(((Coordinator)loggedUser).displayUserEditingMenu());
                    command = input.nextInt();
                    input.nextLine();

                    if (command == 1){
                        if (!(user instanceof Student)){
                            System.out.println("Nao e possivel adicionar usuarios desse tipo a projetos");
                            continue;
                        }

                        String projectName;
                        int projectIdx;
                        double userSalary;
                        Project project;

                        System.out.println("Que projeto?");

                        projectName = input.nextLine();
                        projectIdx = getProjectIndexByName(((Coordinator)loggedUser).getProjectsThatUserIsCoordinator(), projectName);
                        if (projectIdx == NOT_FOUND){
                            System.out.println("Projeto nao encontrado");
                            continue;
                        }
                        project = ((Coordinator)loggedUser).getProjectsThatUserIsCoordinator().get(projectIdx);

                        System.out.println("Qual e o salario do usuario?");

                        userSalary = input.nextDouble();
                        input.nextLine();

                        project.getPeopleOnProject().add((Student)user);
                        project.getSalary().add(userSalary);
                        ((Student)user).getProjectsWorkedOn().add(project);
                        ((Student)user).getSalaries().add(userSalary);
                    }
                    else if (command == 2){
                        if (!(user instanceof Student)){
                            System.out.println("Nao e possivel adicionar usuarios desse tipo a projetos");
                            continue;
                        }

                        String activityName, projectName;
                        int activityIdx, projectIdx;
                        Project project;
                        Activity activity;

                        System.out.println("De que projeto e a atividade?");
                        projectName = input.nextLine();
                        projectIdx = getProjectIndexByName(((Coordinator)loggedUser).getProjectsThatUserIsCoordinator(), projectName);
                        if (projectIdx == NOT_FOUND){
                            System.out.println("Projeto nao encontrado");
                            continue;
                        }
                        project = ((Coordinator)loggedUser).getProjectsThatUserIsCoordinator().get(projectIdx);

                        System.out.println("Que atividade?");
                        activityName = input.nextLine();
                        activityIdx = getActivityIndexByName(project.getActivities(), activityName);
                        if (activityIdx == NOT_FOUND){
                            System.out.println("Atividade nao encontrada");
                            continue;
                        }
                        activity = activities.get(activityIdx);

                        ((Student)user).getActivitiesWorkedOn().add(activity);
                        activity.getWhoIsDoing().add((Student)user);
                    }
                    else if (command == 3){
                        if (user instanceof Coordinator && user != loggedUser){
                            System.out.println("Nao e possivel editar dados de outros coordenadores");
                            continue;
                        }
                        System.out.println("1) Editar nome");
                        System.out.println("2) Editar tipo");
                        System.out.println("99) Sair");
    
                        command = input.nextInt();
                        input.nextLine();
    
                        if (command == 1){
                            String newUserName;
    
                            System.out.println("Digite o novo nome");
                            newUserName = input.nextLine();
    
                            if (newUserName.equals("SAIR")) continue;
    
                            user.setName(newUserName);
                        }
                        else if (command == 2){
                            String newUserType;
    
                            System.out.println("Digite o novo tipo");
                            newUserType = input.nextLine();
    
                            if (newUserType.equals("SAIR")) continue;
                            
                            if (
                                    (user.canBeCoordinator() && !(newUserType.equals("professor") || newUserType.equals("pesquisador")))
                                    || (!user.canBeCoordinator() && (newUserType.equals("professor") || newUserType.equals("pesquisador")))
                                ){
                                System.out.println("Mudanca nao possivel!");
                                System.err.println("Para mudar de Estudante para Coordenador, remova o usuario antigo e");
                                System.out.println("crie um novo com o tipo desejado");
                                continue;
                            }
                            user.setType(newUserType);
                        }
                        else continue;
                    }
                    else continue;
                    
                    EditUserOperation editUserOperation = new EditUserOperation(user, userBeforeEditing);
                    done.push(editUserOperation);
                    undone.clear();
                }
                else continue;
            }
            else if (command == 4){
                loggedAccount = null;
                loggedUser = null;
            }
            else if (command == 5){
                break;
            }
            else if (command == 6 && loggedUser.canBeCoordinator()){
                if (done.size() == 0){
                    System.out.println("Nao ha nada para desfazer!");
                    continue;
                }

                Operation operation = done.pop();
                operation.undo(projects, activities, users);
                undone.push(operation);
            }
            else if (command == 7 && loggedUser.canBeCoordinator()){
                if (undone.size() == 0){
                    System.out.println("Nao ha nada para fazer!");
                    continue;
                }

                Operation operation = undone.pop();
                operation.redo(projects, activities, users);
                done.push(operation);
            }
            else if (command == 8 && loggedUser.canBeCoordinator()){
                int period;
                System.out.println("Ha quantos dias ocorreu o ultimo pagamento das bolsas?");

                period = input.nextInt();
                input.nextLine();

                for (Project project : ((Coordinator)loggedUser).getProjectsThatUserIsCoordinator()) {
                    if (period >= project.getPaymentPeriod()) project.makePayments();
                }
            }
            else{
                System.out.println("Opcao invalida!");
            }
            
        }

        input.close();
    }
}