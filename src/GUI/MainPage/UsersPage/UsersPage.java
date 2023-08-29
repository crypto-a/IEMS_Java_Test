package GUI.MainPage.UsersPage;

import javax.swing.*;

import GUI.Event.Event;
import User.User;

import java.util.ArrayList;
import java.awt.Desktop;
import java.net.URI;

public class UsersPage {
    private JPanel panel1;
    private JPanel mainPanel;
    private JPanel usersPanel;
    private JButton addUserButton;
    private JButton removeUserButton;
    private JButton emailUserButton;
    private JList list1;
    private JButton resetUserPasswordButton;
    private final User user;
    private final Event event;
    private ArrayList<String[]> userArrayList;

    public UsersPage (User user, Event event)
    {
        this.user = user;
        this.event = event;

        //Collect the users list form the EVENT
        this.userArrayList = this.event.getUsersList();

        //Convert it into an array
        String[] userNames = new String[this.userArrayList.size()];

        //Populate the userNames Array with the names
        for (int i = 0; i < this.userArrayList.size(); i++)
        {
            //Add the "I" elements name to the array
            userNames[i] = this.userArrayList.get(i)[0];
        }

        //Add it to the JList
        this.list1.setListData(userNames);

        //Add Action Listener
        this.addUserButton.addActionListener(e -> addUserClick());
        this.removeUserButton.addActionListener(e -> removeUserCLick());
        this.emailUserButton.addActionListener(e -> emailUserClick());
        this.resetUserPasswordButton.addActionListener(e -> changeUserPasswordClick());

    }

    public JPanel requestContent()
    {
        //Return the main Content
        return this.mainPanel;
    }

    private void addUserClick()
    {
        //check if User is Admin
        if (this.user.isAdmin())
        {
            //change the code state
            this.event.setCodeState(8);
        }
        else
        {
            //Show error
            JOptionPane.showMessageDialog(null, "You need to be an Admin to Add Users. Please contact your admin to use this feature", "Access Denied", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeUserCLick()
    {
        if (this.checkIfElementIsSelected())
        {
            //check if User is Admin
            if (this.user.isAdmin())
            {
                //Remove the user
                this.event.removeUser(this.userArrayList.get(this.list1.getSelectedIndex())[1], this.list1.getSelectedIndex());

                //Request ui refresh
                this.event.requestPageRefresh();
            }
            else
            {
                //Show error
                JOptionPane.showMessageDialog(null, "You need to be an Admin to Remove Users. Please contact your admin to use this feature", "Access Denied", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void emailUserClick()
    {
        if (this.checkIfElementIsSelected())
        {
            //Get the user ID
            String userID = this.userArrayList.get(this.list1.getSelectedIndex())[1];

            //get user email
            String email = this.event.getUserEmail(userID);

            //Load page
            try
            {
                // Check if Desktop is supported
                if (Desktop.isDesktopSupported())
                {
                    Desktop desktop = Desktop.getDesktop();
                    // Check if browsing is supported
                    if (desktop.isSupported(Desktop.Action.BROWSE))
                    {
                        // Open the URL in the user's default web browser
                        desktop.browse(new URI("mailto:" + email));
                    } else
                    {
                        JOptionPane.showMessageDialog(null, "This feature is not available of your computer.", "Feature Blocked", JOptionPane.ERROR_MESSAGE);
                    }
                } else
                {
                    JOptionPane.showMessageDialog(null, "This feature is not available of your computer.", "Feature Blocked", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private Boolean checkIfElementIsSelected()
    {
        //Check if a list element is selected
        if (this.list1.isSelectionEmpty())
        {
            //Show error
            JOptionPane.showMessageDialog(null, "Please select an element Before operation!", "Selection Error", JOptionPane.ERROR_MESSAGE);

            //Return false
            return false;
        }

        //Return true
        return true;
    }

    private void changeUserPasswordClick()
    {
        if (this.checkIfElementIsSelected())
        {
            //check if User is Admin
            if (this.user.isAdmin())
            {
                //Change User Password
                this.event.changeUserPassowrd(this.userArrayList.get(this.list1.getSelectedIndex())[1]);

                //notify user
                JOptionPane.showMessageDialog(null, "Users Password has been changed! They will be notified via Email!", "User Password changed", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                //Show error
                JOptionPane.showMessageDialog(null, "You need to be an Admin to Change Users Passwords. Please contact your admin to use this feature", "Access Denied", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
