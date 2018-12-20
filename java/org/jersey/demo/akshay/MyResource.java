package org.jersey.demo.akshay;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;

import org.json.JSONObject;

import com.sun.jersey.api.view.Viewable;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.BodyPartEntity;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataParam;

import model.Category;
import model.Manager;
import model.SecurityManager;
import model.Subcategory;
import pojo.AddressVO;
import pojo.CardVO;
import pojo.CategoryVO;
import pojo.DealVO;
import pojo.ItemVO;
import pojo.QueryStatus;
import pojo.ReceiptVO;
import pojo.StatusVO;
import pojo.UserV1;
import pojo.UserVO;
 
@Path("/webservice")
public class MyResource  {
	  
	public static String und="";
	public static String emailID="";
	public static int id;
	public static String useremail=" ";
	//static String h ="benzos.byethost5.com/Demo/upload/";
	public static final String UPLOAD_FILE_SERVER = "C:/Demo/upload/";
	
	
	@GET
	@Path("/addColors/{color}")
    @Produces(MediaType.APPLICATION_JSON)
	public QueryStatus addcolors(@PathParam("color") String color)
    {
		System.out.println("Color: "+color);
		SecurityManager sm1=new SecurityManager();
		QueryStatus list = sm1.addColors(color);
		return list;
    }
	
	@GET
	@Path("/getColors")
    @Produces(MediaType.APPLICATION_JSON)
	public List<String> getcolors()
    {
		SecurityManager sm1=new SecurityManager();
		List<String> list = sm1.getColors();
		//for(int i=0;i<list.size();i++)
		//	System.out.println(list.get(i));
		return list;
    }
	
