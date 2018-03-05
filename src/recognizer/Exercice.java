package recognizer;

import java.util.ArrayList;

public class Exercice {

	public String getNomExo() {
		return nomExo;
	}
	public void setNomExo(String nomExo) {
		this.nomExo = nomExo;
	}
	public String getThemeExo() {
		return themeExo;
	}
	public void setThemeExo(String themeExo) {
		this.themeExo = themeExo;
	}
	public String getNiveauExo() {
		return niveauExo;
	}
	public void setNiveauExo(String niveauExo) {
		this.niveauExo = niveauExo;
	}
	public String getCheminExo() {
		return cheminExo;
	}
	public void setCheminExo(String cheminExo) {
		this.cheminExo = cheminExo;
	}
	
	public ArrayList<String> getListe_Modeles() {
		return liste_Modeles;
	}
	public void setListe_Modeles(ArrayList<String> listeModeles) {
		liste_Modeles = listeModeles;
	}

	private String nomExo;
	private String themeExo;
	private String niveauExo;
	private String cheminExo;
	private ArrayList<String> liste_Modeles;
}
