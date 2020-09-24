package com.hkm.myTest;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnection {
	String configFile = "src/main/DBConnectionAttributes.properties";
    
    HikariConfig cfg = new HikariConfig(configFile);
    HikariDataSource ds = new HikariDataSource(cfg);
	  
	  Connection con=null;
	  
	  DBConnection(){
		  try {
				con=ds.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("error with connection");
			}
	  }
}
