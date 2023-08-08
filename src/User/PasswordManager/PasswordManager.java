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

public class PasswordManager
{
    private String savedUsername;

    private Boolean isUserIDSaved;
    private File uuidFile;

    private String filePath = "src/User/PasswordManager/UUID.txt";
    private final String secretKeyString = "hpM8/x<$i4}E`d)rc=Qu!)B7`:rrjO=djWW@wP*YY-Yi]rGeC@?Io;OP$/k+zv;";
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
            return new BufferedReader(new FileReader(this.filePath)).readLine();

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

            bw.write(userID.toString());

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

    private String encryptLine(String line)
    {
        try
        {
            byte[] salt = new byte[]{1, 2, 3, 4, 5, 6, 7, 8}; // Salt for encryption

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(this.secretKeyString.toCharArray(), salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedData = cipher.doFinal(line.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private String decryptLine(String encryptedLine)
    {
        try {
            byte[] salt = new byte[]{1, 2, 3, 4, 5, 6, 7, 8}; // Salt for encryption (should be randomly generated for real use)

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(this.secretKeyString.toCharArray(), salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // Use the same padding as used during encryption
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] encryptedData = Base64.getDecoder().decode(encryptedLine);
            byte[] decryptedData = cipher.doFinal(encryptedData);

            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
