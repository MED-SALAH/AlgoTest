package GUI;

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

public class frmCondition extends EmptyFrame{
	
	public frmCondition(AlgoEditor pPrincipal)
	{
		this.mother = pPrincipal.mainFrame;
		this.copiePrincipal =  pPrincipal;
		setTitle("Condition");
		setSize(450, 200);
		setResizable(false);
		this.setName("condFrm");
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
		// Lib Nom de l variable
		JLabel	libCond = new JLabel("Condition ");
		JLabel	libAcSinon = new JLabel("Avec Sinon ");
		chpCond = new JTextField();
		this.visExp = chpCond;
		chpCond.setEditable(false);
		// Lib Type de l variable
		
		//CheckBox
		avecSinon = new JCheckBox();
		// Boutton de validation
		JButton btnValider = new JButton("Valider");
		// Boutton de validation
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//if (frmCondition.this.exp!=null)
					frmCondition.this.valider();
			}
		});
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new exitListener(this));
		JButton btnExpression = new JButton("Exp");
		btnExpression.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frmCondition.this.condExp = new frmExp(frmCondition.this);
				frmCondition.this.setEnabled(false);
				frmCondition.this.condExp.setVisible(true);
			}
		});

		// Lib Noms
		mainPanel.add(libCond,new GBC(0,0,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(chpCond,new GBC(1,0,8,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(libAcSinon,new GBC(4,1,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		mainPanel.add(avecSinon,new GBC(5,1,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		// Lib RangInt
		//mainPanel.add(chpCond,new GBC(4,1,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		mainPanel.add(btnExpression,new GBC(6,1,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		// Valider
		mainPanel.add(btnValider,new GBC(5,2,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		// Annuler
		mainPanel.add(btnAnnuler,new GBC(6,2,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));

		if(typeOuv == 2)
		{	
			chargerInstruction();
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
	public void valider()
	{
		/*Element varElt = this.copiePrincipal.currentDoc.createElement("Var");
		varElt.setAttribute("nom", this.chpNameVar.getText());
		varElt.setAttribute("type", Integer.toString(this.chpTypeVar.getSelectedIndex()+1));
		varElt.setAttribute("taille", this.chpTaille.getText());
		this.copiePrincipal.rootAlgo.appendChild(varElt);
		*/
		String cond = this.chpCond.getText();
		String avecSion = "";
		Element elseElt = null;
		int numLine = this.copiePrincipal.curLine;
		if(!cond.equals(""))
		{
			if(this.avecSinon.isSelected())
			{
				avecSion = "1";
				elseElt = this.copiePrincipal.currentDoc.createElement("Else");
			}
			else
				avecSion = "0";
			Element ifElt = this.copiePrincipal.currentDoc.createElement("If");
			ifElt.setAttribute("cond", cond);
			ifElt.setAttribute("avecSion", avecSion);
			Instruction ifInst = new Instruction();
			Instruction elseInst = new Instruction();
			Instruction beginIfInst = new Instruction();
			Instruction endIfInst = new Instruction();
			Element beginIfElt = this.copiePrincipal.currentDoc.createElement("BeginIf");
			Element endIfElt = this.copiePrincipal.currentDoc.createElement("EndIf");
			if(frmCondition.this.exp !=null)
			{
				Iterator<Expression> it = frmCondition.this.exp.iterator();
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
					beginIfElt.appendChild(expElt);
				}
			}
			
			
			
			
			
			//********************************************************************************
			
			ifInst.setNum(numLine+1);
			ifInst.setExpressions(frmCondition.this.exp);
			ifInst.setNoeud(ifElt);
			//this.copiePrincipal.curAlg.getAlgoLines().add(numLine,ifInst);
			if(this.typeOuv == 1)
			{
				int pos1 = numLine-(this.copiePrincipal.curAlg.getAlgoVars().size()+2);
				this.copiePrincipal.curAlg.getAlgoLines().add(pos1,ifInst);
				
				//111*************************************
				numLine++;
				beginIfInst.setNum(numLine+1);
				beginIfInst.setNoeud(beginIfElt);
				//this.copiePrincipal.curAlg.getAlgoLines().add(numLine,beginIfInst);
				int pos = numLine-(this.copiePrincipal.curAlg.getAlgoVars().size()+2);
				this.copiePrincipal.curAlg.getAlgoLines().add(pos,beginIfInst);
				//***************************
				if (elseElt != null)
				{
					numLine++;
					elseInst.setNum(numLine+1);
					elseInst.setNoeud(elseElt);
					//this.copiePrincipal.curAlg.getAlgoLines().add(numLine,elseInst);
					int pos0 = numLine-(this.copiePrincipal.curAlg.getAlgoVars().size()+2);
					this.copiePrincipal.curAlg.getAlgoLines().add(pos0,elseInst);
					this.copiePrincipal.curLine  = numLine;
				}
				else
					this.copiePrincipal.curLine  = numLine+1;
				
				//*******************************************
				numLine++;
				
				endIfInst.setNum(numLine+1);
				endIfInst.setNoeud(endIfElt);
				//this.copiePrincipal.curAlg.getAlgoLines().add(numLine,endIfInst);
				int pos2 = numLine-(this.copiePrincipal.curAlg.getAlgoVars().size()+2);
				this.copiePrincipal.curAlg.getAlgoLines().add(pos2,endIfInst);
			}
			else
			{
				int pos = this.posInst;
				this.copiePrincipal.curAlg.getAlgoLines().set(pos,ifInst);
			}

			//*******************************
			//this.copiePrincipal.curLine = numLine-1;
			//**************************
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
	public JTextField chpCond;
	public JCheckBox avecSinon;
	public int typeOuv = 1 ; // pour le type d'ouverture de la fenetre 
	public Instruction instACharger = null;
	public int posInst;
}