	@GET
	@Path("/loadColors/{subcat}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<String> getcolors(@PathParam("subcat") String subcategory)
    {
		
		System.out.println(subcategory);
		SecurityManager sm1=new SecurityManager();
		List<String> list = sm1.getColors(subcategory);
		for(int i=0;i<list.size();i++)
			System.out.println(list.get(i));
		return list;
    }
	
	@GET
	@Path("/getBalance/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public int getBalance(@PathParam("email")String email) throws Exception {
		Manager mgr=new Manager();
		int balance = mgr.getBalance(email);
		System.out.println(balance);
		return balance;

	}
	
	@GET
	@Path("/addMoney/{email}/{balance}")
	@Produces(MediaType.APPLICATION_XML)
	public String addMoney(@PathParam("email") String email,@PathParam("balance")int balance) throws Exception {
		Manager mgr=new Manager();
		String res  = mgr.addMoney(email,balance);
		System.out.println("AddMoney:"+balance);
		return res;

	}
	
	@GET
	@Path("/getCartItems/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ItemVO> getItemDetails(@PathParam("email") String n) throws Exception {
		//System.out.println("devesh");
    	Manager mgr=new Manager();
    	ArrayList<ItemVO> itemvo=mgr.getCartDetails(n);
    	return itemvo;
    }
	
	@GET
	@Path("/updateqtyCartItems/{addresscode}/{quantity}")
    @Produces(MediaType.APPLICATION_JSON)
    public void updatequantity(@PathParam("addresscode") String n,@PathParam("quantity") String qty) throws Exception {
		//System.out.println("devesh");
    	Manager mgr=new Manager();
    	 mgr.updateqty(n,qty);
  
    }
	
	@GET
	@Path("/removeCartItems/{ids}/{mail}")
    @Produces(MediaType.APPLICATION_JSON)
    public void getItemDetails(@PathParam("ids") String n,@PathParam("mail") String email) throws Exception {
		//System.out.println("devesh");
    	Manager mgr=new Manager();
    	 mgr.removeCartItems(n,email);
  
    }
	
	@GET
	@Path("/addToCart/{email}/{quantity}/{price}/{itemid}")
	@Produces(MediaType.APPLICATION_JSON)
	public void addToCart(@PathParam("email")String email,@PathParam("quantity")String quantity,@PathParam("price")String price,@PathParam("itemid")String itemid) throws Exception{
		SecurityManager mgr= new SecurityManager();
		mgr.addToCart(email,quantity,price,itemid);
		//System.out.println("juopppppp");
	}
	
	@GET
	@Path("/getItemsbysubcatcolor/{subcat}/{color}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ItemVO> getProductBySubcategory(@PathParam("subcat")String subcat,@PathParam("color")String color) throws Exception {
		//System.out.println(subcat+" "+color);
    	Manager mgr=new Manager();
    	return mgr.getProductBySubcategoryDetail(subcat,color);
 
    }
	
	@GET
	@Path("/getItemsbyprice/{mini}/{maxi}/{subcat}/{color}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ItemVO> getItembyprice(@PathParam("mini")int mini,@PathParam("maxi")int maxi,@PathParam("subcat")String subcat,@PathParam("color")String color) throws Exception {
		System.out.println(mini+" "+maxi);
    	Manager mgr=new Manager();
    	return mgr.getItembyprice(mini,maxi,subcat,color);
 
    }
	
	@GET
	@Path("/getProductDetails/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemVO getProductDetail(@PathParam("name")String name) throws Exception {
		//System.out.println(name);
    	Manager mgr=new Manager();
    	ItemVO itemvo=mgr.getProductDetail(name);
    	return itemvo;
    }
	
	@GET
	@Path("/transferToSeller/{name}/{itemid}")
    @Produces(MediaType.APPLICATION_JSON)
    public QueryStatus transferToSeller(@PathParam("name")String name,@PathParam("itemid")int itemid) throws Exception
	{
		System.out.println("transfer hit");
    	Manager mgr=new Manager();
    	return mgr.transferToSeller(name,itemid);
    }
	
	@GET
	@Path("/getTxDetails/{name}/{addresscode}/{itemid}")
    @Produces(MediaType.APPLICATION_JSON)
    public ReceiptVO getTxDetail(@PathParam("name")String name,@PathParam("addresscode")String addresscode,@PathParam("itemid")String itemid) throws Exception {
		System.out.println("getTxDetails="+name);
    	Manager mgr=new Manager();
    	return mgr.getTxDetail(name,addresscode,itemid);
    
    }
	
	@GET
	@Path("/getUserOrderDetails/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ReceiptVO> getUserOrderDetails(@PathParam("name")String name) throws Exception {
		System.out.println("getTxDetails="+name);
    	Manager mgr=new Manager();
    	return mgr.getUserOrderDetails(name);
    
    }
	
	@GET
	@Path("/updateTxDetails/{name}/{addresscode}")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateTxDetail(@PathParam("name")String name,@PathParam("addresscode")String addresscode) throws Exception {
		System.out.println("updateTxDetails="+name);
    	Manager mgr=new Manager();
    	mgr.updateTxDetail(name,addresscode);
    	//return mgr.getTxDetail(name);
    
    }
	
	@GET
	@Path("/updateBankDetails/{name}/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateBankDetail(@PathParam("name")String name,@PathParam("amount")String amount) throws Exception {
		System.out.println("updateBankDetails="+name);
    	Manager mgr=new Manager();
    	mgr.updateBankDetail(name,amount);
    	//return mgr.getTxDetail(name);
    
    }
	
	@GET
	@Path("/putTxDetails/{name}/{pid}/{quantity}/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public void putTxDetail(@PathParam("name")String name,@PathParam("pid")String pid,@PathParam("quantity")String quantity,@PathParam("amount")String amount) throws Exception {
		//System.out.println(name+" "+pid);
    	SecurityManager mgr=new SecurityManager();
    	 boolean flag = mgr.putTxDetail(name,pid,quantity,amount);
    
    }
	
	@GET
	@Path("/getAllAddress/{name}")
	@Produces(MediaType.APPLICATION_JSON)
    public ArrayList<AddressVO> getAllAddress(@PathParam("name") String name) throws Exception {
		System.out.println("Get All Address");
    
	System.out.println(name);
		Manager mgr=new Manager();
    	ArrayList<AddressVO> addressvo=mgr.getAllAddress(name);
    	return addressvo;
    }
	
	@POST
	@Path("/addAddress")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void addAddress(@FormParam("name") String name,@FormParam("email") String email,@FormParam("addressline1") String addressline1,@FormParam("addressline2") String addressline2,@FormParam("city") String city,@FormParam("state") String state,@FormParam("postalcode") String postalcode) throws Exception
	{
		System.out.println("Just hit");
		SecurityManager mg=new SecurityManager();

		boolean flag=mg.addAddress(name,email,addressline1,addressline2,city,state,postalcode);
		if(flag)
			System.out.println("Successful Registration");
		else
			System.out.println("Failed");
	}
	
	@GET
	@Path("/getaccountinfo/{username}/{amount}")
	@Produces(MediaType.APPLICATION_JSON)
    public CardVO getaccountinfo(@PathParam("username") String cardname,@PathParam("amount") String amount) throws Exception {
		CardVO cv = new CardVO();

		//System.out.println("Hit");
		//System.out.println(cardname);
    
		Manager mgr=new Manager();
     return mgr.getAccountInfo(cardname,amount);
    	
    }
	
	@GET
	@Path("/deleteItem/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteItem(@PathParam("name") int name)throws Exception {
		//System.out.println(name);
    	Manager mgr=new Manager();
    	mgr.deleteItem(name);
    }
	
	@GET
	@Path("/updateItem/{pid}/{cat}/{subcat}/{title}/{brand}/{color}/{weight}/{price}/{size}/{quantity}/{warranty}/{usedfresh}/{discount}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateItem(@PathParam("pid") int pid,@PathParam("cat") String category,@PathParam("subcat") String subcategory,
    		@PathParam("title") String title,@PathParam("brand") String brand,@PathParam("color") String color,
    		@PathParam("weight") String weight,@PathParam("size") String size,@PathParam("price") float price,
    		@PathParam("quantity") int quantity,@PathParam("warranty") String warranty,@PathParam("usedfresh") String usedfresh,
    		@PathParam("discount") float discount)throws Exception {
	//System.out.println("update item");
    	Manager mgr=new Manager();
    	mgr.updateItem(pid,category,subcategory,title,brand,color,weight,size,price,quantity,warranty,usedfresh,discount);
    }
	
	@GET
	@Path("/getItemDetails/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemVO getItemDetail(@PathParam("name")String name) throws Exception {
		//System.out.println(name);
    	Manager mgr=new Manager();
    	ItemVO itemvo=mgr.getItemDetail(name);
    	return itemvo;
    }
	
	@GET
	@Path("/getDealDetails/{dealspid}")
    @Produces(MediaType.APPLICATION_JSON)
    public DealVO getDealDetail(@PathParam("dealspid")String dealspid) throws Exception {
		//System.out.println(dealspid);
    	Manager mgr=new Manager();
    	DealVO dealvo=mgr.getDealDetail(dealspid);
    	return dealvo;
    }
	
	@POST
	@Path("/loginadmin")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
		 public Viewable loginadmin(@FormParam("useremail") String useremail,
				 @FormParam("password") String password) throws Exception{
		SecurityManager se=new SecurityManager();
		if(se.checkAdmin(useremail,password).equals("true"))
		return new Viewable("/admin.jsp");
		else {
			//int flag=1;
			return new Viewable("/adminLogin.jsp");
		}
	}
	
	
	
	@GET
	@Path("/addcategory/{category}/{subcategory}")
    @Produces( MediaType.TEXT_PLAIN)
	public String addsubcat(@PathParam("category") String cat,@PathParam("subcategory") String sub)
    {
		SecurityManager sml=new SecurityManager();
		sml.addCategory(cat, sub); 
		
		return cat+" "+sub;
    }
	
	@GET
	@Path("/getAllDeals")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<DealVO> getDealDetails() throws Exception {
		//System.out.println("Yes!!");
    	Manager mgr=new Manager();
    	ArrayList<DealVO> dealvo=mgr.getDealDetails();
    	return dealvo;
    }
	
	@GET
	@Path("/getAllItems")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ItemVO> getItemDetails() throws Exception {
		//System.out.println("Yes!!");
    	Manager mgr=new Manager();
    	ArrayList<ItemVO> itemvo=mgr.getItemDetails();
    	return itemvo;
    }
	
	@GET
	@Path("/loadsubcategory/{category}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<Subcategory> getsubcat(@PathParam("category") String n)
    {
		SecurityManager sm1=new SecurityManager();
		List<Subcategory> list = sm1.getsubcategory(n);
		
		for (int i = 0; i < list.size(); i++) 
		{
		    Subcategory aName = list.get(i);
		    //System.out.println(aName.getSubcategory());
		}
		
		return sm1.getsubcategory(n);
    }
	
	@GET
	@Path("/loadcategory")
    @Produces(MediaType.APPLICATION_JSON)
	public List<Category> getcat()
    {
		SecurityManager sm1=new SecurityManager();
		List<Category> list = sm1.getcategory();
		
		for (int i = 0; i < list.size(); i++) 
		{
		    Category aName = list.get(i);
		    //System.out.println(aName.getCategory());
		}
		
		return sm1.getcategory();
    }
	
	@POST
	@Path("/upload") 
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Viewable uploadFile(
	        @FormDataParam("uploadFile") InputStream fileInputStream,
	        @FormDataParam("uploadFile") FormDataContentDisposition fileDetail) throws Exception {
		String outputt=null;
		String fileName = null;
        String uploadFilePath = null;
        SecurityManager sm = null;
		try {
            fileName = fileDetail.getFileName();
            System.out.println(fileName);
            uploadFilePath = writeToFileServer(fileInputStream, fileName);
            System.out.println(uploadFilePath);
            sm =new SecurityManager();
            outputt = sm.uploadFile(uploadFilePath,fileName,und);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        finally{
            // release resources, if any
        }
		
		 return new Viewable("/success.jsp", sm.displayPic(und));
		//return fileName+outputt;
	}

private String writeToFileServer(InputStream inputStream, String fileName) throws IOException {
	 
    OutputStream outputStream = null;
    String qualifiedUploadFilePath = UPLOAD_FILE_SERVER + fileName;
    
    System.out.println(qualifiedUploadFilePath);

    try {
        outputStream = new FileOutputStream(new File(qualifiedUploadFilePath));
        int read = 0;
        byte[] bytes = new byte[1024];
        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        outputStream.flush();
    }
    catch (IOException ioe) {
        ioe.printStackTrace();
    }
    finally{
        //release resource, if any
        outputStream.close();
    }
    return qualifiedUploadFilePath;
}


@GET
@Path("loginCheck/{username}")
@Produces(MediaType.APPLICATION_JSON)
public  Viewable checkUserNameValidity(@PathParam("username") String n1) 
{
	SecurityManager sm1=new SecurityManager();
	System.out.println(n1);
	try {
		    
		// = new HashMap<String,String>();
       // model.put("hello", result);
       // model.put("world", "World");
        return new Viewable("/itemlist.jsp", sm1.displayHome());	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}


@GET
@Path("/displayHome")
@Produces(MediaType.APPLICATION_JSON)
public  Viewable  displayHomePage() 
{
	SecurityManager sm1=new SecurityManager();
	//System.out.println(n1);
	try {
		    
		
		// = new HashMap<String,String>();
       // model.put("hello", result);
       // model.put("world", "World");
		System.out.println("got you !");
        return new Viewable("/itemslist.jsp", sm1.displayHome());	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}

@POST
 @Path("/login")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
	 public Viewable login(@FormParam("useremail") String useremail,
			 @FormParam("password") String password) {

		
		
		
		System.out.println("Data Received: " + useremail + password);
         // String s =  getAllUsersList(username, password);
	//	JSONObject jsonObj = new JSONObject(crunchifyBuilder.toString() );
		
	//	String usr=jsonObj.getString("username");
	//	String pwd =jsonObj.getString("password");
	//	String result =  getAllUsersList(username,password);
		
		         String result = checkAllUserList(useremail,password);
    		SecurityManager se=new SecurityManager();
		
			try {
				//return se.displayPic(username);
				/*		System.out.println(result);
				String message = result;
			    URI uri = UriBuilder.fromPath("/home.jsp")
			            .queryParam("message", message)
			            .build();
			      System.out.print(uri);
			    return Response.seeOther(uri).build();
				
			*/	
    		//Map<String, String> model = new HashMap<String,String>();
           // model.put("hello", result);
           // model.put("world", "World");
				if(useremail.equalsIgnoreCase(result))
          return new Viewable("/itemslist.jsp", se.displayHome());	
				//return Response.temporaryRedirect(URI.create("/itemslist.jsp")).build();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
		
		
		
	}
	
/*
	@POST
	@Path("/displayImage")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response displayImage(@FormParam("imagename") String imagename) {
		String result = "Service Successfully started..";
		System.out.println(result);
		String path="";
		SecurityManager mg=new SecurityManager();
		    
				 try {
					path = mg.displayPic();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
				 URI uri = UriBuilder.fromPath("/imagedata.jsp")
				            .queryParam("path", path)
				            .build();
		// return HTTP response 200 in case of success
				 System.out.print(uri.toString());
		return Response.seeOther(uri).build();
	}
*/
@POST
@Path("/addUser")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public void addUser(@FormParam("username") String username,@FormParam("mobnumber") String mobnumber,@FormParam("emailid") String emailid,@FormParam("password") String password) throws Exception
{
	SecurityManager mg=new SecurityManager();
	useremail=emailid;
	boolean flag=mg.addUser(username,mobnumber,emailid,password);
	if(flag)
		System.out.println("Successful Registration");
	else
		System.out.println("Failed");
}
 
@POST
@Path("/addSeller")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public void addSeller(@FormParam("sellername") String sellername,@FormParam("al1") String al1,@FormParam("al2") String al2,@FormParam("city") String city,@FormParam("state") String state,@FormParam("pincode") String pincode,@FormParam("mobnumber") String mobnumber,@FormParam("emailid") String emailid,@FormParam("password") String password) throws Exception
{
	SecurityManager mg=new SecurityManager();
	und=sellername;
	boolean flag=mg.addSeller(sellername,al1,al2,city,state,pincode,mobnumber,emailid,password);
	if(flag)
		System.out.println("Successful Seller Registration");
	else
		System.out.println("Failed Seller Registration");
}

public String getAllUsersList(String username,String password)
 {
 String userListData = null;
 try 
 {
 ArrayList<UserVO> userList = null;
 SecurityManager securityManager= new SecurityManager();
 userList = securityManager.getAllUsersList();
 for (UserVO userVO : userList) {
 if(userVO.getUsername().equals(username))
 {
 if(userVO.getPassword().equals(password))
 {
 return username;
 }
 }
 }
 
} catch (Exception e)
 {
 System.out.println("error");
 }
 return "You are not a Valid User";
 }

public String checkAllUserList(String useremail,String password)
{
String userListData = null;
String pwd = null;


System.out.println(useremail);
SecurityManager securityManager= new SecurityManager();
 try {
	pwd = securityManager.checkUserNameValidity(useremail);
	System.out.println(pwd);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
       if(password.equals(pwd))
    	    return useremail;
       else
    	    return "Not Valid User";

}

   
@POST
@Path("/addItem/{sellerid}")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
/*public void addItem(@FormParam("category") String category,@FormParam("title") String title,
		@FormParam("price") String price,@FormParam("discription") String discription,
		@FormParam("address") String address,@FormParam("city") String city,
		@FormParam("pincode") String pincode,@FormParam("emailid") String emailid,@FormParam("mobileno") String mobileno) throws Exception*/
public void addItem(@PathParam("sellerid") String selleremail, @FormParam("category") String category,@FormParam("subcategory") String subcategory,
		@FormParam("pid") int pid,@FormParam("title") String title,@FormParam("brand") String brand,
		@FormParam("color") String color,@FormParam("weight") String weight,@FormParam("price") float price,
		@FormParam("size") String size,@FormParam("quantity") int quantity,@FormParam("warranty") String warranty,
		@FormParam("used_fresh") String used_fresh,@FormParam("discount") float discount,@FormParam("filter1") String filter1,
		@FormParam("value1") String value1,@FormParam("filter2") String filter2,@FormParam("value2") String value2,
		@FormParam("filter3") String filter3,@FormParam("value3") String value3,@FormParam("description") String description)
{
	id=pid;
	System.out.println(selleremail+category+" "+subcategory+" "+filter1+" "+value1+" "+discount+" "+weight+" "+id+" "+description);
	SecurityManager mg=new SecurityManager();
	//emailID=emailid;
	
	//boolean flag=mg.addItem(category,title,price,discription,address,city,pincode,emailid,mobileno);
	boolean flag=mg.addItem(category,subcategory,pid,title,brand,color,weight,price,size,quantity,warranty,used_fresh,
							discount,filter1,value1,filter2,value2,filter3,value3,description,selleremail);
	if(flag)
		System.out.println("Successfully Inserted");
	else
		System.out.println("failed");
}

@GET
@Path("/updateSellerStatus/{buyerid}/{itemid}/{status}")
@Produces(MediaType.APPLICATION_JSON)
public QueryStatus updateSellerStatus(@PathParam("buyerid") String buyeremail,@PathParam("itemid") int itemid,@PathParam("status") String status) throws Exception
{
	System.out.println("From update Seller Status:"+buyeremail);
	Manager mg=new Manager();
	return mg.updateSellerStatus(buyeremail,itemid,status);
	
}

@GET
@Path("/updateStatus/{buyerid}/{itemid}/{status}/{rating}")
@Produces(MediaType.APPLICATION_JSON)
public QueryStatus updateItemStatus(@PathParam("buyerid") String buyeremail,@PathParam("itemid") int itemid,@PathParam("status") String status,@PathParam("rating") int rating) throws Exception
{
	System.out.println("From update Item Status:"+buyeremail+"rating:"+rating);
	Manager mg=new Manager();
	return mg.updateItemStatus(buyeremail,itemid,status,rating);
	
}

@GET
@Path("/getStatus/{buyerid}/{itemid}")
@Produces(MediaType.APPLICATION_JSON)
public StatusVO getItemStatus(@PathParam("buyerid") String buyeremail,@PathParam("itemid") int itemid) throws Exception
{
	//System.out.println("From DeleteItem:"+buyeremail);
	Manager mg=new Manager();
	return mg.getItemStatus(buyeremail,itemid);
	
}

@GET
@Path("/loadItembyBuyer/{buyerid}")
@Produces(MediaType.APPLICATION_JSON)
public ArrayList<StatusVO> getItembyBuyer(@PathParam("buyerid") String buyeremail) throws Exception
{
	System.out.println("From DeleteItem:"+buyeremail);
	Manager mg=new Manager();
	return mg.getItembyBuyer(buyeremail);
}

@GET
@Path("/loadItembySeller/{sellerid}")
@Produces(MediaType.APPLICATION_JSON)
public ArrayList<ItemVO> getItembySeller(@PathParam("sellerid") String selleremail)
{
	//System.out.println("From DeleteItem:"+selleremail);
	Manager mg=new Manager();
	return mg.getItembySeller(selleremail);
}

@GET
@Path("/sellerItemStatus/{sellerid}")
@Produces(MediaType.APPLICATION_JSON)
public ArrayList<StatusVO> getSellerItemStatus(@PathParam("sellerid") String selleremail) throws Exception
{
	System.out.println("From SellerStatus:"+selleremail);
	Manager mg=new Manager();
	return mg.getSellerItemStatus(selleremail);
}

@POST
@Path("/uploaduserimage") 
@Consumes(MediaType.MULTIPART_FORM_DATA)
public void uploaduserimage(
        @FormDataParam("uploadFile1") InputStream fileInputStream1,
        @FormDataParam("uploadFile1") FormDataContentDisposition fileDetail1) throws Exception
{
	
	System.out.print("Image Data");
	String outputt=null;
	String fileName1 = null,fileName2 = null,fileName3 = null;
    String uploadFilePath1 = null,uploadFilePath2 = null,uploadFilePath3 = null;
    SecurityManager sm = null;
	try {
        fileName1 = fileDetail1.getFileName();
       
        System.out.println(fileName1);
        uploadFilePath1 = writeToFileServer2(fileInputStream1, fileName1);
        System.out.println(uploadFilePath1);
        sm =new SecurityManager();
        outputt = sm.uploaduserimage(uploadFilePath1,fileName1,useremail);
        System.out.println(outputt);
      
    }
    catch(IOException ioe){
        ioe.printStackTrace();
    }
    finally{
        // release resources, if any
    }
	
	// return new Viewable("/amazonhomepage.jsp", sm.displayItem(emailID));
	//return new Viewable("/amazonhomepage.jsp", sm.displayItem(id));
	//return fileName+outputt;
}



@POST
@Path("/uploadfile2") 
@Consumes(MediaType.MULTIPART_FORM_DATA)
public void uploadfile2(
        @FormDataParam("uploadFile1") InputStream fileInputStream1,
        @FormDataParam("uploadFile1") FormDataContentDisposition fileDetail1,@FormDataParam("uploadFile2") InputStream fileInputStream2,
        @FormDataParam("uploadFile2") FormDataContentDisposition fileDetail2,@FormDataParam("uploadFile3") InputStream fileInputStream3,
        @FormDataParam("uploadFile3") FormDataContentDisposition fileDetail3) throws Exception {
	
	System.out.print("Image Data");
	String outputt=null;
	String fileName1 = null,fileName2 = null,fileName3 = null;
    String uploadFilePath1 = null,uploadFilePath2 = null,uploadFilePath3 = null;
    SecurityManager sm = null;
	try {
        fileName1 = fileDetail1.getFileName();
        fileName2 = fileDetail2.getFileName();
        fileName3 = fileDetail3.getFileName();
        System.out.println(fileName1);
        System.out.println(fileName2);
        System.out.println(fileName3);
        uploadFilePath1 = writeToFileServer2(fileInputStream1, fileName1);
        uploadFilePath2 = writeToFileServer2(fileInputStream2, fileName2);
        uploadFilePath3 = writeToFileServer2(fileInputStream3, fileName3);
        System.out.println(uploadFilePath1);
        System.out.println(uploadFilePath2);
        System.out.println(uploadFilePath3);
        sm =new SecurityManager();
        outputt = sm.uploadFile1(uploadFilePath1,fileName1,uploadFilePath2,fileName2,uploadFilePath3,fileName3,id);
        System.out.println(outputt);
      
    }
    catch(IOException ioe){
        ioe.printStackTrace();
    }
    finally{
        // release resources, if any
    }
	
	// return new Viewable("/amazonhomepage.jsp", sm.displayItem(emailID));
	//return new Viewable("/amazonhomepage.jsp", sm.displayItem(id));
	//return fileName+outputt;
}

private String writeToFileServer2(InputStream inputStream, String fileName) throws IOException {
 
OutputStream outputStream = null;
String qualifiedUploadFilePath = UPLOAD_FILE_SERVER + fileName;

System.out.println(qualifiedUploadFilePath);

try {
    outputStream = new FileOutputStream(new File(qualifiedUploadFilePath));
    int read = 0;
    byte[] bytes = new byte[1024];
    while ((read = inputStream.read(bytes)) != -1) {
        outputStream.write(bytes, 0, read);
    }
    outputStream.flush();
}
catch (IOException ioe) {
    ioe.printStackTrace();
}
finally{
    //release resource, if any
    outputStream.close();
}
return qualifiedUploadFilePath;
}

























@GET
@Path("/show") 
@Produces(MediaType.MULTIPART_FORM_DATA)
public Viewable show() throws Exception {
	
	System.out.print("Images Displaying from Database*******************************");
	
	SecurityManager sm = new SecurityManager();
	 return new Viewable("/amazonhomepage.jsp", sm.showItem());
	//return fileName+outputt;
}

@GET
@Path("/getAllCategories/")
@Produces(MediaType.APPLICATION_JSON)
public ArrayList<CategoryVO>  getCategory() throws Exception {
	//System.out.print("manager");
	Manager mgr=new Manager();
	ArrayList<CategoryVO> catvo=mgr.getCategory();
	return catvo;
}

@GET
@Path("/getSubCategories/{subcat}")
@Produces(MediaType.APPLICATION_JSON)
public ArrayList<CategoryVO>  getSubCategory(@PathParam("subcat")String cat) throws Exception {
	//System.out.print("manager");
	Manager mgr=new Manager();
	ArrayList<CategoryVO> catvo=mgr.getSubCategory(cat);
	return catvo;
}

@GET
@Path("/getItemByCat/{subcat}")
@Produces(MediaType.APPLICATION_JSON)
public ArrayList<ItemVO>  getItemByCat(@PathParam("subcat")String cat) throws Exception {
	//System.out.print("manager");
	Manager mgr=new Manager();
	ArrayList<ItemVO> catvo=mgr.getItemByCat(cat);
	return catvo;
}














}











