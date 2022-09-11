import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main{
    public static final int NOT_FOUND = -1;

    public static int findProjectIndexByName(ArrayList<Project> projects, String name){
        for(int i = 0; i < projects.size(); i++){
            if (projects.get(i).id.equals(name)) return i;
        }

        return NOT_FOUND;
    }

    public static int findActivityIndexByName(ArrayList<Activity> activities, String name){
        for(int i = 0; i < activities.size(); i++){
            if (activities.get(i).id.equals(name)) return i;
        }

        return NOT_FOUND;
    }

    public static int findUserIndexByName(ArrayList<User> users, String name){
        for(int i = 0; i < users.size(); i++){
            if (users.get(i).name.equals(name)) return i;
        }

        return NOT_FOUND;
    }

    public static int findAccountIndexByName(ArrayList<Account> accounts, String name){
        for(int i = 0; i < accounts.size(); i++){
            if (accounts.get(i).username.equals(name)) return i;
        }

        return NOT_FOUND;
    }

    // NOTE TO SELF:
    // Ultima coisa que falta implementar: REDO.
    // IMPLEMENTAR A LOGICA DO REDO.
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        ArrayList<Project> projects = new ArrayList<Project>();
        ArrayList<Activity> activities = new ArrayList<Activity>();
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<Account> accounts = new ArrayList<Account>();
        Stack<Operation> done = new Stack<Operation>();
        Stack<Operation> undone = new Stack<Operation>();

        int command;
        boolean isLoggedIn = false;
        Account accountLoggedIn = null;

        while(true){
            if (!isLoggedIn){
                done.clear();
                undone.clear();

                System.out.println("Faca o log-in, ou crie sua conta");
                System.out.println("1) Log in");
                System.out.println("2) Criar conta");
                System.out.println("3) Esqueci minha senha");
                System.out.println("99) Sair");

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

                    if (accountLogIn.password.equals(password)){
                        System.out.println("Bem vindo, " + accountLogIn.accountOwner.name);
                        isLoggedIn = true;
                        accountLoggedIn = accountLogIn;
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

                    newAccount = new Account(username, password, null);

                    if (command == 1){
                        String name;
                        int idx;
                        User user;

                        System.out.println("Informe seu nome como esta cadastrado");
                        name = input.nextLine();

                        idx = findUserIndexByName(users, name);

                        if (idx == NOT_FOUND){
                            System.out.println("Usuario com o nome digitado nao encontrado");
                            continue;
                        }

                        user = users.get(idx);
                        newAccount.accountOwner = user;

                        System.out.println("Usuario vinculado");
                    }
                    else if (command == 2){
                        String name, type;
                        User newUser;

                        System.out.println("Criando usuario...");
                        System.out.println("Diga o nome e o tipo do usuario");
                        System.out.println("(Digite 'SAIR' para sair)");
                        
                        name = input.nextLine();
        
                        if (name.equals("SAIR")) continue;
        
                        type = input.nextLine();
                        newUser = new User(name, type);

                        users.add(newUser);
                        newAccount.accountOwner = newUser;

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

                    if (account.accountOwner.name.equals(name)){
                        System.out.println("A senha de sua conta e: " + account.password);
                    }
                    else{
                        System.out.println("Errado!");
                    }
                }
                else if (command == 99) break;
                else System.out.println("Opcao invalida");
                continue;
            }

            System.out.println("1) Projetos");
            System.out.println("2) Atividades");
            System.out.println("3) Usuarios");
            System.out.println("4) Pagar bolsas");
            System.out.println("96) Undo");
            System.out.println("97) Redo");
            System.out.println("98) Log out");
            System.out.println("99) Sair");

            command = input.nextInt();
            input.nextLine();

            if (command == 1){
                if (accountLoggedIn.accountOwner.canBeCoordinator){
                    System.out.println("1) Criar Projeto");
                    System.out.println("2) Remover Projeto");
                    System.out.println("3) Editar Projeto");
                    System.out.println("4) Enviar usuario por intercambio");
                }
                System.out.println((accountLoggedIn.accountOwner.canBeCoordinator ? 5 : 1) + ") Procurar por Projeto");
                System.out.println((accountLoggedIn.accountOwner.canBeCoordinator ? 6 : 2) + ") Listar Projetos");
                System.out.println((accountLoggedIn.accountOwner.canBeCoordinator ? 7 : 3) + ") Relatorio dos projetos");
                System.out.println("99) Voltar");
                
                command = input.nextInt();
                input.nextLine();

                if (command == 1 && accountLoggedIn.accountOwner.canBeCoordinator){
                    System.out.println("Diga a identificacao e, depois, a descricao");
                    System.out.println("(Digite 'SAIR' para sair)");
    
                    String id, description;
                    Project newProject;
                    int paymentPeriod;

                    id = input.nextLine();
    
                    if (id.equals("SAIR")) continue;
    
                    description = input.nextLine();

                    System.out.println("Diga o periodo da bolsa para pagamento (em dias inteiros)");

                    paymentPeriod = input.nextInt();
                    input.nextLine();

                    newProject = new Project(id, description, "none", "none", accountLoggedIn.accountOwner, paymentPeriod);

                    accountLoggedIn.accountOwner.projectsthatUserIsCoordinator.add(newProject);
                    projects.add(newProject);

                    Operation createdProjectOperation = new Operation(1, newProject);
                    done.push(createdProjectOperation);
                }
                else if (command == 2 && accountLoggedIn.accountOwner.canBeCoordinator){
                    String projectName;
                    Project projectToRemove;
                    int idx;
    
                    System.out.println("Que projeto quer remover?");
                    System.out.println("(Se quiser sair, digite 'SAIR')");
    
                    projectName = input.nextLine();
    
                    if (projectName.equals("SAIR")) continue;
    
                    idx = findProjectIndexByName(projects, projectName);
    
                    if (idx == NOT_FOUND){
                        System.out.println("Projeto nao encontrado!");
                        continue;
                    }

                    projectToRemove = projects.get(idx);

                    for (User user : projectToRemove.peopleOnProject) {
                        user.projectsWorkedOn.remove(projectToRemove);
                    }
                    for (User user : projectToRemove.borrowedUsers){
                        user.projectsThatUserWasLentTo.remove(projectToRemove);
                    }
                    for (Activity activity : projectToRemove.activities) {
                        activities.remove(activity);
                    }
                    accountLoggedIn.accountOwner.projectsthatUserIsCoordinator.remove(projectToRemove);

                    projects.remove(projectToRemove);
                    System.out.println("Projeto " + projectName + " removido");

                    Operation removedProjectOperation = new Operation(2, projectToRemove);
                    done.push(removedProjectOperation);
                }
                else if (command == 3 && accountLoggedIn.accountOwner.canBeCoordinator){
                    String projectName;
                    int projectIdx;
                    Project project;
                    Project projectBeforeEditing;
                    System.out.println("Que projeto?");
                    System.out.println("(Digite 'SAIR' para sair)");

                    projectName = input.nextLine();
                    if (projectName.equals("SAIR")) continue;
                    projectIdx = findProjectIndexByName(accountLoggedIn.accountOwner.projectsthatUserIsCoordinator, projectName);
                    
                    if (projectIdx == NOT_FOUND){
                        System.out.println("Nao encontrado");
                        continue;
                    }

                    project = accountLoggedIn.accountOwner.projectsthatUserIsCoordinator.get(projectIdx);
                    projectBeforeEditing = new Project(project.id, project.description, project.start, project.end, project.coordinator, project.paymentPeriod);
                    projectBeforeEditing.copyProjectInfoFrom(project);

                    System.out.println("1) Identificacao");
                    System.out.println("2) Descricao");
                    System.out.println("3) Inicio");
                    System.out.println("4) Fim");
                    System.out.println("5) Status");
                    System.out.println("6) Coordenador");
                    System.out.println("7) Adicionar usuarios no projeto");
                    System.out.println("8) Remover usuarios do projeto");
                    System.out.println("9) Mudar salario de um usuario");
                    System.out.println("99) Sair");

                    command = input.nextInt();
                    input.nextLine();

                    if (command == 1){
                        String newProjectName;

                        System.out.println("Digite a nova identificacao");
                        newProjectName = input.nextLine();

                        project.id = newProjectName;
                    }
                    else if (command == 2){
                        String newProjectDescription;

                        System.out.println("Digite a nova descricao");
                        newProjectDescription = input.nextLine();

                        project.description = newProjectDescription;
                    }
                    else if (command == 3){
                        String newProjectStart;

                        System.out.println("Digite a nova data de inicio");
                        newProjectStart = input.nextLine();

                        project.start = newProjectStart;
                    }
                    else if (command == 4){
                        String newProjectEnd;

                        System.out.println("Digite a nova data de fim");
                        newProjectEnd = input.nextLine();

                        project.end = newProjectEnd;
                    }
                    else if (command == 5){
                        System.out.println("Status atual: '" + project.status + "'");
                        
                        if (project.status.equals("Em processo de criacao")){
                            System.out.println("Deseja iniciar o projeto?");
                            System.out.println("1) Sim");
                            System.out.println("2) Nao");
                            command = input.nextInt();
                            input.nextLine();

                            if (command == 1){
                                if (project.isReadyToInitiate()) project.initiateProject();
                                else{
                                    System.out.println("O projeto nao esta pronto para ser iniciado!");
                                    continue;
                                }
                            }
                            else continue;
                        }
                        else if (project.status.equals("Iniciado")){
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
                        else if (project.status.equals("Em andamento")){
                            System.out.println("Deseja concluir o projeto?");
                            System.out.println("1) Sim");
                            System.out.println("2) Nao");
                            command = input.nextInt();
                            input.nextLine();

                            if (command == 1){
                                if (project.canBeCompleted()) project.complete();
                                else{
                                    System.out.println("Projeto nao pode ser concluido");
                                    continue;
                                }
                            }
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

                        newProjectCoordinator = users.get(findUserIndexByName(users, newProjectCoordinatorName));

                        if (!newProjectCoordinator.canBeCoordinator){
                            System.out.println("Usuario nao e coordenador");
                            continue;
                        }

                        accountLoggedIn.accountOwner.projectsthatUserIsCoordinator.remove(project);
                        project.coordinator = newProjectCoordinator;
                        project.coordinator.projectsthatUserIsCoordinator.add(project);
                    }
                    else if (command == 7){
                        String userName;
                        int userIdx;
                        double userSalary;
                        User user;

                        System.out.println("Que usuario?");

                        userName = input.nextLine();
                        userIdx = findUserIndexByName(users, userName);
                        if (userIdx == NOT_FOUND){
                            System.out.println("Usuario nao encontrado");
                            continue;
                        }
                        user = users.get(userIdx);

                        System.out.println("Qual e o salario do usuario?");

                        userSalary = input.nextDouble();
                        input.nextLine();

                        project.peopleOnProject.add(user);
                        project.salary.add(userSalary);
                        user.projectsWorkedOn.add(project);
                    }
                    else if (command == 8){
                        String userName;
                        int userIdx;
                        User user;

                        System.out.println("Que usuario?");
                        userName = input.nextLine();
                        userIdx = findUserIndexByName(project.peopleOnProject, userName);
                        if (userIdx == NOT_FOUND){
                            userIdx = findUserIndexByName(project.borrowedUsers, userName);
                            if (userIdx == NOT_FOUND){
                                System.out.println("Usuario nao encontrado");
                                continue;
                            }
                            user = project.borrowedUsers.get(userIdx);
                            project.borrowedUsers.remove(user);
                        }
                        user = project.peopleOnProject.get(userIdx);

                        project.peopleOnProject.remove(user);
                        project.salary.remove(userIdx);
                    }
                    else if (command == 9){
                        String userName;
                        int userIdx;
                        double newSalary;

                        System.out.println("Que usuario?");
                        userName = input.nextLine();
                        userIdx = findUserIndexByName(project.peopleOnProject, userName);
                        if (userIdx == NOT_FOUND){
                            System.out.println("Usuario nao esta no projeto ou nao recebe salario");
                            continue;
                        }

                        System.out.println("Qual e o novo salario?");
                        newSalary = input.nextDouble();
                        input.nextLine();

                        project.salary.set(userIdx, newSalary);
                    }
                    else if (command == 99) continue;
                
                    Operation editedProjectOperation = new Operation(project, projectBeforeEditing);
                    done.push(editedProjectOperation);
                }
                else if (command == 4 && accountLoggedIn.accountOwner.canBeCoordinator){
                    String projectName, userName, targetProjectName;
                    User user;
                    Project project, targetProject, targetProjectBeforeEditing;
                    int projectIdx, userIdx, targetProjectIdx;

                    System.out.println("Usuario de que projeto?");
                    projectName = input.nextLine();
                    projectIdx = findProjectIndexByName(accountLoggedIn.accountOwner.projectsthatUserIsCoordinator, projectName);
                    if (projectIdx == NOT_FOUND){
                        System.out.println("Projeto nao encontrado");
                        continue;
                    }
                    project = accountLoggedIn.accountOwner.projectsthatUserIsCoordinator.get(projectIdx);

                    System.out.println("Que usuario?");
                    userName = input.nextLine();
                    userIdx = findUserIndexByName(project.peopleOnProject, userName);
                    if (userIdx == NOT_FOUND){
                        System.out.println("Usuario nao encontrado");
                        continue;
                    }
                    user = project.peopleOnProject.get(userIdx);

                    System.out.println("Para que projeto o usuario sera enviado?");
                    targetProjectName = input.nextLine();
                    targetProjectIdx = findProjectIndexByName(projects, targetProjectName);
                    if (targetProjectIdx == NOT_FOUND){
                        System.out.println("Projeto nao encontrado");
                        continue;
                    }
                    targetProject = projects.get(targetProjectIdx);

                    targetProjectBeforeEditing = new Project(targetProject.id, targetProject.description, targetProject.start, targetProject.end, targetProject.coordinator, targetProject.paymentPeriod);
                    targetProjectBeforeEditing.copyProjectInfoFrom(targetProject);

                    targetProject.borrowedUsers.add(user);
                    user.projectsThatUserWasLentTo.add(targetProject);

                    Operation editedProjectOperation = new Operation(targetProject, targetProjectBeforeEditing);
                    done.push(editedProjectOperation);
                }
                else if (command == (accountLoggedIn.accountOwner.canBeCoordinator ? 5 : 1)){
                    String projectName;

                    System.out.println("Que projeto?");

                    projectName = input.nextLine();

                    if (findProjectIndexByName(projects, projectName) != NOT_FOUND) System.out.println("Existe");
                    else System.out.println("Nao existe");
                }
                else if (command == (accountLoggedIn.accountOwner.canBeCoordinator ? 6 : 2)){
                    for (Project project : projects) {
                        System.out.println(project.id + " " + project.status);
                    }
                }
                else if (command == (accountLoggedIn.accountOwner.canBeCoordinator ? 7 : 3)){
                    for (Project project : projects){
                        System.out.println("Relatorio:");
                        System.out.println("Identificacao: " + project.id);
                        System.out.println("Descricao: " + project.description);
                        System.out.println("Inicio: " + project.start);
                        System.out.println("Fim: " + project.end);
                        System.out.println("Status: " + project.status);
                        System.out.println("Atividades do projeto: ");
                        for (Activity activity : project.activities) {
                            System.out.print("    " + activity.id);
                        }
                        System.out.println("Coordenador do projeto: " + project.coordinator.name + ", " + project.coordinator.type);
                        System.out.println("Usuarios no projeto: ");
                        for (User user : project.peopleOnProject) {
                            System.out.println("    " + user.name + ", " + user.type);
                        }
                        System.out.println("Usuarios em intercambio no projeto: ");
                        for (User user : project.borrowedUsers) {
                            System.out.println("    " + user.name + ", " + user.type);
                        }
                        System.out.println("\n");
                    }
                }
                else if (command == 99) continue;
                else{
                    System.out.println("Opcao invalida");
                    continue;
                }
            }
            else if (command == 2){
                if (accountLoggedIn.accountOwner.canBeCoordinator){
                    System.out.println("1) Criar Atividade");
                    System.out.println("2) Remover Atividade");
                    System.out.println("3) Editar Atividade");
                }
                System.out.println((accountLoggedIn.accountOwner.canBeCoordinator ? 4 : 1) + ") Procurar por Atividade");
                System.out.println((accountLoggedIn.accountOwner.canBeCoordinator ? 5 : 2) + ") Listar Atividades");
                System.out.println((accountLoggedIn.accountOwner.canBeCoordinator ? 6 : 3) + ") Relatorio de atividades");
                System.out.println("99) Voltar");

                command = input.nextInt();
                input.nextLine();

                if (command == 1 && accountLoggedIn.accountOwner.canBeCoordinator){
                    String id, description, start, end, leaderName, projectName;
                    int projectIdx, userIdx;
                    Activity newActivity;
                    Project project;
                    User leader = null;

                    System.out.println("Diga o id do projeto da atividade");
                    projectName = input.nextLine();
                    projectIdx = findProjectIndexByName(projects, projectName);
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
    
                    userIdx = findUserIndexByName(project.peopleOnProject, leaderName);
                    if (userIdx != NOT_FOUND){
                        leader = project.peopleOnProject.get(userIdx);
                    }
                    else{
                        userIdx = findUserIndexByName(project.borrowedUsers, leaderName);
                        if (userIdx != NOT_FOUND){
                            leader = project.borrowedUsers.get(userIdx);
                        }
                    }

                    if (userIdx == NOT_FOUND){
                        System.out.println("Usuario nao encontrado");
                        continue;
                    }
    
                    newActivity = new Activity(id, description, start, end, leader, project);
                    activities.add(newActivity);
                    leader.activitiesThatUserIsLeader.add(newActivity);
                    project.activities.add(newActivity);

                    Operation createActivityOperation = new Operation(1, newActivity);
                    done.push(createActivityOperation);
                }
                else if (command == 2 && accountLoggedIn.accountOwner.canBeCoordinator){
                    String activityName, projectName;
                    Project project;
                    Activity activityToRemove;
                    int activityIdx, projectIdx;

                    System.out.println("De que projeto e a atividade?");
                    projectName = input.nextLine();
                    projectIdx = findProjectIndexByName(accountLoggedIn.accountOwner.projectsthatUserIsCoordinator, projectName);
                    if (projectIdx == NOT_FOUND){
                        System.out.println("Projeto nao encontrado");
                        continue;
                    }
                    project = accountLoggedIn.accountOwner.projectsthatUserIsCoordinator.get(projectIdx);
    
                    System.out.println("Que atividade quer remover?");
                    activityName = input.nextLine();    
                    activityIdx = findActivityIndexByName(project.activities, activityName);
                    if (activityIdx == NOT_FOUND){
                        System.out.println("Atividade nao encontrada!");
                        continue;
                    }
                    activityToRemove = activities.get(activityIdx);

                    for (User user : activityToRemove.whoIsDoing) {
                        user.activitiesWorkedOn.remove(activityToRemove);
                    }
                    activityToRemove.leader.activitiesThatUserIsLeader.remove(activityToRemove);
                    activityToRemove.ownerProject.activities.remove(activityToRemove);

                    Operation removedActivityOperation = new Operation(2, activityToRemove);
                    done.push(removedActivityOperation);

                    activities.remove(activityToRemove);
                    System.out.println("Atividade '" + activityName + "' removida");
                }
                else if (command == 3 && accountLoggedIn.accountOwner.canBeCoordinator){
                    String activityName, projectName;
                    int activityIdx, projectIdx;
                    Activity activity, activityBeforeEditing;
                    Project project;

                    System.out.println("De que projeto e a atividade?");
                    projectName = input.nextLine();
                    projectIdx = findProjectIndexByName(accountLoggedIn.accountOwner.projectsthatUserIsCoordinator, projectName);
                    if (projectIdx == NOT_FOUND){
                        System.out.println("Projeto nao encontrado");
                        continue;
                    }
                    project = accountLoggedIn.accountOwner.projectsthatUserIsCoordinator.get(projectIdx);

                    System.out.println("Que Atividade?");
                    activityName = input.nextLine();
                    activityIdx = findActivityIndexByName(project.activities, activityName);
                    if (activityIdx == NOT_FOUND){
                        System.out.println("Atividade nao encontrada!");
                        continue;
                    }
                    activity = activities.get(activityIdx);

                    activityBeforeEditing = new Activity(activity.id, activity.description, activity.start, activity.end, activity.leader, activity.ownerProject);
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

                        activity.id = newActivityId;
                    }
                    else if (command == 2){
                        String newActivityDescription;

                        System.out.println("Digite a nova descricao");
                        System.out.println("(Digite 'SAIR' para sair)");
                        newActivityDescription = input.nextLine();

                        if (newActivityDescription.equals("SAIR")) continue;

                        activity.description = newActivityDescription;
                    }
                    else if (command == 3){
                        String newActivityStart;

                        System.out.println("Digite a nova data de inicio");
                        System.out.println("(Digite 'SAIR' para sair)");
                        newActivityStart = input.nextLine();

                        if (newActivityStart.equals("SAIR")) continue;

                        activity.start = newActivityStart;
                    }
                    else if (command == 4){
                        String newActivityEnd;

                        System.out.println("Digite a nova data de fim");
                        newActivityEnd = input.nextLine();

                        if (newActivityEnd.equals("SAIR")) continue;

                        activity.end = newActivityEnd;
                    }
                    else if (command == 5){
                        String newActivityLeaderName;
                        User newActivityLeader;
                        int userIdx;

                        System.out.println("Digite o nome do novo responsavel");
                        newActivityLeaderName = input.nextLine();
                        if (newActivityLeaderName.equals("SAIR")) continue;
                        
                        userIdx = findUserIndexByName(activity.whoIsDoing, newActivityLeaderName);
                        if (userIdx == NOT_FOUND){
                            System.out.println("Usuario nao encontrado");
                            continue;
                        }
                        newActivityLeader = activity.whoIsDoing.get(userIdx);
                        activity.leader = newActivityLeader;
                    }
                    else if (command == 6){
                        String userName, duty;
                        int userIdx;
                        User user;
                        System.out.println("Que usuario?");
                        userName = input.nextLine();
                        userIdx = findUserIndexByName(activity.ownerProject.peopleOnProject, userName);
                        if (userIdx == NOT_FOUND){
                            userIdx = findUserIndexByName(activity.ownerProject.borrowedUsers, userName);
                            if (userIdx == NOT_FOUND){
                                System.out.println("Usuario nao encontrado no projeto");
                                continue;
                            }
                            else{
                                user = activity.ownerProject.borrowedUsers.get(userIdx);
                            }
                        }
                        else user = activity.ownerProject.peopleOnProject.get(userIdx);

                        System.out.println("Qual dever do usuario?");
                        duty = input.nextLine();
                        activity.duties.add(duty);

                        user.activitiesWorkedOn.add(activity);
                        activity.whoIsDoing.add(user);
                    }
                    else if (command == 7){
                        int userIdx;
                        String userName;
                        User user;

                        System.out.println("Que usuario?");

                        userName = input.nextLine();

                        userIdx = findUserIndexByName(activity.whoIsDoing, userName);
                        if (userIdx == NOT_FOUND){
                            System.out.println("Usuario nao encontrado na atividade");
                            continue;
                        }
                        user = activity.whoIsDoing.get(userIdx);

                        user.activitiesWorkedOn.remove(activity);
                        activity.duties.remove(userIdx);
                        activity.whoIsDoing.remove(userIdx);
                    }
                    else if (command == 99) continue;
                
                    Operation editActivityOperation = new Operation(activity, activityBeforeEditing);
                    done.push(editActivityOperation);
                }
                else if (command == (accountLoggedIn.accountOwner.canBeCoordinator ? 4 : 1)){
                    String activityName;
    
                    System.out.println("Que atividade?");
    
                    activityName = input.nextLine();
    
                    if (findActivityIndexByName(activities, activityName) != NOT_FOUND) System.out.println("Existe");
                    else System.out.println("Nao existe");
                }
                else if (command == (accountLoggedIn.accountOwner.canBeCoordinator ? 5 : 2)){
                    for (Activity activity : activities) {
                        System.out.println(activity.id);
                    }
                }
                else if (command == (accountLoggedIn.accountOwner.canBeCoordinator ? 6 : 3)){
                    for (Activity activity : activities) {
                        System.out.println("Relatorio:");
                        System.out.println("Identificacao: " + activity.id);
                        System.out.println("Descricao: " + activity.description);
                        System.out.println("Inicio: " + activity.start);
                        System.out.println("Fim: " + activity.end);
                        System.out.println("Responsavel: " + activity.leader.name + ", " + activity.leader.type);
                        System.out.println("Tarefas:");
                        for (String tarefa : activity.duties) {
                            System.out.println("    " + tarefa);
                        }
                    }
                }
                else continue;
            }
            else if (command == 3){
                if (accountLoggedIn.accountOwner.canBeCoordinator){
                    System.out.println("1) Criar Usuario");
                    System.out.println("2) Remover Usuario");
                    System.out.println("3) Editar Usuario");
                }
                System.out.println((accountLoggedIn.accountOwner.canBeCoordinator ? 4 : 1) + ") Procurar por Usuario");
                System.out.println((accountLoggedIn.accountOwner.canBeCoordinator ? 5 : 2) + ") Listar Usuarios");
                System.out.println("99) Voltar");

                command = input.nextInt();
                input.nextLine();

                if (command == 1 && accountLoggedIn.accountOwner.canBeCoordinator){
                    String name, type;
                    User newUser;

                    System.out.println("Diga o nome e o tipo do usuario");
                    System.out.println("(Digite 'SAIR' para sair)");

                    name = input.nextLine();
    
                    if (name.equals("SAIR")) continue;
    
                    type = input.nextLine();
                    newUser = new User(name, type);

                    users.add(newUser);

                    Operation createUserOperation = new Operation(1, newUser);
                    done.push(createUserOperation);
                }
                else if (command == 2 && accountLoggedIn.accountOwner.canBeCoordinator){
                    String userName;
                    User userToRemove;
                    int idx;
    
                    System.out.println("Que usuario quer remover?");
                    userName = input.nextLine();
                    if (userName.equals("SAIR")) continue;
                    idx = findUserIndexByName(users, userName);
                    if (idx == NOT_FOUND){
                        System.out.println("Usuario nao encontrado!");
                        continue;
                    }
                    userToRemove = users.get(idx);
                    if (userToRemove.projectsthatUserIsCoordinator.size() != 0){
                        System.out.println("O usuario possui projetos que nao foram removidos!");
                        System.out.println("Nao e possivel excluir usuarios com projetos");
                        continue;
                    }

                    for (Project project : userToRemove.projectsWorkedOn) {
                        int userIdx = findUserIndexByName(project.peopleOnProject, userName);
                        project.peopleOnProject.remove(userIdx);
                        project.salary.remove(userIdx);
                    }
                    for (Project project : userToRemove.projectsThatUserWasLentTo){
                        project.borrowedUsers.remove(userToRemove);
                    }
                    for (Activity activity : userToRemove.activitiesWorkedOn) {
                        activity.whoIsDoing.remove(userToRemove);
                    }

                    Operation removeUserOperation = new Operation(2, userToRemove);
                    done.push(removeUserOperation);

                    users.remove(userToRemove);
                    System.out.println("Usuario '" + userName + "' removido");
                }
                else if (command == 3 && accountLoggedIn.accountOwner.canBeCoordinator){
                    String userName;
                    int userIdx;
                    User user, userBeforeEditing;
    
                    System.out.println("Que usuario?");
                    userName = input.nextLine();
                    if (userName.equals("SAIR")) continue;
                    userIdx = findUserIndexByName(users, userName);
                    if (userIdx == NOT_FOUND){
                        System.out.println("Usuario nao encontrado");
                        continue;
                    }
                    user = users.get(userIdx);
                
                    userBeforeEditing = new User(user.name, user.type);
                    userBeforeEditing.copyUserInfoFrom(user);

                    System.out.println("1) Adicionar usuario a um projeto");
                    System.out.println("2) Adicionar usuario a uma atividade");
                    System.out.println("3) Editar informacoes do usuario");
                    System.out.println("99) Sair");
    
                    command = input.nextInt();
                    input.nextLine();
                    if (command == 1){
                        String projectName;
                        int projectIdx;
                        double userSalary;
                        Project project;

                        System.out.println("Que projeto?");

                        projectName = input.nextLine();
                        projectIdx = findProjectIndexByName(accountLoggedIn.accountOwner.projectsthatUserIsCoordinator, projectName);
                        if (projectIdx == NOT_FOUND){
                            System.out.println("Projeto nao encontrado");
                            continue;
                        }
                        project = accountLoggedIn.accountOwner.projectsthatUserIsCoordinator.get(projectIdx);

                        System.out.println("Qual e o salario do usuario?");

                        userSalary = input.nextDouble();
                        input.nextLine();

                        project.peopleOnProject.add(user);
                        project.salary.add(userSalary);
                        user.projectsWorkedOn.add(project);
                    }
                    else if (command == 2){
                        String activityName, projectName;
                        int activityIdx, projectIdx;
                        Project project;
                        Activity activity;

                        System.out.println("De que projeto e a atividade?");
                        projectName = input.nextLine();
                        projectIdx = findProjectIndexByName(accountLoggedIn.accountOwner.projectsthatUserIsCoordinator, projectName);
                        if (projectIdx == NOT_FOUND){
                            System.out.println("Projeto nao encontrado");
                            continue;
                        }
                        project = accountLoggedIn.accountOwner.projectsthatUserIsCoordinator.get(projectIdx);

                        System.out.println("Que atividade?");
                        activityName = input.nextLine();
                        activityIdx = findActivityIndexByName(project.activities, activityName);
                        if (activityIdx == NOT_FOUND){
                            System.out.println("Atividade nao encontrada");
                            continue;
                        }
                        activity = activities.get(activityIdx);

                        user.activitiesWorkedOn.add(activity);
                        activity.whoIsDoing.add(user);
                    }
                    else if (command == 3){
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
    
                            user.name = newUserName;
                        }
                        else if (command == 2){
                            String newUserType;
    
                            System.out.println("Digite o novo tipo");
                            newUserType = input.nextLine();
    
                            if (newUserType.equals("SAIR")) continue;
    
                            user.type = newUserType;
                        }
                        else if (command == 99) continue;
                    }
                    else if (command == 99) continue;
                    else continue;
                
                    Operation editUserOperation = new Operation(user, userBeforeEditing);
                    done.push(editUserOperation);
                }
                else if (command == (accountLoggedIn.accountOwner.canBeCoordinator ? 4 : 1)){
                    String userName;
    
                    System.out.println("Que usuario?");
    
                    userName = input.nextLine();
    
                    if (findUserIndexByName(users, userName) == NOT_FOUND) System.out.println("Existe");
                    else System.out.println("Nao existe");
                }
                else if (command == (accountLoggedIn.accountOwner.canBeCoordinator ? 5 : 2)){
                    for (User user : users) {
                        System.out.println(user.name + ", " + user.type);
                        System.out.println("Pode ser coordenador: " + user.canBeCoordinator + "\n");
                    }
                }
                else continue;
            }
            else if (command == 4){
                int daysSinceLastPayment;
                System.out.println("Ha quanto tempo ocorreu o ultimo pagamento (em dias inteiros)?");

                daysSinceLastPayment = input.nextInt();
                input.nextLine();

                for (Project project : projects) {
                    if (daysSinceLastPayment >= project.paymentPeriod){
                        for (int i = 0; i < project.peopleOnProject.size(); i++) {
                            System.out.println(project.peopleOnProject.get(i).name + ", R$ " + project.salary.get(i));
                        }
                    }
                }
            }
            else if (command == 96){
                Operation poppedOperation;

                if (done.size() == 0){
                    System.out.println("Nao ha nenhuma operacao a ser desfeita");
                    continue;
                }
                poppedOperation = done.pop();

                if (poppedOperation.operationType == 1){
                    if (poppedOperation.secondaryOperationType == 1){
                        Project project = poppedOperation.createdProject;

                        project.coordinator.projectsthatUserIsCoordinator.remove(project);
                        projects.remove(project);
                    }
                    else if (poppedOperation.secondaryOperationType == 2){
                        Project project = poppedOperation.removedProject;

                        for (User user : project.borrowedUsers) {
                            user.projectsThatUserWasLentTo.add(project);
                        }
                        for (User user : project.peopleOnProject){
                            user.projectsWorkedOn.add(project);
                        }
                        for (Activity activity : project.activities) {
                            activities.add(activity);
                        }
                        project.coordinator.projectsthatUserIsCoordinator.add(project);
                    }
                    else if (poppedOperation.secondaryOperationType == 3){
                        Project editedProject = poppedOperation.editedProject;
                        Project projectBeforeEditing = poppedOperation.projectBeforeEditing;

                        for (User user : editedProject.borrowedUsers) {
                            user.projectsThatUserWasLentTo.remove(editedProject);
                            editedProject.borrowedUsers.remove(user);
                        }
                        for (User user : editedProject.peopleOnProject) {
                            user.projectsWorkedOn.remove(editedProject);
                            editedProject.peopleOnProject.remove(user);
                        }

                        editedProject.copyProjectInfoFrom(projectBeforeEditing);

                        for (User user : editedProject.peopleOnProject) {
                            user.projectsWorkedOn.add(editedProject);
                        }
                        for (User user : editedProject.borrowedUsers) {
                            user.projectsThatUserWasLentTo.add(editedProject);
                        }
                    }
                }
                else if (poppedOperation.operationType == 2){
                    if (poppedOperation.secondaryOperationType == 1){
                        Activity activity = poppedOperation.createdActivity;

                        activity.leader.activitiesThatUserIsLeader.remove(activity);
                        activity.ownerProject.activities.remove(activity);

                        activities.remove(activity);
                    }
                    else if (poppedOperation.secondaryOperationType == 2){
                        Activity activity = poppedOperation.removedActivity;

                        for (User user : activity.whoIsDoing) {
                            user.activitiesWorkedOn.add(activity);
                        }
                        activity.leader.activitiesThatUserIsLeader.add(activity);
                        activity.ownerProject.activities.add(activity);
                    }
                    else if (poppedOperation.secondaryOperationType == 3){
                        Activity editedActivity = poppedOperation.editedActivity;
                        Activity activityBeforeEditing = poppedOperation.activityBeforeEditing;

                        for (User user : editedActivity.whoIsDoing) {
                            user.activitiesWorkedOn.remove(editedActivity);
                            editedActivity.whoIsDoing.remove(user);
                        }
                        editedActivity.copyActivityInfoFrom(activityBeforeEditing);
                        for (User user : editedActivity.whoIsDoing) {
                            user.activitiesWorkedOn.add(editedActivity);
                        }
                    }
                }
                else if (poppedOperation.operationType == 3){
                    if (poppedOperation.secondaryOperationType == 1){
                        User user = poppedOperation.createdUser;

                        users.remove(user);
                    }
                    else if (poppedOperation.secondaryOperationType == 2){
                        User user = poppedOperation.removedUser;

                        for (Project project : user.projectsThatUserWasLentTo) {
                            project.borrowedUsers.add(user);
                        }
                        for (Project project : user.projectsWorkedOn) {
                            project.peopleOnProject.add(user);
                        }
                        for (Project project : user.projectsthatUserIsCoordinator) {
                            project.coordinator = user;
                        }
                        for (Activity activity : user.activitiesThatUserIsLeader) {
                            activity.leader = user;
                        }
                        for (Activity activity : user.activitiesWorkedOn) {
                            activity.whoIsDoing.add(user);
                        }

                        users.add(user);
                    }
                    else if (poppedOperation.secondaryOperationType == 3){
                        User userBeforeEditing = poppedOperation.userBeforeEditing;
                        User editedUser = poppedOperation.editedUser;

                        for (Activity activity : editedUser.activitiesWorkedOn) {
                            activity.whoIsDoing.remove(editedUser);
                        }
                        editedUser.copyUserInfoFrom(userBeforeEditing);

                        for (Activity activity : editedUser.activitiesWorkedOn) {
                            activity.whoIsDoing.add(editedUser);
                        }
                    }
                }

                undone.push(poppedOperation);
            }
            else if (command == 97){

            }
            else if (command == 98){
                isLoggedIn = false;
                accountLoggedIn = null;
            }
            else if (command == 99) break;
        }

        input.close();
    }
}