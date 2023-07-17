package User;

import Database.Database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User
{
    private int UserID;
    private boolean isUserAuthenticated;

    private Database database;

    public User()
    {
        this.isUserAuthenticated = false;
    }

    public boolean UserAuthenticated(String username, String password) throws NoSuchAlgorithmException
    {
        try
        {
            //Request data from the database
            String[] userData = this.database.requestUserData(username);

            //Hash password
            String sha256Hash = this.getHash(password + userData[5], "SHA-256");

            //check if user password is correct
            if (sha256Hash.equals(userData[4]))
            {
                this.isUserAuthenticated = true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
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


}
