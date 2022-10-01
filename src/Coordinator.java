import java.util.ArrayList;

public class Coordinator extends User{
    private ArrayList<Project> projectsThatUserIsCoordinator;

    public Coordinator(String name, String type){
        super(name, type);
        this.projectsThatUserIsCoordinator = new ArrayList<Project>();
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
            "4) Criar Projeto" +
            "5) Remover Projeto" +
            "6) Editar Projeto" +
            "7) Enviar usuario por intercambio"
        );
    }

    @Override
    public String displayActivityMenu(){
        return (
            super.displayActivityMenu() +
            "4) Criar Atividade" +
            "5) Remover Atividade" +
            "6) Editar Atividade"
        );
    }

    @Override
    public String displayUserMenu(){
        return (
            super.displayUserMenu() +
            "4) Criar Usuario" +
            "5) Remover Usuario" +
            "6) Editar Usuario"
        );
    }

}
