package dao;
 
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import pojo.UserV1;
import pojo.UserVO;
 
public class LoginHandler {
 
public ArrayList<UserVO> getAllUsers(Connection connection) throws Exception {
ArrayList<UserVO> userList = new ArrayList<UserVO>();
try {
PreparedStatement ps = connection
.prepareStatement("SELECT * FROM amazon");
ResultSet rs = ps.executeQuery();
while (rs.next()) {
UserVO uservo = new UserVO();
uservo.setUsername(rs.getString("user_name"));
uservo.setMobNumber(rs.getString("user_mobnumber"));
uservo.setEmailId(rs.getString("user_emailid"));
uservo.setPassword(rs.getString("user_password"));
uservo.setPicPath(rs.getString("user_photo"));
userList.add(uservo);
}
return userList;
} catch (Exception e) {
throw e;
}
}


public int addUser(Connection conn,String username,String mobnumber,String emailid,String password) throws Exception {
	Statement stmt = null;
	try {
		stmt = (Statement) conn.createStatement(); 
	    String sql = "INSERT INTO `amazon`(user_name,user_password,user_mobnumber,user_emailid) VALUE ('"+username+"','"+password+"','"+mobnumber+"','"+emailid+"')";
	    int rows =stmt.executeUpdate(sql);
	    return rows;
	}catch (Exception e) {
		throw e;
	}
}

public String checkUserNameValidity(Connection connection,String useremail) throws Exception {
//ArrayList<UserV1> userList = new ArrayList<UserV1>();
String pwd="";
	try {
		String sql="";
// String uname = request.getParameter("uname");
		sql="SELECT * FROM amazon where user_emailid ="+"'" +useremail+"'"+";";
PreparedStatement ps = connection
.prepareStatement(sql);
// ps.setString(1,uname);
System.out.println(sql);
ResultSet rs = ps.executeQuery();
if(rs.next()) {
	
//UserV1 userv1 = new UserV1();
      pwd =rs.getString("user_password");
    System.out.println(pwd);
}
return pwd;
} catch (Exception e) {
throw e;
}
}


 
}
