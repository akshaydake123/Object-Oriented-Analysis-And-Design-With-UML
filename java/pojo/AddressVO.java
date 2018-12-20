package pojo;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder= {"addresscode","email","address1","address2","city","state","postalcode"})
public class AddressVO {

private int addresscode;	
private String email;
private String address1;
private String address2;
private String city;
private String state;
private String postalcode;


public int getAddressCode() {
return addresscode;
}
public void setAddressCode(int addresscode) {
	//System.out.println("UserVo called"+username);
this.addresscode = addresscode;
}


public String getEmail() {
return email;
}
public void setEmail(String email) {
	//System.out.println("UserVo called"+username);
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

}