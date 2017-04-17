/**
 * 
 */
package website.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Arnav Bhartiya
 * Apr 15, 2017 
 */
public class BookingServlet extends HttpServlet {
/* (non-Javadoc)
 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String roomType = req.getParameter("roomType");
		HttpSession session = req.getSession();
		int numberOfRooms= (int) session.getAttribute("numberOfRooms");
		session.setAttribute("roomType", roomType);
		System.out.println(numberOfRooms);
		req.setAttribute("roomType",roomType);
		req.setAttribute("numberOfRooms",numberOfRooms);
		req.getRequestDispatcher("/booking.jsp").forward(req, resp);
}
}
