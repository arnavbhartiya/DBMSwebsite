/**
 * 
 */
package website.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * @author Arnav Bhartiya
 * Apr 17, 2017 
 */
public class SQLfiller {

	/**
	 * @param argsf
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException, SQLException {
		String[] card_type={"mastercard","visa","american express","discover", "dummy"};
		int paymentId=1;
		int username =100001101;
		int password =200001101;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl",
				"arnav", "DBMSwebsite");
		Statement stmt = conn.createStatement();
		for(int i=0;i<20000;i++){
			String query = "INSERT INTO USERS(USERNAME,PASSWORD,CONTACT_ID,PAYMENT_ID) VALUES(\'"+(username++)+"\',\'"
					+ (password++)
					+ "\',\'"
					+ paymentId	
					+ "\',\'" + (paymentId++) + "\')";
			System.out.println(query);
			stmt.executeQuery(query);
		}
		stmt.executeQuery("commit");
		conn.close();
	}
}
