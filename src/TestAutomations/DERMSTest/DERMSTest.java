package TestAutomations.DERMSTest;

import TestAutomations.Test.Test;
import TestEngine.TestObject.TestObject;

public class DERMSTest extends Test
{
    public DERMSTest(TestObject testObject, String url, String[] userLoginInfo)
    {
        //Set the properties for the parent Object
        super(testObject, url, userLoginInfo);
    }

    @Override
    public void test()
    {
        //ToDo: Write your test here
    }

    public static void main(String[] args) {
        new DERMSTest(null, "http://ec2-54-210-75-155.compute-1.amazonaws.com:3452/", new String[] {"javad.zare@iemssolution.com", "DLC_2023"});
    }
}
