//package block;
//
////Signature API
//import java.security.*;
//import java.util.Base64;
//import javax.crypto.*;
//
//public class MySignature {
//    
//    private Signature signature;
//    
//    private KeyPairGenerator keygen;
//    private KeyPair keyPair;
//    
//    //constructor
//    public MySignature(){
//    
//        try {
//            signature = Signature.getInstance("SHA256WithRSA");
//            keygen = KeyPairGenerator.getInstance("RSA");
//            keyPair = keygen.generateKeyPair();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//    //sign
//    public String sign( String data ) throws Exception{
////        signature.initSign( keyPair.getPrivate() );
//        signature.initSign( DemoUtils.getPrivateKey("Keypair/PrivateKey") );
//        signature.update( data.getBytes() );
//        return Base64.getEncoder().encodeToString( signature.sign() );
//    }
//    //verify
//    public boolean verify( String data, String ds ) throws Exception{
////        signature.initVerify( keyPair.getPublic() );
//        signature.initVerify( DemoUtils.getPublicKey("Keypair/PublicKey") );
//        signature.update( data.getBytes() );
//        return signature.verify( Base64.getDecoder().decode(ds) );
//    }
//    
//}
