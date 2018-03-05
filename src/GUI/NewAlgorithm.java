package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout.Constraints;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import recognizer.Instruction;
import recognizer.Variable;

import meiters.dauther;
import meiters.exitListener;

import editor.AlgoEditor;
import editor.Algorithm;
import editor.EmptyFrame;
import editor.GBC;

public class NewAlgorithm extends EmptyFrame {
	
	
	public NewAlgorithm(AlgoEditor mother)
	{
		
		this.mother = mother.mainFrame;
		this.copiePrincipal = mother;
		
		setTitle("Nouveau Algorithme");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 250);
		setResizable(false);
		this.setName("newAlgoFrm");
		addWindowListener(new dauther(this.mother,this));
		initialize();
		//setVisible(true);
	}
	 public void finalize()
     {
		 /*if (this.mother != null)
			{
				this.mother.setEnabled(true);
				this.mother.requestFocus();
			}*/
		 System.out.println("Objet nettoyé de la mémoire");
     }
	public void initialize()
	{
		// Panneau
		JPanel mainPanel = new JPanel();
		GridBagLayout mainLayout = new GridBagLayout();
		mainPanel.setLayout(mainLayout);
		this.setContentPane(mainPanel);
		// Composants graphiques
		// Lib Nom de de l'algorithme
		JLabel	libAlgoVar = new JLabel("Nom ");
		
		// Lib Nom de l'evaluation
		JLabel	libEvalName = new JLabel("Evaluation ");
		
		// Lib nom de l'exercice
		JLabel	libExercName = new JLabel("Exercice ");
		
		// Lib Chemin de stackage
		JLabel	libPath = new JLabel("Chemin ");
		
		// Chp Nom de l algorithme
		chpAlgoVar = new JTextField();
		// Chp Type de l evaluation
		chpEvalName = new JComboBox(new String[]{"Eval1", "Eval2", "Eval3","Eval4"});
		//Chp Exercice
		chpExercMane = new JComboBox(new String[]{"Exercice1", "Exercice2", "Exercice3"});
		//Chp 
		chpPath = new JTextField();
		chpPath.setEnabled(false);
		// Boutton de validation
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				chpPath.setText("E:\\Ismail\\Stage METAH 2013\\Experimentation\\Test\\Evaluation_CC_01_1213\\Kebir\\G5");
				// TODO Auto-generated method stub
				if(chpAlgoVar.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,
						    "Le champ Nom de l'algorithme ne doit pas être vide.",
						    " Attention",
						    JOptionPane.WARNING_MESSAGE);
				}
				else 
					if(chpPath.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null,
							    "Le champ Chemin de l'algorithme ne doit pas être vide.",
							    " Attention",
							    JOptionPane.WARNING_MESSAGE);
					}
					else 
					{
						NewAlgorithm.this.copiePrincipal.curAlg = new Algorithm();
						String name = chpAlgoVar.getText();
						String defPath = "others\\SolutionModel.xml";
						//String path = chpPath.getText();
						String path = "E:\\Ismail\\Stage METAH 2013\\Experimentation\\Test\\Evaluation_CC_01_1213\\Kebir\\G5";
						//System.out.println("la valeur a recuperer est : "+name);
						//System.out.println("la valeur a curALg est : "+NewAlgorithm.this.copiePrincipal.curAlg);
						NewAlgorithm.this.copiePrincipal.curAlg.setAlgoName(chpAlgoVar.getText() );
						//System.out.println("la nom curalgo new  est :"+NewAlgorithm.this.copiePrincipal.curAlg.getAlgoName()+chpAlgoVar.getText());
						NewAlgorithm.this.copiePrincipal.curAlg.setAlgoEvaluation((String)chpEvalName.getSelectedItem());
						NewAlgorithm.this.copiePrincipal.curAlg.setAlgoExercise((String)chpEvalName.getSelectedItem());
						//NewAlgorithm.this.copiePrincipal.curAlg.setAlgoPath(chpPath.getText());
						NewAlgorithm.this.copiePrincipal.curAlg.setAlgoPath(path+"\\"+NewAlgorithm.this.copiePrincipal.curAlg.getAlgoName()+".xml");
						//System.out.println(NewAlgorithm.this.copiePrincipal.curAlg.getAlgoName());
						if (NewAlgorithm.this.mother != null)
						{
							NewAlgorithm.this.mother.setEnabled(true);
							NewAlgorithm.this.mother.requestFocus();	
							//System.out.println("ffffffffffff");
						}// E:\Ismail\Stage METAH 2013\Experimentation\Test\Evaluation_CC_01_1213\Exercices\Exo4_Consecutifs
						//NewAlgorithm.this.copiePrincipal.currentAlgorithm = new File("E:\\Ismail\\Stage METAH 2013\\Experimentation\\Test\\Evaluation_CC_01_1213\\Exercices\\Exo4_Consecutifs\\modele7.xml");
						//NewAlgorithm.this.copiePrincipal.currentAlgorithm = new File(NewAlgorithm.this.copiePrincipal.curAlg.getAlgoPath());
						NewAlgorithm.this.copiePrincipal.currentAlgorithm = new File(defPath);
						try {
							NewAlgorithm.this.copiePrincipal.currentDoc = NewAlgorithm.this.copiePrincipal.builder.parse(NewAlgorithm.this.copiePrincipal.currentAlgorithm);
						} catch (SAXException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						NewAlgorithm.this.copiePrincipal.rootAlgo = NewAlgorithm.this.copiePrincipal.currentDoc.getDocumentElement();
						NewAlgorithm.this.copiePrincipal.curAlg.setAlgoVars(NewAlgorithm.this.copiePrincipal.analyser.getVarNames(NewAlgorithm.this.copiePrincipal.rootAlgo));
						java.util.Iterator<Variable> itv = NewAlgorithm.this.copiePrincipal.curAlg.getAlgoVars().iterator();
						while(itv.hasNext())
							System.out.println("est une :**** "+itv.next().getName());
						NewAlgorithm.this.copiePrincipal.curAlg.setAlgoLines(NewAlgorithm.this.copiePrincipal.analyser.getInstSolutionEdit(NewAlgorithm.this.copiePrincipal.rootAlgo));
						java.util.Iterator<Instruction> it = NewAlgorithm.this.copiePrincipal.curAlg.getAlgoLines().iterator();
						while(it.hasNext())
							System.out.println("est une :**** "+it.next().getNoeud().getNodeName());
						NewAlgorithm.this.copiePrincipal.curAlg.setAlgoTasks(NewAlgorithm.this.copiePrincipal.analyser.getTasks(NewAlgorithm.this.copiePrincipal.rootAlgo));
						NewAlgorithm.this.copiePrincipal.curAlg.setAlgoConsts(NewAlgorithm.this.copiePrincipal.analyser.getConstraints(NewAlgorithm.this.copiePrincipal.rootAlgo));
						NewAlgorithm.this.copiePrincipal.refreshAlgoText();
						NewAlgorithm.this.dispose();
						
					}
			}
		});
		// Boutton de validation
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new exitListener(this));
		// Boutton de dialog
		JButton btnPath = new JButton("...");
		btnPath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File("."));
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int res = fc.showDialog(NewAlgorithm.this,"Chemin");
			if(res == JFileChooser.APPROVE_OPTION)
			{
				String pth
				= fc.getSelectedFile().getPath();
				chpPath.setText(pth);
			}
			}
		});
		// gestionnaires des composants
		// Lib Nom
		GridBagConstraints gbc = new GridBagConstraints();
		
		mainPanel.add(libAlgoVar,new GBC(0,0,1,1).setInsets(5).setFill(GridBagConstraints.VERTICAL).setAnchor(GBC.NORTHWEST));
		// Lib Type
		mainPanel.add(libEvalName,new GBC(0,1,1,1).setInsets(10).setFill(GridBagConstraints.HORIZONTAL));
		// Lib taille
		
		mainPanel.add(libExercName,new GBC(0,2,1,1).setInsets(10).setFill(GridBagConstraints.HORIZONTAL));
		mainPanel.add(libPath,new GBC(0,3,1,1).setInsets(10).setFill(GridBagConstraints.HORIZONTAL));
		// Nom
		mainPanel.add(chpAlgoVar,new GBC(1,0,4,1).setInsets(10).setFill(GridBagConstraints.BOTH).setAnchor(GBC.NORTHWEST));
		// Type
		mainPanel.add(chpEvalName,new GBC(1,1,4,1).setInsets(10).setFill(GridBagConstraints.HORIZONTAL));
		// taille
		mainPanel.add(chpExercMane,new GBC(1,2,4,1).setInsets(10).setFill(GridBagConstraints.BOTH));
		mainPanel.add(chpPath,new GBC(1,3,4,1).setInsets(10).setFill(GridBagConstraints.BOTH));
		mainPanel.add(btnPath,new GBC(5,3,1,1).setInsets(10).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GBC.SOUTHEAST));
		// Valider
		mainPanel.add(btnValider,new GBC(3,4,1,1).setInsets(10).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GBC.SOUTHEAST));
		// Annuler
		mainPanel.add(btnAnnuler,new GBC(4,4,1,1).setInsets(10).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GBC.SOUTHEAST));
		
		
		}
	//public JFrame mother;
	
	
	public JTextField	chpAlgoVar;
	public JComboBox	chpEvalName;
	public JTextField	chpPath;
	public JComboBox	chpExercMane;
	AlgoEditor  copiePrincipal;
}
