package recognizer;


import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Instruction {
	public Instruction()
	{
		
	}
	private int num;
	private String texte;
	private Node noeud;
	private ArrayList<Expression> expressions;
	private ArrayList<Expression> clonesExp;
	private boolean reconue;
	public boolean isReconue() {
		return reconue;
	}
	public void setReconue(boolean reconue) {
		this.reconue = reconue;
	}
	public ArrayList<Expression> getClonesExp() {
		return clonesExp;
	}
	public void setClonesExp(ArrayList<Expression> clonesExp) {
		this.clonesExp = clonesExp;
	}
	public ArrayList<Expression> getExpressions() {
		return expressions;
	}
	public void setExpressions(ArrayList<Expression> expressions) {
		this.expressions = expressions;
	}
	public Node getNoeud() {
		return noeud;
	}
	public void setNoeud(Node noeud) {
		this.noeud = noeud;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}

}
