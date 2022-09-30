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

    // TODO
    @Override
    public String displayActivityMenu(){
        
    }

    // TODO
    @Override
    public String displayUserMenu(){
        super.displayUserMenu();
        // TODO
    }

}
