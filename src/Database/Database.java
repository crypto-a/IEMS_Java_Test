package Database;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;

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

    public String[] getFullUserData(String username)
    {
        //Create the connection to the accountants collection
        this.accountCollection = this.database.getCollection("accounts");

        //Get the data for the user with the given username form the database
        Bson projectionFields = Projections.fields(
                Projections.include( "firstName", "lastName", "email"),
                Projections.excludeId());

        Document userAuthDoc;
        userAuthDoc = this.accountCollection.find(eq("username", username))
                .projection(projectionFields)
                .first();

        String userID = userAuthDoc.getString("_id");
        String firstName = userAuthDoc.getString("firstName");
        String lastName = userAuthDoc.getString("lastName");
        String email = userAuthDoc.getString("email");

        return new String[]{userID, firstName, lastName, email};
    }

    public FindIterable<Document> requestTestHistory()
    {
        //Connect to the history collection
        this.testsCollection = this.database.getCollection("tests");

        FindIterable<Document> result = this.testsCollection.find()
                .sort(Sorts.descending("startTestTime"))
                .limit(20);

        return result;
//        //Convert the result to an ArrayList of Documents
//        List<Document> documentsList = new ArrayList<>();
//        for (Document document : result)
//        {
//            documentsList.add(document);
//        }
//
//        //Convert the ArrayList to an array of Documents
//        Document[] documentsArray = documentsList.toArray(new Document[0]);
//
//        //Return Document Array
//        return documentsArray;
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
}
