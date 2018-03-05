package meiters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import editor.EmptyFrame;

public class exitListener implements ActionListener{
	public exitListener(EmptyFrame pfrmName)
	{
		this.frame = pfrmName;
		//this.mother = mother;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String fenName = this.frame.getName();
		if(fenName.equals("decFrm"))
			{
				this.frame.copiePrincipal.decFrm.typeOuv = 1;
				this.frame.copiePrincipal.decFrm.varACharger = null;
			}
		else
			if(fenName.equals("readFrm")||fenName.equals("writeFrm"))
			{
				this.frame.copiePrincipal.readFrm.typeOuv = 1;
				this.frame.copiePrincipal.readFrm.instACharger = null;
			}
			else
				if(fenName.equals("affecttionFrm"))
				{
					this.frame.copiePrincipal.affecttionFrm.typeOuv = 1;
					this.frame.copiePrincipal.affecttionFrm.instACharger = null;
					this.frame.copiePrincipal.affecttionFrm.exp = null;
				}else
					if(fenName.equals("condFrm"))
					{
						this.frame.copiePrincipal.condFrm.typeOuv = 1;
						this.frame.copiePrincipal.condFrm.instACharger = null;
						this.frame.copiePrincipal.condFrm.exp = null;
					}else
						if(fenName.equals("boucleFrm"))
						{
							this.frame.copiePrincipal.boucleFrm.typeOuv = 1;
							this.frame.copiePrincipal.boucleFrm.instACharger = null;
							this.frame.copiePrincipal.boucleFrm.exp = null;
						}
						else
							if(fenName.equals("condVersOFrm"))
							{
								this.frame.copiePrincipal.frmCondVersO.typeOuv = 1;
								this.frame.copiePrincipal.frmCondVersO.instACharger = null;
								this.frame.copiePrincipal.frmCondVersO.exp = null;
								//this.frame.copiePrincipal.frmCondVersO.chpCond.setText("");
							}
		this.frame.dispose();
		if (this.frame.mother != null)
		{
			this.frame.mother.setEnabled(true);
			this.frame.mother.requestFocus();
			//System.out.println("ffffffffffff");
		}
		
	}
	EmptyFrame frame;
	
}
