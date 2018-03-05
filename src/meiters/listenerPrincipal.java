
package meiters;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import editor.EmptyFrame;

public class listenerPrincipal implements WindowListener{
	public listenerPrincipal(EmptyFrame pfrm)
	{
		
		this.mainFrm = pfrm;
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		this.mainFrm.repaint();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
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
	EmptyFrame mainFrm;
}

