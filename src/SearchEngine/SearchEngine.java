package SearchEngine;

import TestEngine.TestObject.TestObject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchEngine
{
    private final HashMap<String, TestObject> testObjectSearchModel = new HashMap<>();

    public void index(Document objectDocument, TestObject testObject)
    {
        //Index the object
        this.testObjectSearchModel.put(objectDocument.toString(), testObject);
    }

    public ArrayList<TestObject> search(String query)
    {
        //Create the results ArrayList
        ArrayList<TestObject> results = new ArrayList<>();

        //loop through all elements
        for (String key: this.testObjectSearchModel.keySet())
        {
            //If the search query exists
            if (key.toLowerCase().contains(query.toLowerCase()))
            {
                //collect the test object
                TestObject testObject = this.testObjectSearchModel.get(key);

                System.out.println(testObject.toString());
                //Add it to the results
                results.add(testObject);
            }
        }

        //Return results
        return results;
    }

    public void clearSearchModel()
    {
        //clear the searchModel
        this.testObjectSearchModel.clear();
    }
}
