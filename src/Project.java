import java.util.ArrayList;

public class Project {
    private String id;
	private String description;
	private String start;
	private String end;
	private String status;
	private Coordinator coordinator;
	private ArrayList<Student> peopleOnProject;
	private ArrayList<Activity> activities;
	private ArrayList<Double> salary;
	private ArrayList<Student> borrowedUsers;
	private int paymentPeriod;

	public Project(String id, String description, Coordinator coordinator, int paymentPeriod){
		this.id = id;
		this.description = description;
		this.start = "Sem data";
		this.end = "Sem data";
		this.status = "Em processo de criacao";
		this.coordinator = coordinator;
		this.peopleOnProject = new ArrayList<Student>();
        this.activities = new ArrayList<Activity>();
        this.salary = new ArrayList<Double>();
        this.borrowedUsers = new ArrayList<Student>();
        this.paymentPeriod = paymentPeriod;
	}

	public Project(){

	}

	public void copyProjectInfoFrom(Project project){
        this.id = project.id;
        this.description = project.description;
        this.start = project.start;
        this.end = project.end;
        this.status = project.status;
        this.coordinator = project.coordinator;
        this.paymentPeriod = project.paymentPeriod;

        this.activities.clear();
        for (Activity activity : project.activities) {
            this.activities.add(activity);
        }
        this.peopleOnProject.clear();
        for (Student student : project.peopleOnProject) {
            this.peopleOnProject.add(student);
        }
        this.salary.clear();
        for (Double salary : project.salary) {
            this.salary.add(salary);
        }
        this.borrowedUsers.clear();
        for (Student student : project.borrowedUsers) {
            this.borrowedUsers.add(student);
        }
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStart() {
		return this.start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return this.end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Coordinator getCoordinator() {
		return this.coordinator;
	}

	public void setCoordinator(Coordinator coordinator) {
		this.coordinator = coordinator;
	}

	public ArrayList<Student> getPeopleOnProject() {
		return this.peopleOnProject;
	}

	public void setPeopleOnProject(ArrayList<Student> peopleOnProject) {
		this.peopleOnProject = peopleOnProject;
	}

	public ArrayList<Activity> getActivities() {
		return this.activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	public ArrayList<Double> getSalary() {
		return this.salary;
	}

	public void setSalary(ArrayList<Double> salary) {
		this.salary = salary;
	}

	public ArrayList<Student> getBorrowedUsers() {
		return this.borrowedUsers;
	}

	public void setBorrowedUsers(ArrayList<Student> borrowedUsers) {
		this.borrowedUsers = borrowedUsers;
	}

	public int getPaymentPeriod() {
		return this.paymentPeriod;
	}

	public void setPaymentPeriod(int paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	@Override
	public String toString(){
		return (	
			"Identificacao: " + this.id +
			"\nDescricao: " + this.description +
			"\nCoordenador: " + this.coordinator
		);
	}

	public String report(){
		String result = (
			this.toString() +
			"\nInicio: " + this.start +
			"\nFim: " + this.end +
			"\nStatus: " + this.status
		);
		result.concat("Atividades do projeto:");
		for (Activity activity : this.activities) {
			result.concat(
				"\n\t" + activity.toString()
			);
		}
		result.concat("\nParticipantes do projeto:");
		for (User user : this.peopleOnProject) {
			result.concat(
				"\n\t" + user.toString()
			);
		}
		result.concat("\nParticipantes em intercambio:");
		for (User user : this.borrowedUsers) {
			result.concat(
				"\n\t" + user.toString()
			);
		}
		

		return result;
	}

	public RemoveProject remove(ArrayList<Project> projects){
		if (this.activities.size() != 0){
			System.out.println("Nao e possivel remover projetos com atividades! Remova as atividades primeiro");
			return null;
		}

		RemoveProject removedProjectOperation = new RemoveProject(this);
		
		projects.remove(this);
		this.getCoordinator().getProjectsThatUserIsCoordinator().remove(this);
		for (Student student : this.getPeopleOnProject()) {
			student.getProjectsWorkedOn().remove(this);
		}
		for (Student student : this.getBorrowedUsers()) {
			student.getProjectsThatUserWasLentTo().remove(this);
		}

		return removedProjectOperation;
	}

	public String displayEditingMenu(){
		return (
			"1) Identificacao" +
			"2) Descricao" +
			"3) Inicio" +
			"4) Fim" +
			"5) Status" +
			"6) Coordenador" +
			"7) Adicionar usuarios no projeto" +
			"8) Remover usuarios do projeto" +
			"9) Mudar salario de um usuario" +
			"10) Sair"
		);
	}

	public boolean isReadyToInitiate(){
		return (!this.start.equals("Sem data") && !this.end.equals("Sem data") && this.status.equals("Em processo de criacao"));
	}

	public void initiateProject(){
		if (this.isReadyToInitiate()) this.status = "Iniciado";
	}

	public void goForward(){
		if (!this.status.equals("Iniciado")){
			System.out.println("Nao eh possivel dar inicio ao projeto! (O projeto ja foi iniciado ou nao esta pronto ainda)");
			return;
		}

		this.status = "Em andamento";
	}

	public boolean canBeCompleted(){
		return this.activities.isEmpty();
	}

	public void complete(){
		if (this.canBeCompleted()) this.status = "Concluido";
		else System.out.println("O projeto nao pode ser concluido");
	}

}