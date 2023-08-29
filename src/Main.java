import Runner.Runner;

public class Main
{
    public static String SoftwareVersion = "1.1.12";
    public static Boolean DebugMode = true;

    public static Runner codeSession;

    public static void main(String[] args)
    {
        //Erase code session
        codeSession = null;

        //Start the program by creating the new Runner object!
        codeSession = new Runner(DebugMode, SoftwareVersion);
    }
}
