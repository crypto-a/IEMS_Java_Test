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

        //runa test
        testEngine.createNewTestObject(user, "DERMS", "https://derms.iemssolution.com/", "This is a test test", new String[]{"alirahbar2005@gmail.com", "Spacex12345678900!"});
        //Run the test


    }
}
