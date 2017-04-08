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

/**
 * @author Arnav Bhartiya Apr 2, 2017
 */
public class LoginServlet extends HttpServlet {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

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
		// TODO Auto-generated method stub
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		try {
		    Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl",
					"arnav", "93Kanhaiya");
		    Statement stmt = conn.createStatement ();
		    ResultSet result = stmt.executeQuery ("select * from USERS");
		    //System.out.println(result);
		    //System.out.println (result.getString ("USERNAME").toString());
		    while (result.next()){
		    	System.out.println("in while");
		        System.out.println (result.getString ("USERNAME").toString());
		        System.out.println (result.getString ("PASSWORD").toString());
		    }
		      conn.close(); // ** IMPORTANT : Close connections when done **

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (userName.equals("Arnav") && password.equals("weakPass")) {

		} else {
			req.setAttribute("error", "wrong password");
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}
	}
}
