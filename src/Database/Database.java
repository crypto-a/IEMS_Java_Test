package Database;

import Database.ChangeStreamUpdater.ChangeStreamUpdater;
import Database.DatabaseConnectionInfo.DatabaseConnectionInfo;
import GUI.Event.Event;
import TestEngine.TestEngine;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;

import org.bson.types.ObjectId;

import javax.print.Doc;
import java.util.ArrayList;

public class Database
{
    private MongoDatabase database;
    private TestEngine testEngine;
    private Event event;

    public Database(TestEngine testEngine, Event event)
    {
        //Set Up object properties
        this.testEngine = testEngine;
        this.event = event;

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

    public Document getTestObject(String objectIDString)
    {
        //Connect to the collection
        MongoCollection<org.bson.Document> issuesCollection = this.database.getCollection("tests");

        //Createw the object id
        ObjectId objectID = new ObjectId(objectIDString);

        //Find the related object
        Document testObject = (Document) issuesCollection.find(eq("_id", objectID)).first();

        return testObject;
    }

    public Document getIssueElement(String objectIDString)
    {
        //Connect to the collection
        MongoCollection<org.bson.Document> issuesCollection = this.database.getCollection("issueUnits");

        //Createw the object id
        ObjectId objectID = new ObjectId(objectIDString);

        //Find the related object
        Document issueElement = (Document) issuesCollection.find(eq("_id", objectID)).first();

        return issueElement;
    }

    public Document getTestElement(String objectIDString)
    {
        //Connect to the collection
        MongoCollection<org.bson.Document> testCollection = this.database.getCollection("testUnits");

        //Createw the object id
        ObjectId objectID = new ObjectId(objectIDString);

        //Find the related object
        Document testElement = (Document) testCollection.find(eq("_id", objectID)).first();

        return testElement;
    }

    public void startChangeStreamSync()
    {
        new ChangeStreamUpdater(this, this.database, this.testEngine, this.event).run();
    }
    public void loadTestHistoryArray(int limit)
    {
        //Connect to the collection
        MongoCollection<org.bson.Document> testObjectCollection = this.database.getCollection("tests");

        FindIterable<Document> testObjectDocs = testObjectCollection.find()
                .sort(Sorts.descending("startTestTime"))
                .limit(limit);

        //Loop thorough all the results
        for (Document testObjectDoc: testObjectDocs)
        {
            //Add it to the test engine
            this.testEngine.addTestObject(testObjectDoc);
        }
    }

    public void updateIssueElement(Document issueElement)
    {
        //Connect to the collection
        MongoCollection<org.bson.Document> issueUnitsCollection = this.database.getCollection("issueUnits");

        //Create the Update Document and add it to the database
        issueUnitsCollection.updateOne(new Document("_id", (Object) issueElement.get("_id")), new Document("$set", issueElement));
    }

    public void loadUsersList()
    {
        //Connect to the collection
        MongoCollection<org.bson.Document> accountsCollection = this.database.getCollection("accounts");

        // Create a projection document to specify the properties to include
        //Get the data for the user with the given username form the database
        Bson projectionFields = Projections.fields(
                Projections.include("firstName", "lastName"),
                Projections.include("_id"));

        MongoCursor<Document> cursor  = accountsCollection.find().projection(projectionFields).iterator();

        ArrayList<String[]> usersList = new ArrayList<>();

        //loop through all the lists of users
        while (cursor.hasNext())
        {
            //Get the next document
            Document document = cursor.next();

            //Add it to the usersList
            usersList.add(new String[]{document.getString("firstName")+ " "+ document.getString("lastName"), document.get("_id").toString()});
        }

        //Return the usersList
        this.event.setUsersList(usersList);
    }


}
