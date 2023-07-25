package TestAutomations.DLCDemoTest;

import TestEngine.TestObject.TestObject;

public class DLCDemoTest implements Runnable
{
    private String webPageURL;
    private String[] loginInfo;
    private TestObject testObject;

    public DLCDemoTest(TestObject testObject, String webPageURL, String[] loginInfo)
    {
        this.webPageURL = webPageURL;
        this.loginInfo = loginInfo;
        this.testObject = testObject;
    }

    @Override
    public void run()
    {
        System.out.println("hello world");
    }
}
