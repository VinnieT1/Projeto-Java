public abstract class User implements Menu{
    private String name;
    private String type;
    private Account account;

    public User(String name, String type){
        this.name = name;
        this.type = type;
    }

    public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String displayMainMenu(){
        return (    
            "1) Projetos" +
            "\n2) Atividades" +
            "\n3) Usuarios" +
            "\n4) Log out" +
            "\n5) Sair"
        );
    }

    public String displayProjectMenu(){
        return (
            "1) Procurar por Projeto" +
            "\n2) Listar Projetos" + 
            "\n3) Relatorio dos projetos"
        );
    }

    // TODO
    public String displayActivityMenu(){
        return (
            "1) Procurar por Atividade" +
            "2) Listar Atividades" +
            "3) Relatorio de Atividades"
        );
    }

    // TODO
    public String displayUserMenu(){
        
    }

    public boolean canBeCoordinator(){
        return (this.type.equals("professor") || this.type.equals("coordenador"));
    }

    @Override
    public String toString(){
        return (
            this.name + ", " + this.type
        );
    }

}
