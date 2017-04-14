/**
 * 
 */
package website.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Arnav Bhartiya
 * Apr 14, 2017 
 */
public class SearchRoomsServlet extends HttpServlet {
/* (non-Javadoc)
 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	String checkInDate = req.getParameter("checkInDate");
	String checkOutDate = req.getParameter("checkOutDate");
	String numberOfRooms = req.getParameter("numberOfRooms");
	System.out.println("check in date was"+checkInDate);
	System.out.println("check out date was"+checkOutDate);
	System.out.println("number of rooms searched were"+numberOfRooms);
	
}
}
