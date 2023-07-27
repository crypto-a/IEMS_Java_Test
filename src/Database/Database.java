package Database;

import Database.DatabaseConnectionInfo.DatabaseConnectionInfo;
import TestEngine.IssueElement.IssueElement;
import TestEngine.TestObject.TestObject;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.eq;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import com.mongodb.client.result.InsertOneResult;

public class Database
{
    private MongoDatabase database;

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

}
