
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Decrypt_AES {

    public  void decryptedFile(Path fileInputPath, Path fileOutPath, String secretKey, String file_name)
    {
        try {
            SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES"); //key convert into bytes
            Cipher cipher = Cipher.getInstance("AES");//cipher instance is AES
            cipher.init(Cipher.DECRYPT_MODE, key);//open cipher decrypt mode

            File fileInput = new File(String.valueOf(fileInputPath)); //Take path of input file
            FileInputStream inputStream = new FileInputStream(fileInput);
            byte[] inputBytes = new byte[(int) fileInput.length()];
            inputStream.read(inputBytes); //input bytes

            byte[] outputBytes = cipher.doFinal(inputBytes); //output bytes

            File fileEncryptOut = new File(String.valueOf(fileOutPath));
            FileOutputStream outputStream = new FileOutputStream(fileEncryptOut);
            outputStream.write(outputBytes); // write to output file

            inputStream.close(); // close input file
            outputStream.close(); // close output file
            String get_file_name = file_name.split("\\.")[0]; // get file name
            System.out.println("Done! File " + file_name +" is Decrypted using AES-192" +" Successfully Decrypted! " +
                    "... New Name is -> "+ get_file_name + ".txt"); // output message
        }
        catch (Exception e) {
            System.out.println("File " + file_name +" is NOT decrypted using AES-192 ..it is a folder..");
        }

    }
}
