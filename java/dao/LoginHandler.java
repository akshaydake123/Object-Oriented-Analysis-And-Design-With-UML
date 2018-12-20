package dao;
 
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import pojo.UserV1;
import pojo.UserVO;
 
public class LoginHandler
{
	public ArrayList<UserVO> getAllUsers(Connection connection) throws Exception
	{
		ArrayList<UserVO> userList = new ArrayList<UserVO>();
		try 
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM amazon");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
			{
				UserVO uservo = new UserVO();
				uservo.setUsername(rs.getString("user_name"));
				uservo.setMobNumber(rs.getString("user_mobnumber"));
				uservo.setEmailId(rs.getString("user_emailid"));
				uservo.setPassword(rs.getString("user_password"));
				uservo.setPicPath(rs.getString("user_photo"));
				userList.add(uservo);
			}
			connection.close();
			return userList;
		} 
		catch (Exception e)
		{
			throw e;
		}
	}

public int addUser(Connection conn,String username,String mobnumber,String emailid,String password) throws Exception
{
	Statement stmt = null;
	try 
	{
		stmt = (Statement) conn.createStatement(); 
	    String sql = "INSERT INTO userprofile(name,password,mobileno,email) VALUE ('"+username+"','"+password+"','"+mobnumber+"','"+emailid+"')";
	    int rows =stmt.executeUpdate(sql);
	    //conn.close();
	    return rows;
	}
	catch (Exception e)
	{
		throw e;
	}
}
public int addSeller(Connection conn,String sellername,String al1,String al2,String city,String state,String pincode,String mobnumber,String emailid,String spassword) throws Exception
{
	Statement stmt = null;
	try 
	{
		
		stmt = (Statement) conn.createStatement();
		System.out.println(sellername+" "+al1+" "+al2+" "+city+" "+" "+state+" "+pincode+" "+mobnumber+" "+emailid+" "+spassword);
		String sql = "INSERT INTO seller(sellername,addressline1,addressline2,city,state,postalcode,mobileno,email,sellerpassword) VALUE ('"+sellername+"','"+al1+"','"+al2+"','"+city+"','"+state+"','"+pincode+"','"+mobnumber+"','"+emailid+"','"+spassword+"')";
	    /*String sql = "insert into seller(sellername,addressline1,addressline2,city,state,postalcode,mobileno,email,"
	    		+ "sellerpassword) values (?,?,?,?,?,?,?,?,?)"; 
	    stmt = (PreparedStatement) conn.prepareStatement(sql); 
	    stmt.setString(1,sellername);
	   	stmt.setString(2,al1);
	   	stmt.setString(3,al2);
	   	stmt.setString(4,city);
	   	stmt.setString(5,state);
	   	stmt.setString(6,pincode);
	   	stmt.setString(7,mobnumber);
	   	stmt.setString(8,emailid);
	   	stmt.setString(9,spassword);*/
	   	
	    int rows =stmt.executeUpdate(sql);
	    //conn.close();
	    return rows;
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		throw e;
	}
}
public String checkUserNameValidity(Connection connection,String useremail) throws Exception
{
//ArrayList<UserV1> userList = new ArrayList<UserV1>();
	String pwd="";
	try 
	{
		String sql="";
		// String uname = request.getParameter("uname");
		sql="SELECT * FROM amazon where user_emailid ="+"'" +useremail+"'"+";";
		PreparedStatement ps = connection.prepareStatement(sql);
		// ps.setString(1,uname);
		System.out.println(sql);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) 
		{
			//UserV1 userv1 = new UserV1();
			pwd =rs.getString("user_password");
			System.out.println(pwd);
		}
		connection.close();
		return pwd;
	} 
	catch (Exception e) 
	{
		throw e;
	}
}


