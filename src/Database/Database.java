package Database;

import TestEngine.IssueElement.IssueElement;
import TestEngine.TestObject.TestObject;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.MongoException;
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

import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.result.InsertOneResult;


public class Database
{
    private MongoDatabase database;
    private MongoCollection<org.bson.Document> accountCollection;
    private MongoCollection<org.bson.Document> testsCollection;


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

        //this.addUser();

    }

    // Method to add a new user to the "account" collection
    public void addUser()
    {
        MongoCollection<org.bson.Document> accountCollection = this.database.getCollection("accounts");

        InsertOneResult result = accountCollection.insertOne(new Document()
                .append("_id", new ObjectId())
                .append("firstName", "Ali")
                .append("lastName", "Rahbar")
                .append("email", "alirahabr2005@gmail.com")
                .append("passwordHash", "67c814faaf5fa15535f1ee0922ad01f023cdca4809f76ec74574addd0bc4ea1e")
                .append("salt", "chipsVaMastMosir")
                .append("registrationDate", LocalDateTime.now().toString())
                .append("lastLogin", LocalDateTime.now().toString()));


        System.out.println("Success! Inserted document id: " + result.getInsertedId());
    }

    public  String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public String[] requestUserDataAuthentication(String username)
    {
        //Create the connection to the accountants collection
        accountCollection = this.database.getCollection("accounts");

        //Get the data for the user with the given username form the database
        Bson projectionFields = Projections.fields(
                Projections.include("username", "passwordHash", "salt"),
                Projections.excludeId());

        Document userAuthDoc;
        userAuthDoc = accountCollection.find(eq("username", username))
                .projection(projectionFields)
                .first();

        if (userAuthDoc != null)
        {
            String passwordHash = userAuthDoc.getString("passwordHash");
            String salt = userAuthDoc.getString("salt");

            return new String[]{username, passwordHash, salt};
        }
        else
        {
            return null;
        }

    }

    public Object[] getFullUserData(String username)
    {
        //Create the connection to the accountants collection
        this.accountCollection = this.database.getCollection("accounts");

        //Get the data for the user with the given username form the database
        Bson projectionFields = Projections.fields(
                Projections.include( "_id", "firstName", "lastName", "email"),
                Projections.include("_id"));

        Document userAuthDoc;
        userAuthDoc = this.accountCollection.find(eq("username", username))
                .projection(projectionFields)
                .first();

        Object userID = userAuthDoc.get("_id");
        String firstName = userAuthDoc.getString("firstName");
        String lastName = userAuthDoc.getString("lastName");
        String email = userAuthDoc.getString("email");

        return new Object[]{userID, firstName, lastName, email};
    }

    public FindIterable<Document> requestTestHistory()
    {
        //Connect to the history collection
        this.testsCollection = this.database.getCollection("tests");

        FindIterable<Document> result = this.testsCollection.find()
                .sort(Sorts.descending("startTestTime"))
                .limit(20);

        return result;
    }

    public Document getTestElement(Object testID)
    {
        //Connect to the history collection
        this.testsCollection = this.database.getCollection("testUnits");

        for (Document testElement: this.testsCollection.find(eq("_id", testID)))
        {
            return testElement;
        }
        return null;
    }

    public Document getIssueElement(Object issueID)
    {
        //Connect to the history collection
        this.testsCollection = this.database.getCollection("issueUnits");

        for (Document testElement: this.testsCollection.find(eq("_id", issueID)))
        {
            return testElement;
        }
        return null;
    }

    public String getUserFullName(Object userID)
    {
        //Create the connection to the accountants collection
        accountCollection = this.database.getCollection("accounts");

        //Get the data for the user with the given username form the database
        Bson projectionFields = Projections.fields(
                Projections.include("firstName", "lastName"),
                Projections.excludeId());

        Document userNameDoc;
        userNameDoc = accountCollection.find(eq("_id", userID))
                .projection(projectionFields)
                .first();


        return userNameDoc.getString("firstName") + " " + userNameDoc.getString("lastName");
    }

    public FindIterable<Document> requestOpenIssues()
    {
        //Connect to the issueUnits collection
        MongoCollection<org.bson.Document> issuesCollection = this.database.getCollection("issueUnits");

        //Pull out all that can a status of open!
        FindIterable<Document> result = this.testsCollection.find(eq("issueStatus", "Open"));

        return result;
    }

    public void pushNewTestObject(TestObject testObject)
    {
        MongoCollection<org.bson.Document> objectsCollection = this.database.getCollection("objects");



    }

    public void updateIssueStatus(IssueElement issueElement)
    {
        //Connect to the issueDatabase
        MongoCollection<org.bson.Document> issuesCollection = this.database.getCollection("issueUnits");

        Document query = new Document().append("_id",  issueElement.getIssueID());

        Bson updates = Updates.combine(
                Updates.set("issueStatus", "Closed"),
                Updates.set("closedTime", issueElement.getClosedDateTime()),
                Updates.set("issueCloser", issueElement.getIssueCloser()),
                Updates.set("developerMessage", issueElement.getDeveloperMessage())
        );

        UpdateOptions options = new UpdateOptions().upsert(true);

        //Update values
        issuesCollection.updateOne(query, updates, options);
    }
}
