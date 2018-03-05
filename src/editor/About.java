package editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


class About extends JDialog
{
	public About(JFrame owner)
	{
		super(owner, "About AlgoTest", true);
		// ajouter le libellé HTML au centre
		add(new JLabel( "<html><h1><i>AlgoTest : Evaluation automatique en Algorithmique</i></h1>" +
				        "<hr> Produit de l'équipe EIAH université Badji Mokhtar Annaba <br> By BOUACHA Ismail enseignant à l'Ecolde Préparatoire aux sciences et techniques Annaba<br>" +
				        "version 0.1, Copyright Novembre 2013</html>"),BorderLayout.CENTER);
		// Le bouton OK ferme la boîte de dialogue
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent event)
									{
										setVisible(false);
									}
								}
							);
		// ajouter le bouton OK dans la partie sud
		JPanel panel = new JPanel();
		panel.add(ok);
		add(panel, BorderLayout.SOUTH);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		// centrer le cadre au milieu de l’écran
		setSize(400,  250);
		setLocation(screenWidth / 3, screenHeight / 3);
		
		//setSize(250, 150);
	}
}