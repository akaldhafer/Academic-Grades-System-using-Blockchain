package Blockchain;

import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.*;
import block.Block;

public class Blockchain {
    
    //master/datafile
    private static final String BCHAIN_FILE = "masterchain.dat";
    
    //data structure - LinkedList
    private static LinkedList<Block> db = new LinkedList<>();
    
    //methods
    public static void nextBlock( Block newBlock ){
        Blockchain.db.add( newBlock );
        persist();
        distribute(newBlock);
    }
    
    //update the chain file
    private static void persist(){
        try( FileOutputStream fos = new FileOutputStream( BCHAIN_FILE ); 
                ObjectOutputStream out = new ObjectOutputStream( fos )
                ) {
            out.writeObject( db );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //get the existing chain
    public static LinkedList<Block> get(){
        try( FileInputStream fis = new FileInputStream( BCHAIN_FILE ); 
                ObjectInputStream in = new ObjectInputStream( fis )
                ) {
            return (LinkedList<Block>)in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }        
    }
    
    //display and store the chain
    private static void distribute(Object block){
        try {
            PrintWriter ADD = new PrintWriter(new FileWriter("MarkList.txt", true));
            String chain = new GsonBuilder().setPrettyPrinting().create().toJson(block);
            System.out.println( chain );
                 
            ADD.println(chain);
            ADD.flush();
            ADD.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
