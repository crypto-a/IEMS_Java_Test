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

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChangeStreamUpdater
{

    private final MongoDatabase database;
    private final Database databaseConnection;
    private final TestEngine testEngine;
    private final Event event;
    private String ChangedObjectID;

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
        MongoCollection<org.bson.Document> accountsCollection = this.database.getCollection("accounts");

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Execute each change stream in a separate thread
        executor.submit(() -> issueUnitCollection.watch().forEach(this::issueUnitUpdate));
        executor.submit(() -> testUnitCollection.watch().forEach(this::testUnitUpdate));
        executor.submit(() -> testObjectCollection.watch().forEach(this::testObjectUpdate));
        executor.submit(() -> accountsCollection.watch().forEach(this::accountsUpdate));


    }

    private void issueUnitUpdate(ChangeStreamDocument<Document> changeDocument)
    {
        //Record the changed object ID
        this.ChangedObjectID = String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue());

        //See what Operation happened
        switch (changeDocument.getOperationType().getValue())
        {
            case "insert" ->
                //Find the object and sdd it to the test engine arraylist
                    this.testEngine.addIssueElement(this.databaseConnection.getIssueElement(this.ChangedObjectID));
            case "update" ->
            {

                //find the object in the arraylist
                IssueElement issueElement = this.testEngine.getIssueElement(this.ChangedObjectID);

                //update properties
                issueElement.updateObject(this.databaseConnection.getIssueElement(this.ChangedObjectID));
            }
        }

        //refresh page to display the change
        this.requestRefresh();

    }

    private void testUnitUpdate(ChangeStreamDocument<Document> changeDocument)
    {
        //Record the changed object ID
        this.ChangedObjectID = String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue());

        //See what Operation happened
        switch (changeDocument.getOperationType().getValue())
        {
            case "insert" ->
            {
                //Find the object and sdd it to the test engine arraylist
                this.testEngine.addTestElement(this.databaseConnection.getTestElement(this.ChangedObjectID));

            }

            case "update" ->
            {

                //find the object in the arraylist
                TestElement testElement = this.testEngine.getTestElement(this.ChangedObjectID);


                Document newTestElementDoc = this.databaseConnection.getTestElement(this.ChangedObjectID);


                //update properties
                testElement.updateObject(newTestElementDoc);


                System.out.println(1);

            }
        }

        //refresh page to display the change
        this.requestRefresh();



    }

    private void testObjectUpdate(ChangeStreamDocument<Document> changeDocument)
    {
        //Record the changed object ID
        this.ChangedObjectID = String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue());

        //See what Operation happened
        switch (changeDocument.getOperationType().getValue())
        {
            case "insert" ->
            {
                //Find the object and sdd it to the test engine arraylist
                this.testEngine.addTestObject(this.databaseConnection.getTestObject(this.ChangedObjectID));

            }

            case "update" ->
            {

                //find the object in the arraylist
                TestObject testObject = this.testEngine.getTestObject(this.ChangedObjectID);

                //update properties
                testObject.updateObject(this.databaseConnection.getTestObject(this.ChangedObjectID));

            }
        }

        //refresh page to display the change
        this.requestRefresh();
    }

    private void accountsUpdate(ChangeStreamDocument<Document> changeDocument)
    {
        //Record the changed object ID
        this.ChangedObjectID = String.valueOf(changeDocument.getDocumentKey().get("_id").asObjectId().getValue());

        if (changeDocument.getOperationType().getValue().equals("insert"))
        {
            //get the user DOc
            Document userDoc = this.databaseConnection.getUserInfo(this.ChangedObjectID);

            //Add this to the usersList in the event
            this.event.addUsersArrayListElement(userDoc.getString("firstName") + " " + userDoc.getString("lastName"), this.ChangedObjectID);
        }
    }

    private void requestRefresh()
    {

        if(this.event.getDidSelfPushChange())
        {
            //Set it ot zero
            this.event.setDidSelfPushChange(false);
        }
        else
        {
            if (this.event.getCodeState() == 1)
            {
                this.event.requestPageRefresh();
            } else if (this.event.getCodeState() == 5 && this.event.getSelectedIssueElement().getIssueID().equals(this.ChangedObjectID))
            {
                //Notify User
                JOptionPane.showMessageDialog(null, "A New Version of this object is available! We are updating your data!", "New Data Available", JOptionPane.INFORMATION_MESSAGE);

                //Request to refresh page
                this.event.requestPageRefresh();
            } else if (this.event.getCodeState() == 3 && this.event.getSelectedTestObject().containsElement(this.ChangedObjectID))
            {
                //Notify User
                JOptionPane.showMessageDialog(null, "A New Version of this object is available! We are updating your data!", "New Data Available", JOptionPane.INFORMATION_MESSAGE);

                //Request to refresh page
                this.event.requestPageRefresh();
            } else if (this.event.getCodeState() == 4 && this.event.getSelectedTestElement().getTestID().equals(this.ChangedObjectID))
            {
                //Notify User
                JOptionPane.showMessageDialog(null, "A New Version of this object is available! We are updating your data!", "New Data Available", JOptionPane.INFORMATION_MESSAGE);

                //Request to refresh page
                this.event.requestPageRefresh();
            }else if (this.event.getCodeState() == 7)
            {
                //Notify User
                JOptionPane.showMessageDialog(null, "There has been a change in the users list! We are updating your data!", "New Data Available", JOptionPane.INFORMATION_MESSAGE);

                //Request to refresh page
                this.event.requestPageRefresh();

            }
        }
    }
}
