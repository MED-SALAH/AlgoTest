package recognizer;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

// Instruction pour
public class ForInstruction extends Instruction{
	public ForInstruction(Instruction pInstruction)
	{
		this.setNoeud(pInstruction.getNoeud());
		this.setNum(pInstruction.getNum());
		this.setExpressions(pInstruction.getExpressions());
		
		NamedNodeMap attributes = this.getNoeud().getAttributes();
		// Recuperation des attributs de l'instr1
		for (int k  = 0; k < attributes.getLength(); k++)
		{
			Node attribute = attributes.item(k);
			String name = attribute.getNodeName();
			String value = attribute.getNodeValue();
			if (name == "var")
			{
				this.var = value;
			}			
			else
				if (name == "valInitiale")
				{
					this.valInit = value;
				}	
				else
					if (name == "cond")
					{
						this.cond = value;
					}	
					else
						if (name == "pas")
						{
							this.pas = value;
						}	
		}
	}
	
	
	
	public String getVar() {
		return var;
	}
	public void setVar(String var) {
		this.var = var;
	}
	public String getValInit() {
		return valInit;
	}
	public void setValInit(String valInit) {
		this.valInit = valInit;
	}
	public String getCond() {
		return cond;
	}
	public void setCond(String cond) {
		this.cond = cond;
	}
	public String getCondText() {
		return condText;
	}
	public void setCondText(String condText) {
		this.condText = condText;
	}
	public String getPas() {
		return pas;
	}
	public void setPas(String pas) {
		this.pas = pas;
	}
	public ArrayList<Expression> getExpressions() {
		return expressions;
	}
	public void setExpressions(ArrayList<Expression> expressions) {
		this.expressions = expressions;
	}



	private String var;
	private String valInit;
	private String cond;
	private String condText;
	private String pas;
	private ArrayList<Expression> expressions;
}
