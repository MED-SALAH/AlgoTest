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
import recognizer.Task;

public class TacheModele extends AbstractTableModel {
    private final List<Task> tasks = new ArrayList<Task>();
 
    private final String[] entetes = {"Nom", "Type", "Début", "Fin", "Note"};
 
    public TacheModele() {
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
        return tasks.size();
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
                return tasks.get(rowIndex).getName();
            case 1:
                return tasks.get(rowIndex).getType();
            case 2:
                return tasks.get(rowIndex).getDeb();
            case 3:
                return tasks.get(rowIndex).getFin();
            case 4:
                return tasks.get(rowIndex).getNote();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addTask(Task pTsk) {
            
    	tasks.add(pTsk);
        fireTableRowsInserted(tasks.size() -1, tasks.size() -1);
    }
 
    public void removeTask(int rowIndex) {
    	tasks.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}



