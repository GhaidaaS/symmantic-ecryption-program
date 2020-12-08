

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Call_Des_Enc_Dec {
    Encrpyt_DES encrypt_des = new Encrpyt_DES();
    Decrypt_DES decrypt_des = new Decrypt_DES();
    Scanner input = new Scanner(System.in);

    public String[] get_files_name(Path path) { //get list of files
        String[] pathnames;
        File f = new File(String.valueOf(path));
        return f.list();
    }

    //ENCRYPTION  DES -64
    public void encrypt_des(int option_3) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Path file_path;
        String key;
        String files;
        if (option_3 == 1) {
            label_1:
            while (true) {
                System.out.println("Enter Path of File to Encrypt : ");
                file_path = Paths.get(input.nextLine()); //take file path
                if (Files.exists(file_path)) {
                    break;
                } else {
                    continue label_1;
                }
            }
            label_2:
            while (true) {
                System.out.println("Enter Key : (length 8 else it will not be accepted) ");
                key = input.nextLine(); // take secret key
                if (key.length() == 8) {
                    break;
                } else {
                    System.out.println("length must be 8 for DES");
                    continue label_2;
                }
            }
            Path file_name = file_path.getFileName();  // get file name
            String string_file_name = file_name.toString();//convert path to string
            String get_file_name = string_file_name.split("\\.")[0]; //  get file name by spliting path
            String new_file_name = get_file_name+".encrypted";
            Path output_path = Paths.get("encrypt/"+new_file_name );
            encrypt_des.encryptedFile(file_path, output_path, key,string_file_name); // Pass to function
        }
        else if (option_3 == 2) {
            label_1:
            while (true) {
                System.out.println("Enter Path of Folder : ");
                file_path = Paths.get(input.nextLine()); // take path folder
                if (Files.exists(file_path)) { break; }
                else { continue label_1; }
            }
            label_2:
            while (true) {
                System.out.println("Enter Key : (length 8 else it will not be accepted) ");
                key = input.nextLine(); // take Secret key
                if (key.length() == 8) {
                    break;
                } else {
                    System.out.println("length must be 8 for DES");
                    continue label_2;
                }
            }
            String[] file_list = get_files_name(file_path);
            String encrypted_files_out = "encrypt/" + "//" + "Encrypted Files Using DES";
            new File(String.valueOf(encrypted_files_out)).mkdir();
            for (int i =0; i < file_list.length ; i++){
                String get_file_name = file_list[i].split("\\.")[0]; // get file name
                String new_file_name = get_file_name+".encrypted";
                Path input_path  = Paths.get(file_path +"//"+ file_list[i]);
                Path output_path = Paths.get(encrypted_files_out+ "//" + new_file_name );
                encrypt_des.encryptedFile(input_path, output_path, key,file_list[i]); //pass to encrypted function
            }
        }
    }
    //Decryption DES -64
    public void decrypt_des(int option_3) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Path file_path;
        String key;
        String files;
        if (option_3 == 1) {
            label_1:
            while (true) {
                System.out.println("Enter Path of File to Decrypt : ");
                file_path = Paths.get(input.nextLine()); //get file path
                if (Files.exists(file_path)) {
                    break;
                } else {
                    continue label_1;
                }
            }
            label_2:
            while (true) {
                System.out.println("Enter Key : (lenght 8 else it will not be accepted)");
                key = input.nextLine(); //get secret key
                if (key.length() == 8) {
                    break;
                } else {
                    System.out.println("length must be 8 for DES");
                    continue label_2;
                }
            }
            Path file_name = file_path.getFileName(); // get file name
            String string_file_name = file_name.toString();
            String get_file_name = string_file_name.split("\\.")[0];  // Decrypted file name
            String new_file_name = get_file_name+".txt"; //Decrypted file type
            Path output_path = Paths.get("decrypt/" + new_file_name ); // Decrypted file path
            decrypt_des.decryptedFile(file_path, output_path, key,string_file_name); // pass to decrypt function
        }
        else if (option_3 == 2) {
            label_1:
            while (true) {
                System.out.println("Enter Path of Folder : ");
                file_path = Paths.get(input.nextLine()); // take path of folder
                if (Files.exists(file_path)) { break; }
                else { continue label_1; }
            }
            label_2:
            while (true) {
                System.out.println("Enter Key : (length 8 else it will not be accepted)");
                key = input.nextLine(); // get secret key
                if (key.length() == 8) {
                    break;
                } else {
                    System.out.println("length must be 8 for DES");
                    continue label_2;
                }
            }
            String[] file_list = get_files_name(file_path);
            String encrypted_files_out = "decrypt" + "//" + "Decrypted Files Using DES";
            new File(String.valueOf(encrypted_files_out)).mkdir(); // make directory
            for (int i =0; i < file_list.length ; i++){
                String get_file_name = file_list[i].split("\\.")[0];
                String new_file_name = get_file_name+".txt";
                Path input_path  = Paths.get(file_path +"//"+ file_list[i]);
                Path output_path = Paths.get(encrypted_files_out+ "//" + new_file_name );
                decrypt_des.decryptedFile(input_path, output_path, key,file_list[i]); // pass to decrypt function
            }
        }
    }

}
