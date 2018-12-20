package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Statement;

import dao.DbConnection;
import dao.LoginHandler;
import pojo.AddressVO;
import pojo.CardVO;
import pojo.CategoryVO;
import pojo.DealVO;
import pojo.ItemVO;
import pojo.QueryStatus;
import pojo.ReceiptVO;
import pojo.StatusVO;

public class Manager {
	public ArrayList<ItemVO> getItemDetails() throws Exception {
		//String sql="Select * from items";
		String sql="select * from seller s,selleritem si,items i where s.SellerId=si.SellerId and i.itemid=si.itemid";
		ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			ItemVO itemvo = new ItemVO();
			itemvo.setTitle(rs.getString("Title"));
			itemvo.setCategory(rs.getString("Category"));
			//itemvo.setName(rs.getString("name"));
		itemvo.setSubCategory(rs.getString("SubCategory"));
	     itemvo.setItemId(rs.getInt("ItemId"));
			itemvo.setProductId(rs.getInt("ProductId"));
	    	itemvo.setBrand(rs.getString("Brand"));
	    	itemvo.setColor(rs.getString("Color"));
		itemvo.setWeight(rs.getString("Weight"));
			itemvo.setPrice(rs.getFloat("price"));
		itemvo.setSize(rs.getString("Size"));
	itemvo.setQuantity(rs.getInt("Quantity"));
	    	itemvo.setWarranty(rs.getString("WarrantyDuration"));
			itemvo.setUsedFresh(rs.getString("Used_Fresh"));
			itemvo.setDiscount(rs.getFloat("Discount"));
			//itemvo.setDescription(rs.getString("Description"));
			itemvo.setImage1(rs.getString("Image1"));
			itemvo.setsellername(rs.getString("sellername"));
			itemList.add(itemvo);
		}
			connection.close();
			return itemList;
	}
	
	public ArrayList<DealVO> getDealDetails() throws Exception {
		/*DateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
		LocalDate now = LocalDate.now();
		String s1 = formatter.format(now);
		System.out.println("Todays Date:"+s1);*/
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		Date date1 = sdf.parse(localDate);*/
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		String fs = localDate.format(dtf);
		System.out.println(fs);
		
		String sql="select * from seller s,items i,deals d,selleritem si where d.itemid=i.itemid and si.itemid=d.itemid and s.sellerid=si.sellerid and d.endDate >= '"+fs+"'";
		ArrayList<DealVO> dealList = new ArrayList<DealVO>();
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			DealVO dealvo = new DealVO();
			dealvo.setTitle(rs.getString("Title"));
			dealvo.setCategory(rs.getString("Category"));
			//itemvo.setName(rs.getString("name"));
		dealvo.setSubcategory(rs.getString("SubCategory"));
	     dealvo.setItemid(rs.getInt("ItemId"));
			dealvo.setProductid(rs.getInt("ProductId"));
	    	dealvo.setBrand(rs.getString("Brand"));
	    	dealvo.setColor(rs.getString("Color"));
		dealvo.setWeight(rs.getString("Weight"));
			dealvo.setPrice(rs.getFloat("price"));
		dealvo.setSize(rs.getString("Size"));
	dealvo.setQuantity(rs.getInt("Quantity"));
	    	dealvo.setWarranty(rs.getString("WarrantyDuration"));
			dealvo.setUsed_fresh(rs.getString("Used_Fresh"));
			dealvo.setDiscount(rs.getFloat("Discount"));
			//itemvo.setDescription(rs.getString("Description"));
			dealvo.setImage1(rs.getString("Image1"));
			dealvo.setSellername(rs.getString("sellername"));
			dealvo.setDealtype(rs.getInt("dealtype"));
			dealvo.setInitialquantity(rs.getInt("initialquantity"));
			dealvo.setStartDate(rs.getDate("startDate"));
			dealvo.setEndDate(rs.getDate("endDate"));
			dealvo.setDealid(rs.getInt("dealid"));
			System.out.println("Initial Quantity: "+rs.getInt("initialquantity"));
			dealList.add(dealvo);
		}
			connection.close();
			return dealList;
	}
	
	public void deleteItem(int name) throws Exception {
		
		//String sql="Select * from items where itemid="+x+"";
		String sql="delete from items where itemid="+name+"";
		String sql1="delete from selleritem where itemid="+name+"";
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
		PreparedStatement ps1=(PreparedStatement) connection.prepareStatement(sql1);
		int rs = ps.executeUpdate();
		int rs1 = ps1.executeUpdate();
		if(rs == 1 && rs1 == 1)
			System.out.println("success");
		else
			System.out.println("failed");
		connection.close();
	
	}
	
	public ItemVO getItemDetail(String name) throws Exception {
		int x = Integer.parseInt(name);
		//String sql="Select * from items where itemid="+x+"";
		String sql="select * from seller s,selleritem si,items i where s.SellerId=si.SellerId and i.itemid=si.itemid and i.itemid="+x+"";
		String sql1="select sellerid from selleritem where itemid="+x+"";
		
		ItemVO itemvo = new ItemVO();
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		PreparedStatement ps1=(PreparedStatement) connection.prepareStatement(sql1);
		ResultSet rs1 = ps1.executeQuery();
		int sid=0;
		int itemcount=0;
		float sumrating=0.0f;
		
		if(rs1.next())
		{
		  sid = rs1.getInt(1);	
		}
		/*String sql2="select count(itemid) from selleritem where sellerid="+sid+"";
		PreparedStatement ps2=(PreparedStatement) connection.prepareStatement(sql2);
		ResultSet rs2 = ps2.executeQuery();
		
		if(rs2.next())
		{
		  itemcount = rs2.getInt(1);	
		}*/
		String sql2="select count(itemid) from transactions where itemid in(select itemid from selleritem where sellerid="+sid+")";
		PreparedStatement ps2=(PreparedStatement) connection.prepareStatement(sql2);
		ResultSet rs2 = ps2.executeQuery();
		
		if(rs2.next())
		{
		  itemcount = rs2.getInt(1);	
		}
		
		String sql3="select sum(rating) from transactions where itemid in(select itemid from selleritem where sellerid="+sid+")";
		PreparedStatement ps3=(PreparedStatement) connection.prepareStatement(sql3);
		ResultSet rs3 = ps3.executeQuery();
		
		if(rs3.next())
		{
			sumrating = rs3.getInt(1);
		}
		System.out.println("sid:"+sid+" itemcount:"+itemcount+" sum:"+sumrating);
		float avgrating = sumrating / itemcount;
		
		while(rs.next()) {
			itemvo.setTitle(rs.getString("Title"));
			itemvo.setCategory(rs.getString("Category"));
			//itemvo.setName(rs.getString("name"));
		itemvo.setSubCategory(rs.getString("SubCategory"));
	     	itemvo.setItemId(rs.getInt("ItemId"));
		itemvo.setProductId(rs.getInt("ProductId"));
	    	itemvo.setBrand(rs.getString("Brand"));
	    	itemvo.setColor(rs.getString("Color"));
			itemvo.setWeight(rs.getString("Weight"));
			itemvo.setPrice(rs.getFloat("price"));
			itemvo.setSize(rs.getString("Size"));
			itemvo.setQuantity(rs.getInt("Quantity"));
	    	itemvo.setWarranty(rs.getString("WarrantyDuration"));
			itemvo.setUsedFresh(rs.getString("Used_Fresh"));
			itemvo.setDiscount(rs.getFloat("Discount"));
			//itemvo.setDescription(rs.getString("Description"));
			itemvo.setImage1(rs.getString("Image1"));
			itemvo.setsellername(rs.getString("sellername"));
			itemvo.setSum(sumrating);
			itemvo.setCount(itemcount);
			itemvo.setAvgrating(avgrating);
			System.out.println(rs.getString("sellername"));
		}
		    connection.close();
			return itemvo;
	}
	
	public ArrayList<ItemVO> getItembySeller(String x)
	{
		// TODO Auto-generated method stub
		ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
		try 
		{
			DbConnection database= new DbConnection();
			Connection connection = database.getConnection();
			//LoginHandler loginHandler= new LoginHandler();
			String sql = "select * from seller s,selleritem si,items i where s.SellerId=si.SellerId and i.itemid=si.itemid and s.email='"+x+"'";
			
			PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ItemVO itemvo = new ItemVO();
				itemvo.setTitle(rs.getString("Title"));
				itemvo.setCategory(rs.getString("Category"));
				//itemvo.setName(rs.getString("name"));
				itemvo.setSubCategory(rs.getString("SubCategory"));
				itemvo.setItemId(rs.getInt("ItemId"));
				itemvo.setProductId(rs.getInt("ProductId"));
		    	itemvo.setBrand(rs.getString("Brand"));
		    	itemvo.setColor(rs.getString("Color"));
		    	itemvo.setWeight(rs.getString("Weight"));
				itemvo.setPrice(rs.getFloat("price"));
				itemvo.setSize(rs.getString("Size"));
				itemvo.setQuantity(rs.getInt("Quantity"));
		    	itemvo.setWarranty(rs.getString("WarrantyDuration"));
				itemvo.setUsedFresh(rs.getString("Used_Fresh"));
				itemvo.setDiscount(rs.getFloat("Discount"));
				//itemvo.setDescription(rs.getString("Description"));
				itemvo.setImage1(rs.getString("Image1"));
				itemvo.setsellername(rs.getString("sellername"));
				itemList.add(itemvo);
			}
			connection.close();
			
		} 
		catch (Exception e)
		{		}
		return itemList;
	}
	
	
	public ArrayList<CategoryVO> getCategory() throws Exception {
		String sql="Select distinct(category) from itemcategory";
		
		ArrayList<CategoryVO> itemList = new ArrayList<CategoryVO>();
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			CategoryVO catvo=new CategoryVO();
			//System.out.println(rs.getString("category"));
			catvo.setCategory(rs.getString("category"));
			//catvo.setsubCategory(rs.getString("SubCategory"));
			itemList.add(catvo);
			}
		    connection.close();
			return itemList;
	}
	
	public ArrayList<CategoryVO> getSubCategory(String cat) throws Exception {
		String sql="Select SubCategory from itemcategory where category='"+cat+"'";
		
		ArrayList<CategoryVO> itemList = new ArrayList<CategoryVO>();
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			CategoryVO catvo=new CategoryVO();
			//System.out.println(rs.getString("category"));
			//catvo.setCategory(rs.getString("category"));
			catvo.setsubCategory(rs.getString("SubCategory"));
			itemList.add(catvo);
			}
		    connection.close();
			return itemList;
	}
	
	public ArrayList<ItemVO> getItemByCat(String cat) throws Exception {
		String sql="Select * from items where SubCategory='"+cat+"'";
		
		ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			ItemVO catvo=new ItemVO();
			ItemVO itemvo = new ItemVO();
			itemvo.setTitle(rs.getString("Title"));
			itemvo.setCategory(rs.getString("Category"));
		//	itemvo.setName(rs.getString("name"));
			itemvo.setSubCategory(rs.getString("SubCategory"));
	     	itemvo.setItemId(rs.getInt("ItemId"));
			itemvo.setProductId(rs.getInt("ProductId"));
	    	itemvo.setBrand(rs.getString("Brand"));
	    	itemvo.setColor(rs.getString("Color"));
			itemvo.setWeight(rs.getString("Weight"));
			itemvo.setPrice(rs.getFloat("price"));
			itemvo.setSize(rs.getString("Size"));
			itemvo.setQuantity(rs.getInt("Quantity"));
	    	itemvo.setWarranty(rs.getString("WarrantyDuration"));
			itemvo.setUsedFresh(rs.getString("Used_Fresh"));
			itemvo.setDiscount(rs.getFloat("Discount"));
		//	itemvo.setDescription(rs.getString("Description"));
			itemvo.setImage1(rs.getString("Image1"));
			itemList.add(itemvo);
			itemList.add(catvo);
			}
		    connection.close();
			return itemList;
	}
	
	public CardVO getAccountInfo(String cardname,String amount)
	{
		
		CardVO cv= new CardVO();
		try 
		{
			DbConnection database= new DbConnection();
			Connection connection = database.getConnection();
			//LoginHandler loginHandler= new LoginHandler();
			String sql = "select amount from bank where useremail='"+cardname+"'";
			System.out.println(sql);
			//int am=0;
			float am=0.0f;
			PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
			if(rs.next()) {
		  am =  rs.getFloat("amount");
		  System.out.println(amount);
				}
			
			//int c = Integer.valueOf(cardamount);
			//System.out.print(amount);
			//int fd = Integer.parseInt(amount);
			float fd = Float.parseFloat(amount);
			int re = Float.compare(fd, am);
			
			if(re < 0) {
				cv.setSuccess("1");
			}
			else {
				cv.setSuccess("0");
			}
			

				 
			
		} 
		catch (Exception e)
		{		}
		
		
		return cv;
	}
	
	public ArrayList<AddressVO> getAllAddress(String useremail) throws Exception {
		//String sql="Select * from items";
		String sql="select * from useraddress u,userprofile p  where u.Email=p.Email and  u.Email='"+useremail+"'";
		System.out.println(sql);
		ArrayList<AddressVO> itemList = new ArrayList<AddressVO>();
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			AddressVO itemvo = new AddressVO();
		    itemvo.setAddressCode(rs.getInt("Addresscode"));
		    System.out.println(rs.getInt("Addresscode"));
			itemvo.setEmail(rs.getString("Email"));
			itemvo.setAddress1(rs.getString("AddressLine1"));
			itemvo.setAddress2(rs.getString("AddressLine2"));
	        itemvo.setCity(rs.getString("City"));
	    	itemvo.setState(rs.getString("State"));
	    	itemvo.setPostalcode(rs.getString("Postalcode"));
		
			itemList.add(itemvo);
		}
			connection.close();
		
			return itemList;
	
	}
	
	public ReceiptVO getTxDetail(String name,String addresscode, String itemid) 
	{
		
		System.out.println("getTxDetail");
		ReceiptVO itemvo = new ReceiptVO();
		String sql="select * from transactions t,useraddress u,userprofile up,items i,seller s,selleritem si where t.email = u.email and t.itemid = i.itemid and t.email=up.email and t.itemid = si.itemid and si.sellerid = s.sellerid and  t.addresscode=u.Addresscode and t.email='"+name+"' and t.Addresscode='"+addresscode+"' and t.itemid='"+itemid+"'"+";";
		System.out.println(sql);
			try
		{
		
				DbConnection database= new DbConnection();
				Connection connection = database.getConnection();
				PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
	  System.out.println("Hello="+sql);	
		while(rs.next()) {
			itemvo.setName(rs.getString("Name"));
			itemvo.setAmount(rs.getString("amount"));
			itemvo.setAddresscode(rs.getInt("Addresscode"));
			itemvo.setEmail(rs.getString("Email"));
			itemvo.setAddress1(rs.getString("AddressLine1"));
			itemvo.setAddress2(rs.getString("AddressLine2"));
	        itemvo.setCity(rs.getString("City"));
	    	itemvo.setState(rs.getString("State"));
	    	itemvo.setPostalcode(rs.getString("Postalcode"));
			itemvo.setTitle(rs.getString("Title"));
			itemvo.setCategory(rs.getString("Category"));
			//itemvo.setName(rs.getString("name"));
		itemvo.setSubcategory(rs.getString("SubCategory"));
	     	itemvo.setItemid(rs.getInt("ItemId"));
		itemvo.setProductid(rs.getInt("ProductId"));
	    	itemvo.setBrand(rs.getString("Brand"));
	    	itemvo.setColor(rs.getString("Color"));
			itemvo.setWeight(rs.getString("Weight"));
			itemvo.setPrice(rs.getFloat("price"));
			itemvo.setSize(rs.getString("Size"));
			itemvo.setQuantity(rs.getInt("Quantity"));
	    	itemvo.setWarranty(rs.getString("WarrantyDuration"));
			itemvo.setUsed_fresh(rs.getString("Used_Fresh"));
			itemvo.setDiscount(rs.getFloat("Discount"));
			//itemvo.setDescription(rs.getString("Description"));
			itemvo.setImage1(rs.getString("Image1"));
			itemvo.setSellername(rs.getString("sellername"));
			System.out.println(rs.getString("sellername"));
		}
		    connection.close();
			
		}
		
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return itemvo;
	}
	
	public void updateTxDetail(String name, String addresscode) {
		// TODO Auto-generated method stub
		
		DbConnection database= new DbConnection();
		Connection conn;
		try {
			conn = database.getConnection();
		
		
		String querySetLimit = "SET GLOBAL max_allowed_packet=104857600;";  // 10 MB
	    Statement stSetLimit = (Statement) conn.createStatement();
	     stSetLimit.execute(querySetLimit);
	    
	     
	     //String sql = "UPDATE item SET item_image1 =" +"'"+ combine1 + "'"+ "," + "item_image2 =" +"'"+ combine2 + "'"+ "," + "item_image3 =" +"'"+ combine3 + "'"+ " WHERE seller_emailid = " + "'"+emailID +"'"+";";
	     String sql = "UPDATE transactions SET addresscode =" +"'"+addresscode + "'"+ " WHERE email = " + "'"+name +"'"+";";
	     System.out.println(sql);
	     PreparedStatement statement = conn.prepareStatement(sql);
	      

	     int row = statement.executeUpdate();
	     
	     conn.close();
	     //inputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void updateBankDetail(String name, String amount) throws Exception {
		// TODO Auto-generated method stub
		
		String d1=null,d2=null,d3=null,d4=null;
		
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		String admin="root@gmail.com";
		float result1=0.0f;
				float result2=0.0f;
		float am = Float.parseFloat(amount);
		
		String find1="select amount from  bank where useremail="+"'"+name+"'"+";";
		
	
		
		
		PreparedStatement ps1=(PreparedStatement) connection.prepareStatement(find1);
		ResultSet rs1 = ps1.executeQuery();
		
		if(rs1.next()){
	    	String a = rs1.getString("amount");
	     	float a1 = Float.parseFloat(a);
	   	    result1=a1-am;
	   	 d1=new Float(result1).toString();
		}
		
		String update1 ="UPDATE bank SET amount =" +"'"+d1 + "'"+ " WHERE useremail = " + "'"+name +"'"+";"; 
		 PreparedStatement statement = connection.prepareStatement(update1);
	      

	     int row = statement.executeUpdate();
		
		String find2="select amount from  bank where useremail="+"'"+admin+"'"+";";
		PreparedStatement ps3=(PreparedStatement) connection.prepareStatement(find2);
		ResultSet rs3 = ps3.executeQuery();
		
		if(rs3.next()){
	    	String x = rs3.getString("amount");
	     	float a1 = Float.parseFloat(x);
	   	    result2=a1+am;
	   	 d2=new Float(result2).toString();
		}

		String update2 ="UPDATE bank SET amount =" +"'"+d2 + "'"+ " WHERE useremail = " + "'"+admin +"'"+";";
		
		 PreparedStatement statement1 = connection.prepareStatement(update2);
	      

	     int row1 = statement1.executeUpdate();
		
			connection.close();
		
	}

	public DealVO getDealDetail(String pid) throws Exception {
		int x = Integer.parseInt(pid);
		//String sql="Select * from items where itemid="+x+"";
		String sql="select * from seller s,items i,deals d,selleritem si where d.itemid=i.itemid and si.itemid=d.itemid and s.sellerid=si.sellerid and i.itemid="+x+"";
		String sql1="select sellerid from selleritem where itemid="+x+"";
		DealVO itemvo = new DealVO();
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		PreparedStatement ps1=(PreparedStatement) connection.prepareStatement(sql1);
		ResultSet rs1 = ps1.executeQuery();
		int sid=0;
		int itemcount=0;
		float sumrating=0.0f;
		
		if(rs1.next())
		{
		  sid = rs1.getInt(1);	
		}
		
		String sql2="select count(itemid) from transactions where itemid in(select itemid from selleritem where sellerid="+sid+")";
		PreparedStatement ps2=(PreparedStatement) connection.prepareStatement(sql2);
		ResultSet rs2 = ps2.executeQuery();
		
		if(rs2.next())
		{
		  itemcount = rs2.getInt(1);	
		}
		
		String sql3="select sum(rating) from transactions where itemid in(select itemid from selleritem where sellerid="+sid+")";
		PreparedStatement ps3=(PreparedStatement) connection.prepareStatement(sql3);
		ResultSet rs3 = ps3.executeQuery();
		
		if(rs3.next())
		{
			sumrating = rs3.getInt(1);
		}
		System.out.println("sid:"+sid+" itemcount:"+itemcount+" sum:"+sumrating);
		float avgrating = sumrating / itemcount;
		
		while(rs.next()) {
			itemvo.setTitle(rs.getString("Title"));
			itemvo.setCategory(rs.getString("Category"));
			//itemvo.setName(rs.getString("name"));
		itemvo.setSubcategory(rs.getString("SubCategory"));
	     	itemvo.setItemid(rs.getInt("ItemId"));
		itemvo.setProductid(rs.getInt("ProductId"));
	    	itemvo.setBrand(rs.getString("Brand"));
	    	itemvo.setColor(rs.getString("Color"));
			itemvo.setWeight(rs.getString("Weight"));
			itemvo.setPrice(rs.getFloat("price"));
			itemvo.setSize(rs.getString("Size"));
			itemvo.setQuantity(rs.getInt("Quantity"));
	    	itemvo.setWarranty(rs.getString("WarrantyDuration"));
			itemvo.setUsed_fresh(rs.getString("Used_Fresh"));
			itemvo.setDiscount(rs.getFloat("Discount"));
			//itemvo.setDescription(rs.getString("Description"));
			itemvo.setImage1(rs.getString("Image1"));
			itemvo.setSellername(rs.getString("sellername"));
			itemvo.setDealtype(rs.getInt("dealtype"));
			itemvo.setInitialquantity(rs.getInt("InitialQuantity"));
			itemvo.setDealid(rs.getInt("dealid"));
			itemvo.setSum(sumrating);
			itemvo.setCount(itemcount);
			itemvo.setAvgrating(avgrating);
			System.out.println(rs.getString("sellername"));
		}
		    connection.close();
			return itemvo;
	}

	public ArrayList<ReceiptVO> getUserOrderDetails(String name) {
		System.out.println("getTxDetail");
		ArrayList<ReceiptVO> itemList = new ArrayList<ReceiptVO>();
		String sql="select * from transactions t,useraddress u,userprofile up,items i,seller s,selleritem si where t.email = u.email and t.itemid = i.itemid and t.email=up.email and t.itemid = si.itemid and si.sellerid = s.sellerid and  t.addresscode=u.Addresscode and t.email='"+name+"'"+";";
		System.out.println(sql);
			try
		{
		
				DbConnection database= new DbConnection();
				Connection connection = database.getConnection();
				PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
	  System.out.println("Hello="+sql);	
		while(rs.next()) {
			ReceiptVO itemvo = new ReceiptVO();
			itemvo.setName(rs.getString("Name"));
			itemvo.setAmount(rs.getString("amount"));
			itemvo.setAddresscode(rs.getInt("Addresscode"));
			itemvo.setEmail(rs.getString("Email"));
			itemvo.setAddress1(rs.getString("AddressLine1"));
			itemvo.setAddress2(rs.getString("AddressLine2"));
	        itemvo.setCity(rs.getString("City"));
	    	itemvo.setState(rs.getString("State"));
	    	itemvo.setPostalcode(rs.getString("Postalcode"));
			itemvo.setTitle(rs.getString("Title"));
			itemvo.setCategory(rs.getString("Category"));
			//itemvo.setName(rs.getString("name"));
		itemvo.setSubcategory(rs.getString("SubCategory"));
	     	itemvo.setItemid(rs.getInt("ItemId"));
		itemvo.setProductid(rs.getInt("ProductId"));
	    	itemvo.setBrand(rs.getString("Brand"));
	    	itemvo.setColor(rs.getString("Color"));
			itemvo.setWeight(rs.getString("Weight"));
			itemvo.setPrice(rs.getFloat("price"));
			itemvo.setSize(rs.getString("Size"));
			itemvo.setQuantity(rs.getInt("Quantity"));
	    	itemvo.setWarranty(rs.getString("WarrantyDuration"));
			itemvo.setUsed_fresh(rs.getString("Used_Fresh"));
			itemvo.setDiscount(rs.getFloat("Discount"));
			//itemvo.setDescription(rs.getString("Description"));
			itemvo.setImage1(rs.getString("Image1"));
			itemvo.setSellername(rs.getString("sellername"));
			itemList.add(itemvo);
			System.out.println(rs.getString("sellername"));
		}
		    connection.close();
			
		}
		
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return itemList;
	}

	public void updateItem(int pid, String category, String subcategory, String title, String brand, String color,
			String weight, String size, float price, int quantity, String warranty, String usedfresh, float discount) 
	{
		try 
		{
			DbConnection database= new DbConnection();
			Connection connection = database.getConnection();
			String sql = "UPDATE items SET category =" +"'"+ category + "'"+ "," + "subcategory =" +"'"+ subcategory + "'"+ "," + "title =" +"'"+ title + "'"+ ","
					+ "" + "brand =" +"'"+ brand + "'"+ "," + "color =" +"'"+ color + "'"+ "," + "weight =" +"'"+ weight + "'"+ ","
							+ "" + "size =" +"'"+ size + "'"+ "," + "price =" +""+ price + ""+ "," + "quantity =" +""+ quantity + ""+ ","
									+ "" + "warrantyduration =" +"'"+ warranty + "'"+ "," + "used_fresh =" +"'"+ usedfresh + "'"+ ","
											+ "" + "discount =" +""+ discount + ""+ " WHERE productid = " + ""+pid +""+";";
			System.out.println(sql);
			 PreparedStatement statement = connection.prepareStatement(sql);
		      

		     statement.executeUpdate();
				connection.close();
			 
		} 
		catch (Exception e)
	{		
			System.out.println(e.getMessage());
		
	}
		
	}

	public ItemVO getProductDetail(String name) throws Exception
	{
		int x = Integer.parseInt(name);
		//String sql="Select * from items where itemid="+x+"";
		String sql="select * from items where productid="+x+"";
		ItemVO itemvo = new ItemVO();
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			itemvo.setTitle(rs.getString("Title"));
			itemvo.setCategory(rs.getString("Category"));
			//itemvo.setName(rs.getString("name"));
		itemvo.setSubCategory(rs.getString("SubCategory"));
	     	itemvo.setItemId(rs.getInt("ItemId"));
		itemvo.setProductId(rs.getInt("ProductId"));
	    	itemvo.setBrand(rs.getString("Brand"));
	    	itemvo.setColor(rs.getString("Color"));
			itemvo.setWeight(rs.getString("Weight"));
			itemvo.setPrice(rs.getFloat("price"));
			itemvo.setSize(rs.getString("Size"));
			itemvo.setQuantity(rs.getInt("Quantity"));
	    	itemvo.setWarranty(rs.getString("WarrantyDuration"));
			itemvo.setUsedFresh(rs.getString("Used_Fresh"));
			itemvo.setDiscount(rs.getFloat("Discount"));
			//itemvo.setDescription(rs.getString("Description"));
			itemvo.setImage1(rs.getString("Image1"));
			//itemvo.setsellername(rs.getString("sellername"));
			//System.out.println(rs.getString("sellername"));
		}
		    connection.close();
			return itemvo;
	
	}

	public ArrayList<ItemVO> getProductBySubcategoryDetail(String subcat, String co)throws Exception
	{

		//String sql="Select * from items where itemid="+x+"";
		String sql="select * from items where subcategory='"+subcat+"' and color='"+co+"'";
		ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			ItemVO itemvo = new ItemVO();
			itemvo.setTitle(rs.getString("Title"));
			itemvo.setCategory(rs.getString("Category"));
			//itemvo.setName(rs.getString("name"));
		itemvo.setSubCategory(rs.getString("SubCategory"));
	     	itemvo.setItemId(rs.getInt("ItemId"));
		itemvo.setProductId(rs.getInt("ProductId"));
	    	itemvo.setBrand(rs.getString("Brand"));
	    	itemvo.setColor(rs.getString("Color"));
			itemvo.setWeight(rs.getString("Weight"));
			itemvo.setPrice(rs.getFloat("price"));
			itemvo.setSize(rs.getString("Size"));
			itemvo.setQuantity(rs.getInt("Quantity"));
	    	itemvo.setWarranty(rs.getString("WarrantyDuration"));
			itemvo.setUsedFresh(rs.getString("Used_Fresh"));
			itemvo.setDiscount(rs.getFloat("Discount"));
			//itemvo.setDescription(rs.getString("Description"));
			itemvo.setImage1(rs.getString("Image1"));
			itemList.add(itemvo);
			//itemvo.setsellername(rs.getString("sellername"));
			//System.out.println(rs.getString("sellername"));
		}
		    connection.close();
			return itemList;
	}

	public ArrayList<ItemVO> getItembyprice(int mini, int maxi,String subcat,String co) throws Exception
	{
		//String sql="Select * from items where itemid="+x+"";
				String sql="select * from items where subcategory='"+subcat+"' and color='"+co+"' and price between "+mini+" and "+maxi+"";
				ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
				DbConnection database= new DbConnection();
				Connection connection = database.getConnection();
				PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					ItemVO itemvo = new ItemVO();
					itemvo.setTitle(rs.getString("Title"));
					itemvo.setCategory(rs.getString("Category"));
					//itemvo.setName(rs.getString("name"));
				itemvo.setSubCategory(rs.getString("SubCategory"));
			     	itemvo.setItemId(rs.getInt("ItemId"));
				itemvo.setProductId(rs.getInt("ProductId"));
			    	itemvo.setBrand(rs.getString("Brand"));
			    	itemvo.setColor(rs.getString("Color"));
					itemvo.setWeight(rs.getString("Weight"));
					itemvo.setPrice(rs.getFloat("price"));
					itemvo.setSize(rs.getString("Size"));
					itemvo.setQuantity(rs.getInt("Quantity"));
			    	itemvo.setWarranty(rs.getString("WarrantyDuration"));
					itemvo.setUsedFresh(rs.getString("Used_Fresh"));
					itemvo.setDiscount(rs.getFloat("Discount"));
					//itemvo.setDescription(rs.getString("Description"));
					itemvo.setImage1(rs.getString("Image1"));
					itemList.add(itemvo);
					//itemvo.setsellername(rs.getString("sellername"));
					//System.out.println(rs.getString("sellername"));
				}
				    connection.close();
					return itemList;
	}

	public ArrayList<StatusVO> getItembyBuyer(String buyeremail) throws Exception
	{
		// TODO Auto-generated method stub
		String status = "Item Shipped";
		String sql = "select * from transactions t,items i where i.itemid=t.itemid and t.email='"+buyeremail+"' and t.status='"+status+"'";
		ArrayList<StatusVO> itemList = new ArrayList();
		try
		{
			DbConnection database= new DbConnection();
			Connection connection = database.getConnection();
			PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while(rs.next())
			{
				StatusVO statusvo = new StatusVO();
				statusvo.setCategory(rs.getString("category"));
				statusvo.setSubcategory(rs.getString("subcategory"));
				statusvo.setItemid(rs.getInt("itemid"));
				statusvo.setProductid(rs.getInt("productid"));
				statusvo.setTitle(rs.getString("title"));
				statusvo.setBrand(rs.getString("brand"));
				statusvo.setStatus(rs.getString("status"));
				itemList.add(statusvo);
				//System.out.println(rs.getString("sellername"));
			}
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return itemList;
	}

	public StatusVO getItemStatus(String buyeremail,int itemid) throws Exception
	{
		String sql = "select * from transactions t,items i where i.itemid=t.itemid and t.itemid='"+itemid+"' and t.email='"+buyeremail+"'";
		StatusVO statusvo = new StatusVO();
		try
		{
			DbConnection database= new DbConnection();
			Connection connection = database.getConnection();
			PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				statusvo.setCategory(rs.getString("category"));
				statusvo.setSubcategory(rs.getString("subcategory"));
				statusvo.setItemid(rs.getInt("itemid"));
				statusvo.setProductid(rs.getInt("productid"));
				statusvo.setTitle(rs.getString("title"));
				statusvo.setBrand(rs.getString("brand"));
				statusvo.setStatus(rs.getString("status"));
			}
			connection.close();
		
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return statusvo;
	}

	public QueryStatus updateItemStatus(String buyeremail, int itemid, String status,int rating) throws Exception
	{
		QueryStatus qs = new QueryStatus();
		String sql = "update transactions set status='"+status+"', rating="+rating+" where email='"+buyeremail+"' and itemid="+itemid+"";
		System.out.println(sql);
		
		try
		{
			DbConnection database= new DbConnection();
			Connection connection = database.getConnection();
			PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
			int rows = ps.executeUpdate();
			qs.setNumrows(rows);
			connection.close();
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return qs;
	}

	public QueryStatus transferToSeller(String name, int itemid) throws Exception
	{
		QueryStatus qs = new QueryStatus();
		String status="Item Received";
		String sql = "select amount from transactions where email='"+name+"' and itemid="+itemid+" and status='"+status+"'";
		String sql1 = "select sellerid from selleritem where itemid="+itemid+"";
		
		float am=0.0f;
		int sid=0;
		String smail="";
		
		try
		{
			DbConnection database= new DbConnection();
			Connection connection = database.getConnection();
			PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
			PreparedStatement ps1=(PreparedStatement) connection.prepareStatement(sql1);
			ResultSet rs = ps.executeQuery();
			ResultSet rs1 = ps1.executeQuery();
			
			if(rs.next())
			{
				am = rs.getFloat(1);
			}
			System.out.println("User Amount"+am);
			if(rs1.next())
			{
				sid = rs1.getInt(1);
			}
			
			String sql2 = "select email from seller where sellerid="+sid+"";
			PreparedStatement ps2=(PreparedStatement) connection.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery();
			if(rs2.next())
			{
				smail = rs2.getString(1);
			}
			System.out.println("Seller Email:"+smail);
			String admin="root@gmail.com";
			float adminamt=0.0f;
			String sql3 = "select amount from bank where useremail='"+admin+"'";
			PreparedStatement ps3=(PreparedStatement) connection.prepareStatement(sql3);
			ResultSet rs3 = ps3.executeQuery();
			if(rs3.next())
			{
				adminamt = rs3.getFloat(1);
			}
			System.out.println("Admin Amount:"+adminamt);
			adminamt = adminamt-am;
			System.out.println("Admin Amount after deduction:"+adminamt);
			String sql4 = "select amount from bank where useremail='"+smail+"'";
			PreparedStatement ps4=(PreparedStatement) connection.prepareStatement(sql4);
			ResultSet rs4 = ps4.executeQuery();
			float samount=0.0f;
			if(rs4.next())
			{
				 samount = rs4.getFloat(1);
			}
			System.out.println("Seller Amount:"+samount);
			samount = samount + am;
			System.out.println("Seller Amount After Addition:"+samount);
			String sql5 = "update bank set amount="+adminamt+" where useremail='"+admin+"'";
			PreparedStatement ps5=(PreparedStatement) connection.prepareStatement(sql5);
			int row1 = ps5.executeUpdate();
			
			String sql6 = "update bank set amount="+samount+" where useremail='"+smail+"'";
			PreparedStatement ps6=(PreparedStatement) connection.prepareStatement(sql6);
			int row2 = ps6.executeUpdate();
			
			if(row1 == 1 && row2 == 1)
			{
				qs.setNumrows(row1);
			}
			connection.close();
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return qs;
	}

	public ArrayList<StatusVO> getSellerItemStatus(String selleremail) throws Exception
	{
		//String sql = "select sellerid from seller where email='"+selleremail+"'";
		
		int sid=0;
		ArrayList<StatusVO> itemList = new ArrayList();
		String str ="Payment Completed";
		try
		{
			DbConnection database= new DbConnection();
			Connection connection = database.getConnection();
			/*PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				sid = rs.getInt(1);
			}*/
			
			String sql1 = "select * from seller s,selleritem si,transactions t where s.sellerid=si.sellerid and si.itemid=t.itemid and"
					+ " s.email='"+selleremail+"' and t.status='"+str+"'";
			PreparedStatement ps1 =(PreparedStatement) connection.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			
			while(rs1.next())
			{
				StatusVO statusvo = new StatusVO();
				//statusvo.setCategory(rs1.getString("category"));
				//statusvo.setSubcategory(rs1.getString("subcategory"));
				statusvo.setItemid(rs1.getInt("itemid"));
				//statusvo.setProductid(rs1.getInt("productid"));
				//statusvo.setTitle(rs1.getString("title"));
				//statusvo.setBrand(rs1.getString("brand"));
				statusvo.setStatus(rs1.getString("status"));
				statusvo.setEmail(rs1.getString("t.email"));
				itemList.add(statusvo);
			}
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return itemList;
	}

	public QueryStatus updateSellerStatus(String buyeremail, int itemid, String status)
	{
		QueryStatus qs = new QueryStatus();
		String sql = "update transactions set status ='"+status+"' where email='"+buyeremail+"' and itemid='"+itemid+"'";
		try
		{
			DbConnection database= new DbConnection();
			Connection connection = database.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			int rows = ps.executeUpdate();
			qs.setNumrows(rows);
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return qs;
	}
	
	public ArrayList<ItemVO> getCartDetails(String n) throws Exception {
		//String sql="Select * from items";
		String sql="select * from cart c,seller s,selleritem si,items i where c.email= '"+n+"'and s.SellerId=si.SellerId and i.itemid=si.itemid and c.itemid=i.itemid ";
		ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			ItemVO itemvo = new ItemVO();
			itemvo.setTitle(rs.getString("Title"));
			itemvo.setCategory(rs.getString("Category"));
			//itemvo.setName(rs.getString("name"));
		itemvo.setSubCategory(rs.getString("SubCategory"));
	     itemvo.setItemId(rs.getInt("ItemId"));
			itemvo.setProductId(rs.getInt("ProductId"));
	    	itemvo.setBrand(rs.getString("Brand"));
	    	itemvo.setColor(rs.getString("Color"));
		itemvo.setWeight(rs.getString("Weight"));
			itemvo.setPrice(rs.getFloat("price"));
		itemvo.setSize(rs.getString("Size"));
	itemvo.setQuantity(rs.getInt("Quantity"));
	    	itemvo.setWarranty(rs.getString("WarrantyDuration"));
			itemvo.setUsedFresh(rs.getString("Used_Fresh"));
			itemvo.setDiscount(rs.getFloat("Discount"));
			//itemvo.setDescription(rs.getString("Description"));
			itemvo.setImage1(rs.getString("Image1"));
			itemvo.setAtt2(rs.getString("qty"));
			itemvo.setAtt1(rs.getInt("cartid"));
			itemvo.setsellername(rs.getString("sellername"));
			itemList.add(itemvo);
		}
			connection.close();
			return itemList;
	}
	
	public void updateqty(String n,String qty) throws Exception {
		//String sql="Select * from items";
		int uniqueco=Integer.parseInt(n);
		int quantity=Integer.parseInt(qty);
		System.out.println(uniqueco+" gebbar is back"+quantity);
		String sql1="update cart set qty= "+"'"+qty+"'"+"where cartid='"+n+"' ";
		
		
		//String sql="delete from cart where email='"+email+"' and itemid='"+str[i]+"'";
		
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql1);
		int rs = ps.executeUpdate();
		if(rs==1)
			System.out.println("success");
		else
			System.out.println("failed");
			connection.close();
	
		
	}
	
	public void removeCartItems(String n,String email) throws Exception {
		//String sql="Select * from items";
		String[] str= n.split("B");
		
		for(int i=0;i<str.length;i++) {
		String sql="delete from cart where email='"+email+"' and itemid='"+str[i]+"'";
		ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		PreparedStatement ps=(PreparedStatement) connection.prepareStatement(sql);
		int rs = ps.executeUpdate();
		if(rs==1)
			System.out.println("success");
		else
			System.out.println("failed");
			connection.close();
	}
		
	}
	
	public String addMoney( String email,int balance) throws Exception
	{
		
		//float amount = balance;
		DbConnection database= new DbConnection();
		Connection connection = database.getConnection();
		String sql = "Update userprofile  set balance = balance + " + balance + " where email  = " + "'" + email + "'" + ";" ;
		PreparedStatement statement = connection.prepareStatement(sql);
		// ResultSet rs = statement.executeQuery();
		String admin="root@gmail.com";
		String sql1 = "select amount from bank where useremail = '"+admin+"'";
		try
		{
		PreparedStatement statement1 = connection.prepareStatement(sql1);
		ResultSet rs = statement1.executeQuery();
		float amt=0.0f;
		if(rs.next())
		{
			amt = rs.getFloat(1);
		}
		amt = amt - balance;
		String sql2 = "update bank set amount="+amt+" where useremail='"+admin+"'";
		PreparedStatement statement2 = connection.prepareStatement(sql2);
		int row = statement2.executeUpdate();
		
		//System.out.println(sql);
		 statement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		 
			connection.close();
		 
		return "Wallet updated successfully";
	}
	
	public int getBalance(String email)  throws Exception
	{
	
			int balance = 0;
			DbConnection database= new DbConnection();
			Connection connection = database.getConnection();
			String sql = "select balance from userprofile where Email='"+email+"';";
			System.out.println(sql);
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet rs = statement.executeQuery();
			 
			 if (rs.next()) {
				    balance = rs.getInt(1);
				}

		    
				connection.close();
			
				return balance;
		} 
}
