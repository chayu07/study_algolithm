package com.hoons.apps.study.term.mongodb;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

public class MongodbConnector {
	public final static int READ_PORT = 27017;
	private final static int MAX_CONN = 500;
	private String hostName;

	private MongoClient mongoClient;

	public MongodbConnector(String hostName) {
		this.hostName = hostName;

	}

	public void makeClient() throws IOException {
		mongoClient = new MongoClient(hostName, MongoClientOptions.builder()
				.connectionsPerHost(MAX_CONN).connectTimeout(0).maxWaitTime(240000).autoConnectRetry(true).build());

	}

	public void writeTest(int writeCount, int threadNo) throws IOException,
			InterruptedException, TimeoutException {
		DB database = mongoClient.getDB("admin");
		DBCollection coll = database.getCollection("test");

		//contents length = 235 byte
		String contents = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345";

		//moreContents lenght = 20 byte
		String moreContents = "12345678901234567890";

		for (int i = 1; i <= writeCount; i++) {
			BasicDBObject doc = new BasicDBObject("_id", Integer.toString(threadNo) + "_" + Integer.toString(i)).append("user_content", contents+moreContents).append("user_content1", contents+moreContents)
					.append("user_content2", contents+moreContents).append("user_content3", contents).append("createThreadNo", threadNo);
			coll.insert(doc);
		}
	}

	public void readTest(int threadNo) throws IOException, InterruptedException, TimeoutException{
		DB database = mongoClient.getDB("admin");
		DBCollection coll = database.getCollection("test");

		Random randomUserId = new Random(10000);

		for (int i = 0; i < 10000; i++) {
			BasicDBObject findDoc = new BasicDBObject("_id",  Integer.toString(threadNo) + "_" + Integer.toString(randomUserId.nextInt()));
			DBObject returnDoc = coll.findOne(findDoc);
			//debug
			//System.out.println(returnDoc.get("a") + ", " + returnDoc.get("b"));
		}

	}

	public void remove() throws IOException, InterruptedException, TimeoutException{
		DB database = mongoClient.getDB("admin");
		DBCollection coll = database.getCollection("test");

		BasicDBObject removeDoc = new BasicDBObject();
		coll.remove(removeDoc);
		System.out.println("test Collection Count: " + coll.count());
	}

	public void shutdownClient() throws IOException {
		mongoClient.close();
	}

	public static void main(String[] args) {
		MongodbConnector connector = new MongodbConnector("54.238.216.70");
		try {
			connector.makeClient();
			connector.remove();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
