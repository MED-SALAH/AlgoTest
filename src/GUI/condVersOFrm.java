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

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import recognizer.Constraint;
import recognizer.Expression;
import recognizer.Instruction;
import recognizer.Variable;

import meiters.ModeleCondVersO;
import meiters.TacheModele;
import meiters.dauther;
import meiters.exitListener;

import editor.AlgoEditor;
import editor.EmptyFrame;
import editor.GBC;

public class condVersOFrm extends EmptyFrame{
	
	public condVersOFrm(AlgoEditor pPrincipal)
	{
		this.mother = pPrincipal.mainFrame;
		this.copiePrincipal =  pPrincipal;
		setTitle("Autre Condition");
		setSize(550, 480);
		setResizable(false);
		this.setName("condVersOFrm");
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
		
		
		
		
		this.Conds_Model = new ModeleCondVersO();
		this.Conds_table = new JTable(this.Conds_Model);
		this.Conds_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.Conds_Pane = new JScrollPane(this.Conds_table);
		// Lib Nom de l variable
		JLabel	libCond = new JLabel("Condition ");
		JLabel	libAcSinon = new JLabel("Version ");
		chpCond = new JTextField();
		chpVersO = new JTextField();
		this.visExp = chpVersO;
		chpCond.setEditable(false);
		chpVersO.setEditable(false);
		// Lib Type de l variable
		
		//CheckBox
		//avecSinon = new JCheckBox();
		// Boutton de validation
		JButton btnValider = new JButton("Ajouter");
		// Boutton de validation
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//if (frmCondition.this.exp!=null)
				condVersOFrm.this.condExp = new frmExp(condVersOFrm.this);
				condVersOFrm.this.setEnabled(false);
				condVersOFrm.this.condExp.setVisible(true);
			}
		});
		JButton btnAnnuler = new JButton("Supprimer");
		//btnAnnuler.addActionListener(new exitListener(this));
		btnAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int i = condVersOFrm.this.Conds_table.getSelectedRow();
				 
	            if(i >= 0)
	            {
	            	Constraint curCtr = (Constraint) 	condVersOFrm.this.Conds_Model.getCtrs().get(i);
	            	Iterator<Constraint> it = condVersOFrm.this.copiePrincipal.curAlg.getAlgoConsts().iterator();
	            	int j = 0;
	            	while(it.hasNext())
	            	{
	            		Constraint item = it.next();
	            		String numLig = Integer.toString(condVersOFrm.this.copiePrincipal.curLine);
	            		ArrayList<Variable> vars = condVersOFrm.this.copiePrincipal.curAlg.getAlgoVars();
	            		String exp1;
	            		if (item.getExps() == null)
	            		{
	            			j++;
	            			continue;
	            		}
	            		else
	            		{
	            			exp1 =  condVersOFrm.this.copiePrincipal.analyser.getTextExp(item.getExps());
	            			String exp2 = condVersOFrm.this.copiePrincipal.analyser.getTextExp(curCtr.getExps());
		        			
		            		if(item.getNumLig().equals(numLig)&&exp1.equals(exp2))
		            		{
		            			condVersOFrm.this.copiePrincipal.curAlg.getAlgoConsts().remove(j);
		            			break;
		            		}
		            		j++;
	            		}	
	            	}
	            	condVersOFrm.this.Conds_Model.removeCtr(i);
	            	if(i-1>=0)
	            		condVersOFrm.this.Conds_table.setRowSelectionInterval(i-1, i-1);
	            	else
	            		if(condVersOFrm.this.Conds_table.getRowCount()>0)
	               			condVersOFrm.this.Conds_table.setRowSelectionInterval(i+1, i+1);
	            }		
			}
		});
		/*JButton btnExpression = new JButton("Exp");
		btnExpression.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				condVersOFrm.this.condExp = new frmExp(condVersOFrm.this);
				condVersOFrm.this.setEnabled(false);
				condVersOFrm.this.condExp.setVisible(true);
			}
		});*/

		// Lib Noms
		mainPanel.add(libCond,new GBC(0,0,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(chpCond,new GBC(1,0,8,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(libAcSinon,new GBC(0,1,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(chpVersO,new GBC(1,1,8,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		// Lib RangInt
		//mainPanel.add(chpCond,new GBC(4,1,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		//mainPanel.add(btnExpression,new GBC(9,1,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		// Valider
		mainPanel.add(btnValider,new GBC(5,2,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		// Annuler
		mainPanel.add(btnAnnuler,new GBC(6,2,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		this.add(this.Conds_Pane, BorderLayout.CENTER);
		this.add(mainPanel, BorderLayout.SOUTH);
		if(this.copiePrincipal.curAlg!=null && this.instACharger!=null)
		{
			//this.exp = ;
			this.charger();
			String textExp = this.copiePrincipal.analyser.getTextExp(this.instACharger.getExpressions());
			this.chpCond.setText(textExp);
		}
		else 
		{
			this.chpCond.setText("");
		}		
	}
	public void chargerInstruction()
	{
		String avecSion = "";
		String cond = "";
		NamedNodeMap attributes = this.instACharger.getNoeud().getAttributes();
		for (int k  = 0; k < attributes.getLength(); k++)
		{
			Node attribute = attributes.item(k);
			String name = attribute.getNodeName();
			String value = attribute.getNodeValue();
			if (name == "cond")
			{
				cond = value;
			}			
			else
				if (name == "avecSion")
				{
					avecSion = value;
				}	
		}
		
		this.exp = this.instACharger.getExpressions();
		//String expTexte = this.copiePrincipal.analyser.getTextExp(this.exp);
		this.visExp.setText(cond);
		if(avecSion.equals("1"))
			this.avecSinon.setSelected(true);
		else
			this.avecSinon.setSelected(false);
		

		
	}
	public void charger()
	{
		int i = 0;
		int nbreLig = this.Conds_table.getRowCount();
		while(i < nbreLig)
		{
			this.Conds_Model.removeCtr(i);
			i++;
		}
		Iterator<Constraint> it = condVersOFrm.this.copiePrincipal.curAlg.getAlgoConsts().iterator();
    	while(it.hasNext())
    	{
    		Constraint item = it.next();
    		String numLig = Integer.toString(this.copiePrincipal.curLine);
    		if(item.getNumLig().equals(numLig)&& item.getType().equals("8"))
    			this.Conds_Model.addCtr(item);
    	}
    	if(condVersOFrm.this.Conds_table.getRowCount()>0)
   			condVersOFrm.this.Conds_table.setRowSelectionInterval(0, 0);
	}
	public frmExp condExp;
	public JTextField chpCond;
	public JTextField chpVersO;
	public JCheckBox avecSinon;
	public int typeOuv = 1 ; // pour le type d'ouverture de la fenetre 
	public Instruction instACharger = null;
	public int posInst;
	
	
	public int LastCond = -1;
	
	public JTable Conds_table;
	public JScrollPane Conds_Pane;
	public ModeleCondVersO Conds_Model;	
}



