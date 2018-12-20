package pojo;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlAccessorType
@XmlType(propOrder= {"category","subcategory"})
public class CategoryVO {
	public String category,subcategory;
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		return category;
	}
	public void setsubCategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public String getsubCategory() {
		return subcategory;
	}
}