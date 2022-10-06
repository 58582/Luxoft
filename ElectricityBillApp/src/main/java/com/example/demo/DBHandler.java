package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBHandler {
public Connection establishConnection(){
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/ebbill",
				"root",
				"sroot");
		}
	catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
}
