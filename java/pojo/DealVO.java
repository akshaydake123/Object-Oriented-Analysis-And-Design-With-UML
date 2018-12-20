package pojo;

import java.util.Date;

import javax.xml.bind.annotation.*;


@XmlRootElement
@XmlType(propOrder= {"itemid","category","subcategory","productid","title","brand","color","weight","price",
"size","quantity","warranty","used_fresh","discount","image1","sellername","dealtype","initialquantity"})
public class DealVO {
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
public int dealtype;
public int initialquantity;
public Date startDate;
public Date endDate;
public int dealid;
public float avgrating;
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
public float getAvgrating() {
	return avgrating;
}
public void setAvgrating(float avgrating) {
	this.avgrating = avgrating;
}
public int getDealid() {
	return dealid;
}
public void setDealid(int dealid) {
	this.dealid = dealid;
}
public Date getStartDate() {
	return startDate;
}
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}
public Date getEndDate() {
	return endDate;
}
public void setEndDate(Date endDate) {
	this.endDate = endDate;
}
public int getItemid() {
	return itemid;
}
public void setItemid(int itemid) {
	this.itemid = itemid;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getSubcategory() {
	return subcategory;
}
public void setSubcategory(String subcategory) {
	this.subcategory = subcategory;
}
public int getProductid() {
	return productid;
}
public void setProductid(int productid) {
	this.productid = productid;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public String getWeight() {
	return weight;
}
public void setWeight(String weight) {
	this.weight = weight;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}
public String getSize() {
	return size;
}
public void setSize(String size) {
	this.size = size;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public String getWarranty() {
	return warranty;
}
public void setWarranty(String warranty) {
	this.warranty = warranty;
}
public String getUsed_fresh() {
	return used_fresh;
}
public void setUsed_fresh(String used_fresh) {
	this.used_fresh = used_fresh;
}
public float getDiscount() {
	return discount;
}
public void setDiscount(float discount) {
	this.discount = discount;
}
public String getImage1() {
	return image1;
}
public void setImage1(String image1) {
	this.image1 = image1;
}
public String getSellername() {
	return sellername;
}
public void setSellername(String sellername) {
	this.sellername = sellername;
}
public int getDealtype() {
	return dealtype;
}
public void setDealtype(int dealtype) {
	this.dealtype = dealtype;
}
public int getInitialquantity() {
	return initialquantity;
}
public void setInitialquantity(int initialquantity) {
	this.initialquantity = initialquantity;
}



}
