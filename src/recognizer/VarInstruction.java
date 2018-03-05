package recognizer;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

//Instrcution Lire
public class VarInstruction extends Instruction{
	public VarInstruction(String pNumInst, String pName,String pType,String pTaille)
	{
		this.numInst = pNumInst;
		this.name = pName;
		this.type = pType;
		this.taille = pTaille;
	}
	
	public String getNumInst() {
		return numInst;
	}
	public void setNumInst(String numInst) {
		this.numInst = numInst;
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
	public String getTaille() {
		return taille;
	}
	public void setTaille(String taille) {
		this.taille = taille;
	}



	private String numInst;
	private String name;
	private String type; // la variable
	private String taille; // le rang (cas d'un tableau)
	
}
