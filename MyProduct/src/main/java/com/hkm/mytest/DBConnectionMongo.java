package com.hkm.myTest;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class DBConnectionMongo {
	
	private static DBConnectionMongo single_instance = null; 
	private MongoCollection<Document> mcc=null;
	
	private DBConnectionMongo() 
    { 
       
    } 
  
    // static method to create instance of Singleton class 
    public static DBConnectionMongo getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new DBConnectionMongo(); 
  
        return single_instance; 
    }
    
    protected MongoCollection<Document> mcClient(){
    	try(MongoClient mc =MongoClients.create("mongodb://localhost:27017");){
    		
    		System.out.println("connection created");
    		MongoDatabase db = mc.getDatabase("library");
    		System.out.println("get database");
    		mcc = db.getCollection("Product");
    		System.out.println("got collection");

    	   /*
    	   Document doc = new Document("_Id","1");
 		   doc.append("_Name","mi");
 		   doc.append("_Quantity", "100");
 		   doc.append("_isLive", "yes");
 		   
 		   System.out.println("ok");
 		   try{
 		   mcc.insertOne(doc);
 		   System.out.println("inserted");
 		   }catch(Exception e){System.out.println(e);}
 		   
 		  FindIterable<Document> iterDoc = mcc.find();
 			int i = 1;
 			// Getting the iterator
 			Iterator<Document> it = iterDoc.iterator();
 			while (it.hasNext()) {
 				System.out.println(it.next());
 				i++;
 			}
 			
 			System.out.println("After update");
 			   
			   mcc.updateOne(new Document("_Id","1"), new Document("$set",new Document("_Name","samsung")));
			   System.out.println("ok");
			   
			   FindIterable<Document> iterDoc2 = mcc.find();
	 			int i2 = 1;
	 			// Getting the iterator
	 			Iterator<Document> it2 = iterDoc2.iterator();
	 			while (it2.hasNext()) {
	 				System.out.println(it2.next());
	 				i2++;
	 			}
	 			
    		*/
 		   }
    	System.out.println("returning mcc");
		return mcc;
    }
	
}
