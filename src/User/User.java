package User;

import Database.Database;
import GUI.Event.Event;
import User.PasswordManager.PasswordManager;
import org.bson.Document;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class User
{
    private boolean isUserAuthenticated;
    private Object userID;
    private String firstName;
    private String lastName;
    private String username;
    private String passwordHash;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDateTime registrationDate;
    private LocalDateTime lastLoginDate;
    private Boolean isAdmin;

    private final Database database;
    private final Event event;
    private final PasswordManager passwordManager;

    public User(Database database, Event event)
    {
        this.database = database;
        this.event = event;

        //Create the password manager
        this.passwordManager = new PasswordManager();

        try
        {
            //Check if the User info is saved
            if (this.passwordManager.isUserIDSaved())
            {
                //Call it form the database and save it
                this.loadUserInfo(this.database.getUserInfo(this.passwordManager.getSavedUserID()));

                //Change state
                event.setCodeState(1);

                //Set is user authenticated to true
                this.isUserAuthenticated = true;
            }
            else
            {
                //Set is user authenticated to false
                this.isUserAuthenticated = false;
            }
        } catch(Exception e)
        {
            //Set is user authenticated to false
            this.isUserAuthenticated = false;

            //Clear the test file as the password saved there is wrong
            this.passwordManager.clearSavedUserID();
        }

        //load the users lIst
        this.loadUsersList();
    }

    private void loadUserInfo(Document userAuthenticationDocument)
    {
        //Load different properties the the user object
        this.firstName = userAuthenticationDocument.getString("firstName");
        this.lastName = userAuthenticationDocument.getString("lastName");
        this.userID = userAuthenticationDocument.get("_id");
        this.email = userAuthenticationDocument.getString("email");
        this.lastName = userAuthenticationDocument.getString("lastName");
        this.isAdmin = userAuthenticationDocument.getBoolean("isAdmin");

        System.out.println("Hi " + this.firstName);
    }

    public boolean userAuthenticate(String username, String password, Boolean isSavePasswordClicked)
    {
        try
        {
            //Request data from the database
            Document userAuthDoc = this.database.requestUserDataAuthentication(username);//

            if (userAuthDoc != null)
            {
                //Hash password
                String sha256Hash = this.getHash(password + userAuthDoc.getString("salt"), "SHA-256");

                //check if user password is correct
                if (sha256Hash.equals(userAuthDoc.getString("passwordHash")))
                {
                    this.isUserAuthenticated = true;

                    //SetUp last login
                    this.lastLoginDate = LocalDateTime.now();

                    System.out.println("userAuthenticated");

                    this.loadUserInfo(this.database.getUserInfo(userAuthDoc.get("_id").toString()));

                    if (isSavePasswordClicked)
                    {
                        System.out.println(1);
                        this.passwordManager.saveUserID(userAuthDoc.get("_id"));
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }

        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println(e.getMessage());
        }

        return false;

    }

    public String getHash(String data, String algorithm) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] hashBytes = md.digest(data.getBytes());

        // Convert the byte array to a hexadecimal representation
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public String getName()
    {
        //Return full name
        return this.firstName+ " " + this.lastName;
    }

    public Boolean isUserAuthenticated()
    {
        //Return isUserAuthenticated
        return isUserAuthenticated;
    }

    public Object getUserID()
    {
        //Return user id
        return userID;
    }

    public void clearUserInfo()
    {
        //Delete all the recorded data
        this.isUserAuthenticated = false;

        //clear the password saver
        this.passwordManager.clearSavedUserID();
    }

    private void loadUsersList()
    {
        this.database.loadUsersList();
    }

    public Boolean isAdmin()
    {
        //Return isAdmin
        return this.isAdmin;
    }

    public String generateUniqueString(int length)
    {
        //Return the results
        return this.passwordManager.generateRandomPassword(length);
    }
}
