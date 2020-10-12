package com.hkm.testprojection;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Filters.eq;

public class TestProjectionQueries {
	static MongoCollection<Document> mcc = null;

	public static void main(String args[]) {
		try {
			MongoClient mc = MongoClients.create("mongodb://localhost:27017");
			MongoDatabase db = mc.getDatabase("CommentDB");
			mcc = db.getCollection("comment");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Bson filter = eq("username", "xyz");

		System.out.println("exclude projection\n");
		try (MongoCursor<Document> cur = mcc.find().projection(exclude("name")).iterator();) {
			while (cur.hasNext()) {
				Document doc = cur.next();
				System.out.println(doc);
			}
		}
		
		System.out.println("include projection\n");
		try (MongoCursor<Document> cur = mcc.find().projection(include("name")).iterator();) {
			while (cur.hasNext()) {
				Document doc = cur.next();
				System.out.println(doc);
			}
		}
		
		System.out.println("fields and eleMatch Projection\n");
		try (MongoCursor<Document> cur = mcc.find().projection(fields(elemMatch("comments", filter), excludeId()))
				.iterator();) {
			while (cur.hasNext()) {
				Document doc = cur.next();
				System.out.println(doc);
			}
		}
		
		System.out.println("slice projection\n");
		try (MongoCursor<Document> cur = mcc.find().projection(slice("comments", 2)).iterator();) {
			while (cur.hasNext()) {
				Document doc = cur.next();
				System.out.println(doc);
			}
		}

		System.out.println("computed projection\n");
		try (MongoCursor<Document> cur = mcc.find().projection(computed("_id", 3)).iterator();) {
			while (cur.hasNext()) {
				Document doc = cur.next();
				System.out.println(doc);
			}
		}
		
		System.out.println("metaTextScore projection\n");
		try (MongoCursor<Document> cur = mcc.find().projection(metaTextScore("name")).iterator()) {
			while (cur.hasNext()) {
				Document doc = cur.next();
				System.out.println(doc);
			}
		}
	}
}
