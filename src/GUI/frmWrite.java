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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout.Constraints;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import recognizer.Instruction;
import recognizer.Variable;

import meiters.dauther;
import meiters.exitListener;

import editor.AlgoEditor;
import editor.EmptyFrame;
import editor.GBC;

public class frmWrite extends EmptyFrame{
	
	public frmWrite(AlgoEditor pPrincipal)
	{
		this.mother = pPrincipal.mainFrame;
		this.copiePrincipal =  pPrincipal;
		setTitle("Ecriture");
		this.setName("writeFrm");
		setSize(400, 200);
		setResizable(false);
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
		chpText = new JTextField();
		chpText.setEditable(true);
		// Lib Nom de l variable
		JLabel	libNameVar = new JLabel("Nom ");
		
		// Lib Type de l variable
		JLabel	libRangInt = new JLabel("Rang ");
		
		// Lib taille de l variable
		JLabel	libRangVar = new JLabel("Var ");
		
		// Lib chaine
		JLabel	libChaine = new JLabel("Chaine ");
		
		// Chp Nom de l variable
		chpNameVar = new JComboBox();
		chpNameVar.setEnabled(false);
		// Chp Type de l variable
		chpRangVar = new JComboBox();
		chpRangVar.setEnabled(false);
		//CheckBox
		varType = new JCheckBox();
		varType.setEnabled(false);
		//CheckBox
		chkText = new JCheckBox();
		chkText.setSelected(true);
		//Chp Taille de l variable, s'il s'agit d'un type tableau
		chpRangInt = new    
	    JFormattedTextField(NumberFormat.getIntegerInstance());
		chpRangInt.setValue(new Integer(0)); 
		chpRangInt.setEditable(false);
		// Boutton de validation
		varType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (frmWrite.this.varType.isSelected())
				{
					frmWrite.this.chpRangInt.setEditable(false);
					frmWrite.this.chpRangInt.setValue(new Integer(0));
					frmWrite.this.chpRangVar.setEnabled(true);
					//frmWrite.this.chkText.setSelected(false);
					//frmWrite.this.chkText.setEnabled(false);
					//frmWrite.this.chpText.setText("");
					//frmWrite.this.chpText.setEditable(false);
					
				}
				else
				{
					frmWrite.this.chpRangInt.setEditable(true);
					frmWrite.this.chpRangVar.setEnabled(false);
					//frmWrite.this.chpText.setEditable(false);
					//frmWrite.this.chkText.setEnabled(true);
				}
			}
		});
		chkText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (frmWrite.this.chkText.isSelected())
				{
					/*frmWrite.this.chpRangInt.setEditable(false);
					frmWrite.this.chpRangInt.setValue(new Integer(0));
					frmWrite.this.chpRangVar.setEnabled(true);*/
					frmWrite.this.chpText.setEditable(true);
					frmWrite.this.chpNameVar.setEnabled(false);
					frmWrite.this.chpRangInt.setEditable(false);
					frmWrite.this.chpRangVar.setEnabled(false);
					frmWrite.this.varType.setEnabled(false);
				}
				else
				{
					/*frmWrite.this.chpRangInt.setEditable(true);
					frmWrite.this.chpRangVar.setEnabled(false);*/
					frmWrite.this.chpText.setText("");
					frmWrite.this.chpText.setEditable(false);
					frmWrite.this.chpNameVar.setEnabled(true);
					//frmWrite.this.chkText.setEnabled(false);
					if(frmWrite.this.var !=null)
					{
						if(Integer.parseInt(frmWrite.this.var.getType())== 3 ||Integer.parseInt(frmWrite.this.var.getType())== 4)
							{
								frmWrite.this.chpRangInt.setEditable(true);
								frmWrite.this.varType.setEnabled(true);
									//frmRead.this.chpRangVar.setEnabled(true);
							}
							else
								{
									frmWrite.this.chpRangInt.setEditable(false);
									frmWrite.this.varType.setSelected(false);
									frmWrite.this.varType.setEnabled(false);
									frmWrite.this.chpRangVar.setEnabled(false);
								}
					}
					
				}
			}
		});
		chpNameVar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frmWrite.this.varName = (String) frmWrite.this.chpNameVar.getSelectedItem();
				frmWrite.this.var = frmWrite.this.copiePrincipal.analyser.nameToVar(varName,frmWrite.this.copiePrincipal.curAlg.getAlgoVars());
				if(frmWrite.this.var !=null)
				{
					if(Integer.parseInt(frmWrite.this.var.getType())== 3 ||Integer.parseInt(frmWrite.this.var.getType())== 4)
						{
							frmWrite.this.chpRangInt.setEditable(true);
							frmWrite.this.varType.setEnabled(true);
								//frmRead.this.chpRangVar.setEnabled(true);
						}
						else
							{
								frmWrite.this.chpRangInt.setEditable(false);
								frmWrite.this.varType.setSelected(false);
								frmWrite.this.varType.setEnabled(false);
								frmWrite.this.chpRangVar.setEnabled(false);
							}
				}
			}
		});
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmWrite.this.valider();
			}
		});	
		// Boutton de validation
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new exitListener(this));
		// gestionnaires des composants
		
		GridBagConstraints gbc = new GridBagConstraints();
		// Lib Noms
		mainPanel.add(libNameVar,new GBC(0,0,1,1).setInsets(5).setFill(GridBagConstraints.VERTICAL).setAnchor(GBC.NORTHWEST));
		// Lib RangInt
		mainPanel.add(libRangInt,new GBC(0,1,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		// RangVar
		mainPanel.add(libRangVar,new GBC(0,2,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		// Lib taille
		
		
		mainPanel.add(chpNameVar,new GBC(1,0,4,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		// Nom
		mainPanel.add(chpRangInt,new GBC(1,1,3,1).setInsets(5).setFill(GridBagConstraints.BOTH).setAnchor(GBC.NORTHWEST));
		// Type
		mainPanel.add(chpRangVar,new GBC(1,2,4,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		// taille
		mainPanel.add(varType,new GBC(5,2,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		// Valider
		mainPanel.add(btnValider,new GBC(3,4,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GBC.SOUTHEAST));
		// Annuler
		mainPanel.add(btnAnnuler,new GBC(4,4,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GBC.SOUTHEAST));
		mainPanel.add(chpText,new GBC(2,3,12,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(libChaine,new GBC(0,3,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(chkText,new GBC(14,3,1,1).setInsets(5));	
		if(typeOuv == 2)
		{	
			chargerInstruction();
		}
	}
	public void chargerInstruction()
	{
		String valueWrite = "";
		String rangeWrite = "";
		NamedNodeMap attributes = this.instACharger.getNoeud().getAttributes();
		for (int k  = 0; k < attributes.getLength(); k++)
		{
			Node attribute = attributes.item(k);
			String name = attribute.getNodeName();
			String value = attribute.getNodeValue();
			if (name == "value")
			{
				valueWrite = value;
			}			
			else
				if (name == "range")
				{
					rangeWrite = value;
				}	
		}
		chargeVars();
		chargeVarsRange();
		// s'il s'agit d'une variable
		if (this.copiePrincipal.analyser.isItVar(valueWrite, this.copiePrincipal.curAlg.getAlgoVars()))
		{
			this.chpNameVar.setEnabled(true); 
			this.chpText.setEditable(false);
			this.chkText.setSelected(false);
			Variable vRangWrite = this.copiePrincipal.analyser.nameToVar(valueWrite, this.copiePrincipal.curAlg.getAlgoVars());
			this.chpNameVar.setSelectedItem(valueWrite);
			if(vRangWrite.getType().equals("4")||vRangWrite.getType().equals("5"))
			{
				this.chpRangVar.setSelectedItem(rangeWrite);
				this.chpRangVar.setEnabled(true);
				this.chpRangInt.setEditable(false);
				
			}
			else
				{
					this.chpRangInt.setText(rangeWrite);
					this.chpRangVar.setEnabled(false);
					this.chpRangInt.setEditable(true);
				}
			this.chpText.setEditable(false);
		}
		else
		{
			this.chpText.setEditable(true);
			this.chkText.setSelected(true);
			this.chpText.setText(valueWrite);
			this.chpRangVar.setEnabled(false);
			this.chpRangInt.setEditable(false);
			this.chpNameVar.setEnabled(false);
		}
		
		

		
	}
	public  void chargeVars()
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
		System.out.println("la taille:"+this.copiePrincipal.curAlg.getAlgoVars().size());
		while(it.hasNext())
			{
				Variable item = it.next(); 
				//System.out.println(item.getName());
				
				if(Integer.parseInt(item.getType())!= 3 &&Integer.parseInt(item.getType())!= 4)
				{
					//this.chpNameVar.addItem(item.getName());
					this.chpRangVar.addItem(item.getName());
				}
			}	
	}
	public void valider()
	{
		/*Element varElt = this.copiePrincipal.currentDoc.createElement("Var");
		varElt.setAttribute("nom", this.chpNameVar.getText());
		varElt.setAttribute("type", Integer.toString(this.chpTypeVar.getSelectedIndex()+1));
		varElt.setAttribute("taille", this.chpTaille.getText());
		this.copiePrincipal.rootAlgo.appendChild(varElt);
		*/
		String value = (String)this.chpNameVar.getSelectedItem();
		String range = "";
		int numLine = this.copiePrincipal.curLine;
		if(chkText.isSelected())
		{
			value = chpText.getText();
			range = "";
			Element writeElt = this.copiePrincipal.currentDoc.createElement("Write");
			writeElt.setAttribute("range", range);
			writeElt.setAttribute("value", value);
			Instruction writeInst = new Instruction();
			writeInst.setNum(numLine+1);
			writeInst.setNoeud(writeElt);
			int pos;
			//this.copiePrincipal.curAlg.getAlgoLines().add(numLine,writeInst);
			if(this.typeOuv == 2)
			{
				pos = this.posInst;
				this.copiePrincipal.curAlg.getAlgoLines().set(pos,writeInst);
			}
			else
				{
					pos = numLine-(this.copiePrincipal.curAlg.getAlgoVars().size()+2);
					this.copiePrincipal.curAlg.getAlgoLines().add(pos,writeInst);
					this.copiePrincipal.curLine++;
				}
			
			this.copiePrincipal.refreshAlgoText();
			this.copiePrincipal.btnSaveSC.setEnabled(true);
			this.copiePrincipal.curAlg.setSaved(false);
		}
		else
		{
			if(!value.equals(""))
			{
				
				Variable var = frmWrite.this.copiePrincipal.analyser.nameToVar(value,frmWrite.this.copiePrincipal.curAlg.getAlgoVars());
				if(Integer.parseInt(var.getType())== 3 || Integer.parseInt(var.getType())== 4)
				{
					if(!this.varType.isSelected())
						range = this.chpRangInt.getText();
					else
						range = (String)this.chpRangVar.getSelectedItem();
				}
				else 
					range = "";
				
				Element writeElt = this.copiePrincipal.currentDoc.createElement("Write");
				writeElt.setAttribute("range", range);
				writeElt.setAttribute("value", value);
				Instruction writeInst = new Instruction();
				writeInst.setNum(numLine+1);
				writeInst.setNoeud(writeElt);
				//this.copiePrincipal.curAlg.getAlgoLines().add(numLine,writeInst);
				int pos;
				if(this.typeOuv == 2)
				{
					pos = this.posInst;
					this.copiePrincipal.curAlg.getAlgoLines().set(pos,writeInst);
				}
				else
					{
						pos = numLine-(this.copiePrincipal.curAlg.getAlgoVars().size()+2);
						this.copiePrincipal.curAlg.getAlgoLines().add(pos,writeInst);
						this.copiePrincipal.curLine++;
					}
				this.copiePrincipal.refreshAlgoText();
				this.copiePrincipal.btnSaveSC.setEnabled(true);
				this.copiePrincipal.curAlg.setSaved(false);
			}
		}
		
		this.dispose();
	}
	public JComboBox	chpNameVar;
	public JComboBox	chpRangVar;
	public JCheckBox varType;
	public JCheckBox chkText;
	public JFormattedTextField chpRangInt;
	public JTextField chpText;
	//**********************
	public String varName ;
	public Variable  var;
	public int typeOuv = 1 ; // pour le type d'ouverture de la fenetre 
	public Instruction instACharger = null;
	public int posInst;
}
