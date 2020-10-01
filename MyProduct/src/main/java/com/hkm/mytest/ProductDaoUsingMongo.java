package com.hkm.myTest;

import java.util.Iterator;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;

public class ProductDaoUsingMongo {
	
	//DBConnectionMongo dbc = DBConnectionMongo.getInstance();
	MongoCollection<Document> mongoCollection =MainApp.mcc;
	
	
	protected void addProduct(Product pd){
		   Document doc = new Document("_id",pd.getId());
		   doc.append("Name",pd.getName());
		   doc.append("Quantity", pd.getQuantity());
		   doc.append("isLive", pd.getInAvailable());
		   System.out.println("ok");
		   
		   try{
		   mongoCollection.insertOne(doc);
		   System.out.println("Product added successfully.....");
		   }catch(Exception e){
			   System.out.println("Looks like you are trying to add duplicate value for id:"+pd.getId()+"......");} 
		   
	   }
	
	protected void editProduct(Product pd){
		   Document doc = new Document("_id",pd.getId());
		   doc.append("Name",pd.getName());
		   doc.append("Quantity", pd.getQuantity());
		   doc.append("isLive", pd.getInAvailable());
		   System.out.println("ok");
		   Bson filter = eq("_id", pd.getId());
		   try{
			   mongoCollection.findOneAndReplace(filter, doc);
			   System.out.println("Product detail updated for id:"+pd.getId());
		       }catch(Exception e){System.out.println("Looks like no such id exist for which you want to update");} 
	   }
	
	protected void viewProduct(String id){
		Bson filter = eq("_id", id);
		FindIterable<Document> iterDoc = mongoCollection.find(filter);
		// Getting the iterator
		Iterator<Document> it = iterDoc.iterator();
		if(it.hasNext()){
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		}
		else{
			System.out.println("No such Document Exist");
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
		
		if(it.hasNext()){
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		}
		else{
			System.out.println("No such Document Exist");
		}
		
	   }
	
	protected void deleteProduct(String id){
		Bson filter = eq("_id",id);
		mongoCollection.findOneAndDelete(filter);
		System.out.println("Product deleted");
	}
}
