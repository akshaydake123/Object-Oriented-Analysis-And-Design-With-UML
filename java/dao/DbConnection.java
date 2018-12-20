package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DbConnection {
 
public Connection getConnection() throws Exception
{
try
{
//String connectionURL =  "jdbc:mysql://localhost:3306/login";
String connectionURL =  "jdbc:mysql://localhost:3306/AmazonDB";
Connection connection = null;
Class.forName("com.mysql.jdbc.Driver").newInstance();
connection = DriverManager.getConnection(connectionURL, "root", "root");
//System.out.println(connection);
return connection;
}
catch (SQLException e)
{
System.out.println("I am coding here");
throw e;
}
catch (Exception e)
{
	System.out.println("coding");
throw e;

}
}
 
}
