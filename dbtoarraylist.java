package mcarbon1;

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

public class dbtoarraylist {
	Scanner sc = new Scanner(System.in);
	int count = 1;
	ArrayList<DBData> alldata = null;
	HashMap<String, Integer> hashmap = null;
	public int total_test_case = 0;

	public dbtoarraylist() {
		alldata = new ArrayList<DBData>();
		hashmap = new HashMap<String, Integer>();
	}

	public static void main(String[] args) {
		dbtoarraylist da = new dbtoarraylist();
		try {
			da.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void execute() throws Exception {

		getAlldata();
		getTotalTestCase();
		getmetadata();
		

		System.out.println("\n\nAll data :\n ");

		for (DBData DbData : alldata) {
			System.out.println(DbData.msisdn);
		}

		totalparameter();
		System.out.println("\n\nmeta data : \n");

		for (Map.Entry m : hashmap.entrySet()) {
			System.out.println(m.getValue() + " " + m.getKey());
		}
		   getvaluefromhash();
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

	public void getAlldata() throws SQLException {

		ResultSet rs = getdbconnection();
		while (rs.next()) {

			DBData DbData = new DBData(rs.getString("input_msisdn"), rs.getString("Desc_TestCaseCondition"));
			alldata.add(DbData);
			total_test_case++;

		}

	}

	public HashMap<String, Integer> getmetadata() throws SQLException {

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
		return hashmap;
	}

	public void getTotalTestCase() {
		System.out.println("total test cases=  " + total_test_case);
	}

	public void totalparameter() {
		System.out.println("\n\ntotal parameter=   " + --count);
	}

	void getvaluefromhash() {
		System.out.println("\nenter the key which index you want\n");
		String sc1 = sc.nextLine();
		String s = null;
		s = hashmap.get(sc1).toString();
		int index = Integer.parseInt(s);
		System.out.println("index = " + index);
		
	}

}

class DBData {
	String msisdn;
	String Desc_TestCaseCondition;
DBData(String msisdn, String Desc_TestCaseCondition) {
		this.msisdn = msisdn;
		this.Desc_TestCaseCondition = Desc_TestCaseCondition;
	}
}
