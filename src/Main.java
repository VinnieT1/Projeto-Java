import java.util.ArrayList;
import java.util.Scanner;

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

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        ArrayList<Project> projects = new ArrayList<Project>();
        ArrayList<Activity> activities = new ArrayList<Activity>();
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<Account> accounts = new ArrayList<Account>();
        int command;
        boolean isLoggedIn = false;
        Account accountLoggedIn = null;

        while(true){
            if (!isLoggedIn){
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
            System.out.println("98) Log out");
            System.out.println("99) Sair");

            command = input.nextInt();
            input.nextLine();

            if (command == 1){
                if (accountLoggedIn.accountOwner.canBeCoordinator){
                    System.out.println("1) Criar Projeto");
                    System.out.println("2) Remover Projeto");
                    System.out.println("3) Editar Projeto");
                }
                System.out.println((accountLoggedIn.accountOwner.canBeCoordinator ? 4 : 1) + ") Procurar por Projeto");
                System.out.println((accountLoggedIn.accountOwner.canBeCoordinator ? 5 : 2) + ") Listar Projetos");
                System.out.println("99) Voltar");
                
                command = input.nextInt();
                input.nextLine();

                if (command == 1 && accountLoggedIn.accountOwner.canBeCoordinator){
                    System.out.println("Diga a identificacao e, depois, a descricao");
                    System.out.println("(Digite 'SAIR' para sair)");
    
                    String id, description;
                    Project newProject;

                    id = input.nextLine();
    
                    if (id.equals("SAIR")) continue;
    
                    description = input.nextLine();

                    newProject = new Project(id, description, "none", "none", accountLoggedIn.accountOwner);

                    accountLoggedIn.accountOwner.projectsWorkedOn.add(newProject);
                    projects.add(newProject);
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
    
                    if (idx == NOT_FOUND) System.out.println("Projeto nao encontrado!");
                    else{
                        projectToRemove = projects.get(idx);

                        for (User user : projectToRemove.peopleOnProject) {
                            user.projectsWorkedOn.remove(projectToRemove);
                        }

                        projects.remove(idx);
                        System.out.println("Projeto " + projectName + " removido");
                    }
                }
                else if (command == 3 && accountLoggedIn.accountOwner.canBeCoordinator){
                    System.out.println("1) Adicionar atividades ao projeto");
                    System.out.println("2) Adicionar usuarios no projeto");
                    System.out.println("3) Editar informacoes de um projeto");
                    System.out.println("99) Voltar");

                    command = input.nextInt();
                    input.nextLine();

                    
                    if (command == 3){
                        String projectName;
                        int idx;
                        Project project;
                        System.out.println("Que projeto?");

                        projectName = input.nextLine();

                        if (projectName.equals("SAIR")) continue;

                        idx = findProjectIndexByName(accountLoggedIn.accountOwner.projectsWorkedOn, projectName);

                        if (idx == NOT_FOUND){
                            System.out.println("Nao encontrado");
                            continue;
                        }

                        project = accountLoggedIn.accountOwner.projectsWorkedOn.get(idx);

                        System.out.println("1) Identificacao");
                        System.out.println("2) Descricao");
                        System.out.println("3) Inicio");
                        System.out.println("4) Fim");
                        System.out.println("5) Status");
                        System.out.println("6) Coordenador");
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

                            projects.get(idx).start = newProjectStart;
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
                                    else System.out.println("O projeto nao esta pronto para ser iniciado!");
                                }
                                else if (command == 2) continue;
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

                            accountLoggedIn.accountOwner.projectsWorkedOn.remove(project);
                            project.coordinator = newProjectCoordinator;
                        }
                        else if (command == 99) continue;
                    }
                    else if (command == 99) continue;
                }
                else if (command == (accountLoggedIn.accountOwner.canBeCoordinator ? 4 : 1)){
                    String projectName;

                    System.out.println("Que projeto?");

                    projectName = input.nextLine();

                    if (findProjectIndexByName(projects, projectName) != NOT_FOUND) System.out.println("Existe");
                    else System.out.println("Nao existe");
                }
                else if (command == (accountLoggedIn.accountOwner.canBeCoordinator ? 5 : 2)){
                    for (Project project : projects) {
                        System.out.println(project.id + " " + project.status);
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
                System.out.println("99) Voltar");

                command = input.nextInt();
                input.nextLine();

                if (command == 1 && accountLoggedIn.accountOwner.canBeCoordinator){
                    String id, description, start, end, leaderName;
                    Activity newActivity;
                    User leader = users.get(0);
    
                    System.out.println("Diga o id, descricao, inicio, fim e nome do responsavel pela atividade");
    
                    id = input.nextLine();
    
                    if (id.equals("SAIR")) continue;
    
                    description = input.nextLine();
                    start = input.nextLine();
                    end = input.nextLine();
                    leaderName = input.nextLine();
    
                    for (User user : users) {
                        if (user.name.equals(leaderName) && user.type.equals("coordenador")){
                            leader = user;
                        }
                    }
    
                    newActivity = new Activity(id, description, start, end, leader);
                    activities.add(newActivity);
                }
                else if (command == 2 && accountLoggedIn.accountOwner.canBeCoordinator){
                    String activityName;
                    Activity activityToRemove;
                    int idx;
    
                    System.out.println("Que atividade quer remover?");
    
                    activityName = input.nextLine();
    
                    if (activityName.equals("SAIR")) continue;
    
                    idx = findActivityIndexByName(activities, activityName);
    
                    if (idx == NOT_FOUND) System.out.println("Atividade nao encontrada!");
                    else{
                        activityToRemove = activities.get(idx);

                        for (User user : activityToRemove.whoIsDoing) {
                            user.activitiesWorkedOn.remove(activityToRemove);
                        }

                        activities.remove(idx);
                        System.out.println("Atividade '" + activityName + "' removida");
                    }
                }
                else if (command == 3 && accountLoggedIn.accountOwner.canBeCoordinator){
                    System.out.println("1) Adicionar atividade a um projeto");
                    System.out.println("2) Adicionar deveres a uma atividade");
                    System.out.println("3) Adicionar usuarios a uma atividade");
                    System.out.println("4) Editar informacoes de uma atividade");
                    System.out.println("99) Sair");

                    command = input.nextInt();
                    input.nextLine();

                    if (command == 4){
                        String activityName;
                        int idx;

                        System.out.println("Que Atividade?");
                        System.out.println("(Digite 'SAIR' para voltar)");

                        activityName = input.nextLine();

                        if (activityName.equals("SAIR")) continue;

                        idx = findActivityIndexByName(activities, activityName);

                        if (idx == NOT_FOUND){
                            System.out.println("Nao encontrada!");
                            continue;
                        }

                        System.out.println("1) Identificacao");
                        System.out.println("2) Descricao");
                        System.out.println("3) Inicio");
                        System.out.println("4) Fim");
                        System.out.println("5) Responsavel");
                        System.out.println("99) Sair");

                        command = input.nextInt();
                        input.nextLine();

                        if (command == 1){
                            String newActivityId;
    
                            System.out.println("Digite a nova identificacao");
                            newActivityId = input.nextLine();
    
                            if (newActivityId.equals("SAIR")) continue;
    
                            activities.get(idx).id = newActivityId;
                        }
                        else if (command == 2){
                            String newActivityDescription;
    
                            System.out.println("Digite a nova descricao");
                            newActivityDescription = input.nextLine();
    
                            if (newActivityDescription.equals("SAIR")) continue;
    
                            activities.get(idx).description = newActivityDescription;
                        }
                        else if (command == 3){
                            String newActivityStart;
    
                            System.out.println("Digite a nova data de inicio");
                            newActivityStart = input.nextLine();
    
                            if (newActivityStart.equals("SAIR")) continue;
    
                            activities.get(idx).start = newActivityStart;
                        }
                        else if (command == 4){
                            String newActivityEnd;
    
                            System.out.println("Digite a nova data de fim");
                            newActivityEnd = input.nextLine();
    
                            if (newActivityEnd.equals("SAIR")) continue;
    
                            activities.get(idx).end = newActivityEnd;
                        }
                        else if (command == 5){
                            String newActivityLeaderName;
                            User newActivityLeader;
    
                            System.out.println("Digite a nova data de fim");
                            newActivityLeaderName = input.nextLine();
    
                            if (newActivityLeaderName.equals("SAIR")) continue;
    
                            newActivityLeader = users.get(findUserIndexByName(users, newActivityLeaderName));
    
                            activities.get(idx).leader = newActivityLeader;
                        }
                        else if (command == 99) continue;
                    }
                    else if (command == 99) continue;
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
                else if (command == 99) continue;
            }
            else if (command == 3){
                System.out.println("1) Criar Usuario");
                System.out.println("2) Remover Usuario");
                System.out.println("3) Editar Usuario");
                System.out.println("4) Procurar por Usuario");
                System.out.println("5) Listar Usuarios");
                System.out.println("99) Voltar");

                command = input.nextInt();
                input.nextLine();

                if (command == 1){
                    String name, type;
                    User newUser;

                    System.out.println("Diga o nome e o tipo do usuario");
                    System.out.println("(Digite 'SAIR' para sair)");

                    name = input.nextLine();
    
                    if (name.equals("SAIR")) continue;
    
                    type = input.nextLine();
                    newUser = new User(name, type);

                    users.add(newUser);
                }
                else if (command == 2){
                    String userName;
                    User userToRemove;
                    int idx;
    
                    System.out.println("Que usuario quer remover?");
    
                    userName = input.nextLine();
    
                    if (userName.equals("SAIR")) continue;
    
                    idx = findUserIndexByName(users, userName);
    
                    if (idx == NOT_FOUND) System.out.println("Usuario nao encontrado!");
                    else{
                        userToRemove = users.get(idx);

                        for (Project project : userToRemove.projectsWorkedOn) {
                            project.peopleOnProject.remove(userToRemove);
                        }
                        for (Activity activity : userToRemove.activitiesWorkedOn) {
                            activity.whoIsDoing.remove(userToRemove);
                        }

                        users.remove(idx);
                        System.out.println("Usuario '" + userName + "' removido");
                    }
                }
                else if (command == 3){
                    String userName;
                    int idx;
    
                    System.out.println("Que usuario?");
    
                    userName = input.nextLine();
    
                    if (userName.equals("SAIR")) continue;
    
                    idx = findUserIndexByName(users, userName);
                    
                    System.out.println("1) Adicionar usuario a um projeto");
                    System.out.println("2) Adicionar usuario a uma atividade");
                    System.out.println("3) Editar informacoes do usuario");
                    System.out.println("99) Sair");
    
                    command = input.nextInt();
                    input.nextLine();
    
                    if (command == 99) continue;
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
    
                            users.get(idx).name = newUserName;
                        }
                        else if (command == 2){
                            String newUserType;
    
                            System.out.println("Digite o novo tipo");
                            newUserType = input.nextLine();
    
                            if (newUserType.equals("SAIR")) continue;
    
                            users.get(idx).type = newUserType;
                        }
                        else if (command == 99) continue;
                    }
                }
                else if (command == 4){
                    String userName;
    
                    System.out.println("Que usuario?");
    
                    userName = input.nextLine();
    
                    if (findUserIndexByName(users, userName) == NOT_FOUND) System.out.println("Existe");
                    else System.out.println("Nao existe");
                }
                else if (command == 5){
                    for (User user : users) {
                        System.out.println(user.name + ", " + user.type);
                        System.out.println("Pode ser coordenador: " + user.canBeCoordinator + "\n");
                    }
                }
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