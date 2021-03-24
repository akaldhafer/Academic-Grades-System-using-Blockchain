package Hash;

import java.util.Base64;
public class HashAccount {
    private static final String ALGO = "MD5";
    public static String user;
    public static String pass;
    public static String Rand;
    public static void create( String username, String Password ) throws Exception{
        /*Hashing the account details*/
        String rand = Base64.getEncoder().encodeToString( Hasher.getSecureRand() );
        String hash_Password = Hasher.hash( Txt.append(rand, Password), ALGO);
        String hash_Username = Hasher.hash( Txt.append(rand, username), ALGO);   
        /*assign the hashed details to the local variables to be used in the recoding class*/
        user = hash_Username;
        pass = hash_Password;
        Rand = rand;              
        //displaying the hashed details below: 
         System.out.println( "rand: " + rand );
         System.out.println( "Password Hash: " + hash_Password );
         System.out.println( "Username Hash: " + hash_Username );
    }   
}
