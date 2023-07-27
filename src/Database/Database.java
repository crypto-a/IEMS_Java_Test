package Database;

import Database.ChangeStreamUpdater.ChangeStreamUpdater;
import Database.DatabaseConnectionInfo.DatabaseConnectionInfo;
import TestEngine.TestEngine;
import com.mongodb.MongoException;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;

import org.bson.types.ObjectId;

import javax.print.Doc;

public class Database
{
    private MongoDatabase database;
    private TestEngine testEngine;

    public Database()
    {

        //Create the password class
        DatabaseConnectionInfo databaseConnectionInfo = new DatabaseConnectionInfo();

        //Try connecting to the database
        try
        {
            // Connect to the cluster
            MongoClient mongoClient = MongoClients.create(databaseConnectionInfo.connectionURL);

            // Connect to the Database
            this.database = mongoClient.getDatabase("IEMS-Test-Software");
        }
        catch (MongoException e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println("Connected Successfully");
    }

    public Document getUserInfo(String userID)
    {
        //Create the connection to the accountants collection
        MongoCollection<org.bson.Document> accountCollection = this.database.getCollection("accounts");


        ObjectId id = new ObjectId(userID);

        Document userInfo = (Document) accountCollection.find(eq("_id", id)).first();

        //return the document
        return userInfo;
    }

    public Document requestUserDataAuthentication(String username)
    {
        //Create the connection to the accountants collection
        MongoCollection<org.bson.Document> accountCollection = this.database.getCollection("accounts");

        //Get the data for the user with the given username form the database
        Bson projectionFields = Projections.fields(
                Projections.include("username", "passwordHash", "salt"),
                Projections.include("_id"));

        Document userAuthDoc;
        userAuthDoc = accountCollection.find(eq("username", username))
                .projection(projectionFields)
                .first();

        if (userAuthDoc != null)
        {
            return userAuthDoc;
        }
        else
        {
            return null;
        }
    }

//    public Iterable<Document> getTestObjects()
//    {
//
//    }
//    public Iterable<Document> getOpenIssues()
//    {
//
//    }
//
//    public Document getIssueElement()
//    {
//
//    }
//
//    public Document getTestElement()
//    {
//
//    }

    public void startChangeStreamSync()
    {
        ChangeStreamUpdater changeStreamUpdater = new ChangeStreamUpdater(this.database, this.testEngine);

        Thread thread = new Thread(changeStreamUpdater);

        thread.start();
    }



}
