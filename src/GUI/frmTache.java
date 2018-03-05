package GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout.Constraints;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.w3c.dom.Element;

import recognizer.Constraint;
import recognizer.Expression;
import recognizer.ForInstruction;
import recognizer.Instruction;
import recognizer.Task;
import recognizer.Variable;

import meiters.ModeleContraintes;
import meiters.TacheModele;
import meiters.dauther;
import meiters.exitListener;

import editor.AlgoEditor;
import editor.EmptyFrame;
import editor.GBC;

public class frmTache extends EmptyFrame{
	
	public frmTache(AlgoEditor pPrincipal)
	{
		this.mother = pPrincipal.mainFrame;
		this.copiePrincipal =  pPrincipal;
		setTitle("Tâche");
		setSize(550, 480);
		setResizable(false);
		this.setName("tacheFrm");
		addWindowListener(new dauther(pPrincipal.mainFrame,this));
		initialize();
		//setVisible(true);
	}
	public void initialize()
	{
		// Panneau
		JPanel mainPanel = new JPanel();
		GridBagLayout mainLayout = new GridBagLayout();
		mainPanel.setLayout(mainLayout);
		//this.setContentPane(mainPanel);
		this.Tasks_Model = new TacheModele();
		this.Tasks_table = new JTable(this.Tasks_Model);
		this.Tasks_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.Tasks_Pane = new JScrollPane(this.Tasks_table);
		
		
		
		// Composants graphiques
		// Libs
		this.libNom = new JLabel("Nom");
		this.libMere = new JLabel("Mere");
		this.libType = new JLabel("Type");
		this.libNote = new JLabel("Note");
		this.libDeb = new JLabel("Début");
		this.libFin = new JLabel("Fin");
		
		this.libLigneCritique =  new JLabel("Ligne critique");
		this.libNumCL =  new JLabel("Num ligne");
		// champs
		this.chpNom = new JTextField();
		this.chpMere = new JComboBox();
		this.libMere.setVisible(false);
		this.chpMere.setVisible(false);
		this.chpType = new JComboBox(new String[]{"Tâche","Sous Tâche"});
		this.chpDebut = new JComboBox();
		
		this.chpFin = new JComboBox();
		//chargerLignes(this.chpFin);
		this.chpNote = new JFormattedTextField(NumberFormat.getIntegerInstance());
		this.chpNumCL = new JComboBox();
		//this.chpNumCL.setEnabled(false);
		//chargerLignes(this.chpNumCL);
		//this.chpNumCL.setEnabled(false);
		this.chpLigneCritique = new JComboBox();
				// Boutton de validation
		
		 btnValider = new JButton("Valider");
		// Boutton de validation
		btnAnnuler = new JButton("Annuler");
		btnSortir = new JButton("Sortir");
		btnSortir.addActionListener(new exitListener(this));
		JButton btnAddCL = new JButton("Ajouter CL");
		btnDeleteCL = new JButton("Supprimer CL");
		btnDeleteCL.setEnabled(false);
		
		this.btnAnnuler.setEnabled(false);
		this.btnValider.setEnabled(false);
		
		this.btnAdd = new JButton("Ajouter");
		this.btnDelete = new JButton("Supprimer");
		
		
		btnAddCL.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String val = "";
				//frmTache.this.chpNumCL.setEnabled(true);
				if(frmTache.this.chpNumCL.getItemCount()>0)
					val = (String)frmTache.this.chpNumCL.getSelectedItem();
				if(! val.equals("")&& ! isContained(frmTache.this.chpLigneCritique, val))
				{
					frmTache.this.chpLigneCritique.addItem(val);
					frmTache.this.chpLigneCritique.setSelectedItem(val);
					if(frmTache.this.chpNumCL.getItemCount()>0)
						frmTache.this.chpNumCL.setSelectedIndex(0);
					frmTache.this.btnDeleteCL.setEnabled(true);
					//frmTache.this.chpNumCL.setEnabled(false);
				}
				
			}
		});
		btnDeleteCL.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String val = (String)frmTache.this.chpLigneCritique.getSelectedItem();
				if(! val.equals(""))
				{
					frmTache.this.chpLigneCritique.removeItem(val);
					if(frmTache.this.chpNumCL.getItemCount()>0)
						frmTache.this.chpNumCL.setSelectedIndex(0);
					if (frmTache.this.chpLigneCritique.getItemCount()>0)
						frmTache.this.btnDeleteCL.setEnabled(false);
				}
			}
		});
		
		this.chpType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(frmTache.this.chpType.getSelectedIndex() == 0)
				{
					frmTache.this.libMere.setVisible(false);
					frmTache.this.chpMere.setVisible(false);
				}
				else
				{
					frmTache.this.libMere.setVisible(true);
					frmTache.this.chpMere.setVisible(true);
					// this.chargerTaches(frmTache.this.chpMere);
				}
			}
		});
		// Lib Noms
		// Lib taille
		
		this.btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int[] selection = frmTache.this.Tasks_table.getSelectedRows();
				 
	            for(int i = selection.length - 1; i >= 0; i--){
	            	frmTache.this.LastTsk  = selection[i];
	            	frmTache.this.Tasks_Model.removeTask(selection[i]);
	            	frmTache.this.copiePrincipal.curAlg.getAlgoTasks().remove(i);
	            	frmTache.this.LastTsk--;
	            	if(frmTache.this.LastTsk>=0)
	            		frmTache.this.Tasks_table.setRowSelectionInterval(frmTache.this.LastTsk, frmTache.this.LastTsk);
	            	else
	            		if(frmTache.this.Tasks_table.getRowCount()>0)
	            		{
	            			frmTache.this.LastTsk++;	
	            			frmTache.this.Tasks_table.setRowSelectionInterval(frmTache.this.LastTsk, frmTache.this.LastTsk);
	            		}
	            }	
			}
		});
		
		this.btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmTache.this.chpNumCL.setEnabled(true);
				if(frmTache.this.chpType.getItemCount()>0)
					frmTache.this.chpType.setSelectedIndex(0);
				if(frmTache.this.chpDebut.getItemCount()>0)
					frmTache.this.chpDebut.setSelectedIndex(0);
				if(frmTache.this.chpFin.getItemCount()>0)
					frmTache.this.chpFin.setSelectedIndex(0);
				if(frmTache.this.chpNumCL.getItemCount()>0)
					frmTache.this.chpNumCL.setSelectedIndex(0);
				frmTache.this.chpLigneCritique.removeAllItems();
				frmTache.this.chpNote.setText("");
				
				frmTache.this.chpNom.setText("");
				frmTache.this.chpNom.requestFocus();
				frmTache.this.btnAdd.setEnabled(false);
				frmTache.this.btnDelete.setEnabled(false);
				frmTache.this.btnValider.setEnabled(true);
				frmTache.this.btnAnnuler.setEnabled(true);
				int[] selection = frmTache.this.Tasks_table.getSelectedRows();
				 
	            for(int i = selection.length - 1; i >= 0; i--){
	            	frmTache.this.LastTsk = selection[i];
	            }	
			}
		});
		this.btnAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Last task :"+frmTache.this.LastTsk);
				if(frmTache.this.LastTsk >= 0 )
				{
					frmTache.this.chargerTask(frmTache.this.copiePrincipal.curAlg.getAlgoTasks().get(frmTache.this.LastTsk ));
					
				}
				
				frmTache.this.btnAdd.setEnabled(true);
				frmTache.this.btnDelete.setEnabled(true);
				frmTache.this.btnValider.setEnabled(false);
				frmTache.this.btnAnnuler.setEnabled(false);
			}
		});
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frmTache.this.valider();
			}
		});
		ListSelectionModel selectionModel = this.Tasks_table.getSelectionModel();
		selectionModel.addListSelectionListener( new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int i = frmTache.this.Tasks_table.getSelectedRow();
				 
	            if(i >= 0){
	            	frmTache.this.chargerTask(frmTache.this.copiePrincipal.curAlg.getAlgoTasks().get(i));
	            }	
			}
		});
		mainPanel.add(this.libNom,new GBC(0,0,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(this.chpNom,new GBC(1,0,6,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		
		mainPanel.add(this.libType,new GBC(0,1,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(this.chpType,new GBC(1,1,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		mainPanel.add(this.libMere,new GBC(4,1,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(this.chpMere,new GBC(5,1,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		mainPanel.add(this.libDeb,new GBC(0,2,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(this.chpDebut,new GBC(1,2,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		mainPanel.add(this.libFin,new GBC(4,2,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(this.chpFin,new GBC(5,2,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		mainPanel.add(this.libLigneCritique,new GBC(0,4,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(this.chpLigneCritique,new GBC(1,4,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		mainPanel.add(this.libNumCL,new GBC(0,3,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(this.chpNumCL,new GBC(1,3,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		
		
		mainPanel.add(btnAddCL,new GBC(4,3,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(btnDeleteCL,new GBC(5,3,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		mainPanel.add(this.libNote,new GBC(4,4,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(this.chpNote,new GBC(5,4,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
			// Valider
		mainPanel.add(btnValider,new GBC(3,5,1,1).setInsets(5).setFill(GridBagConstraints.BOTH).setAnchor(GBC.SOUTHEAST));
		// Annuler
		mainPanel.add(btnAnnuler,new GBC(4,5,1,1).setInsets(5).setFill(GridBagConstraints.BOTH).setAnchor(GBC.SOUTHEAST));
		
		mainPanel.add(btnAdd,new GBC(0,5,1,1).setInsets(5).setFill(GridBagConstraints.BOTH).setAnchor(GBC.SOUTHEAST));
		mainPanel.add(btnDelete,new GBC(1,5,1,1).setInsets(5).setFill(GridBagConstraints.BOTH).setAnchor(GBC.SOUTHEAST));
		mainPanel.add(btnSortir,new GBC(5,5,1,1).setInsets(5).setFill(GridBagConstraints.BOTH).setAnchor(GBC.SOUTHEAST));
		
		this.add(this.Tasks_Pane, BorderLayout.CENTER);
		this.add(mainPanel, BorderLayout.SOUTH);
		if(this.copiePrincipal.curAlg!=null)
		{
			//System.out.println("nbre items chpDebut  chpNumCL:"+chpNumCL.getItemCount());
			//frmTache.this.chpNumCL.setEnabled(true);
			this.chargerTasks();
			this.chargerTaches(this.chpMere);
			
			this.chargerLignes();
			//this.repaint();
			//System.out.println("nbre items chpDebut  chpNumCL:"+chpNumCL.getItemCount());
		}
	}
	public boolean isContained(JComboBox pChp, String pVal)
	{
		int nbElts = pChp.getItemCount();
		int i = 0;
		while (i<nbElts)
			if (pChp.getItemAt(i).equals(pVal))
				return true;
			else 
				i++;
		return false;
	}
	public void chargerTaches(JComboBox pChp)
	{
		pChp.removeAllItems();
		if(this.copiePrincipal.curAlg != null)
		{
			Iterator<Task> it = this.copiePrincipal.curAlg.getAlgoTasks().iterator();
			while(it.hasNext())
			{
				Task item = it.next();
				pChp.addItem(item.getName());
			}
 		}
	}
	public void chargerTask(Task pTsk)
	{
		
		String name = pTsk.getName();
		String note = pTsk.getNote();
		String deb = pTsk.getDeb();
		String fin = pTsk.getFin();
		ArrayList<String> cls = pTsk.getCriticalLines();
		String type = pTsk.getType();
		String mere = "";
		this.chpNom.setText(name);
		this.chpNote.setText(note);
		if(this.isContained(this.chpDebut, deb))
			this.chpDebut.setSelectedItem(deb);
		if(this.isContained(this.chpFin, fin))
			this.chpFin.setSelectedItem(fin);
		if(type.equals("1"))
			this.chpType.setSelectedItem(0);
			else
				{
					this.chpType.setSelectedItem(1);
					if(this.chpMere.getItemCount()>0)
						mere = (String) this.chpMere.getSelectedItem();
				}
		this.chpLigneCritique.removeAllItems();
		if(cls.size()>0)
		{
			Iterator <String> it = cls.iterator();
			while(it.hasNext())
			{
				String item = it.next();
				this.chpLigneCritique.addItem(item);
			}
		}
		
	}
	public void chargerTasks()
	{
		int i = 0;
		int nbreLig = this.Tasks_table.getRowCount();
		while(i < nbreLig)
		{
			this.Tasks_Model.removeTask(i);
			i++;
		}
		if(this.copiePrincipal.curAlg.getAlgoTasks().size() > 0)
		{
			Iterator<Task> it = this.copiePrincipal.curAlg.getAlgoTasks().iterator();
			while(it.hasNext())
			{
				Task item = it.next();
				Task item_colne = new Task();
				
				item_colne.setName(item.getName());
				item_colne.setNote(item.getNote());
				item_colne.setDeb(item.getDeb());
				item_colne.setFin(item.getFin());
				item_colne.setMother(item.getMother());
				item_colne.setCriticalLines(item.getCriticalLines());
				int t = Integer.parseInt(item.getType());
				switch(t)
				{
					case 1 :
								item_colne.setType("Tache");
								break;
					case 2 :	
								item_colne.setType("Sous Tache");
								break;
					
				}
				this.Tasks_Model.addTask(item_colne);
			}
			if(this.copiePrincipal.curAlg.getAlgoTasks().size()>0)
			{
				this.Tasks_table.setRowSelectionInterval(0, 0);
				this.chargerTask(this.copiePrincipal.curAlg.getAlgoTasks().get(0));
			}
 		}
	}
	public void valider()
	{
		String nom =  this.chpNom.getText();
		String type = Integer.toString(this.chpType.getSelectedIndex()+1);
		String mere = "";
		if (this.chpType.getSelectedIndex() == 1 && this.chpMere.getItemCount()>0)
			mere = (String) this.chpMere.getSelectedItem(); 
		String note = this.chpNote.getText();
		String debut = "";
		if(this.chpDebut.getItemCount()>0)
			debut = (String) this.chpDebut.getSelectedItem();
		String fin = "";
		if(this.chpFin.getItemCount()>0)
			fin = (String) this.chpFin.getSelectedItem();
		boolean donnValid = !nom.equals("") && !note.equals("") && !debut.equals("") && !fin.equals("");
		
		//this.copiePrincipal.rootAlgo.appendChild(varElt);
		
		//String cond = this.chpCond.getText();
		if(donnValid )
		{
			/*Element taskElt = this.copiePrincipal.currentDoc.createElement("Task");
			taskElt.setAttribute("Name",nom);
			taskElt.setAttribute("Type", type);
			taskElt.setAttribute("Mere", mere);
			taskElt.setAttribute("Note", note);
			taskElt.setAttribute("Deb", this.chpDebut.getText());
			taskElt.setAttribute("Fin", this.chpFin.getText());*/
			Task curTask = new Task();
			curTask.setName(nom);
			curTask.setType(type);
			curTask.setMother(mere);
			curTask.setNote(note);
			curTask.setDeb(debut);
			curTask.setFin(fin);
			ArrayList<String> cls = new ArrayList<String>();
			int nbElts = this.chpLigneCritique.getItemCount();
			int i = 0;
			while (i<nbElts)
				{
					cls.add((String)this.chpLigneCritique.getItemAt(i));
					i++;
				}
			curTask.setCriticalLines(cls);	
				//**************************
			this.copiePrincipal.curAlg.getAlgoTasks().add(curTask);
			Task tk = new Task();
			
			tk.setName(curTask.getName());
			tk.setDeb(curTask.getDeb());
			tk.setFin(curTask.getFin());
			tk.setNote(curTask.getNote());
			tk.setMother(curTask.getMother());
			tk.setCriticalLines(curTask.getCriticalLines());
			int j = Integer.parseInt(curTask.getType());
			switch(j)
			{
				case 1 :
							tk.setType("Tache");
							break;
				case 2 :	
							tk.setType("Sous Tache");
							break;
				
			}
			this.Tasks_Model.addTask(tk);
			this.Tasks_table.setRowSelectionInterval(this.Tasks_Model.getRowCount()-1, this.Tasks_Model.getRowCount()-1);
			
			//this.copiePrincipal.refreshAlgoText();
			this.copiePrincipal.btnSaveSC.setEnabled(true);
			this.copiePrincipal.curAlg.setSaved(false);
			//this.dispose();
			
			this.btnValider.setEnabled(false);
			this.btnAnnuler.setEnabled(false);
			this.btnAdd.setEnabled(true);
			this.btnDelete.setEnabled(true);
		}
		else
		{
			JOptionPane.showMessageDialog(null,
				    "Un champ obligatoire n'est pas rempli, veuiller verifier vos données.",
				    " Attention",
				    JOptionPane.WARNING_MESSAGE);
		}
		
	}
	public void chargerLignes()
	{
		
		//int xx = this.copiePrincipal.curAlg.getAlgoLines().size()+this.copiePrincipal.curAlg.getAlgoVars().size()+3;
		//System.out.println("nbreLignes : "+xx);
		//System.out.println("nbre items   Avant:"+chpDebut.getItemCount());
		this.chpDebut.removeAllItems();
		this.chpFin.removeAllItems();
		this.chpNumCL.removeAllItems();
		//System.out.println("nbre items   Après:"+chpDebut.getItemCount());
		if(this.copiePrincipal.curAlg != null)
		{
			int i = this.copiePrincipal.curAlg.getAlgoVars().size()+3;
			int nbreLig = this.copiePrincipal.curAlg.getAlgoLines().size()+i;
			
			while(i<nbreLig)
			{
				this.chpDebut.addItem(Integer.toString(i));
				this.chpFin.addItem(Integer.toString(i));
				this.chpNumCL.addItem(Integer.toString(i));
				i++;
			}
		}
		
	}
	
	//public frmExp condExp;
	public JLabel libNom;
	public JLabel libMere;
	public JLabel libType;
	public JLabel libNote;
	public JLabel libDeb;
	public JLabel libFin;
	public JLabel libLigneCritique;
	public JLabel libNumCL;
	
	public JTextField chpNom;
	public JComboBox chpMere;
	public JComboBox chpType;
	
	public JComboBox chpDebut;
	public JComboBox chpFin;
	public JFormattedTextField chpNote;
	public JComboBox chpNumCL;
	public JComboBox chpLigneCritique;
	
	public JButton btnDeleteCL ;
	
	public JButton btnAdd ;
	public JButton btnDelete ;
	public JButton btnMod ;
	public JButton btnValider;
	public JButton btnAnnuler;
	public JButton btnSortir;
	
	
	public int LastTsk = -1;
	
	public JTable Tasks_table;
	public JScrollPane Tasks_Pane;
	public TacheModele Tasks_Model;
}



