package editor;

import java.util.ArrayList;

import recognizer.Constraint;
import recognizer.Instruction;
import recognizer.Task;
import recognizer.Variable;

public class Algorithm {
	public Algorithm()
	{
		this.algoVars = new ArrayList<Variable>();
	}
	public ArrayList<Variable> getAlgoVars() {
		return algoVars;
	}
	public void setAlgoVars(ArrayList<Variable> algoVars) {
		this.algoVars = algoVars;
	}
	public String getAlgoName() {
		return algoName;
	}
	public void setAlgoName(String algoName) {
		//System.out.println("J'ai recu la valeur : "+algoName);
		this.algoName = algoName;
	}
	public String getAlgoEvaluation() {
		return algoEvaluation;
	}
	public void setAlgoEvaluation(String algoEvaluation) {
		this.algoEvaluation = algoEvaluation;
	}
	public String getAlgoExercise() {
		return algoExercise;
	}
	public void setAlgoExercise(String algoExercise) {
		this.algoExercise = algoExercise;
	}
	public String getAlgoPath() {
		return algoPath;
	}
	public void setAlgoPath(String algoPath) {
		this.algoPath = algoPath;
	}
	
public boolean isSaved() {
		return saved;
	}
	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	
	
public ArrayList<Instruction> getAlgoLines() {
		return algoLines;
	}
	public void setAlgoLines(ArrayList<Instruction> algoLines) {
		this.algoLines = algoLines;
	}



public ArrayList<Task> getAlgoTasks() {
		return algoTasks;
	}
	public void setAlgoTasks(ArrayList<Task> algoTasks) {
		this.algoTasks = algoTasks;
	}
	public ArrayList<Constraint> getAlgoConsts() {
		return algoConsts;
	}
	public void setAlgoConsts(ArrayList<Constraint> algoConsts) {
		this.algoConsts = algoConsts;
	}



private ArrayList<Variable> algoVars;
private ArrayList<Instruction> algoLines;
private ArrayList<Task> algoTasks;
private ArrayList<Constraint> algoConsts;
private String algoName;
private String algoEvaluation;
private String algoExercise;
private String algoPath;
private boolean saved;
}
