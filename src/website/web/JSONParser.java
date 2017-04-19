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

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Arnav Bhartiya
 * Apr 18, 2017 
 */
public class JSONParser {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		BufferedReader br = new BufferedReader(new FileReader("usersTableGenerator.txt"));
		StringBuffer str = new StringBuffer();
		String line;
		while((line= br.readLine())!=null){
			str.append(line);
		}
		JSONObject obj = new JSONObject(str.toString());
		JSONArray arr = obj.getJSONArray("arr");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl",
				"arnav", "DBMSwebsite");
		Statement stmt = conn.createStatement();
		
		for(int i=0;i<arr.length();i++){
		obj=arr.getJSONObject(i);
		int Contact_Id = (int) obj.get("CONTACT_ID");
		String username = (String) obj.get("username");
		String password = (String) obj.get("FIRSTNAME");
		String lastName = (String) obj.get("LASTNAME");
		String phone = (String) obj.get("PHONE");
		String address = (String) obj.get("ADDRESS");
		String email = (String) obj.get("EMAIL");
		String country = (String) obj.get("COUNTRY");
		System.out.println("INSERT INTO CONTACT_TABLE(CONTACT_ID,FIRSTNAME,LASTNAME,COUNTRY,ADDRESS,CITY,PHONE,EMAIL) VALUES("+Contact_Id+",\'"
				+ firstName
				+ "\',\'"
				+ lastName
				+ "\',\'"
				+ country
				+ "\',\'"
				+ address
				+ "\',\'"
				+ city
				+ "\',\'"
				+ phone
				+ "\',\'" + email + "\')");
		stmt.executeQuery("INSERT INTO CONTACT_TABLE(CONTACT_ID,FIRSTNAME,LASTNAME,COUNTRY,ADDRESS,CITY,PHONE,EMAIL) VALUES("+Contact_Id+",\'"
				+ firstName
				+ "\',\'"
				+ lastName
				+ "\',\'"
				+ country
				+ "\',\'"
				+ address
				+ "\',\'"
				+ city
				+ "\',\'"
				+ phone
				+ "\',\'" + email + "\')");
		}
		stmt.executeQuery("commit");
		conn.close();
	}

}
