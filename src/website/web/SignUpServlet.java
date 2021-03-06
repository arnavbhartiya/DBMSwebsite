/**
 * 
 */
package website.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Arnav Bhartiya
 * Apr 8, 2017 
 */
public class SignUpServlet extends HttpServlet {
/* (non-Javadoc)
 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
	String firstName = req.getParameter("firstName");
	String pass="DBMSwebsite";
	String lastName = req.getParameter("lastName");
	String userName = req.getParameter("userName");
	String password = req.getParameter("password");
	String phoneNo = req.getParameter("phoneNo");
	String email = req.getParameter("email");
	String gender = (req.getParameter("male")==null)?"female":"male";
	try {
	    Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl",
				"arnav", pass);
	    Statement stmt = conn.createStatement ();
	    ResultSet checkUsername = stmt.executeQuery ("select * from USERS WHERE USERNAME=\'"+userName+"\'");
	   if(checkUsername.next()){
		   req.setAttribute("userNameError", "username already taken");
			req.getRequestDispatcher("/SignUp.jsp").forward(req, resp);
			 
	   }else{
		  boolean isInserted= storeUsersInDatabase(firstName,lastName,userName,password,phoneNo,email,gender,conn);
		  if(!isInserted){
			  req.setAttribute("signUpError", "Unable to Sign Up! Please Try again");
				req.getRequestDispatcher("/SignUp.jsp").forward(req, resp);
		  }else{
			  HttpSession session= req.getSession();
				session.setAttribute("username", userName);
			  req.setAttribute("username", userName);
			  System.out.println("Sign up successfull!! going to the customer profile page");
				req.getRequestDispatcher("/CustomerProfile.jsp").forward(req, resp);
		  }
	   }
	   conn.close(); 
	    	
	    
	     

	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
}

private boolean storeUsersInDatabase(String firstName, String lastName,
		String userName, String password, String phoneNo, String email,
		String gender, Connection conn) throws SQLException {
	Statement stmt = conn.createStatement ();
	String contact_query = "INSERT INTO CONTACT_TABLE(CONTACT_ID,FIRSTNAME,LASTNAME,PHONE,EMAIL) VALUES(PAYMENT_SEQUENCE.NEXTVAL,\'"
			+ firstName
			+ "\',\'"
			+ lastName
			+ "\',\'"
			+ phoneNo
			+ "\',\'" + email + "\')";
	System.out.println(contact_query);
	stmt.executeQuery(contact_query);
	System.out.println("select CONTACT_ID FROM CONTACT_TABLE WHERE CONTACT_ID=(select MAX(TO_NUMBER(contact_id)) from contact_table)");
	ResultSet contactID= stmt.executeQuery("select CONTACT_ID FROM CONTACT_TABLE WHERE CONTACT_ID=(select MAX(TO_NUMBER(contact_id)) from contact_table)");
	String contact_iD="";
	while(contactID.next()){
		contact_iD=contactID.getString("CONTACT_ID");
	}
	System.out.println(contact_iD);
	System.out.println("INSERT INTO USERS(USERNAME,PASSWORD,CONTACT_ID) VALUES("+"\'"+userName+"\',\'"+password+"\',\'"+contact_iD+"\')");
    ResultSet store = stmt.executeQuery ("INSERT INTO USERS(USERNAME,PASSWORD,CONTACT_ID) VALUES("+"\'"+userName+"\',\'"+password+"\',\'"+contact_iD+"\')");
    //if(store.rowInserted())
    	return true;
    //return false;
}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
}
