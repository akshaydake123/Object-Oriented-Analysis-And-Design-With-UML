package pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReceiptVO
{		private String name;
		private String email;
		private String address1;
		private String address2;
		private String city;
		private String state;
		private String postalcode;
		private int itemid;
		private String category;
		private String subcategory;
		private int productid;
		private String title;
		private String brand;
		private String color;
		private String weight;
		private float price;
		private String size;
		private int quantity; 
		private String warranty;
		private String used_fresh;
		private float discount; 
		private String image1;
		private String sellername;
		private String amount;
		private int addresscode;
		private String status;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getAddress1() {
			return address1;
		}
		public void setAddress1(String address1) {
			this.address1 = address1;
		}
		public String getAddress2() {
			return address2;
		}
		public void setAddress2(String address2) {
			this.address2 = address2;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getPostalcode() {
			return postalcode;
		}
		public void setPostalcode(String postalcode) {
			this.postalcode = postalcode;
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
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public int getAddresscode() {
			return addresscode;
		}
		public void setAddresscode(int addresscode) {
			this.addresscode = addresscode;
		}
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
}