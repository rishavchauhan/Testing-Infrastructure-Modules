
package mcarbon1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Scanner;
import java.util.Set;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class updatejson 
{
	private static final Exception NullPointerException = null;
	private static final Exception IndexOutOfBoundsException = null;
	private static Scanner sc;

	int update(JSONObject jsonObject, String key, String value,int flag,String split1) throws Exception 
	{
	   int m=0;
	   if(flag==1)
	   {   
		   String []i=split1.split("\\]");
	       int j=Integer.parseInt(i[0]);
		   JSONArray arr = new JSONArray();
		   arr = (JSONArray)jsonObject.get(key);
			String str = (arr.get(j).toString());
			if(!str.equals(null))
			{
			arr.remove(j);
			arr.add(j,value);
			}
			else
			{
				throw IndexOutOfBoundsException;
			}
		  m=1;
	   }
	   else if(flag==0)
	   {
		jsonObject.replace(key, value);
		m=1;
	   }
        return m;
	}

	int check(int n,String KeyMain, Set<String> k) 
	{
		for (String strr : k) 
		{
			if (strr.equals(KeyMain)) 
			{
				n = 1;
				break;
			}
		}
		
		return n;
	}

	public static void main(String[] args) throws Exception, NullPointerException 
	{
		updatejson up = new updatejson();
		int l = 0, n = 0,m = 0,j,flag = 0,z;
		Set<String> k;
		String key, value;
		String []keyMain=null;
		String []split=null;
		JSONParser parser = new JSONParser();
		JSONObject jsonObject;
		
		JSONObject[] jsonchildobject = new JSONObject[100];
		try 
		{
			sc = new Scanner(System.in);
			FileReader fr = new FileReader("d:\\basemanager.db.clean.json");
			FileWriter fw = new FileWriter("d:\\file3.json");
			jsonObject = (JSONObject) parser.parse(fr);
			System.out.println(jsonObject);
			System.out.println("enter the key and corresponding value you want to change\n");
			key = sc.nextLine();
			value = sc.nextLine();
			int length=key.length();
			char []key1=new char[100];
			key1=key.toCharArray();
			for(z = 0;z<key1.length;z++)
			{
			if(key1[z]=='[')
			{
			split=	key.split("\\[");
			keyMain=split[0].split("\\.");
			flag = 1;
			break;
			}
			}
			if(flag!=1)
			{
			keyMain = key.split("\\.");
			}
			j = keyMain.length;
			do 
			{
				n = 0;
				if (j == 1) 
				{
					k = jsonObject.keySet();
					n = up.check(n,keyMain[0], k);
					if (n == 1&&flag==1)
					{
					m = up.update(jsonObject, keyMain[0], value,flag,split[1]);
					}
					else if(n==1)
					{
						m = up.update(jsonObject, keyMain[0], value,flag,null);
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
					k = jsonchildobject[l].keySet();
					n = up.check(n,keyMain[l+1], k);
				    	j--;
				    	l++;				
				    if(n!=1)
				    	throw NullPointerException;
				}	
		   } while (j > 1);
			
			if(m!=1&&flag==1)
				up.update(jsonchildobject[l - 1], keyMain[l],value,flag,split[1]);	
			else if(m!=1)
				up.update(jsonchildobject[l - 1], keyMain[l],value,flag,null);
			System.out.println("after updation" + jsonObject);
			fw.write(jsonObject.toJSONString());
			fw.close();
			fr.close();

		} 
		catch (FileNotFoundException e) 		{ e.printStackTrace(); } 
		catch (IOException e) 	  				{ e.printStackTrace(); }
		catch (ParseException e) 				{ e.printStackTrace(); }
		catch (ClassCastException e)    		{ System.out.println("key not found, please enter a valid key");} 
		catch (NullPointerException e)  		{ System.out.println("key not found, please enter a valid key");    }
	    catch (ArrayIndexOutOfBoundsException e){ System.out.println("key not found, please enter a valid key");    }
		catch (IndexOutOfBoundsException e){ System.out.println("key not found, please enter a valid key");    }
	}

}
