package com.hkm.myTest;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Projections;

import static com.mongodb.client.model.Filters.eq;

public class ProductDaoUsingMongo {
	
	//DBConnectionMongo dbc = DBConnectionMongo.getInstance();
	MongoCollection<Document> mongoCollection =MainApp.mcc;
	
	//function to add new Product to DB
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
	
	//function to edit Product to DB
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
	
	//function to view Product by Id
	protected void viewProduct(String id){
		Bson filter = eq("_id", id);
		/*FindIterable<Document> iterDoc = mongoCollection.find(filter);
		// Getting the iterator
		Iterator<Document> it = iterDoc.iterator();
		if(it.hasNext()){
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		}
		//if no result matches the given filter then iterator will be empty so else part will be executed
		else{
			System.out.println("No such Document Exist");
		}
		*/
		
		
		try(MongoCursor<Document> cur = mongoCollection.find(filter).iterator();){
			while(cur.hasNext()){
				Document doc = cur.next();
				System.out.println("Id:"+doc.get("_id")+" Name:"+doc.get("Name")+" Quantity:"+doc.get("Quantity")+" isLive:"+doc.get("isLive"));
			}
		}
	   }
	
	//function to view complete list of products in collection
	protected void listProduct(){
		
		// this is the correct implementation
		/*
		try(MongoCursor<Document> cur = mongoCollection.find().iterator()){
			while(cur.hasNext()){
				Document doc = cur.next();
				System.out.println("Id:"+doc.get("_id")+" Name:"+doc.get("Name")+" Quantity:"+doc.get("Quantity")+" isLive:"+doc.get("isLive"));
			}
		}
		
		*/
		
		
		try(MongoCursor<Document> cur = mongoCollection.find().projection(Projections.elemMatch("Id")).iterator();){
			while (cur.hasNext()) {
				Document doc = cur.next();
		         System.out.println(doc);
		      }
		}
		
		/*FindIterable<Document> iterDoc = mongoCollection.find();
		// Getting the iterator
		Iterator<Document> it = iterDoc.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		*/
	   }
	
	//function to view product by its name
	protected void nameProduct(String name){
		Bson filter = eq("Name",name);
		try(MongoCursor<Document> cur = mongoCollection.find(filter).iterator()){
			while(cur.hasNext()){
				Document doc = cur.next();
				System.out.println("Id:"+doc.get("_id")+" Name:"+doc.get("Name")+" Quantity:"+doc.get("Quantity")+" isLive:"+doc.get("isLive"));
			}
		}
		/*
		FindIterable<Document> iterDoc = mongoCollection.find(filter);
		// Getting the iterator
		Iterator<Document> it = iterDoc.iterator();
		
		if(it.hasNext()){
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		}
		//if no result matches the given filter then iterator will be empty so else part will be executed
		else{
			System.out.println("No such Document Exist");
		}
		*/
	   }
	
	//function to delete product by its Id
	protected void deleteProduct(String id){
		Bson filter = eq("_id",id);
		mongoCollection.findOneAndDelete(filter);
		System.out.println("Product deleted");
	}
	
}
