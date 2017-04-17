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
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Arnav Bhartiya
 * Apr 17, 2017 
 */
public class BookingConfirmationServlet extends HttpServlet {
/* (non-Javadoc)
 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String country = req.getParameter("country");
		String Address = req.getParameter("address");
		String city = req.getParameter("city");
		String phone = req.getParameter("phoneNo");
		String email = req.getParameter("email");
		String cardType = req.getParameter("cardType");
		String cardNumber = req.getParameter("cardNumber");
		String expirationYear = req.getParameter("expirationyear");
		String cvv = req.getParameter("cvv");
		HttpSession session = req.getSession();
		String checkInDateString=(String) session.getAttribute("checkInDateString");
		String checkOutDateString=(String) session.getAttribute("checkOutDateString");
		int numberOfRooms= (int) session.getAttribute("numberOfRooms");
		String roomType = (String) session.getAttribute("roomType");
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl",
				"arnav", "DBMSwebsite");
	    Statement stmt = conn.createStatement ();
	    System.out.println("select * from rooms where BOOKED_TILL_DATE<TO_DATE(\'"+ checkInDateString + "\', \'mm/dd/yyyy\') AND ROOM_TYPE=\'"+roomType+"\'");
	    ResultSet findAvailableRooms = stmt.executeQuery ("select * from rooms where BOOKED_TILL_DATE<TO_DATE(\'"+ checkInDateString + "\', \'mm/dd/yyyy\') AND ROOM_TYPE=\'"+roomType+"\'");
	    while(findAvailableRooms.next()){
	    	String roomId= findAvailableRooms.getString("ROOMID");
	    	
	    }
		}
		catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	
}
}
