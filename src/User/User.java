package User;

import Database.Database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class User
{
    private boolean isUserAuthenticated;
    private String userID;
    private String firstName;
    private String lastName;
    private String username;
    private String passwordHash;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDateTime registrationDate;
    private LocalDateTime lastLoginDate;

    private final Database database;

    public User(Database database)
    {
        this.database = database;

        this.isUserAuthenticated = false;
    }

    public boolean userAuthenticate(String username, String password)
    {
        try
        {
            //Request data from the database
            String[] userData = this.database.requestUserDataAuthentication(username);//

            if (userData != null)
            {
                //Hash password
                String sha256Hash = this.getHash(password + userData[2], "SHA-256");

                //check if user password is correct
                if (sha256Hash.equals(userData[1]))
                {
                    this.isUserAuthenticated = true;

                    //SetUp last login
                    this.lastLoginDate = LocalDateTime.now();

                    System.out.println("userAuthenticated");

                    String[] userPrivateData = this.database.getFullUserData(userData[0]);

                    //Set properties of object to the values
                    this.username = userData[0];
                    this.userID = userPrivateData[0];
                    this.firstName = userPrivateData[1];
                    this.lastName = userPrivateData[2];
                    this.email = userPrivateData[3];
                    this.passwordHash = sha256Hash;

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
        return isUserAuthenticated;
    }
}
