package com.Comcast.crm.generic.databaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	
	Connection con;

	public void getDbconnection(String url, String username, String password) throws SQLException {
		try {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		
		 con = DriverManager.getConnection(url, username, password);
	}
		catch(Exception e) {}
	}
	
	public void getDbconnection() throws SQLException {
		try {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vtiger2","root","root");
	}
		catch(Exception e) {}
	}
	
	
	public void closeDbconnection() throws SQLException {
		try {
		con.close();
		}
		catch(Exception e) {}
	}
	
	public ResultSet executeConSelectQuery(String Query) throws Throwable {
		ResultSet result=null;
		try {
		Statement stat = con.createStatement();
		 result = stat.executeQuery(Query);
		
		}
		catch(Exception e) {}
		return result;
	}
	
	public int executeNonSelectQuery(String Query) throws Throwable {
		int result=0;
		try {
		Statement stat = con.createStatement();
		 result = stat.executeUpdate(Query);
		
		}
		catch(Exception e) {}
		return result;
	}
}
