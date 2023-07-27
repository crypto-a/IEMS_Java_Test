package GUI.UserAuthenticationPage;

import GUI.Event.Event;
import User.User;
import GUI.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class UserAuthenticationPage
{
    private JPanel mainPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JLabel errorMessage;
    private JCheckBox rememberMeCheckBox;
    private final User user;
    private final Event event;
    private final GUI gui;

    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 256;


    public UserAuthenticationPage(GUI gui, Event event, User user)
    {
        //SetUp object properties
        this.gui = gui;
        this.event = event;
        this.user = user;

        //Set Error to Not show
        this.errorMessage.setText("");

        //Add the action Listener
        this.loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Update the event property
                submitPage();
            }
        });
    }

    public JPanel requestPage()
    {
        //return the main panel
        return this.mainPanel;
    }

    private void submitPage()
    {
        if (!this.user.userAuthenticate(this.textField1.getText(), this.passwordField1.getText()))
        {
            this.errorMessage.setText("- Username or Password is incorrect!");
            this.textField1.setText("");
            this.passwordField1.setText("");
        } else if (this.rememberMeCheckBox.isSelected())
        {
            //If the remember me is selected save data to the file

        }
    }
}
