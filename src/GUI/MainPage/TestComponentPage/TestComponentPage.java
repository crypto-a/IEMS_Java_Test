package GUI.MainPage.TestComponentPage;

import GUI.Event.Event;
import TestEngine.TestElement.TestElement;
import TestEngine.TestEngine;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestComponentPage
{
    private JPanel mainPanel;
    private JTree testDetailsTree;
    private JButton returnButton;
    private JLabel testID;
    private JLabel testStatus;
    private JLabel testErrors;
    private JPanel testInfo;
    private JLabel testScnario;
    private JLabel testElementIdentification;

    private final Event event;
    private final TestEngine testEngine;
    private final TestElement testElement;

    private DefaultMutableTreeNode rootNode;

    public TestComponentPage(TestEngine testEngine, Event event)
    {
        //SetUp object properties
        this.event = event;
        this.testEngine = testEngine;

        //Collect the test element form event
        this.testElement = this.event.getSelectedTestElement();

        //set Up test properties
        this.testID.setText(this.testElement.getTestID());
        this.testElementIdentification.setText(this.testElement.getTestElementIdentification());
        this.testStatus.setText(this.testElement.getStatus());
        this.testErrors.setText(this.testElement.getErrorsNum());
        this.testScnario.setText(this.testElement.getScenario());

        //collect the test data form test element
        String[][][] testdata = this.testElement.getTestData();

        //Loop trough att th elements
        for(int i = 0; i < testdata[0].length; i++)
        {
            //Collect tht eID
            String ID = testdata[0][i][0];

            //create a new node
            DefaultMutableTreeNode node = new DefaultMutableTreeNode("Element: " + ID);

            String[] activeActualElement = new String[0];

            //Find the elements with that ID in the actual data
            for (String[] element: testdata[0])
            {
                if (element[0].equals(ID))
                {
                    //Collect the element
                    activeActualElement = element;

                    break;
                }
            }

            String[] activeExpectedElement = new String[0];
            //Find the element with that ID in the teoretical Data
            for (String[] element: testdata[1])
            {
                if (element[0].equals(ID))
                {
                    //Collect the element
                    activeExpectedElement = element;

                    break;
                }
            }
            //Find the element with that ID in the results
            String[] activeResultElement = new String[0];
            for (String[] element: testdata[2])
            {
                if (element[0].equals(ID))
                {
                    //Collect the element
                    activeResultElement = element;

                    break;
                }
            }

            //loop trough every sub element of each list
            for (int b = 1; b < testdata[0][i].length; b++)
            {
                //Make the value Node
                DefaultMutableTreeNode valueNode = new DefaultMutableTreeNode("Value " + b + ": " + activeResultElement[b]);

                //Create nodes
                DefaultMutableTreeNode actualNode = new DefaultMutableTreeNode("Actual Data: " + activeActualElement[b]);
                DefaultMutableTreeNode expectedNode = new DefaultMutableTreeNode("Expected Data: " + activeExpectedElement[b]);


                // Add records to value node
                valueNode.add(actualNode);
                valueNode.add(expectedNode);

                //add the value node to the main node
                node.add(valueNode);
            }

            //Add the node to the JTree
            this.rootNode.add(node);
        }

        //SetUp action Listiner
        this.returnButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Update the event property
                formButtonClicked(1);
            }
        });

    }

    public JPanel requestContent()
    {
        //return the main panel
        return this.mainPanel;
    }


    private void createUIComponents()
    {
        //Create the root node
        this.rootNode = new DefaultMutableTreeNode("TestData");

        //create the JTree
        this.testDetailsTree = new JTree(this.rootNode);
    }

    private void formButtonClicked(int index)
    {
//        //Update Event
//        this.event.setFormButtonPressed(index);
    }

}
