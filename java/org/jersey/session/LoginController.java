package org.jersey.session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
 
/**
 * Servlet implementation class Session
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String un = request.getParameter("username");
		String pwd = request.getParameter("password");
		System.out.print(un);
		if (un.equals("akshay")) {
			out.print("Welcome, " + un);
			HttpSession session = request.getSession(true); // reuse existing
															// session if exist
															// or create one
			session.setAttribute("username", un);
			session.setMaxInactiveInterval(30); // 30 seconds
			response.sendRedirect("home.jsp");
			JSONObject object = new JSONObject();
			
			
			try {
				 object.put("username", un);
				 object.put("password", pwd);
				 
				 System.out.println("JSON data: "+object.toString());
				
				 } catch (JSONException e) {
				 
				e.printStackTrace();
				 }
			 try {

				// String a ="http://localhost:8080/akshay/webapi/webservice/login?username=akshay&password=a@123";
				
					URL url = new URL("http://localhost:8080/akshay/webapi/webservice/login");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "application/json");
					String input = "{\"username\":\"akshay\",\"password\":\"a@123\"}";
                     System.out.print(input);
					OutputStream os = conn.getOutputStream();
					os.write(input.getBytes());
					os.flush();

					if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
						throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
					}

					BufferedReader br = new BufferedReader(new InputStreamReader(
							(conn.getInputStream())));

					String output;
					System.out.println("Output from Server .... \n");
					while ((output = br.readLine()) != null) {
						System.out.println(output);
					}

					conn.disconnect();

				  } catch (MalformedURLException e) {

					e.printStackTrace();

				  } catch (IOException e) {

					e.printStackTrace();

				 }
			
			
			
			
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		} // TODO Auto-generated method stub
		
		
		
		
		
	}
}