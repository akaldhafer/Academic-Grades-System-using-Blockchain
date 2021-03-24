package SystemClasses;

import Encrypt_Decrypt.AppConfig;
import Encrypt_Decrypt.AsymmetricCrypto;
import Hash.HashAccount;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMethod {

    //private static String username, Password, rand;
    public static void main(String[] args) throws Exception {  
       //AddAdmin();
       run();
       
       LoginMenu ad = new LoginMenu();
       ad.setLocationRelativeTo(null);            
       ad.setVisible(true);
    } 
    public static void AddAdmin() throws Exception{
        AsymmetricCrypto enc = new AsymmetricCrypto();
        String name = "Malik";
        String ID = "1234";
        String username = "admin";
        String Password = "admin";
        PrintWriter addM;
        try {
            try {
                HashAccount.create(username, Password);
            } catch (Exception ex) {
                Logger.getLogger(MainMethod.class.getName()).log(Level.SEVERE, null, ex);
            }
            addM = new PrintWriter(new FileWriter("Admin.txt", true));
            addM.print(enc.encrypt(name, getPublickey( AppConfig.PUBLICKEY_FILE )) + ":");
            addM.print(enc.encrypt(ID, getPublickey(AppConfig.PUBLICKEY_FILE)) + ":");
            addM.print(HashAccount.user + ":");
            addM.print(HashAccount.pass + ":");
            addM.println(HashAccount.Rand);
            addM.flush();
            addM.close();
        } catch (IOException ex) {
            Logger.getLogger(MainMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    static void run(){
        
         try (Scanner read = new Scanner (new FileReader ("Student.txt"))) {
            String line ;
            while ((line=read.nextLine())!= null)
            {
                String [] colum= line.split(":");
                String name =              colum [0];//name
                String TP =                colum [1];//TP
                String Phone_num =         colum [2];//Phone number
                String Email =             colum [3];//Email
                String Course =            colum [4];//course
                String TestMark1 =         colum [5];
                String LabMark1 =          colum [6];
                String FinalMark1 =        colum [7];
                String Username =          colum [8];
                String Password =          colum [9];
                String Rand =              colum [10];
                String Dsignature =        colum[11];
                int TestMark = Integer.parseInt(TestMark1);
                int LabMark = Integer.parseInt(LabMark1);
                int FinalMark = Integer.parseInt(FinalMark1);                             
                Students ST = new Students();
                ST.setName(name);
                ST.setTP(TP); 
                ST.setPhone_number(Phone_num);
                ST.setEmail(Email);
                ST.setCourse(Course);
                ST.setTestMark(TestMark);
                ST.setLabMark(LabMark);
                ST.setFinalMark(FinalMark);
                ST.setUsername(Username);
                ST.setPassword(Password);
                ST.setRand(Rand);
                ST.setDSignature(Dsignature);
                Students.getStudentList().add(ST);
            }

        }catch(Exception e){}
    }
    private static PublicKey getPublickey( String filename ) throws Exception{
        byte[] keyBytes = Files.readAllBytes( Paths.get(filename) );
        X509EncodedKeySpec spec = new X509EncodedKeySpec( keyBytes );
        return KeyFactory.getInstance( AppConfig.ALGORITHM ).generatePublic(spec);
    }
}
    

