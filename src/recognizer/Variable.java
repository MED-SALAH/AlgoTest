package recognizer;
import java.util.ArrayList;


public class Variable {
	private String name;
	private String type;
	private String taille;
	public String getTaille() {
		return taille;
	}
	public void setTaille(String taille) {
		this.taille = taille;
	}
	private String substitute; // la variable correspondante
	// Ajouter une classe trace : comment elle se change toutes 
	// les lignes qui affecte sa valeur ou l'utilise
	private ArrayList<String> varClone;
	private String clone;
	public ArrayList<String> getVarClone() {
		return varClone;
	}
	public void setVarClone(ArrayList<String> varClone) {
		this.varClone = varClone;
	}
	public String getClone() {
		return clone;
	}
	public void setClone(String clone) {
		this.clone = clone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubstitute() {
		return substitute;
	}
	public void setSubstitute(String substitute) {
		this.substitute = substitute;
	}

}
