package com.hkm.myTest;

import java.util.Scanner;

public class MainApp {

	   public String start() {
		   return "success";
	   }

	   public static void main(String[] args) {
	      MainApp app = new MainApp();
	      System.out.println(app.start());
	      
	      Scanner in = new Scanner(System.in);
	      int x;
	      ProductDao test = new ProductDao();
	      
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
		    	  test.addProduct(id,name,quantity,isalive);break;}
		      
		      case 2: {
		    	  System.out.println("Enter Product ID");
				  String id = in.nextLine();
				  System.out.println("Enter Product Name");
				  String name = in.nextLine();
				  System.out.println("Enter Product Quantity");
				  int quantity = Integer.parseInt(in.nextLine());
				  System.out.println("Enter is alive only yes/no");
				  String isalive = in.nextLine();
		    	  test.editProduct(id,name,quantity,isalive);break;}
		      
		      case 3: {
		    	  System.out.println("Enter Product ID");
				  String id = in.nextLine();
		    	  test.viewProduct(id);break;}
		      
		      case 4: test.listProduct();break;
		      
		      case 5: {
		    	  System.out.println("Enter Name of Product");
				  String name = in.nextLine();
		    	  test.nameProduct(name);break;}
		      
		      case 6: {System.out.println("Closing program.....");System.exit(0);}
		      
		      default: System.out.println("wrong input");
		      }
		  }while(x!=6);
	      in.close();
	   }   
	}
