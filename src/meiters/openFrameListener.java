package meiters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import recognizer.Instruction;

import editor.AlgoEditor;
import editor.EmptyFrame;
import editor.AlgoEditor.instTypes_en;

import GUI.NewAlgorithm;
import GUI.condVersOFrm;
import GUI.frmAffectation;
import GUI.frmBoucle;
import GUI.frmCondition;
import GUI.frmContrainte;
import GUI.frmDeclaration;
import GUI.frmRead;
import GUI.frmTache;
import GUI.frmWrite;
// permet de faire le traitement des boutons ouvrir
public class openFrameListener implements ActionListener{
	public openFrameListener(String typeFrame, AlgoEditor pMotherFrm)
	{	
		this.copiePrincipal = pMotherFrm;
		this.typeFrame = typeFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.copiePrincipal.curAlg!=null)
		{
			//switch( frameType.valueOf(this.typeFrame))
    		JButton btnSrc = (JButton) e.getSource();
			String opType = btnSrc.getName();
    	switch( frameType.valueOf(opType))
			{
    			case Declaration : 
    								
    								this.copiePrincipal.decFrm.initialize();
    								this.copiePrincipal.decFrm.setVisible(true);
    								this.copiePrincipal.mainFrame.setEnabled(false);
    							
    								break;
    			case Affectation : 
    								//this.copiePrincipal.affecttionFrm.chargeVars();
    								this.copiePrincipal.affecttionFrm.exp = null;	
    								this.copiePrincipal.affecttionFrm.initialize();
    								this.copiePrincipal.affecttionFrm.setVisible(true);
									this.copiePrincipal.mainFrame.setEnabled(false);
									break;
    			case Lire : 
    								this.copiePrincipal.readFrm.initialize();
    								this.copiePrincipal.readFrm.chargeVars();		
				    				this.copiePrincipal.readFrm.chargeVarsRange();	
				    				this.copiePrincipal.readFrm.setVisible(true);
    								this.copiePrincipal.mainFrame.setEnabled(false);
    							
    								break;
    			case Ecrire : 
    								this.copiePrincipal.writeFrm.initialize();
    								this.copiePrincipal.writeFrm.chargeVars();	
    								this.copiePrincipal.writeFrm.chargeVarsRange();	
    								this.copiePrincipal.writeFrm.setVisible(true);
									this.copiePrincipal.mainFrame.setEnabled(false);
									break;
    			case Condition : 
    								this.copiePrincipal.condFrm.exp = null;
    								this.copiePrincipal.condFrm.initialize();
    								this.copiePrincipal.condFrm.setVisible(true);
									this.copiePrincipal.mainFrame.setEnabled(false);
									break;
    			case Boucle : 
    								this.copiePrincipal.boucleFrm.exp = null;
    								this.copiePrincipal.boucleFrm.initialize();
    								this.copiePrincipal.boucleFrm.chargeVars();	
    								this.copiePrincipal.boucleFrm.setVisible(true);
									this.copiePrincipal.mainFrame.setEnabled(false);
									break;
    			case Tache : 
    								this.copiePrincipal.tacheFrm = new frmTache(this.copiePrincipal);
    								this.copiePrincipal.tacheFrm.initialize();
				    				
    								this.copiePrincipal.tacheFrm.setVisible(true);
									this.copiePrincipal.mainFrame.setEnabled(false);
    								break;
    			case Contrainte : 
    								this.copiePrincipal.contrainteFrm = new frmContrainte(this.copiePrincipal);
    								this.copiePrincipal.contrainteFrm.initialize();
    								this.copiePrincipal.contrainteFrm.setVisible(true);
									this.copiePrincipal.mainFrame.setEnabled(false);
									break;
    			case CondVersO : 
    								this.copiePrincipal.frmCondVersO = new condVersOFrm(this.copiePrincipal);
    								Instruction inst = this.copiePrincipal.curAlg.getAlgoLines().get(this.copiePrincipal.curLine-(this.copiePrincipal.curAlg.getAlgoVars().size()+3));
    								this.copiePrincipal.frmCondVersO.instACharger = inst;
    								this.copiePrincipal.frmCondVersO.initialize();
    								this.copiePrincipal.frmCondVersO.setVisible(true);
    								this.copiePrincipal.mainFrame.setEnabled(false);
    								break;

			}	
		}
	}
	String typeFrame;
	AlgoEditor copiePrincipal;
	public enum frameType { 	//les mots cles
		Declaration, Affectation,Lire, Ecrire, Condition,Boucle, Tache, Contrainte, CondVersO;
	}
}

