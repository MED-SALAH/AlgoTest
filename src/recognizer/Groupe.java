package recognizer;

import java.util.ArrayList;
import java.util.Iterator;

public class Groupe {
	
	
	public String getNomGroupe() {
		return nomGroupe;
	}
	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}
	public ArrayList<Etudiant> getListe_Etudiants() {
		return liste_Etudiants;
	}
	public void setListe_Etudiants(ArrayList<Etudiant> listeEtudiants) {
		liste_Etudiants = listeEtudiants;
	}
	
	public ArrayList<String> getListe_Sol() {
		return liste_Sol;
	}
	public void setListe_Sol(ArrayList<String> listeSol) {
		liste_Sol = listeSol;
	}

	public ArrayList<SolutionEvaluation> getEvals() {
		return evals;
	}
	public void setEvals(ArrayList<SolutionEvaluation> evals) {
		this.evals = evals;
	}
	
	public int getNb_Sol_Rec() {
		this.nb_Sol_Rec = 0;
		ArrayList<SolutionEvaluation> listeEvals = this.getEvals();
		if(listeEvals != null)
		{
			Iterator<SolutionEvaluation> it = listeEvals.iterator();
			while(it.hasNext())
			{
				SolutionEvaluation item = it.next();
				if(item.isEtat())
					//{
					this.nb_Sol_Rec ++;
					//System.out.println(item.getName()+"    true     "+ item.getModel());
					//}
			}
		}
		return nb_Sol_Rec;
	}
	public void setNb_Sol_Rec(int nbSolRec) {
		nb_Sol_Rec = nbSolRec;
	}
	public int getNb_Sol_NonRec() {
		this.nb_Sol_NonRec = 0;
		ArrayList<SolutionEvaluation> listeEvals = this.getEvals();
		if(listeEvals != null)
		{
			Iterator<SolutionEvaluation> it = listeEvals.iterator();
			while(it.hasNext())
			{
				SolutionEvaluation item = it.next();
				if(!item.isEtat())
					this.nb_Sol_NonRec ++;
			}
		}
		return nb_Sol_NonRec;
	}
	public void setNb_Sol_NonRec(int nbSolNonRec) {
		nb_Sol_NonRec = nbSolNonRec;
	}

	private String nomGroupe;
	private ArrayList<Etudiant> liste_Etudiants;
	private ArrayList<String> liste_Sol;
	private ArrayList<SolutionEvaluation> evals;
	private int nb_Sol_Rec;
	private int nb_Sol_NonRec;
	
}
