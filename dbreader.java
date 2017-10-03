package dbreader;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class dbreader {
	
	private static final Exception ArrayIndexOutOfBoundsException = null;
	Scanner sc = new Scanner(System.in);
	int count = 1;
	ArrayList<DBData> alldata = null;
	HashMap<String, Integer> hashmap = null;
	public int total_test_case = 0;

	public dbreader() {
		alldata = new ArrayList<DBData>();
		hashmap = new HashMap<String, Integer>();
	}

	public static void main(String[] args) {
		dbreader da = new dbreader();
		try {
			da.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void execute() throws Exception {

		getmetadata();
		getAlldata();
	}

	public ResultSet getdbconnection() {
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.200.208.29:1521:orcl12", "ttt", "ttt");
			Statement statement = con.createStatement();
			String s = "SELECT * FROM DBTest";
			rs = statement.executeQuery(s);

		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

	public String getAlldata() throws SQLException {

		String []str2=null;
try
{
		ResultSet rs = getdbconnection();
		System.out.println("\nenter the Sequence of Operation you want to perform out of displayed operation");
		String str=sc.nextLine();
		String str1=null;
		while (rs.next()) {
			DBData DbData = new DBData(rs.getString(str));
			alldata.add(DbData);
			total_test_case++;

		}
		getTotalTestCase();
		System.out.println("\n\nenter the index no of which you want data :\n ");
		int j=sc.nextInt();
		int i=1;
		for (DBData DbData : alldata) {
			if(i==j)
				str1=DbData.str;
			i++;
			}
		str2=str1.split("\"");
		if(!str2[1].equals(null))
		System.out.println(str2[1]);
		else
			throw ArrayIndexOutOfBoundsException;

}catch(Exception e)
{
	System.out.println("File does not exist");
}
return str2[1];
	}

	public void getmetadata() throws SQLException {

		ResultSet rs = getdbconnection();
		ResultSetMetaData meta = null;
		meta = rs.getMetaData();

		int columncount = meta.getColumnCount();
		String[] columnNames = new String[columncount];

		while (count <= columncount) {

			columnNames[count - 1] = meta.getColumnLabel(count);
			hashmap.put(columnNames[count - 1], count);
			count++;
		}
		totalparameter();
		System.out.println("\n\nmeta data : \n");

		for (Map.Entry m : hashmap.entrySet()) {
			System.out.println(m.getValue() + " " + m.getKey());
		}
	}

	public void getTotalTestCase() {
		System.out.println("\n\ntotal test cases=  " + total_test_case);
	}

	public void totalparameter() {
		System.out.println("total parameter=   " + --count);
	}

	/*void getvaluefromhash() {
		System.out.println("\nenter the key which index you want\n");
		String sc1 = sc.nextLine();
		String s = null;
		s = hashmap.get(sc1).toString();
		int index = Integer.parseInt(s);
		System.out.println("index = " + index);
		
	}*/

}

class DBData {
	String str;
DBData(String str) {
		this.str = str;
	}
}
