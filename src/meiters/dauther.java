package meiters;


import GUI.condVersOFrm;
import GUI.frmAffectation;
import GUI.frmBoucle;
import GUI.frmCondition;
import GUI.frmDeclaration;
import GUI.frmRead;
import GUI.frmWrite;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import editor.EmptyFrame;

public class dauther extends EmptyFrame implements WindowListener{
	public dauther(EmptyFrame pMother,EmptyFrame pFrm)
	{
		this.mother = pMother;
		this.copieFrm = pFrm;
		
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("fffffffffffffffffffff");
		//this.pack();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(this.mother);
		String fenName = this.copieFrm.getName();
		if(fenName.equals("decFrm"))
			{
				frmDeclaration copie = (frmDeclaration) this.copieFrm; 
				copie.typeOuv = 1;
				copie.varACharger = null;
			}
		else
			if(fenName.equals("readFrm"))
			{
				frmRead copie = (frmRead) this.copieFrm;
				copie.typeOuv = 1;
				copie.instACharger = null;
			}
			else 
				if(fenName.equals("writeFrm"))
				{
					frmWrite copie = (frmWrite) this.copieFrm;
					copie.typeOuv = 1;
					copie.instACharger = null;
				}
				else 
					if(fenName.equals("affecttionFrm"))
					{
						frmAffectation copie = (frmAffectation) this.copieFrm;
						copie.typeOuv = 1;
						copie.instACharger = null;
						copie.exp = null;
					}
					else 
						if(fenName.equals("condFrm"))
						{
							frmCondition copie = (frmCondition) this.copieFrm;
							copie.typeOuv = 1;
							copie.instACharger = null;
							copie.exp = null;
						}
						else 
							if(fenName.equals("boucleFrm"))
							{
								frmBoucle copie = (frmBoucle) this.copieFrm;
								copie.typeOuv = 1;
								copie.instACharger = null;
								copie.exp = null;
							}
							else 
								if(fenName.equals("condVersOFrm"))
								{
									condVersOFrm copie = (condVersOFrm) this.copieFrm;
									copie.typeOuv = 1;
									//copie.chpCond.setText("");
									copie.instACharger = null;
									copie.exp = null;
								}
		
		if (this.mother != null)
		{
			this.mother.setEnabled(true);
			this.mother.requestFocus();
		}
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		/*System.out.println(this.mother);
		if (this.mother != null)
		{
			this.mother.setEnabled(true);
			this.mother.requestFocus();
		}*/
		String fenName = this.copieFrm.getName();
		if(fenName.equals("decFrm"))
			{
				frmDeclaration copie = (frmDeclaration) this.copieFrm; 
				copie.typeOuv = 1;
				copie.varACharger = null;
			}
		else
			if(fenName.equals("readFrm"))
			{
				frmRead copie = (frmRead) this.copieFrm;
				copie.typeOuv = 1;
				copie.instACharger = null;
			}
			else 
				if(fenName.equals("writeFrm"))
				{
					frmWrite copie = (frmWrite) this.copieFrm;
					copie.typeOuv = 1;
					copie.instACharger = null;
				}
				else 
					if(fenName.equals("affecttionFrm"))
					{
						frmAffectation copie = (frmAffectation) this.copieFrm;
						copie.typeOuv = 1;
						copie.instACharger = null;
						copie.exp = null;
					}
					else 
						if(fenName.equals("condFrm"))
						{
							frmCondition copie = (frmCondition) this.copieFrm;
							copie.typeOuv = 1;
							copie.instACharger = null;
							copie.exp = null;
						}
						else 
							if(fenName.equals("boucleFrm"))
							{
								frmBoucle copie = (frmBoucle) this.copieFrm;
								copie.typeOuv = 1;
								copie.instACharger = null;
								copie.exp = null;
							}else 
								if(fenName.equals("condVersOFrm"))
								{
									condVersOFrm copie = (condVersOFrm) this.copieFrm;
									copie.typeOuv = 1;
									//copie.chpCond.setText("");
									copie.instACharger = null;
									copie.exp = null;
								}
		if (this.mother != null)
		{
			this.mother.setEnabled(true);
			this.mother.requestFocus();
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public EmptyFrame mother;
	public EmptyFrame copieFrm;
	
}
