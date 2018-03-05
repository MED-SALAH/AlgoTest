package recognizer;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class AlgoRecognizer {

	
	public static void main(String[] args) 
	{
		
		System.out.println("***********************************************************************");
		System.out.println("*****AlogTest : Un outil d'evaluation automatique des algorithmes******");
		System.out.println("***********************************************************************");
		System.out.println("****************BOUACHA.Ismail, Equipe  : EIAH*************************");
		System.out.println("******************Fait a LIG : Grenoble 2013**************************");
		System.out.println("***********************************************************************");
		//prenons une solution, et essayons de l'evaluer
		//String solution = "C:\\Users\\SALEH\\Desktop\\G1\\AMOUCHE_Manel.xml";
		try {
				ModelAnalyser analyseur = new ModelAnalyser();
				Iterator<String> itEvalPaths = analyseur.solutionsPath.iterator();
				while(itEvalPaths.hasNext())
				{
					String curSolPth = itEvalPaths.next();
					analyseur.evaluateMePlease(curSolPth);
				}
				Iterator<SolutionEvaluation> itEvaluations = analyseur.evaluations.iterator();
				while(itEvaluations.hasNext())
				{
					SolutionEvaluation curEval = (SolutionEvaluation) itEvaluations.next(); 
					System.out.println("	Nom de Solution : "+curEval.getName()+", Resultat de reconnaissance : "+curEval.isEtat()+", Note : "+curEval.getNote()+", Modele correspondant	"+curEval.getModel());
				}
				
				System.out.println("That's all!!!");
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
}
