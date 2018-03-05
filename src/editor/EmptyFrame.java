package editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import recognizer.Expression;

import meiters.dauther;

public class EmptyFrame extends JFrame
{

	public EmptyFrame()
	{
		
		// extraire les dimensions de l��cran
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		// centrer le cadre au milieu de l��cran
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
		// d�finir l�ic�ne et le titre du cadre
		Image imgIco = kit.getImage("images\\AlgTestIcon.png");
		setIconImage(imgIco);
		//Image imgFont = kit.getImage("images\\fontAlg.png");
		//(imgIco);
		//JPanel myPanel = new JPanel();
		//myPanel.setBackground(Color.white);
		//add(myPanel);
		//setTitle("AlgoEditor : Allows you to edit an algorithm or its model.");
		setTitle("AlgoEditor : Vous permet d'�diter un algorithme ou un mod�le d'algorithme.");
	}
	public EmptyFrame mother;
	public ArrayList<Expression> exp;
	public JTextField visExp;
	public AlgoEditor copiePrincipal;
		
}
