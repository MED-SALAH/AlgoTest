package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.NumberFormat;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout.Constraints;

import org.w3c.dom.Element;

import recognizer.Variable;

import meiters.dauther;
import meiters.exitListener;

import editor.AlgoEditor;
import editor.EmptyFrame;
import editor.GBC;

public class frmDeclaration extends EmptyFrame {
	
	
	public frmDeclaration(AlgoEditor pPrincipal)
	{
		this.mother = pPrincipal.mainFrame;
		this.copiePrincipal =  pPrincipal;
		setTitle("Declaration d'une varible.");
		this.setName("decFrm");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 200);
		setResizable(false);
		addWindowListener(new dauther( pPrincipal.mainFrame,this));
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
		JLabel	libTypeVar = new JLabel("Type ");
		
		// Lib taille de l variable
		JLabel	libTaille = new JLabel("Taille ");
		
		// Chp Nom de l variable
		chpNameVar = new JTextField();
		// Chp Type de l variable
		chpTypeVar = new JComboBox(new String[]{"Nombre", "Caractere", "Const","Tableau de nombres", "Tableau de caracteres","Booleen"});
		chpTypeVar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (frmDeclaration.this.chpTypeVar.getSelectedIndex()==3 ||frmDeclaration.this.chpTypeVar.getSelectedIndex()==4)
					//frmDeclaration.this.chpTaille.setEnabled(true);
					chpTaille.setEditable(true);
				else
					{
						frmDeclaration.this.chpTaille.setText("1");
						//frmDeclaration.this.chpTaille.setEnabled(false);
						chpTaille.setEditable(false);
					}
			}
		});
		//Chp Taille de l variable, s'il s'agit d'un type tableau
		chpTaille = new    
	    JFormattedTextField(NumberFormat.getIntegerInstance());
		chpTaille.setValue(new Integer(1)); 
		chpTaille.setEditable(false);
		// Boutton de validation
		JButton btnValider = new JButton("Valider");
		// Boutton de validation
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frmDeclaration.this.valider();
			}
		});
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new exitListener(this));
		// gestionnaires des composants
		// Lib Nom
		GridBagConstraints gbc = new GridBagConstraints();
		
		mainPanel.add(libNameVar,new GBC(0,0,1,1).setInsets(5).setFill(GridBagConstraints.VERTICAL).setAnchor(GBC.NORTHWEST));
		// Lib Type
		mainPanel.add(libTypeVar,new GBC(0,1,1,1).setInsets(10).setFill(GridBagConstraints.HORIZONTAL));
		// Lib taille
		
		mainPanel.add(libTaille,new GBC(3,1,1,1).setInsets(10).setFill(GridBagConstraints.HORIZONTAL));
		// Nom
		mainPanel.add(chpNameVar,new GBC(1,0,4,1).setInsets(10).setFill(GridBagConstraints.BOTH).setAnchor(GBC.NORTHWEST));
		// Type
		mainPanel.add(chpTypeVar,new GBC(1,1,1,1).setInsets(10).setFill(GridBagConstraints.HORIZONTAL));
		// taille
		mainPanel.add(chpTaille,new GBC(4,1,1,1).setInsets(10).setFill(GridBagConstraints.BOTH));
		// Valider
		mainPanel.add(btnValider,new GBC(3,2,1,1).setInsets(10).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GBC.SOUTHEAST));
		// Annuler
		mainPanel.add(btnAnnuler,new GBC(4,2,1,1).setInsets(10).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GBC.SOUTHEAST));
		if(typeOuv == 2)
		{	
			chargerVariable();
		}
		
		}
	public void valider()
	{
		
		if (this.chpNameVar.getText().indexOf(" ")!=-1 ||this.chpNameVar.getText().indexOf("²")!=-1||this.chpNameVar.getText().indexOf("&")!=-1||this.chpNameVar.getText().indexOf("é")!=-1
				||this.chpNameVar.getText().indexOf("\"")!=-1||this.chpNameVar.getText().indexOf("'")!=-1||this.chpNameVar.getText().indexOf("(")!=-1||this.chpNameVar.getText().indexOf(")")!=-1
				||this.chpNameVar.getText().indexOf("-")!=-1||this.chpNameVar.getText().indexOf("è")!=-1||this.chpNameVar.getText().indexOf("ç")!=-1||this.chpNameVar.getText().indexOf("à")!=-1
				||this.chpNameVar.getText().indexOf(")")!=-1||this.chpNameVar.getText().indexOf("=")!=-1||this.chpNameVar.getText().indexOf("*")!=-1
				||this.chpNameVar.getText().indexOf(",")!=-1||this.chpNameVar.getText().indexOf(";")!=-1||this.chpNameVar.getText().indexOf(":")!=-1
				||this.chpNameVar.getText().indexOf("!")!=-1||this.chpNameVar.getText().indexOf("<")!=-1||this.chpNameVar.getText().indexOf(">")!=-1
				||this.chpNameVar.getText().indexOf("?")!=-1||this.chpNameVar.getText().indexOf(".")!=-1||this.chpNameVar.getText().indexOf("/")!=-1
				||this.chpNameVar.getText().indexOf("§")!=-1||this.chpNameVar.getText().indexOf("+")!=-1||this.chpNameVar.getText().indexOf("°")!=-1
				||this.chpNameVar.getText().indexOf("~")!=-1||this.chpNameVar.getText().indexOf("#")!=-1||this.chpNameVar.getText().indexOf("{")!=-1
				||this.chpNameVar.getText().indexOf("[")!=-1||this.chpNameVar.getText().indexOf("|")!=-1||this.chpNameVar.getText().indexOf("\\")!=-1
				||this.chpNameVar.getText().indexOf("^")!=-1||this.chpNameVar.getText().indexOf("@")!=-1||this.chpNameVar.getText().indexOf("]")!=-1
				||this.chpNameVar.getText().indexOf("}")!=-1)
		{
			JOptionPane.showMessageDialog(null,
				    "Le nom d'une variable de doit pas contenir d'espace, ni de caractère special.",
				    " Attention",
				    JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			if(this.copiePrincipal.currentDoc!=null)
			{
				
				if(this.typeOuv == 1)
				{
					if(! isDec(this.chpNameVar.getText().trim()))
					{
						/*Element varElt = this.copiePrincipal.currentDoc.createElement("Var");
						varElt.setAttribute("nom", this.chpNameVar.getText());
						varElt.setAttribute("type", Integer.toString(this.chpTypeVar.getSelectedIndex()+1));
						varElt.setAttribute("taille", this.chpTaille.getText());
						this.copiePrincipal.rootAlgo.appendChild(varElt);
						*/
						Variable  myVariable = new Variable();
						myVariable.setName(this.chpNameVar.getText());
						myVariable.setType(Integer.toString(this.chpTypeVar.getSelectedIndex()+1));
						myVariable.setTaille(this.chpTaille.getText());
						int pos;
						if(this.copiePrincipal.curLine -1 >= this.copiePrincipal.curAlg.getAlgoVars().size())
							pos = this.copiePrincipal.curAlg.getAlgoVars().size();
							else
								pos = this.copiePrincipal.curLine -1; 
						//if(this.copiePrincipal.curLine<this.copiePrincipal.curAlg.getAlgoVars().size())
						//	this.copiePrincipal.curAlg.getAlgoVars().add(this.copiePrincipal.curLine-2,myVariable);
						//else
							this.copiePrincipal.curAlg.getAlgoVars().add(pos,myVariable);
							/*java.util.Iterator<Variable> itv = this.copiePrincipal.curAlg.getAlgoVars().iterator();
							while(itv.hasNext())
								System.out.println("est une :**** "+itv.next().getName());*/
						/*int i = 2;
						Iterator<Variable> it = this.copiePrincipal.curAlg.getAlgoVars().iterator();
						while(it.hasNext())
						{
							
						}*/
						this.typeOuv = 1;
						this.varACharger = null;
						this.copiePrincipal.refreshAlgoText();
						this.copiePrincipal.btnSaveSC.setEnabled(true);
						this.copiePrincipal.curAlg.setSaved(false);
						this.dispose();

					}
					else
						JOptionPane.showMessageDialog(null,
							    "Cette varaiable est deja déclarée.",
							    " Attention",
							    JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					this.copiePrincipal.curAlg.getAlgoVars().get(this.posVar).setName(this.chpNameVar.getText());
					this.copiePrincipal.curAlg.getAlgoVars().get(this.posVar).setType(Integer.toString(this.chpTypeVar.getSelectedIndex()+1));
					this.copiePrincipal.curAlg.getAlgoVars().get(this.posVar).setTaille(this.chpTaille.getText());
					this.typeOuv = 1;
					this.varACharger = null;
					this.copiePrincipal.refreshAlgoText();
					this.copiePrincipal.btnSaveSC.setEnabled(true);
					this.copiePrincipal.curAlg.setSaved(false);
					this.dispose();
				}
			}
		}
	}
	//public JFrame mother;
	public boolean isDec(String pName)
	{
		Iterator<Variable> it = this.copiePrincipal.curAlg.getAlgoVars().iterator();
		while(it.hasNext())
		{
			Variable item = it.next();
			if(item.getName().equals(pName))
				return true;
		}
		return false;
		
	}
	public void chargerVariable()
	{
		String VarName = this.varACharger.getName();
		int VarType = Integer.parseInt(this.varACharger.getType());
		String VarTaille = this.varACharger.getTaille();
		this.chpNameVar.setText(VarName);
		this.chpTypeVar.setSelectedIndex(VarType-1);
		if(VarType == 4 || VarType == 5)
		{
			this.chpTaille.setText(VarTaille);
			this.chpTaille.setEditable(false);
			//this.chpTypeVar.setEnabled(true);
		}
		else
			{
				this.chpTaille.setText("0");
				this.chpTaille.setEditable(true);
				//this.chpTypeVar.setEnabled(true);
			}
	}
	JTextField	chpNameVar;
	JComboBox	chpTypeVar;
	JFormattedTextField chpTaille;
	
	public int typeOuv = 1 ; // pour le type d'ouverture de la fenetre 
	public Variable varACharger = null;
	public int posVar;
	
}
