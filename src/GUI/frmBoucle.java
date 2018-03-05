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
import recognizer.ForInstruction;
import recognizer.Instruction;
import recognizer.Variable;

import meiters.dauther;
import meiters.exitListener;

import editor.AlgoEditor;
import editor.EmptyFrame;
import editor.GBC;

public class frmBoucle extends EmptyFrame{
	
	public frmBoucle(AlgoEditor pPrincipal)
	{
		this.mother = pPrincipal.mainFrame;
		this.copiePrincipal =  pPrincipal;
		setTitle("Boucle");
		setSize(450, 250);
		setResizable(false);
		this.setName("boucleFrm");
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
		chpNameVar = new JComboBox();
		JLabel	libType = new JLabel("TypeBoucle ");
		// Lib Nom de l variable
		JLabel	libCond = new JLabel("Condition ");
		chpCond = new JTextField();
		chpCond.setEditable(false);
		this.visExp = chpCond;
		// Lib Type de l variable
		JLabel	libDepart = new JLabel("Départ ");
		// Lib Type de l variable
		JLabel	libPas = new JLabel("Pas ");
	
		// Chp Nom de l variable
		chpType = new JComboBox(new String[]{"Pour", "Tant que"});
		chpType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(frmBoucle.this.chpType.getSelectedIndex() == 0)
				{
					frmBoucle.this.chpDepart.setEditable(true);
					frmBoucle.this.chpDepart.setText(Integer.toString(0));
					frmBoucle.this.chpPas.setEditable(true);
					frmBoucle.this.chpPas.setText(Integer.toString(1));
					frmBoucle.this.chpNameVar.setVisible(true);
				}
				else
				{
					frmBoucle.this.chpDepart.setEditable(false);
					frmBoucle.this.chpPas.setEditable(false);
					frmBoucle.this.chpNameVar.setVisible(false);
				}
			}
		});
		
		//Chp Taille de l variable, s'il s'agit d'un type tableau
		chpDepart = new    
	    JTextField();
		chpDepart.setText("0"); 
		//chpDepart.setEditable(false);
		//Chp Taille de l variable, s'il s'agit d'un type tableau
		chpPas = new    
	    JFormattedTextField(NumberFormat.getIntegerInstance());
		chpPas.setValue(new Integer(1)); 
		//chpPas.setEditable(false);
		// Boutton de validation
		JButton btnValider = new JButton("Valider");
		// Boutton de validation
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new exitListener(this));
		JButton btnExpression = new JButton("Expession");
		btnExpression.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frmBoucle.this.condExp = new frmExp(frmBoucle.this);
				frmBoucle.this.setEnabled(false);
				frmBoucle.this.condExp.setVisible(true);
			}
		});
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmBoucle.this.valider();
			}
		});
		
		
		// Lib Noms
		// Lib taille
		
		
		mainPanel.add(libType,new GBC(0,0,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		mainPanel.add(chpType,new GBC(1,0,4,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		mainPanel.add(chpNameVar,new GBC(5,0,2,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		mainPanel.add(btnExpression,new GBC(5,1,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		mainPanel.add(libCond,new GBC(0,1,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		mainPanel.add(chpCond,new GBC(1,1,4,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		mainPanel.add(libDepart,new GBC(0,2,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		// Nom
		mainPanel.add(chpDepart,new GBC(1,2,3,1).setInsets(5).setFill(GridBagConstraints.BOTH).setAnchor(GBC.NORTHWEST));
		// Type
		mainPanel.add(libPas,new GBC(0,3,4,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		// taille
		mainPanel.add(chpPas,new GBC(1,3,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		// Valider
		mainPanel.add(btnValider,new GBC(3,4,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GBC.SOUTHEAST));
		// Annuler
		mainPanel.add(btnAnnuler,new GBC(4,4,1,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GBC.SOUTHEAST));
		if(typeOuv == 2)
		{	
			chargerInstruction();
		}
		
	}
	public void chargerInstruction()
	{
		
		this.exp = this.instACharger.getExpressions();
		//String expTexte = this.copiePrincipal.analyser.getTextExp(this.exp);
		//this.visExp.setText(cond);
		if(this.instACharger.getNoeud().getNodeName().equals("For"))
			this.chargerPour();
		else
			this.chargerWhile();
		
		
		
	}
	public void chargerPour()
	{
		String valInitiale = "";
		String cond = "";
		String var = "";
		String pas = "";
		NamedNodeMap attributes = this.instACharger.getNoeud().getAttributes();
		this.chpType.setSelectedIndex(0);
		this.chpNameVar.setVisible(true);
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
				if (name == "var")
				{
					var = value;
				}	
				else
					if (name == "valInitiale")
					{
						valInitiale = value;
					}
					else
						if (name == "pas")
						{
							pas = value;
						}	
		}
		chargeVars();
		this.chpCond.setText(cond);
		this.chpNameVar.setSelectedItem(var); // attention du cas où la variable a ete supprimé
		this.chpPas.setText(pas);
		this.chpDepart.setText(valInitiale);
		
		
	} 
	public void chargerWhile()
	{
		String cond = "";
		NamedNodeMap attributes = this.instACharger.getNoeud().getAttributes();
		this.chpType.setSelectedIndex(1);
		this.chpNameVar.setVisible(false);
		for (int k  = 0; k < attributes.getLength(); k++)
		{
			Node attribute = attributes.item(k);
			String name = attribute.getNodeName();
			String value = attribute.getNodeValue();
			if (name == "cond")
				cond = value;
		}			
		this.chpCond.setText(cond);
		this.chpPas.setEditable(false);
		this.chpDepart.setEditable(false);
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
		if(!cond.equals(""))
		{
			if(this.chpType.getSelectedIndex() == 0)
			{
				validerPour();
			}
			else
			{
				validerWhile();
			}
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
				//this.chpNameVar.addItem(item.getName());
				
				if(item.getType().equals("1"))
					this.chpNameVar.addItem(item.getName());
			}	
	}
	public void validerPour()
	{
		String cond = this.chpCond.getText();
		String var = "";
		if(this.chpNameVar.getItemCount()>0)
			var = (String) this.chpNameVar.getSelectedItem();
		String valInitiale  = this.chpDepart.getText();
		String pas = this.chpPas.getText();
		int numLine = this.copiePrincipal.curLine;
		Element forElt = this.copiePrincipal.currentDoc.createElement("For");
		forElt.setAttribute("var", var);
		forElt.setAttribute("cond", cond);
		forElt.setAttribute("valInitiale", valInitiale );
		forElt.setAttribute("pas", pas );
		Instruction forInst = new Instruction();
		Instruction beginIfInst = new Instruction();
		Instruction endIfInst = new Instruction();
		Element beginForElt = this.copiePrincipal.currentDoc.createElement("BeginFor");
		Element endForElt = this.copiePrincipal.currentDoc.createElement("EndFor");
		if(frmBoucle.this.exp !=null)
		{
			Iterator<Expression> it = frmBoucle.this.exp.iterator();
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
				beginForElt.appendChild(expElt);
			}
		}
		
		
		
		
		
		//********************************************************************************
		
		forInst.setNum(numLine+1);
		forInst.setExpressions(this.exp);
		forInst.setNoeud(forElt);
		ForInstruction for_Instruction = new ForInstruction(forInst);
		for_Instruction.setValInit(valInitiale);
		for_Instruction.setPas(pas);
		//this.copiePrincipal.curAlg.getAlgoLines().add(numLine,for_Instruction);
		if(this.typeOuv == 1)
		{
			int pos1 = numLine-(this.copiePrincipal.curAlg.getAlgoVars().size()+2);
			this.copiePrincipal.curAlg.getAlgoLines().add(pos1,for_Instruction);
			//111*************************************
			numLine++;
			beginIfInst.setNum(numLine+1);
			beginIfInst.setNoeud(beginForElt);
			//this.copiePrincipal.curAlg.getAlgoLines().add(numLine,beginIfInst);
			int pos = numLine-(this.copiePrincipal.curAlg.getAlgoVars().size()+2);
			this.copiePrincipal.curAlg.getAlgoLines().add(pos,beginIfInst);
			//***************************
			numLine++;
			this.copiePrincipal.curLine  = numLine;
			endIfInst.setNum(numLine+1);
			endIfInst.setNoeud(endForElt);
			//this.copiePrincipal.curAlg.getAlgoLines().add(numLine,endIfInst);
			int pos2 = numLine-(this.copiePrincipal.curAlg.getAlgoVars().size()+2);
			this.copiePrincipal.curAlg.getAlgoLines().add(pos2,endIfInst);
		}
		else
		{
			int pos = this.posInst;
			this.copiePrincipal.curAlg.getAlgoLines().set(pos,for_Instruction);
		}
		//*******************************
		//this.copiePrincipal.curLine = numLine-1;

	}
	public void validerWhile()
	{
		String cond = this.chpCond.getText();
		int numLine = this.copiePrincipal.curLine;
		Element whileElt = this.copiePrincipal.currentDoc.createElement("While");
		whileElt.setAttribute("cond", cond);
		Instruction whileInst = new Instruction();
		Instruction beginWhileInst = new Instruction();
		Instruction endWhileInst = new Instruction();
		Element beginWhileElt = this.copiePrincipal.currentDoc.createElement("BeginWhile");
		Element endWhileElt = this.copiePrincipal.currentDoc.createElement("EndWhile");
		if(frmBoucle.this.exp !=null)
		{
			Iterator<Expression> it = frmBoucle.this.exp.iterator();
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
				beginWhileElt.appendChild(expElt);
			}
		}
	
	
	
	
	//********************************************************************************
	
	whileInst.setNum(numLine+1);
	whileInst.setExpressions(this.exp);
	whileInst.setNoeud(whileElt);
	//this.copiePrincipal.curAlg.getAlgoLines().add(numLine,whileInst);
	if(this.typeOuv == 1)
	{
		int pos0 = numLine-(this.copiePrincipal.curAlg.getAlgoVars().size()+2);
		this.copiePrincipal.curAlg.getAlgoLines().add(pos0,whileInst);
		//111*************************************
		numLine++;
		beginWhileInst.setNum(numLine+1);
		beginWhileInst.setNoeud(beginWhileElt);
		//this.copiePrincipal.curAlg.getAlgoLines().add(numLine,beginWhileInst);
		int pos1 = numLine-(this.copiePrincipal.curAlg.getAlgoVars().size()+2);
		this.copiePrincipal.curAlg.getAlgoLines().add(pos1,beginWhileInst);
		//***************************
		numLine++;
		this.copiePrincipal.curLine  = numLine;
		endWhileInst.setNum(numLine+1);
		endWhileInst.setNoeud(endWhileElt);
		//this.copiePrincipal.curAlg.getAlgoLines().add(numLine,endWhileInst);
		int pos = numLine-(this.copiePrincipal.curAlg.getAlgoVars().size()+2);
		this.copiePrincipal.curAlg.getAlgoLines().add(pos,endWhileInst);
	}
	else
	{
		int pos = this.posInst;
		this.copiePrincipal.curAlg.getAlgoLines().set(pos,whileInst);
	}
	//*******************************
	//this.copiePrincipal.curLine = numLine-1;

}
	public frmExp condExp;
	public JTextField chpCond;
	public JComboBox chpType;
	public JTextField chpDepart;
	public JFormattedTextField chpPas;
	public JComboBox chpNameVar;
	public int typeOuv = 1 ; // pour le type d'ouverture de la fenetre 
	public Instruction instACharger = null;
	public int posInst;
}


