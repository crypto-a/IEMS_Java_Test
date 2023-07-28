package Database.ChangeStreamUpdater;

import TestEngine.IssueElement.IssueElement;
import TestEngine.TestElement.TestElement;
import TestEngine.TestEngine;
import TestEngine.TestObject.TestObject;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.bson.Document;
import Database.Database;

public class ChangeStreamUpdater implements Runnable
{

    private final MongoDatabase database;
    private final Database databaseConnection;
    private final TestEngine testEngine;

    public ChangeStreamUpdater(Database databaseConnection, MongoDatabase database, TestEngine testEngine)
    {
        //SetUp object properties
        this.database = database;
        this.testEngine = testEngine;
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void run()
    {
        //SetUp Change Streams
        this.database.getCollection("issueUnits").watch().forEach(this::issueUnitUpdate);
        this.database.getCollection("testUnits").watch().forEach(this::testUnitUpdate);
        this.database.getCollection("tests").watch().forEach(this::testObjectUpdate);
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
                break;
        }
    }

    private void testUnitUpdate(ChangeStreamDocument<Document> changeDocument)
    {
        //See what Operation happened
        switch (changeDocument.getOperationType().getValue())
        {
            case "insert":
                //Find the object and sdd it to the test engine arraylist
                this.testEngine.addTestElement(this.databaseConnection.getTestElement(String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue())));
                break;

            case "update":

                //find the object in the arraylist
                TestElement testElement = this.testEngine.getTestElement(String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue()));

                //update properties
                testElement.updateObject(this.databaseConnection.getTestElement(String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue())));
                break;
        }
    }

    private void testObjectUpdate(ChangeStreamDocument<Document> changeDocument)
    {
        //See what Operation happened
        switch (changeDocument.getOperationType().getValue())
        {
            case "insert":
                //Find the object and sdd it to the test engine arraylist
                this.testEngine.addTestObject(this.databaseConnection.getTestObject(String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue())));
                break;

            case "update":

                //find the object in the arraylist
                TestObject testObject = this.testEngine.getTestObject(String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue()));

                //update properties
                testObject.updateObject(this.databaseConnection.getTestObject(String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue())));
                break;
        }
    }
}
