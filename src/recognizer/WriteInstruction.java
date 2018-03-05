package recognizer;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class WriteInstruction extends Instruction{
	public WriteInstruction(Instruction pInst)
	{
		this.setNoeud(pInst.getNoeud());
		this.setNum(pInst.getNum());
		NamedNodeMap attributes = this.getNoeud().getAttributes();
		// Recuperation des attributs de l'instr1
		for (int k  = 0; k < attributes.getLength(); k++)
		{
			Node attribute = attributes.item(k);
			String name = attribute.getNodeName();
			String value = attribute.getNodeValue();
			if (name == "value")
			{
				this.value = value;
			}			
			else
				if (name == "range")
				{
					this.range = value;
				}	
		}
	}
	
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	private String value; // la variable
	private String range; // le rang (cas d'un tableau)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
