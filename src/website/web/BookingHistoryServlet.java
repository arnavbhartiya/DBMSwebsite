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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Arnav Bhartiya
 * Apr 19, 2017 
 */
public class BookingHistoryServlet extends HttpServlet {
/* (non-Javadoc)
 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	HttpSession session = req.getSession();
	String username= (String) session.getAttribute("username");
	
	Connection conn;
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(
				"jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl",
				"arnav", "DBMSwebsite");
		Statement stmt = conn.createStatement();
		ResultSet booking = stmt.executeQuery("SELECT * FROM BOOKINGS WHERE USERNAME=\'"+username+"\'");
		List<List<String>> bookingHistory = new ArrayList<List<String>>();
		while(booking.next()){
			List<String> bookingInfo = new ArrayList<String>();
			String checkInDate = booking.getString("CHECKIN");
			String checkOutDate = booking.getString("CHECKOUT");
			String roomsBooked = booking.getString("ROOMS");
			String isPayed = (booking.getString("PAYMENT_ID").length()!=0)?"Full":"Pending";
			bookingInfo.add(checkInDate);
			bookingInfo.add(checkOutDate);
			bookingInfo.add(roomsBooked);
			bookingInfo.add(isPayed);
			bookingHistory.add(bookingInfo);
			bookingInfo = new ArrayList<>();
		}
		req.setAttribute("bookingHistory", bookingHistory);
	} catch (SQLException e) {
		e.printStackTrace();
	}catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	req.getRequestDispatcher("/BookingHistory.jsp").forward(req, resp);
}
}
