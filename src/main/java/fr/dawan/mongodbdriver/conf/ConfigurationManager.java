package fr.dawan.mongodbdriver.conf;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

public class ConfigurationManager {
    // "mongodb://<username>:<password>@<hostname>:<port>/?authSource=<authenticationDb>"
    private static final ConnectionString CONNECTION_STRING = new ConnectionString("mongodb://localhost:27017");

    private static MongoClient client;
    private static final String DB_NAME = "db";

    private ConfigurationManager() {

    }

    public static MongoClient getInstance() {
        if(client == null) {
            client = MongoClients.create(CONNECTION_STRING);
        }
        return client;
    }

    // à ne pas exposer
    public static MongoIterable<String> getDatabaseList() {
        return getInstance().listDatabaseNames();
    }

    public static MongoCollection<Document> getCollection(String collectionName) {
        return getInstance().getDatabase(DB_NAME).getCollection(collectionName);
    }
}
