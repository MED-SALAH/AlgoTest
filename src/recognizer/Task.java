package recognizer;
import java.util.ArrayList;


public class Task {
	private String name;
	private String type;
	private String mother;
	private String note;
	private String deb;
	private String fin;
	private ArrayList<String> criticalLines;
	private int reconCLs;
	private int identCLs;
	private boolean reconue;
	private boolean canBeMissed;
	
	public int getIdentCLs() {
		return identCLs;
	}
	public void setIdentCLs(int identCLs) {
		this.identCLs = identCLs;
	}
	public int getReconCLs() {
		return reconCLs;
	}
	public void setReconCLs(int reconCLs) {
		this.reconCLs = reconCLs;
	}
	public ArrayList<String> getCriticalLines() {
		return criticalLines;
	}
	public void setCriticalLines(ArrayList<String> criticalLines) {
		this.criticalLines = criticalLines;
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
	public String getMother() {
		return mother;
	}
	public void setMother(String mother) {
		this.mother = mother;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDeb() {
		return deb;
	}
	public void setDeb(String deb) {
		this.deb = deb;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	public boolean isReconue() {
		return reconue;
	}
	public void setReconue(boolean reconue) {
		this.reconue = reconue;
	}
	public boolean isCanBeMissed() {
		return canBeMissed;
	}
	public void setCanBeMissed(boolean canBeMissed) {
		this.canBeMissed = canBeMissed;
	}
	
}
