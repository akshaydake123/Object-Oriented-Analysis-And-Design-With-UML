package model;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojo.ItemVO;
import pojo.QueryStatus;
import pojo.UserV1;
import pojo.UserVO;
 
import dao.DbConnection;

import dao.LoginHandler;
 
public class SecurityManager {
 
public ArrayList<UserVO> getAllUsersList()throws Exception {
ArrayList<UserVO> userList = null;
try {
DbConnection database= new DbConnection();
Connection connection = database.getConnection();
LoginHandler loginHandler= new LoginHandler();
userList= loginHandler.getAllUsers(connection);

} catch (Exception e) {
throw e;
}
return userList;
}

public List<Subcategory> getsubcategory(String n)
{
	List<Subcategory> list = new ArrayList();
	String sql = "select subcategory from ItemCategory where category= '"+n+"'";
	
	try
	{
		DbConnection database= new DbConnection();
		Connection con = database.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		int i=0;
		while(rs.next())
		{
			Subcategory a = new Subcategory();
			a.setSubcategory(rs.getString(1));
			list.add(a);
		}
	}
	
	catch(Exception e)
	{
		System.out.println(e);
	}
	return list; 
}

//public List<String> getcategory()
public List<Category> getcategory()
{
	List<Category> list = new ArrayList();
	String sql = "select distinct(category) from ItemCategory";
	
	try
	{
		DbConnection database= new DbConnection();
		Connection con = database.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		int i=0;
		while(rs.next())
		{
			Category a = new Category();
			a.setCategory(rs.getString(1));
			list.add(a);
		}
		con.close();
	}
	
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	return list; 
}


public  String checkUserNameValidity(String useremail) throws Exception{
	//ArrayList<UserV1> userList = null;
	String userList="";
	try {
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		LoginHandler loginHandler= new LoginHandler();
		userList=loginHandler.checkUserNameValidity(connection,useremail);
		return userList;
		} catch (Exception e) {
		throw e;
		}
}

public boolean addUser(String username,String mobnumber,String emailid,String password) throws Exception{
	try {
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		LoginHandler loginHandler= new LoginHandler();
		int rows=loginHandler.addUser(connection,username,mobnumber,emailid,password);
		connection.close();
			if(rows==0)
				return false;
			else
				return true;
		} catch (Exception e) {
		throw e;
		}
}

public boolean addSeller(String name,String al1,String al2,String city,String state,String pincode,String mobnumber,String emailid,String password) throws Exception{
	try {
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		LoginHandler loginHandler= new LoginHandler();
		int rows=loginHandler.addSeller(connection,name,al1,al2,city,state,pincode,mobnumber,emailid,password);
		connection.close();
			if(rows==0)
				return false;
			else
				return true;
			
		} catch (Exception e) {
		throw e;
		}
}
 
//Upload Code	
public String uploadFile(String filepath,String fileName,String und) throws Exception{
	DbConnection database= new DbConnection();
	Connection conn = database.getConnection();
	String reply=null; 
	String querySetLimit = "SET GLOBAL max_allowed_packet=104857600;";  // 10 MB
     Statement stSetLimit = conn.createStatement();
     stSetLimit.execute(querySetLimit);
     String way="http://127.0.0.1:8887/upload/";
     String gdd = filepath.substring(filepath.lastIndexOf('/')+1);
     String combine =way+gdd;
     String sql = "UPDATE amazon SET user_photo =" +"'"+ combine + "'"+" WHERE user_name = " + "'"+und +"'"+";";
     System.out.println(sql);
     PreparedStatement statement = conn.prepareStatement(sql);
    // statement.setString(1,"virat");
    // statement.setString(1, fileName.toString());
     //InputStream inputStream = new FileInputStream(new File(filepath));

    // statement.setString(2, filepath);

     int row = statement.executeUpdate();
     if (row > 0) {
         reply = "A contact was inserted with photo image.";
        return reply;
     }
     conn.close();
     //inputStream.close();
	return reply;
}









public HashMap<String, String> displayPic(String username) throws Exception{
	UserVO a = new UserVO();
	 ArrayList<String> list=new ArrayList<String>();
	 HashMap<String, String> hmap = new HashMap<String, String>();
	DbConnection database= new DbConnection();
	Connection conn = database.getConnection();
	  System.out.println("Creating statement...");
	  Statement stmt = null;
	  String path="";
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT * FROM amazon where user_name = " + "'"+ username + "'"+";";
     
     // sql="SELECT * FROM amazon";
      System.out.println(sql);
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
     
      if(rs.next())
		{
    	    String a1=rs.getString("user_name");
    	    String b=rs.getString("user_password");
    	    String c=rs.getString("user_mobnumber");
    	    String d=rs.getString("user_emailid");
    	    String e=rs.getString("user_photo");
    	  String e1 = e.substring(e.lastIndexOf('/')+1);
    	  System.out.println(e1);
    	    System.out.println(a1);
    	    System.out.println(b);
    	    System.out.println(c);
    	    System.out.println(d);
    	    System.out.println(e);
			a.setUsername(a1);
			a.setPassword(b);
			
			a.setMobNumber(c);
			a.setEmailId(d);
			a.setPicPath(e);
			hmap.put("username", a1);
			hmap.put("password", b);
			hmap.put("mobnumber", c);
			hmap.put("emailid", d);
			hmap.put("picpath", e);
			
			
			
			return hmap;
			
		}
   
       //  System.out.print(" picpath : " + path);
       
      
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
     // return a;
	return hmap;
}

public Map<String, Object> displayHome() throws Exception{
	UserVO a = new UserVO();
	 ArrayList<String> list=new ArrayList<String>();
	 HashMap<String, String> hmap = new HashMap<String, String>();
	DbConnection database= new DbConnection();
	Connection conn = database.getConnection();
	  System.out.println("Creating statement...");
	  Statement stmt = null;
	  String path="";
      stmt = conn.createStatement();
      String sql;
     // sql = "SELECT * FROM users where username = " + "'"+ username + "'"+";";
     
      sql="SELECT * FROM amazon";
      System.out.println(sql);
      ResultSet rs = stmt.executeQuery(sql);
      Map<String, String> map = new HashMap<String, String>();
      Map<String, Object> p = new HashMap<String, Object>();
      //STEP 5: Extract data from result set
      List<String> l = new ArrayList<String>();
      List<String> l1 = new ArrayList<String>();
      while(rs.next())
		{
    	  
    	    String a1=rs.getString("user_name");
    	    String b=rs.getString("user_password");
    	    String c=rs.getString("user_mobnumber");
    	    String d=rs.getString("user_emailid");
    	    String e=rs.getString("user_photo");
          
    	     System.out.println("Hello");
    	     
    	     map.put(a1, e);
			p.put("share", map);
			
		}
	
       //  System.out.print(" picpath : " + path);
       
    //  hmap.put("username","exit");
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
     // return a;
  	return p;
}


public boolean addItem(String category,String subcategory,int pid,String title,String brand,String color,
		String weight,float price,String size,int quantity,String warranty,String used_fresh,float discount,
		String filter1,String value1,String filter2,String value2,String filter3,String value3,String description,String selleremail)
{
	// TODO Auto-generated method stub
	try 
	{
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		LoginHandler loginHandler= new LoginHandler();
		System.out.println("SecurityManager:"+discount);
		int rows=loginHandler.addItem(connection,category,subcategory,pid,title,brand,color,weight,price,size,quantity,warranty,used_fresh,
				discount,filter1,value1,filter2,value2,filter3,value3,description,selleremail);
		//System.out.println(rows);
		 if(rows==0)
				return false;

	} 
	catch (Exception e)
{		//throw e;}
	
}
	return true;


}

public Map<String, String> displayItem(int id) throws Exception{
	UserVO a = new UserVO();
	 ArrayList<String> list=new ArrayList<String>();
	 HashMap<String, String> hmap = new HashMap<String, String>();
	DbConnection database= new DbConnection();
	Connection conn = database.getConnection();
	  System.out.println("Creating statement...");
	  Statement stmt = null;
	  String path="";
      stmt = conn.createStatement();
      String sql;
     sql = "SELECT * FROM items where productid = " + "'"+ id + "'"+";";
     
     // sql="SELECT * FROM amazon";
      System.out.println(sql);
      ResultSet rs = stmt.executeQuery(sql);
      Map<String, String> map = new HashMap<String, String>();
      Map<String, Object> p = new HashMap<String, Object>();
      //STEP 5: Extract data from result set
      List<String> l = new ArrayList<String>();
      List<String> l1 = new ArrayList<String>();
      while(rs.next())
		{
    	  
    	    String a1=rs.getString("item_title");
    	    String b=rs.getString("item_price");
    	    String m=rs.getString("item_discreption");
    	    String n=rs.getString("seller_emailid");
    	    String c=rs.getString("item_image1");
    	    String d=rs.getString("item_image2");
    	    String e=rs.getString("item_image3");
          String a4="item_title1";
    	    String a2="item_title2";
          String a3="item_title3";
    	     System.out.println("Hello");
    	     System.out.println(a1);
    	     System.out.println(b);
    	     System.out.println(c);
    	     System.out.println(d);
    	     System.out.println(e);
    	     map.put("key1", c);
    	     map.put("key2", d);
    	     map.put("key3", e);
    	     map.put("key4", a1);
    	     map.put("key5", b);
    	     map.put("key6",m);
    	     map.put("key7", n);
    	    
			//p.put("share", map);
			
		}
	
       //  System.out.print(" picpath : " + path);
       
    //  hmap.put("username","exit");
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
     // return a;
  	return map;
}

public String uploaduserimage(String filepath1, String fileName1,String id) throws Exception {
	// TODO Auto-generated method stub
	DbConnection database= new DbConnection();
	Connection conn = database.getConnection();
	String reply=null; 
	System.out.println("User Image:"+id);
	String querySetLimit = "SET GLOBAL max_allowed_packet=104857600;";  // 10 MB
     Statement stSetLimit = conn.createStatement();
     stSetLimit.execute(querySetLimit);
     String way="http://127.0.0.1:8887/upload/";
     String gdd1 = filepath1.substring(filepath1.lastIndexOf('/')+1);
     
     String combine1 =way+gdd1;
     
     //String sql = "UPDATE item SET item_image1 =" +"'"+ combine1 + "'"+ "," + "item_image2 =" +"'"+ combine2 + "'"+ "," + "item_image3 =" +"'"+ combine3 + "'"+ " WHERE seller_emailid = " + "'"+emailID +"'"+";";
     String sql = "UPDATE userprofile SET profilepic =" +"'"+ combine1 + "'"+ " WHERE email = " + "'"+id +"'"+";";
     System.out.println(sql);
     PreparedStatement statement = conn.prepareStatement(sql);
    // statement.setString(1,"virat");
    // statement.setString(1, fileName.toString());
     //InputStream inputStream = new FileInputStream(new File(filepath));

    // statement.setString(2, filepath);

     int row = statement.executeUpdate();
     if (row > 0) {
         reply = "A user is inserted with photo image.";
        return reply;
     }
     conn.close();
     //inputStream.close();
	return reply;
}


public String uploadFile1(String filepath1, String fileName1,String filepath2, String fileName2,String filepath3, String fileName3, int id) throws Exception {
	// TODO Auto-generated method stub
	DbConnection database= new DbConnection();
	Connection conn = database.getConnection();
	String reply=null; 
	String querySetLimit = "SET GLOBAL max_allowed_packet=104857600;";  // 10 MB
     Statement stSetLimit = conn.createStatement();
     stSetLimit.execute(querySetLimit);
     String way="http://127.0.0.1:8887/upload/";
     String gdd1 = filepath1.substring(filepath1.lastIndexOf('/')+1);
     String gdd2 = filepath2.substring(filepath2.lastIndexOf('/')+1);
     String gdd3 = filepath3.substring(filepath3.lastIndexOf('/')+1);
     String combine1 =way+gdd1;
     String combine2 =way+gdd2;
     String combine3 =way+gdd3;
     //String sql = "UPDATE item SET item_image1 =" +"'"+ combine1 + "'"+ "," + "item_image2 =" +"'"+ combine2 + "'"+ "," + "item_image3 =" +"'"+ combine3 + "'"+ " WHERE seller_emailid = " + "'"+emailID +"'"+";";
     String sql = "UPDATE items SET image1 =" +"'"+ combine1 + "'"+ "," + "image2 =" +"'"+ combine2 + "'"+ "," + "image3 =" +"'"+ combine3 + "'"+ " WHERE productId = " + "'"+id +"'"+";";
     System.out.println(sql);
     PreparedStatement statement = conn.prepareStatement(sql);
    // statement.setString(1,"virat");
    // statement.setString(1, fileName.toString());
     //InputStream inputStream = new FileInputStream(new File(filepath));

    // statement.setString(2, filepath);

     int row = statement.executeUpdate();
     if (row > 0) {
         reply = "A contact was inserted with photo image.";
        return reply;
     }
     conn.close();
     //inputStream.close();
	return reply;
}

public Map<String, String> showItem() throws Exception{
	UserVO a = new UserVO();
	 ArrayList<String> list=new ArrayList<String>();
	 HashMap<String, String> hmap = new HashMap<String, String>();
	DbConnection database= new DbConnection();
	Connection conn = database.getConnection();
	  System.out.println("Creating statement...");
	  Statement stmt = null;
	  String path="";
      stmt = conn.createStatement();
      String sql;
     sql = "SELECT * FROM item";
     
     // sql="SELECT * FROM amazon";
      System.out.println(sql);
      ResultSet rs = stmt.executeQuery(sql);
      Map<String, String> map = new HashMap<String, String>();
      Map<String, Object> p = new HashMap<String, Object>();
      //STEP 5: Extract data from result set
      List<String> l = new ArrayList<String>();
      List<String> l1 = new ArrayList<String>();
      while(rs.next())
		{
    	  
    	    String a1=rs.getString("item_title");
    	    String b=rs.getString("item_price");
    	    String c=rs.getString("item_image1");
    	    String d=rs.getString("item_image2");
    	    String e=rs.getString("item_image3");
          String a4="item_title1";
    	    String a2="item_title2";
          String a3="item_title3";
    	     System.out.println("Hello");
    	     System.out.println(a1);
    	     System.out.println(b);
    	     System.out.println(c);
    	     System.out.println(d);
    	     System.out.println(e);
    	     map.put("key1", c);
    	     map.put("key2", d);
    	     map.put("key3", e);
    	     map.put("key4", a1);
    	     map.put("key5", b);
			//p.put("share", map);
			
		}
	
       //  System.out.print(" picpath : " + path);
       
    //  hmap.put("username","exit");
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
     // return a;
  	return map;
}

public String checkAdmin(String UserName,String Password) throws Exception{
	DbConnection database= new DbConnection();
	
	Connection connection = database.getConnection();
	
	String sql="select * from Admin where userName='"+UserName+"' and adminPassword='"+Password+"'";
	Connection conn = database.getConnection();
	 Statement stmt = null;
	stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	if(rs.next()) {
	conn.close();
	return "true";	
	}
	else {
		conn.close();
		return "false";
	}
	
}

public void addCategory(String category,String subcategory) {
	try {
	DbConnection database= new DbConnection();
	Connection conn = database.getConnection();

	String sql="insert into ItemCategory values('"+category+"','"+subcategory+"')";
	
	  PreparedStatement statement = conn.prepareStatement(sql);
	  
	     int row = statement.executeUpdate();
	     conn.close();
	}
	catch(Exception ex) {
		
	}
	
}

public boolean addAddress(String name, String email, String addressline1, String addressline2, String city,
		String state, String postalcode) throws Exception {
	// TODO Auto-generated method stub
	try {
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		LoginHandler loginHandler= new LoginHandler();
		int rows=loginHandler.addAddress(connection,name,email,addressline1,addressline2,city,state,postalcode);
			if(rows==0)
				return false;
			else
				return true;
		} catch (Exception e) {
		throw e;
		}
}

public boolean putTxDetail(String name, String pid, String quantity, String amount) {
	// TODO Auto-generated method stub

	try {
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		LoginHandler loginHandler= new LoginHandler();
		int rows=loginHandler.addTx(connection,name,pid,quantity,amount);
			if(rows==0)
				return false;
			else
				return true;
		} catch (Exception e) {
		
		}
	return false;
	
	
	
}

public void addToCart(String email,String qty,String price,String itemid)  throws Exception{
	try {
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		System.out.println("add to cart");
		int p=Integer.parseInt(qty);
		String sql="select * from cart where email='"+email+"' and itemid='"+itemid+"'";
		 System.out.println("add to cart");
		 PreparedStatement statement = connection.prepareStatement(sql);
		 ResultSet rs= statement.executeQuery(); //WHERE productId = " + "'"+id +"'"+";";
		 if(rs.next()) {
			 int q= rs.getInt("qty")+p;
			 sql= "update cart set qty= "+"'"+q+"'"+"where email='"+email+"' and itemid='"+itemid+"'";
			 statement = connection.prepareStatement(sql);
			  statement.executeUpdate();
		 }
		 else {
			  sql="insert into cart(email,itemid,price,qty) values('"+email+"','"+itemid+"','"+price+"','"+qty+"')";
			  
			 statement = connection.prepareStatement(sql);
			 statement.executeUpdate();
		 }
			 
	     connection.close();
			
		
	}catch(Exception e) {
		throw e;
	}
}

public List<String> getColors(String subcategory)
{
	List<String> list = new ArrayList();
	String sql = "select distinct(color) from Items where subcategory= '"+subcategory+"' " ;
	
	try
	{
		DbConnection database= new DbConnection();
		Connection con = database.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		int i=0;
		while(rs.next())
		{
			String color= rs.getString("color");
			list.add(color);
		}
		con.close();
	}
	
	catch(Exception e)
	{
		System.out.println(e);
	}
	return list; 
}

public List<String> getColors()
{
	List<String> list = new ArrayList();
	String sql = "select distinct(color) from colors" ;
	
	try
	{
		DbConnection database= new DbConnection();
		Connection con = database.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		int i=0;
		while(rs.next())
		{
			String color= rs.getString("color");
			list.add(color);
		}
		con.close();
	}
	
	catch(Exception e)
	{
		System.out.println(e);
	}
	return list; 
}

public QueryStatus addColors(String color)
{
	System.out.println(color);
	String sql = "insert into colors(color) values ('"+color+"')" ;
	QueryStatus qs = new QueryStatus();
	try
	{
		DbConnection database= new DbConnection();
		Connection con = database.getConnection();
		Statement st = con.createStatement();
		int num = st.executeUpdate(sql);
		qs.setNumrows(num);
		con.close();
	}
	
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	return qs;
}
}