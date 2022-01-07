package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection{
	public Connection databaselink;
	
	public Connection getConnection(){
	String user = "root";
	String password = "1234567890p";
	String url = "jdbc:mysql://localhost:3306/school";
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		databaselink = DriverManager.getConnection(url,user,password);
	}catch(Exception e) {
		e.printStackTrace();
	}
	return databaselink;
  }
}
