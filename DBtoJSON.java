package dbreader;

import java.sql.SQLException;

public class DBtoJSON {

	public static void main(String[] args) throws Exception, NullPointerException {
		JsonReader defaul;
		defaul = new JsonReader();
		try {
			defaul.execute();
		} catch (Exception e) {
			System.out.println("Exception occured");
		}
	}

}
