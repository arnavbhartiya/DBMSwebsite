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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * @author Arnav Bhartiya Apr 14, 2017
 */
public class SearchRoomsServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String checkInDateString = req.getParameter("checkInDate");
		String checkOutDateString = req.getParameter("checkOutDate");
		int numberOfRooms = Integer.parseInt(req.getParameter("numberOfRooms"));
		HttpSession session = req.getSession();
		session.setAttribute("numberOfRooms", numberOfRooms);
		String username=(String) session.getAttribute("username");
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date checkInDate = df.parse(checkInDateString);
			Date checkOutDate = df.parse(checkOutDateString);
			session.setAttribute("checkInDateString", checkInDateString);
			session.setAttribute("checkOutDateString", checkOutDateString);
			if (checkInDate.after(checkOutDate)) {
				req.setAttribute("checkInError",
						"Check In date can't be after Check out date!");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			} else if (numberOfRooms <= 0) {
				req.setAttribute("checkInError", "Invalid number of rooms!");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl",
					"arnav", "DBMSwebsite");
			Statement stmt = conn.createStatement();
			Map<String, Integer> roomTypeAvailableMap = findRoomsAvailable(
					stmt, checkInDateString);
			Map<String, List<String>> fullRoomDetailsMap = fillFullRoomDetails(
					stmt, roomTypeAvailableMap, numberOfRooms);
			conn.close();
			req.setAttribute("username", username);
			req.setAttribute("fullRoomDetailsMap", fullRoomDetailsMap);
			req.getRequestDispatcher("/SearchRooms.jsp").forward(req, resp);

		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	private Map<String, Integer> findRoomsAvailable(Statement stmt,
			String checkInDateString) throws SQLException {
		Map<String, Integer> roomTypeAvailableMap = new HashMap<String, Integer>();
		ResultSet searchResultFromDb = stmt
				.executeQuery("select * from rooms where BOOKED_TILL_DATE<TO_DATE(\'"
						+ checkInDateString + "\', \'mm/dd/yyyy\')");
		while (searchResultFromDb.next()) {
			String room_type = searchResultFromDb.getString("ROOM_TYPE")
					.toString();
			if (!roomTypeAvailableMap.containsKey(room_type)) {
				roomTypeAvailableMap.put(room_type, 1);
			} else {
				roomTypeAvailableMap.put(room_type,
						roomTypeAvailableMap.get(room_type) + 1);
			}
		}
		return roomTypeAvailableMap;
	}
	private Map<String, List<String>> fillFullRoomDetails(Statement stmt,
			Map<String, Integer> roomTypeAvailableMap, int numberOfRooms) throws SQLException {
		Map<String, List<String>> fullRoomDetailsMap = new HashMap<String, List<String>>();
		List<String> roomDetails = new ArrayList<String>();
		for (Map.Entry<String, Integer> entrySet : roomTypeAvailableMap
				.entrySet()) {
			if(entrySet.getValue()>=numberOfRooms){

				ResultSet getRoomDetailsFromDb = stmt
						.executeQuery("select * from ROOMTYPE where Room_type=\'"
								+ entrySet.getKey() + "\'");
				while (getRoomDetailsFromDb.next()) {
					String roomPrice = getRoomDetailsFromDb.getString("PRICE")
							.toString();
					String roomDescription = getRoomDetailsFromDb
							.getString("ROOM_DESCRIPTION");
					String roomsAvailable = entrySet.getValue() + "";
					roomDetails.add(roomPrice);
					roomDetails.add(roomsAvailable);
					roomDetails.add(roomDescription);
				}
				fullRoomDetailsMap.put(entrySet.getKey(), roomDetails);
				roomDetails = new ArrayList<>();
			}
		}
		return fullRoomDetailsMap;
	}
}
