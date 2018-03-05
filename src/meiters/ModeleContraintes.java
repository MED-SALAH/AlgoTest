package meiters;

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

public class ModeleContraintes extends AbstractTableModel {
    private final List<Constraint> ctrs = new ArrayList<Constraint>();
 
    private final String[] entetes = {"Type", "Num Ligne", "Tache", "Penalité", "Source","Destination"};
 
    public ModeleContraintes() {
        super();
       
    }
 
    public int getRowCount() {
        return ctrs.size();
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
                return ctrs.get(rowIndex).getType();
            case 1:
                return ctrs.get(rowIndex).getNumLig();
            case 2:
                return ctrs.get(rowIndex).getTaskName();
            case 3:
                return ctrs.get(rowIndex).getPenalite();
            case 4:
                return ctrs.get(rowIndex).getSource();
            case 5:
                return ctrs.get(rowIndex).getTarget();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addCtr(Constraint pCtr) {
            
    	ctrs.add(pCtr);
        fireTableRowsInserted(ctrs.size() -1, ctrs.size() -1);
    }
 
    public void removeCtr(int rowIndex) {
    	ctrs.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}



