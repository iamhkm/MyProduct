package com.hkm.myTest;

import java.util.Iterator;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;

public class ProductDaoUsingMongo {
	
	DBConnectionMongo dbc = DBConnectionMongo.getInstance();
	MongoCollection<Document> mongoCollection =dbc.mcClient();
	
	
	protected void addProduct(Product pd){
		
		   System.out.println("ok i'm in addProduct method");
		   Document doc = new Document("_Id",pd.getId());
		   doc.append("_Name",pd.getName());
		   doc.append("_Quantity", pd.getQuantity());
		   doc.append("_isLive", pd.getInAvailable());
		   System.out.println("ok");
		   
		   try{
		   mongoCollection.insertOne(doc);
		   }catch(Exception e){
			   System.out.println(e);} 
		   
	   }
	
	protected void editProduct(Product pd){
		   System.out.println("ok i'm in editProduct method");
		   Document doc = new Document("_Id",pd.getId());
		   doc.append("_Name",pd.getName());
		   doc.append("_Quantity", pd.getQuantity());
		   doc.append("_isLive", pd.getInAvailable());
		   System.out.println("ok");
		   
		   try{
			   mongoCollection.updateOne(new Document("_Id",pd.getId()), doc);
		       }catch(Exception e){System.out.println(e);} 
	   }
	
	protected void viewProduct(String id){
		Bson filter = eq("_Id", id);
		FindIterable<Document> iterDoc = mongoCollection.find(filter);
		// Getting the iterator
		Iterator<Document> it = iterDoc.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
	   }
	
	protected void listProduct(){
		FindIterable<Document> iterDoc = mongoCollection.find();
		// Getting the iterator
		Iterator<Document> it = iterDoc.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	   }
	
	protected void nameProduct(String name){
		Bson filter = eq("Name",name);
		FindIterable<Document> iterDoc = mongoCollection.find(filter);
		// Getting the iterator
		Iterator<Document> it = iterDoc.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	   }
}
