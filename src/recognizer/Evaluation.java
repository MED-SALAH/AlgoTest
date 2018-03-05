package recognizer;

import java.util.ArrayList;

public class Evaluation {
	public Evaluation(String pNum, String pType)
	{
		this.numEval = pNum;
		this.typeEval = pType;
		this.dateEval = "";
		this.etatEval = "";
		this.nb_Etud_Eval = 0;
		this.salleEval = "";
		this.dureeEval = "";
	}
	public String getNumEval() {
		return numEval;
	}
	public void setNumEval(String numEval) {
		this.numEval = numEval;
	}
	public String getTypeEval() {
		return typeEval;
	}
	public void setTypeEval(String typeEval) {
		this.typeEval = typeEval;
	}
	public String getDateEval() {
		return dateEval;
	}
	public void setDateEval(String dateEval) {
		this.dateEval = dateEval;
	}
	public String getEtatEval() {
		return etatEval;
	}
	public void setEtatEval(String etatEval) {
		this.etatEval = etatEval;
	}
	public int getNb_Etud_Eval() {
		return nb_Etud_Eval;
	}
	public void setNb_Etud_Eval(int nbEtudEval) {
		nb_Etud_Eval = nbEtudEval;
	}
	public String getSalleEval() {
		return salleEval;
	}
	public void setSalleEval(String salleEval) {
		this.salleEval = salleEval;
	}
	public String getDureeEval() {
		return dureeEval;
	}
	public void setDureeEval(String dureeEval) {
		this.dureeEval = dureeEval;
	}
	public ArrayList<Exercice> getListe_Exos() {
		return liste_Exos;
	}
	public void setListe_Exos(ArrayList<Exercice> listeExos) {
		liste_Exos = listeExos;
	}
	
	
	public ArrayList<Groupe> getListe_Groupes() {
		return liste_Groupes;
	}
	public void setListe_Groupes(ArrayList<Groupe> listeGroupes) {
		liste_Groupes = listeGroupes;
	}


	private String numEval;
	private String typeEval;
	private String dateEval;
	private String etatEval;
	private int nb_Etud_Eval;
	private String salleEval;
	private String dureeEval;
	private ArrayList<Exercice> liste_Exos;
	private ArrayList<Groupe> liste_Groupes;
}
