package sample;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Encryption {
    private static SecretKeySpec secretKey;
    private static byte[] key;

    //Function that searches for the folder and encrypts them.
    public static void searchToEncrypt(File directory) throws FileNotFoundException {
        String[] filelist =directory.list(); // created and array so the files in folder can be easily accessible
        for(String name:filelist){  // do operation for each file in the folder
            FileReader file = new FileReader( directory +"/"+ name); // helps to read file
            FileWriter writer = null;
            BufferedReader reader = new BufferedReader(file);
            String text = "";
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                System.out.println("This doesn't work");
                e.printStackTrace();

            }
            while(line !=null)
            {
                text +=line; // basicaly if text has multiple lines then it's just stores in one variable
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    System.out.println("This doesn't work");
                    e.printStackTrace();
                }
            }
            String encrypted =encrypt(text,"abc"); // Sends to encrypt function.

            PrintWriter prw= new PrintWriter (directory+"/"+name);
            prw.println(encrypted); // replace the previous content with encrypted content
            prw.close();


        }
    }

    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("MD5"); // this is where I use my MD5
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public static String encrypt(String strToEncrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
    public static void searchtoDecrypt(File directory)throws FileNotFoundException{
        String[] filelist =directory.list(); // created and array so the files in folder can be easily accessible
        for(String name:filelist){  // do operation for each file in the folder
            FileReader file = new FileReader( directory +"/"+ name); // helps to read file
            BufferedReader reader = new BufferedReader(file);
            String text = "";
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                System.out.println("This doesn't work");
                e.printStackTrace();

            }
            while(line !=null)
            {
                text +=line; // basicaly if text has multiple lines then it's just stores in one variable
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    System.out.println("This doesn't work");
                    e.printStackTrace();
                }
            }
            String decrypted =decrypt(text,"abc"); // Sends to encrypt function.
            PrintWriter prw= new PrintWriter (directory+"/"+name);
            prw.println(decrypted); // replace the previous content with encrypted content
            prw.close();
            // this algorhytm should also work if you  decrypt the file.
        }
    }


    public static String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}
