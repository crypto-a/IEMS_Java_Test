package Email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

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
            message.setText("Hello " + firstName +", \nWelcome to the IEMS Solutions test Software. The following are your Username and password for your account: \n\nUsername: " + username + "\nPassword: " + password + "\n\nYou can change your password later after you login");

            // Send the message
            Transport.send(message);

        } catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
}
