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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout.Constraints;

import recognizer.Constraint;
import recognizer.Expression;
import recognizer.Variable;

import meiters.dauther;
import meiters.exitListener;

import editor.AlgoEditor;
import editor.EmptyFrame;
import editor.GBC;

public class frmExp extends EmptyFrame{
	
	public frmExp(EmptyFrame mother)
	{
		this.mother = mother;
		this.copiePrincipal = mother.copiePrincipal;
		setTitle("Editer une expression");
		setSize(650, 400);
		setResizable(false);
		this.setName("expFrm");
		addWindowListener(new dauther(this.mother,this));
		if(this.mother.exp != null)
			this.expLists = this.mother.exp;
		else 
			this.expLists = new ArrayList<Expression>();
		initialize();
		
		//setVisible(true);
	}
	public void initialize()
	{
		// Panneau
		JPanel mainPanel = new JPanel();
		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel southPanel = new JPanel();
		
		
		GridBagLayout mainLayout = new GridBagLayout();
		centerPanel.setLayout(mainLayout);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(northPanel,BorderLayout.NORTH);
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		this.setContentPane(mainPanel);
		// Composants graphiques
		// Lib Expression Finale
		JLabel	libExpFinal = new JLabel("Expression ");
		
		// Lib Exp courante
		JLabel	libCurExp = new JLabel("Exp courante ");
		//Chp Expression Finale
		
		
		chpExpFinal = new JTextField(40);
		chpExpFinal.setEditable(false);
		// Exp cour
		chpExpCour = new JComboBox();
		
		//chpExpCour.addItem("Expression Finale");
		
		
		
		// Lib Left Operande 
		libLeftOp = new JLabel("Op Gauche ");
		// Lib Operateur 
		chpLeftOpVar = new JComboBox();
		chpLeftOpVar.setEnabled(false);
		
		libOp = new JLabel("Operateur ");
		// Lib right Operande 
		chpOp = new JComboBox(new String[]{"=","!=","==",">","<",">=","<=","+","-","*","/","%","AND","OR","XOR"});
		//chpOp.setEnabled(false);
		
		libRightOp = new JLabel("Op Droite ");
		chpRightOpVar = new JComboBox();
		chpRightOpVar.setEnabled(false);
		// Lib Type Var gauche
		libLeftVar = new JLabel("Var ");
		// Lib Type Val gauche
		libLeftVal = new JLabel("Val ");
		chpLeftOpVal = new JTextField(10);
		chpLeftOpVal.setEditable(false);
		// Lib Type Exp gauche
		libLeftExp = new JLabel("Expression ");
		chpLeftOpExp = new JComboBox();
		chpLeftOpExp.setEnabled(false);
		// Lib Type Var droite
		libRightVar = new JLabel("Var ");
		// Lib Type Val droite
		libRightVal = new JLabel("Val ");
		chpRightOpVal = new JTextField(10);
		chpRightOpVal.setEditable(false);
		// Lib Type Exp droite
		libRightExp = new JLabel("Expression ");
		chpRightOpExp = new JComboBox();
		chpRightOpExp.setEnabled(false);
		// Chp Nom de l variable
		chpNameVar = new JComboBox();
		// Chp Type de l variable
		chpRangVar = new JComboBox();
		chpRangVar.setEnabled(false);
		//CheckBox
		chkLeftOpVar = new JCheckBox();
		chkLeftOpVal = new JCheckBox();
		chkLeftOpVal.setVisible(false);
		chkLeftOpExp = new JCheckBox();
		chkRightOpVar = new JCheckBox();
		chkRightOpVal = new JCheckBox();
		chkRightOpVal.setVisible(false);
		chkRightOpExp = new JCheckBox();
		//ch
		//*************************
		addExp = new JButton("Ajouter");
		delExp = new JButton("Supp");
		//*************************
		typeOpLeft = new JComboBox();
		typeOpLeft.addItem("Variable");
		typeOpLeft.addItem("Valeur");
		typeOpLeft.addItem("Expression");
		typeOpRight  = new JComboBox();
		typeOpRight.addItem("Variable");
		typeOpRight.addItem("Valeur");
		typeOpRight.addItem("Expression");
		visOpLeft = new JTextField();
		visOpLeft.setEditable(false);
		visOpLeft.setVisible(false);
		visOpRight = new JTextField();
		visOpRight.setEditable(false);
		visOpRight.setVisible(false);
		//*************
		// Boutton de validation
		btnValider = new JButton("Valider");
		// Boutton de validation
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new exitListener(this));
		btnAnnuler.addActionListener(new exitListener(this));
		// Boutton de validation d'une expression courante
		btnOkCourante = new JButton("Ok");
		btnOkCourante.setEnabled(false);
		// Boutton de validation d'une expression courante
		btnAnnulerCourante = new JButton("Anuuler");
		btnAnnulerCourante.setEnabled(false);
		