public int addItem(Connection conn,String category,String subcategory,int pid,String title,String brand,String color,
		String weight,float price,String size,int quantity,String warranty,String used_fresh,float discount,
		String filter1,String value1,String filter2,String value2,String filter3,String value3,String description,String selleremail)
{
	
	PreparedStatement stmt = null,stmt1=null;
	try 
	{
		String sql = "insert into Items(category,subcategory,productid,title,brand,color,weight,price,size,quantity,warrantyduration,used_fresh,\r\n" + 
				"discount,filter1,value1,filter2,value2,filter3,value3) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		stmt = (PreparedStatement) conn.prepareStatement(sql);
		
	   //String sql = "insert into `Items`(category,subcategory,productid,title,brand,color,weight,price,size,quantity,warrantyduration,used_fresh,discount,filter1,value1,filter2,value2,filter3,value3) values('"+category+"','"+subcategory+"',pid,'"+title+"','"+brand+"','"+color+"',weight,price,'"+size+"',quantity,'"+warranty+"','"+used_fresh+"','"+discount+"','"+filter1+"','"+value1+"','"+filter2+"','"+value2+"','"+filter3+"','"+value3+"')";
	   	
	   	stmt.setString(1,category);
	   	stmt.setString(2,subcategory);
	   	stmt.setInt(3, pid);
	   	stmt.setString(4,title);
	   	stmt.setString(5,brand);
	   	stmt.setString(6,color);
	   	stmt.setString(7,weight);
	   	stmt.setFloat(8,price);
	   	stmt.setString(9,size);
	   	stmt.setInt(10, quantity);
	   	stmt.setString(11,warranty);
	   	stmt.setString(12,used_fresh);
	   	stmt.setFloat(13,discount);
	   	stmt.setString(14,filter1);
	   	stmt.setString(15,value1);
	   	stmt.setString(16,filter2);
	   	stmt.setString(17,value2);
	   	stmt.setString(18,filter3);
	   	stmt.setString(19,value3);
	   	
	   	int rows =stmt.executeUpdate();
	    System.out.println("Description:"+description);
	    
	    int maxID = 0;
   		java.sql.Statement s2 = conn.createStatement();
   		s2.execute("SELECT MAX(itemid) FROM Items");    
   		ResultSet rs2 = s2.getResultSet(); // 
   		if ( rs2.next() )
   		{
   			maxID = rs2.getInt(1);
   		}
   		
   		//entry in selleritem
   		Statement s3 = (Statement) conn.createStatement();
   		s3.execute("SELECT sellerid from seller where email ='"+selleremail+"'");
   		ResultSet rs3 = s3.getResultSet();
   		int sid = 0;
   		if(rs3.next())
   		{
   			sid = rs3.getInt(1);
   		}
   		System.out.println("sid:"+sid);
   		String sql2 = "insert into selleritem(sellerid,itemid) values(?,?)";
   		stmt1 = (PreparedStatement) conn.prepareStatement(sql2);
   		stmt1.setInt(1,sid);
		stmt1.setInt(2,maxID);
		stmt1.executeUpdate();
   		
	   	if(!(description.equals("NA")))
	   	{
	   		System.out.println("I can do it");
	   		String sql1 = "insert into itemattribute(itemid,attribute,value) values(?,?,?)";
	   		stmt1 = (PreparedStatement) conn.prepareStatement(sql1);
	   		
	   		String[] str1 = description.split(",");	   	
	   		System.out.println("LoginHandler:"+rows+ " max:"+maxID);
	   		for(int i=0;i<str1.length;i++)
	   		{
	   			String[] str2 = str1[i].split(":");
	   			String str3 = str2[0];
	   			String str4 = str2[1];
	   			System.out.println(str3+" "+str4);
	   			stmt1.setInt(1,maxID);
	   			stmt1.setString(2,str3);
	   			stmt1.setString(3,str4);
	   			stmt1.executeUpdate();
	   		
	   		}
	   		//System.out.println("LoginHandler:"+rows+ " max:"+maxID);
	   	}
	   	conn.close();
	    return rows;
	}
	
	catch (Exception e)
	{
		//throw e;
		System.out.println(e);
	}
	
	return 0;
}

public int addAddress(Connection connection, String name, String email, String addressline1, String addressline2,
		String city, String state, String postalcode) throws Exception {
	// TODO Auto-generated method stub
	String d1="null",d2="null";
	Statement stmt = null;
	try 
	{
		stmt = (Statement) connection.createStatement(); 
	    String sql = "INSERT INTO useraddress(Email,AddressLine1,AddressLine2,City,State,Postalcode,att1,att2)  VALUES('"+email+"','"+addressline1+"','"+addressline2+"','"+city+"','"+state+"','"+postalcode+"','"+d1+"','"+d2+"')";
	     System.out.println(sql);
	    int rows =stmt.executeUpdate(sql);
	    connection.close();
	    return rows;
	}
	catch (Exception e)
	{
		throw e;
	}
}

public int addTx(Connection connection, String name, String pid, String quantity,String amount) {
	// TODO Auto-generated method stub

	Statement stmt = null;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDate localDate = LocalDate.now();
	String date = dtf.format(localDate);
	int quan=0;
	try 
	{
		/*stmt = (Statement) connection.createStatement(); 
		
	    String sql = "INSERT INTO transactions(Email,ItemID,quantity,TransactionDate,Amount)  VALUES('"+name+"','"+pid+"','"+quantity+"','"+date+"','"+amount+"')";
	     System.out.println(sql);
	    int rows =stmt.executeUpdate(sql);
	    connection.close();
	    return rows;*/
		
		stmt = (Statement) connection.createStatement();
		String status = "Payment Completed";
        

        String sql = "INSERT INTO transactions(Email,ItemID,quantity,TransactionDate,Amount,status)  VALUES('"+name+"','"+pid+"','"+quantity+"','"+date+"','"+amount+"','"+status+"')";

         System.out.println(sql);

        int rows =stmt.executeUpdate(sql);

       

        String sql1="select Quantity from items where ItemId="+"'" +pid+"'"+";";

     

           PreparedStatement ps1=(PreparedStatement) connection.prepareStatement(sql1);

           ResultSet rs1 = ps1.executeQuery();

          

           if(rs1.next())

           {  quan = rs1.getInt("Quantity");

           }

          

           int qww = Integer.parseInt(quantity);

          

           int netquantity = quan-qww;

          

           String net = new Integer(netquantity).toString();

          

           String sql3 = "UPDATE items SET Quantity =" +"'"+net + "'"+ " WHERE ItemId = " + "'"+pid +"'"+";";

           PreparedStatement statement = connection.prepareStatement(sql3);

         



         int row = statement.executeUpdate();

          

           connection.close();



        return rows;
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
	}
	return 0;
}

}

 

