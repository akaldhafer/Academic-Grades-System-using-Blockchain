package Hash;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class Hasher {
    private static int size = 16;
    public static byte[] getSecureRand() throws Exception {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[size];
        sr.nextBytes(bytes);
        return bytes;}
    //generate the hash output for the given input (String passwd)
    public static String hash( String passwd, String algo ) throws Exception{
        //create message digest instance for algorithm
        MessageDigest md = MessageDigest.getInstance( algo ); 
        md.update( passwd.getBytes());//add the input to the class
        byte[] hashcode = md.digest();//generate the hash
        //convert hashcode into String object ie., hash variable
        System.out.println( "Hashcode: " + Base64.getEncoder().encodeToString(hashcode) );
        //convert the hashcode into hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashcode.length; i++) {
            sb.append(Integer.toHexString(0xFF & hashcode[i]));
        }
        return sb.toString();
    }   
}
