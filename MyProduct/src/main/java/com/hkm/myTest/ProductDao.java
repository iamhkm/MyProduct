package com.hkm.myTest;

import java.sql.SQLException;
import java.util.Scanner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductDao extends DBConnection{
	  
	  
	  PreparedStatement ps=null;
	  Scanner in = new Scanner(System.in);
	  Product product = new Product();
	  
	  protected void addProduct(String id,String name,int quantity,String isalive){
		  product.setId(id);
		  product.setName(name);
		  product.setQuantity(quantity);
		  product.setInAvailable(isalive);
		  
		   try(PreparedStatement ps = con.prepareStatement("insert into products values(?,?,?,?)");) {
			ps.setString(1,product.getId());
			ps.setString(2,product.getName());
			ps.setInt(3,product.getQuantity());
			ps.setString(4,product.getInAvailable()); 
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error.....check if id already exist or check input again...");
		}
		   
	   }
	   
	   protected void editProduct(String id, String name, int quantity, String isalive){
			  product.setId(id);
			  product.setName(name);
			  product.setQuantity(quantity);
			  product.setInAvailable(isalive);
			  
			   try(PreparedStatement ps = con.prepareStatement("update products set Name=?,Quantity=?,isAlive=? where Id=?"); ) {
				ps.setString(1,product.getName());
				ps.setInt(2,product.getQuantity());
				ps.setString(3,product.getInAvailable());
				ps.setString(4,product.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("error.....check if id already exist or check input again...");
			}
	   }
	   
	   protected void viewProduct(String id){
		      
			  
			   try(PreparedStatement ps = con.prepareStatement("select *from products where Id=?"); ) {
				ps.setString(1,id);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
				System.out.println("ID:"+rs.getString(1)+"\t"+"Name:"+rs.getString(2)+"\tQuantity:"+rs.getInt(3)+"\tIsAvailable:"+rs.getString(4));}
				else{
					System.out.println("error.....no such id exist");
				}
				rs.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
	   }
	   
	   protected void listProduct(){
		   System.out.println("ok");
		   try(PreparedStatement ps = con.prepareStatement("select *from products"); ) {
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
				System.out.println("ID:"+rs.getString(1)+"\t"+"Name:"+rs.getString(2)+"\tQuantity:"+rs.getInt(3)+"\tIsAvailable:"+rs.getString(4));}
				rs.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
	   }
	   
	   protected void nameProduct(String name){
		   try (PreparedStatement ps = con.prepareStatement("select *from products where name=?"); ){
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
				System.out.println("ID:"+rs.getString(1)+"\t"+"Name:"+rs.getString(2)+"\tQuantity:"+rs.getInt(3)+"\tIsAvailable:"+rs.getString(4));}
				rs.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
	   }
}
