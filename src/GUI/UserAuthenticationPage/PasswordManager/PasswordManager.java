//package GUI.UserAuthenticationPage.PasswordManager;
//
//import Database.Database;
//
//import javax.crypto.Cipher;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.nio.charset.StandardCharsets;
//import java.util.Arrays;
//import java.util.Base64;
//
//import static javax.crypto.Cipher.SECRET_KEY;
//
//import java.io.*;
//
//public class PasswordManager
//{
//    protected String filePath = "GUI/UserAuthenticationPage/PasswordManager/info.txt";
//
//    public void saveUserPass(String username, String password)
//    {
//        this.writeTextToBinaryFile(Arrays.toString(new String[]{ username, password}));
//    }
//
//    public String[] getUserPass()
//    {
//        String input=
//    }
//
//   private void writeTextToBinaryFile(String text)
//    {
//        try (OutputStream outputStream = new FileOutputStream(this.filePath);
//             DataOutputStream dataOutputStream = new DataOutputStream(outputStream))
//        {
//
//            // Convert text to bytes and write to the file
//            byte[] binaryData = text.getBytes();
//            dataOutputStream.write(binaryData);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private String readTextFromBinaryFile()
//    {
//        try (InputStream inputStream = new FileInputStream(this.filePath);
//             DataInputStream dataInputStream = new DataInputStream(inputStream))
//        {
//
//            // Read the binary data from the file
//            byte[] binaryData = new byte[(int) new File(this.filePath).length()];
//            dataInputStream.readFully(binaryData);
//
//            // Convert binary data back to text
//            String text = new String(binaryData);
//            return text;
//
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
