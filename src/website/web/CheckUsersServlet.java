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
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Arnav Bhartiya
 * Apr 20, 2017 
 */
public class CheckUsersServlet extends HttpServlet {
/* (non-Javadoc)
 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String arrivalDate=req.getParameter("arrivalDate");
		String departureDate= req.getParameter("departureDate");
	try {
	    Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl",
				"arnav", "DBMSwebsite");
	    Statement stmt = conn.createStatement ();
	    System.out.println("SELECT users.username FROM users INNER JOIN bookings ON bookings.username=users.username where bookings.checkin>=TO_DATE(\'"+ arrivalDate + "\', \'mm/dd/yyyy\') and bookings.checkout<=TO_DATE(\'"+ departureDate + "\', \'mm/dd/yyyy\')");
	    ResultSet guestList = stmt.executeQuery ("SELECT users.username FROM users INNER JOIN bookings ON bookings.username=users.username where bookings.checkin>=TO_DATE(\'"+ arrivalDate + "\', \'mm/dd/yyyy\') and bookings.checkout<=TO_DATE(\'"+ departureDate + "\', \'mm/dd/yyyy\')");
	    ArrayList<String> guests= new ArrayList<>();
	    while (guestList.next()){
	    	String guest=guestList.getString ("USERNAME");
	    	guests.add(guest);
	    }
	    req.setAttribute("guestsList", guestList);
	    req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
	    
	      conn.close(); // ** IMPORTANT : Close connections when done **

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
