package recognizer;
import java.util.ArrayList;
import java.util.Iterator;


public class Model {
	private String sourcePath;
	private String path;
	private ArrayList<Variable> variables;
	private ArrayList<Task> tasks;
	private ArrayList<Constraint> constraints;
	private ArrayList<Expression> expressions;
	private int totalNbOfCls;
	private int nbOfRecCls;
	private int nbOfRecTasks;
	private boolean seen;
	private int nbOfIdentCls;
	private int nbOfIdentTasks;

	public int getNbOfIdentCls() {
		return nbOfIdentCls;
	}
	public void setNbOfIdentCls(int nbOfIdentCls) {
		this.nbOfIdentCls = nbOfIdentCls;
	}
	public int getNbOfIdentTasks() {
		return nbOfIdentTasks;
	}
	public void setNbOfIdentTasks(int nbOfIdentTasks) {
		this.nbOfIdentTasks = nbOfIdentTasks;
	}
	public int getTotalNbOfCls() {
		this.totalNbOfCls=0;
		Iterator <Task> it = this.getTasks().iterator(); 
		while (it.hasNext())
		{
			Task item = (Task) it.next();
			this.totalNbOfCls += item.getCriticalLines().size();
			//System.out.println("Tache :"+item.getName()+" NB_CLs : "+item.getCriticalLines().size());
		}
		//System.out.println(" Res :"+this.totalNbOfCls);
		return totalNbOfCls;
	}
	public void setTotalNbOfCls(int totalNbOfCls) {
		
		this.totalNbOfCls = totalNbOfCls;
	}
	public int getNbOfRecCls() {
		return nbOfRecCls;
	}
	public void setNbOfRecCls(int nbOfRecCls) {
		this.nbOfRecCls = nbOfRecCls;
	}
	public int getNbOfRecTasks() {
		return nbOfRecTasks;
	}
	public void setNbOfRecTasks(int nbOfRecTasks) {
		this.nbOfRecTasks = nbOfRecTasks;
	}
	public boolean isSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	public ArrayList<Variable> getVariables() {
		return variables;
	}
	public void setVariables(ArrayList<Variable> variables) {
		this.variables = variables;
	}
	
	public ArrayList<Expression> getExpressions() {
		return expressions;
	}
	public void setExpressions(ArrayList<Expression> expressions) {
		this.expressions = expressions;
	}
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	public ArrayList<Constraint> getConstraints() {
		return constraints;
	}
	public void setConstraints(ArrayList<Constraint> constraints) {
		this.constraints = constraints;
	}
	
}
