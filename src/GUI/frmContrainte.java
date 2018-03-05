package GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
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
import meiters.dauther;
import meiters.exitListener;

import editor.AlgoEditor;
import editor.EmptyFrame;
import editor.GBC;

public class frmContrainte extends EmptyFrame{
	
	public frmContrainte(AlgoEditor pPrincipal)
	{
		this.mother = pPrincipal.mainFrame;
		this.copiePrincipal =  pPrincipal;
		setTitle("Contrainte");
		setSize(800, 480);
		setResizable(false);
		this.setName("contrainteFrm");
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
		
		
		
		this.Ctrs_Model = new  ModeleContraintes();
		this.Ctrs_table = new JTable(this.Ctrs_Model);
		this.Ctrs_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.Ctrs_Pane = new JScrollPane(this.Ctrs_table);
				
		// Composants graphiques
		// Libs
		this.libType = new JLabel("Type");
		this.libNumLig= new JLabel("Num Ligne");
		this.libTache = new JLabel("Tâche");
		this.libPenalite = new JLabel("Pénalité");
		this.libSrc = new JLabel("Source");
		this.libDes = new JLabel("Destination");
		
		//this.libLigneCritique =  new JLabel("Ligne critique");
		//this.libNumCL =  new JLabel("Num ligne");
		// champs
		this.chpType = new JComboBox(new String[]{"Présence","Absence","Desodre Taches","Desodre Lignes","Absence Tâche","Présence Tâche"});
		
		this.chpNumLig = new JComboBox();
		//this.chargerLignes(this.chpNumLig);
		
		this.chpTache = new JComboBox();
		frmContrainte.this.chpTache.setEnabled(false);
		//this.chargerTaches(this.chpTache)
		this.chpPenalite = new JFormattedTextField(NumberFormat.getNumberInstance());
		this.chpSrc = new JComboBox();
		this.chpDes = new JComboBox();
		frmContrainte.this.chpSrc.setEnabled(false);
		frmContrainte.this.chpDes.setEnabled(false);
		
		btnValider = new JButton("Valider");
		// Boutton de validation
		btnAnnuler = new JButton("Annuler");
		btnSortir = new JButton("Sortir");
		btnSortir.addActionListener(new exitListener(this));
		this.btnAdd = new JButton("Ajouter");
		this.btnDelete = new JButton("Supprimer");
		//this.btnAdd.setEnabled(false);
		//this.btnDelete.setEnabled(false);
		this.btnAnnuler.setEnabled(false);
		this.btnValider.setEnabled(false);
		this.btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int[] selection = frmContrainte.this.Ctrs_table.getSelectedRows();
				 
	            for(int i = selection.length - 1; i >= 0; i--){
	            	frmContrainte.this.LastCtr  = selection[i];
	            	frmContrainte.this.Ctrs_Model.removeCtr(selection[i]);
	            	frmContrainte.this.copiePrincipal.curAlg.getAlgoConsts().remove(frmContrainte.this.LastCtr);
	            	frmContrainte.this.LastCtr--;
	            	if(frmContrainte.this.LastCtr>=0)
	            		frmContrainte.this.Ctrs_table.setRowSelectionInterval(frmContrainte.this.LastCtr, frmContrainte.this.LastCtr);
	            	else
	            		if(frmContrainte.this.Ctrs_table.getRowCount()>0)
	            		{
	            			frmContrainte.this.LastCtr++;	
	            			frmContrainte.this.Ctrs_table.setRowSelectionInterval(frmContrainte.this.LastCtr, frmContrainte.this.LastCtr);
	            		}
	            	//frmContrainte.this.copiePrincipal.curAlg.getAlgoConsts().remove(i);
	            	frmContrainte.this.copiePrincipal.btnSaveSC.setEnabled(true);
	            	frmContrainte.this.copiePrincipal.curAlg.setSaved(false);
	            	
	            }	
			}
		});
		
		this.btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(frmContrainte.this.chpType.getItemCount()>0)
					frmContrainte.this.chpType.setSelectedIndex(0);
				if(frmContrainte.this.chpTache.getItemCount()>0)
					frmContrainte.this.chpTache.setSelectedIndex(0);
				if(frmContrainte.this.chpSrc.getItemCount()>0)
					frmContrainte.this.chpSrc.setSelectedIndex(0);
				if(frmContrainte.this.chpDes.getItemCount()>0)
					frmContrainte.this.chpDes.setSelectedIndex(0);
				if(frmContrainte.this.chpNumLig.getItemCount()>0)
					frmContrainte.this.chpNumLig.setSelectedIndex(0);
				frmContrainte.this.chpPenalite.setText("");
				frmContrainte.this.btnAdd.setEnabled(false);
				frmContrainte.this.btnDelete.setEnabled(false);
				frmContrainte.this.btnValider.setEnabled(true);
				frmContrainte.this.btnAnnuler.setEnabled(true);
				int[] selection = frmContrainte.this.Ctrs_table.getSelectedRows();
				 
	            for(int i = selection.length - 1; i >= 0; i--){
	            	frmContrainte.this.LastCtr = i;
	            }	
			}
		});
		/*btnDeleteCL = new JButton("Supprimer CL");
		btnDeleteCL.setEnabled(false);
		btnAddCL.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String val = frmTache.this.chpNumCL.getText();
				if(! val.equals("")&& ! isContained(frmTache.this.chpLigneCritique, val))
				{
					frmTache.this.chpLigneCritique.addItem(val);
					frmTache.this.chpLigneCritique.setSelectedItem(val);
					frmTache.this.chpNumCL.setText("");
					frmTache.this.btnDeleteCL.setEnabled(true);
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
					frmTache.this.chpNumCL.setText("");
					if (frmTache.this.chpLigneCritique.getItemCount()>0)
						frmTache.this.btnDeleteCL.setEnabled(false);
				}
			}
		});*/
		
		this.chpType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(frmContrainte.this.chpType.getSelectedIndex() == 2)
				{
					frmContrainte.this.chargerLignes(frmContrainte.this.chpNumLig);
					//frmContrainte.this.chpNumLig.setEnabled(false);
					frmContrainte.this.chpTache.setEnabled(false);
					frmContrainte.this.chpSrc.setEnabled(true);
					frmContrainte.this.chpDes.setEnabled(true);
					if(frmContrainte.this.chpNumLig.getItemCount()>0)
						frmContrainte.this.chpNumLig.setSelectedIndex(0);
					frmContrainte.this.chpNumLig.setEnabled(false);
					frmContrainte.this.chargerTaches(frmContrainte.this.chpSrc);
					frmContrainte.this.chargerTaches(frmContrainte.this.chpDes);
				}
				else
					if(frmContrainte.this.chpType.getSelectedIndex() == 3)
					{
						frmContrainte.this.chargerLignes(frmContrainte.this.chpNumLig);
						frmContrainte.this.chpNumLig.setEnabled(true);
						frmContrainte.this.chpTache.setEnabled(false);
						frmContrainte.this.chpSrc.setEnabled(true);
						frmContrainte.this.chpDes.setEnabled(true);
						if(frmContrainte.this.chpNumLig.getItemCount()>0)
							frmContrainte.this.chpNumLig.setSelectedIndex(0);
						frmContrainte.this.chpNumLig.setEnabled(false);
						
						frmContrainte.this.chargerLignes(frmContrainte.this.chpSrc);
						frmContrainte.this.chargerLignes(frmContrainte.this.chpDes);
					}
					else if(frmContrainte.this.chpType.getSelectedIndex() == 4||frmContrainte.this.chpType.getSelectedIndex() == 5)
						 {
							frmContrainte.this.chpNumLig.setEnabled(false);
							frmContrainte.this.chpTache.setEnabled(true);
							frmContrainte.this.chargerTaches(frmContrainte.this.chpTache);
							frmContrainte.this.chpNumLig.removeAllItems();
							frmContrainte.this.chpSrc.removeAllItems();
							frmContrainte.this.chpDes.removeAllItems();
							frmContrainte.this.chpSrc.setEnabled(false);
							frmContrainte.this.chpDes.setEnabled(false);
						 }
						else
						{
							frmContrainte.this.chpNumLig.setEnabled(true);
							frmContrainte.this.chpSrc.removeAllItems();
							frmContrainte.this.chpDes.removeAllItems();
							frmContrainte.this.chpSrc.setEnabled(false);
							frmContrainte.this.chpDes.setEnabled(false);
							
						}
			}
		});
		
		// Lib Noms
		// Lib taille
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frmContrainte.this.valider();
			}
		});
		this.btnAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(frmContrainte.this.LastCtr >= 0 )
				{
					frmContrainte.this.chargerCtr(frmContrainte.this.copiePrincipal.curAlg.getAlgoConsts().get(frmContrainte.this.LastCtr ));
				}
				frmContrainte.this.btnAdd.setEnabled(true);
				frmContrainte.this.btnDelete.setEnabled(true);
				frmContrainte.this.btnValider.setEnabled(false);
				frmContrainte.this.btnAnnuler.setEnabled(false);
			}
		});
		ListSelectionModel selectionModel = this.Ctrs_table.getSelectionModel();
		selectionModel.addListSelectionListener( new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int i = frmContrainte.this.Ctrs_table.getSelectedRow();
				 
	            if(i >= 0){
	            	frmContrainte.this.chargerCtr(frmContrainte.this.copiePrincipal.curAlg.getAlgoConsts().get(i));
	            }	
			}
		});
		
		mainPanel.add(this.libType,new GBC(0,0,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(this.chpType,new GBC(1,0,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		
		mainPanel.add(this.libNumLig,new GBC(0,1,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(this.chpNumLig,new GBC(1,1,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		mainPanel.add(this.libTache,new GBC(4,1,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(this.chpTache,new GBC(5,1,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		mainPanel.add(this.libPenalite,new GBC(0,2,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(this.chpPenalite,new GBC(1,2,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		mainPanel.add(this.libSrc,new GBC(0,3,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(this.chpSrc,new GBC(1,3,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		mainPanel.add(this.libDes,new GBC(4,3,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(this.chpDes,new GBC(5,3,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
				
			
		mainPanel.add(btnAdd,new GBC(0,4,1,1).setInsets(5).setFill(GridBagConstraints.BOTH).setAnchor(GBC.SOUTHEAST));

		mainPanel.add(btnDelete,new GBC(1,4,1,1).setInsets(5).setFill(GridBagConstraints.BOTH).setAnchor(GBC.SOUTHEAST));
		
		// Valider
		mainPanel.add(btnValider,new GBC(4,4,1,1).setInsets(5).setFill(GridBagConstraints.BOTH).setAnchor(GBC.SOUTHEAST));
		// Annuler
		mainPanel.add(btnAnnuler,new GBC(5,4,1,1).setInsets(5).setFill(GridBagConstraints.BOTH).setAnchor(GBC.SOUTHEAST));
		mainPanel.add(btnSortir,new GBC(7,4,1,1).setInsets(5).setFill(GridBagConstraints.BOTH).setAnchor(GBC.SOUTHEAST));
		this.add(this.Ctrs_Pane, BorderLayout.CENTER);
		this.add(mainPanel, BorderLayout.SOUTH);
		if(this.copiePrincipal.curAlg != null )
		{
			this.chargerCtrs();
			this.chargerLignes(this.chpNumLig);
			this.chargerTaches(this.chpTache);
		}
		
		}
	public void chargerLignes(JComboBox pChp)
	{
		pChp.removeAllItems();
		if(this.copiePrincipal.curAlg != null)
		{
			int i = this.copiePrincipal.curAlg.getAlgoVars().size()+3;
			int nbreLig = this.copiePrincipal.curAlg.getAlgoLines().size();
			while(i<=nbreLig)
				{pChp.addItem(Integer.toString(i));i++;}
		}
		
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
	public void chargerCtr(Constraint pCtr)
	{
		
		String type = pCtr.getType();
		String numLig = pCtr.getNumLig();
		String tache = pCtr.getTaskName();
		String source = pCtr.getSource();
		String dest = pCtr.getTarget();
		String penalite = pCtr.getPenalite();
		int i = Integer.parseInt(pCtr.getType());
		switch(i)
		{
			case 2 :
						this.chpType.setSelectedIndex(0);
						this.chpTache.setSelectedItem(tache);
						this.chpNumLig.setSelectedItem(numLig);
						break;
			case 3 :	
						
						this.chpType.setSelectedIndex(1);
						this.chpTache.setSelectedItem(tache);
						this.chpNumLig.setSelectedItem(numLig);
						break;
			case 4 :	
						
						this.chpType.setSelectedIndex(2);
						this.chpSrc.setSelectedItem(source);
						this.chpDes.setSelectedItem(dest);
						break;
			case 5 :	
						
						this.chpType.setSelectedIndex(3);
						this.chpSrc.setSelectedItem(pCtr.getSource());
						this.chpDes.setSelectedItem(pCtr.getTarget());;
						
						break;
			case 6 :	
						
						this.chpType.setSelectedIndex(4);
						this.chpSrc.setSelectedItem(source);
						this.chpDes.setSelectedItem(dest);
						break;
			case 7 :	
						
						this.chpType.setSelectedIndex(5);
						this.chpSrc.setSelectedItem(source);
						this.chpDes.setSelectedItem(dest);
						break;
		}
		
		
		this.chpPenalite.setText(pCtr.getPenalite());
		
	}
	public void chargerCtrs()
	{
		int i = 0;
		int nbreLig = this.Ctrs_table.getRowCount();
		while(i < nbreLig)
		{
			this.Ctrs_Model.removeCtr(i);
			i++;
		}
		if(this.copiePrincipal.curAlg.getAlgoConsts().size() > 0)
		{
			Iterator<Constraint> it = this.copiePrincipal.curAlg.getAlgoConsts().iterator();
			while(it.hasNext())
			{
				Constraint item = it.next();
				Constraint item_colne = new Constraint();
				
				item_colne.setTaskName(item.getTaskName());
				item_colne.setNumLig(item.getNumLig());
				item_colne.setPenalite(item.getPenalite());
				item_colne.setSource(item.getSource());
				item_colne.setTarget(item.getTarget());
				int t = Integer.parseInt(item.getType());
				switch(t)
				{
					case 2 :
								item_colne.setType("Présence");
								break;
					case 3 :	
								item_colne.setType("Absence Ligne");
								break;
					case 4 :	
								item_colne.setType("Desordre Tâches");
								break;
					case 5 :	
								item_colne.setType("Desordre Lignes");
								break;
					case 6 :	
								item_colne.setType("Absence Tâches");
								break;
					case 7 :	
								item_colne.setType("Presence Tâche");
								break;
				}
				if(!item.getType().equals("8"))
					this.Ctrs_Model.addCtr(item_colne);
			}
			this.Ctrs_table.setRowSelectionInterval(0, 0);
			this.chargerCtr(this.copiePrincipal.curAlg.getAlgoConsts().get(0));
			/*if(this.copiePrincipal.curAlg.getAlgoConsts().size()>0)
			{
				this.Ctrs_table.setRowSelectionInterval(0, 0);
				this.chargerCtr(this.copiePrincipal.curAlg.getAlgoConsts().get(0));
			}*/
 		}
	}
	
	public void valider()
	{
		
		String type =  "";
		String tache = "";
		if(this.chpTache.getItemCount()>0)
			tache = (String) this.chpTache.getSelectedItem();
		
		String numLig = ""; 
		if(this.chpNumLig.getItemCount()>0)
			numLig = (String) this.chpNumLig.getSelectedItem();
		String penalite = this.chpPenalite.getText();
		String src = "";
		String des = "";
		boolean donValid = false;
		if (this.chpType.getSelectedIndex() == 2 || this.chpType.getSelectedIndex()==3)
		{
			src = (String) this.chpSrc.getSelectedItem();
			des = (String) this.chpDes.getSelectedItem();
			if(!src.equals("")&&!des.equals(""))
			{	
				donValid = true;
				if(penalite.equals("")||penalite.equals("0"))
					type = Integer.toString(this.chpType.getSelectedIndex()+ 2);
				else 
					type = Integer.toString(this.chpType.getSelectedIndex()+ 4);
			}
			
		}
		else 
			if(this.chpType.getSelectedIndex() == 4 || this.chpType.getSelectedIndex()==5)
			{
				donValid = true;
				src="";
				des="";
				numLig = "";
				type = Integer.toString(this.chpType.getSelectedIndex()+ 2);
			}
			else
				if(!numLig.equals(""))
				{	
					donValid = true;
					type = Integer.toString(this.chpType.getSelectedIndex()+ 2);
				}
		
		//this.copiePrincipal.rootAlgo.appendChild(varElt);
		
		//String cond = this.chpCond.getText();
		if(donValid )
		{
			/*Element taskElt = this.copiePrincipal.currentDoc.createElement("Task");
			taskElt.setAttribute("Name",nom);
			taskElt.setAttribute("Type", type);
			taskElt.setAttribute("Mere", mere);
			taskElt.setAttribute("Note", note);
			taskElt.setAttribute("Deb", this.chpDebut.getText());
			taskElt.setAttribute("Fin", this.chpFin.getText());*/
			Constraint curConstraint = new Constraint();
			curConstraint.setType(type);
			curConstraint.setTaskName(tache);
			curConstraint.setNumLig(numLig);
			curConstraint.setPenalite(penalite);
			curConstraint.setSource(src);
			curConstraint.setTarget(des);
				//**************************
			this.copiePrincipal.curAlg.getAlgoConsts().add(curConstraint);
			Constraint ctr = new Constraint();
			
			ctr.setTaskName(curConstraint.getTaskName());
			ctr.setNumLig(curConstraint.getNumLig());
			ctr.setPenalite(curConstraint.getPenalite());
			ctr.setSource(curConstraint.getSource());
			ctr.setTarget(curConstraint.getTarget());
			int i = Integer.parseInt(curConstraint.getType());
			switch(i)
			{
				case 2 :
							ctr.setType("Présence");
							break;
				case 3 :	
							ctr.setType("Absence");
							break;
				case 4 :	
							ctr.setType("Desordre Taches");
							break;
				case 5 :	
							ctr.setType("Desordre Lignes");
							break;
				case 6 :	
							ctr.setType("Desordre Taches");
							break;
				case 7 :	
							ctr.setType("Desordre Lignes");
							break;
			}
			this.Ctrs_Model.addCtr(ctr);
			this.Ctrs_table.setRowSelectionInterval(this.Ctrs_Model.getRowCount()-1, this.Ctrs_Model.getRowCount()-1);
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
	
	public int LastCtr = -1;
	public JLabel libType;
	public JLabel libNumLig;
	public JLabel libPenalite;
	public JLabel libTache;
	public JLabel libSrc;
	public JLabel libDes;
	
	public JComboBox chpType;
	public JComboBox chpTache;
	
	public JComboBox chpSrc;
	public JComboBox chpDes;
	
	public JComboBox chpNumLig;
	public JFormattedTextField chpPenalite;

	
	public JButton btnAdd ;
	public JButton btnDelete ;
	public JButton btnMod ;
	public JButton btnValider;
	public JButton btnAnnuler;
	public JButton btnSortir;
	
	public JTable Ctrs_table;
	public JScrollPane Ctrs_Pane;
	public ModeleContraintes Ctrs_Model;
}


