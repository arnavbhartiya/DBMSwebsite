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
 * @author Arnav Bhartiya Apr 17, 2017
 */
public class BookingConfirmationServlet extends HttpServlet {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
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
		String expirationYear = req.getParameter("expirationYear");
		String cvv = req.getParameter("cvv");
		HttpSession session = req.getSession();
		String checkInDateString = (String) session
				.getAttribute("checkInDateString");
		String checkOutDateString = (String) session
				.getAttribute("checkOutDateString");
		String userName = (String) session.getAttribute("username");
		System.out.println(userName);
		int numberOfRooms = (int) session.getAttribute("numberOfRooms");
		String roomType = (String) session.getAttribute("roomType");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl",
					"arnav", "DBMSwebsite");
			Statement stmt = conn.createStatement();
			ResultSet findAvailableRooms = stmt
					.executeQuery("select * from rooms where BOOKED_TILL_DATE<TO_DATE(\'"
							+ checkInDateString
							+ "\', \'mm/dd/yyyy\') AND ROOM_TYPE=\'"
							+ roomType
							+ "\' AND ROWNUM<=" + numberOfRooms);
			System.out.println("select * from rooms where BOOKED_TILL_DATE<TO_DATE(\'"
							+ checkInDateString
							+ "\', \'mm/dd/yyyy\') AND ROOM_TYPE=\'"
							+ roomType
							+ "\' AND ROWNUM<=" + numberOfRooms);
			String roomsBooked = "";
			while (findAvailableRooms.next()) {
				String currentRoom = findAvailableRooms.getString("ROOMID");
				roomsBooked = currentRoom;
				stmt.executeQuery("UPDATE ROOMS SET BOOKED_FROM_DATE=TO_DATE(\'"
						+ checkInDateString
						+ "\',\'mm/dd/yyyy\'),BOOKED_TILL_DATE=TO_DATE(\'"
						+ checkOutDateString
						+ "\',\'mm/dd/yyyy\') WHERE ROOMID=" + currentRoom);
				System.out.println("currentRoom"+currentRoom);

			}
			System.out.println("roomBooked"+roomsBooked);
			stmt.executeQuery("INSERT INTO CONTACT_TABLE(CONTACT_ID,FIRSTNAME,LASTNAME,COUNTRY,ADDRESS,CITY,PHONE,EMAIL) VALUES(PAYMENT_SEQUENCE.NEXTVAL,\'"
					+ firstName
					+ "\',\'"
					+ lastName
					+ "\',\'"
					+ country
					+ "\',\'"
					+ Address
					+ "\',\'"
					+ city
					+ "\',\'"
					+ phone
					+ "\',\'" + email + "\')");
			stmt.executeQuery("INSERT INTO PAYMENT_TABLE(PAYMENT_ID,CARD_TYPE,CARD_NUM,EXP_YEAR,CVV) VALUES(PAYMENT_SEQUENCE.NEXTVAL,\'"
					+ cardType
					+ "\',\'"
					+ cardNumber
					+ "\',\'"
					+ expirationYear + "\',\'" + cvv + "\')");
			
			System.out.println(roomsBooked);
			String contact_ID = "";
			String payment_ID = "";
			ResultSet contactId = stmt
					.executeQuery("SELECT CONTACT_ID FROM CONTACT_TABLE WHERE EMAIL=\'"
							+ email + '\'');
			while (contactId.next()) {
				contact_ID = contactId.getString("CONTACT_ID");
			}
			ResultSet paymentId = stmt
					.executeQuery("SELECT PAYMENT_ID FROM PAYMENT_TABLE WHERE CARD_NUM=\'"
							+ cardNumber + '\'');
			while (paymentId.next()) {
				payment_ID = paymentId.getString("PAYMENT_ID");
			}
			stmt.executeQuery("INSERT INTO BOOKINGS(BOOKING_ID,USERNAME,CHECKIN,CHECKOUT,CONTACT_ID,PAYMENT_ID,ROOMS) VALUES(PAYMENT_SEQUENCE.NEXTVAL,\'"
					+ userName
					+ "\',TO_DATE(\'"
					+ checkInDateString
					+ "\',\'mm/dd/yyyy\'),"
					+ "TO_DATE(\'"
					+ checkOutDateString
					+ "\',\'mm/dd/yyyy\'),\'"
					+ contact_ID
					+ "\',\'"
					+ payment_ID + "\',\'" + roomsBooked + "\')");
			conn.close();
			req.getRequestDispatcher("/BookingConfirmation.jsp").forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// REMEMBER TO CLOSE THE CONNECTION TO DATABASE!!!!!!!!
	}
}
