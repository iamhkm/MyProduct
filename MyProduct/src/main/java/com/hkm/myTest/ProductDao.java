package com.hkm.myTest;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductDao{
	  
	  DBConnection dbc = DBConnection.getInstance();
	  Connection con = dbc.connection();
	  
	  protected void addProduct(Product pd){
		  
		   try(PreparedStatement ps = con.prepareStatement("insert into products values(?,?,?,?)");) {
			ps.setString(1,pd.getId());
			ps.setString(2,pd.getName());
			ps.setInt(3,pd.getQuantity());
			ps.setString(4,pd.getInAvailable()); 
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error.....check if id already exist or check input again...");
		}
		   
	   }
	   
	   protected void editProduct(Product pd){
			  
			   try(PreparedStatement ps = con.prepareStatement("update products set Name=?,Quantity=?,isAlive=? where Id=?"); ) {
				ps.setString(1,pd.getName());
				ps.setInt(2,pd.getQuantity());
				ps.setString(3,pd.getInAvailable());
				ps.setString(4,pd.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("error.....check if id already exist or check input again...");
			}
	   }
	   
	   protected void viewProduct(String id){
		      
			  
			   try(PreparedStatement ps = con.prepareStatement("select *from products where Id=?");) {
				ps.setString(1,id);
				try(ResultSet rs = ps.executeQuery();){
				if(rs.next()){
				System.out.println("ID:"+rs.getString(1)+"\t"+"Name:"+rs.getString(2)+"\tQuantity:"+rs.getInt(3)+"\tIsAvailable:"+rs.getString(4));}
				else{
					System.out.println("error.....no such id exist");
				}
				rs.close();
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
	   }
	   
	   protected void listProduct(){
		   try(PreparedStatement ps = con.prepareStatement("select *from products"); ResultSet rs = ps.executeQuery();) {
				while(rs.next()){
				System.out.println("ID:"+rs.getString(1)+"\t"+"Name:"+rs.getString(2)+"\tQuantity:"+rs.getInt(3)+"\tIsAvailable:"+rs.getString(4));}
			} catch (SQLException e) {
				System.out.println(e);
			}
	   }
	   
	   protected void nameProduct(String name){
		   try (PreparedStatement ps = con.prepareStatement("select *from products where name=?"); ){
				ps.setString(1, name);
				try(ResultSet rs = ps.executeQuery();){
				while(rs.next()){
				System.out.println("ID:"+rs.getString(1)+"\t"+"Name:"+rs.getString(2)+"\tQuantity:"+rs.getInt(3)+"\tIsAvailable:"+rs.getString(4));}
				rs.close();
			} 
		   }catch (SQLException e) {
				System.out.println(e);
			}
	   }
}
