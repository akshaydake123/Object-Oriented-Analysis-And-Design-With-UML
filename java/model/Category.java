package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Category
{
	private String category;
	
	public Category()
	{}
	
	public Category(String a)
	{
		this.category = a;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}
	
}
