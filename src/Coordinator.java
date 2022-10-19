import java.util.ArrayList;

public class Coordinator extends User{
    private ArrayList<Project> projectsThatUserIsCoordinator;

    public Coordinator(String name, String type){
        super(name, type);
        this.projectsThatUserIsCoordinator = new ArrayList<Project>();
    }

    public Coordinator(){
        super();
    }

    public ArrayList<Project> getProjectsThatUserIsCoordinator() {
        return this.projectsThatUserIsCoordinator;
    }

    public void setProjectsThatUserIsCoordinator(ArrayList<Project> projectsThatUserIsCoordinator) {
        this.projectsThatUserIsCoordinator = projectsThatUserIsCoordinator;
    }

    @Override
    public String displayMainMenu(){
        return (
            super.displayMainMenu() +
            "\n6) Undo" +
            "\n7) Redo" +
            "\n8) Pagar bolsas"
        );
    }

    @Override
    public String displayProjectMenu(){
        return (
            super.displayProjectMenu() +
            "\n4) Criar Projeto" +
            "\n5) Remover Projeto" +
            "\n6) Editar Projeto" +
            "\n7) Enviar usuario por intercambio"
        );
    }

    @Override
    public String displayActivityMenu(){
        return (
            super.displayActivityMenu() +
            "\n4) Criar Atividade" +
            "\n5) Remover Atividade" +
            "\n6) Editar Atividade"
        );
    }

    public String displayActivityEditingMenu(){
        return (
            "1) Identificacao" +
            "\n2) Descricao" +
            "\n3) Inicio" +
            "\n4) Fim" +
            "\n5) Responsavel" +
            "\n6) Adicionar usuario a atividade" +
            "\n7) Remover usuario da atividade" +
            "\n99) Sair"
        );
    }

    @Override
    public String displayUserMenu(){
        return (
            super.displayUserMenu() +
            "\n4) Criar Usuario" +
            "\n5) Remover Usuario" +
            "\n6) Editar Usuario"
        );
    }

    public String displayUserEditingMenu(){
        return (
            "1) Adicionar usuario a um projeto" +
            "\n2) Adicionar usuario a uma atividade" +
            "\n3) Editar informacoes do usuario" +
            "\n4) Sair"
        );
    }

    @Override
    public void remove(ArrayList<User> users) {
        for (Project project : this.projectsThatUserIsCoordinator) {
            project.setCoordinator(null);
        }
        users.remove(this);
    }   

    @Override
    public void undoRemove(ArrayList<User> users) {
        for (Project project : this.projectsThatUserIsCoordinator) {
            project.setCoordinator(this);
        }
        users.add(this);
    }

    public void copyUserInfoFrom(User coordinator){
        super.setName(coordinator.getName());
        super.setType(coordinator.getType());
        super.setAccount(coordinator.getAccount());

        this.projectsThatUserIsCoordinator.clear();
        for (Project project : ((Coordinator)coordinator).projectsThatUserIsCoordinator) {
            this.projectsThatUserIsCoordinator.add(project);
        }
    }
}
