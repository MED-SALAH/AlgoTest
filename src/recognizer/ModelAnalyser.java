package recognizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.activation.FileTypeMap;
import javax.swing.DebugGraphics;
import javax.swing.ListModel;
import javax.swing.text.DefaultEditorKit.CutAction;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.css.Rect;
import org.xml.sax.SAXException;
import basics.entierCouter;

public class ModelAnalyser {
	//Cette classe permet de faire l'analyse
	//C'est à dire la comparaison de la solution avec
	//tous les modèles
	public ModelAnalyser() throws ParserConfigurationException, SAXException, IOException
	/* Constructeur
	 * */
	{
		modelsPath =  new ArrayList<String>();
		//variables =  new ArrayList<Variable>();
		models = new ArrayList<Model>();
		solutionsPath =  new ArrayList<String>();
		//variables =  new ArrayList<Variable>();
		evaluations = new ArrayList<SolutionEvaluation>();
		if (builder == null)
		{
			DocumentBuilderFactory factory
			= DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
		}
		
		chargeModels(modelsPath);
		chargeSolutions(solutionsPath);
		
	}
	public void listerFiles(String pDirectory, ExtensionFilter extFilter)
	{
		try
		{
			File pathName = new File(pDirectory);
			String[] fileNames;
			if (extFilter == null)
				fileNames = pathName.list(); 
			else
				fileNames = pathName.list(extFilter); 
			// énumérer tous les fichiers du répertoire
			for (int i = 0; i < fileNames.length; i++)
			{
				File f = new File(pathName.getPath(),fileNames[i]);
				// si le fichier est à nouveau un répertoire, appeler
				// la méthode main de manière récurrente
				if (f.isDirectory())
				{
					System.out.println(f.getCanonicalPath());
					String sunFile = f.getPath();
					listerFiles(sunFile, new ExtensionFilter("xml"));
				}
				if (f.isFile())
				{
					System.out.println(f.getCanonicalPath());
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	} 
	public void reinitEvals()
	{
		evaluations = new ArrayList<SolutionEvaluation>();
		
	}
	public void reinitModelsPaths()
	{
		modelsPath =  new ArrayList<String>();
	}
	public void chargeModels(ArrayList<String> pModelsPath)
	// Initialisation
	{
		// Par la suit il serait possible de les charger à partir d'un autre emplacement
		pModelsPath.add("C:\\AMOUCHE_Manel.xml");
		pModelsPath.add("C:\\Users\\saleh\\Desktop\\G1\\BAHACHE_Zakarya.xml");
		pModelsPath.add("C:\\Users\\saleh\\Desktop\\G1\\BENTERKI_AbdEnnour.xml");
		pModelsPath.add("C:\\Users\\saleh\\Desktop\\G1\\BOUABOUA_AbdElMoumen.xml");
		pModelsPath.add("C:\\Users\\saleh\\Desktop\\G1\\BOUSSAFEL_Hacene.xml");
		pModelsPath.add("C:\\Users\\saleh\\Desktop\\G1\\BRAKNI_Bouthaina.xml");
		pModelsPath.add("C:\\Users\\saleh\\Desktop\\G1\\DJADIDL_Idir.xml");
		pModelsPath.add("C:\\Users\\saleh\\Desktop\\G1\\exempleTes.xml");
		pModelsPath.add("C:\\Users\\saleh\\Desktop\\G1\\HASSAD_Ramzi.xml");
		
		Iterator <String> paths = pModelsPath.iterator(); 
		while (paths.hasNext())
		{
			 String item =  (paths.next());
			 chargeModel(models,item);
		}
		currentFile = new File(models.get(0).getPath());
		try {
			currentDoc = builder.parse(currentFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*Element root = currentDoc.getDocumentElement();
		Instruction myInst = new Instruction();
		myInst.setNum(1);
		System.out.println(myInst.getNum());
		getInsctruction(root, myInst, Integer.parseInt("8"));
		//aff
		afficherNodeInsctruction(myInst.getNoeud());*/
		
	}
	public void chargeSolutions(ArrayList<String> pSolutionsPath)
	// Initialisation
	{
		// Par la suit il serait possible de les charger à partir d'un autre emplacement
		
		pSolutionsPath.add("C:\\AMOUCHE_Manel.xml");
		/*pSolutionsPath.add("C:\\Users\\SALEH\\Desktop\\G1\\BAHACHE_Zakarya.xml");
		  pSolutionsPath.add("C:\\Users\\SALEH\\Desktop\\G1\\BENTERKI_AbdEnnour.xml");
		  pSolutionsPath.add("C:\\Users\\SALEH\\Desktop\\G1\\BOUABOUA_AbdElMoumen.xml");
		  pSolutionsPath.add("C:\\Users\\SALEH\\Desktop\\G1\\BOUSSAFEL_Hacene.xml");
		  pSolutionsPath.add("C:\\Users\\SALEH\\Desktop\\G1\\BRAKNI_Bouthaina.xml");
		  pSolutionsPath.add("C:\\Users\\SALEH\\Desktop\\G1\\DJADIDL_Idir.xml");
		  pSolutionsPath.add("C:\\Users\\SALEH\\Desktop\\G1\\exempleTes.xml");
		  pSolutionsPath.add("C:\\Users\\SALEH\\Desktop\\G1\\HASSAD_Ramzi.xml");*/
		
		Iterator <String> paths = pSolutionsPath.iterator(); 
		while (paths.hasNext())
		{
			 String item =  (paths.next());
			 chargeModel(models,item);
		}
	}
	public void chargeModel(ArrayList<Model> pModels, String pModelPath)
	// Permet de charger tous les modèles
	{
		Model mod = new Model();
		mod.setPath(pModelPath);
		mod.setName(getModelName(pModelPath));
		// String sourcPath = private String sourcePath; txt au lieu de xml
		//System.out.println(pModelPath.substring(0,pModelPath.indexOf("xml"))+"txt");
		mod.setSourcePath(pModelPath.substring(0,pModelPath.indexOf("xml"))+"txt");
		//afficherModelSrcCode(mod.getSourcePath());
		mod.setVariables(getVarNames(pModelPath));
		mod.setTasks(getTasks(pModelPath));
		//if(mod.getName().equals("modele 9"))
		//	afficherTasks(mod.getTasks());
		mod.setConstraints(getConstraints(pModelPath));
		mod.setExpressions(getExpressions(pModelPath));
		//mod.setExpressions(getCriticalLines(pModelPath, pTaskName)(pModelPath));
		//afficherExpressions(mod.getExpressions());
		
		models.add(mod);
	}
	public ArrayList<Task> getTasks(String pModelPath)
	{	
		ArrayList<Task> taches = new ArrayList<Task>();
		currentFile = new File(pModelPath);
		try {
			currentDoc = builder.parse(currentFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = currentDoc.getDocumentElement();
		NodeList children = root.getChildNodes();
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			//Tous les fils
			if (child instanceof Element && child.getNodeName()=="Tasks")
			{
				//Tous les éléments de la solution
				Element childElement = (Element)child;	
				//System.out.println(child.getNodeName());
				//Tous les fils
				NodeList tasks = childElement.getChildNodes(); 
				for (int j = 0; j < tasks.getLength(); j++)
				{
					Node currentVar = tasks.item(j);
					if (currentVar instanceof Element)
					{
						Element childElement2 = (Element)currentVar;
						//System.out.println("	"+currentVar.getNodeName());
						//Tous les attributs
						NamedNodeMap attributes = childElement2.getAttributes();
						Task task = new Task();
						String Name = "";
						String Type = "";
						String Mother = "";
						String Note = "";
						String Deb ="";
						String Fin = "";
						for (int k  = 0; k < attributes.getLength(); k++)
						{
							Node attribute = attributes.item(k);
							String name = attribute.getNodeName();
							String value = attribute.getNodeValue();
							if (name=="Name")
							{
								Name = value;
								//task.setName(value);
							}
							else 
								if (name=="Type")
								{
									Type = value;
									//task.setType(value);
								}
							else 
								if (name=="Mere")
								{
									Mother = value;
									//task.setMother(value);
								}
							else 
								if (name=="Note")
								{
									Note = value;
									//task.setNote(value);
								}
							else 
								if (name=="Deb")
								{
									Deb = value;
									//task.setDeb(value);
								}
							else 
								if (name=="Fin")
								{
									Fin = value;
									//task.setFin(value);
								}
						}
								
						task.setName(Name);task.setType(Type);task.setMother(Mother);task.setNote(Note);task.setDeb(Deb);task.setFin(Fin);
						task.setCriticalLines(getCriticalLines(pModelPath, task.getName()));
						taches.add(task);
						//System.out.println(task.getName());
						//ArrayList<String> CLS = getCriticalLines(pModelPath,task.getName());
						//task.setCriticalLines(CLS);
						//afficherCL(task.getCriticalLines());

						//System.out.println(k+" "+task.getName());
					}
					
				}
			}
			//else 
			//	System.out.println("I m here !");	
		}
		//afficherTasks(taches);
		return taches;
	}
	
	public ArrayList<Task> getTasks(Element root)
	{	
		ArrayList<Task> taches = new ArrayList<Task>();
		NodeList children = root.getChildNodes();
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			//Tous les fils
			if (child instanceof Element && child.getNodeName()=="Tasks")
			{
				//Tous les éléments de la solution
				Element childElement = (Element)child;	
				//System.out.println(child.getNodeName());
				//Tous les fils
				NodeList tasks = childElement.getChildNodes(); 
				for (int j = 0; j < tasks.getLength(); j++)
				{
					Node currentVar = tasks.item(j);
					if (currentVar instanceof Element)
					{
						Element childElement2 = (Element)currentVar;
						//System.out.println("	"+currentVar.getNodeName());
						//Tous les attributs
						NamedNodeMap attributes = childElement2.getAttributes();
						Task task = new Task();
						String Name = "";
						String Type = "";
						String Mother = "";
						String Note = "";
						String Deb ="";
						String Fin = "";
						for (int k  = 0; k < attributes.getLength(); k++)
						{
							Node attribute = attributes.item(k);
							String name = attribute.getNodeName();
							String value = attribute.getNodeValue();
							if (name=="Name")
							{
								Name = value;
								//task.setName(value);
							}
							else 
								if (name=="Type")
								{
									Type = value;
									//task.setType(value);
								}
							else 
								if (name=="Mere")
								{
									Mother = value;
									//task.setMother(value);
								}
							else 
								if (name=="Note")
								{
									Note = value;
									//task.setNote(value);
								}
							else 
								if (name=="Deb")
								{
									Deb = value;
									//task.setDeb(value);
								}
							else 
								if (name=="Fin")
								{
									Fin = value;
									//task.setFin(value);
								}
						}
								
						task.setName(Name);task.setType(Type);task.setMother(Mother);task.setNote(Note);task.setDeb(Deb);task.setFin(Fin);
						task.setCriticalLines(getCriticalLines(root, task.getName()));
						taches.add(task);
						//System.out.println(task.getName());
						//ArrayList<String> CLS = getCriticalLines(pModelPath,task.getName());
						//task.setCriticalLines(CLS);
						//afficherCL(task.getCriticalLines());

						//System.out.println(k+" "+task.getName());
					}
					
				}
			}
			//else 
			//	System.out.println("I m here !");	
		}
		//afficherTasks(taches);
		return taches;
	}
	public ArrayList<Constraint> getConstraints(String pModelPath)
	{	
		ArrayList<Constraint> constraints = new ArrayList<Constraint>();
		currentFile = new File(pModelPath);
		try {
			currentDoc = builder.parse(currentFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = currentDoc.getDocumentElement();
		NodeList children = root.getChildNodes();
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			//Tous les fils
			if (child instanceof Element && child.getNodeName()=="Constraints")
			{
				//Tous les éléments de la solution
				Element childElement = (Element)child;	
				//System.out.println(child.getNodeName());
				//Tous les fils
				NodeList constrs = childElement.getChildNodes(); 
				for (int j = 0; j < constrs.getLength(); j++)
				{
					Node currentCtr = constrs.item(j);
					if (currentCtr instanceof Element)
					{
						Element childElement2 = (Element)currentCtr;
						//System.out.println("	"+currentVar.getNodeName());
						//Tous les attributs
						NamedNodeMap attributes = childElement2.getAttributes();
						Constraint ctr = new Constraint();
						String Type = "";
						String NumLig = "";
						String TaskName = "";
						String Penalite = "";
						String Src ="";
						String Trg = "";
						for (int k  = 0; k < attributes.getLength(); k++)
						{
							Node attribute = attributes.item(k);
							String name = attribute.getNodeName();
							String value = attribute.getNodeValue();
							//System.out.println(name+" "+value);
							if (name=="Type")
							{
								Type = value;
								//task.setName(value);
							}
							else 
								if (name=="NumLig")
								{
									NumLig = value;
									//task.setType(value);
								}
							else 
								if (name=="TaskName")
								{
									TaskName = value;
									//task.setMother(value);
								}
							else 
								if (name=="Penalite")
								{
									Penalite = value;
									//task.setNote(value);
								}
							else 
								if (name=="Src")
								{
									Src = value;
									//task.setDeb(value);
								}
							else 
								if (name=="Trg")
								{
									Trg = value;
									//System.out.println(Trg);
									//task.setFin(value);
								}
						}
						//System.out.println(Type+" : "+NumLig+" : "+TaskName+" : "+Penalite+" "+Src+" : "+Trg);
						if (! Type.equals("1"))
							{
								//System.out.println("Here"+Trg);
								ctr.setType(Type);ctr.setNumLig(NumLig);ctr.setTaskName(TaskName);ctr.setPenalite(Penalite);ctr.setSource(Src);ctr.setTarget(Trg);
								constraints.add(ctr);
								if(Type.equals("8"))
									chargeCtrExp(currentCtr,ctr);
							}
						
					}
					
				}
			}
		}
			//else 
			//	System.out.println("I m here !");	
		//afficherConstraints(constraints);
		return constraints;
	}
	public ArrayList<Constraint> getConstraints(Element root)
	{	
		ArrayList<Constraint> constraints = new ArrayList<Constraint>();
		NodeList children = root.getChildNodes();
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			//Tous les fils
			if (child instanceof Element && child.getNodeName()=="Constraints")
			{
				//Tous les éléments de la solution
				Element childElement = (Element)child;	
				//System.out.println(child.getNodeName());
				//Tous les fils
				NodeList constrs = childElement.getChildNodes(); 
				for (int j = 0; j < constrs.getLength(); j++)
				{
					Node currentCtr = constrs.item(j);
					if (currentCtr instanceof Element)
					{
						Element childElement2 = (Element)currentCtr;
						//System.out.println("	"+currentVar.getNodeName());
						//Tous les attributs
						NamedNodeMap attributes = childElement2.getAttributes();
						Constraint ctr = new Constraint();
						String Type = "";
						String NumLig = "";
						String TaskName = "";
						String Penalite = "";
						String Src ="";
						String Trg = "";
						for (int k  = 0; k < attributes.getLength(); k++)
						{
							Node attribute = attributes.item(k);
							String name = attribute.getNodeName();
							String value = attribute.getNodeValue();
							//System.out.println(name+" "+value);
							if (name=="Type")
							{
								Type = value;
								//task.setName(value);
							}
							else 
								if (name=="NumLig")
								{
									NumLig = value;
									//task.setType(value);
								}
							else 
								if (name=="TaskName")
								{
									TaskName = value;
									//task.setMother(value);
								}
							else 
								if (name=="Penalite")
								{
									Penalite = value;
									//task.setNote(value);
								}
							else 
								if (name=="Src")
								{
									Src = value;
									//task.setDeb(value);
								}
							else 
								if (name=="Trg")
								{
									Trg = value;
									//System.out.println(Trg);
									//task.setFin(value);
								}
						}
						//System.out.println(Type+" : "+NumLig+" : "+TaskName+" : "+Penalite+" "+Src+" : "+Trg);
						if (! Type.equals("1"))
							{
								//System.out.println("Here"+Trg);
								ctr.setType(Type);ctr.setNumLig(NumLig);ctr.setTaskName(TaskName);ctr.setPenalite(Penalite);ctr.setSource(Src);ctr.setTarget(Trg);
								constraints.add(ctr);
								if(Type.equals("8"))
									chargeCtrExp(currentCtr,ctr);
							}
						
						
					}
					
				}
			}
		}
			//else 
			//	System.out.println("I m here !");	
		//afficherConstraints(constraints);
		return constraints;
	}
	public ArrayList<Expression> getExpressions(String pModelPath)
	{	
		ArrayList<Expression> expressions = new ArrayList<Expression>();
		currentFile = new File(pModelPath);
		try {
			currentDoc = builder.parse(currentFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = currentDoc.getDocumentElement();
		NodeList children = root.getChildNodes();
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			//Tous les fils
			if (child instanceof Element && child.getNodeName()=="Expressions")
			{
				//Tous les éléments de la solution
				Element childElement = (Element)child;	
				//System.out.println(child.getNodeName());
				//Tous les fils
				NodeList constrs = childElement.getChildNodes(); 
				for (int j = 0; j < constrs.getLength(); j++)
				{
					Node currentCtr = constrs.item(j);
					if (currentCtr instanceof Element)
					{
						Element childElement2 = (Element)currentCtr;
						//System.out.println("	"+currentVar.getNodeName());
						//Tous les attributs
						NamedNodeMap attributes = childElement2.getAttributes();
						Expression ctr = new Expression();
						String nameExp = "";
						String left = "";
						String right = "";
						String op = "";
						String rangLeft ="";
						String rangRight = "";
						for (int k  = 0; k < attributes.getLength(); k++)
						{
							Node attribute = attributes.item(k);
							String name = attribute.getNodeName();
							String value = attribute.getNodeValue();
							//System.out.println(name+" "+value);
							if (name=="name")
							{
								nameExp = value;
								//task.setName(value);
							}
							else 
								if (name=="left")
								{
									left = value;
									//task.setType(value);
								}
							else 
								if (name=="right")
								{
									right = value;
									//task.setMother(value);
								}
							else 
								if (name=="op")
								{
									op = value;
									//task.setNote(value);
								}
							else 
								if (name=="rangLeft")
								{
									rangLeft = value;
									//task.setDeb(value);
								}
							else 
								if (name=="rangRight")
								{
									rangRight = value;
									//System.out.println(Trg);
									//task.setFin(value);
								}
						}
						//System.out.println(Type+" : "+NumLig+" : "+TaskName+" : "+Penalite+" "+Src+" : "+Trg);
						ctr.setName(nameExp);ctr.setLeft(left);ctr.setRight(right);ctr.setOp(op);ctr.setRangLeft(rangLeft);ctr.setRangRight(rangRight);
						expressions.add(ctr);					
					}
					
				}
			}
		}
			//else 
			//	System.out.println("I m here !");	
		//afficherConstraints(constraints);
		return expressions;
	}
	public void chargeCtrExp(Node pCtrNode, Constraint pCtr)
	{
		pCtr.setExps(new ArrayList<Expression>());
		NodeList childrenExp = pCtrNode.getChildNodes();
		NamedNodeMap exprs;
		
		for (int j = 0; j < childrenExp.getLength(); j++)
		{
			
			Node exp = childrenExp.item(j);
			if(exp instanceof Element)
			{
				NamedNodeMap curExpAtt = exp.getAttributes();
				Expression ctr = new Expression();
				String nameExp = "";
				String left = "";
				String right = "";
				String op = "";
				String rangLeft ="";
				String rangRight = "";
				for (int k  = 0; k < curExpAtt.getLength(); k++)
				{
					Node attribute = curExpAtt.item(k);
					String name = attribute.getNodeName();
					String value = attribute.getNodeValue();
					//System.out.println(name+" "+value);
					if (name=="name")
					{
						nameExp = value;
						//task.setName(value);
					}
					else 
						if (name=="left")
						{
							left = value;
							//task.setType(value);
						}
					else 
						if (name=="right")
						{
							right = value;
							//task.setMother(value);
						}
					else 
						if (name=="op")
						{
							op = value;
							//task.setNote(value);
						}
					else 
						if (name=="rangLeft")
						{
							rangLeft = value;
							//task.setDeb(value);
						}
					else 
						if (name=="rangRight")
						{
							rangRight = value;
							//System.out.println(Trg);
							//task.setFin(value);
						}
					//NodeExrpressions.add();	
				}
				ctr.setName(nameExp);ctr.setLeft(left);ctr.setRight(right);ctr.setOp(op);ctr.setRangLeft(rangLeft);ctr.setRangRight(rangRight);
				pCtr.getExps().add(ctr);
			}
		}
	}
	public ArrayList<String> getCriticalLines(String pModelPath,String pTaskName)
	// Charage Les lignes critiques d'un modele
	{	
		ArrayList<String> criticalLines = new ArrayList<String>();
		currentFile = new File(pModelPath);
		try {
			currentDoc = builder.parse(currentFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print("Here.....");
		//System.out.println(pTaskName);
		Element root = currentDoc.getDocumentElement();
		NodeList children = root.getChildNodes();
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			//Tous les fils
			if (child instanceof Element && child.getNodeName()=="Constraints")
			{
				//Tous les éléments de la solution
				Element childElement = (Element)child;	
				//System.out.println(child.getNodeName());
				//Tous les fils
				NodeList CLs = childElement.getChildNodes(); 
				for (int j = 0; j < CLs.getLength(); j++)
				{
					Node currentCL = CLs.item(j);
					if (currentCL instanceof Element)
					{
						Element childElement2 = (Element)currentCL;
						//System.out.println("	"+currentCL.getNodeName());
						//Tous les attributs
						NamedNodeMap attributes = childElement2.getAttributes();
						String type = ""  ;
						String taskName = "";
						String NumLig  = "";
						for (int k  = 0; k < attributes.getLength(); k++)
						{
							Node attribute = attributes.item(k);
							String name = attribute.getNodeName();
							String value = attribute.getNodeValue();
							
							if (name=="TaskName")
							{
								taskName = value;
							}
							else 
								if (name=="NumLig")
								{
										NumLig = value;
								}
							else 
								if (name=="Type")
								{
									type = value;
								}
						}
						//System.out.println(pTaskName+"**"+taskName+" "+type+ " "+NumLig);
						if (type.equals("1") && pTaskName.equals(taskName) )
							criticalLines.add(NumLig);
							//System.out.println(taskName+" "+type+ " "+NumLig);
					}
					
				}
			}
			//else 
			//	System.out.println("I m here !");	
		}
		//afficherCL(criticalLines);
		return criticalLines;
	}
	public ArrayList<String> getCriticalLines(Element root,String pTaskName)
	// Charage Les lignes critiques d'un modele
	{	
		ArrayList<String> criticalLines = new ArrayList<String>();
		NodeList children = root.getChildNodes();
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			//Tous les fils
			if (child instanceof Element && child.getNodeName()=="Constraints")
			{
				//Tous les éléments de la solution
				Element childElement = (Element)child;	
				//System.out.println(child.getNodeName());
				//Tous les fils
				NodeList CLs = childElement.getChildNodes(); 
				for (int j = 0; j < CLs.getLength(); j++)
				{
					Node currentCL = CLs.item(j);
					if (currentCL instanceof Element)
					{
						Element childElement2 = (Element)currentCL;
						//System.out.println("	"+currentCL.getNodeName());
						//Tous les attributs
						NamedNodeMap attributes = childElement2.getAttributes();
						String type = ""  ;
						String taskName = "";
						String NumLig  = "";
						for (int k  = 0; k < attributes.getLength(); k++)
						{
							Node attribute = attributes.item(k);
							String name = attribute.getNodeName();
							String value = attribute.getNodeValue();
							
							if (name=="TaskName")
							{
								taskName = value;
							}
							else 
								if (name=="NumLig")
								{
										NumLig = value;
								}
							else 
								if (name=="Type")
								{
									type = value;
								}
						}
						//System.out.println(pTaskName+"**"+taskName+" "+type+ " "+NumLig);
						if (type.equals("1") && pTaskName.equals(taskName) )
							criticalLines.add(NumLig);
							//System.out.println(taskName+" "+type+ " "+NumLig);
					}
					
				}
			}
			//else 
			//	System.out.println("I m here !");	
		}
		//afficherCL(criticalLines);
		return criticalLines;
	}
	public String getModelName(String pModelPath)
	{
		String modelName="";
		currentFile = new File(pModelPath);
		try {
			currentDoc = builder.parse(currentFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = currentDoc.getDocumentElement();
		NamedNodeMap attributes = root.getAttributes();
		for (int k  = 0; k < attributes.getLength(); k++)
		{
			Node attribute = attributes.item(k);
			String name = attribute.getNodeName();
			String value = attribute.getNodeValue();
			if (name == "nom")
			{
				modelName = value;
			}			
		}
		return modelName;
	}
	public ArrayList<Variable> getVarNames(String pModelPath)
	// Charage Les lignes critiques d'un modele
	{	
		currentFile = new File(pModelPath);
		ArrayList<Variable> variables = new ArrayList<Variable>();
		try {
			currentDoc = builder.parse(currentFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = currentDoc.getDocumentElement();
		NodeList children = root.getChildNodes();
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			//Tous les fils
			if (child instanceof Element && child.getNodeName()=="Dec")
			{
				//Tous les éléments de la solution
				Element childElement = (Element)child;	
				//System.out.println(child.getNodeName());
				//Tous les fils
				NodeList vars = childElement.getChildNodes(); 
				for (int j = 0; j < vars.getLength(); j++)
				{
					Node currentVar = vars.item(j);
					if (currentVar instanceof Element)
					{
						Element childElement2 = (Element)currentVar;
						//System.out.println("	"+currentVar.getNodeName());
						//Tous les attributs
						NamedNodeMap attributes = childElement2.getAttributes();
						Variable var = new Variable();
						String nameVar="";
						String typeVar="";
						String tailleVar="";
						for (int k  = 0; k < attributes.getLength(); k++)
						{
							Node attribute = attributes.item(k);
							String name = attribute.getNodeName();
							String value = attribute.getNodeValue();
							
							if (name=="nom")
							{
								nameVar = value;
							}
							else 
								if (name=="type")
								{
									//var.setName(name);
									typeVar = value;
								}
								else 
									if (name=="taille")
									{
										//var.setName(name);
										tailleVar = value;
									}
							
						}
						var.setName(nameVar);
						var.setType(typeVar);
						var.setTaille(tailleVar);
						variables.add(var);
					}
					
				}
			}
			//else 
			//	System.out.println("I m here !");	
		}
		//afficherVar();
		return variables;
	}
	public ArrayList<Variable> getVarNames(Element pRoot)
	// Charage Les lignes critiques d'un modele
	{	
		NodeList children = pRoot.getChildNodes();
		ArrayList<Variable> variables = new ArrayList<Variable>();
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			//Tous les fils
			if (child instanceof Element && child.getNodeName()=="Dec")
			{
				//Tous les éléments de la solution
				Element childElement = (Element)child;	
				//System.out.println(child.getNodeName());
				//Tous les fils
				NodeList vars = childElement.getChildNodes(); 
				for (int j = 0; j < vars.getLength(); j++)
				{
					Node currentVar = vars.item(j);
					if (currentVar instanceof Element)
					{
						Element childElement2 = (Element)currentVar;
						//System.out.println("	"+currentVar.getNodeName());
						//Tous les attributs
						NamedNodeMap attributes = childElement2.getAttributes();
						Variable var = new Variable();
						String nameVar="";
						String typeVar="";
						String tailleVar="";
						for (int k  = 0; k < attributes.getLength(); k++)
						{
							Node attribute = attributes.item(k);
							String name = attribute.getNodeName();
							String value = attribute.getNodeValue();
							
							if (name=="nom")
							{
								nameVar = value;
							}
							else 
								if (name=="type")
								{
									//var.setName(name);
									typeVar = value;
								}
								else 
									if (name=="taille")
									{
										//var.setName(name);
										tailleVar = value;
									}
							
						}
						var.setName(nameVar);
						var.setType(typeVar);
						var.setTaille(tailleVar);
						variables.add(var);
					}
					
				}
			}
			//else 
			//	System.out.println("I m here !");	
		}
		//afficherVar();
		return variables;
	}
	public void afficherModelSrcCode(String pModelPath)
	{
			// permet d'afficher le code source du modèle
		try 
		{
			modelSourceCode = new FileReader(pModelPath);
			modelTampon = new BufferedReader(modelSourceCode);
			while (true) 
			{
				// Lit une ligne de scores.txt
				String ligne = modelTampon.readLine();
				// Vérifie la fin de fichier
				if (ligne == null)
					break;
				System.out.println(ligne);
			} // Fin du while
		} catch (IOException exception) 
				{
						exception.printStackTrace();
				} 
		finally {
			try {
				modelTampon.close();
					modelSourceCode.close();
					} catch(IOException exception1) {
													exception1.printStackTrace();
													}
		}
	}
	public void afficherVar(ArrayList<Variable>pVar)
	{
		Iterator <Variable> it = pVar.iterator(); 
		while (it.hasNext())
		{
			 Variable item = (Variable) it.next();
			 System.out.println(item.getName()+" : "+item.getType());
			
		}
	}
	public void afficherTasks(ArrayList<Task> pTaches)
	{
		Iterator <Task> it = pTaches.iterator(); 
		while (it.hasNext())
		{
			Task item = (Task) it.next();
			 System.out.println("Name : "+item.getName()+" Type : "+item.getType()+" Mother : "+item.getMother()+" Note : "+item.getNote()+" Deb : "+item.getDeb()+" fin : "+item.getFin());
			 System.out.println("les lignes critque de cette tache :");
			 afficherCL(item.getCriticalLines());
		}
	}
	public void afficherCL(ArrayList<String> pCL)
	{
		Iterator <String> it = pCL.iterator();
		
		while (it.hasNext())
		{
			String item =  it.next();
			System.out.println (item+"  ");
		}
	}
	public void afficherConstraints(ArrayList<Constraint> pConstraints)
	{
		Iterator <Constraint> it = pConstraints.iterator(); 
		while (it.hasNext())
		{
			Constraint item = (Constraint) it.next();
			 System.out.println(item.getType()+" : "+item.getNumLig()+" : "+item.getTaskName()+" : "+item.getPenalite()+" "+item.getSource()+" : "+item.getTarget());

		}
	}
	public void afficherExpressions(ArrayList<Expression> pExpressions)
	{
		if (pExpressions != null)
		{
			Iterator <Expression> it = pExpressions.iterator(); 
			while (it.hasNext())
			{
				Expression item = (Expression) it.next();
				 System.out.println(item.getName()+" : "+item.getLeft()+" : "+item.getRight()+" : "+item.getOp()+" "+item.getRangLeft()+" : "+item.getRangRight());

			}
		}
		else
			System.out.println("**Sorry it's empty**");
				
	}
	public void evaluateMePlease(String pSolutionPath)
	{			
		reinitModelsStat(models);
		
		// Responsable de l'evaluation
		SolutionEvaluation curEval = new SolutionEvaluation();
		// Preparation d'une evaluation
		curEval.setSourcePath(pSolutionPath);
		//curEval.setXmlPath(pSolutionPath.substring(0,pSolutionPath.indexOf("txt"))+"xml");
		curEval.setXmlPath(pSolutionPath);
		currentSolFile = new File(curEval.getXmlPath());
		try {
			currentSolDoc = builder.parse(currentSolFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		curEval.setVariables(getVarNames(curEval.getXmlPath()));
		curEval.setEtat(false);
		curEval.setNote(0);
		curEval.setName(getModelName(curEval.getXmlPath()));
		curEval.setRootSol(currentDoc.getDocumentElement());
		entierCouter nbreInst = new entierCouter();
		getInstrctionNb(curEval.getRootSol(),nbreInst);
		curEval.setNbreInst(nbreInst.getVal());
		chargeSolInstructions(curEval);
		//afficherSolInstructions(curEval);
		// Parcours des modeles
		Iterator<Model> mods = models.iterator();
		while(mods.hasNext())
		{
			Model curMdl = (Model) mods.next();
			Iterator <Variable> it = curMdl.getVariables().iterator(); 
			while (it.hasNext())
			{
				 Variable item = (Variable) it.next();
				 item.setVarClone(getVarClones(item,curEval));
			}
			//Analyse des lignes critiques
			//System.out.println("Modele : "+curMdl.getName());
			isThereCLPlease(curMdl, curEval);
		}
		Model cand = wichOneShoudWeTry(models);
		while(cand !=null && !curEval.isEtat())
		// Just a try
		//tryThisModel(cand,curEval);
		{
			//System.out.println("Why don't u try this one : "+cand.getName());
			tryThisModel(cand, curEval);
			cand.setSeen(true);
			//if (cand.getName().equals("modele 3"))
				//tryThisModel(cand, curEval);
			cand = wichOneShoudWeTry(models);
			
		}
		//System.out.println(curEval);
		evaluations.add(curEval);
		
	}
	public ArrayList<Model> sortModels(ArrayList<Model> pModels,SolutionEvaluation pEvaluation)
	// permet de trier les modeles selon les lignes critiques
	{
		// Le necessaire pour le chargement et le parcour
		
		//Instruction myInst = new Instruction();
		//myInst.setNum(1);
		//System.out.println(myInst.getNum());
		//getInsctruction(root, myInst, 9);
		//afficherNodeInsctruction(myInst.getNoeud());
		// Parcours des modeles 
		Iterator <Model> itModels = pModels.iterator(); 
		while (itModels.hasNext())
		{
			Model itemModel = (Model) itModels.next();
			System.out.println("Modele : "+itemModel.getName());
			System.out.print("	Nb Total Taches : "+itemModel.getTasks().size());
			System.out.print("	Nb Taches Rec : "+itemModel.getNbOfRecTasks());
			System.out.print("	Nb Total Cls : "+itemModel.getTotalNbOfCls());
			System.out.println("	Nb Cls Rec : "+itemModel.getNbOfRecCls());
			//System.out.println("Taches : ");
			//isThereCLPlease(itemModel, pEvaluation);
		}	
		
		return pModels;
	}
	public String getInstructionText(String pNumLig, String pSource)
	// permet de recuper l'instruction qui correspond 
	// au numero donné comme parametre
	{
		String Ligne = "";
		int cpt = 0;
		FileReader sourceReader = null;
		BufferedReader sourceCode = null;
		try 
		{
			sourceReader = new FileReader(pSource);
			sourceCode = new BufferedReader(sourceReader);
			while (true) 
			{
				cpt++;
				// Lit une ligne de scores.txt
				String ligne = sourceCode.readLine();
				// Vérifie la fin de fichier
				if (ligne == null || cpt == Integer.parseInt(pNumLig) )
					{
						Ligne = ligne.trim();
						break;
					}
				//System.out.println(ligne);
			} // Fin du while
		} catch (IOException exception) 
				{
						exception.printStackTrace();
				} 
		finally {
			try {
					sourceCode.close();
					sourceCode.close();
					} catch(IOException exception1) {
													exception1.printStackTrace();
													}
		}
		return Ligne;
		
	}
	public boolean isItThere(Instruction pInstruction, SolutionEvaluation pEvaluation,Model pModel)
	// permet de tester si la ligne critique CL est presente dans 
	// la solution (le code est stocké dans pEvaluation)
	{	
		//System.out.println("Recherch de l'insctruction : ");
		Document solDoc = null;
		File solFile;
		solFile = new File(pEvaluation.getXmlPath());
		try {
			solDoc = builder.parse(solFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = solDoc.getDocumentElement();
		Node instNode = pInstruction.getNoeud();
		ArrayList<Variable> varSolution = pEvaluation.getVariables();
		ArrayList<Variable> varModel = pModel.getVariables();
		ArrayList<Instruction> sameInst = new ArrayList<Instruction>();
		ArrayList<Expression> expInst = new ArrayList<Expression>();
		ArrayList<Expression> expInstSol = new ArrayList<Expression>();
		
		//System.out.println(instNode.getNodeName());
		//afficherNodeInsctruction(instNode);
		if(instNode.getNodeName().equals("Read"))
		{// Il s'agit d'une instruction de lecture
		// il y a deux cas de figures : 1 - value est type simple 2- type tableau
			// dans ce dernier cas : 
			//System.out.println("Lecture: ");
			String valueRead = "";
			String rangeRead = "";
			NamedNodeMap attributes = instNode.getAttributes();
			for (int k  = 0; k < attributes.getLength(); k++)
			{
				Node attribute = attributes.item(k);
				String name = attribute.getNodeName();
				String value = attribute.getNodeValue();
				if (name == "value")
				{
					valueRead = value;
				}			
				else
					if (name == "range")
					{
						rangeRead = value;
					}	
			}
			getAllInsctruction(root, sameInst, "Read");
			
			if (!rangeRead.equals(""))
			{
				Iterator <Instruction> it = sameInst.iterator(); 
				while (it.hasNext())
				{
					Instruction item = (Instruction) it.next();
					//System.out.println(item.getNoeud().getNodeName());
					NamedNodeMap Atts = item.getNoeud().getAttributes();
					for (int k  = 0; k < Atts.getLength(); k++)
					{
						Node curAtt = Atts.item(k);
						String namecurAtt = curAtt.getNodeName();
						String valuecurAtt = curAtt.getNodeValue();
						if (namecurAtt == "range")
							{
								if(!valuecurAtt.equals(""))
									return true;
							}	
					}
	
				}
				return false;
			}
			else 
				if (isItVar(rangeRead,pModel.getVariables()))
				return true; // au moins une seule existe
		}
		else 
			if(instNode.getNodeName().equals("Write"))
			{
				//System.out.println("Ecriture : ");
				getAllInsctruction(root, sameInst, "Write");
				if (sameInst.size()>0)
					return true;
				else 
					return false;
			}
			else 
				if(instNode.getNodeName().equals("If") || instNode.getNodeName().equals("For")|| instNode.getNodeName().equals("While")|| instNode.getNodeName().equals("Affectation"))
				{
					String typeIns = instNode.getNodeName(); 
					//System.out.println("Instruct de tupe ** : "+typeIns);
					String nameExp = "";
					String left = "";
					String right = "";
					String op = "";
					String rangLeft ="";
					String rangRight = "";
					boolean res = false;//
					getAllInsctruction(root, sameInst, typeIns);
					//System.out.println(pInstruction+"**Inst**");
					expInst = pInstruction.getExpressions();
					//afficherExpressions(expInst);
					//System.out.println(expInst.size()+"**sizeExpInst**");
					// les instructions de mm type
					Iterator <Instruction> it = sameInst.iterator(); 
					while (it.hasNext()&& !res)
					{
						Instruction item = (Instruction) it.next();
						//System.out.println(item.getNoeud().getNodeName());
						expInstSol = item.getExpressions(); // les expressions
						if (expInst.size()==expInstSol.size())
							// sinon les conditions sont forcement differents 
						{
							/*NamedNodeMap Atts = item.getNoeud().getAttributes();
							for (int k  = 0; k < Atts.getLength(); k++)
							{
								Node curAtt = Atts.item(k);
								String namecurAtt = curAtt.getNodeName();
								String valuecurAtt = curAtt.getNodeValue();
								// JE PEUX COMMENCER
								if (namecurAtt == "range")
									{
										if(!valuecurAtt.equals(""))
											return true;
									}	
							}*/
							res = areTheseSamesExps(expInst, expInstSol, pModel.getVariables(),pEvaluation.getVariables());
							
							
						}
							
					}
					return res;
				}
		return false;
	}
	public  void getInsctruction(Node pRoot,Instruction pInst,int NumLin )
	// permet de recuerer l'instruction 
	// ligne donnée
	{
		//if (pInst.getNoeud() == null)
		//{
			NodeList children = pRoot.getChildNodes();
			NamedNodeMap attributes;
			for (int i = 0; i < children.getLength(); i++)
			{
				Node child = children.item(i);
				if (child instanceof Element)
				{
						if (pInst.getNum() >= NumLin && pInst.getNoeud()==null)
						{
							//System.out.println("here :"+pRoot.getNodeName()+" Cl : "+pInst.getNum()+" p :"+NumLin);	
							//System.out.println(" res maint : "+pInst.getNoeud()+"	Noeud :"+child.getNodeName());
							pInst.setNoeud(child);
							getInsctructionExpressions(pInst);							//System.out.println("Res est devenu: "+pInst.getNoeud()+"	noeud :"+child.getNodeName()+" Cl : "+pInst.getNum()+" p :"+NumLin);
	
							break;
						}
						else 
						{
							pInst.setNum(pInst.getNum()+1);
							if (child.getNodeName()=="Solution"||child.getNodeName()=="Dec"||child.getNodeName()=="Body"||child.getNodeName()=="For" || child.getNodeName()=="If" ||child.getNodeName()=="While" ||child.getNodeName()=="Else" )
							{ 
								//System.out.println(" NewAppel :"+child.getNodeName()+" Cl : "+pInst.getNum()+" p :"+NumLin);
								//
								getInsctruction(child,pInst,NumLin);
							}	
						}	
				}
			}

	//	}
		
	}
	public  void afficherNode(Node pRoot)
	{
		System.out.println(pRoot.getNodeName());
		NodeList children = pRoot.getChildNodes();
		NamedNodeMap attributes;
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			if (child instanceof Element)
			{
				System.out.println(child.getNodeName());
				attributes = child.getAttributes();
				for (int k  = 0; k < attributes.getLength(); k++)
				{
					Node attribute = attributes.item(k);
					String name = attribute.getNodeName();
					String value = attribute.getNodeValue();
					System.out.println("		"+name+"="+value+", ");			
				}
			}
		}
	}		
	public  void afficherNodeInsctruction(Node pRoot)
	{
		NamedNodeMap attributes = pRoot.getAttributes();
		for (int k  = 0; k < attributes.getLength(); k++)
		{
			Node attribute = attributes.item(k);
			String name = attribute.getNodeName();
			String value = attribute.getNodeValue();
			System.out.println("		"+name+"="+value+", ");			
		}
	}
	public  void goOn(Node pRoot )
	// permet de voir s'il ya d'autre niveaux 
	// l'arbre xml d'un programme
	{
		NodeList children = pRoot.getChildNodes();
		NamedNodeMap attributes;
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			if (child instanceof Element)
			{
				//System.out.println(child.getNodeName());
				attributes = child.getAttributes();
				for (int k  = 0; k < attributes.getLength(); k++)
				{
					Node attribute = attributes.item(k);
					String name = attribute.getNodeName();
					String value = attribute.getNodeValue();
					System.out.println("		"+name+"="+value+", ");			
				}
				if (child.getNodeName()=="For")
				{
					//System.out.println("ici pour");
					goOn(child);
				}
				else 
					if (child.getNodeName()=="If")
						{
						goOn(child);
						}
					else 
						if (child.getNodeName()=="While")
						{
							goOn(child);
						}		
				
			}
		}
	}
	public boolean isThereCLPlease(Model pModel,SolutionEvaluation pEvaluation)
	// permet de chercher les des lignes critiques dans la solution
	// 
	{
		currentFile = new File(pModel.getPath());
		try {
			currentDoc = builder.parse(currentFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = currentDoc.getDocumentElement();
		//Element root = (Element) pEvaluation.getRootSol();
		//Parcours des taches 
		Iterator <Task> itTasks = pModel.getTasks().iterator(); 
		while (itTasks.hasNext())
		{
			Task itemTask = (Task) itTasks.next();
			//System.out.print("	Tache : "+itemTask.getName());
			//System.out.println("				nb_Cls : "+itemTask.getCriticalLines().size());
			//Parcours des lignes critiques	
			Iterator <String> itCLs = itemTask.getCriticalLines().iterator(); 
			while (itCLs.hasNext())
			{	
				String itemCL =  itCLs.next();
				//System.out.print(itemCL+ "***");
				// D'ici on peut commencer la recherche
				Instruction myInst = new Instruction();
				//System.out.print(itemCL+"   "+ Integer.parseInt(itemCL));
				myInst.setNum(1);
				getInsctruction(root, myInst, Integer.parseInt(itemCL.trim()));
				//System.out.println(myInst.getNoeud().getNodeName()+" resultat :"+isItThere(myInst, pEvaluation, pModel));
				if (isItThere(myInst, pEvaluation, pModel))
					{
						itemTask.setIdentCLs(itemTask.getIdentCLs()+1);
						pModel.setNbOfIdentCls(pModel.getNbOfIdentCls()+1);
					}
				
			}
			if(itemTask.getIdentCLs()>0)
				pModel.setNbOfIdentTasks(pModel.getNbOfIdentTasks()+1);
			
			//System.out.println("		le nbre detect **"+itemTask.getIdentCLs());
		}
		/*if(pModel.getName().equals("modele 9"))
		{
		System.out.println("		le nbre  Cls " +pModel.getTotalNbOfCls());
		//System.out.println("		le nbre detect **"+pModel.getNbOfIdentCls());	
		System.out.println("		le nbre detect  Cls "+pModel.getNbOfIdentCls());
		}*/
		//System.out.println("		le nbre detect  Cls "+pModel.getNbOfIdentCls());
		if (pModel.getNbOfIdentTasks()>0)
			return true;
		else 
			return false;
	}
	public  void getAllInsctruction(Node pRoot, ArrayList<Instruction> pInst,String  pType )
	// permet de recuper les toute les instructions du mm type que pType
	{
			NodeList children = pRoot.getChildNodes();
			NamedNodeMap attributes;
			for (int i = 0; i < children.getLength(); i++)
			{
				Node child = children.item(i);
				if (child instanceof Element)
				{
					String childName = child.getNodeName();	
					//System.out.println(childName);
					if (childName=="Solution"||childName=="Dec"||childName=="Body" ||childName=="Else" )
					{
						getAllInsctruction(child,pInst,pType);
					}
					else 
						{	
							Instruction inst = new Instruction();
							if(child.getNodeName()=="For" || child.getNodeName()=="If" ||child.getNodeName()=="While")
							{
								if (childName== pType)
								{	
									inst.setNoeud(child);
									getInsctructionExpressions(inst);	
									getAllInsctruction(child,pInst,pType);
									pInst.add(inst);
								}
								else 
									getAllInsctruction(child,pInst,pType);
							}
							else 
								if (childName== pType)
									{
										inst.setNoeud(child);
										if (childName.equals("Affectation"))
											getInsctructionExpressions(inst);
										pInst.add(inst);
									}
							
						}
				}
			}

		
	}
	public  void getInsctructionExpressions(Instruction pInst )
	{
			NodeList children = pInst.getNoeud().getChildNodes();
			NamedNodeMap attributes;
			ArrayList<Expression> instExp = new ArrayList<Expression>();
			if(pInst.getNoeud().getNodeName().equals("If"))
			{
				for (int i = 0; i < children.getLength(); i++)
				{
					Node child = children.item(i);
					if (child instanceof Element)
					{
							if(child.getNodeName().equals("BeginIf"))
							{
								NodeList childrenExp = child.getChildNodes();
								NamedNodeMap exprs;
								
								for (int j = 0; j < childrenExp.getLength(); j++)
								{
									
									Node exp = childrenExp.item(j);
									if(exp instanceof Element)
									{
										NamedNodeMap curExpAtt = exp.getAttributes();
										Expression ctr = new Expression();
										String nameExp = "";
										String left = "";
										String right = "";
										String op = "";
										String rangLeft ="";
										String rangRight = "";
										for (int k  = 0; k < curExpAtt.getLength(); k++)
										{
											Node attribute = curExpAtt.item(k);
											String name = attribute.getNodeName();
											String value = attribute.getNodeValue();
											//System.out.println(name+" "+value);
											if (name=="name")
											{
												nameExp = value;
												//task.setName(value);
											}
											else 
												if (name=="left")
												{
													left = value;
													//task.setType(value);
												}
											else 
												if (name=="right")
												{
													right = value;
													//task.setMother(value);
												}
											else 
												if (name=="op")
												{
													op = value;
													//task.setNote(value);
												}
											else 
												if (name=="rangLeft")
												{
													rangLeft = value;
													//task.setDeb(value);
												}
											else 
												if (name=="rangRight")
												{
													rangRight = value;
													//System.out.println(Trg);
													//task.setFin(value);
												}
											//NodeExrpressions.add();	
										}
										ctr.setName(nameExp);ctr.setLeft(left);ctr.setRight(right);ctr.setOp(op);ctr.setRangLeft(rangLeft);ctr.setRangRight(rangRight);
										instExp.add(ctr);
									}
								}
							}
							else 
								break;
						
						
					}
				}

			}
			else
				if(pInst.getNoeud().getNodeName().equals("For"))
				{
					for (int i = 0; i < children.getLength(); i++)
					{
						Node child = children.item(i);
						if (child instanceof Element)
						{
								if(child.getNodeName().equals("BeginFor"))
								{
									NodeList childrenExp = child.getChildNodes();
									NamedNodeMap exprs;
									
									for (int j = 0; j < childrenExp.getLength(); j++)
									{
										
										Node exp = childrenExp.item(j);
										if(exp instanceof Element)
										{
											NamedNodeMap curExpAtt = exp.getAttributes();
											Expression ctr = new Expression();
											String nameExp = "";
											String left = "";
											String right = "";
											String op = "";
											String rangLeft ="";
											String rangRight = "";
											for (int k  = 0; k < curExpAtt.getLength(); k++)
											{
												Node attribute = curExpAtt.item(k);
												String name = attribute.getNodeName();
												String value = attribute.getNodeValue();
												//System.out.println(name+" "+value);
												if (name=="name")
												{
													nameExp = value;
													//task.setName(value);
												}
												else 
													if (name=="left")
													{
														left = value;
														//task.setType(value);
													}
												else 
													if (name=="right")
													{
														right = value;
														//task.setMother(value);
													}
												else 
													if (name=="op")
													{
														op = value;
														//task.setNote(value);
													}
												else 
													if (name=="rangLeft")
													{
														rangLeft = value;
														//task.setDeb(value);
													}
												else 
													if (name=="rangRight")
													{
														rangRight = value;
														//System.out.println(Trg);
														//task.setFin(value);
													}
												//NodeExrpressions.add();	
											}
											ctr.setName(nameExp);ctr.setLeft(left);ctr.setRight(right);ctr.setOp(op);ctr.setRangLeft(rangLeft);ctr.setRangRight(rangRight);
											instExp.add(ctr);
										}
									}
								}
								else 
									break;
							
							
						}
					}

				}
				else 
					if(pInst.getNoeud().getNodeName().equals("While"))
					{
						for (int i = 0; i < children.getLength(); i++)
						{
							Node child = children.item(i);
							if (child instanceof Element)
							{
									if(child.getNodeName().equals("BeginWhile"))
									{
										NodeList childrenExp = child.getChildNodes();
										NamedNodeMap exprs;
										
										for (int j = 0; j < childrenExp.getLength(); j++)
										{
											
											Node exp = childrenExp.item(j);
											if(exp instanceof Element)
											{
												NamedNodeMap curExpAtt = exp.getAttributes();
												Expression ctr = new Expression();
												String nameExp = "";
												String left = "";
												String right = "";
												String op = "";
												String rangLeft ="";
												String rangRight = "";
												for (int k  = 0; k < curExpAtt.getLength(); k++)
												{
													Node attribute = curExpAtt.item(k);
													String name = attribute.getNodeName();
													String value = attribute.getNodeValue();
													//System.out.println(name+" "+value);
													if (name=="name")
													{
														nameExp = value;
														//task.setName(value);
													}
													else 
														if (name=="left")
														{
															left = value;
															//task.setType(value);
														}
													else 
														if (name=="right")
														{
															right = value;
															//task.setMother(value);
														}
													else 
														if (name=="op")
														{
															op = value;
															//task.setNote(value);
														}
													else 
														if (name=="rangLeft")
														{
															rangLeft = value;
															//task.setDeb(value);
														}
													else 
														if (name=="rangRight")
														{
															rangRight = value;
															//System.out.println(Trg);
															//task.setFin(value);
														}
													//NodeExrpressions.add();	
												}
												ctr.setName(nameExp);ctr.setLeft(left);ctr.setRight(right);ctr.setOp(op);ctr.setRangLeft(rangLeft);ctr.setRangRight(rangRight);
												instExp.add(ctr);
											}
										}
									}
									else 
										break;
								
								
							}
						}

					}
					else if(pInst.getNoeud().getNodeName().equals("Affectation"))
					{
						
						for (int i = 0; i < children.getLength(); i++)
						{
							Node child = children.item(i);
							if (child instanceof Element)
							{
								Node exp = children.item(i);
								if(exp instanceof Element)
								{
									NamedNodeMap curExpAtt = exp.getAttributes();
									Expression ctr = new Expression();
									String nameExp = "";
									String left = "";
									String right = "";
									String op = "";
									String rangLeft ="";
									String rangRight = "";
									for (int k  = 0; k < curExpAtt.getLength(); k++)
									{
										Node attribute = curExpAtt.item(k);
										String name = attribute.getNodeName();
										String value = attribute.getNodeValue();
										//System.out.println(name+" "+value);
										if (name=="name")
										{
											nameExp = value;
											//task.setName(value);
										}
										else 
											if (name=="left")
											{
												left = value;
												//task.setType(value);
											}
										else 
											if (name=="right")
											{
												right = value;
												//task.setMother(value);
											}
										else 
											if (name=="op")
											{
												op = value;
												//task.setNote(value);
											}
										else 
											if (name=="rangLeft")
											{
												rangLeft = value;
												//task.setDeb(value);
											}
										else 
											if (name=="rangRight")
											{
												rangRight = value;
												//System.out.println(Trg);
												//task.setFin(value);
											}
										//NodeExrpressions.add();	
									}
									ctr.setName(nameExp);ctr.setLeft(left);ctr.setRight(right);ctr.setOp(op);ctr.setRangLeft(rangLeft);ctr.setRangRight(rangRight);
									instExp.add(ctr);
								}
							}

							}
						}

			
		pInst.setExpressions(instExp);
		
	}
	public ArrayList<String> getVarClones(Variable pVarName, SolutionEvaluation pEval )
	// permet de recuperer les variables qui 
	// sont probablement utilisées dans la slution (au lieu de celle utilisée dans le modèle) 
	{
		ArrayList<String> clones = new ArrayList<String>();
		clones.add(pVarName.getName());
		Iterator<Variable> solVar = pEval.getVariables().iterator();
		while(solVar.hasNext())
		{
			Variable curVar = solVar.next();
			if(curVar.getType().equals(pVarName.getType()))
				clones.add(curVar.getName());	
		}
		return clones;
	}
	public ArrayList<String> getVarClones(Variable pVarName, ArrayList<Variable> pVars )
	// permet de recuperer les variables qui 
	// sont probablement utilisées dans la slution (au lieu de celle utilisée dans le modèle) 
	{
		ArrayList<String> clones = new ArrayList<String>();
		clones.add(pVarName.getName());
		Iterator<Variable> solVar = pVars.iterator();
		while(solVar.hasNext())
		{
			Variable curVar = solVar.next();
			if(curVar.getType().equals(pVarName.getType()))
				clones.add(curVar.getName());	
		}
		return clones;
	}
	public void afficherColnes(ArrayList<String> pClones)
	{
		Iterator <String> it = pClones.iterator(); 
		while (it.hasNext())
		{
			String item =  it.next();
			 System.out.println("	"+item+" ");

		}
	}
	public boolean areTheseSamesExps(ArrayList<Expression> pExpSrc,ArrayList<Expression> pExpTrg, ArrayList<Variable> pSrcVar, ArrayList<Variable> pTrgVar)
	// permet de comparer deux expression
	// d'une instruction
	{
		boolean res = false;
		if(pExpSrc.size()==pExpTrg.size())
		{
			ArrayList<Expression> clonesExp = new ArrayList<Expression>();
			Iterator<Expression> expsSrc = pExpSrc.iterator();
			res = true;
			//System.out.println("* je v comparer : ");
			//System.out.println(" l :"+leftSrc+" R :"+rightSrc+" O :"+OpSrc+" lR :"+leftRangSrc+" RR :"+rightRangeSrc);
			//afficherExpressions(pExpSrc);
			//System.out.println("* Avec : ");
			//afficherExpressions(pExpTrg);
			while(expsSrc.hasNext() && res==true)
			{
				// Pr toute expression source
				Expression curSrcExp = expsSrc.next();
				String leftSrc = curSrcExp.getLeft();
				String rightSrc = curSrcExp.getRight();
				String OpSrc = curSrcExp.getOp();
				String leftRangSrc = curSrcExp.getRangLeft() ;
				String rightRangeSrc = curSrcExp.getRangRight();
				boolean exist = false;
				Iterator<Expression> expsTrg = pExpTrg.iterator();
				while(expsTrg.hasNext())
				{
					// Pr toute expression trg
					Expression curSrcTrg = expsTrg.next();
					String leftTrg = curSrcTrg.getLeft();
					String rightTrg = curSrcTrg.getRight();
					String OpTrg = curSrcTrg.getOp();
					String leftRangTrg = curSrcTrg.getRangLeft() ;
					String rightRangeTrg = curSrcTrg.getRangRight();
					//System.out.println("* Avec: ");
					//System.out.println(" l :"+leftTrg+" R :"+rightTrg+" O :"+OpTrg+" lR :"+leftRangTrg+" RR :"+rightRangeTrg);
					if (OpSrc.equals(OpTrg)) // l'operation doit etre la meme
					{
						if (isItVar(leftSrc, pSrcVar))
						{
							if(isItVar(leftTrg, pTrgVar))
							{
								// la var trg doit etre presente dans les clones de varSrc
								Variable leftVar = nameToVar(leftSrc,pSrcVar);
								if (isItClone(leftTrg, leftVar))
									{
										leftVar.setClone(leftTrg);
										if(isItVar(rightSrc, pSrcVar))
										{
											Variable rightVar = nameToVar(rightSrc,pSrcVar);
											if(isItClone(rightTrg, rightVar))
											{
												rightVar.setClone(rightTrg);
												// mm chose pr les rangs
												if (isItVar(leftRangSrc, pSrcVar))
												{
													if(isItVar(leftRangTrg, pTrgVar))
													{
														Variable leftRangVar = nameToVar(leftRangSrc,pSrcVar);
														if (isItClone(leftRangTrg, leftRangVar)) // 
															{
																leftRangVar.setClone(leftRangTrg);
																// mm chose pr left right
																if (isItVar(rightRangeSrc, pSrcVar))
																{
																	if(isItVar(rightRangeTrg, pTrgVar))
																	{
																		Variable rightRangVar = nameToVar(rightRangeSrc,pSrcVar);
																		if (isItClone(rightRangeTrg, rightRangVar))
																			{
																				rightRangVar.setClone(rightRangeTrg);
																				exist = true;break;
																			}
																		else 
																			continue;
																	}
																	else 
																		continue;	
																}
																else 
																{
																	if(isItVar(rightRangeTrg, pTrgVar))
																		continue;
																	else
																	{
																		if(rightRangeSrc.indexOf("exp")!= -1 && rightRangeTrg.indexOf("exp")!=-1)
																			exist = true;
																		else 
																			if(rightRangeSrc.equals(rightRangeTrg))
																				{exist = true;break;}
																			else 
																				continue;
																				
																	}
																}
															}
														else 
															continue;
													}
													else 
														continue;
												}
												else 
												{
													if(isItVar(leftRangTrg, pTrgVar))
														continue;
													else
													{
														if(leftRangSrc.indexOf("exp")!=-1 && leftRangTrg.indexOf("exp")!=-1)
															exist = true;
														else 
															if(leftRangSrc.equals(leftRangTrg))
																{exist = true;break;}
															else 
																continue;
																
													}
												}
											}
											else
												continue;
										}
										else 
											{
											if(isItVar(rightTrg, pTrgVar))
												continue;
											else
											{
												if(rightSrc.indexOf("exp")!=-1 && rightTrg.indexOf("exp")!=-1)
												{
													if (isItVar(leftRangSrc, pSrcVar))
													{
														if(isItVar(leftRangTrg, pTrgVar))
														{
															Variable leftRangVar = nameToVar(leftRangSrc,pSrcVar);
															if (isItClone(leftRangTrg, leftRangVar)) // 
																{
																	leftRangVar.setClone(leftRangTrg);
																	// mm chose pr left right
																	if (isItVar(rightRangeSrc, pSrcVar))
																	{
																		if(isItVar(rightRangeTrg, pTrgVar))
																		{
																			Variable rightRangVar = nameToVar(rightRangeSrc,pSrcVar);
																			if (isItClone(rightRangeTrg, rightRangVar))
																				{
																					rightRangVar.setClone(rightRangeTrg);
																					exist = true;break;
																				}
																			else 
																				continue;
																		}
																		else 
																			continue;	
																	}
																	else 
																	{
																		if(isItVar(rightRangeTrg, pTrgVar))
																			continue;
																		else
																		{
																			if(rightRangeSrc.indexOf("exp")!= -1 && rightRangeTrg.indexOf("exp")!=-1)
																				exist = true;
																			else 
																				if(rightRangeSrc.equals(rightRangeTrg))
																					{exist = true;break;}
																				else 
																					continue;
																					
																		}
																	}
																}
															else 
																continue;
														}
														else 
															continue;
													}
													else 
													{
														if(isItVar(leftRangTrg, pTrgVar))
															continue;
														else
														{
															if(leftRangSrc.indexOf("exp")!=-1 && leftRangTrg.indexOf("exp")!=-1)
																exist = true;
															else 
																if(leftRangSrc.equals(leftRangTrg))
																	{exist = true;break;}
																else 
																	continue;
																	
														}
													}
												}
												else 
													if(rightSrc.equals(rightTrg))
														{exist = true;break;}
													else 
														return false;
														
											}
											}//
									}
								else 
									continue;
								
							}
							else continue;
						}
						else 
							{
								if(isItVar(leftTrg, pTrgVar))
									continue;
								else
								{
									if(leftSrc.indexOf("exp")!=-1 && leftTrg.indexOf("exp")!=-1)
									{
										if(isItVar(rightSrc, pSrcVar))
										{
											Variable rightVar = nameToVar(rightSrc,pSrcVar);
											if(isItClone(rightTrg, rightVar))
											{
												rightVar.setClone(rightTrg);
												// mm chose pr les rangs
												if (isItVar(leftRangSrc, pSrcVar))
												{
													if(isItVar(leftRangTrg, pTrgVar))
													{
														Variable leftRangVar = nameToVar(leftRangSrc,pSrcVar);
														if (isItClone(leftRangTrg, leftRangVar)) // 
															{
																leftRangVar.setClone(leftRangTrg);
																// mm chose pr left right
																if (isItVar(rightRangeSrc, pSrcVar))
																{
																	if(isItVar(rightRangeTrg, pTrgVar))
																	{
																		Variable rightRangVar = nameToVar(rightRangeSrc,pSrcVar);
																		if (isItClone(rightRangeTrg, rightRangVar))
																			{
																				rightRangVar.setClone(rightRangeTrg);
																				exist = true;break;
																			}
																		else 
																			continue;
																	}
																	else 
																		continue;	
																}
																else 
																{
																	if(isItVar(rightRangeTrg, pTrgVar))
																		continue;
																	else
																	{
																		if(rightRangeSrc.indexOf("exp")!= -1 && rightRangeTrg.indexOf("exp")!=-1)
																			exist = true;
																		else 
																			if(rightRangeSrc.equals(rightRangeTrg))
																				{exist = true;break;}
																			else 
																				continue;
																				
																	}
																}
															}
														else 
															continue;
													}
													else 
														continue;
												}
												else 
												{
													if(isItVar(leftRangTrg, pTrgVar))
														continue;
													else
													{
														if(leftRangSrc.indexOf("exp")!=-1 && leftRangTrg.indexOf("exp")!=-1)
															exist = true;
														else 
															if(leftRangSrc.equals(leftRangTrg))
																{exist = true;break;}
															else 
																continue;
																
													}
												}
											}
											else
												continue;
										}
										else 
										{

											if(isItVar(rightTrg, pTrgVar))
												continue;
											else
											{
												if(rightSrc.indexOf("exp")!=-1 && rightTrg.indexOf("exp")!=-1)
												{
													if (isItVar(leftRangSrc, pSrcVar))
													{
														if(isItVar(leftRangTrg, pTrgVar))
														{
															Variable leftRangVar = nameToVar(leftRangSrc,pSrcVar);
															if (isItClone(leftRangTrg, leftRangVar)) // 
																{
																	leftRangVar.setClone(leftRangTrg);
																	// mm chose pr left right
																	if (isItVar(rightRangeSrc, pSrcVar))
																	{
																		if(isItVar(rightRangeTrg, pTrgVar))
																		{
																			Variable rightRangVar = nameToVar(rightRangeSrc,pSrcVar);
																			if (isItClone(rightRangeTrg, rightRangVar))
																				{
																					rightRangVar.setClone(rightRangeTrg);
																					exist = true;break;
																				}
																			else 
																				continue;
																		}
																		else 
																			continue;	
																	}
																	else 
																	{
																		if(isItVar(rightRangeTrg, pTrgVar))
																			continue;
																		else
																		{
																			if(rightRangeSrc.indexOf("exp")!= -1 && rightRangeTrg.indexOf("exp")!=-1)
																				exist = true;
																			else 
																				if(rightRangeSrc.equals(rightRangeTrg))
																					{exist = true;break;}
																				else 
																					continue;
																					
																		}
																	}
																}
															else 
																continue;
														}
														else 
															continue;
													}
													else 
													{
														if(isItVar(leftRangTrg, pTrgVar))
															continue;
														else
														{
															if(leftRangSrc.indexOf("exp")!=-1 && leftRangTrg.indexOf("exp")!=-1)
																exist = true;
															else 
																if(leftRangSrc.equals(leftRangTrg))
																	{exist = true;break;}
																else 
																	continue;
																	
														}
													}
												}
												else 
													if(rightSrc.equals(rightTrg))
														{exist = true;break;}
													else 
														return false;
														
											}
										}//continue;
									}
									else 
										if(leftSrc.equals(leftTrg))
											{exist = true;break;}
										else 
											continue;
											
								}
							}//222222222222222222222222222222222222222222222222222
					}
					else 
						continue;
					
				}
				res = exist;
			}
		}

		//System.out.println("* Res final de comp: "+res);
		return res;
		
	}
	public boolean isItVar(String pCh,ArrayList<Variable> pVars)
	{
		boolean trv = false;
		Iterator<Variable> vars = pVars.iterator();
		while(vars.hasNext()&& trv==false)
		{
			Variable curVar = vars.next();
			if(curVar.getName().equals(pCh))
				trv = true;
		}
		return trv;
	}
	public Variable nameToVar(String pCh,ArrayList<Variable> pVars)
	{
		Variable trv=null;
		Iterator<Variable> vars = pVars.iterator();
		while(vars.hasNext() && trv == null)
		{
			Variable curVar = vars.next();
			if(curVar.getName().equals(pCh))
				trv = curVar;
		}
		return trv;
	}
	public boolean isItClone(String pCh,Variable pVar)
	// Permet de tester si ch est l'une des clones de var
	{
		boolean trv = false;
		Iterator<String> vars = pVar.getVarClone().iterator();
		while(vars.hasNext()&& trv==false)
		{
			String curVar = vars.next();
			if(curVar.equals(pCh))
				trv = true;
		}
		return trv;
	}
	public Model wichOneShoudWeTry(ArrayList<Model> pModels)
	// permet de choisir le modele qui a le plus de taches 
	// et le plus de cls
	{
		float prcMaxRecTasks = 0;
		float prcMaxRecCls = 0;
		float prcRecTasksCurModel = 0;
		float prcRecClsModel = 0;
		Model resMod = null;
		Iterator<Model> models =  pModels.iterator();
		while(models.hasNext())
		{
			Model curModel = models.next();
			//System.out.println("");
			if (! curModel.isSeen())
			{
				
				//System.out.println("	Nb_Taches_Id : "+curModel.getNbOfIdentTasks()+ " total : "+curModel.getTasks().size());
				//System.out.println("	Nb_Cls_Id : "+curModel.getNbOfIdentCls()+ " total : "+curModel.getTotalNbOfCls());
				prcRecTasksCurModel =  (curModel.getTasks().size()>0)?(curModel.getNbOfIdentTasks()*100)/curModel.getTasks().size():0;
				prcRecClsModel = (curModel.getTotalNbOfCls()>0)?(curModel.getNbOfIdentCls()*100)/curModel.getTotalNbOfCls():0;
				//System.out.println("Model : "+curModel.getName());
				//System.out.println("	Nb_Taches : "+curModel.getTasks().size()+", Nb_Taches_Rec :"+prcRecTasksCurModel);
				//System.out.println("	Nb_CLs : "+curModel.getTotalNbOfCls()+", Nb_Cls_Rec :"+prcRecTasksClsModel);
				if ( prcRecTasksCurModel>= prcMaxRecTasks )
				{
					prcMaxRecTasks = prcRecTasksCurModel;
					
					if (prcRecClsModel>=prcMaxRecCls)
					{
						prcMaxRecCls = prcRecClsModel;
						resMod = curModel;
					}
				}
				
			}
		}
		return resMod;
	}
	public boolean tryThisModel2(Model pModel, SolutionEvaluation pEvaluation)
	// sert à la reconnaissance 
	{	
		System.out.println("W'll try with : "+pModel.getName());
		ArrayList<Task> taches = pModel.getTasks();
		ArrayList<Constraint> contraintes = pModel.getConstraints();
		pEvaluation.setNote(0);
		// reconnaissances des taches
		currentFile = new File(pModel.getPath());
		try {
			currentDoc = builder.parse(currentFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentSolFile = new File(pEvaluation.getXmlPath());
		try {
			currentSolDoc = builder.parse(currentSolFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = currentDoc.getDocumentElement();
		Element rootSol = currentSolDoc.getDocumentElement();
		ArrayList<Task> ListOfRecTasks = new ArrayList<Task>();
		System.out.println("On a "+ ListOfRecTasks.size());
		Iterator itTasks = taches.iterator();
		//afficherTasks(pModel.getTasks());
		while (itTasks.hasNext())
		{
			
			Task curTask = (Task) itTasks.next();
			System.out.println("Recherche de la tache : "+curTask.getName());
			String debTask = curTask.getDeb();
			String finTask = curTask.getFin();
			//System.out.println("ma valeur est :"+curTask.getFin());
			//System.out.println("Nom tache :"+curTask.getName()+" Deb :"+curTask.getDeb()+" fin : "+curTask.getFin());
			int curInst = Integer.parseInt(debTask); // on va commencer la reconnaissance à partir d'ici
			entierCouter numLine = new entierCouter();
			numLine.setVal(1);
			Instruction inst = new Instruction();
			inst.setNum(1);
			getInsctruction(root, inst, curInst);
			ArrayList<Instruction> samInst = new ArrayList<Instruction>();
			getAllInsctructionWithNumLine(rootSol, samInst,inst.getNoeud().getNodeName(),numLine);
			//afficherInsctructions(samInst);
			boolean res = true;
			boolean desordre = false;
			float note = 0;
			ArrayList<String> listeDesordre = new ArrayList<String>();
			//curInst++;
			Iterator<Instruction> itPossibleInst = samInst.iterator();
			while(itPossibleInst.hasNext())
			{
				Instruction possibleDebInst = (Instruction) itPossibleInst.next();
				int possibleDeb = possibleDebInst.getNum();
				int possibleDebClone = possibleDeb;
				int fin = Integer.parseInt(finTask);
				curInst = Integer.parseInt(debTask);
				boolean wanted = true;
				
				System.out.println("Debut Possible : "+possibleDeb);
				if(!alreadyTreated(possibleDeb, ListOfRecTasks))
				{
					while (curInst <= fin  && wanted)
					{
						//System.out.println("Instr Mod Av App : "+curInst);
						//System.out.println("Instr SolAv App : "+possibleDeb);
						Instruction instMod = new Instruction();
						instMod.setNum(1);
						Instruction instSol = new Instruction();
						instSol.setNum(1);
						getInsctruction(root, instMod, curInst);
						instMod.setNum(curInst);
						//System.out.println(" Inst Mode : "+instMod.getNoeud().getNodeName());
						//afficherNodeInsctruction(instMod.getNoeud());
						getInsctruction(rootSol, instSol, possibleDeb);
						instSol.setNum(possibleDeb);
						//System.out.println(" nb_inst : "+pEvaluation.getNbreInst());
						//afficherNodeInsctruction(instSol.getNoeud());
						//System.out.println("Num : "+curInst+", Type : "+instMod.getNoeud().getNodeName());
						//System.out.println("Instr Mod: "+instMod.getNum()+", normalemnt :"+curInst);
						//System.out.println("Instr Sol: "+instMod.getNum()+", normalemnt :"+possibleDeb);
						//afficherNodeInsctruction(instMod.getNoeud());
						 if(possibleDeb <= pEvaluation.getNbreInst())// ici il faut pa refaire le début
						 {
							 if (areTheseSameInstruction(instMod, instSol,pModel,pEvaluation)) 
								{
									possibleDeb ++;
									curInst++;
									continue; 
								}
								else 
									if ( ! true)//isItMissed(instMod,pModel,pEvaluation,Integer.parseInt(curTask.getDeb()),possibleDebInst.getNum()))//-----------------------------------
									{
										// A ce niveau là il faut verifier les contraintes de désordre
										// il faut tout d'abord 
										possibleDeb ++;
										curInst++;
										if(!desordre)
											{
												desordre = false;
												listeDesordre.add(Integer.toString(instMod.getNum()));
											}
										continue;
									}
									else 
									{
										// les deux instructions sont differentes
										// je dois verifier qu'il n y a pas de contrainte d'absence sur cette inst
									
										// verification des contraintes d'absence
										// il peut aussi y avoir des contraines de desordre
										//System.out.println(curTask.getName()+"ici********************"+instMod.getNum()+" nor "+curInst);
										if (canIbeMissed(instMod.getNum(),pModel.getConstraints()))//-------------------------------------------------------------
										{
											//System.out.println("Possible********************");
											possibleDeb ++;
											curInst++;
											continue;
										}
										else
										{
											//System.out.println("Possible********************");
											wanted = false;
											break;
										}	
										
									}
						 }
						 else
						 { System.out.println("ce debut : "+possibleDeb+" est traité : ");;wanted = false;}
						
					}
				}
				else 
					wanted = false;
				
				System.out.println("Res rech : "+wanted+ "note tache :"+curTask.getNote()); 
				//System.out.println("Mntnant on a "+ ListOfRecTasks.size());
				if (wanted) // la tache a été reconnue
				{
					//System.out.println("tache : "+curTask.getName() +" reconue  "+curTask.getNote());
					Task recTask = new Task();
					
					recTask.setName(curTask.getName());
					recTask.setDeb(Integer.toString(possibleDebClone));
					recTask.setFin(Integer.toString(possibleDeb - 1));
					//setRecognizedInstructions(pEvaluation, Integer.parseInt(recTask.getDeb()), Integer.parseInt(recTask.getFin()));
					recTask.setType(curTask.getType());
					// Penalisation de desodre 
					
					if(desordre)
						note = Float.parseFloat(curTask.getNote()) - getPenDesordre(pModel.getConstraints(),listeDesordre);
					else
						note = Float.parseFloat(curTask.getNote());
					recTask.setNote(Float.toString(note));
					ListOfRecTasks.add(recTask);
					pModel.setNbOfRecTasks(pModel.getNbOfRecTasks()+1);
					//MAJ de la note
					//System.out.println("Note :"+recTask.getNote()+", ancienne "+pEvaluation.getNote()+", Koulech :"+pEvaluation.getNote()+Float.parseFloat(recTask.getNote()));
					pEvaluation.setNote(pEvaluation.getNote()+note);
					//pEvaluation.setEtat(true);
					// faire d'autre choses
					
	
				}
				
			}
		}
		//afficherInsctructions(samInst);
		// Verifier le desodre de taches.----------------------------------------------------------------------------------
		pEvaluation.setTaches(ListOfRecTasks);
		System.out.println(" nb tache reconues : "+pEvaluation.getTaches().size());
		System.out.println(" nb tache modele : "+pModel.getTasks().size());
		System.out.println("Mntnant on a "+ ListOfRecTasks.size());
		if (pEvaluation.getTaches().size() == pModel.getTasks().size())
			pEvaluation.setEtat(true);
		if (pEvaluation.isEtat())
			{System.out.println("Reconnue.*********************"+ pEvaluation.getNote());}
		else 
			System.out.println("Non Reconnue.**********DESOLE**");
		return pEvaluation.isEtat();
	}
	public  void getAllInsctructionWithNumLine(Node pRoot, ArrayList<Instruction> pInst,String  pType,entierCouter pNumLine )
	// permet de recuper  toute les instructions du mm type que pType
	{
			NodeList children = pRoot.getChildNodes();
			NamedNodeMap attributes;
			for (int i = 0; i < children.getLength(); i++)
			{
				Node child = children.item(i);
				if (child instanceof Element)
				{
					String childName = child.getNodeName();	
					if (childName=="Dec")
					{
						getAllInsctructionWithNumLine(child, pInst, pType,pNumLine);
					}
					else
					{
						pNumLine.setVal(pNumLine.getVal()+1);
						//System.out.println("ici******"+childName+"***"+pNumLine.getVal());
						
						if (childName=="Solution"||childName=="Body"  ||childName=="Else" )
						{
							//pNumLine.setVal(pNumLine.getVal()+1);
							getAllInsctructionWithNumLine(child, pInst, pType,pNumLine);
						}
						else 
							{	
								Instruction inst = new Instruction();
								if(child.getNodeName()=="For" || child.getNodeName()=="If" ||child.getNodeName()=="While")
								{
									//pNumLine.setVal(pNumLine.getVal()+1);
									if (childName== pType)
									{	
										inst.setNum(pNumLine.getVal());
										inst.setNoeud(child);
										getInsctructionExpressions(inst);	
										getAllInsctructionWithNumLine(child, pInst, pType,pNumLine);
										pInst.add(inst);
									}
									else 
										getAllInsctructionWithNumLine(child, pInst, pType,pNumLine);
								}
								else 
									if (childName== pType)
										{
											//pNumLine.setVal(pNumLine.getVal()+1);
											inst.setNum(pNumLine.getVal());	
											inst.setNoeud(child);
											
											
											if (childName.equals("Affectation"))
												getInsctructionExpressions(inst);
											pInst.add(inst);
										}
							}
					}
						
				}
			}

		
	}
	
	public  void afficherInsctructions(ArrayList<Instruction> pInsts)
	{
		Iterator<Instruction> it = pInsts.iterator();
		while(it.hasNext())
		{
			Instruction item = it.next();
			System.out.println("Inst :"+item.getNoeud().getNodeName()+", 	NumLine :"+item.getNum());
			NamedNodeMap attributes = item.getNoeud().getAttributes();
			for (int k  = 0; k < attributes.getLength(); k++)
			{
				Node attribute = attributes.item(k);
				String name = attribute.getNodeName();
				String value = attribute.getNodeValue();
				System.out.println("		"+name+"="+value+", ");			
			}
		}
		
	}
	public boolean areTheseSameInstruction(Instruction pInst1, Instruction pInst2,Model pModel, SolutionEvaluation pEval)
	// permet de tester si deux instructions sont identiques ou non
	{
		if (pInst1.getNoeud() != null && pInst2.getNoeud() != null)
		{
			Node instNode1 = pInst1.getNoeud();
			Node instNode2 = pInst2.getNoeud();
			String TypeInst1 = instNode1.getNodeName();
			String TypeInst2 = instNode2.getNodeName();
			// s'il s'agit d'une inst Lire
			if(TypeInst1.equals("Read")&& TypeInst2.equals("Read"))
			{
				// Recuperation des attributs des instr
				String valueInst1 = "";
				String rangeInst1 = "";
				String valueInst2 = "";
				String rangeInst2 = "";
				NamedNodeMap attributes = instNode1.getAttributes();
				// Recuperation des attributs de l'instr1
				for (int k  = 0; k < attributes.getLength(); k++)
				{
					Node attribute = attributes.item(k);
					String name = attribute.getNodeName();
					String value = attribute.getNodeValue();
					if (name == "value")
					{
						valueInst1 = value;
					}			
					else
						if (name == "range")
						{
							rangeInst1 = value;
						}	
				}// Recuperation des attributs de l'instr2
				attributes = instNode2.getAttributes();
				for (int k  = 0; k < attributes.getLength(); k++)
				{
					Node attribute = attributes.item(k);
					String name = attribute.getNodeName();
					String value = attribute.getNodeValue();
					if (name == "value")
					{
						valueInst2 = value;
					}			
					else
						if (name == "range")
						{
							rangeInst2 = value;
						}	
				}
				// Comparaison
				Variable varInst1 = nameToVar(valueInst1, pModel.getVariables());
				Variable varInst2 = nameToVar(valueInst2, pEval.getVariables());
				if (isItClone(valueInst2, varInst1))
				{
					varInst1.setClone(valueInst2);
					if (isItVar(rangeInst1, pModel.getVariables()) && isItVar(rangeInst2, pEval.getVariables()) )
					{
						varInst1 = nameToVar(rangeInst1, pModel.getVariables());
						if (isItClone(rangeInst2, varInst1))
							return true;
						else 
							return false;
					}
					else 
						return false;
					
				}
				else 
					return false;
			}
			else
				if(TypeInst1.equals("BeginIf")&& TypeInst2.equals("BeginIf")||TypeInst1.equals("Else")&& TypeInst2.equals("Else")||TypeInst1.equals("EndIf")&& TypeInst2.equals("EndIf"))
				{
					return true;
				}
				else 
					if(TypeInst1.equals("BeginFor")&& TypeInst2.equals("BeginFor")||TypeInst1.equals("EndFor")&& TypeInst2.equals("EndFor")||TypeInst1.equals("BeginWhile")&& TypeInst2.equals("BeginWhile")||TypeInst1.equals("EndWhile")&& TypeInst2.equals("EndWhile"))
					{
						return true;
					}
					else 
						if(TypeInst1.equals("If")&& TypeInst2.equals("If")||TypeInst1.equals("For")&& TypeInst2.equals("For")||TypeInst1.equals("While")&& TypeInst2.equals("While")||TypeInst1.equals("Affectation")&& TypeInst2.equals("Affectation"))
						{
							//
							//afficherExpressions(pInst1.getExpressions());
							//afficherExpressions(pInst2.getExpressions());
							if(areTheseSamesExps(pInst1.getExpressions(), pInst2.getExpressions(), pModel.getVariables(), pEval.getVariables()))
								return true;
							else
								return false;
						}
						else
							if(TypeInst1.equals("Write")&&TypeInst2.equals("Write"))
								return true;
						else 
							return false;
		}
		else 
			return false;
	}
	public boolean canIbeMissedOld(Instruction pInst,ArrayList<Constraint> pContraintes)
	// permet de tester si une instrction peut etre absente dans une solution
	{
		Iterator<Constraint> it = pContraintes.iterator();
		boolean uCan = false;
		//System.out.println("Num : "+pInst.getNum()+", Type : "+pInst.getNoeud().getNodeName());
		//afficherNodeInsctruction(pInst.getNoeud());
		while(it.hasNext() && !uCan)
		{
			Constraint ctr = it.next();
			if (ctr.getType().equals("3") && Integer.parseInt(ctr.getNumLig())==pInst.getNum())
				{
					uCan = true;
					break;
				}
		}
		return uCan;
	}
	public boolean canIbeMissed(int pNumInst,ArrayList<Constraint> pContraintes)
	// permet de tester si une instrction peut etre absente dans une solution
	{
		Iterator<Constraint> it = pContraintes.iterator();
		boolean uCan = false;
		//System.out.println("Num : "+pInst.getNum()+", Type : "+pInst.getNoeud().getNodeName());
		//afficherNodeInsctruction(pInst.getNoeud());
		while(it.hasNext() && !uCan)
		{
			Constraint ctr = it.next();
			if (ctr.getType().equals("3") && Integer.parseInt(ctr.getNumLig())== pNumInst)
				{
					uCan = true;
					break;
				}
		}
		return uCan;
	}
	public boolean canThisTaskBeMissed(String pTaskName, ArrayList<Constraint> pContraintes)
	// permet de tester si une Tache peut etre absente dans une solution
	{
		Iterator<Constraint> it = pContraintes.iterator();
		boolean uCan = false;
		//System.out.println("Num : "+pInst.getNum()+", Type : "+pInst.getNoeud().getNodeName());
		//afficherNodeInsctruction(pInst.getNoeud());
		while(it.hasNext() && !uCan)
		{
			Constraint ctr = it.next();
			if (ctr.getType().equals("6") && ctr.getTaskName().equals(pTaskName))
				{
					uCan = true;
					break;
				}
		}
		return uCan;
	}
	public boolean isTaskPresent(String pTaskName, ArrayList<Task> pTaks)
	// permet de tester si une est deja presente dans la liste des taches reconnues
	{
		Iterator<Task> it = pTaks.iterator();
		boolean isPres = false;
		//System.out.println("Num : "+pInst.getNum()+", Type : "+pInst.getNoeud().getNodeName());
		//afficherNodeInsctruction(pInst.getNoeud());
		while(it.hasNext() && !isPres)
		{
			Task tsk = it.next();
			if (tsk.getName().equals(pTaskName))
				{
				isPres = true;
					break;
				}
		}
		return isPres;
	}
	public boolean isItMissed(Instruction pInstruction, Model pModel, SolutionEvaluation pEval, int pDeb,int pPossDeb,ArrayList<String> pListeDejaVus)
	// Permet de teset l'absenece d'une instruction dans une solution
	{
		//ArrayList<Constraint> contraintes = pModel.getConstraints();
		boolean exist = false;
		// reconnaissances des taches
		currentFile = new File(pModel.getPath());
		try {
			currentDoc = builder.parse(currentFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentSolFile = new File(pEval.getXmlPath());
		try {
			currentSolDoc = builder.parse(currentSolFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = currentDoc.getDocumentElement();
		Element rootSol = currentSolDoc.getDocumentElement();
		//int curInst = pDeb;
		int possDeb = pPossDeb;
		boolean recInst ;
		while( !exist)
		{
			//Instruction instMod = new Instruction();
			//instMod.setNum(1);
			Instruction instSol = new Instruction();
			instSol.setNum(1);
			//getInsctruction(root, instMod, curInst);
			if (possDeb >= pEval.getNbreInst())
				{//System.out.println("Je n'esxiste pas.");
				return true;}				
			else
			{
				String xxx = pEval.getListeInstruction().get(possDeb-1).getNoeud().getNodeName();
				
				recInst =pEval.getListeInstruction().get(possDeb-1).isReconue();
				//System.out.println("je compare avc "+xxx+ "    "+recInst);
				if(!isItDejaVu(pListeDejaVus,Integer.toString(possDeb))&&canIbeDesordred(possDeb,pModel.getConstraints()))
					{
					//System.out.println("je cherche "+pEval.getListeInstruction().get(possDeb).getNoeud().getNodeName()+"  "+pEval.getListeInstruction().get(possDeb-1).isReconue());
					//System.out.println("je cherche "+pEval.getListeInstruction().get(possDeb-1).getNoeud().getNodeName()+"   "+pEval.getListeInstruction().get(possDeb-1).isReconue());
						getInsctruction(rootSol, instSol, possDeb);
						
						
						exist = areTheseSameInstruction(pInstruction,instSol, pModel, pEval);
						//System.out.println("Val exist: "+!exist);
					}
				
				//curInst++;
				possDeb++;
			}
//			System.out.println(" I'm : "+instSol.getNum()+ "& my valuee"+instSol.getNoeud());	
		}
		
		return !exist;
	}
	public float getPenDesordre(ArrayList<Constraint> pContraintes,ArrayList<String> pListDsr)
	// permet de tester si une instrction peut etre absente dans une solution
	{
		float res = 0;
		Iterator<String> itDsr = pListDsr.iterator();
		
		while(itDsr.hasNext())
		{
			String curInst = itDsr.next();
			//System.out.println(curInst+"  " );
			Iterator<Constraint> it = pContraintes.iterator();
			while(it.hasNext())
			{
				Constraint ctr = it.next();
				if (ctr.getType().equals("3") && ctr.getNumLig().equals(curInst))
					{
						res += Float.parseFloat(ctr.getPenalite());
						break;
					}
			}
			
		}
		
		return res;
	}
	public void getInstrctionNb(Node pRoot, entierCouter pRes)
	{
		NodeList children = pRoot.getChildNodes();
		NamedNodeMap attributes;
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			if (child instanceof Element)
			{
				pRes.setVal(pRes.getVal()+1);
				if (child.getNodeName()=="Solution"||child.getNodeName()=="Dec"||child.getNodeName()=="Body"||child.getNodeName()=="For" || child.getNodeName()=="If" ||child.getNodeName()=="While" ||child.getNodeName()=="Else" )
				{ 
					//System.out.println(" NewAppel :"+child.getNodeName()+" Cl : "+pInst.getNum()+" p :"+NumLin);
					//
					getInstrctionNb(child,pRes);
				}	
			}
		}
	}
	public void chargeSolInstructions(SolutionEvaluation pEval)
	// permet de charger toutes les intructions d'une solutions
	{
		ArrayList<Instruction> instrs = new ArrayList<Instruction>();
		int i = 1;
		while(i <=  pEval.getNbreInst())
		{
			Instruction curInstr =  new Instruction();
			curInstr.setNum(1);
			getInsctruction(pEval.getRootSol(),curInstr, i);
			curInstr.setNum(i);
			String name = curInstr.getNoeud().getNodeName(); 
			if (name.equals("Dec")||name.equals("Body")||name.equals("Var"))
				curInstr.setReconue(true);
			else 
				curInstr.setReconue(false);
			instrs.add(curInstr);
			i++;
		}
		pEval.setListeInstruction(instrs);
	}
	public ArrayList<Instruction> getInstSolution(Element pRoot)
	// permet de charger toutes les intructions d'une solutions
	{
		entierCouter nbreInst = new entierCouter();
		getInstrctionNb(pRoot,nbreInst);
		//curEval.setNbreInst(nbreInst.getVal());
		ArrayList<Instruction> instrs = new ArrayList<Instruction>();
		int i = 1;
		while(i <= nbreInst.getVal())
		{
			Instruction curInstr =  new Instruction();
			curInstr.setNum(1);
			getInsctruction(pRoot,curInstr, i);
			curInstr.setNum(i);
			String name = curInstr.getNoeud().getNodeName();
			if(name.equals("Tasks")||name.equals("Constraints"))
				break;
			if (name.equals("Dec")||name.equals("Body")||name.equals("Var"))
				curInstr.setReconue(true);
			else 
				curInstr.setReconue(false);
			
			instrs.add(curInstr);
			i++;
		}
		return instrs;
	}
	public ArrayList<Instruction> getInstSolutionEdit(Element pRoot)
	// permet de charger toutes les intructions d'une solutions
	{
		entierCouter nbreInst = new entierCouter();
		getInstrctionNb(pRoot,nbreInst);
		//curEval.setNbreInst(nbreInst.getVal());
		ArrayList<Instruction> instrs = new ArrayList<Instruction>();
		int i = 1;
		while(i <= nbreInst.getVal())
		{
			Instruction curInstr =  new Instruction();
			curInstr.setNum(1);
			getInsctruction(pRoot,curInstr, i);
			curInstr.setNum(i);
			String name = curInstr.getNoeud().getNodeName();
			if(name.equals("Tasks")||name.equals("Constraints"))
				break;
			if (name.equals("Dec")||name.equals("Body")||name.equals("Var"))
				curInstr.setReconue(true);
			else 
				{
					curInstr.setReconue(false);
					instrs.add(curInstr);
				}
			i++;
		}
		return instrs;
	}
	public void afficherSolInstructions(SolutionEvaluation pEval)
	// permet d'afficher toutes les instrutions d'une solution
	{
		Iterator<Instruction>  it = pEval.getListeInstruction().iterator();
		while(it.hasNext())
		{
			Instruction curInstruction = it.next();
			if(curInstruction.getNoeud().getNodeName().equals("Dec"))
				System.out.print("Line N°  "+curInstruction.getNum()+" : Algorithm");
			else 
				if(curInstruction.getNoeud().getNodeName().equals("Body"))
					System.out.print("Line N°  "+curInstruction.getNum()+" : Begin");
				else 
					System.out.print("Line N°  "+curInstruction.getNum()+" : "+curInstruction.getNoeud().getNodeName());
			
			if (curInstruction.isReconue())
				System.out.println(", reconue.");
			else
				System.out.println(", non reconue.");
		}
		System.out.println("End");
	}
	public void setRecognizedInstructions(SolutionEvaluation pEval, ArrayList<String> pListRec)
	// permet de mettre à jour l'etat des instr données en paramètre de faux  à vrai
	{
		Iterator<String> it = pListRec.iterator();
		while(it.hasNext())
		{
			String curInstruction = it.next();
			pEval.getListeInstruction().get(Integer.parseInt(curInstruction)-1).setReconue(true);
		}
	}
	public boolean alreadyTreated(int pDebPossible, ArrayList<Task> pListRecTask)
	{
		boolean treated = false;
		Iterator<Task> it = pListRecTask.iterator();
		while(it.hasNext() && !treated)
		{
			Task curTask = it.next();
			if(Integer.parseInt(curTask.getDeb())==pDebPossible)
				treated = true;
		}
		return treated;
	} 
	public boolean alreadyImportedDebPossible(int pDebPossible, ArrayList<Instruction> pList)
	{
		boolean treated = false;
		Iterator<Instruction> it = pList.iterator();
		while(it.hasNext() && !treated)
		{
			Instruction curInst = it.next();
			if(curInst.getNum()==pDebPossible)
				treated = true;
		}
		return treated;
	}
	public boolean tryThisModel(Model pModel, SolutionEvaluation pEvaluation)
	// sert à la reconnaissance 
	{	
		//System.out.println("W'll try with : "+pModel.getName());
		
		reinitSolInstStat(pEvaluation.getListeInstruction());
		ArrayList<Task> taches = pModel.getTasks();
		ArrayList<Constraint> contraintes = pModel.getConstraints();
		pEvaluation.setNote(0);
		// reconnaissances des taches
		currentFile = new File(pModel.getPath());
		try {
			currentDoc = builder.parse(currentFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentSolFile = new File(pEvaluation.getXmlPath());
		try {
			currentSolDoc = builder.parse(currentSolFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = currentDoc.getDocumentElement(); // modele
		Element rootSol = currentSolDoc.getDocumentElement(); // solution
		ArrayList<Task> ListOfRecTasks = new ArrayList<Task>();
		ArrayList<Task> ListOfDesiredTasks = pModel.getTasks();
		int possibleDeb = 0;
		int possibleDebClone = 0;
		int fin;
		boolean wanted = false;
		//boolean LocTask = false;
		Task recTask = null;
		entierCouter nbInst = new entierCouter();
		nbInst.setVal(0);
		getInstrctionNb(rootSol, nbInst);
		// S'il n'y a aucune instruction
		if(nbInst.getVal()==0)
		{
			// Le modèle correspondant est le modele vide 
			pEvaluation.setEtat(true);
			pEvaluation.setModel("Modele Vide.");
		}
		else
		{
			//System.out.println("On a "+ ListOfRecTasks.size());
			Iterator itTasks = taches.iterator(); // tant qu'il y a une tache
			while (itTasks.hasNext())
			{
				//Charger la  tache suivante
				Task curTask = (Task) itTasks.next();
				//System.out.println("Recherche de la tache : "+curTask.getName());
				String debTask = curTask.getDeb();
				String finTask = curTask.getFin();
				int curInst = Integer.parseInt(debTask); // on va commencer la reconnaissance à partir d'ici
				/*;
				numLine.setVal(1);
				Instruction inst = new Instruction();
				inst.setNum(1);
				getInsctruction(root, inst, curInst);
				inst.setNum(curInst);
				
				getAllInsctructionWithNumLine(rootSol, samInst,inst.getNoeud().getNodeName(),numLine);
				//afficherInsctructions(samInst);
				/*while (curInst<= Integer.parseInt(finTask) && canIbeMissed(curInst, pModel.getConstraints()))
				{
					curInst++;
				}
				*/
				Instruction inst = new Instruction(); // dans laquelle on va charger l'instruction de debut
				ArrayList<Instruction> samInst = new ArrayList<Instruction>(); // elle va contenir les instructions similaire
																			 //   Ces instructions representeront des débuts possible
				entierCouter numLine = new entierCouter();
				inst.setNum(1);
				getInsctruction(root, inst, curInst);
				inst.setNum(curInst);numLine.setVal(1);
				// Charger les debuts ppossibles (dans la solution)
				// dans le cas o
				//Poue eviter la rechargement de tache 
				// exemple : Pour : lecture, on charge les deb possib : deux pour (lecture et test)
				//           Pour : test, on recharger les deb possibles  : deux pour (lecture et test)
				if(!alreadyImportedDebPossible(curInst, samInst))
					getAllInsctructionWithNumLine(rootSol, samInst,inst.getNoeud().getNodeName(),numLine);
				//afficherInsctructions(samInst);
				boolean res = true;
				boolean desordre ;
				float note = 0;
				ArrayList<String> listeDesordre = new ArrayList<String>();
				ArrayList<String> listeMissed = new ArrayList<String>();
				ArrayList<String> listeReconus = new ArrayList<String>();
				ArrayList<String> dejaVus = new ArrayList<String>();
				//ArrayList<String> listeMissed = new ArrayList<String>();
				// pour tout deb possible
				Iterator<Instruction> itPossibleInst = samInst.iterator();
				while(itPossibleInst.hasNext())
				{
					// on essaye avec ce debut
					Instruction possibleDebInst = (Instruction) itPossibleInst.next();
					possibleDeb = possibleDebInst.getNum();
					possibleDebClone = possibleDeb;
					fin = Integer.parseInt(finTask);
					curInst = Integer.parseInt(debTask);
					int MaxTaskSol ;
					wanted = true; // on suppose que c'est la c'est la tache voulue
					desordre = false;
					//LocTask = false;
					Instruction instMod = new Instruction();
					instMod.setNum(1);
					inst.setNum(curInst);
					//System.out.println("Debut Possible : "+possibleDeb);
					// Si ce deb possible n'a pas été traité, et n'a pas été reconuu
					if(!alreadyTreated(possibleDeb, ListOfRecTasks)&&!pEvaluation.getListeInstruction().get(possibleDeb-1).isReconue())
					{
						
						// Verifier toutes les instructions de la taches
						while (curInst <= fin && wanted)
						{
							
							instMod = new Instruction();
							instMod.setNum(1);
							Instruction instSol = new Instruction();
							instSol.setNum(1);
							getInsctruction(root, instMod, curInst);
							instMod.setNum(curInst);
							getInsctruction(rootSol, instSol, possibleDeb);
							instSol.setNum(possibleDeb);
							 //if(possibleDeb <= pEvaluation.getNbreInst())// ici il faut pa refaire le début
							 //{
								//System.out.println("je ss dans "+instMod.getNoeud().getNodeName());
								//Comprarer les deux instructions
								if (areTheseSameInstruction(instMod, instSol,pModel,pEvaluation)) 
									{
										// Les deux instrcutions sont les memes
										dejaVus.add(Integer.toString(possibleDeb)); // marquer comme déja vue () 
										listeReconus.add(Integer.toString(possibleDeb)); // maruqer comme reconnue
									 	//pEvaluation.getListeInstruction().get(possibleDeb-1).setReconue(true);
									 	possibleDeb ++;
										curInst++;
										continue; 
									}
									else 
										// les deux instructOs ne sont pas les memes 
										// Verifier l'existence d'une inst similaire 
										if ( ! isItMissed(instMod,pModel,pEvaluation,Integer.parseInt(curTask.getDeb()),possibleDebInst.getNum(),dejaVus))//-----------------------------------
										{
											// il est présente
											dejaVus.add(Integer.toString(possibleDeb));
											listeReconus.add(Integer.toString(possibleDeb));
										 	//pEvaluation.getListeInstruction().get(possibleDeb-1).setReconue(true);
											// mentionner le desordre
											if(!desordre)
												{
													desordre = true;
													listeDesordre.add(Integer.toString(instMod.getNum()));
												}
											possibleDeb ++;
											curInst++;
											continue;
										}
										// si elle est absente
										else 
										{
											// les deux instructions sont differentes
											// je dois verifier qu'il n y a pas de contrainte d'absence sur cette inst
										
											// verification des contraintes d'absence
											// il peut aussi y avoir des contraines de desordre
											if(instMod.getNoeud().getNodeName().equals("If")&&instSol.getNoeud().getNodeName().equals("If"))
											{
												if(isThereCondLike(instSol,pModel.getConstraints(),pModel.getVariables(),pEvaluation.getVariables()))
												{
													// il est présente
													dejaVus.add(Integer.toString(possibleDeb));
													listeReconus.add(Integer.toString(possibleDeb));
												 	//pEvaluation.getListeInstruction().get(possibleDeb-1).setReconue(true);
													// mentionner le desordre
													/*if(!desordre)
														{
															desordre = true;
															listeDesordre.add(Integer.toString(instMod.getNum()));
														}*/
													possibleDeb ++;
													curInst++;
													continue;
												}
											}
											if (canIbeMissed(instMod.getNum(),pModel.getConstraints()))//-------------------------------------------------------------
											{
												//possibleDeb ++;
												//System.out.println("Missed :"+curInst);
												listeMissed.add(Integer.toString(curInst));
												curInst++;
												
												continue;
											}
											else
											{
												wanted = false;
												break;
											}	
												
											
										}
							 //else
							 
							/*if(&& ! isItMissed(instMod, pModel, pEvaluation, curInst, fin, possibleDeb))
							{
								
							}*/	
						}
					}
					else 
					{ //System.out.println("ce debut : "+possibleDeb+" est traité : ");
						wanted = false;
					}
					
					//System.out.println("Res rech : "+wanted+ "note tache :"+curTask.getNote()); 
					//System.out.println("Mntnant on a "+ ListOfRecTasks.size());
					if (wanted) // la tache a été reconnue
					{
						//System.out.println("tache : "+curTask.getName() +" reconue  "+curTask.getNote());
						//LocTask = true;
						recTask = new Task();
						
						recTask.setName(curTask.getName());
						recTask.setDeb(Integer.toString(possibleDebClone));
						recTask.setFin(Integer.toString(possibleDeb - 1));
						setRecognizedInstructions(pEvaluation, listeReconus); // signaler ces inst comme reconnues
						recTask.setType(curTask.getType());
						note = Float.parseFloat(curTask.getNote());
						if(desordre)
							note = note - getPenDesordre(pModel.getConstraints(),listeDesordre);
						if(listeMissed.size() != 0)
							note = note - getPenDesordre(pModel.getConstraints(),listeMissed);
						recTask.setNote(Float.toString(note));
						ListOfRecTasks.add(recTask);
						pModel.setNbOfRecTasks(pModel.getNbOfRecTasks()+1);
						//MAJ de la note
						//System.out.println("Note :"+recTask.getNote()+", ancienne "+pEvaluation.getNote()+", Koulech :"+pEvaluation.getNote()+Float.parseFloat(recTask.getNote()));
						pEvaluation.setNote(pEvaluation.getNote()+note);
						// Penalisation de desodre 
						
						//pEvaluation.setEtat(true);
						// faire d'autre choses
						
						//afficherSolInstructions(pEvaluation);
					}
					
				}
				
			}
			//afficherInsctructions(samInst);
			// Verifier le desodre de taches.----------------------------------------------------------------------------------
			pEvaluation.setTaches(ListOfRecTasks);
			//System.out.println(" nb tache reconues : "+pEvaluation.getTaches().size());
			//System.out.println(" nb tache modele : "+pModel.getTasks().size());
			//System.out.println("Mntnant on a "+ ListOfRecTasks.size());
			boolean SolReconnue = true;
			Iterator<Task> itDesTasks = ListOfDesiredTasks.iterator();
			int i = 0;
			while(itDesTasks.hasNext())
			{
				Task itemDesTask = (Task) itDesTasks.next();
				if(!isTaskPresent(itemDesTask.getName(), ListOfRecTasks))
				{
					
					ListOfDesiredTasks.get(i).setReconue(false);
					if(!canThisTaskBeMissed(itemDesTask.getName(), pModel.getConstraints()))
					{
						SolReconnue = false;
					}
					else
					{
						ListOfDesiredTasks.get(i).setCanBeMissed(true);
					}
				}
				else
				{
					ListOfDesiredTasks.get(i).setReconue(true);
				}
				i++;
			}				
			//if (pEvaluation.getTaches().size() >= pModel.getTasks().size())
			if (SolReconnue)
			{
				pEvaluation.setEtat(true);
				pEvaluation.setModel(pModel.getName());
			}
			else 
			{
				pEvaluation.setNote(0);
			}
			//if (pEvaluation.isEtat())
				//{System.out.println("Reconnue.*********************"+ pEvaluation.getNote());}
			//else 
				//System.out.println("Non Reconnue.**********DESOLE**");
		}
		
		
		return pEvaluation.isEtat();
		//return false;
	}
	public boolean tryThisModel_old(Model pModel, SolutionEvaluation pEvaluation)
	// sert à la reconnaissance 
	{	
		//System.out.println("W'll try with : "+pModel.getName());
		
		reinitSolInstStat(pEvaluation.getListeInstruction());
		ArrayList<Task> taches = pModel.getTasks();
		ArrayList<Constraint> contraintes = pModel.getConstraints();
		pEvaluation.setNote(0);
		// reconnaissances des taches
		currentFile = new File(pModel.getPath());
		try {
			currentDoc = builder.parse(currentFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentSolFile = new File(pEvaluation.getXmlPath());
		try {
			currentSolDoc = builder.parse(currentSolFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = currentDoc.getDocumentElement();
		Element rootSol = currentSolDoc.getDocumentElement();
		ArrayList<Task> ListOfRecTasks = new ArrayList<Task>();
		//System.out.println("On a "+ ListOfRecTasks.size());
		Iterator itTasks = taches.iterator();
		while (itTasks.hasNext())
		{
			//Charger la  tache suivante
			Task curTask = (Task) itTasks.next();
			
			//System.out.println("Recherche de la tache : "+curTask.getName());
			String debTask = curTask.getDeb();
			String finTask = curTask.getFin();
			int curInst = Integer.parseInt(debTask); // on va commencer la reconnaissance à partir d'ici
			/*;
			numLine.setVal(1);
			Instruction inst = new Instruction();
			inst.setNum(1);
			getInsctruction(root, inst, curInst);
			inst.setNum(curInst);
			
			getAllInsctructionWithNumLine(rootSol, samInst,inst.getNoeud().getNodeName(),numLine);
			//afficherInsctructions(samInst);
			/*while (curInst<= Integer.parseInt(finTask) && canIbeMissed(curInst, pModel.getConstraints()))
			{
				curInst++;
			}
			*/
			Instruction inst = new Instruction();
			ArrayList<Instruction> samInst = new ArrayList<Instruction>();
			entierCouter numLine = new entierCouter();
			inst.setNum(1);
			getInsctruction(root, inst, curInst);
			inst.setNum(curInst);numLine.setVal(1);
			// Charger les debuts ppossibles (dans la solution)
			// dans le cas o
			if(!alreadyImportedDebPossible(curInst, samInst))
				getAllInsctructionWithNumLine(rootSol, samInst,inst.getNoeud().getNodeName(),numLine);
			//afficherInsctructions(samInst);
			boolean res = true;
			boolean desordre = false;
			float note = 0;
			ArrayList<String> listeDesordre = new ArrayList<String>();
			ArrayList<String> listeMissed = new ArrayList<String>();
			ArrayList<String> listeReconus = new ArrayList<String>();
			ArrayList<String> dejaVus = new ArrayList<String>();
			//ArrayList<String> listeMissed = new ArrayList<String>();
			Iterator<Instruction> itPossibleInst = samInst.iterator();
			while(itPossibleInst.hasNext())
			{
				Instruction possibleDebInst = (Instruction) itPossibleInst.next();
				int possibleDeb = possibleDebInst.getNum();
				int possibleDebClone = possibleDeb;
				int fin = Integer.parseInt(finTask);
				curInst = Integer.parseInt(debTask);
				int MaxTaskSol ;
				boolean wanted = true;
				Instruction instMod = new Instruction();
				instMod.setNum(1);
				inst.setNum(curInst);
				//System.out.println("Debut Possible : "+possibleDeb);
				if(!alreadyTreated(possibleDeb, ListOfRecTasks)&&!pEvaluation.getListeInstruction().get(possibleDeb-1).isReconue())
				{
					while (curInst <= fin && wanted)
					{
						
						instMod = new Instruction();
						instMod.setNum(1);
						Instruction instSol = new Instruction();
						instSol.setNum(1);
						getInsctruction(root, instMod, curInst);
						instMod.setNum(curInst);
						getInsctruction(rootSol, instSol, possibleDeb);
						instSol.setNum(possibleDeb);
						 //if(possibleDeb <= pEvaluation.getNbreInst())// ici il faut pa refaire le début
						 //{
							//System.out.println("je ss dans "+instMod.getNoeud().getNodeName());
							if (areTheseSameInstruction(instMod, instSol,pModel,pEvaluation)) 
								{
									dejaVus.add(Integer.toString(possibleDeb));
									listeReconus.add(Integer.toString(possibleDeb));
								 	//pEvaluation.getListeInstruction().get(possibleDeb-1).setReconue(true);
								 	possibleDeb ++;
									curInst++;
									continue; 
								}
								else 
									if ( ! isItMissed(instMod,pModel,pEvaluation,Integer.parseInt(curTask.getDeb()),possibleDebInst.getNum(),dejaVus))//-----------------------------------
									{
										dejaVus.add(Integer.toString(possibleDeb));
										listeReconus.add(Integer.toString(possibleDeb));
									 	//pEvaluation.getListeInstruction().get(possibleDeb-1).setReconue(true);
										
										if(!desordre)
											{
												desordre = false;
												listeDesordre.add(Integer.toString(instMod.getNum()));
											}
										possibleDeb ++;
										curInst++;
										continue;
									}
									else 
									{
										// les deux instructions sont differentes
										// je dois verifier qu'il n y a pas de contrainte d'absence sur cette inst
									
										// verification des contraintes d'absence
										// il peut aussi y avoir des contraines de desordre
										if (canIbeMissed(instMod.getNum(),pModel.getConstraints()))//-------------------------------------------------------------
										{
											//possibleDeb ++;
											//System.out.println("Missed :"+curInst);
											listeMissed.add(Integer.toString(curInst));
											curInst++;
											
											continue;
										}
										else
										{
											wanted = false;
											break;
										}	
										
									}
						 //else
						 
						/*if(&& ! isItMissed(instMod, pModel, pEvaluation, curInst, fin, possibleDeb))
						{
							
						}*/	
					}
				}
				else 
				{ //System.out.println("ce debut : "+possibleDeb+" est traité : ");
					wanted = false;
				}
				
				//System.out.println("Res rech : "+wanted+ "note tache :"+curTask.getNote()); 
				//System.out.println("Mntnant on a "+ ListOfRecTasks.size());
				if (wanted) // la tache a été reconnue
				{
					//System.out.println("tache : "+curTask.getName() +" reconue  "+curTask.getNote());
					Task recTask = new Task();
					
					recTask.setName(curTask.getName());
					recTask.setDeb(Integer.toString(possibleDebClone));
					recTask.setFin(Integer.toString(possibleDeb - 1));
					setRecognizedInstructions(pEvaluation, listeReconus);
					recTask.setType(curTask.getType());
					// Penalisation de desodre 
					note = Float.parseFloat(curTask.getNote());
					if(desordre)
						note = note - getPenDesordre(pModel.getConstraints(),listeDesordre);
					if(listeMissed.size() != 0)
						note = note - getPenDesordre(pModel.getConstraints(),listeMissed);
					recTask.setNote(Float.toString(note));
					ListOfRecTasks.add(recTask);
					pModel.setNbOfRecTasks(pModel.getNbOfRecTasks()+1);
					//MAJ de la note
					//System.out.println("Note :"+recTask.getNote()+", ancienne "+pEvaluation.getNote()+", Koulech :"+pEvaluation.getNote()+Float.parseFloat(recTask.getNote()));
					pEvaluation.setNote(pEvaluation.getNote()+note);
					//pEvaluation.setEtat(true);
					// faire d'autre choses
					
					//afficherSolInstructions(pEvaluation);
				}
				
			}
		}
		//afficherInsctructions(samInst);
		// Verifier le desodre de taches.----------------------------------------------------------------------------------
		pEvaluation.setTaches(ListOfRecTasks);
		//System.out.println(" nb tache reconues : "+pEvaluation.getTaches().size());
		//System.out.println(" nb tache modele : "+pModel.getTasks().size());
		//System.out.println("Mntnant on a "+ ListOfRecTasks.size());
		if (pEvaluation.getTaches().size() >= pModel.getTasks().size())
		{
			pEvaluation.setEtat(true);
			pEvaluation.setModel(pModel.getName());
		}
		else 
		{
			pEvaluation.setNote(0);
		}
		//if (pEvaluation.isEtat())
			//{System.out.println("Reconnue.*********************"+ pEvaluation.getNote());}
		//else 
			//System.out.println("Non Reconnue.**********DESOLE**");
		
		return pEvaluation.isEtat();
		//return false;
	}
	public boolean isItDejaVu(ArrayList<String> pListeDejaVus,String pNum)
	{
		boolean res = false;
		Iterator<String> it = pListeDejaVus.iterator();
		while(it.hasNext() && !res)
		{
			String item = it.next();
			if(item.equals(pNum)) res = true;
		}
		return res;
	}
	public boolean canIbeDesordred(int pNumInst,ArrayList<Constraint> pContraintes)
	// permet de tester si une instrction peut etre absente dans une solution
	{
		Iterator<Constraint> it = pContraintes.iterator();
		boolean uCan = false;
		//System.out.println("Num : "+pInst.getNum()+", Type : "+pInst.getNoeud().getNodeName());
		//afficherNodeInsctruction(pInst.getNoeud());
		while(it.hasNext() && !uCan)
		{
			Constraint ctr = it.next();
			if (ctr.getType().equals("5") && ((Integer.parseInt(ctr.getNumLig())== pNumInst||ctr.getTaskName().equals(Integer.toString(pNumInst)))))
				{
					uCan = true;
					break;
				}
		}
		return uCan;
	}
	public boolean isThereCondLike(Instruction pInst,ArrayList<Constraint> pConsts, ArrayList<Variable> pVarMod, ArrayList<Variable> pVarSol)
	{
		boolean thereIs = false;
		Iterator<Constraint> it = pConsts.iterator();
		while(it.hasNext())
		{
			Constraint item = it.next();
			if(item.getType().equals("8"))
				if(areTheseSamesExps( item.getExps(), pInst.getExpressions(),pVarMod, pVarSol))
					return true;
		}
		
		return thereIs;
	}
 	public void reinitModelsStat(ArrayList<Model> pListeModels)
	{
	
		Iterator<Model> it = pListeModels.iterator();
		while(it.hasNext())
		{
			Model item = (Model)it.next();
			item.setSeen(false);
			
		}

	}
	public void reinitSolInstStat(ArrayList<Instruction> pListeInst)
	{
	
		Iterator<Instruction> it = pListeInst.iterator();
		while(it.hasNext())
		{
			Instruction item = (Instruction)it.next();
			if(item.getNoeud().getNodeName().equals("Dec")||item.getNoeud().getNodeName().equals("Var")||item.getNoeud().getNodeName().equals("Body"))
				item.setReconue(true);
			else 
				item.setReconue(false);
			
		}

	}
	public String getTextExp(ArrayList<Expression> pListeExp)
	{
		Iterator<Expression> it = pListeExp.iterator();
		
		return getTextExpression(it.next(),pListeExp);
	}
	public String getTextExpression(Expression pExp, ArrayList<Expression> pListeExp)
	{
		if(!isItExpression(pExp.getLeft(), pListeExp))
		{
			if(pExp.getRangLeft().equals(""))
			{
				if (!isItExpression(pExp.getRight(), pListeExp))
				{
					if(pExp.getRangRight().equals(""))
					{
						return pExp.getLeft()+pExp.getOp()+pExp.getRight();
					}
					else
					{
						if(!isItExpression(pExp.getRangRight(), pListeExp))
							return pExp.getLeft()+pExp.getOp()+pExp.getRight()+"["+pExp.getRangRight()+"]";
						else
							return pExp.getLeft()+pExp.getOp()+pExp.getRight()+"["+getTextExpression(getExpFromString(pExp.getRangRight(),pListeExp), pListeExp)+"]";
					}
				}
				else
					return pExp.getLeft()+pExp.getOp()+getTextExpression(getExpFromString(pExp.getRight(),pListeExp),pListeExp);
			}
			else
			{
				if(!isItExpression(pExp.getRangLeft(), pListeExp))
				{
					if (!isItExpression(pExp.getRight(), pListeExp))
					{
						if(pExp.getRangRight().equals(""))
						{
							return pExp.getLeft()+"["+pExp.getRangLeft()+"]"+pExp.getOp()+pExp.getRight();
						}
						else
						{
							if(!isItExpression(pExp.getRangRight(), pListeExp))
								return pExp.getLeft()+"["+pExp.getRangLeft()+"]"+pExp.getOp()+pExp.getRight()+"["+pExp.getRangRight()+"]";
							else
								return pExp.getLeft()+"["+pExp.getRangLeft()+"]"+pExp.getOp()+pExp.getRight()+"["+getTextExpression(getExpFromString(pExp.getRangRight(),pListeExp), pListeExp)+"]";
						}
					}
					else
						return pExp.getLeft()+"["+pExp.getRangLeft()+"]"+pExp.getOp()+getTextExpression(getExpFromString(pExp.getRight(),pListeExp),pListeExp);
				}
				else
				{
					if (!isItExpression(pExp.getRight(), pListeExp))
					{
						if(pExp.getRangRight().equals(""))
						{
							return pExp.getLeft()+"["+getTextExpression(getExpFromString(pExp.getRangLeft(), pListeExp),pListeExp)+"]"+pExp.getOp()+pExp.getRight();
						}
						else
						{
							if(!isItExpression(pExp.getRangRight(), pListeExp))
								return pExp.getLeft()+"["+getTextExpression(getExpFromString(pExp.getRangLeft(), pListeExp),pListeExp)+"]"+pExp.getOp()+pExp.getRight()+"["+pExp.getRangRight()+"]";
							else
								return pExp.getLeft()+"["+getTextExpression(getExpFromString(pExp.getRangLeft(), pListeExp),pListeExp)+"]"+pExp.getOp()+pExp.getRight()+"["+getTextExpression(getExpFromString(pExp.getRangRight(),pListeExp), pListeExp)+"]";
						}
					}
					else
						return pExp.getLeft()+"["+getTextExpression(getExpFromString(pExp.getRangLeft(), pListeExp),pListeExp)+"]"+pExp.getOp()+getTextExpression(getExpFromString(pExp.getRight(),pListeExp),pListeExp);
				}
				
			}
				
		}
		else
		{
			if (!isItExpression(pExp.getRight(), pListeExp))
			{
				if(pExp.getRangRight().equals(""))
				{
					return getTextExpression(getExpFromString(pExp.getLeft(),pListeExp), pListeExp)+pExp.getOp()+pExp.getRight();
				}
				else
				{
					if(!isItExpression(pExp.getRangRight(), pListeExp))
						return  getTextExpression(getExpFromString(pExp.getLeft(), pListeExp),pListeExp)+pExp.getOp()+pExp.getRight()+"["+pExp.getRangRight()+"]";
					else
						return  getTextExpression(getExpFromString(pExp.getLeft(), pListeExp),pListeExp)+pExp.getOp()+pExp.getRight()+"["+getTextExpression(getExpFromString(pExp.getRangRight(),pListeExp), pListeExp)+"]";
				}
			}
			else
				return  getTextExpression(getExpFromString(pExp.getLeft(), pListeExp),pListeExp)+pExp.getOp()+getTextExpression(getExpFromString(pExp.getRight(),pListeExp),pListeExp);
				
		}
		
	}
	public boolean isItExpression (String pExp,ArrayList<Expression> pListeExp)
	// permet de tester si une operande est une expression
	{
	
		Iterator<Expression> it = pListeExp.iterator();
		while(it.hasNext())
		{
			Expression curExp = it.next();
			if(curExp.getName().equals(pExp))
				return true;
		}
		return false;
	}
	public Expression getExpFromString (String pExp,ArrayList<Expression> pListeExp)
	// permet de tester si une operande est une expression
	{
	
		Iterator<Expression> it = pListeExp.iterator();
		while(it.hasNext())
		{
			Expression curExp = it.next();
			if(curExp.getName().equals(pExp))
				return curExp;
		}
		return null;
	}
	public  void addInsctruction(Node pRoot,Instruction pInst,int NumLin )
	// permet de recuerer l'instruction 
	// ligne donnée
	{
		//if (pInst.getNoeud() == null)
		//{
			NodeList children = pRoot.getChildNodes();
			NamedNodeMap attributes;
			for (int i = 0; i < children.getLength(); i++)
			{
				Node child = children.item(i);
				if (child instanceof Element)
				{
						if (pInst.getNum() >= NumLin && pInst.getNoeud()==null)
						{
							//System.out.println("here :"+pRoot.getNodeName()+" Cl : "+pInst.getNum()+" p :"+NumLin);	
							//System.out.println(" res maint : "+pInst.getNoeud()+"	Noeud :"+child.getNodeName());
							pInst.setNoeud(child);
							getInsctructionExpressions(pInst);							//System.out.println("Res est devenu: "+pInst.getNoeud()+"	noeud :"+child.getNodeName()+" Cl : "+pInst.getNum()+" p :"+NumLin);
	
							break;
						}
						else 
						{
							pInst.setNum(pInst.getNum()+1);
							if (child.getNodeName()=="Solution"||child.getNodeName()=="Dec"||child.getNodeName()=="Body"||child.getNodeName()=="For" || child.getNodeName()=="If" ||child.getNodeName()=="While" ||child.getNodeName()=="Else" )
							{ 
								//System.out.println(" NewAppel :"+child.getNodeName()+" Cl : "+pInst.getNum()+" p :"+NumLin);
								//
								getInsctruction(child,pInst,NumLin);
							}	
						}	
				}
			}

	//	}
		
	}
	
	
	
	
	public ArrayList<String> modelsPath; // Tableau contenant les models (chemins)
	public	ArrayList<String> solutionsPath;
	public ArrayList<Model> models;
	public ArrayList<SolutionEvaluation> evaluations;
	ArrayList<String> criticalLig; // Tableau contenant les lignes critiques
	FileReader modelSourceCode = null;
	FileReader solSourceCode = null;
	BufferedReader modelTampon = null;
	BufferedReader solTampon = null;
	private final String tableauNombres = "Tableau de nombres";
	private final String tableauCaract = "Tableau de carateres";
	//ArrayList<String> varNames; // Tableau contenant les noms de variables
	ArrayList<Variable> variables; // Tableau contenant les noms de variables
	ArrayList<Variable> solVariables; // Tableau contenant les noms de variables
	private DocumentBuilder builder;
	private Document currentDoc;
	private Document currentSolDoc;
	private File currentFile;
	private File currentSolFile;
	private entierCouter NumLine;
	public enum keyWord { 	//les mots cles
							Algorithme, Var, Nombre, Const,tableauNombres, tableauCaract, Booleen, Debut, Fin, Lire, Ecrire, Si, Sinon,DebutSi,
							FinSi, Pour, DebutPour, FinPour,Tantque,DebutTq, FinTq, Faire;
						}
	public enum Instructions
	{
		Read, Write, Affectation, If, For,While; 
	}
	public enum paramTask {}
}
