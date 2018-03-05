package meiters;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import recognizer.Constraint;
import recognizer.ModelAnalyser;

public class ModeleCondVersO extends AbstractTableModel
	{
		
		private final List<Constraint> ctrs = new ArrayList<Constraint>();
	 
	    public List<Constraint> getCtrs() {
			return ctrs;
		}


		private final String[] entetes = {"Conditions"};
	 
	    public ModeleCondVersO() {
	        super();
	       try {
			this.analayser = new ModelAnalyser();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
                return this.analayser.getTextExp(ctrs.get(rowIndex).getExps());
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
    
    
    public ModelAnalyser analayser;
   
}




