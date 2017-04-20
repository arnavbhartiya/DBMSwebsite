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
		String[] username={"Elaina"," Delorse"," Nakesha"," Zenaida"," Nelly"," Buck"," Sherlyn"," Krissy"," Chante"," Tracie"," Rebbecca"," Marcia"," Lisette"," Tierra"," Velda"," Nanci"," Andra"," Ilda"," Daine"," Anastasia"," Billye"," Beryl"," Toya"," Lauren"," Norene"," Clay"," Nanette"," Ammie"," Renna"," Dimple"," Lenny"," Nedra"," Lavenia"," Juliane"," Cherryl"," Kathryn"," Naoma"," Lora"," Winford"," Aletha"," Kaley"," Tyrone"," Codi"," Viola"," Cassondra"," Debby"," Kylie"," Criselda"," Mireille"," Shasta"};
		int paymentId=19951;
		int user=0;
		String checkInDateString="04/01/2017";
		String checkOutDateString="04/30/2017";
		int password =200001101;
		int roomsBooked=940;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl",
				"arnav", "DBMSwebsite");
		Statement stmt = conn.createStatement();
		for(int i=0;i<50;i++){
			String query ="INSERT INTO BOOKINGS(BOOKING_ID,USERNAME,CHECKIN,CHECKOUT,CONTACT_ID,PAYMENT_ID,ROOMS) VALUES(PAYMENT_SEQUENCE.NEXTVAL,\'"
					+ username[user++]
					+ "\',TO_DATE(\'"
					+ checkInDateString
					+ "\',\'mm/dd/yyyy\'),"
					+ "TO_DATE(\'"
					+ checkOutDateString
					+ "\',\'mm/dd/yyyy\'),\'"
					+ paymentId
					+ "\',\'"
					+ (paymentId++) + "\',\'" + (roomsBooked++) + "\')";
			
			System.out.println(query);
			stmt.executeQuery(query);
		}
		stmt.executeQuery("commit");
		conn.close();
	}
}
