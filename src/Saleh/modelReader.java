package Saleh;

import java.io.File;
import java.io.IOException;

import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;


public class modelReader {
/*	public Object getRoot()
	{ 
		return doc.getDocumentElement(); 
	}
	public int getChildCount(Object parent)
	{
		Node node = (Node) parent;
		NodeList list = node.getChildNodes();
		return list.getLength();
	}
	public Object getChild(Object parent, int index)
	{
		Node node = (Node) parent;
		NodeList list = node.getChildNodes();
		return list.item(index);
	}
	public int getIndexOfChild(Object parent, Object child)
	{
		Node node = (Node) parent;
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++)
		if (getChild(node, i) == child)
		return i;
		return -1;
	}
	public boolean isLeaf(Object node)
	{ 
		return getChildCount(node) == 0; 
	}
	public void valueForPathChanged(TreePath path,
	Object newValue) {}

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		if (builder == null)
		{
			DocumentBuilderFactory factory
			= DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
		}
		f = new File("C:\\AMOUCHE_Manel.xml");
		Document doc = builder.parse(f);
		// Analyse
		//Go!
		//
		Element root = doc.getDocumentElement();
		NamedNodeMap attributes = root.getAttributes();
		for (int k  = 0; k < attributes.getLength(); k++)
		{
			Node attribute = attributes.item(k);
			String name = attribute.getNodeName();
			String value = attribute.getNodeValue();
			System.out.println("		"+name+"="+value+", ");			
		}
		NodeList children = root.getChildNodes();
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			if (child instanceof Element)
			{
				//Tous les éléments de la solution
				Element childElement = (Element)child;	
				System.out.println(child.getNodeName());
				//Tous les fils
				NodeList children2 = childElement.getChildNodes(); 
				for (int j = 0; j < children2.getLength(); j++)
				{
					Node child2 = children2.item(j);
					if (child2 instanceof Element)
					{
						Element childElement2 = (Element)child2;
						System.out.println("	"+child2.getNodeName());
						//Tous les attributs
						attributes = childElement2.getAttributes();
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
		}
	}
	private static DocumentBuilder builder;
	private Document doc;
	private static File f;
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 400;
}

