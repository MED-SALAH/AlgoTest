package editeurSolution;
import recognizer.*;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Editor {

	public void createSol()
	{
		String path = "";
		currentFilePath = path;
		currentFile = new File(path);
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
		
	}
	private Document currentDoc;
	private File currentFile;
	private String currentFilePath;
	private DocumentBuilder builder;
	private int numCurInst;
	private Model curSoltion;
	//private 
}
