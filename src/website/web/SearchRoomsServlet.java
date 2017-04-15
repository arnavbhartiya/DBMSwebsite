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
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * @author Arnav Bhartiya Apr 14, 2017
 */
public class SearchRoomsServlet extends HttpServlet {
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
		String checkInDateString = req.getParameter("checkInDate");
		String checkOutDateString = req.getParameter("checkOutDate");
		int numberOfRooms = Integer.parseInt(req.getParameter("numberOfRooms"));
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date checkInDate = df.parse(checkInDateString);
			Date checkOutDate = df.parse(checkOutDateString);
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
			ResultSet searchResultFromDb = stmt
					.executeQuery("select * from rooms where BOOKED_TILL_DATE<TO_DATE(\'"
							+ checkInDateString + "\', \'mm/dd/yyyy\')");
			req.setAttribute("searchRoomsResultset", searchResultFromDb);
			req.getRequestDispatcher("/SearchRooms.jsp").forward(req, resp);
			
			
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
