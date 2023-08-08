package GUI.MainPage.AddUserPage;

import GUI.Event.Event;
import org.bson.Document;
import User.User;

import javax.swing.*;
import java.security.SecureRandom;
import User.PasswordManager.PasswordManager;
import org.bson.types.ObjectId;

public class AddUserPage
{
    private JPanel panel1;
    private JPanel mainPanel;
    private JButton returnButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton addUserButton;
    private JCheckBox makeUserAdminCheckBox;
    private JTextField textField4;
    private final Event event;
    private final User user;


    public AddUserPage(Event event, User user)
    {
        this.event = event;
        this.user = user;
        //Add Action Listeners
        this.addUserButton.addActionListener(e -> addUserClick());
        this.returnButton.addActionListener(e -> returnClick());
    }


    public JPanel requestContent()
    {
        //return the main Panel
        return this.mainPanel;
    }

    private void returnClick()
    {
        //change the set code property
        this.event.setCodeState(7);
    }

    private void addUserClick()
    {
        //get the user name
        String username = this.textField3.getText();

        //make the user password
        String userPass = this.user.generateUniqueString(8);
        String salt = this.user.generateUniqueString(128);

        String passwordHash = null;
        try
        {
            passwordHash = this.user.getHash(userPass + salt, "SHA-256");
        }
        catch (Exception e)
        {
            System.out.println("error hashing the password");
        }

        ObjectId userID = new ObjectId();

        //create the document for the user
        Document userDoc = new Document("_id", userID)
                .append("firstName", this.textField1.getText())
                .append("lastName", this.textField2.getText())
                .append("email", this.textField4.getText())
                .append("salt", salt)
                .append("passwordHash", passwordHash)
                .append("username", username)
                .append("isAdmin", this.makeUserAdminCheckBox.isSelected());

        //pass values to the event to take care of the rest
        this.event.addUser(userDoc, userPass);


    }


}
