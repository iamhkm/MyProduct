package com.hkm.myTest;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnection {

	private static DBConnection single_instance = null; 
    String configFile = "src/main/DBConnectionAttributes.properties";
    HikariConfig cfg = new HikariConfig(configFile);
    HikariDataSource ds = new HikariDataSource(cfg);
	Connection con=null;
	
    private DBConnection() 
    { 
       
    } 
  
    // static method to create instance of Singleton class 
    public static DBConnection getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new DBConnection(); 
  
        return single_instance; 
    }
    
	  
	  protected Connection connection(){
		try{
			con=ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	  }
	  
}
