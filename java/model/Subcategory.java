package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Subcategory
{
	private String subcategory;
	
	public Subcategory()
	{}
	
	public Subcategory(String a)
	{
		this.subcategory = a;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
}
