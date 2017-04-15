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
		String pass="DBMSwebsite";
		String password = req.getParameter("password");
		String passwordFromDb="";
		try {
		    Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl",
					"arnav", pass);
		    Statement stmt = conn.createStatement ();
		    ResultSet passwordFromDbResult = stmt.executeQuery ("select PASSWORD from USERS WHERE USERNAME=\'"+userName+"\'");
		    while (passwordFromDbResult.next()){
		        passwordFromDb=passwordFromDbResult.getString ("PASSWORD").toString();
		        System.out.println(passwordFromDb);
		    }
		      conn.close(); // ** IMPORTANT : Close connections when done **

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (password.equals(passwordFromDb)) {
			req.getRequestDispatcher("/CustomerProfile.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "wrong password");
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}
	}
}
