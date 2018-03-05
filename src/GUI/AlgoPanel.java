package GUI;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import editor.AlgoEditor;
import editor.Algorithm;

public class AlgoPanel extends JPanel {
	public AlgoPanel(AlgoEditor principal)
	{
		//init(this.getGraphics());
		this.copiePrincipal = principal;
	} 
	public void paintComponent(Graphics g)
	{
		afficherAlgorithmes(g);
		//super.paint(g);
		/*Graphics2D g2D = (Graphics2D) g;
		super.paintComponent(g2D);
		setBackground(Color.white);
		afficherAlgorithmes(g2D);
		g2D.setPaint(Color.red);
		//setBackground(Color.BLACK);
		Color wardi = new Color(180,200,160);
		//setBackground(wardi);
		g2D.drawString(Integer.toString(this.num), 5, this.num*5);
		
		//Graphics2D g2 = (Graphics2D) g;
		// r1 est un rectangle contenant le caractère où le curseur
		// est actuellement positionné :
		Rectangle2D recCurLig = new Rectangle2D.Double(30,0,100,20);
		//r1.setFrameFromDiagonal();
		// on en déduit la zone à surligner, c'est-à-dire 
		// toute la ligne :
		Rectangle2D.Double highlightZone = new Rectangle2D.Double(
				recCurLig.getX(), recCurLig.getY(), this.getWidth(), recCurLig.getHeight());
		// préparation du surlignement :
		g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, TRANSPARENCY_LEVEL));
		//g2D.draw(recCurLig);
		g2D.setColor(CUR_LIG_COLOR);
		// on surligne la ligne :
		
		g2D.fill(highlightZone);
		Rectangle2D recNumLig = new Rectangle2D.Double(0,0,30,20);
		Rectangle2D.Double numLigZone = new Rectangle2D.Double(
				recNumLig.getX(), recNumLig.getY(), recNumLig.getWidth(), recNumLig.getHeight());
		//g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, TRANSPARENCY_LEVEL));
		g2D.setColor(NUM_LIG_COLOR);
		g2D.fill(numLigZone);
		//System.out.println("la valeur curalgo paint est :"+this.copiePrincipal.curAlg);
		if (this.copiePrincipal.curAlg!=null)
			System.out.println("la nom curalgo paint est :"+this.copiePrincipal.curAlg.getAlgoName());
		else
			System.out.println("llllllllllllllllllllllllaaaaaaaaaaaaaaaaaaaa");*/
	}
	public void afficherAlgorithmes(Graphics g)
	{
		
		/*if (this.copiePrincipal.curAlg != null)
		{
			this.copiePrincipal.curAlg.setAlgoVars(this.copiePrincipal.analyser.getVarNames(this.copiePrincipal.rootAlgo));
			g2D.setPaint(NUM_LIG_COLOR);
			g2D.drawString("1", 5, 5);
			Rectangle2D recNumLig = new Rectangle2D.Double(0,0,30,20);
			Rectangle2D.Double numLigZone = new Rectangle2D.Double(
					recNumLig.getX(), recNumLig.getY(), recNumLig.getWidth(), recNumLig.getHeight());
			g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, TRANSPARENCY_LEVEL));
			g2D.setColor(NUM_LIG_COLOR);
			g2D.fill(numLigZone);
			g2D.setPaint(Key_WORDS_COLOR);
			g2D.drawString("Algorithme", 30, 5);
			g2D.setPaint(Color.black);
			g2D.drawString("Algorithme", 45, 5);
			Rectangle2D recCurLig = new Rectangle2D.Double(30,0,100,20);
			Rectangle2D.Double highlightZone = new Rectangle2D.Double(
					recCurLig.getX(), recCurLig.getY(), this.getWidth(), recCurLig.getHeight());
			g2D.setColor(CUR_LIG_COLOR);
			g2D.fill(highlightZone);
		}*/
		
		System.out.println("gggggggggggggggggggggggggggggggggggggggggggg");
		if (this.copiePrincipal.curAlg != null)
			this.copiePrincipal.algoText.append(this.copiePrincipal.curAlg.getAlgoName());
		else 
			this.copiePrincipal.algoText.append("Sarah");
		
		
	}
	 public int num = 1;
	 public AlgoEditor copiePrincipal;
	 private static final float TRANSPARENCY_LEVEL = 0.6f;
	 private static final Color CUR_LIG_COLOR = new Color(51, 153, 255);
	 private static final Color Key_WORDS_COLOR = Color.red;
	 private static final Color NUM_LIG_COLOR = Color.yellow ;
}
