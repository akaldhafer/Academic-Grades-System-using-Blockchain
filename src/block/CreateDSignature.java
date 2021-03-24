package block;
import javax.crypto.*;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateDSignature {
    //Algorithms
    private final String HASHING_ALGO = "SHA-256";
    private final String CRYPTO_ALGO = "RSA";
    //CRYPTO SYSTEM
    private Cipher cipher;
    //KeyPair
    KeyPairGenerator keygen;
    KeyPair keyPair; 
    //constructor
    public CreateDSignature(){
        //make keypair and instantiate the cipher
        try {
            //keypair
            keygen = KeyPairGenerator.getInstance(CRYPTO_ALGO);
            keyPair = keygen.generateKeyPair();
            //cipher instance
            cipher = Cipher.getInstance(CRYPTO_ALGO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Operation: hash, encrypt, decrypt/verify
    public String hash( String data ){
        byte[] hashBytes = null;
        try {
            //What is the class needed in hashing? -MessageDigest
            MessageDigest md = MessageDigest.getInstance(HASHING_ALGO);
            hashBytes = md.digest( data.getBytes() );
            //you may address the hashBytes into hex format
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();}
        return Base64.getEncoder().encodeToString(hashBytes);
    }
    //DS- encrypt-Private; decrypt-Public
    public String encrypt( String dataHash ){
        byte[] dsBytes = null;
        try {
            //init for cipher
            cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
            dsBytes = cipher.doFinal( dataHash.getBytes() );
        } catch (Exception ex) {
            ex.printStackTrace();}
        return Base64.getEncoder().encodeToString(dsBytes);
    }  
    public boolean verify( String data, String digitalSignature ){
        byte[] dataHash = Base64.getDecoder().decode(this.hash(data));
        byte[] dsBytes = null;
        try {cipher.init(Cipher.DECRYPT_MODE, keyPair.getPublic());
            dsBytes = cipher.doFinal( Base64.getDecoder().decode(digitalSignature) );
        } catch (Exception ex) {
            Logger.getLogger(CreateDSignature.class.getName()).log(Level.SEVERE, null, ex);}
        return Arrays.equals(dataHash, Base64.getDecoder().decode(dsBytes));
    }
    
}
