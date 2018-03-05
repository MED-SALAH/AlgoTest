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

public class frmRead extends EmptyFrame{
	
	public frmRead(AlgoEditor pPrincipal)
	{
		this.mother = pPrincipal.mainFrame;
		this.copiePrincipal =  pPrincipal;
		this.setName("readFrm");
		setTitle("Lecture");
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
		// Lib Nom de l variable
		JLabel	libNameVar = new JLabel("Nom ");
		
		// Lib Type de l variable
		JLabel	libRangInt = new JLabel("Rang ");
		
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
		chpRangInt.setValue(new Integer(0)); 
		chpRangInt.setEditable(false);
		chpNameVar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String varName = (String) frmRead.this.chpNameVar.getSelectedItem();
				Variable var = frmRead.this.copiePrincipal.analyser.nameToVar(varName,frmRead.this.copiePrincipal.curAlg.getAlgoVars());
				if(var !=null)
				{
					if(Integer.parseInt(var.getType())== 3 ||Integer.parseInt(var.getType())== 4)
						{
							frmRead.this.chpRangInt.setEditable(true);
							frmRead.this.varType.setEnabled(true);
							//frmRead.this.chpRangVar.setEnabled(true);
						}
						else
							{
								frmRead.this.chpRangInt.setEditable(false);
								frmRead.this.varType.setSelected(false);
								frmRead.this.varType.setEnabled(false);
								frmRead.this.chpRangVar.setEnabled(false);
							}
				}
			}
		});
		// Boutton de validation
		varType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (frmRead.this.varType.isSelected())
				{
					frmRead.this.chpRangInt.setEditable(false);
					chpRangInt.setValue(new Integer(0));
					frmRead.this.chpRangVar.setEnabled(true);
				}
				else
				{
					frmRead.this.chpRangInt.setEditable(true);
					frmRead.this.chpRangVar.setEnabled(false);
				}
			}
		});
		
		JButton btnValider = new JButton("Valider");
		// Boutton de validation
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmRead.this.valider();
			}
		});
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
		mainPanel.add(btnValider,new GBC(3,3,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GBC.SOUTHEAST));
		// Annuler
		mainPanel.add(btnAnnuler,new GBC(4,3,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GBC.SOUTHEAST));
		if(typeOuv == 2)
		{	
			chargerInstruction();
		}
		}
	public void chargerInstruction()
	{
		String valueRead = "";
		String rangeRead = "";
		NamedNodeMap attributes = this.instACharger.getNoeud().getAttributes();
		for (int k  = 0; k < attributes.getLength(); k++)
		{
			Node attribute = attributes.item(k);
			String name = attribute.getNodeName();
			String value = attribute.getNodeValue();
			if (name == "value")
			{
				valueRead = value;
			}			
			else
				if (name == "range")
				{
					rangeRead = value;
				}	
		}
		chargeVars();
		chargeVarsRange();
		Variable vRangRead = this.copiePrincipal.analyser.nameToVar(valueRead, this.copiePrincipal.curAlg.getAlgoVars());
		this.chpNameVar.setSelectedItem(valueRead);
		if(vRangRead.getType().equals("4")||vRangRead.getType().equals("5"))
		{
			this.chpRangVar.setSelectedItem(rangeRead);
			this.chpRangVar.setEnabled(true);
			this.chpRangInt.setEditable(false);
			
		}
		else
			{
				this.chpRangInt.setText(rangeRead);
				this.chpRangVar.setEnabled(false);
				this.chpRangInt.setEditable(true);
			}
		

		
	}
	public void chargerRead()
	{
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
			String range;
			int numLine = this.copiePrincipal.curLine;
			if(!value.equals(""))
			{
				
				Variable var = frmRead.this.copiePrincipal.analyser.nameToVar(value,frmRead.this.copiePrincipal.curAlg.getAlgoVars());
				if(Integer.parseInt(var.getType())== 3 ||Integer.parseInt(var.getType())== 4)
				{
					if(!this.varType.isSelected())
						range = this.chpRangInt.getText();
					else
						range = (String)this.chpRangVar.getSelectedItem();
				}
				else 
					range = "";
				
				Element readElt = this.copiePrincipal.currentDoc.createElement("Read");
				readElt.setAttribute("range", range);
				readElt.setAttribute("value", value);
				Instruction readInst = new Instruction();
				readInst.setNum(numLine+1);
				readInst.setNoeud(readElt);
				int pos;
				if(this.typeOuv == 2)
				{
					pos = this.posInst;
					this.copiePrincipal.curAlg.getAlgoLines().set(pos,readInst);
				}
				else
					{
						pos = numLine-(this.copiePrincipal.curAlg.getAlgoVars().size()+2);
						this.copiePrincipal.curAlg.getAlgoLines().add(pos,readInst);
						this.copiePrincipal.curLine++;
					}
				
				this.typeOuv = 1;
				this.instACharger = null;
				this.copiePrincipal.refreshAlgoText();
				this.copiePrincipal.btnSaveSC.setEnabled(true);
				this.copiePrincipal.curAlg.setSaved(false);
			}
			this.dispose();
		}
		public JComboBox	chpNameVar;
		public JComboBox	chpRangVar;
		public JCheckBox varType ;
		public JFormattedTextField chpRangInt;
		public int typeOuv = 1 ; // pour le type d'ouverture de la fenetre 
		public Instruction instACharger = null;
		public int posInst;
}
