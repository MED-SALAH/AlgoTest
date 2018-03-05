package recognizer;


public class PleaseAnalyseForMe {

	public static void main(String arg[]) 
	{
		Runnable r1 = new AnalyseModel("Analyseur 1");
		Thread Anlyseur1 = 	new Thread(r1);
		Anlyseur1.start();
		Runnable r2 = new AnalyseModel("Analyseur 2");
		Thread Anlyseur2 = 	new Thread(r2);
		Anlyseur2.start();
		
	}
}
