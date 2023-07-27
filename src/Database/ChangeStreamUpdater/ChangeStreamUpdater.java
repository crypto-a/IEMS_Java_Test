package Database.ChangeStreamUpdater;

import TestEngine.TestEngine;
import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.bson.BsonObjectId;
import org.bson.Document;

public class ChangeStreamUpdater implements Runnable
{

    private final MongoDatabase database;
    private final TestEngine testEngine;

    public ChangeStreamUpdater(MongoDatabase database, TestEngine testEngine)
    {
        //SetUp object properties
        this.database = database;
        this.testEngine = testEngine;
    }

    @Override
    public void run()
    {
        MongoCollection<Document> issueElementCollection = database.getCollection("issueUnits");
        MongoCollection<Document> testElementCollection = database.getCollection("testUnits");
        MongoCollection<Document> testObjectCollection = database.getCollection("tests");

        issueElementCollection.watch().forEach(changeDocument -> this.issuesUnitUpdate(changeDocument));



    }

    private void issuesUnitUpdate(ChangeStreamDocument<Document> changeDocument)
    {
        System.out.println(changeDocument.getDocumentKey().get("_id").asObjectId().getValue());

        //See what Operation happened
        switch (changeDocument.getOperationType().getValue())
        {
            case "insert":
                //ToDo

                break;

            case "update":

                break;
        }
    }
}
