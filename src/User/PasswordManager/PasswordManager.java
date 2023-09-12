package User.PasswordManager;


import javax.crypto.SecretKeyFactory;
import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.io.InputStream;

public class PasswordManager
{
    private String savedUsername;

    private Boolean isUserIDSaved;
    private File uuidFile;

    private String filePath = "UUID.txt";
    private String secretKeyString = "770A8A65DA156D24EE2A093277530142";
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";

    public PasswordManager()
    {
        try
        {
            //Check to see if the file is written
            if (new BufferedReader(new FileReader(this.filePath)).readLine() == null)
            {
                //set the isUserIDSaved to false
                this.isUserIDSaved = false;

            } else
            {
                //set the isUserIDSaved to true
                this.isUserIDSaved = true;
            }
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);

        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    public Boolean isUserIDSaved()
    {
        //Return the is User id saved value
        return isUserIDSaved;
    }

    public String getSavedUserID()
    {
        try
        {
            //read the string form the textFile
            return this.decrypt(new BufferedReader(new FileReader(this.filePath)).readLine());

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public void saveUserID(Object userID)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));

            bw.write(this.encryptLine(userID.toString()));

            bw.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    public void clearSavedUserID()
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath)))
        {
            bw.write(""); // Write an empty string to clear the file
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String encryptLine(String input)
    {
        try
        {
            SecretKeySpec keySpec = new SecretKeySpec(this.secretKeyString.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e)
        {
            e.printStackTrace();

            return null;
        }
    }

    private String decrypt(String encryptedInput)
    {
        try
        {
            SecretKeySpec keySpec = new SecretKeySpec(this.secretKeyString.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedInput);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String generateRandomPassword(int length)
    {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        String charSet = LOWERCASE_CHARS + UPPERCASE_CHARS + DIGITS;

        for (int i = 0; i < length; i++)
        {
            int randomIndex = random.nextInt(charSet.length());
            password.append(charSet.charAt(randomIndex));
        }

        return password.toString();
    }
}
