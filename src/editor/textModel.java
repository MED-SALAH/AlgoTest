package editor;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

public class textModel extends JTextArea implements MouseListener{
	private static final int TEXT_COMPONENT_WIDTH = 400;
    private static final int TEXT_COMPONENT_HEIGHT = 250;
    private static final Color HIGHLIGHT_COLOR = new Color(51, 153, 255);
 
    // niveau de transparence :
    // (de plus en plus opaque au fur et à mesure
    // qu'on se rapproche de 1)
    private static final float TRANSPARENCY_LEVEL = 0.6f;
 
    // constructeur
    public textModel(AlgoEditor pAlgoEditor) {
    	this.copiePrincipal = pAlgoEditor;
    	this.addMouseListener(this);
        // ajout d'un listener réagissant aux mouvement du curseur
        // il appelle la méthode de surlignement, c'est-à-dire
        // ici paint() :
        this.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                repaint();
            }
        });
    }
 
    @Override 
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
		// r1 est un rectangle contenant le caractère où le curseur
		// est actuellement positionné :
		if (this.copiePrincipal.curAlg != null)
		{
			Rectangle r1;
			try {
				r1 = this.modelToView(this.getCaretPosition());
				r1.setLocation(0, (this.copiePrincipal.curLine-1)*19);
				Rectangle2D.Double highlightZone = new Rectangle2D.Double(
				        0, r1.y, this.getWidth(), 19);
				// préparation du surlignement :
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, TRANSPARENCY_LEVEL));
				g2.setColor(HIGHLIGHT_COLOR);
				
				// on surligne la ligne :
				
				g2.fill(highlightZone);
				
			
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
		// on en déduit la zone à surligner, c'est-à-dire 
		// toute la ligne :
		
		
    }
    AlgoEditor copiePrincipal;

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("hnaa 1*** :"+this.copiePrincipal.curLine);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("hnaa 2 *** :"+this.copiePrincipal.curLine);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("hnaa  4*** :"+this.copiePrincipal.curLine);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int y = this.getMousePosition().y/19;
		if(y<=this.copiePrincipal.curAlg.getAlgoVars().size()+2+this.copiePrincipal.curAlg.getAlgoLines().size())
		{
			this.copiePrincipal.curLine = y+1;
			textModel.this.copiePrincipal.refreshAlgoText();
			textModel.this.copiePrincipal.algoText.repaint();
			//System.out.println("dkhalech :");
		}
		//else
		//	System.out.println("ma dkhalech :"+this.copiePrincipal.curLine);
		//this.copiePrincipal.curLine
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("hnaa 5 *** :"+this.copiePrincipal.curLine);
	}
}
