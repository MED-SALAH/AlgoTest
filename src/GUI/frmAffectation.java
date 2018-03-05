package GUI;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout.Constraints;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import recognizer.Expression;
import recognizer.Instruction;
import recognizer.Variable;

import meiters.dauther;
import meiters.exitListener;

import editor.AlgoEditor;
import editor.EmptyFrame;
import editor.GBC;

public class frmAffectation extends EmptyFrame{
	
	public frmAffectation(AlgoEditor pPrincipal)
	{
		this.mother = pPrincipal.mainFrame;
		this.copiePrincipal =  pPrincipal;
		setTitle("Affectation");
		setSize(450, 250);
		setResizable(false);
		this.setName("affecttionFrm");
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
		this.setContentPane(mainPanel);
		// Composants graphiques
		// Lib Nom de l variable
		/*JLabel	libNameVar = new JLabel("Nom ");
		// Lib Nom de l variable*/
		JLabel	libVal = new JLabel("Valeur ");
		chpVal = new JTextField();
		chpVal.setEditable(false);
		this.visExp = chpVal;
		// Lib Type de l variable
		/*JLabel	libRangInt = new JLabel("Rang ");
		
		// Lib taille de l variable
		JLabel	libRangVar = new JLabel("Var ");
		
		// Chp Nom de l variable
		chpNameVar = new JComboBox();
		// Chp Type de l variable
		chpRangVar = new JComboBox();
		chpRangVar.setEnabled(false);
		//CheckBox
		varType = new JCheckBox();
		//Chp Taille de l variable, s'il s'agit d'un type tableau
		chpRangInt = new    
	    JFormattedTextField(NumberFormat.getIntegerInstance());
		chpRangInt.setValue(new Integer(1)); 
		chpRangInt.setEditable(false);
		// Boutton de validation
		chpNameVar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String varName = (String) frmAffectation.this.chpNameVar.getSelectedItem();
				Variable var = frmAffectation.this.copiePrincipal.analyser.nameToVar(varName,frmAffectation.this.copiePrincipal.curAlg.getAlgoVars());
				if(var !=null)
				{
					if(Integer.parseInt(var.getType())== 3 ||Integer.parseInt(var.getType())== 4)
						{
							frmAffectation.this.chpRangInt.setEditable(true);
							frmAffectation.this.varType.setEnabled(true);
							//frmRead.this.chpRangVar.setEnabled(true);
						}
						else
							{
								frmAffectation.this.chpRangInt.setEditable(false);
								frmAffectation.this.varType.setSelected(false);
								frmAffectation.this.varType.setEnabled(false);
								frmAffectation.this.chpRangVar.setEnabled(false);
							}
				}
			}
		});*/
		JButton btnValider = new JButton("Valider");
		// Boutton de validation
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new exitListener(this));
		JButton btnExpression = new JButton("Expession");
		btnExpression.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frmAffectation.this.condExp = new frmExp(frmAffectation.this);
				frmAffectation.this.setEnabled(false);
				frmAffectation.this.condExp.setVisible(true);
			}
		});
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//if (frmCondition.this.exp!=null)
					frmAffectation.this.valider();
			}
		});
		// gestionnaires des composants
		/*varType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (frmAffectation.this.varType.isSelected())
				{
					frmAffectation.this.chpRangInt.setEditable(false);
					chpRangInt.setValue(new Integer(0));
					frmAffectation.this.chpRangVar.setEnabled(true);
				}
				else
				{
					frmAffectation.this.chpRangInt.setEditable(true);
					frmAffectation.this.chpRangVar.setEnabled(false);
				}
			}
		});*/
		GridBagConstraints gbc = new GridBagConstraints();
		// Lib Noms
		//mainPanel.add(libNameVar,new GBC(0,0,1,1).setInsets(5).setFill(GridBagConstraints.VERTICAL).setAnchor(GBC.NORTHWEST));
		mainPanel.add(libVal,new GBC(0,0,1,1).setInsets(5).setFill(GridBagConstraints.VERTICAL));
		// Lib RangInt
		/*mainPanel.add(libRangInt,new GBC(0,2,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		// RangVar
		mainPanel.add(libRangVar,new GBC(0,3,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		// Lib taille
		*/
		
		//mainPanel.add(chpNameVar,new GBC(1,0,4,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		mainPanel.add(chpVal,new GBC(1,0,4,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		mainPanel.add(btnExpression,new GBC(5,0,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		// Nom
		//mainPanel.add(chpRangInt,new GBC(1,2,3,1).setInsets(5).setFill(GridBagConstraints.BOTH).setAnchor(GBC.NORTHWEST));
		// Type
		//mainPanel.add(chpRangVar,new GBC(1,3,4,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		// taille
		//mainPanel.add(varType,new GBC(5,3,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		// Valider
		mainPanel.add(btnValider,new GBC(3,1,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GBC.SOUTHEAST));
		// Annuler
		mainPanel.add(btnAnnuler,new GBC(4,1,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GBC.SOUTHEAST));
		
		if(typeOuv == 2)
		{	
			chargerInstruction();
		}
	}
	public void chargerInstruction()
	{
		this.exp = this.instACharger.getExpressions();
		String expTexte = this.copiePrincipal.analyser.getTextExp(this.exp);
		this.visExp.setText(expTexte);
		

		
	}

	/*public  void chargeVars()
	{
		this.chpNameVar.removeAllItems();
		//this.chpRangVar.removeAllItems();
		Iterator<Variable> it = this.copiePrincipal.curAlg.getAlgoVars().iterator();
		System.out.println("la taille:"+this.copiePrincipal.curAlg.getAlgoVars().size());
		while(it.hasNext())
			{
				Variable item = it.next(); 
				//System.out.println(item.getName());
				
					this.chpNameVar.addItem(item.getName());
				//this.chpRangVar.addItem(item.getName());
			}	
	}
	public  void chargeVarsRange()
	{
		//this.chpNameVar.removeAllItems();
		this.chpRangVar.removeAllItems();
		Iterator<Variable> it = this.copiePrincipal.curAlg.getAlgoVars().iterator();
		//System.out.println("la taille:"+this.copiePrincipal.curAlg.getAlgoVars().size());
		while(it.hasNext())
			{
				Variable item = it.next(); 
				//System.out.println(item.getName());
				
				if(Integer.parseInt(item.getType())!= 3 && Integer.parseInt(item.getType())!= 4)
				{
					//this.chpNameVar.addItem(item.getName());
					this.chpRangVar.addItem(item.getName());
				}
			}	
	}*/
	public void valider()
	{
		/*Element varElt = this.copiePrincipal.currentDoc.createElement("Var");
		varElt.setAttribute("nom", this.chpNameVar.getText());
		varElt.setAttribute("type", Integer.toString(this.chpTypeVar.getSelectedIndex()+1));
		varElt.setAttribute("taille", this.chpTaille.getText());
		this.copiePrincipal.rootAlgo.appendChild(varElt);
		*/
		String cond = this.chpVal.getText();
		int numLine = this.copiePrincipal.curLine;
		if(!cond.equals(""))
		{
			Element affElt = this.copiePrincipal.currentDoc.createElement("Affectation");
			Instruction affInst = new Instruction();
			if(frmAffectation.this.exp !=null)
			{
				Iterator<Expression> it = frmAffectation.this.exp.iterator();
				while (it.hasNext())
				{
					Expression curExp = it.next();
					Element expElt = this.copiePrincipal.currentDoc.createElement("exp");
					expElt.setAttribute("name",curExp.getName());
					expElt.setAttribute("left",curExp.getLeft());
					expElt.setAttribute("right",curExp.getRight());
					expElt.setAttribute("op",curExp.getOp());
					expElt.setAttribute("rangLeft",curExp.getRangLeft());
					expElt.setAttribute("rangRight",curExp.getRangRight());
					affElt.appendChild(expElt);
				}
			}
			
			
			
			
			
			//********************************************************************************
			
			affInst.setNum(numLine+1);
			affInst.setExpressions(frmAffectation.this.exp);
			affInst.setNoeud(affElt);
			int pos;
			if(this.typeOuv == 2)
			{
				pos = this.posInst;
				this.copiePrincipal.curAlg.getAlgoLines().set(pos,affInst);
			}
			else
				{
					pos = numLine-(this.copiePrincipal.curAlg.getAlgoVars().size()+2);
					this.copiePrincipal.curAlg.getAlgoLines().add(pos,affInst);
					this.copiePrincipal.curLine++;
				}
			this.copiePrincipal.refreshAlgoText();
			this.copiePrincipal.btnSaveSC.setEnabled(true);
			this.copiePrincipal.curAlg.setSaved(false);
			this.dispose();
		}
		else
		{
			JOptionPane.showMessageDialog(null,
				    "Le champ condition ne doit pas être vide..",
				    " Attention",
				    JOptionPane.WARNING_MESSAGE);
		}
		
	}
	public frmExp condExp;
	public JTextField chpVal;
	public JComboBox chpNameVar;
	public 	JCheckBox varType;
	public JFormattedTextField chpRangInt;
	public JComboBox	chpRangVar;
	
	public int typeOuv = 1 ; // pour le type d'ouverture de la fenetre 
	public Instruction instACharger = null;
	public int posInst;
}

