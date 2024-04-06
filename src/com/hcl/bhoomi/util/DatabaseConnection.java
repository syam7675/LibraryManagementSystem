package com.hcl.bhoomi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static final String DB_DRIVER="com.mysql.cj.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/LibraryAppDB";
	private static final String UNAME="root";
	private static final String PASS="Chinni@123";
	private static Connection conn=null;
	public static Connection getConnectionDb() throws ClassNotFoundException, SQLException
	{
		Class.forName(DB_DRIVER);
		conn=DriverManager.getConnection(URL,UNAME,PASS);
		return conn;
		
	}

}
