import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static int findProjectIndexByName(ArrayList<Project> projects, String name){
        for(int i = 0; i < projects.size(); i++){
            if (projects.get(i).id.equals(name)) return i;
        }

        return -1;
    }

    public static int findActivityIndexByName(ArrayList<Activity> activities, String name){
        for(int i = 0; i < activities.size(); i++){
            if (activities.get(i).id.equals(name)) return i;
        }

        return -1;
    }

    public static int findUserIndexByName(ArrayList<User> users, String name){
        for(int i = 0; i < users.size(); i++){
            if (users.get(i).name.equals(name)) return i;
        }

        return -1;
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        ArrayList<Project> projects = new ArrayList<Project>();
        ArrayList<Activity> activities = new ArrayList<Activity>();
        ArrayList<User> users = new ArrayList<User>();
        int command;

        while(true){
            System.out.println("1) Criar projeto");
            System.out.println("2) Criar atividade");
            System.out.println("3) Criar usuario");
            System.out.println("4) Remover projeto");
            System.out.println("5) Remover atividade");
            System.out.println("6) Remover usuario");
            System.out.println("7) Editar projeto");
            System.out.println("8) Editar Atividade");
            System.out.println("9) Editar Usuario");
            System.out.println("10) Procurar por projeto");
            System.out.println("11) Procurar por atividade");
            System.out.println("12) Procurar por usuario");
            System.out.println("99) Sair");

            command = input.nextInt();
            input.nextLine();

            if (command == 1){
                System.out.println("\n\n\nDiga a identificacao, descricao, inicio, fim e nome do coordenador");

                String id, description, start, end, coordinatorName;
                User coordinator;
                id = input.nextLine();

                if (id.equals("SAIR")) continue;

                description = input.nextLine();
                start = input.nextLine();
                end = input.nextLine();
                coordinatorName = input.nextLine();
                
                coordinator = users.get(findUserIndexByName(users, coordinatorName));

                projects.add(new Project(id, description, start, end, coordinator));
            }
            else if (command == 2){
                String id, description, start, end, leaderName;
                Activity newActivity;
                User leader = users.get(0);

                System.out.println("\n\n\nDiga o id, descricao, inicio, fim e nome do responsavel pela atividade");

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
            else if (command == 3){
                String name, type;
                System.out.println("\n\n\nDiga o nome e o tipo do usuario");

                name = input.nextLine();

                if (name.equals("SAIR")) continue;

                type = input.nextLine();

                users.add(new User(name, type));
            }
            else if (command == 4){
                String projectName;
                int idx;

                System.out.println("\n\n\nQue projeto quer remover?");

                projectName = input.nextLine();

                if (projectName.equals("SAIR")) continue;

                idx = findProjectIndexByName(projects, projectName);

                if (idx == -1) System.out.println("Projeto nao encontrado!");
                else{
                    projects.remove(idx);
                    System.out.println("Projeto " + projectName + " removido");
                }

            }
            else if (command == 5){
                String activityName;
                int idx;

                System.out.println("\n\n\nQue atividade quer remover?");

                activityName = input.nextLine();

                if (activityName.equals("SAIR")) continue;

                idx = findActivityIndexByName(activities, activityName);

                if (idx == -1) System.out.println("Atividade nao encontrada!");
                else{
                    activities.remove(idx);
                    System.out.println("Atividade '" + activityName + "' removida");
                }
            }
            else if (command == 6){
                String userName;
                int idx;

                System.out.println("\n\n\nQue usuario quer remover?");

                userName = input.nextLine();

                if (userName.equals("SAIR")) continue;

                idx = findUserIndexByName(users, userName);

                if (idx == -1) System.out.println("Usuario nao encontrado!");
                else{
                    users.remove(idx);
                    System.out.println("Usuario '" + userName + "' removido");
                }
            }
            else if (command == 7){
                System.out.println("1) Adicionar atividades ao projeto");
                System.out.println("2) Adicionar usuarios no projeto");
                System.out.println("3) Editar informacoes de um projeto");
                System.out.println("99) Voltar");

                command = input.nextInt();
                input.nextLine();

                if (command == 99) continue;
                else if (command == 3){
                    String projectName;
                    int idx;
                    System.out.println("Que projeto?");

                    projectName = input.nextLine();

                    if (projectName.equals("SAIR")) continue;

                    idx = findProjectIndexByName(projects, projectName);

                    if (idx == -1){
                        System.out.println("Nao encontrado");
                        continue;
                    }

                    System.out.println("1) Identificacao");
                    System.out.println("2) Descricao");
                    System.out.println("3) Inicio");
                    System.out.println("4) Fim");
                    System.out.println("5) Status");
                    System.out.println("6) Coordenador");
                    System.out.println("99) Sair");

                    command = input.nextInt();
                    input.nextLine();

                    if (command == 99) continue;
                    else if (command == 1){
                        String newProjectName;

                        System.out.println("Digite a nova identificacao");
                        newProjectName = input.nextLine();

                        projects.get(idx).id = newProjectName;
                    }
                    else if (command == 2){
                        String newProjectDescription;

                        System.out.println("Digite a nova descricao");
                        newProjectDescription = input.nextLine();

                        projects.get(idx).description = newProjectDescription;
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

                        projects.get(idx).end = newProjectEnd;
                    }
                    else if (command == 5){
                        String newProjectStatus;

                        System.out.println("Digite o novo status");
                        newProjectStatus = input.nextLine();

                        projects.get(idx).status = newProjectStatus;
                    }
                    else if (command == 6){
                        String newProjectCoordinatorName;
                        User newProjectCoordinator;

                        System.out.println("Digite o nome do novo coordenador");
                        newProjectCoordinatorName = input.nextLine();

                        newProjectCoordinator = users.get(findUserIndexByName(users, newProjectCoordinatorName));

                        projects.get(idx).coordinator = newProjectCoordinator;
                    }
                }
            }
            else if (command == 8){
                System.out.println("1) Adicionar deveres a uma atividade");
                System.out.println("2) Adicionar usuarios a uma atividade");
                System.out.println("3) Editar informacoes de uma atividade");
                System.out.println("99) Voltar");

                command = input.nextInt();
                input.nextLine();

                if (command == 3){
                    String activityName;
                    int idx;
                    System.out.println("Que Atividade?");

                    activityName = input.nextLine();

                    if (activityName.equals("SAIR")) continue;

                    idx = findActivityIndexByName(activities, activityName);

                    if (idx == -1){
                        System.out.println("Nao encontrado!");
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

                    if (command == 99) continue;
                    else if (command == 1){
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
                }
                else if (command == 99) continue;
            }
            else if (command == 9){
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

                    if (command == 99) continue;
                    else if (command == 1){
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
                }
            }
            else if (command == 10){
                String projectName;

                System.out.println("Que projeto?");

                projectName = input.nextLine();

                if (findProjectIndexByName(projects, projectName) != -1) System.out.println("Existe");
                else System.out.println("Nao existe");
            }
            else if (command == 11){
                String activityName;

                System.out.println("Que atividade?");

                activityName = input.nextLine();

                if (findActivityIndexByName(activities, activityName) != -1) System.out.println("Existe");
                else System.out.println("Nao existe");
            }
            else if (command == 10){
                String userName;

                System.out.println("Que usuario?");

                userName = input.nextLine();

                if (findUserIndexByName(users, userName) == -1) System.out.println("Existe");
                else System.out.println("Nao existe");
            }

            else if (command == 96){
                for (Project project : projects) {
                    System.out.println(project.id + " " + project.status);
                }
            }
            else if (command == 97){
                for (Activity activity : activities) {
                    System.out.println(activity.id);
                }
            }
            else if (command == 98){
                for (User user : users) {
                    System.out.println(user.name + " " + user.type);
                }
            }
            else if (command == 99) break;
        }

        input.close();
    }
}
// System.out.println("Para os profissionais envolvidos, digite 'Sair' para parar de adicionar profissionais");
// System.out.println("Para cada profissional, digite seu nome, seu tipo, a");

// while(true){
//     UserName = input.nextLine();

//     if (UserName.equals("Sair")) break;

//     UserType = input.nextLine();

//     newProject.peopleOnProject.add(new User(UserName, UserType));
// }