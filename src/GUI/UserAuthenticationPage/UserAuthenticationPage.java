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
import javax.swing.*;

public class UserAuthenticationPage
{
    private JPanel mainPanel;
    private JTextField textField1;
    private JCheckBox rememberMeCheckBox;
    private JButton loginButton;
    private JLabel errorMessage;
    private JPasswordField passwordField1;
    private final Event event;
    private final User user;

    public UserAuthenticationPage(Event event, User user)
    {
        //Set up the object properties
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

    public JPanel getMainPanel()
    {
        //return the main panel
        return mainPanel;
    }

    private void submitPage()
    {
        if (!this.user.userAuthenticate(this.textField1.getText(), this.passwordField1.getText(), this.rememberMeCheckBox.isSelected()))
        {
            this.errorMessage.setText("- Username or Password is incorrect!");
            this.textField1.setText("");
            this.passwordField1.setText("");
        }
    }
}
