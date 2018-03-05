package editor;
import editor.GBC;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import recognizer.*;
import GUI.*;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import recognizer.Expression;
import recognizer.Groupe;
import recognizer.Instruction;
import recognizer.ModelAnalyser;
import recognizer.Variable;

import GUI.AlgoPanel;
import GUI.NewAlgorithm;
import GUI.frmAffectation;
import GUI.frmBoucle;
import GUI.frmCondition;
import GUI.frmDeclaration;
import GUI.frmRead;
import GUI.frmWrite;

import meiters.dauther;
import meiters.exitListener;
import meiters.listenerPrincipal;
import meiters.openFrameListener;

public class AlgoEditor extends EmptyFrame{
// Editeur des algorithmes et des modèles  
    public AlgoEditor() throws ParserConfigurationException, SAXException, IOException {
    	ecrire_message = new ArrayList<String>();
    	read_valeur = new ArrayList<String>();
    	index=0;
    	
    	mainFrame = new EmptyFrame();
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		// centrer le cadre au milieu de l’écran
		mainFrame.setSize(screenWidth - (screenWidth / 4), screenHeight- (screenHeight / 4));
		mainFrame.setLocation(screenWidth / 8, screenHeight / 8);
		mainFrame.setTitle("AlgoEditor : Vous permet d'éditer un algorithme ou un modèle d'algorithme.");
    	intializeGraphics(mainFrame);
    	this.decFrm = new frmDeclaration(this);
    	//this.decFrm.setName("decFrm");
        this.affecttionFrm = new frmAffectation(this);
        //this.affecttionFrm.setName("affecttionFrm");
        this.readFrm = new frmRead(this);
        //this.readFrm.setName("readFrm");
        this.writeFrm = new frmWrite(this);
        //this.writeFrm.setName("writeFrm");
        this.condFrm = new frmCondition(this);
        //this.condFrm.setName("condFrm");
        this.boucleFrm = new frmBoucle(this);
        //this.boucleFrm.setName("boucleFrm");
        this.tacheFrm = new frmTache(this);
        //this.tacheFrm.setName("tacheFrm");
        this.contrainteFrm = new frmContrainte(this);
        this.frmCondVersO = new condVersOFrm(this);
        //this.contrainteFrm.setName("contrainteFrm");
    	this.analyser = new ModelAnalyser();
    	if (this.builder  == null)
		{
			DocumentBuilderFactory factory
			= DocumentBuilderFactory.newInstance();
			this.builder = factory.newDocumentBuilder();
		};
    	mainFrame.addWindowListener(new listenerPrincipal(mainFrame));
    	mainFrame.setVisible(true);
    	//mainFrame.repaint();
    }
    public void initialize ()
    {
    	// Fentre principale
    	
    	// Barre de menu
    	menuBar = new JMenuBar();
    	// Barre d'outils
    	toolBar = new JToolBar();
    	// Menus
    	// Fichiers
    	fileMenu = new JMenu("Fichier");
    	// Sous menus de fichier
    	newItem = new  JMenuItem("Nouveau");
    	openItem = new  JMenuItem("Ouvrir");
    	quitItem = new  JMenuItem("Quitter");
    	quitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Verifer si l'algo cour est sauvegardé ou  non
			}
		});
    	fileMenu.add(newItem);
    	newItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AlgoEditor.this.nouvAlgo();
			}
		});
    	fileMenu.add(openItem);
    	openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AlgoEditor.this.openAlgo();
			}
		});
    	fileMenu.addSeparator();
    	fileMenu.add(quitItem);
    	quitItem.addActionListener(new exitListener(mainFrame));
    	// Ajouter fichier à la barre de menu
    	menuBar.add(fileMenu);
    	// Menu edition
    	editMenu = new JMenu("Edition");
    	menuBar.add(editMenu);
    	// Menu aide
    	helpMenu = new JMenu("Aide");
    	JMenuItem apropos = new JMenuItem("A propos");
    	apropos.addActionListener(new
    			ActionListener()
    			{
    			public void actionPerformed(ActionEvent event)
    			{
	    			About dialog = new About(AlgoEditor.this);
	    			dialog.setVisible(true); // boîte de dialogue
    			}
    			});
    	helpMenu.add(apropos);
    	menuBar.add(helpMenu);
    	// Tool bar
    	toolBar = new JToolBar("AlgoTest");
    	btnNewSC = new JButton( new ImageIcon("images\\new.png"));
    	btnNewSC.setToolTipText("Nouveau");
    	btnNewSC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AlgoEditor.this.nouvAlgo();
			}
		});
    	btnlancer = new JButton( new ImageIcon("images\\run.png"));
    	btnlancer.setToolTipText("Nouveau");
    	btnlancer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AlgoEditor.this.executerLines();
				consolText.setText(ecrire_message.get(0));
			}
		});
    	btnarret = new JButton( new ImageIcon("images\\stop.png"));
    	btnarret.setToolTipText("Nouveau");
    	btnarret.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// TODO Auto-generated method stub
				consolText.setText("");
				//AlgoEditor.this.nouvAlgo();
			}
		});
    	JButton btnOpenSC = new JButton(new ImageIcon("images\\open.png"));
    	btnOpenSC.setToolTipText("Ouvrir");
    	btnOpenSC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AlgoEditor.this.openAlgo();
			}
		});
    	btnSaveSC = new JButton(new ImageIcon("images\\save.png"));
    	btnSaveSC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					AlgoEditor.this.saveDocument();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	btnSaveSC.setToolTipText("Enregistrer");
    	btnRecognitionSC = new JButton(new ImageIcon("images\\recognition.png"));
    	btnRecognitionSC.setToolTipText("Reconnaitre");
    	this.resEval = new JLabel("");
    	JButton btnEnnonceSC = new JButton( new ImageIcon("images\\eye.png"));
    	
    	btnEnnonceSC.setToolTipText("Ennoncé");
    	this.btnRecognitionSC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				evaluateCurSolution();
			}
		});
    	toolBar.add(btnNewSC);
    	toolBar.add(btnNewSC);
    	toolBar.add(btnOpenSC);
    	toolBar.add(btnSaveSC);
    	toolBar.add(btnEnnonceSC);
    	toolBar.add(btnRecognitionSC);
    	toolBar.addSeparator(new Dimension(50,10));
    	toolBar.add(btnlancer);
    	toolBar.add(btnarret);
    	toolBar.add(this.resEval);
    	
    	// definir la barre de menu de la fenetre principale
    	mainFrame.setJMenuBar(menuBar);
    	mainFrame.add(toolBar, BorderLayout.NORTH);
    }
    public void intializeGraphics(EmptyFrame pfrm)
    {
    	initialize();
    	// les panneaux 
    	mainPanel = new JPanel();
    	eastPanel = new JPanel();
    	westPanel = Box.createVerticalBox();
    	northPanel = Box.createHorizontalBox();
    	southPanel = new JPanel();
    	consolPanel = new JPanel();

    	topPanel = new JPanel();
    	downPanel = new JPanel();
    	
    	// eastPanel
    	eastPanel.setLayout(new BorderLayout());
    	eastPanel.add(northPanel,BorderLayout.NORTH);
    	eastPanel.add(southPanel,BorderLayout.CENTER);
    	eastPanel.add(consolPanel,BorderLayout.SOUTH);
    	// west Panle
    	westPanel.add(topPanel);
    	//westPanel.add(Box.createVerticalStrut(10));
    	westPanel.add(downPanel);
    	// north panel
    	JButton btnDown = new JButton(new ImageIcon("images\\down.png"));
    	btnDown.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AlgoEditor.this.down();
			}
		});
    	btnDown.setToolTipText("Descendre");
    	JButton btnUp = new JButton(new ImageIcon("images\\up.png"));
    	btnUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AlgoEditor.this.up();
					//AlgoEditor.this.algoText.paint(AlgoEditor.this.algoText.getGraphics());
					//System.out.println("la valeur modifie :"+AlgoEditor.this.curLine);
					//System.out.println("la valeur envoyee est :"+AlgoEditor.this.curLine);
					
				
			}
		});
    	btnUp.setToolTipText("Monter");
    	//btnUp.setIcon();
    	northPanel.add(btnDown);
    	northPanel.add(Box.createHorizontalStrut(1));
    	northPanel.add(btnUp);
    	// south Panel
    	southPanel.setLayout(new BorderLayout());
    	
    	algoPanel = new AlgoPanel(AlgoEditor.this);
    	//algoPanel.setBackground(new Color(51,155,240));
    	this.algoText = new textModel(this);
    	this.algoText.setEditable(false);
    	this.algoText.setFont(new Font("Serif", Font.BOLD, 16));
    	 this.algoText.addCaretListener(new CaretListener() {
             public void caretUpdate(CaretEvent e) {
                 repaint();
             }
         });
    	algoPane = new JScrollPane(this.algoText);
    	algoPane.setPreferredSize(new Dimension(TEXT_COMPONENT_WIDTH ,TEXT_COMPONENT_HEIGHT));
    	algoPanel.add(algoPane);
    	
    	southPanel.add(algoPane,BorderLayout.CENTER);
    	// mainPanel
    	mainPanel.setLayout(new BorderLayout());
    	mainPanel.add(westPanel,BorderLayout.WEST);
    	mainPanel.add(eastPanel,BorderLayout.CENTER);
    	
    	this.consolText = new consolModel();
    	this.consolText.setEditable(true);
    	this.consolText.setFont(new Font("Serif", Font.BOLD, 16));
    	this.consolText.setForeground(Color.white);

    	
    
    	consolText.setBackground(Color.black);
    	consolPane = new JScrollPane(this.consolText);
    	consolPanel.add(consolPane);
    	consolText.setPreferredSize(new Dimension(800 ,TEXT_COMPONENT_HEIGHT));
    	
    	consolText.addKeyListener (new KeyAdapter(){ 
    		public void keyPressed(KeyEvent e){
    			
    		    	
    				int touche = e.getKeyCode() ;
    				if(touche == KeyEvent.VK_ENTER){
    					number = 0;
    					
    					int pos = consolText.getCaretPosition(); //get the cursor position
    					System.out.println(pos + "  CaretPosition");
    					
    					if(index==0){
    						number = ecrire_message.get(index).length()*(index+1);
    						}
    					
    					else if (index==1){
    						number = (ecrire_message.get(index).length()*(index+1))+read_valeur.get(0).length()+1;
    						}
    					
    					System.out.println(number + "  lenght of text in textarea");
    					
    					readvalue = consolText.getText().substring(number);
    					
    					read_valeur.add(readvalue);
    					
    					System.out.println(readvalue+" la valeur");
    					
    					consolText.append("\n");
    					if(index==1){
    						
    					consolText.append(ecrire_message.get(index+1)+read_valeur.get(0));
    					
    					}else{
    						
    						consolText.append(ecrire_message.get(index+1));
    						
    					}
    					
        				index = index +1;
        				if(index==2){
        					consolText.append("\n");
        					consolText.append(ecrire_message.get(3)+read_valeur.get(1));
        					
        				}
        				
        			
    					
    				}
    				
    	       
    	   }
    	}) ;
    	mainPanel.add(consolPanel,BorderLayout.SOUTH);
    	//top panel
    	GridBagLayout gridTop = new GridBagLayout();
    	topPanel.setLayout(gridTop);
    	JLabel libInst = new JLabel("Instructions");
    	libInst.setFont(new Font("Serif", Font.BOLD,14));
    	libInst.setForeground(Color.red);
    	btnDec = new JButton("Déclaration");
    	btnDec.setName("Declaration");
    	btnDec.setEnabled(false);
    	btnAffect = new JButton("Affectation");
    	btnAffect.setName("Affectation");
    	btnAffect.setEnabled(false);
    	btnAffect.addActionListener(new openFrameListener("affecttionFrm",AlgoEditor.this));
    	btnLire = new JButton("Lire");
    	btnLire.setEnabled(false);
    	btnLire.setName("Lire");
    	btnLire.addActionListener(new openFrameListener("readFrm", AlgoEditor.this));
    	btnEcrire = new JButton("Ecrire");
    	btnEcrire.setName("Ecrire");
    	btnEcrire.setEnabled(false);
    	btnEcrire.addActionListener(new openFrameListener("writeFrm",AlgoEditor.this));
    	btnCond = new JButton("Condition");
    	btnCond.setName("Condition");
    	btnCond.setEnabled(false);
    	btnCond.addActionListener(new openFrameListener("condFrm",AlgoEditor.this));
    	btnBoucle = new JButton("Boucle");
    	btnBoucle.setName("Boucle");
    	btnBoucle.setEnabled(false);
    	btnBoucle.addActionListener(new openFrameListener("boucleFrm",AlgoEditor.this));
    	btnDec.addActionListener(new openFrameListener("decFrm", AlgoEditor.this));
    	
    	btnLire.addActionListener(new openFrameListener("readFrm", AlgoEditor.this));
    	btnFction = new JButton("Fonction");
    	btnFction.setEnabled(false);
    	btnComment = new JButton("Commentaire");
    	btnComment.setEnabled(false);
    	btnMod = new JButton("Modifier");
    	btnMod.setEnabled(false);
    	btnMod.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AlgoEditor.this.updateInst();
			}
		});
    	btnSupp = new JButton("Supprimer");
    	btnSupp.setEnabled(false);
    	btnSupp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AlgoEditor.this.deleteInst();
			}
		});
    	//**********************
    	groupInst = new ButtonGroup();
    	
    	topPanel.add(libInst,new GBC(0,0,1,1).setFill(GridBagConstraints.BOTH).setInsets(5));
    	topPanel.add(btnDec,new GBC(0,1,1,1).setFill(GridBagConstraints.BOTH).setInsets(5));
    	topPanel.add(btnAffect,new GBC(1,1,1,1).setFill(GridBagConstraints.BOTH).setInsets(5));
    	topPanel.add(btnLire,new GBC(0,2,1,1).setFill(GridBagConstraints.BOTH).setInsets(5));
    	topPanel.add(btnEcrire,new GBC(1,2,1,1).setFill(GridBagConstraints.BOTH).setInsets(5));
    	topPanel.add(btnCond,new GBC(0,3,1,1).setFill(GridBagConstraints.BOTH).setInsets(5));
    	topPanel.add(btnBoucle,new GBC(1,3,1,1).setFill(GridBagConstraints.BOTH).setInsets(5));
    	topPanel.add(btnFction,new GBC(0,4,1,1).setFill(GridBagConstraints.BOTH).setInsets(5));
    	topPanel.add(btnComment,new GBC(1,4,1,1).setFill(GridBagConstraints.BOTH).setInsets(5));
    	topPanel.add(btnMod,new GBC(0,5,1,1).setFill(GridBagConstraints.BOTH).setInsets(5));
    	topPanel.add(btnSupp,new GBC(1,5,1,1).setFill(GridBagConstraints.BOTH).setInsets(5));
    	//down panel
    	GridBagLayout gridDown = new GridBagLayout();
    	downPanel.setLayout(gridDown);
    	JLabel libDesc = new JLabel("Description");
    	libDesc.setFont(new Font("Serif", Font.ITALIC,14));
    	libDesc.setForeground(Color.red);
    	btnTask = new JButton("Tache");
    	btnTask.setName("Tache");
    	btnTask.addActionListener(new openFrameListener("tacheFrm",AlgoEditor.this));
    	
    	
    	btnCondVersO = new JButton("Autre Cond");
    	btnCondVersO.setName("CondVersO");
    	btnCondVersO.setEnabled(false);
    	btnCondVersO.addActionListener(new openFrameListener("condVersOFrm",AlgoEditor.this));
    	
    	btnConst = new JButton("Descriptifs");
    	btnConst.addActionListener(new openFrameListener("contrainteFrm",AlgoEditor.this));
    	btnConst.setName("Contrainte");
    	downPanel.add(libDesc,new GBC(0,0,1,1).setFill(GridBagConstraints.BOTH).setInsets(5));
    	downPanel.add(btnTask,new GBC(0,1,1,1).setFill(GridBagConstraints.BOTH).setInsets(5));
    	downPanel.add(btnConst,new GBC(0,2,1,1).setFill(GridBagConstraints.BOTH).setInsets(5));
    	downPanel.add(btnCondVersO,new GBC(0,3,1,1).setFill(GridBagConstraints.BOTH).setInsets(5));
    	//downPanel.setVisible(false);
    	algoPanel.num = 2;
    	algoPanel.num = 3;
    	pfrm.add(mainPanel,BorderLayout.CENTER);
    	//pfrm.repaint();
    	//pfrm.
    	
 
    }
    public void nouvAlgo()
    {
    	if (AlgoEditor.this.curAlg == null)
		{
			
			//System.out.println("la valeur envoyee est :"+AlgoEditor.this);
			
		}
		else 
		{
			// teser
		}
		AlgoEditor.this.resEval.setText("");
		//AlgoEditor.this.curAlg = new Algorithm();
		NewAlgorithmFrm = new NewAlgorithm(AlgoEditor.this);
		
		NewAlgorithmFrm.setVisible(true);
		mainFrame.setEnabled(false);
    }
   public void openAlgo()
   {
	   JFileChooser fc = new JFileChooser();
		//fc.setCurrentDirectory(new File("."));
		//fc.setCurrentDirectory(new File("E:\\Ismail\\Stage METAH 2013\\Experimentation\\Test\\Evaluation_CC_01_1213\\Gharbi\\E4"));"E:\\Ismail\\Stage METAH 2013\\Experimentation\\Test\\Evaluation_CC_01_1213\\Kebir\\G5"
		fc.setCurrentDirectory(new File("C:\\Users\\SALEH\\workspace\\AlgoTest\\others\\evaluation_Test_28_11_2013\\Modeles"));
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int res = fc.showDialog(AlgoEditor.this,"Chemin");
		if(res == JFileChooser.APPROVE_OPTION)
		{
			AlgoEditor.this.resEval.setText("");
			String pth = fc.getSelectedFile().getPath();
			//chpPath.setText(pth);
			
			AlgoEditor.this.curAlg = new Algorithm();
			
			AlgoEditor.this.currentAlgorithm = new File(pth);
			try {
				AlgoEditor.this.currentDoc = AlgoEditor.this.builder.parse(AlgoEditor.this.currentAlgorithm);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			AlgoEditor.this.rootAlgo = AlgoEditor.this.currentDoc.getDocumentElement();
			AlgoEditor.this.rootAlgo = AlgoEditor.this.currentDoc.getDocumentElement();
			AlgoEditor.this.curAlg.setAlgoName(AlgoEditor.this.rootAlgo.getAttribute("nom"));
			AlgoEditor.this.curAlg.setAlgoEvaluation(AlgoEditor.this.rootAlgo.getAttribute("evaluation"));
			AlgoEditor.this.curAlg.setAlgoExercise(AlgoEditor.this.rootAlgo.getAttribute("exercice"));
			AlgoEditor.this.curAlg.setAlgoPath(pth);
			
			AlgoEditor.this.curAlg.setAlgoVars(AlgoEditor.this.analyser.getVarNames(AlgoEditor.this.rootAlgo));
			/*java.util.Iterator<Variable> itv = AlgoEditor.this.curAlg.getAlgoVars().iterator();
			while(itv.hasNext())
				System.out.println("est une :**** "+itv.next().getName());*/
			AlgoEditor.this.curAlg.setAlgoLines(AlgoEditor.this.analyser.getInstSolutionEdit(AlgoEditor.this.rootAlgo));
			/*java.util.Iterator<Instruction> it = AlgoEditor.this.curAlg.getAlgoLines().iterator();
			while(it.hasNext())
				System.out.println("est une :**** "+it.next().getNoeud().getNodeName());*/
			AlgoEditor.this.curAlg.setAlgoTasks(AlgoEditor.this.analyser.getTasks(AlgoEditor.this.rootAlgo));
			AlgoEditor.this.curAlg.setAlgoConsts(AlgoEditor.this.analyser.getConstraints(AlgoEditor.this.rootAlgo));
			AlgoEditor.this.refreshAlgoText();
			AlgoEditor.this.dispose();
		}
   }
    public void down()
    {
    	if (AlgoEditor.this.curAlg != null)
		{
			if(AlgoEditor.this.curLine < AlgoEditor.this.algoText.getLineCount())
				AlgoEditor.this.curLine++;
			AlgoEditor.this.refreshAlgoText();
			AlgoEditor.this.algoText.repaint();
			//System.out.println("la valeur modifie :"+AlgoEditor.this.curLine);
			
		}
    }
    public void up()
    {
    	if (AlgoEditor.this.curAlg != null)
		{
			if(AlgoEditor.this.curLine>1)
				AlgoEditor.this.curLine--;
			
			AlgoEditor.this.refreshAlgoText();
			AlgoEditor.this.algoText.repaint();
		}
    }
    public void viderAlgoZone(AlgoPanel pZone)
    {
    	pZone = new AlgoPanel(AlgoEditor.this);
    }
    public void repaintAlgoZone(AlgoPanel pZone)
    // permet de raffraichir  la zone de l'algorithme
    {
    	repaintAlgoZone(pZone);
    	
    }
    public void refreshAlgoText()
	// permet de raffraichir le texte de l'algorithme
    {
		this.algoText.setText("");
		if (this.curAlg != null)
		{
			//this.algoText.setForeground(Color.green);
			//this.algoText.setFont(new Font("Serif", Font.PLAIN,14));
			this.algoText.append("1.  ");
			//this.algoText.setForeground(Color.red);
			this.algoText.setFont(new Font("Serif", Font.ITALIC,14));
			this.algoText.append("Algorithme ");
			/*try {
				this.algoText.getHighlighter().addHighlight(0, 3,    new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			
			//this.curLine = 5;
			//this.algoText.setCaretPosition(this.curLine);
			//this.algoText.setForeground(Color.black);
			//this.algoText.setFont(new Font("Serif", Font.PLAIN,14));
			this.algoText.append(this.curAlg.getAlgoName()+";\n");
			//this.curAlg.setAlgoVars(this.analyser.getVarNames(this.rootAlgo));
			if(this.curAlg.getAlgoVars()!=null)
				afficherVars(this.curAlg.getAlgoVars());
			afficherLines();
			this.algoText.setCaretPosition(5);
			//
			int nbLig =  this.curAlg.getAlgoLines().size()+this.curAlg.getAlgoVars().size()+3;
			//System.out.println("lig cour : "+this.curLine+", saize Vars : "+this.curAlg.getAlgoVars().size());
			if(this.curLine==1)
			{
				this.desActiverBtnsInst();
				this.btnSupp.setEnabled(false);
				this.btnMod.setEnabled(false);
			}
			else
				if(this.curLine > 1 && this.curLine < this.curAlg.getAlgoVars().size()+2)
				{
					this.desActiverBtnsInst();
					this.btnSupp.setEnabled(true);
					this.btnMod.setEnabled(true);
				}
			else 
				if(this.curLine == this.curAlg.getAlgoVars().size()+2)
				{
					this.activerBtnsInst();
					this.btnSupp.setEnabled(false);
					this.btnMod.setEnabled(false);
				}
				else 
					if(this.curLine == nbLig)
					{
						this.desActiverBtnsInst();
						this.btnSupp.setEnabled(false);
						this.btnMod.setEnabled(false);
					}
					else
					{
						btnCondVersO.setEnabled(false);
						int pos = this.curLine-(this.curAlg.getAlgoVars().size()+3);
						Instruction curInst = this.curAlg.getAlgoLines().get(pos);
						String instType = curInst.getNoeud().getNodeName().trim();
						//System.out.println("Num :"+this.curLine+", inst type : "+curInst.getNoeud().getNodeName());
						if (!instType.equals("For")&&!instType.equals("While")&&!instType.equals("If"))
							this.activerBtnsInst();
						else 
							{
								this.desActiverBtnsInst();
								if(instType.equals("If"))
									btnCondVersO.setEnabled(true);
							}
						if (!instType.equals("BeginIf")&&!instType.equals("BeginFor")&&!instType.equals("BeginWhile")&&!instType.equals("EndIf")&&!instType.equals("EndFor")&&!instType.equals("EndWhile"))
						{
							this.btnSupp.setEnabled(true);
							if(instType.equals("Else"))
								this.btnMod.setEnabled(false);
							else
								this.btnMod.setEnabled(true);
						}
						else
						{
							this.btnSupp.setEnabled(false);
							this.btnMod.setEnabled(false);
						}
						
						
					}
			
			
		}
	}
    public void activerBtnsInst()
    {
    	btnAffect.setEnabled(true);
    	btnLire.setEnabled(true);
    	btnEcrire.setEnabled(true);
    	btnCond.setEnabled(true);
    	btnBoucle.setEnabled(true);
    	if(this.curLine > 1)
    		btnDec.setEnabled(true);
    	else 
    		btnDec.setEnabled(false);
    	//btnFction.setEnabled(true);
    	//btnComment.setEnabled(true);
    	//btnMod.setEnabled(true);
    	//btnSupp.setEnabled(true);
    }
    public void desActiverBtnsInst()
    {
    	//btnDec.setEnabled(false);
    	if(this.curLine > 1)
    		btnDec.setEnabled(true);
    	else 
    		btnDec.setEnabled(false);
    	btnAffect.setEnabled(false);
    	btnLire.setEnabled(false);
    	btnEcrire.setEnabled(false);
    	btnCond.setEnabled(false);
    	btnBoucle.setEnabled(false);
    	//btnFction.setEnabled(false);
    	//btnComment.setEnabled(false);
    	//btnMod.setEnabled(false);
    	//btnSupp.setEnabled(false);
    }
    public void afficherVars(ArrayList<Variable> pVars)
    {
    	int i = 2;
    	Iterator<Variable> it = pVars.iterator();
		while(it.hasNext())
    	{
			Variable item = (Variable) it.next() ;
    		String line = "";
    		//"Nombre", "Caractere", "Const","Tableau de nombres", "Tableau de caracteres","Booleen"
    		this.algoText.append(Integer.toString(i)+".");
    		if (i == 2)
    			this.algoText.append(" Var");
    		switch(Integer.parseInt(item.getType()))
    		{
    			case 1 :
    					line = "	Nombre : "+item.getName();
    					break;
    			case 2 :
    					line = "	Caractere : "+item.getName();
    					break;
    			case 3 :
    					line = "	Const : "+item.getName();
    					break;
    			case 4 :
    					line = "	Tableau de nombres : "+item.getName()+"["+item.getTaille()+"]";
    					break;
    			case 5 :
    					line = "	Tableau de caracteres : "+item.getName()+"["+item.getTaille()+"]";
    					break;
    			case 6 :
    					line = "	Booleen : "+item.getName();
    					break;
    		}
    		this.algoText.append(line+";\n");
    		i++;;
    	}
    }
    
    public void executerLines()
   
    {
    	consolText.setText("");
    	
    	int curLine;
    	if (this.curAlg.getAlgoVars()!=null)
    		curLine = this.curAlg.getAlgoVars().size()+2;
    	else
    		curLine = 2;
    	
    	curLine++;
    	
    	ArrayList<Instruction> listInst = this.curAlg.getAlgoLines();
    	Iterator it = listInst.iterator();
    	
    	while(it.hasNext())
    	{
    		Instruction item = (Instruction) it.next() ;
    		
    		String instType = item.getNoeud().getNodeName();
    		int nbre = this.curAlg.getAlgoLines().size();
    		
    		if(!instType.equals("Dec") && !instType.equals("Var")&&!instType.equals("Body"))
    		{
    			
    			
    			
    			switch( instTypes_en.valueOf(instType))
    		
	    		{
	    		case Read : 
	    					
	    					ReadInstruction instRead = new ReadInstruction(item);
	    					if(instRead.getRange().equals("")){
	    						
	    						}
	    					
	    					else {
	    						this.consolText.append(getTab(this.tabLevel)+"Read("+instRead.getValue()+"["+instRead.getRange()+"]);\n");
	    					}
	    					curLine++;
	    					break;
	    		case Write : 
			    			WriteInstruction instWrite = new WriteInstruction(item);
							if(instWrite.getRange().equals("")){
								ecrire = instWrite.getValue();
								ecrire_message.add(ecrire);
								//this.consolText.append(getTab(this.tabLevel)+instWrite.getValue());
								}
							else {
								this.consolText.append(getTab(this.tabLevel)+"Write("+instWrite.getValue()+"["+instWrite.getRange()+"]);\n");
							}
							curLine++;
					break;
	    		case Affectation :
	    					String textAffecf = this.analyser.getTextExp(item.getExpressions());
	    					this.consolText.append(getTab(this.tabLevel)+textAffecf+";\n");
							curLine++;
							break;
	    		case If : 
	    					String text = this.analyser.getTextExp(item.getExpressions());
	    					this.consolText.append(getTab(this.tabLevel)+"If ( "+text +" ) Then\n");
	    					curLine++;
	    					break;
	    		case BeginIf : 
	    					this.consolText.append(getTab(this.tabLevel)+"BeginIf\n");
	    					this.tabLevel++;
	    					curLine++;
	    					break;
	    		case Else : 
	    					
	    					this.tabLevel--;
	    					this.consolText.append(getTab(this.tabLevel)+"Else\n");
	    					this.tabLevel++;
	    					curLine++;
	    					break;
	    		case EndIf : 
	    					
	    					this.tabLevel--;
	    					this.consolText.append(getTab(this.tabLevel)+"EndIf\n");
	    					curLine++;
	    					break;
	    		case For : 
	    					ForInstruction instFor = new ForInstruction(item);
	    					//this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"For ("+instFor.getVar()+" = "+instFor.getValInit()+"; "+this.analyser.getTextExp(instFor.getExpressions())+"; "+instFor.getVar()+" = "+instFor.getPas()+")\n");
	    					this.consolText.append(getTab(this.tabLevel)+"For ("+instFor.getVar()+" = "+instFor.getValInit()+"; "+this.analyser.getTextExp(instFor.getExpressions())+"; "+instFor.getPas()+")\n");
	    					curLine++;
	    					break;
	    		case BeginFor :
	    					this.consolText.append(getTab(this.tabLevel)+"BeginFor\n");
	    					this.tabLevel++;
	    					curLine++;
	    					break;
	    		case EndFor :
	    					this.tabLevel--;
	    					this.consolText.append(getTab(this.tabLevel)+"EndFor\n");
	    					curLine++;
	    					break;
	    		case While : 
	    					String textWhile = this.analyser.getTextExp(item.getExpressions());
	    					this.consolText.append(getTab(this.tabLevel)+"While ( "+textWhile+" )\n");
	    					curLine++;
	    					break;
	    		case BeginWhile :
	    					this.consolText.append(getTab(this.tabLevel)+"BeginWhile\n");
	    					this.tabLevel++;
	    					curLine++;
	    					break;
	    		case EndWhile :
			    			this.tabLevel--;		
			    			this.consolText.append(getTab(this.tabLevel)+"EndWhile\n");
	    					curLine++;
	    					break;
	    		}
    		}
	    		
    	}
    	//this.algoText.append(Integer.toString(curLine)+"."+" Fin");
    	
        // r1 est un rectangle contenant le caractère où le curseur
        // est actuellement positionné :
    	//this.algoText.setCaretPosition(10);
    	/*Graphics g = this.algoText.getGraphics();
    	this.algoText.paint(g);
    	Graphics2D g2 = (Graphics2D) g;
		// r1 est un rectangle contenant le caractère où le curseur
		// est actuellement positionné :
		Rectangle2D r1 =new Rectangle2D.Double(0,0,100,10);
		// on en 12déduit la zone à surligner, c'est-à-dire 
		// toute la ligne :
		Rectangle2D.Double highlightZone = new Rectangle2D.Double(
		        0, 0, 100,10);
		// préparation du surlignement :
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, TRANSPARENCY_LEVEL));
		g2.setColor(HIGHLIGHT_COLOR);
		// on surligne la ligne :
		g2.fill(highlightZone);*/
    	
    }
    
    public void afficherLines()
    //permet d'afficher les instructions
    {
    	int curLine;
    	if (this.curAlg.getAlgoVars()!=null)
    		curLine = this.curAlg.getAlgoVars().size()+2;
    	else
    		curLine = 2;
    	//this.curLine = curLine;
    	this.algoText.append(Integer.toString(curLine)+"."+" Debut\n");
    	curLine++;
    	//ArrayList<Instruction> listInst = this.analyser.getInstSolution(this.rootAlgo);
    	ArrayList<Instruction> listInst = this.curAlg.getAlgoLines();
    	Iterator it = listInst.iterator();
    	//int tabLevel =1;
    	
    	while(it.hasNext())
    	{
    		Instruction item = (Instruction) it.next() ;
    		//System.out.println(item.getNoeud().getNodeName());
    		//instTypes_en();
    		//instTypes_en type;
    		String instType = item.getNoeud().getNodeName();
    		int nbre = this.curAlg.getAlgoLines().size();
    		//System.out.println(instTypes_en.valueOf(instType));
    		if(!instType.equals("Dec") && !instType.equals("Var")&&!instType.equals("Body"))
    		{
    			
    			switch( instTypes_en.valueOf(instType))
    		
	    		{
	    		case Read : 
	    					
	    					ReadInstruction instRead = new ReadInstruction(item);
	    					if(instRead.getRange().equals("")){
	    						this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"Read("+instRead.getValue()+");\n");
	    					}
	    					
	    					else {
	    						this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"Read("+instRead.getValue()+"["+instRead.getRange()+"]);\n");
	    					}
	    					curLine++;
	    					break;
	    		case Write : 
			    			WriteInstruction instWrite = new WriteInstruction(item);
							if(instWrite.getRange().equals(""))
								this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"Write("+instWrite.getValue()+");\n");
							else 
								this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"Write("+instWrite.getValue()+"["+instWrite.getRange()+"]);\n");
							curLine++;
					break;
	    		case Affectation :
	    					String textAffecf = this.analyser.getTextExp(item.getExpressions());
	    					this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+textAffecf+";\n");
							curLine++;
							break;
	    		case If : 
	    					String text = this.analyser.getTextExp(item.getExpressions());
	    					this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"If ( "+text +" ) Then\n");
	    					curLine++;
	    					break;
	    		case BeginIf : 
	    					this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"BeginIf\n");
	    					this.tabLevel++;
	    					curLine++;
	    					break;
	    		case Else : 
	    					
	    					this.tabLevel--;
	    					this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"Else\n");
	    					this.tabLevel++;
	    					curLine++;
	    					break;
	    		case EndIf : 
	    					
	    					this.tabLevel--;
	    					this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"EndIf\n");
	    					curLine++;
	    					break;
	    		case For : 
	    					ForInstruction instFor = new ForInstruction(item);
	    					//this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"For ("+instFor.getVar()+" = "+instFor.getValInit()+"; "+this.analyser.getTextExp(instFor.getExpressions())+"; "+instFor.getVar()+" = "+instFor.getPas()+")\n");
	    					this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"For ("+instFor.getVar()+" = "+instFor.getValInit()+"; "+this.analyser.getTextExp(instFor.getExpressions())+"; "+instFor.getPas()+")\n");
	    					curLine++;
	    					break;
	    		case BeginFor :
	    					this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"BeginFor\n");
	    					this.tabLevel++;
	    					curLine++;
	    					break;
	    		case EndFor :
	    					this.tabLevel--;
	    					this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"EndFor\n");
	    					curLine++;
	    					break;
	    		case While : 
	    					String textWhile = this.analyser.getTextExp(item.getExpressions());
	    					this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"While ( "+textWhile+" )\n");
	    					curLine++;
	    					break;
	    		case BeginWhile :
	    					this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"BeginWhile\n");
	    					this.tabLevel++;
	    					curLine++;
	    					break;
	    		case EndWhile :
			    			this.tabLevel--;		
			    			this.algoText.append(Integer.toString(curLine)+"."+getTab(this.tabLevel)+"EndWhile\n");
	    					curLine++;
	    					break;
	    		}
    		}
	    		
    	}
    	this.algoText.append(Integer.toString(curLine)+"."+" Fin");
    	
        // r1 est un rectangle contenant le caractère où le curseur
        // est actuellement positionné :
    	//this.algoText.setCaretPosition(10);
    	/*Graphics g = this.algoText.getGraphics();
    	this.algoText.paint(g);
    	Graphics2D g2 = (Graphics2D) g;
		// r1 est un rectangle contenant le caractère où le curseur
		// est actuellement positionné :
		Rectangle2D r1 =new Rectangle2D.Double(0,0,100,10);
		// on en 12déduit la zone à surligner, c'est-à-dire 
		// toute la ligne :
		Rectangle2D.Double highlightZone = new Rectangle2D.Double(
		        0, 0, 100,10);
		// préparation du surlignement :
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, TRANSPARENCY_LEVEL));
		g2.setColor(HIGHLIGHT_COLOR);
		// on surligne la ligne :
		g2.fill(highlightZone);*/
    	
    }
    public String getTab(int pTabLev)
    {
    	String tab ="";
		for(int i = 1;i<=pTabLev*5;i++ )
			{	
				tab +=" ";
			}
		if(curLine>=10)
			tab = tab.substring(0,tab.length()-2);
		return tab;
    }
    public static void main(String arg[])
    // petmet de vider la zone de l'algorithme
    {
    	try {
			new AlgoEditor();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
    Enregistre le dessin au format SVG.
    */
    public void saveDocument()
    throws TransformerException, IOException
	{
	    if (this.curAlg!=null)
	    {
	     	File f = new File(this.curAlg.getAlgoPath());
	    	this.translateCodeToXml();
	   
		    //Document doc = panel.buildDocument();
		    Transformer t = TransformerFactory
		    .newInstance().newTransformer();
		    
		    t.transform(new DOMSource(this.currentDoc),
		    new StreamResult(new FileOutputStream(f)));
		    this.btnSaveSC.setEnabled(false);
		    this.curAlg.setSaved(true);
	    }
    }
    /**
     * Preparer le document pour la sauvegarde
     * 
     */
    public Element getElement(Element pRoot, String pName)
    {
    	//Element root = currentDoc.getDocumentElement();
		NodeList children = pRoot.getChildNodes();
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			//Tous les fils
			if (child instanceof Element && child.getNodeName()==pName)
			{
				//Tous les éléments de la solution
				return (Element)child;	
			}
		}
		return null;
    }
    public void translateCodeToXml()
    {
    	this.rootAlgo.setAttribute("nom",this.curAlg.getAlgoName());
    	this.rootAlgo.setAttribute("evaluation",this.curAlg.getAlgoEvaluation());
    	this.rootAlgo.setAttribute("exercice",this.curAlg.getAlgoExercise());
    	Element decDel = this.getElement(this.rootAlgo, "Dec");
    	Element bodyDel = this.getElement(this.rootAlgo, "Body");
    	Element tasksDel = this.getElement(this.rootAlgo, "Tasks");
    	Element constsDel = this.getElement(this.rootAlgo, "Constraints");
    	if(decDel != null)
    		this.rootAlgo.removeChild(decDel);
    	if(bodyDel != null)
    		this.rootAlgo.removeChild(bodyDel);
    	if(tasksDel != null)
    		this.rootAlgo.removeChild(tasksDel);
    	if(constsDel != null)
    		this.rootAlgo.removeChild(constsDel);
    	
    	//this.currentDoc.
    	// les variables 
    	if(this.curAlg.getAlgoVars().size()>0)
    	{
    		Element decElt = this.currentDoc.createElement("Dec");
    		Iterator<Variable> itVar = this.curAlg.getAlgoVars().iterator();
    		while(itVar.hasNext())
    		{
    			Variable curVar = itVar.next();
    			Element curVarElt = this.currentDoc.createElement("Var");
    			curVarElt.setAttribute("nom",curVar.getName());
    			curVarElt.setAttribute("type",curVar.getType());
    			curVarElt.setAttribute("taille",curVar.getTaille());
    			//***********************************************************************************************
    			decElt.appendChild(curVarElt);
    			this.rootAlgo.appendChild(decElt);
    		}
    	}
    	// Les instructions
    	if(this.curAlg.getAlgoLines().size()>0)
    	{
    		ArrayList<Instruction> listeInsts = this.curAlg.getAlgoLines();
    		Element instsElt = this.currentDoc.createElement("Body");
    		Node parent = instsElt;
    		int numLig = 0;
    		while(numLig < listeInsts.size())
    		{
    			Instruction curInst = listeInsts.get(numLig);
    			String typeNode = curInst.getNoeud().getNodeName();
    			if(typeNode.equals("Read")||typeNode.equals("Write")||typeNode.equals("Affectation")||typeNode.equals("Comment"))
    			{
    				instsElt.appendChild(curInst.getNoeud());
    				numLig++;
    			}
    			else 
    				if (typeNode.equals("If")||typeNode.equals("For")||typeNode.equals("While"))
    				{
    					parent.appendChild(curInst.getNoeud());
    					int ligParc = numLig+1;
    					Node nd = listeInsts.get(ligParc).getNoeud();
    					parent = curInst.getNoeud();
    					parent.appendChild(nd);
    					ligParc++;
    					int level = 1;
    					while(level>0)
    					{
    						Instruction InstParc = listeInsts.get(ligParc);
    						String typeNodeParc = InstParc.getNoeud().getNodeName();
    						if(typeNodeParc.equals("Read")||typeNodeParc.equals("Write")||typeNodeParc.equals("Affectation")||typeNodeParc.equals("Comment"))
    		    			{
    							parent.appendChild(InstParc.getNoeud());
    							ligParc++;
    		    			}
    						else 
    							if (typeNodeParc.equals("EndIf"))
    							{
    								if(parent.getNodeName().equals("Else"))
    									parent = parent.getParentNode();
    								parent.appendChild(InstParc.getNoeud());
    								parent = parent.getParentNode();
    								level--;
    								ligParc++;
    							}
    							else 
    								if(typeNodeParc.equals("EndFor")||typeNodeParc.equals("EndWhile"))
    								{
        								parent.appendChild(InstParc.getNoeud());
        								parent = parent.getParentNode();
        								level--;
        								ligParc++;
    								}
	    							else 
	        							if (typeNodeParc.equals("Else"))
	        							{
	        								parent.appendChild(InstParc.getNoeud());
	        								parent = InstParc.getNoeud();
	        								ligParc++;
	        							}
        							else 
            							if (typeNodeParc.equals("If")||typeNodeParc.equals("For")||typeNodeParc.equals("While"))
            							{
            								parent.appendChild(InstParc.getNoeud());
            								parent = InstParc.getNoeud();
            								ligParc++;;
            								parent.appendChild(listeInsts.get(ligParc).getNoeud());
            								level++;
            								ligParc++;
            							}
    						
    					}
    					numLig = ligParc;
    					//instsElt.appendChild(parent);
    				}
	    			/*else
	    			{
	    				int ligParc = numLig+1;
	    			}*/
    			this.rootAlgo.appendChild(instsElt);
    		}
    		
    	}
    	// Les taches
    	if(this.curAlg.getAlgoTasks().size()>0)
    	{
    		Element tsksElt = this.currentDoc.createElement("Tasks");
    		Iterator<Task> itTsks = this.curAlg.getAlgoTasks().iterator();
    		while(itTsks.hasNext())
    		{
    			Task curTsk = itTsks.next();
    			Element curTskElt = this.currentDoc.createElement("Task");
    			curTskElt.setAttribute("Name",curTsk.getName());
    			curTskElt.setAttribute("Type",curTsk.getType());
    			curTskElt.setAttribute("Mere",curTsk.getMother());
    			curTskElt.setAttribute("Note",curTsk.getNote());
    			curTskElt.setAttribute("Deb",curTsk.getDeb());
    			curTskElt.setAttribute("Fin",curTsk.getFin());
    			//***********************************************************************************************
    			tsksElt.appendChild(curTskElt);
    			this.rootAlgo.appendChild(tsksElt);
    		}
    	}
    	
    	// Les Contraintes
    	if(this.curAlg.getAlgoConsts().size()>0)
    	{
    		Element ctrsElt = this.currentDoc.createElement("Constraints");
    		Iterator<Constraint> itCtrs = this.curAlg.getAlgoConsts().iterator();
    		while(itCtrs.hasNext())
    		{
    			Constraint curCtr = itCtrs.next();
    			Element curCtrElt = this.currentDoc.createElement("Constraint");
    			curCtrElt.setAttribute("Type",curCtr.getType());
    			curCtrElt.setAttribute("NumLig",curCtr.getNumLig());
    			curCtrElt.setAttribute("TaskName",curCtr.getTaskName());
    			curCtrElt.setAttribute("Penalite",curCtr.getPenalite());
    			curCtrElt.setAttribute("Src",curCtr.getSource());
    			curCtrElt.setAttribute("Trg",curCtr.getTarget());
    			if(curCtr.getType().equals("8"))
    			{
    				Iterator<Expression> it = curCtr.getExps().iterator();
    				while (it.hasNext())
    				{
    					Expression curExp = it.next();
    					Element expElt = this.currentDoc.createElement("exp");
    					expElt.setAttribute("name",curExp.getName());
    					expElt.setAttribute("left",curExp.getLeft());
    					expElt.setAttribute("right",curExp.getRight());
    					expElt.setAttribute("op",curExp.getOp());
    					expElt.setAttribute("rangLeft",curExp.getRangLeft());
    					expElt.setAttribute("rangRight",curExp.getRangRight());
    					curCtrElt.appendChild(expElt);
    				}
    			}
    			//***********************************************************************************************
    			ctrsElt.appendChild(curCtrElt);
    			this.rootAlgo.appendChild(ctrsElt);
    		}
    	}
    	
    }
    public void evaluateCurSolution()
	{
		if(this.curAlg != null)
			{
			ArrayList<String> liste_Chemins_Modeles = new  ArrayList<String>();
			//liste_Chemins_Modeles.add("others\\evaluation_Test_28_11_2013\\Modeles\\modele1.xml");
			liste_Chemins_Modeles.add("others\\evaluation_Test_28_11_2013\\Modeles\\modele2.xml");
			liste_Chemins_Modeles.add("others\\evaluation_Test_28_11_2013\\Modeles\\modele3.xml");
			//liste_Chemins_Modeles.add("others\\evaluation_Test_28_11_2013\\Modeles\\modele4.xml");
			liste_Chemins_Modeles.add("others\\evaluation_Test_28_11_2013\\Modeles\\modele5.xml");
			//liste_Chemins_Modeles.add("others\\evaluation_Test_28_11_2013\\Modeles\\modele6.xml");
			liste_Chemins_Modeles.add("others\\evaluation_Test_28_11_2013\\Modeles\\modele7.xml");
			//liste_Chemins_Modeles.add("others\\evaluation_Test_28_11_2013\\Modeles\\modele8.xml");
			//liste_Chemins_Modeles.add("others\\evaluation_Test_28_11_2013\\Modeles\\modele9.xml");
			liste_Chemins_Modeles.add("others\\evaluation_Test_28_11_2013\\Modeles\\modele10.xml");
			//
			//consecuditf.setListe_Modeles(liste_Chemins_Modeles);
			this.analyser.chargeModels(liste_Chemins_Modeles);
				this.analyser.reinitEvals();
				this.analyser.evaluateMePlease(this.curAlg.getAlgoPath());
				if(this.analyser.evaluations.get(0).isEtat())
					this.resEval.setText("Reconnue");
				else
					this.resEval.setText("Non Reconnue");
			}
	}
    public void addInst(Element pParent,ArrayList<Instruction> pInsts)
    {
    	/*
    	int numLig = 0;
		while(numLig < pInsts.size())
		{
			Instruction curTsk = pInsts.get(numLig);
			String typeNode = curTsk.getNoeud().getNodeName();
			if(typeNode.equals("Read")||typeNode.equals("Write")||typeNode.equals("Affectation"))
			{
				
			}
			
			//***********************************************************************************************
			tsksElt.appendChild(curTskElt);
		}*/
    }
    public void deleteInst()
    {
    	if(this.curLine > 1 && this.curLine < this.curAlg.getAlgoVars().size()+2)
		{
    		// Une variable
    		//System.out.println("Suppression de Variable");
    		int posVar = this.curLine-2;
    		this.curAlg.getAlgoVars().remove(posVar);
    		
		}
    	else
    	{
    		// Une instruction
    		//System.out.println("Suppression d'une instruction");
    		int posInst = this.curLine-(this.curAlg.getAlgoVars().size()+3);
    		int ligParc  = posInst;
			Instruction curInst = this.curAlg.getAlgoLines().get(posInst);
			String instType = curInst.getNoeud().getNodeName().trim();
			// les instructions simples
			if (instType.equals("Read")||instType.equals("Affectation")||instType.equals("Write"))//||!instType.equals("Comment"))
			{
				this.curAlg.getAlgoLines().remove(posInst);
				refreshAlgoText();
				
				
				this.btnSaveSC.setEnabled(true);
				this.curAlg.setSaved(false);
			}
			// les if , for, and while
			else
				if (instType.equals("If")||instType.equals("For")||instType.equals("While"))
				{
					//this.curAlg.getAlgoLines().remove(posInst);
					ligParc = posInst+1;
					//this.curAlg.getAlgoLines().remove(ligParc); // Supp de l'inst debut
					ligParc++;
					int level = 1;
					while(level>0)
					{
						Instruction InstParc = this.curAlg.getAlgoLines().get(ligParc);
						String typeNodeParc = InstParc.getNoeud().getNodeName();
						if(typeNodeParc.equals("Read")||typeNodeParc.equals("Write")||typeNodeParc.equals("Affectation")||typeNodeParc.equals("Comment"))
		    			{
							//this.curAlg.getAlgoLines().remove(ligParc);
							ligParc++;
		    			}
						else 
							if (typeNodeParc.equals("EndIf"))
							{
								//this.curAlg.getAlgoLines().remove(ligParc);
								level--;
								ligParc++;
							}
							else 
								if(typeNodeParc.equals("EndFor")||typeNodeParc.equals("EndWhile"))
								{
									//this.curAlg.getAlgoLines().remove(ligParc);
    								level--;
    								ligParc++;
								}
    							else 
        							if (typeNodeParc.equals("Else"))
        							{
        								//this.curAlg.getAlgoLines().remove(ligParc);
        								ligParc++;
        							}
    							else 
        							if (typeNodeParc.equals("If")||typeNodeParc.equals("For")||typeNodeParc.equals("While"))
        							{
        								//this.curAlg.getAlgoLines().remove(ligParc);
        								ligParc++;;
        								//this.curAlg.getAlgoLines().remove(ligParc);
        								level++;
        								ligParc++;
        							}
						
					}
					//numLig = ligParc;
					//instsElt.appendChild(parent);
				}
			//System.out.println("Deb de Suppression : "+posInst);
			//System.out.println("Fin de Suppression : "+Integer.toString(ligParc-1));
			for(int i = posInst;i<ligParc;i++)
			{
			
				this.curAlg.getAlgoLines().remove(posInst);
			}
			
    	}
    	this.curLine--;
    	this.refreshAlgoText();
		this.btnSaveSC.setEnabled(true);
		this.curAlg.setSaved(false);
    }
    public void updateInst()
    {
    	// la mise à jour
    	if(this.curLine > 1 && this.curLine < this.curAlg.getAlgoVars().size()+2)
		{
    		// modification d'une variable
    		int posVar = this.curLine-2; // position de la variable
    		this.decFrm.typeOuv = 2;
    		this.decFrm.posVar = posVar;
    		this.decFrm.varACharger = this.curAlg.getAlgoVars().get(posVar);
    		this.decFrm.initialize();
			this.decFrm.setVisible(true);
			this.mainFrame.setEnabled(false);
		}
    	else
    	{
    		// modification d'une instrcution
    		int posInst = this.curLine-(this.curAlg.getAlgoVars().size()+3);
    		int ligParc  = posInst;
    		Instruction curInst = this.curAlg.getAlgoLines().get(posInst);
			String instType = curInst.getNoeud().getNodeName().trim();
			if(instType.equals("Read"))
			{
				this.readFrm.typeOuv = 2;
	    		this.readFrm.posInst = posInst;
	    		this.readFrm.instACharger = this.curAlg.getAlgoLines().get(posInst);
	    		this.readFrm.initialize();
				this.readFrm.setVisible(true);
				this.mainFrame.setEnabled(false);
			}
			else 
				if(instType.equals("Write"))
				{
					this.writeFrm.typeOuv = 2;
		    		this.writeFrm.posInst = posInst;
		    		this.writeFrm.instACharger = this.curAlg.getAlgoLines().get(posInst);
		    		this.writeFrm.initialize();
					this.writeFrm.setVisible(true);
					this.mainFrame.setEnabled(false);
				}
				else 
					if(instType.equals("Affectation"))
					{
						this.affecttionFrm.typeOuv = 2;
			    		this.affecttionFrm.posInst = posInst;
			    		this.affecttionFrm.instACharger = this.curAlg.getAlgoLines().get(posInst);
			    		this.affecttionFrm.initialize();
						this.affecttionFrm.setVisible(true);
						this.mainFrame.setEnabled(false);
					}
					else 
						if(instType.equals("If"))
						{
							this.condFrm.typeOuv = 2;
				    		this.condFrm.posInst = posInst;
				    		this.condFrm.instACharger = this.curAlg.getAlgoLines().get(posInst);
				    		this.condFrm.initialize();
							this.condFrm.setVisible(true);
							this.mainFrame.setEnabled(false);
						}
						else 
							if(instType.equals("For")||instType.equals("While"))
							{
								this.boucleFrm.typeOuv = 2;
					    		this.boucleFrm.posInst = posInst;
					    		this.boucleFrm.instACharger = this.curAlg.getAlgoLines().get(posInst);
					    		this.boucleFrm.initialize();
								this.boucleFrm.setVisible(true);
								this.mainFrame.setEnabled(false);
							}
			
    	}
    	
    }
   
    public DocumentBuilder builder;
    public Document currentDoc;
    public Element rootAlgo;
	//private Document currentSolDoc;
	public File currentAlgorithm;
	//public String curAlgoPath;
	public Algorithm curAlg = null;
    public JMenuBar menuBar ;
    public JToolBar toolBar;
    public JMenu fileMenu ;
    public JMenuItem newItem ;
    public JMenuItem openItem ;
    public JMenuItem quitItem ;
    public JMenu editMenu ;
    public JMenu helpMenu ;    
    public EmptyFrame mainFrame;
    public JPanel mainPanel;
    public JPanel eastPanel;
    public Box westPanel;
    public Box northPanel;
    
    public JPanel southPanel;
    
    public JPanel consolPanel;
    public JPanel topPanel;
    public JPanel downPanel;
    public AlgoPanel algoPanel;
    JScrollPane algoPane;
    JScrollPane consolPane;
    public textModel algoText;
    public consolModel consolText;
    //****************
    public frmDeclaration decFrm;
    public frmAffectation affecttionFrm;
    public frmRead readFrm;
    public frmWrite writeFrm;
    public frmCondition condFrm;
    public frmBoucle boucleFrm;
    public  NewAlgorithm NewAlgorithmFrm;
    public  frmTache tacheFrm;
    public  frmContrainte contrainteFrm;
    public  condVersOFrm frmCondVersO;
    //************************
    public Element curInst;
    public int curLine = 2;
    public int tabLevel = 1;
    //**************************************
    public ModelAnalyser analyser;
    private  final float TRANSPARENCY_LEVEL = 0.6f;
    private  final Color HIGHLIGHT_COLOR = new Color(51, 153, 255);
    private static final int TEXT_COMPONENT_WIDTH = 400;
    private static final int TEXT_COMPONENT_HEIGHT = 250;
    public enum keyWord { 	//les mots cles
		Algorithme, Var, Nombre, Const,tableauNombres, tableauCaract, Booleen, Debut, Fin, Lire, Ecrire, Si, Sinon,DebutSi,
		FinSi, Pour, DebutPour, FinPour,Tantque,DebutTq, FinTq, Faire;
	}
    public enum instTypes_fr { 	//les mots cles
		Lire, Ecrire, Si, Sinon,DebutSi,
		FinSi, Pour, DebutPour, FinPour,Tantque,DebutTq, FinTq;
	}
    public enum instTypes_en { 	//les mots cles
		Read, Write,Affectation, If, Else,BeginIf,
		EndIf, For, BeginFor, EndFor,While,BeginWhile, EndWhile;
	}
    // fctFrm;
    //frmDeclaration commentFrm;
    public JButton btnNewSC;
    public JButton btnlancer;
    public JButton btnarret;
    public JButton btnSaveSC ;
    public ButtonGroup groupInst; 
    public JButton btnRecognitionSC ;
    //******************************
    public JButton btnDec;
    public JButton btnAffect;
    public JButton btnLire;
    public JButton btnEcrire;
    public JButton btnCond;
    public JButton btnBoucle;
    public JButton btnFction;
    public JButton btnComment;
    public JButton btnMod;
    public JButton btnSupp;
    
    public JButton btnTask;
    public JButton btnConst;
    public JButton btnCondVersO;
    private String ecrire = "";
    private String readvalue;
    private ArrayList<String>ecrire_message;
    private ArrayList<String>read_valeur;
    private int index;
    private int number;
    
    public JLabel resEval;
    
    
}
