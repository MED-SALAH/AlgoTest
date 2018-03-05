package editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.xml.parsers.ParserConfigurationException;

import meiters.TacheModele;

import org.xml.sax.SAXException;

import recognizer.AlgoRecognizer;
import recognizer.Evaluation;
import recognizer.Exercice;
import recognizer.Groupe;
import recognizer.ModelAnalyser;
import recognizer.SolutionEvaluation;
import recognizer.Variable;



public class AlgoTest extends EmptyFrame {
	public AlgoTest() throws ParserConfigurationException, SAXException, IOException
	{
		AlgoTestFrame = new EmptyFrame();
		AlgoTestFrame = new EmptyFrame();
		AlgoTestFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		// centrer le cadre au milieu de l’écran
		AlgoTestFrame.setSize(screenWidth - (screenWidth / 4), screenHeight- (screenHeight / 4));
		AlgoTestFrame.setLocation(screenWidth / 8, screenHeight / 8);
		AlgoTestFrame.setTitle("AlgoTest : Outil d'evaluation automatique des apprenants en algorithmique.");
		initializeTeacher();
		this.reconnaisseur = new ModelAnalyser();
		AlgoTestFrame.setVisible(true);
	}
	/**
	 * Permet d'initialiser la fenetre principal 
	 * pour une un enseignant
	 * @param arg
	 */
	public void initializeTeacher()
	{
		// Barre de menu
    	menuBar = new JMenuBar();
    	// Barre d'outils
    	toolBar = new JToolBar();
    	// Menus
    	evaluationMenu = new JMenu("Evaluations");
    	exerciseMenu = new JMenu("Exercices");
    	studientMenu = new JMenu("Etudiants");
    	classMenu = new JMenu("Salles d'evaluation");
    	helpMenu = new JMenu("Aide");
    	
    	firstTest = new JButton("Lancer le Test");
    	firstTest.setIcon(new ImageIcon("images\\recognition.png"));
    	firstTest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AlgoTest.this.doFirstTest();
			}
		});
    	firstTest.setToolTipText("Lancer le Test");
    	// Bouton de la barre d'outil
    	btnEvalutions = new JButton();
    	btnEvalutions.setIcon(new ImageIcon("images\\quiz.png"));
    	/*btnEvalutions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AlgoTest.this.doFirstTest();
			}
		});*/
    	btnEvalutions.setToolTipText("Evaluations");
    	btnExercices = new JButton();
    	btnExercices.setIcon(new ImageIcon("images\\Exercices.png"));
    	btnExercices.setToolTipText("Exercices");
    	btnStudients = new JButton();
    	btnStudients.setIcon(new ImageIcon("images\\studients.png"));
    	btnStudients.setToolTipText("Etudiants");
    	btnClass = new JButton();
    	btnClass.setIcon(new ImageIcon("images\\class.png"));
    	btnClass.setToolTipText("Salles");
    	//************************
    	itemPrepareEval = new JMenuItem("Préparer une évaluation");
    	itemListeEval = new JMenuItem("liste des évaluations");
    	itemStat = new JMenuItem("Statistique");
    	
    	evaluationMenu.add(itemPrepareEval);
    	evaluationMenu.add(itemListeEval);
    	evaluationMenu.addSeparator();
    	evaluationMenu.add(itemStat);
    	
    	menuBar.add(evaluationMenu);
    	
        //----------------------------------------------------------
    	
    	//**************************************************
    	itemAddExercise = new JMenuItem("Ajouter un  exercice");
        itemListeExercises = new JMenuItem("Liste des exercices");
        itemAddSolModel = new JMenuItem("Ajouter un modèle");
        itemListeSolModel = new JMenuItem("Liste des modèles");
    	
        
        exerciseMenu.add(itemAddExercise);
        exerciseMenu.add(itemListeExercises);
        exerciseMenu.addSeparator();
        exerciseMenu.add(itemAddSolModel);
        exerciseMenu.add(itemListeSolModel);
        
        menuBar.add(exerciseMenu);
    	//----------------------------------------------------------
    	//******************************************
    	itemAddStud = new JMenuItem("Ajouter un  étudiant");
        itemListeStud = new JMenuItem("Liste des étudiants");
        
        studientMenu.add(itemAddStud);
        studientMenu.add(itemListeStud);
        
        menuBar.add(studientMenu);
    	//---------------------------------------------------------------
        itemAddClass = new JMenuItem("Ajouter une  salle");
        itemListeClass = new JMenuItem("Liste des salles");
        itemAddDispoClass = new JMenuItem("Disponibilité des salles");
        
        classMenu.add(itemAddClass);
        classMenu.add(itemListeClass);
        classMenu.addSeparator();
        classMenu.add(itemAddDispoClass);
    	menuBar.add(classMenu);
    	//**********************************
    	
    	//------------------------------------------------------------------
    	
    	//*************************************
    	itemContenu = new JMenuItem("Contenu");
    	itemAbout = new JMenuItem("A propos");
    	itemAbout.addActionListener(new
    			ActionListener()
    			{
    			public void actionPerformed(ActionEvent event)
    			{
	    			if (dialog == null) // première fois
	    			dialog = new About(AlgoTest.this);
	    			dialog.setVisible(true); // boîte de dialogue
    			}
    			});
         helpMenu.add(itemContenu);
         helpMenu.addSeparator();
         helpMenu.add(itemAbout);
         
         menuBar.add(helpMenu);
    	
    	//menuBar.add(evaluationMenu);
    	toolBar.add(btnEvalutions);
    	toolBar.add(btnExercices);
    	toolBar.add(btnStudients);
    	toolBar.add(btnClass);
    	//
    	this.Res_Panel = new JPanel(new BorderLayout());
    	this.Res_Model = new ModeleTableResultats();
		this.Res_table = new JTable(this.Res_Model);
		this.Res_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.Res_Pane = new JScrollPane(this.Res_table);
		//
		this.filtreGrp = new JComboBox(new String[]{"Tous les groupes ","groupe G1"," groupe G2","groupe G3","groupe G5"});
		this.nbreTotal = new JTextField(4);
		//this.nbreTotal.setSize(new Dimension(20,10));
		this.nbreTotal.setEditable(false);
		this.nbreTotal.setText("0");
		
		this.nbreRecon = new JTextField(4);
		this.nbreRecon.setEditable(false);
		this.nbreRecon.setText("0");
		
		this.nbreNonRecon = new JTextField(4);
		this.nbreNonRecon.setEditable(false);
		this.nbreNonRecon.setText("0");
		
    	AlgoTestFrame.setJMenuBar(menuBar);
    	JPanel filtrePanel = new JPanel();
    	JPanel countPanel = new JPanel();
    	
    	filtrePanel.add(new JLabel("Groupe : "),FlowLayout.LEFT);
    	filtrePanel.add(this.filtreGrp);
    	filtrePanel.add(firstTest);
    	
    	countPanel.add(new JLabel("Nbre Total : "),FlowLayout.LEFT);
    	countPanel.add(this.nbreTotal);
    	countPanel.add(new JLabel("Nbre Sols Reconnue : "));
    	countPanel.add(this.nbreRecon);
    	countPanel.add(new JLabel("Nbre Sols Non Reconnue : "));
    	countPanel.add(this.nbreNonRecon);
    	AlgoTestFrame.add(toolBar, BorderLayout.NORTH);
    	this.Res_Panel.add(filtrePanel,BorderLayout.NORTH);
    	this.Res_Panel.add(Res_Pane,BorderLayout.CENTER);
    	this.Res_Panel.add(countPanel,BorderLayout.SOUTH);
    	AlgoTestFrame.add(Res_Panel, BorderLayout.CENTER);
		
	}
	/**
	 * Permet d'initialiser la fenetre principal 
	 * pour une un enseignant
	 * @param arg
	 */
	public void initializeStudent()
	{
		
	}
	/**
	 * Cette methode sera utilisée beaucoup plus pour le test
	 * @param arg
	 */
	public void doFirstTest()
	{
		this.testEvaluation = new Evaluation("Eval_01_2013","Test");
		this.chargeExo();
		this.testEvaluation.setListe_Exos(this.testExos);
		this.chargeGroupes();
		this.testEvaluation.setListe_Groupes(this.testGroupes);
		this.LancerEvaluatO(this.testEvaluation);
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(0).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(0).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(1).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(1).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(2).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(2).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(3).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(3).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(4).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(4).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(5).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(5).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(6).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(6).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(7).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(7).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(8).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(8).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(9).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(9).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(10).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(10).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(11).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(11).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(12).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(12).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(13).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(13).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(14).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(14).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(15).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(15).isEtat());
		/*System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(16).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(16).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(17).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(17).isEtat());
		System.out.print("nor : "+this.testGroupes.get(2).getEvals().get(18).getName());
		System.out.println("Etat : "+this.testGroupes.get(2).getEvals().get(18).isEtat());
		/*System.out.print("nor : "+this.testGroupes.get(0).getEvals().get(19).getName());
		System.out.println("Etat : "+this.testGroupes.get(0).getEvals().get(19).isEtat());
		System.out.print("nor : "+this.testGroupes.get(0).getEvals().get(20).getName());
		System.out.println("Etat : "+this.testGroupes.get(0).getEvals().get(20).isEtat());
		System.out.print("nor : "+this.testGroupes.get(0).getEvals().get(21).getName());
		System.out.println("Etat : "+this.testGroupes.get(0).getEvals().get(21).isEtat());
		System.out.print("nor : "+this.testGroupes.get(0).getEvals().get(22).getName());
		System.out.println("Etat : "+this.testGroupes.get(0).getEvals().get(22).isEtat());
		System.out.print("nor : "+this.testGroupes.get(0).getEvals().get(23).getName());
		System.out.println("Etat : "+this.testGroupes.get(0).getEvals().get(23).isEtat());
		*/
		
		System.out.println("Done!.");
	}
	public void LancerEvaluatO(Evaluation pEval)
	{
		
		switch (this.filtreGrp.getSelectedIndex()) {
		case 0 : 
			
			ArrayList<Groupe> pGps = pEval.getListe_Groupes();
			Iterator<Groupe> it = pGps.iterator();
			while(it.hasNext())
			{
				Groupe item = it.next();
				this.evaluateGroupe(item);
				//System.out.println("Groupe "+item.getNomGroupe()+ ":"+item.getNb_Sol_Rec()+" sols reconnues, et "+item.getNb_Sol_NonRec()+" sols reconnues.");	
			}
			
			chargerResEval();
			
			break;

		default:
			System.out.println(this.testGroupes.get(this.filtreGrp.getSelectedIndex()-1).getNomGroupe());
			this.nbreTotal.setText("0");
			this.nbreRecon.setText("0");
			this.nbreNonRecon.setText("0");
			if(this.Res_Model.getRowCount()>0)
			{
				this.Res_Model.fireTableRowsDeleted(0, this.Res_Model.getRowCount()-1);
			}
				
			this.evaluateGroupe(this.testGroupes.get(this.filtreGrp.getSelectedIndex()-1));
			if(this.Res_Model.getRowCount()>0)
			{
				int n = this.Res_Model.getRowCount();
				for (int i=n-1 ; i>=0 ; --i) this.Res_Model.removeEval(i);
				//this.Res_Model.fireTableRowsDeleted(0, this.Res_Model.getRowCount()-1);
			}
			chargerEvalGrp(this.testGroupes.get(this.filtreGrp.getSelectedIndex()-1));
			break;
		}
		
		//System.out.println(this.nbreTotal.getValue());
	}
	
	public void chargerResEval()
	{
		// Vider la table
		this.nbreTotal.setText("0");
		this.nbreRecon.setText("0");
		this.nbreNonRecon.setText("0");
		if(this.Res_Model.getRowCount()>0)
		{
			int n = this.Res_Model.getRowCount();
			for (int i=n-1 ; i>=0 ; --i) this.Res_Model.removeEval(i);
			//this.Res_Model.fireTableRowsDeleted(0, this.Res_Model.getRowCount()-1);
		}
		
		// charger le resultat de tous les groupes
		Iterator<Groupe> it = testEvaluation.getListe_Groupes().iterator();
		while(it.hasNext())
		{
			Groupe item = it.next();
			chargerEvalGrp(item);
		}
	}
	public void chargerEvalGrp(Groupe pGrp)
	{
		
		Iterator<SolutionEvaluation> it = pGrp.getEvals().iterator();
		int nbreTotal = 0;
		int nbreRec = 0;
		int nbreNonRec = 0;
		while(it.hasNext())
		{
			SolutionEvaluation item = it.next();
			this.Res_Model.addEval(item);
			nbreTotal++;
			if(item.isEtat())
				nbreRec++;
			else
				nbreNonRec++;
			//this.Res_table.setRowSelectionInterval(this.Res_Model.getRowCount()-1, this.Res_Model.getRowCount()-1);
		}
		nbreTotal += Integer.parseInt(this.nbreTotal.getText());
		nbreRec += Integer.parseInt(this.nbreRecon.getText());
		nbreNonRec += Integer.parseInt(this.nbreNonRecon.getText());
		this.nbreTotal.setText(Integer.toString(nbreTotal));
		this.nbreRecon.setText(Integer.toString(nbreRec));
		this.nbreNonRecon.setText(Integer.toString(nbreNonRec));
		
	}
	public void evaluateGroupe(Groupe pGp)
	{
		this.reconnaisseur.reinitEvals();
		
		Iterator<String> it = pGp.getListe_Sol().iterator();
		while(it.hasNext())
		{
			String item = it.next();
			//System.out.println("Groupe "+pGp.getNomGroupe());
			this.reconnaisseur.evaluateMePlease(item);
		}
		pGp.setEvals(this.reconnaisseur.evaluations);
	}
	/**
	 * Charger l'exo du test
	 * @param arg
	 */
	public  void listerFiles(String pDirectory,ArrayList<String> pArrLst)
	// permet de charger tous les fichiers  : modeles ou solutions 
	// par programmation
	{
		try
		{
			File pathName = new File(pDirectory);
			String[] fileNames = pathName.list();
			
			// énumérer tous les fichiers du répertoire
			for (int i = 0; i < fileNames.length; i++)
			{
				File f = new File(pathName.getPath(),fileNames[i]);
				// si le fichier est à nouveau un répertoire, appeler
				// la méthode main de manière récurrente
				if (f.isDirectory())
				{
					//System.out.println(f.getCanonicalPath());
					String sunFile = f.getPath();
					listerFiles(sunFile,pArrLst);
				}
				if (f.isFile() && f.getCanonicalPath().endsWith("xml"))
				{
					//System.out.println(f.getCanonicalPath().replaceAll("\\", "\\\\"));
					pArrLst.add(f.getCanonicalPath());System.out.println("1");
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	} 
	public void chargeExo()
	{
		this.testExos = new ArrayList<Exercice>();
		Exercice consecuditf = new Exercice();
		consecuditf.setNomExo("Elements_Consectifs");
		consecuditf.setNiveauExo("Moyen"); // pareil plusieurs niveaux
		consecuditf.setThemeExo("Les tableaux"); // par la suite ça sera plusieurs themes qui representront des mots clés
		consecuditf.setCheminExo("");
		//
		ArrayList<String> liste_Chemins_Modeles = new  ArrayList<String>();
		listerFiles("others\\evaluation_Test_28_11_2013\\Modeles",liste_Chemins_Modeles);
		/*
		liste_Chemins_Modeles.add("C:\\workspace\\afcepf\\AlgoTest\\others\\evaluation_Test_28_11_2013\\Modeles\\modele1.xml");
		liste_Chemins_Modeles.add("C:\\workspace\\afcepf\\AlgoTest\\others\\evaluation_Test_28_11_2013\\Modeles\\modele2.xml");
		liste_Chemins_Modeles.add("C:\\workspace\\afcepf\\AlgoTest\\others\\evaluation_Test_28_11_2013\\Modeles\\modele3.xml");
		liste_Chemins_Modeles.add("C:\\workspace\\afcepf\\AlgoTest\\others\\evaluation_Test_28_11_2013\\Modeles\\modele4.xml");
		liste_Chemins_Modeles.add("C:\\workspace\\afcepf\\AlgoTest\\others\\evaluation_Test_28_11_2013\\Modeles\\modele5.xml");
		liste_Chemins_Modeles.add("C:\\workspace\\afcepf\\AlgoTest\\others\\evaluation_Test_28_11_2013\\Modeles\\modele6.xml");
		liste_Chemins_Modeles.add("C:\\workspace\\afcepf\\AlgoTest\\others\\evaluation_Test_28_11_2013\\Modeles\\modele7.xml");
		liste_Chemins_Modeles.add("C:\\workspace\\afcepf\\AlgoTest\\others\\evaluation_Test_28_11_2013\\Modeles\\modele8.xml");
		liste_Chemins_Modeles.add("C:\\workspace\\afcepf\\AlgoTest\\others\\evaluation_Test_28_11_2013\\Modeles\\modele9.xml");
		liste_Chemins_Modeles.add("C:\\workspace\\afcepf\\AlgoTest\\others\\evaluation_Test_28_11_2013\\Modeles\\modele10.xml");
		liste_Chemins_Modeles.add("C:\\workspace\\afcepf\\AlgoTest\\others\\evaluation_Test_28_11_2013\\Modeles\\modele11.xml");
		*/
		Iterator <String> it = liste_Chemins_Modeles.iterator(); 
		while (it.hasNext())
		{
			String item = (String) it.next();
			 System.out.println(item);
			
		}
		consecuditf.setListe_Modeles(liste_Chemins_Modeles);
		this.reconnaisseur.chargeModels(liste_Chemins_Modeles);
		this.testExos.add(consecuditf);
		//
		
	}
	/**
	 * Charger les groupes de test
	 * @param arg
	 */
	public void chargeGroupes()
	{
		this.testGroupes = new ArrayList<Groupe>();
		Groupe g5 = new Groupe();
		g5.setNomGroupe("G5");
		ArrayList<String> liste_Sols = new ArrayList<String>();
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\DEBIB_AbdElHakim.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\GHELBI_Ramzi.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\MANAA_Ouissam.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\RAHMANI_Amir.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\HADJIRA_AbdElMalek.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\ATTABI_Selma.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\BEDAIDIA_Amina.xml");
		//liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\TABCHICHE_Meriem.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\CHEKROUD_Walid.xml");
		//liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\BAALOUL_Khawla.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\IDRICI_Kouloud.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\BENALIA_Sara.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\CHELGHOUM_Sahar.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\GHORAB_Amira.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\HABCHI_Imene.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\DRIS_Med.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\BELOUCH_Hasna.xml");
		//liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\DJEDOUANI_Kheira.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\BOURAS_Imen.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\TAIBAOUI_MedMokhtar.xml");
		//liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\BENHAMED_SifEddine.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\GHENDIRE_Souhail.xml");
		//liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\MAMMERIE_Khadija.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G5\\ABDELAZIZ_Soraya.xml");
		g5.setListe_Sol(liste_Sols);
		
		//*********************
		Groupe g3 = new Groupe();
		g3.setNomGroupe("G3");
		liste_Sols = new ArrayList<String>();
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\AZARA_Abir.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\BENOUADFEL_Massinissa.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\MARIR_Moncef.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\ZIDANE_Khaled.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\BAKIR_Hichem.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\KECHOUD_Imane.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\GUETTAF_FatimaZohra.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\TIOUCHE_0ussama.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\BOUDERSA_AbdErrahman.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\RIGHI_Ines.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\DRIS_SaraAnfel.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\KHANFRI_Zakaria.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\MESSINI_Meriem.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\BENNARA_Ziyad.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\GUITTOUM_Sara.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\OULDAHMED_FAOUZI.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\BELAID_Redouane.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G3\\HADFI_Khouloud.xml");
		g3.setListe_Sol(liste_Sols);
		
		//**************************************************
		Groupe g2 = new Groupe();
		g2.setNomGroupe("G2");
		liste_Sols = new ArrayList<String>();
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\BOUDJET_Ayoub.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\DADOU_AbdEssalem.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\BOUZAOUIT_Yassine.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\DIB_Amine.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\DJEGHEB_HoussemEddinel.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\HAMIMED_Wissem.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\HAMZA_Messaoud.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\KHATER_MohammedAmine.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\KHELIF_AbdElHakim.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\KRINE_Karima.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\MECHERI_Djalel.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\MOHAMADI_Hichem.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\OUITES_Naziha.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\SAMAI_Meriem.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\SEDDIKI_HoussemEddine.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G2\\SOUAYAH_MohamedElMlajed.xml");
		g2.setListe_Sol(liste_Sols);
		
		//********************
		//**************************************************
		Groupe g1 = new Groupe();
		g1.setNomGroupe("G1");
		liste_Sols = new ArrayList<String>();
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\AMOUCHE_Manel.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\BAHACHE_Zakarya.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\BENTERKI_AbdEnnour.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\BOUABOUA_AbdElMoumen.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\BOUSSAFEL_Hacene.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\BRAKNI_Bouthaina.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\DJADIDL_Idir.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\HASSAD_Ramzi.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\KHINOUCHE_Yaakoubi.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\Laidoudi_Aicha.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\MANSOURI_TaharAyoubi.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\MEDJOUBI_Karim.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\MELIANI_Kalifa.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\MESSAOUDI_MedBachir.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\MIHOUB_Keltoum.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\NASRI_Amani.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\SAAIDIA_MustaphaSouheil.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\ZAMOUCHE_Lyes.xml");
		liste_Sols.add("others\\evaluation_Test_28_11_2013\\Solutions\\G1\\ZAOUALI_Khaoulai.xml");
		
		g1.setListe_Sol(liste_Sols);
		this.testGroupes.add(g1);
		this.testGroupes.add(g2);
		this.testGroupes.add(g3);
		this.testGroupes.add(g5);
	}
	public static void main(String arg[]) throws ParserConfigurationException, SAXException, IOException
	{
		new AlgoTest();
	}
	
	EmptyFrame AlgoTestFrame;
	// MenuBar et ToolBar
	public JMenuBar menuBar ;
    public JToolBar toolBar;
    // Menus
    public JMenu evaluationMenu ;
    public JMenu exerciseMenu ;
    public JMenu studientMenu ;
    public JMenu classMenu ;
    public JMenu paramMenu ;
    public JMenu helpMenu ;
    //Options des menus
    public JMenuItem itemPrepareEval;
    public JMenuItem itemListeEval;
    public JMenuItem itemStat;
    //***********************
    public JMenuItem itemAddExercise;
    public JMenuItem itemListeExercises;
    public JMenuItem itemAddSolModel;
    public JMenuItem itemListeSolModel;
    //************************************
    public JMenuItem itemAddStud;
    public JMenuItem itemListeStud;
    //public JMenuItem itemAddSolModel;

    //************************
    public JMenuItem itemAddClass;
    public JMenuItem itemListeClass;
    public JMenuItem itemAddDispoClass;
    //*******************************
    public JMenuItem itemContenu;
    public JMenuItem itemAbout;
    

    
    
    
    // Btns de la barre d'outil
    public JButton btnEvalutions;
    public JButton btnExercices ; 
    public JButton btnStudients ;
    public JButton btnClass ;
    public ButtonGroup groupInst;
    private About dialog;
    public JButton firstTest;
    //
    private Evaluation testEvaluation;
    private ArrayList<Exercice> testExos;
    private ArrayList<Groupe> testGroupes;
    //*************
    public ModelAnalyser reconnaisseur;
    public JComboBox filtreGrp;
    public JTextField nbreTotal;
    public JTextField nbreRecon;
    public JTextField nbreNonRecon;
    //
    public JPanel Res_Panel;
    public JTable Res_table;
	public JScrollPane Res_Pane;
	public ModeleTableResultats Res_Model;
}
