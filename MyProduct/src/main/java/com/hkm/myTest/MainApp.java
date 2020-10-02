package com.hkm.myTest;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MainApp {
	
	//this object will be used to perform all the CRUD operations
	static MongoCollection<Document> mcc=null;
	  
	   public static void main(String[] args) {
		   
		   try{
			    MongoClient mc =MongoClients.create("mongodb://localhost:27017"); //opening connection
			    //MongoClient mc = MongoClients.create("mongodb+srv://hkm:hkm%400101@cluster0.pqcrn.mongodb.net/library?retryWrites=true&w=majority");
			    System.out.println("connection created");
	    		MongoDatabase db = mc.getDatabase("library"); //connecting to desired database
	    		System.out.println("get database");
	    		mcc = db.getCollection("Product");// open desired collection
	    		System.out.println("got collection");
	    		System.out.println(mc.getClusterDescription());
	    	}catch(Exception e){}
	      int x;
	      //ProductDao test = new ProductDao();
	      ProductDaoUsingMongo test = new ProductDaoUsingMongo();
	      Product pd = new Product();
	      
	      try(Scanner in = new Scanner(System.in);){
	      do{
	    	  System.out.println("--------------------------------------");
		      System.out.println("What You want to do?");
		      System.out.println("1. Add Product\n2. Edit Product\n3. View Product By Id\n4. List of Products\n5. Product By Name\n6. Product I'd you want to delete\n7.Exit");
		      System.out.println("--------------------------------------");
		      
		      x = Integer.parseInt(in.nextLine());
		      switch(x){
		      
		      //Adding new Product
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
		    	  test.addProduct(pd);//Calling addProduct Function
		    	  break;}
		      
		      //Editing the detail of Product
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
		    	  test.editProduct(pd); // calling editProduct function
		    	  break;}
		      
		      //Show Product by Id
		      case 3: {
		    	  System.out.println("Enter Product ID");
				  String id = in.nextLine();
		    	  test.viewProduct(id);//Calling viewProduct function
		    	  break;}
		      
		      //Show complete list of product
		      case 4: test.listProduct();//Calling listProduct function
		      break;
		      
		      //Show Product by Name
		      case 5: {
		    	  System.out.println("Enter Name of Product");
				  String name = in.nextLine();
		    	  test.nameProduct(name);//Calling nameProduct function
		    	  break;}
		      
		      //Deleting the Product
		      case 6: {System.out.println("Enter id of product you want to delete");
		               String id = in.nextLine();
		               test.deleteProduct(id);////Calling deleteProduct function
		               break;}
		      
		      //Closing Program
		      case 7: {System.out.println("Closing program......");System.exit(0);}//Closing Program
		      
		      default: System.out.println("wrong input");//Error for wrong input
		      }
		  }while(x!=7);
	      in.close();
	   }catch(Exception e){
		   System.out.println(e);
	   } 
	   }
	}
