package TestAutomations;


import Database.Database;
import GUI.Event.Event;
import TestAutomations.DLCDemo.DLCDemo;
import TestEngine.TestEngine;
import TestEngine.TestObject.TestObject;
import User.User;
import org.bson.types.ObjectId;

public class TestRun
{
    public static void main(String[] args)
    {
        //Create the event
        Event event = new Event();

        //create a test engine
        TestEngine testEngine = new TestEngine(event);

        //Database
        Database database = new Database(testEngine, event);

        //User
        User user = new User(database, event);

        event.setDatabase(database);
        testEngine.setDatabase(database);

        //testEngine.createNewIssue("DLC Demo", "bus-element", new ObjectId(), 5, "1.6", "1.8", "Wrong value");

        //runa test
        testEngine.createNewTestObject(user, "DLC Demo", "http://ec2-54-210-75-155.compute-1.amazonaws.com:3452/", "This is a test test", new String[]{"alirahbar2005@gmail.com", "Ali1234"});
        //Run the test




    }
}
