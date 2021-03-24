package block;

import java.security.MessageDigest;

public class Hasher {
    
    //generate the hash output for the given input (byte[] Block)
    public static String hash( byte[] blockBytes, String algo ) throws Exception{
        //create message digest instance for algorithm
        MessageDigest md = MessageDigest.getInstance( algo ); 
        //add the input to the md
        //md.update( passwd.getBytes() );
        md.update( blockBytes );
        //generate the hash
        byte[] hashcode = md.digest();
        //convert hashcode into String object ie., hash variable
//        System.out.println( "Hashcode: " + Base64.getEncoder().encodeToString(hashcode) );
        //convert the hashcode into hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashcode.length; i++) {
            sb.append( 
                    Integer.toHexString(0xFF & hashcode[i])
            );
        }
        return sb.toString();
    }
    
}
