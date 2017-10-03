package mcarbon1;

import java.sql.*;

public class sender
{
public static void main(String args[]) throws ClassNotFoundException, SQLException
{
	int i=0;
	String str,k;
	try
	{
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.200.208.29:1521:orcl12", "ttt", "ttt");
Statement st = con.createStatement();
ResultSet rs1;
//int rs = st.executeUpdate("Insert into DBTest values('fetch type --memory than DB ,reloading --NO,single table ,datafound=true','Application  must be fetch data from memory if  found not fetch from db ','1243123415','10','2017-05-05','DL','test','pawan','hello','10.200.208.29','1234','getrequest','104','Db(\"basemanager.db.clean.json\")','Config(\"basemanager.config104.json\")','Restart(\"basemanager.restart.json\")','Wait(60)','Db(\"basemanager.db.insert.json\")','Udp(\"basemanager.udplistener.json\")','Udp(\"basemanager.udpsender.json\")','Result(type:udp,provider:test.basemanager.Decoder.decode,cwt:msisdn|param_1,cwv:@msisdn|@param_1)','Result(type:db,provider:basemanager.db.select.json,cwt:aon|datetime,cwv:@Result.aon|@Result.datetime)',' ')");
//int rs = st.executeUpdate("Insert into DBTest values('fetch type --memory than DB ,reloading --NO,single table ,datafound=false','Application  must be fetch data from memory , if not found data fetch from db ','3','30','2017-05-05','DL','test','pankaj','Pankaj','10.200.208.29','1234','getrequest'	,'104',	'Db(\"basemanager.db.clean.json\")',	'Config(\"basemanager.config104.json\")'	,'Restart(\"basemanager.restart.json\")'	,'Wait(60)'	,'	','Udp(\"basemanager.udplistener.json\")',	'Udp(\"basemanager.udpsender.json\")'	,'Result(type:udp,provider:test.basemanager.Decoder.decode,cwt:msisdn|param_1|aon|datetime,cwv:@msisdn|@param_1|0|0)','	',' ')");
//int rs = st.executeUpdate("delete from DBTest");
rs1=st.executeQuery("select * from DBTest ");
while (rs1.next()) {
	str = rs1.getString(3);
	k = rs1.getString(2);
	System.out.println(str);
}
}catch(Exception e){
	e.printStackTrace();
}
}
}