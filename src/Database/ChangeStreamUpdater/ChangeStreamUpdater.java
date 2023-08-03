package Database.ChangeStreamUpdater;

import GUI.Event.Event;
import TestEngine.IssueElement.IssueElement;
import TestEngine.TestElement.TestElement;
import TestEngine.TestEngine;
import TestEngine.TestObject.TestObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.bson.Document;
import Database.Database;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChangeStreamUpdater
{

    private final MongoDatabase database;
    private final Database databaseConnection;
    private final TestEngine testEngine;
    private final Event event;

    public ChangeStreamUpdater(Database databaseConnection, MongoDatabase database, TestEngine testEngine, Event event)
    {

        //SetUp object properties
        this.database = database;
        this.testEngine = testEngine;
        this.databaseConnection = databaseConnection;
        this.event = event;
    }


    public void run()
    {

        //Collect different collections
        MongoCollection<org.bson.Document> issueUnitCollection = this.database.getCollection("issueUnits");
        MongoCollection<org.bson.Document> testUnitCollection = this.database.getCollection("testUnits");
        MongoCollection<org.bson.Document> testObjectCollection = this.database.getCollection("tests");

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Execute each change stream in a separate thread
        executor.submit(() -> issueUnitCollection.watch().forEach(this::issueUnitUpdate));
        executor.submit(() -> testUnitCollection.watch().forEach(this::testUnitUpdate));
        executor.submit(() -> testObjectCollection.watch().forEach(this::testObjectUpdate));

        // Shutdown the executor once all tasks are complete
        //executor.shutdown();

    }

    private void issueUnitUpdate(ChangeStreamDocument<Document> changeDocument)
    {
        //See what Operation happened
        switch (changeDocument.getOperationType().getValue())
        {
            case "insert":
                //Find the object and sdd it to the test engine arraylist
                this.testEngine.addIssueElement(this.databaseConnection.getIssueElement(String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue())));
                break;

            case "update":

                //find the object in the arraylist
                IssueElement issueElement = this.testEngine.getIssueElement(String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue()));

                //update properties
                issueElement.updateObject(this.databaseConnection.getIssueElement(String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue())));
                System.out.println("2");
                break;
        }

    }

    private void testUnitUpdate(ChangeStreamDocument<Document> changeDocument)
    {
        System.out.println(changeDocument.getOperationType().getValue());
        //See what Operation happened
        switch (changeDocument.getOperationType().getValue())
        {
            case "insert" ->
            {
                //Find the object and sdd it to the test engine arraylist
                this.testEngine.addTestElement(this.databaseConnection.getTestElement(String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue())));

            }

            case "update" ->
            {

                System.out.println("1");

                //find the object in the arraylist
                TestElement testElement = this.testEngine.getTestElement(String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue()));

                System.out.println("1");

                //update properties
                testElement.updateObject(this.databaseConnection.getTestElement(String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue())));

                System.out.println("1");
            }
        }



    }

    private void testObjectUpdate(ChangeStreamDocument<Document> changeDocument)
    {
        //See what Operation happened
        switch (changeDocument.getOperationType().getValue())
        {
            case "insert" ->
            {
                //Find the object and sdd it to the test engine arraylist
                this.testEngine.addTestObject(this.databaseConnection.getTestObject(String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue())));

            }

            case "update" ->
            {

                //find the object in the arraylist
                TestObject testObject = this.testEngine.getTestObject(String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue()));

                //update properties
                testObject.updateObject(this.databaseConnection.getTestObject(String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue())));

                System.out.println("3");
            }


        }
    }

    private synchronized void requestChangeInIsDataUpdated(int elementIndex, String ObjectIDString)
    {
        //Set the change
        this.event.setDatabaseDateUpdated(elementIndex, ObjectIDString);
    }
}
