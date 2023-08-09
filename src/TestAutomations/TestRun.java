package TestAutomations;


import TestAutomations.DLCDemo.DLCDemo;
import TestEngine.TestObject.TestObject;
import org.bson.types.ObjectId;

public class TestRun
{
    public static void main(String[] args)
    {
        //Run the test
        TestObject testObject = new TestObject(new ObjectId(), "DLCDemo", "http://ec2-54-210-75-155.compute-1.amazonaws.com:3452/", "This is a test test", new String[]{"javad.zare@iemssolution.com", "DLC_2023"});

    }
}
