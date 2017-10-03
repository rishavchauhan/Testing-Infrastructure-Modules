
package dbreader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Scanner;
import java.util.Set;

import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLException;

public class JsonReader {
	
	private static final Exception NullPointerException = null;
	private static final Exception IndexOutOfBoundsException = null;
	private static Scanner sc;
	int l,n,m,j,flag,z,index,length,ans;
	char[] key1 ;
	static Set<String> keys;
	static String key;
	String value;
	static String[] keyMain;
	static String[] split;
	JSONParser parser ;
	JSONObject jsonObject;
	JSONArray arr ;
	String filename;
	JSONObject[] jsonchildobject;
	static FileReader fr;
	static FileWriter fw ;
	String str;
	public JsonReader()
	{
	sc=new Scanner(System.in);	
	this.l= this.n = this.m = this.flag = this.ans=0;
	this.value=null;
	this.key1 = new char[100];
	this.jsonObject=new JSONObject();
	this.parser = new JSONParser();
	this.arr = new JSONArray();
	this.jsonchildobject = new JSONObject[100];
	
	}
	
	public JsonReader(String filename) throws SQLException 
	{
		this.filename=filename;
	}
	
	void execute() throws SQLException 
	{
		dbreader db=new dbreader();
		db.getmetadata();
	    String filename=db.getAlldata();
		JsonReader param;
		param = new JsonReader(filename);
		try {
			 sc = new Scanner(System.in);
			 fr= new FileReader("d:\\"+filename);
			 fw = new FileWriter("d:\\file3.json");
			jsonObject = (JSONObject) parser.parse(fr);
			System.out.println(jsonObject);
			System.out.println("\nenter the key whose corresponding value you want\n");
			key = sc.nextLine();
			length = key.length();
			key1 = key.toCharArray();
			
			for (z = 0; z < key1.length; z++) {
				if (key1[z] == '[') {
					split = key.split("\\[");
					keyMain = split[0].split("\\.");
					flag = 1;
					break;
				}
			}
			
			if (flag != 1)
			{
				keyMain = key.split("\\.");
			}
			j = keyMain.length;
			
			
			do {
				    
				  n = 0;
				    if (j == 1) 
				    {
				    		keys = jsonObject.keySet();
				    		n = param.check(keyMain[0]);
					
					    if (n == 1 && flag == 1) 
					    {
					    		m = param.update(jsonObject, keyMain[0],split[1]);
					    } 
					    else if (n == 1) 
					    {
					    		m = param.update(jsonObject, keyMain[0],null);
					    } 
					    else
					    	break;
				  } 
				 else 
				 {
					if (j == keyMain.length)
						jsonchildobject[l] = (JSONObject) jsonObject.get(keyMain[l]);
					else
						jsonchildobject[l] = (JSONObject) jsonchildobject[l - 1].get(keyMain[l]);
				    keys = jsonchildobject[l].keySet();
				    n = param.check(keyMain[l + 1]);
				    
				    j--;
				    l++;
					if (n != 1)
						throw NullPointerException;
				}
			} while (j > 1);

			
			if (m != 1 && flag == 1)
			{
				param.update(jsonchildobject[l - 1], keyMain[l],split[1]);
			}
			else if (m != 1) 
			{
				param.update(jsonchildobject[l - 1], keyMain[l],null);
			}
			
			System.out.println("after updation" + jsonObject);
			fw.write(jsonObject.toJSONString());
			fw.close();
			fr.close();

		} catch (Exception e) {	System.out.println("key not found");} 
    }
	
	
int check(String KeyMain) {
		for (String strr : keys) {
			if (strr.equals(KeyMain)) {
				n = 1;
				break;
			}
		}

		return n;
	}


int update(JSONObject jsonObject, String key,String split1) throws Exception 
	{
		m = 0;
		    if (flag == 1) 
			{
				String[] i = split1.split("\\]");
				index = Integer.parseInt(i[0]);
				arr = (JSONArray) jsonObject.get(key);
				str = arr.get(index).toString();
				System.out.println("value = "+str);
				System.out.println("\npress 1 if want to update the existing value else 0");
				ans=sc.nextInt();
				if(ans==1)
				{
					System.out.println("\n enter the new value you want to put on a particular key\n");
					value=sc.nextLine();
					if (!str.equals(null)) 
					{
						arr.remove(index);
						arr.add(index, value);
					} 
				  else 
				  {
					  throw IndexOutOfBoundsException;
				  }
			   }
			   m = 1;
		  } 
		  else if (flag == 0) 
		  {
			  str = (String) jsonObject.get(key);
			  System.out.println("value = "+str);
			  System.out.println("\npress 1 if want to update the existing value else 0");
			  ans=sc.nextInt();
			  if(ans==1)
			  {    System.out.println("\n enter the new value you want to put on a particular key\n");
				   value=sc.nextLine();
				  jsonObject.replace(key, value);
			  }
			  m = 1;
		}
		return m;
	}
}
