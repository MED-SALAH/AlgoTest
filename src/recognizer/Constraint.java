package recognizer;

import java.util.ArrayList;

public class Constraint {
	private String type;
	private String numLig;
	private String taskName;
	private String penalite;
	private String source;
	private String target;
	private ArrayList<Expression> exps;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumLig() {
		return numLig;
	}
	public void setNumLig(String numLig) {
		this.numLig = numLig;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getPenalite() {
		return penalite;
	}
	public void setPenalite(String penalite) {
		this.penalite = penalite;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public ArrayList<Expression> getExps() {
		return exps;
	}
	public void setExps(ArrayList<Expression> exps) {
		this.exps = exps;
	}
	

}