		// Boutton de validation d'une expression courante
		btnSuppCourante = new JButton("Supp");
		btnSuppCourante.setEnabled(false);
		// Boutton de validation d'une expression courante
		btnViderExpFinal = new JButton("Vider");
		visExpCour = new JTextField();
		visExpCour.setEditable(false);
		//**************
		chkLeftOpVar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(frmExp.this.chkLeftOpVar.isSelected())
				{
					frmExp.this.chpLeftOpVar.setEnabled(true);
					frmExp.this.chpLeftOpVal.setEditable(false);
					frmExp.this.chpLeftOpExp.setEnabled(false);
					frmExp.this.chkLeftOpExp.setSelected(false);
					frmExp.this.chkLeftOpVal.setSelected(false);
					//frmExp.this.chkLeftOpVar.setSelected(false);
				}
				else
				{
					frmExp.this.chkLeftOpExp.setSelected(false);
					frmExp.this.chkLeftOpVal.setSelected(false);
					//frmExp.this.chkLeftOpVar.setSelected(false);
					frmExp.this.chpLeftOpVar.setEnabled(false);
				}
				
			}
		});
		
		chkLeftOpExp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (frmExp.this.chkLeftOpExp.isSelected())
				{
					frmExp.this.chpLeftOpVal.setEditable(false);
					frmExp.this.chpLeftOpVal.setText(Integer.toString(0));
					frmExp.this.chpLeftOpExp.setEnabled(true);
				}
				else
				{
					frmExp.this.chpLeftOpVal.setEditable(true);
					frmExp.this.chpLeftOpExp.setEnabled(false);
				}
			}
		});
		chkRightOpExp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (frmExp.this.chkRightOpExp.isSelected())
				{
					frmExp.this.chpRightOpVal.setEditable(false);
					frmExp.this.chpRightOpVal.setText(Integer.toString(0));
					frmExp.this.chpRightOpExp.setEnabled(true);
				}
				else
				{
					frmExp.this.chpRightOpVal.setEditable(true);
					frmExp.this.chpRightOpExp.setEnabled(false);
				}
			}
		});
		chpLeftOpVar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String varName = (String) frmExp.this.chpLeftOpVar.getSelectedItem();
				Variable var = frmExp.this.copiePrincipal.analyser.nameToVar(varName,frmExp.this.copiePrincipal.curAlg.getAlgoVars());
				if(var !=null)
				{
					if(Integer.parseInt(var.getType())== 3 ||Integer.parseInt(var.getType())== 4)
						{
							frmExp.this.chpLeftOpVal.setEditable(true);
							frmExp.this.chkLeftOpExp.setEnabled(true);
							//frmRead.this.chpRangVar.setEnabled(true);
						}
						else
							{
								frmExp.this.chpLeftOpVal.setEditable(false);
								frmExp.this.chkLeftOpExp.setSelected(false);
								frmExp.this.chkLeftOpExp.setEnabled(false);
								frmExp.this.chkLeftOpExp.setEnabled(false);
							}
				}
			}
		});
		chpRightOpVar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String varName = (String) frmExp.this.chpRightOpVar.getSelectedItem();
				Variable var = frmExp.this.copiePrincipal.analyser.nameToVar(varName,frmExp.this.copiePrincipal.curAlg.getAlgoVars());
				if(var !=null)
				{
					if(Integer.parseInt(var.getType())== 3 ||Integer.parseInt(var.getType())== 4)
						{
							frmExp.this.chpRightOpVal.setEditable(true);
							frmExp.this.chkRightOpExp.setEnabled(true);
							//frmRead.this.chpRangVar.setEnabled(true);
						}
						else
							{
								frmExp.this.chpRightOpVal.setEditable(false);
								frmExp.this.chkRightOpExp.setSelected(false);
								frmExp.this.chkRightOpExp.setEnabled(false);
								frmExp.this.chkRightOpExp.setEnabled(false);
							}
				}
			}
		});
		typeOpLeft.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int selectedItem = frmExp.this.typeOpLeft.getSelectedIndex();
				switch(selectedItem)
				{
					case 0 :
							
							frmExp.this.chargeVars(frmExp.this.chpLeftOpVar);
							frmExp.this.chargeVarsRange((frmExp.this.chpLeftOpExp));
							frmExp.this.chpLeftOpVar.setEnabled(true);
							//frmExp.this.chk
							frmExp.this.visOpLeft.setVisible(false);
							frmExp.this.libLeftVal.setText("Rang");
							frmExp.this.chpLeftOpExp.setEnabled(false);
							frmExp.this.chpLeftOpVal.setEditable(false);
							frmExp.this.libLeftExp.setText("Var/Expession");
							break;
					case 1 :
							frmExp.this.libLeftVal.setText("Val");
							frmExp.this.chpLeftOpVar.setEnabled(false);
							frmExp.this.chpLeftOpExp.setEnabled(false);
							frmExp.this.chpLeftOpVal.setEditable(true);
							frmExp.this.chkLeftOpExp.setSelected(false);
							frmExp.this.chkLeftOpExp.setEnabled(false);
							frmExp.this.libLeftExp.setText("Expession");
							break;
					case 2 :
							frmExp.this.libLeftVal.setText("Val");
							frmExp.this.chpLeftOpVar.setEnabled(false);
							frmExp.this.visOpLeft.setVisible(true);
							frmExp.this.chargeExps((frmExp.this.chpLeftOpExp));
							frmExp.this.chpLeftOpExp.setEnabled(true);
							frmExp.this.chpLeftOpVal.setEditable(false);
							frmExp.this.libLeftExp.setText("Expession");
							frmExp.this.chkLeftOpExp.setSelected(false);
							frmExp.this.chkLeftOpExp.setEnabled(false);
							break;
				}
				
			}
		});
		typeOpRight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int selectedItem = frmExp.this.typeOpRight.getSelectedIndex();
				switch(selectedItem)
				{
					case 0 :
							
							frmExp.this.chargeVars(frmExp.this.chpRightOpVar);
							frmExp.this.chargeVarsRange((frmExp.this.chpRightOpExp));
							frmExp.this.chpRightOpVar.setEnabled(true);
							//frmExp.this.chk
							frmExp.this.visOpRight.setVisible(false);
							frmExp.this.libRightVal.setText("Rang");
							frmExp.this.chpRightOpExp.setEnabled(false);
							frmExp.this.chpRightOpVal.setEditable(false);
							frmExp.this.libRightExp.setText("Var/Expession");
							break;
					case 1 :
							frmExp.this.libRightVal.setText("Val");
							frmExp.this.chpRightOpVar.setEnabled(false);
							frmExp.this.chpRightOpExp.setEnabled(false);
							frmExp.this.chpRightOpVal.setEditable(true);
							frmExp.this.chkRightOpExp.setSelected(false);
							frmExp.this.chkRightOpExp.setEnabled(false);
							frmExp.this.libRightExp.setText("Expession");
							break;
					case 2 :
							frmExp.this.libRightVal.setText("Val");
							frmExp.this.chpRightOpVar.setEnabled(false);
							frmExp.this.visOpRight.setVisible(true);
							frmExp.this.chargeExps((frmExp.this.chpRightOpExp));
							frmExp.this.chpRightOpExp.setEnabled(true);
							frmExp.this.chpRightOpVal.setEditable(false);
							frmExp.this.libRightExp.setText("Expession");
							frmExp.this.chkRightOpExp.setSelected(false);
							frmExp.this.chkRightOpExp.setEnabled(false);
							break;
				}
				
			}
		});
		this.chpExpCour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stubint 
				if (frmExp.this.chpExpCour.getItemCount()>0)
				{
					String expName = (String) frmExp.this.chpExpCour.getSelectedItem();
					int indexExp = frmExp.this.chpExpCour.getSelectedIndex();
					if(frmExp.this.Mode != 1)
					{
						Expression exp = frmExp.this.expLists.get(indexExp);
						frmExp.this.chargerExpression(exp);
					}
				}
			}
		});
		//this.chpExpCour.setEnabled(false);
		this.typeOpLeft.setEnabled(false);
		this.typeOpRight.setEnabled(false);
		this.chpOp.setEnabled(false);
		addExp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String expName = "exp"+(frmExp.this.chpExpCour.getItemCount()+1);
				Expression exp = new Expression();
				exp.setName(expName);
				frmExp.this.typeOpLeft.setEnabled(true);
				frmExp.this.typeOpRight.setEnabled(true);
				frmExp.this.chpOp.setEnabled(true);
				if(frmExp.this.chpExpCour.getItemCount()>0)
					frmExp.this.lastExp = (String) frmExp.this.chpExpCour.getSelectedItem();
				frmExp.this.Mode = 1;
				frmExp.this.chpExpCour.addItem(expName); 
				frmExp.this.chpExpCour.setSelectedItem(expName);
				frmExp.this.expLists.add(exp);
				//*********************************
				frmExp.this.chargeVars(frmExp.this.chpLeftOpVar);
				frmExp.this.chargeVarsRange((frmExp.this.chpLeftOpExp));
				frmExp.this.chpLeftOpVar.setEnabled(true);
				//frmExp.this.chk
				frmExp.this.visOpLeft.setVisible(false);
				frmExp.this.libLeftVal.setText("Rang");
				frmExp.this.chpLeftOpExp.setEnabled(false);
				frmExp.this.chpLeftOpVal.setEditable(false);
				frmExp.this.libLeftExp.setText("Var/Expession");
				//****************************************************************************
				frmExp.this.chargeVars(frmExp.this.chpRightOpVar);
				frmExp.this.chargeVarsRange((frmExp.this.chpRightOpExp));
				frmExp.this.chpRightOpVar.setEnabled(true);
				//frmExp.this.chk
				frmExp.this.visOpRight.setVisible(false);
				frmExp.this.libRightVal.setText("Rang");
				frmExp.this.chpRightOpExp.setEnabled(false);
				frmExp.this.chpRightOpVal.setEditable(false);
				frmExp.this.libRightExp.setText("Var/Expession");
				//********************************
				frmExp.this.chpLeftOpVal.setText("");
				frmExp.this.chpRightOpVal.setText("");
				frmExp.this.chpOp.setSelectedIndex(0);
				frmExp.this.chpExpCour.setEnabled(false);
				//******************************************************************************************************
				frmExp.this.typeOpLeft.setEnabled(true);
				frmExp.this.typeOpRight.setEnabled(true);
				frmExp.this.chpOp.setEnabled(true);
				frmExp.this.typeOpLeft.setSelectedIndex(0);
				frmExp.this.typeOpRight.setSelectedIndex(0);
				frmExp.this.chpOp.setSelectedIndex(0);
				frmExp.this.visExpCour.setText("");
				//*************************
				frmExp.this.btnOkCourante.setEnabled(true);
				frmExp.this.btnAnnulerCourante.setEnabled(true);
				//****************************************
				frmExp.this.addExp.setEnabled(false);
				frmExp.this.delExp.setEnabled(false);
			}
		});
		btnAnnulerCourante.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frmExp.this.expLists.remove(frmExp.this.chpExpCour.getSelectedIndex());
				frmExp.this.chpExpCour.removeItemAt(frmExp.this.chpExpCour.getSelectedIndex());
				
				if (frmExp.this.Mode==1 && frmExp.this.lastExp!=null)
				{
					frmExp.this.chpExpCour.setSelectedItem(frmExp.this.lastExp);
				}
				frmExp.this.Mode = 0;
				//*********************************
				frmExp.this.typeOpLeft.setEnabled(false);
				frmExp.this.typeOpRight.setEnabled(false);
				frmExp.this.chpOp.setEnabled(false);
				//******************************
				frmExp.this.addExp.setEnabled(true);
				frmExp.this.delExp.setEnabled(true);
				//******************************
				frmExp.this.btnOkCourante.setEnabled(false);
				frmExp.this.btnAnnulerCourante.setEnabled(false);
			}
		});
		this.btnOkCourante.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//String name = (String) frmExp.this.chpExpCour.getSelectedItem();
				String left = "";
				String right="";
				String op = (String) frmExp.this.chpOp.getSelectedItem();
				String rangLeft = "";
				String rangRight = "";
				switch(frmExp.this.typeOpLeft.getSelectedIndex())
				{
				case 0 : 
						
						left =  (String) frmExp.this.chpLeftOpVar.getSelectedItem();
						if(frmExp.this.chkLeftOpExp.isSelected())
							rangLeft =  (String) frmExp.this.chpLeftOpExp.getSelectedItem();
						else
							rangLeft =  frmExp.this.chpLeftOpVal.getText();
						break;
				case 1 : 
						left =  frmExp.this.chpLeftOpVal.getText();
						
						break;
				case 2 : 
						left =  (String) frmExp.this.chpLeftOpExp.getSelectedItem();
						break;
						
				}
				switch(frmExp.this.typeOpRight.getSelectedIndex())
				{
				case 0 : 
						
						right =  (String) frmExp.this.chpRightOpVar.getSelectedItem();
						if(frmExp.this.chkRightOpExp.isSelected())
							rangRight =  (String) frmExp.this.chpRightOpExp.getSelectedItem();
						else
							rangRight =  frmExp.this.chpRightOpVal.getText();
						break;
				case 1 : 
						right =  frmExp.this.chpRightOpVal.getText();
						
						break;
				case 2 : 
						right =  (String) frmExp.this.chpRightOpExp.getSelectedItem();
						break;
						
				}
				Expression exp = frmExp.this.expLists.get(frmExp.this.chpExpCour.getSelectedIndex());
				exp.setLeft(left);
				exp.setRight(right);
				exp.setRangLeft(rangLeft);
				exp.setRangRight(rangRight);
				exp.setOp(op);
				frmExp.this.chpLeftOpVal.setText("");
				frmExp.this.chpRightOpVal.setText("");
				frmExp.this.chpExpCour.setEnabled(true);
				//********************************************************
				frmExp.this.chargerExpression(exp);
				frmExp.this.Mode = 0;
				//*******************************************************
				frmExp.this.typeOpLeft.setEnabled(false);
				frmExp.this.typeOpRight.setEnabled(false);
				frmExp.this.chpOp.setEnabled(false);
				//****************************************************
				frmExp.this.addExp.setEnabled(true);
				frmExp.this.delExp.setEnabled(true);
				frmExp.this.btnOkCourante.setEnabled(false);
				frmExp.this.btnAnnulerCourante.setEnabled(false);
			}
		});
		delExp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String expName = (String ) frmExp.this.chpExpCour.getSelectedItem();
				int expRange = frmExp.this.chpExpCour.getSelectedIndex();
				//if(expRange!=0)
				frmExp.this.chpExpCour.removeItemAt(expRange);
				frmExp.this.expLists.remove(expRange);
			}
		});
		this.chargeInitExps(chpExpCour);
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frmExp.this.valider();
				
			}
		});
		
		northPanel.add(libExpFinal);
		northPanel.add(chpExpFinal);
		northPanel.add(btnViderExpFinal);
		southPanel.add(btnValider);
		southPanel.add(btnAnnuler);
		northPanel.setVisible(false);
		//**************
		centerPanel.add(libCurExp,new GBC(0,0,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(chpExpCour,new GBC(1,0,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		centerPanel.add(this.visExpCour,new GBC(2,0,8,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		//centerPanel.add(addExp,new GBC(5,0,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		//centerPanel.add(delExp,new GBC(6,0,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		centerPanel.add(typeOpLeft,new GBC(1,1,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(visOpLeft,new GBC(2,1,5,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		centerPanel.add(libLeftOp,new GBC(0,1,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(chpLeftOpVar,new GBC(1,2,4,1).setInsets(5).setFill(GridBagConstraints.HORIZONTAL));
		//centerPanel.add(chkLeftOpVar,new GBC(1,3,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(libLeftVar,new GBC(2,3,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(chpLeftOpVal,new GBC(5,2,2,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(chkLeftOpVal,new GBC(5,3,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(libLeftVal,new GBC(6,3,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(chpLeftOpExp,new GBC(7,2,2,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(chkLeftOpExp,new GBC(7,3,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(libLeftExp,new GBC(8,3,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		centerPanel.add(libOp,new GBC(0,4,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(chpOp,new GBC(1,4,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		centerPanel.add(typeOpRight,new GBC(1,5,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(visOpRight,new GBC(2,5,5,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		
		centerPanel.add(libRightOp,new GBC(0,5,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(chpRightOpVar,new GBC(1,6,4,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		//centerPanel.add(chkRightOpVar,new GBC(1,7,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(libRightVar,new GBC(2,7,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(chpRightOpVal,new GBC(5,6,2,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(chkRightOpVal,new GBC(5,7,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		centerPanel.add(addExp,new GBC(1,8,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(delExp,new GBC(2,8,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		centerPanel.add(libRightVal,new GBC(6,7,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(chpRightOpExp,new GBC(7,6,3,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(chkRightOpExp,new GBC(7,7,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(libRightExp,new GBC(8,7,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(btnOkCourante,new GBC(7,8,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(btnAnnulerCourante,new GBC(8,8,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		centerPanel.add(btnSuppCourante,new GBC(4,8,1,1).setInsets(5).setFill(GridBagConstraints.BOTH));
		
		/*

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
		*/
		
		}
	public void valider()
	{
		if (frmExp.this.chpExpCour.getItemCount()>0)
		{
			int indexFinExp = frmExp.this.chpExpCour.getSelectedIndex();
			Expression finalExp = frmExp.this.expLists.get(indexFinExp);
			this.expLists.remove(indexFinExp);
			this.expLists.add(0, finalExp);
			this.mother.exp = frmExp.this.expLists; 
			String textExp = frmExp.this.copiePrincipal.analyser.getTextExp(frmExp.this.mother.exp);
			this.mother.visExp.setText(textExp);
			if (this.mother.getName().equals("condVersOFrm"))
			{
				condVersOFrm copieMoth = (condVersOFrm)  this.mother;
				int numLigCour = this.copiePrincipal.curLine;
				Constraint ctr = new Constraint();
				// il faut verifer que cette contrainte n'existe pas
				ctr.setNumLig(Integer.toString(numLigCour));
				ctr.setType("8");
				ctr.setExps(this.expLists);
				copieMoth.Conds_Model.addCtr(ctr);
				this.copiePrincipal.curAlg.getAlgoConsts().add(ctr);
				int pos = copieMoth.Conds_table.getRowCount()-1;
				copieMoth.Conds_table.setRowSelectionInterval(pos, pos);
				
				this.mother.exp = null;
				
			}
		}
		else
			if(frmExp.this.mother.exp == null)
				frmExp.this.mother.visExp.setText("");
		frmExp.this.dispose();
		
	}
	public  void chargeVars(JComboBox pChp)
	{
		pChp.removeAllItems();
		//this.chpRangVar.removeAllItems();
		Iterator<Variable> it = this.copiePrincipal.curAlg.getAlgoVars().iterator();
		//System.out.println("la taille:"+this.copiePrincipal.curAlg.getAlgoVars().size());
		while(it.hasNext())
			{
				Variable item = it.next(); 
				//System.out.println(item.getName());
				pChp.addItem(item.getName());
				//this.chpRangVar.addItem(item.getName());
			}	
	}
	public  void chargeVarsRange(JComboBox pChp)
	{
		//this.chpNameVar.removeAllItems();
		pChp.removeAllItems();
		Iterator<Variable> it = this.copiePrincipal.curAlg.getAlgoVars().iterator();
		//System.out.println("la taille:"+this.copiePrincipal.curAlg.getAlgoVars().size());
		while(it.hasNext())
			{
				Variable item = it.next(); 
				//System.out.println(item.getName());
				
				if(Integer.parseInt(item.getType())!= 3 && Integer.parseInt(item.getType())!= 4)
				{
					//this.chpNameVar.addItem(item.getName());
					pChp.addItem(item.getName());
				}
			}	
		Iterator<Expression> itExp = this.expLists.iterator();
		while(itExp.hasNext())
		{
			Expression itemExp = itExp.next(); 
			if(!itemExp.getName().equals(this.chpExpCour.getSelectedItem()))
				pChp.addItem(itemExp.getName());
		}
	}
	public  void chargeExps(JComboBox pChp)
	// permet de charger toutes les expressions dans un champ combo 
	{
		
		pChp.removeAllItems();
		Iterator<Expression> itExp = this.expLists.iterator();
		while(itExp.hasNext())
		{
			Expression itemExp = itExp.next(); 
			if(!itemExp.getName().equals(this.chpExpCour.getSelectedItem()))
				pChp.addItem(itemExp.getName());
		}
	}
	public  void chargeInitExps(JComboBox pChp)
	// permet de charger toutes les expressions dans un champ combo 
	{
		
		if(this.expLists.size()!=0)
		{
			pChp.removeAllItems();
			Iterator<Expression> itExp = this.expLists.iterator();
			while(itExp.hasNext())
			{
				Expression itemExp = itExp.next(); 
				pChp.addItem(itemExp.getName());
			}
			Expression expFinal = this.expLists.get(0);
			this.chargeVars(this.chpLeftOpVar);
			this.chargeVars(this.chpRightOpVar);
			this.chargeVarsRange(this.chpLeftOpExp);
			this.chargeVarsRange(this.chpRightOpExp);
			
			pChp.setSelectedIndex(0);
			this.chargerExpression(expFinal);
		}
	}
	public void chargerExpression(Expression pExp)
	// permet de charger une expression
	{
		String left = pExp.getLeft();
		String op = pExp.getOp();
		frmExp.this.chpOp.setSelectedItem(op);
		String right = pExp.getRight();
		String rleft = pExp.getRangLeft();
		String rRight = pExp.getRangRight();
		Variable tmpVar  = new Variable();
		// verfication coté guauche
		
		if (!frmExp.this.copiePrincipal.analyser.isItVar(left, frmExp.this.copiePrincipal.curAlg.getAlgoVars()))
		// Ce n'est pas une variable
		{
			frmExp.this.libLeftVal.setText("Val");
			frmExp.this.libLeftExp.setText("Expression");
			// est elle une expression
			if (isItExp(left))
			{
				frmExp.this.chkLeftOpExp.setSelected(false);
				frmExp.this.chkLeftOpExp.setEnabled(false);
				//*********************************************************
				frmExp.this.typeOpLeft.setSelectedIndex(2);
				//******************************************************
				frmExp.this.chpLeftOpVar.removeAllItems();
				frmExp.this.chpLeftOpVar.setEnabled(false);
				//************************************************************
				frmExp.this.chpLeftOpVal.setText("");
				frmExp.this.chpLeftOpVal.setEditable(false);
				
				frmExp.this.chpLeftOpExp.setSelectedItem(left);
				frmExp.this.chpLeftOpExp.setEnabled(false);
			}
			// dc forcement c'est une valeur
			else 
			{
				frmExp.this.chkLeftOpExp.setSelected(false);
				frmExp.this.chkLeftOpExp.setEnabled(false);
				//**************************************************************
				frmExp.this.typeOpLeft.setSelectedIndex(1);
				//frmExp.this.chpLeftOpVal.setEditable(true);
				//************************************************************************
				frmExp.this.chpLeftOpVar.removeAllItems();
				frmExp.this.chpLeftOpVar.setEnabled(false);
				//*************************************************
				frmExp.this.chpLeftOpExp.removeAllItems();
				frmExp.this.chpLeftOpExp.setEnabled(false);
				//*********************************************************************************
				frmExp.this.chpLeftOpVal.setText(left);
				frmExp.this.chpLeftOpVal.setEditable(false);
			}
		}
		// c'est une variable
		else
		{
			frmExp.this.typeOpLeft.setSelectedIndex(0);
			frmExp.this.chpLeftOpVar.setSelectedItem(left);
			
			//******************************************************************
			tmpVar = frmExp.this.copiePrincipal.analyser.nameToVar(left,frmExp.this.copiePrincipal.curAlg.getAlgoVars());
			if(tmpVar.getType().equals("3") ||tmpVar.getType().equals("4"))
			// il s'agit d'un type tableau
			{
				
				frmExp.this.libLeftVal.setText("Rang");
				frmExp.this.libLeftExp.setText("Val/Expression");
				if(frmExp.this.copiePrincipal.analyser.isItVar(rleft, frmExp.this.copiePrincipal.curAlg.getAlgoVars())||isItExp(rleft))
				// cas var ou expression
				{
					
					frmExp.this.chkLeftOpExp.setSelected(true);
					frmExp.this.chkLeftOpExp.setEnabled(false);
					//************************************************************************************
					//System.out.println("p 2 3"+frmExp.this.chpRightOpVar.getSelectedItem());
					frmExp.this.chpLeftOpVal.setText("0");
					frmExp.this.chpLeftOpVal.setEditable(false);
					//************************************************************************************
					frmExp.this.chpLeftOpExp.setSelectedItem(rleft);
					frmExp.this.chpLeftOpExp.setEnabled(false);
					
					
					
				}
				// cas valeur
				else
				{
					frmExp.this.chkLeftOpExp.setEnabled(false);
					frmExp.this.chkLeftOpExp.setSelected(false);
					//*********************************************************
					frmExp.this.chpLeftOpVal.setText(rleft);
					frmExp.this.chpLeftOpVal.setEditable(false);
					//******************************************************
					frmExp.this.chpLeftOpExp.removeAllItems();
					frmExp.this.chpLeftOpExp.setEnabled(false);
				}
			}
			// il 'sagit d'un type normal
			else
			{
				frmExp.this.chkLeftOpExp.setEnabled(false);
				frmExp.this.chkLeftOpExp.setSelected(false);
				//****************************************************************
				frmExp.this.libLeftVal.setText("Rang");
				frmExp.this.libLeftExp.setText("Val/Expression");
				//************************************************************
				//System.out.println("p 2 2"+frmExp.this.chpLeftOpVar.getSelectedItem());
				
				//if (frmExp.this.chpLeftOpVar.getItemCount()>0)
				//	frmExp.this.typeOpLeft.setSelectedIndex(0);
				frmExp.this.chpLeftOpVal.setText("");
				frmExp.this.chpLeftOpVal.setEditable(false);
				//********************************************************************************
				frmExp.this.chpLeftOpExp.removeAllItems();
				frmExp.this.chpLeftOpExp.setEnabled(false);
				
			}
			frmExp.this.chpLeftOpVar.setEnabled(false);
			
		}
		
		
		// verification coté droit 
		if (!frmExp.this.copiePrincipal.analyser.isItVar(right, frmExp.this.copiePrincipal.curAlg.getAlgoVars()))
			// Ce n'est pas une variable
			{
				frmExp.this.libRightVal.setText("Val");
				frmExp.this.libRightExp.setText("Expression");
				// est elle une expression
				if (isItExp(right))
				{
					frmExp.this.chkRightOpExp.setSelected(false);
					frmExp.this.chkRightOpExp.setEnabled(false);
					//*********************************************************
					frmExp.this.typeOpRight.setSelectedIndex(2);
					//******************************************************
					frmExp.this.chpRightOpVar.removeAllItems();
					frmExp.this.chpRightOpVar.setEnabled(false);
					//************************************************************
					frmExp.this.chpRightOpVal.setText("");
					frmExp.this.chpRightOpVal.setEditable(false);
					
					frmExp.this.chpRightOpExp.setSelectedItem(right);
					frmExp.this.chpRightOpExp.setEnabled(false);
				}
				// dc forcement c'est une valeur
				else 
				{
					frmExp.this.chkRightOpExp.setSelected(false);
					frmExp.this.chkRightOpExp.setEnabled(false);
					//**************************************************************
					frmExp.this.typeOpRight.setSelectedIndex(1);
					//frmExp.this.chpRightOpVal.setEditable(true);
					//************************************************************************
					frmExp.this.chpRightOpVar.removeAllItems();
					frmExp.this.chpRightOpVar.setEnabled(false);
					//*************************************************
					frmExp.this.chpRightOpExp.removeAllItems();
					frmExp.this.chpRightOpExp.setEnabled(false);
					//*********************************************************************************
					frmExp.this.chpRightOpVal.setText(right);
					frmExp.this.chpRightOpVal.setEditable(false);
				}
			}
			// c'est une variable
			else
			{
				frmExp.this.typeOpRight.setSelectedIndex(0);
				frmExp.this.chpRightOpVar.setSelectedItem(right);
				
				//******************************************************************
				tmpVar = frmExp.this.copiePrincipal.analyser.nameToVar(right,frmExp.this.copiePrincipal.curAlg.getAlgoVars());
				if(tmpVar.getType().equals("3") ||tmpVar.getType().equals("4"))
				// il s'agit d'un type tableau
				{
					
					frmExp.this.libRightVal.setText("Rang");
					frmExp.this.libRightExp.setText("Val/Expression");
					if(frmExp.this.copiePrincipal.analyser.isItVar(rRight, frmExp.this.copiePrincipal.curAlg.getAlgoVars())||isItExp(rRight))
					// cas var ou expression
					{
						
						frmExp.this.chkRightOpExp.setSelected(true);
						frmExp.this.chkRightOpExp.setEnabled(false);
						//************************************************************************************
						//System.out.println("p 2 3"+frmExp.this.chpRightOpVar.getSelectedItem());
						frmExp.this.chpRightOpVal.setText("0");
						frmExp.this.chpRightOpVal.setEditable(false);
						//************************************************************************************
						frmExp.this.chpRightOpExp.setSelectedItem(rRight);
						frmExp.this.chpRightOpExp.setEnabled(false);
						
						
						
					}
					// cas valeur
					else
					{
						frmExp.this.chkRightOpExp.setEnabled(false);
						frmExp.this.chkRightOpExp.setSelected(false);
						//*********************************************************
						frmExp.this.chpRightOpVal.setText(rRight);
						frmExp.this.chpRightOpVal.setEditable(false);
						//******************************************************
						frmExp.this.chpRightOpExp.removeAllItems();
						frmExp.this.chpRightOpExp.setEnabled(false);
					}
				}
				// il 'sagit d'un type normal
				else
				{
					frmExp.this.chkRightOpExp.setEnabled(false);
					frmExp.this.chkRightOpExp.setSelected(false);
					//****************************************************************
					frmExp.this.libRightVal.setText("Rang");
					frmExp.this.libRightExp.setText("Val/Expression");
					//************************************************************
					//System.out.println("p 2 2"+frmExp.this.chpRightOpVar.getSelectedItem());
					
					//if (frmExp.this.chpRightOpVar.getItemCount()>0)
					//	frmExp.this.typeOpRight.setSelectedIndex(0);
					frmExp.this.chpRightOpVal.setText("");
					frmExp.this.chpRightOpVal.setEditable(false);
					//********************************************************************************
					frmExp.this.chpRightOpExp.removeAllItems();
					frmExp.this.chpRightOpExp.setEnabled(false);
					
				}
				frmExp.this.chpRightOpVar.setEnabled(false);
				
			}
		String textExp  = this.copiePrincipal.analyser.getTextExpression(pExp,this.expLists);
		this.visExpCour.setText(textExp);
	}
	public boolean isItExp(String pExp)
	{
		Iterator<Expression> itExp = this.expLists.iterator();
		while(itExp.hasNext())
		{
			Expression itemExp = itExp.next(); 
			if(itemExp.getName().equals(pExp))
				return true;
		}	
		return false;
	}
	
	public JTextField chpExpFinal;
	public JTextField visExpCour;
	public JComboBox chpExpCour;
	public JComboBox chpLeftOpVar;
	public JComboBox chpOp;
	public JComboBox chpRightOpVar;
	public JTextField chpLeftOpVal;
	public JComboBox chpLeftOpExp;
	public JTextField chpRightOpVal;
	public JComboBox chpRightOpExp;
	public JComboBox	chpNameVar;
	public JComboBox	chpRangVar;
	public JCheckBox chkLeftOpVar;
	public JCheckBox chkLeftOpVal;
	public JCheckBox chkLeftOpExp;
	public JCheckBox chkRightOpVar;
	public JCheckBox chkRightOpVal;
	public JCheckBox chkRightOpExp;
	public JButton addExp;
	public JButton delExp;
	//********************************
	public JLabel	libLeftOp;
	public JLabel	libOp ;
	public JLabel	libRightOp;
	public JLabel	libLeftVar;
	public JLabel	libLeftVal;
	public JLabel	libLeftExp;
	public  JLabel	libRightVar;
	public JLabel	libRightVal;
	public JLabel	libRightExp;
	

	//***************
	public ArrayList<Expression> expLists;
	public JComboBox typeOpLeft;
	public JComboBox typeOpRight;
	public JTextField visOpLeft;
	public JTextField visOpRight;
	//*****
	//public AlgoEditor copiePrincipale;
	public JButton btnOkCourante ;
	public JButton btnAnnulerCourante;
	public JButton btnSuppCourante;
	public JButton btnViderExpFinal;
	public JButton btnValider;
	public JButton btnAnnuler;
	//**********************
	public String lastExp;
	public int Mode; // ajout : 1, Mod : 2

}
