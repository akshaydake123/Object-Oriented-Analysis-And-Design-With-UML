package pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserVO {

private String username;
private String password;
private String mobnumber;
private String emailid;
private String picpath;
public String getUsername() {
return username;
}
public void setUsername(String username) {
	//System.out.println("UserVo called"+username);
this.username = username;
}
public String getPassword() {
return password;
}
public void setPassword(String password) {
this.password = password;
}
public void setMobNumber(String mobnumber) {
	// TODO Auto-generated method stub
	this.mobnumber = mobnumber; 
	
}
public String getMobNumber() {
return mobnumber;
}
public void setEmailId(String emailid) {
	// TODO Auto-generated method stub
	this.emailid = emailid;
}
public String getEmailId() {
return emailid;
}


public void setPicPath(String picpath) {
	// TODO Auto-generated method stub
	this.picpath = picpath;
}
public String getPicPath() {
	//System.out.println(picpath);
return picpath;
}


}
