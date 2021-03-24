package block;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//Serializable makes this object to be able to convert to byte[](hashing requires byte[])
public class Block implements Serializable {
    
    //data properties
    private Integer index;
    private String hash, previousHash;
    private int Test, Lab, FinalM;
    private String Course;
    private String timestamp_datestamp, Dsignature;
    
    //constructor
    public Block(String Dsignature,int Test,int Lab,int FinalM,String Course,
            String previousHash) throws Exception{
        this.Test = Test;
        this.Lab = Lab;
        this.FinalM = FinalM;
        this.Course = Course;
        this.previousHash = previousHash;
        this.Dsignature = Dsignature;
        this.timestamp_datestamp = new SimpleDateFormat("dd/MM/yyyy _HH.mm.ss").format(Calendar.getInstance().getTime());
        byte[] blockBytes = Block.getBytes( this ); 
        if ( blockBytes != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(previousHash.getBytes());
            baos.write(Long.toString(Test).getBytes());
            baos.write(Long.toString(Lab).getBytes());
            baos.write(Long.toString(FinalM).getBytes());
            baos.write(Course.getBytes());
            baos.write(timestamp_datestamp.getBytes());
            baos.write(Dsignature.getBytes());
            baos.write( blockBytes );
            this.hash = Hasher.hash(baos.toByteArray(), "SHA-256");
        } else { //throw an exception
            throw new Exception("Unable to generate currentHash!");
        }
    }
    //Purpose: To convert the Block_Add object into byte[]
    private static byte[] getBytes( Block blk ){
        //ByteArrayOutputStream and ObjectOutputStream are from java.io
        try( ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(baos);
                ) {
            //write Block_Add object into Stream
            out.writeObject( blk );
            return baos.toByteArray();//get byte[] from baos
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //getset methods
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
    public String getDsignature() {
        return Dsignature;
    }

    public void setDsignature(String Dsignature) {
        this.Dsignature = Dsignature;
    }
}
