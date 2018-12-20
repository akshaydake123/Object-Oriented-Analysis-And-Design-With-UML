package pojo;

import javax.xml.bind.annotation.*;


@XmlRootElement
@XmlType(propOrder= {"itemid","category","subcategory","productid","title","brand","color","weight","price",
"size","quantity","warranty","used_fresh","discount","image1","sellername"})
public class ItemVO {
public int itemid;
public String category;
public String subcategory;
public int productid;
public String title;
public String brand;
public String color;
public String weight;
public float price;
public String size;
public int quantity; 
public String warranty;
public String used_fresh;
public float discount; 
//public String description;
public String image1;
public String sellername;
public float avgrating;
public int att1;
public String att2;
public float sum;
public int count;

public float getSum() {
	return sum;
}
public void setSum(float sum) {
	this.sum = sum;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public int getAtt1() {
	return att1;
}
public void setAtt1(int att1) {
	this.att1 = att1;
}
public String getAtt2() {
	return att2;
}
public void setAtt2(String att2) {
	this.att2 = att2;
}

public int getItemId() {
return itemid;
}
public void setItemId(int itemid) {
this.itemid = itemid;
}
public void setCategory(String category) {
this.category = category;
}
public String getCategory() {
return category;
}
public void setSubCategory(String subcategory) {
this.subcategory = subcategory;
}public String getSubCategory() {
return subcategory;
}
public void setProductId(int productid) {
this.productid =productid ;
}
public int getProductId() {
return productid;
}
public void setBrand(String brand) {
this.brand = brand;
}
public String getBrand() {
return brand;
}
public void setColor(String color) {
this.color = color;
}
public String getColor() {
return color;
}
public void setWeight(String weight) {
this.weight = weight;
}
public String getWeight() {
return weight;
}
public void setPrice(float price) {
this.price = price;
}
public float getPrice() {
return price;
}
public void setSize(String size) {
this.size =size;
}
public String getSize() {
return size;
}
public void setQuantity(int quantity) {
this.quantity = quantity;
}
public int getQuantity() {
return quantity;
}
public void setWarranty(String warranty) {
this.warranty = warranty;
}
public String getWarranty() {
return warranty;
}
public void setUsedFresh(String used_fresh) {
this.used_fresh = used_fresh;
}
public String getUsedFresh() {
return used_fresh;
}
public void setDiscount(float discount) {
this.discount = discount;
}
public float getDiscount() {
return discount;
}

public void setImage1(String image1) {
this.image1 = image1;
}
public String getImage1() {
return image1;
}
public void setTitle(String title) {
this.title = title;
}
public String getTitle() {
return title;
}

public void setsellername(String sellername) {
this.sellername = sellername;
}
public float getAvgrating() {
	return avgrating;
}
public void setAvgrating(float avgrating) {
	this.avgrating = avgrating;
}
public String getsellername() {
return sellername;
}

}


