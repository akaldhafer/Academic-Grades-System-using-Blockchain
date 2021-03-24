package Encrypt_Decrypt;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.*;

public class Keymaker {
    KeyPairGenerator keygen;
    KeyPair keypair;   
    //constructor
    public Keymaker() throws Exception {
        keygen = KeyPairGenerator.getInstance( AppConfig.ALGORITHM );
        //keysize=1024
        keygen.initialize(1024);
    }
    //make-keypair
    public static void mkKeyPair(){
        try {
            
            //instantiate Keymaker object
            Keymaker keymkr = new Keymaker();
            keymkr.keypair = keymkr.keygen.generateKeyPair();       
            //Public key
            PublicKey pubkey = keymkr.keypair.getPublic();
            //Private key
            PrivateKey prvkey = keymkr.keypair.getPrivate();
            //store them into file
            Keymaker.store( AppConfig.PUBLICKEY_FILE , pubkey.getEncoded());
            Keymaker.store( AppConfig.PRIVATEKEY_FILE , prvkey.getEncoded());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //store keypair to file
    public static void store( String path, byte[] key ){
       File file = new File( path );
       file.getParentFile().mkdirs();
        try {
            Files.write(Paths.get(path), key, StandardOpenOption.CREATE);
            System.out.println("Done...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }           
    public static void main(String[] args) {
        Keymaker.mkKeyPair();
    }
    
    
}
