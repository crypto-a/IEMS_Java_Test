package Email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;

import TestEngine.TestObject.TestObject;
import User.User;

public class Email
{
    private Session session;
    private String username = "alirahbartest@gmail.com";
    private String password = "wmjpmrxckjpbyoou";

    public Email()
    {
        // Set the email configuration properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP server
        properties.put("mail.smtp.port", "25"); // Replace with your SMTP port
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");



        // Create a session with the properties and authenticator
         this.session = Session.getInstance(properties, new Authenticator()
         {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void newUserEmail(String firstName, String email, String username, String password)
    {
        try
        {
            // Create a MimeMessage
            Message message = new MimeMessage(this.session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(this.username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            // Set the subject and content
            message.setSubject("Account Created");
            message.setText("Hello " + firstName +", \nWelcome to the IEMS Solutions test Software. The following are your Username and password for your account: \n\nUsername: " + username + "\nPassword: " + password + "\n");

            // Send the message
            Transport.send(message);

        } catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

    public void emailTestResults(User user, TestObject testObject)
    {
        try
        {
            // Create a MimeMessage
            Message message = new MimeMessage(this.session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(this.username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getUserEmail()));

            System.out.println(user.getUserEmail());

            // Set the subject and content
            message.setSubject("Test: " + testObject.getTestID() + " Results");
            message.setText(
                            "Test ID: " + testObject.getTestID() +
                            "\nTargeted Web App: " + testObject.getTargetedWebPage() +
                            "\nURL: " + testObject.getWebPageURL() +
                            "\nTest Date: " + testObject.getTestDate() +
                            "\nTestTime: " + testObject.getTestStartTime() +
                            "\nDuration: " + testObject.getDuration());

            // Send the message
            Transport.send(message);

            //Notify User
            JOptionPane.showMessageDialog(null, "The results of this test have been emailed to you", "Email Sent", JOptionPane.INFORMATION_MESSAGE);

        } catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

    public void emailPasswordChange(String firstName, String email, String username, String password)
    {
        try
        {
            // Create a MimeMessage
            Message message = new MimeMessage(this.session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(this.username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            // Set the subject and content
            message.setSubject("Password Changed");
            message.setText("Hello " + firstName +", \nThe system admin has reset your password. The following are your Username and new password for your account: \n\nUsername: " + username + "\nPassword: " + password + "\n");

            // Send the message
            Transport.send(message);

        } catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }


}
