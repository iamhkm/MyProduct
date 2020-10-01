package com.hkm.myTest;

import java.util.Scanner;

public class MainApp {
	  
	   public static void main(String[] args) {
	      
	      int x;
	      //ProductDao test = new ProductDao();
	      ProductDaoUsingMongo test = new ProductDaoUsingMongo();
	      Product pd = new Product();
	      
	      try(Scanner in = new Scanner(System.in);){
	      do{
	    	  System.out.println("--------------------------------------");
		      System.out.println("What You want to do?");
		      System.out.println("1. Add Product\n2. Edit Product\n3. View Product By Id\n4. List of Products\n5. Product By Name\n6. Exit ");
		      System.out.println("--------------------------------------");
		      
		      x = Integer.parseInt(in.nextLine());
		      switch(x){
		      case 1: {
		    	  System.out.println("Enter Product ID");
				  String id = in.nextLine();
				  System.out.println("Enter Product Name");
				  String name = in.nextLine();
				  System.out.println("Enter Product Quantity");
				  int quantity = Integer.parseInt(in.nextLine());
				  System.out.println("Enter is alive only yes/no");
				  String isalive = in.nextLine();
				  pd.setId(id);
				  pd.setName(name);
				  pd.setQuantity(quantity);
				  pd.setInAvailable(isalive);
		    	  test.addProduct(pd);break;}
		      
		      case 2: {
		    	  System.out.println("Enter Product ID");
				  String id = in.nextLine();
				  System.out.println("Enter Product Name");
				  String name = in.nextLine();
				  System.out.println("Enter Product Quantity");
				  int quantity = Integer.parseInt(in.nextLine());
				  System.out.println("Enter is alive only yes/no");
				  String isalive = in.nextLine();
				  pd.setId(id);
				  pd.setName(name);
				  pd.setQuantity(quantity);
				  pd.setInAvailable(isalive);
		    	  test.editProduct(pd);break;}
		      
		      case 3: {
		    	  System.out.println("Enter Product ID");
				  String id = in.nextLine();
		    	  test.viewProduct(id);break;}
		      
		      case 4: test.listProduct();break;
		      
		      case 5: {
		    	  System.out.println("Enter Name of Product");
				  String name = in.nextLine();
		    	  test.nameProduct(name);break;}
		      
		      case 6: {System.out.println("Enter id of product you want to delete");
		               String id = in.nextLine();
		               test.deleteProduct(id);break;}
		      
		      case 7: {System.out.println("Closing program......");System.exit(0);}
		      
		      default: System.out.println("wrong input");
		      }
		  }while(x!=6);
	      in.close();
	   }catch(Exception e){
		   System.out.println(e);
	   } 
	   }
	}
