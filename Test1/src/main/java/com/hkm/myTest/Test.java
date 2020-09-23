package com.hkm.myTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test {
	  private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	  private final String DB_URL = "jdbc:mysql://localhost/library";
	  private final String USER = "root";
	  private final String PASS = "hkm0101";
	  Connection con;
	  PreparedStatement ps;
	  Scanner in = new Scanner(System.in);
	  Product product = new Product();
	  
	  Test(){
		  try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Driver or Configuration error");
		}
			
	  }
	  
	  protected void addProduct(){
		  System.out.println("Enter Product ID");
		  String id = in.nextLine();
		  System.out.println("Enter Product Name");
		  String name = in.nextLine();
		  System.out.println("Enter Product Quantity");
		  int quantity = Integer.parseInt(in.nextLine());
		  System.out.println("Enter is alive only yes/no");
		  String isalive = in.nextLine();
		  
		  product.setId(id);
		  product.setName(name);
		  product.setQuantity(quantity);
		  product.setInAvailable(isalive);
		  
		  
		   try {
			ps = con.prepareStatement("insert into products values(?,?,?,?)"); 
			ps.setString(1,product.getId());
			ps.setString(2,product.getName());
			ps.setInt(3,product.getQuantity());
			ps.setString(4,product.getInAvailable());
			  
			ps.executeUpdate();  
			ps.close();
		} catch (SQLException e) {
			System.out.println("error.....check if id already exist or check input again...");
		}
		   
	   }
	   
	   protected void editProduct(){
		   System.out.println("Enter Product ID");
			  String id = in.nextLine();
			  System.out.println("Enter Product Name");
			  String name = in.nextLine();
			  System.out.println("Enter Product Quantity");
			  int quantity = Integer.parseInt(in.nextLine());
			  System.out.println("Enter is alive only yes/no");
			  String isalive = in.nextLine();
			  
			  product.setId(id);
			  product.setName(name);
			  product.setQuantity(quantity);
			  product.setInAvailable(isalive);
			  
			  
			   try {
				ps = con.prepareStatement("update products set Name=?,Quantity=?,isAlive=? where Id=?"); 
				ps.setString(1,product.getName());
				ps.setInt(2,product.getQuantity());
				ps.setString(3,product.getInAvailable());
				ps.setString(4,product.getId());
				ps.executeUpdate();  
				ps.close();
			} catch (SQLException e) {
				System.out.println("error.....check if id already exist or check input again...");
			}
	   }
	   
	   protected void viewProduct(){
		      System.out.println("Enter Product ID");
			  String id = in.nextLine();
			  
			   try {
				ps = con.prepareStatement("select *from products where Id=?"); 
				ps.setString(1,id);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
				System.out.println("ID:"+rs.getString(1)+"\t"+"Name:"+rs.getString(2)+"\tQuantity:"+rs.getInt(3)+"\tIsAvailable:"+rs.getString(4));}
				else{
					System.out.println("error.....no such id exist");
				}
				ps.close();
				rs.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
	   }
	   
	   protected void listProduct(){
		   
		   try {
				ps = con.prepareStatement("select *from products"); 
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
				System.out.println("ID:"+rs.getString(1)+"\t"+"Name:"+rs.getString(2)+"\tQuantity:"+rs.getInt(3)+"\tIsAvailable:"+rs.getString(4));}
				ps.close();
			        rs.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
	   }
	   
	   protected void nameProduct(){
		      System.out.println("Enter Name of Product");
			  String id = in.nextLine();
		   try {
				ps = con.prepareStatement("select *from products where name=?"); 
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
				System.out.println("ID:"+rs.getString(1)+"\t"+"Name:"+rs.getString(2)+"\tQuantity:"+rs.getInt(3)+"\tIsAvailable:"+rs.getString(4));}
				ps.close();
			        rs.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
	   }
}
