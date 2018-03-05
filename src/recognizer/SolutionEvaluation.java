package recognizer;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SolutionEvaluation {
	// represente une evaluation d'une solution
	private String sourcePath;
	private String xmlPath;
	private String name;
	
	private ArrayList<Variable> variables;
	private ArrayList<Task> taches; // les taches presentes
	private String model; // chemin du code source du modele correspondant
	private float note; // la  note
	private boolean etat; // reconnu ou Non
	private int nbreInst; // le nombre des instructions de la solution
	private Element rootSol; // la racine du document xml representnt lz code source de la solution
	private ArrayList<Instruction> listeInstruction; // liste des instruction avec leur etat
	private int curEditInstrcution; 
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Instruction> getListeInstruction() {
		return listeInstruction;
	}
	public void setListeInstruction(ArrayList<Instruction> listeInstruction) {
		this.listeInstruction = listeInstruction;
	}
	public int getCurEditInstrcution() {
		return curEditInstrcution;
	}
	public void setCurEditInstrcution(int curEditInstrcution) {
		this.curEditInstrcution = curEditInstrcution;
	}
	public Node getRootSol() {
		return rootSol;
	}
	public void setRootSol(Element rootSol) {
		this.rootSol = rootSol;
	}
	public int getNbreInst() {
		return nbreInst;
	}
	public void setNbreInst(int nbreInst) {
		this.nbreInst = nbreInst;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	public String getXmlPath() {
		return xmlPath;
	}
	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}
	public ArrayList<Variable> getVariables() {
		return variables;
	}
	public void setVariables(ArrayList<Variable> variables) {
		this.variables = variables;
	}
	public ArrayList<Task> getTaches() {
		return taches;
	}
	public void setTaches(ArrayList<Task> taches) {
		this.taches = taches;
	}
	public float getNote() {
		return note;
	}
	public void setNote(float note) {
		this.note = note;
	}
	public boolean isEtat() {
		return etat;
	}
	public void setEtat(boolean etat) {
		this.etat = etat;
	}
}
