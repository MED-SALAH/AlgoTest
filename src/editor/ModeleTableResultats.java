package editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import recognizer.Constraint;
import recognizer.SolutionEvaluation;
import recognizer.Task;

public class ModeleTableResultats extends AbstractTableModel {
    private final List<SolutionEvaluation> resEvals = new ArrayList<SolutionEvaluation>();
 
    private final String[] entetes = {"Nom", "Resultat", "Note", "Modele"};
 
    public ModeleTableResultats() {
        super();
        /*Task curTask = new Task();
        curTask.setType("xxx");
        curTask.setName("xxx");
        curTask.setNote("xx");
        curTask.setDeb("xx");
        curTask.setFin("xx");
		
		tasks.add(curTask);*/
		
    }
 
    public int getRowCount() {
        return resEvals.size();
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
    @Override
    /*public Class getColumnClass(int columnIndex){
    	switch(columnIndex){
    		case 2:
    			return Color.class;
    		case 3:
    			return Boolean.class;
    		default:
    			return Object.class;
    	}
    }*/

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return resEvals.get(rowIndex).getName();
            case 1:
                	if(resEvals.get(rowIndex).isEtat())
                		return "Reconnue";
                	else
                		return "--------";
            case 2:
            	if(resEvals.get(rowIndex).isEtat())
            		return resEvals.get(rowIndex).getNote();
            	else
            		return "";
            case 3:
                return resEvals.get(rowIndex).getModel();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addEval(SolutionEvaluation pResEval) {
            
    	resEvals.add(pResEval);
        fireTableRowsInserted(resEvals.size() -1, resEvals.size() -1);
    }
 
    public void removeEval(int rowIndex) {
    	resEvals.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}




