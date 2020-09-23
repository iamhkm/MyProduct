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
	      Test test = new Test();
	      
	      do{
	    	  System.out.println("--------------------------------------");
		      System.out.println("What You want to do?");
		      System.out.println("1. Add Product\n2. Edit Product\n3. View Product By Id\n4. List of Products\n5. Product By Name\n6. Exit ");
		      System.out.println("--------------------------------------");
		      
		      x = in.nextInt();
		      switch(x){
		      case 1: test.addProduct();break;
		      case 2: test.editProduct();break;
		      case 3: test.viewProduct();break;
		      case 4: test.listProduct();break;
		      case 5: test.nameProduct();break;
		      case 6: {System.out.println("Closing program.....");System.exit(0);}
		      default: System.out.println("wrong input");
		      }
		  }while(x!=6);
	      in.close();
	   }   
	}
