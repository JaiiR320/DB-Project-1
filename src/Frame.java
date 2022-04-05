import java.lang.String;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Frame {
    private String content; // the actual file line
    String[] records; // array of the records
    boolean dirty = false; // dirty bit
    boolean pinned = false; // pinned bit
    int blockId = -1; // block ID
    
    /**
     * Constructor for Frame with data from block/file
     * scans the doc for the data, and splits it by the '.'
     * stores the records in an array for easy access
     * @param fileName the file name to import data from
     */
    Frame(String fileName){
        try{
            File f = new File(fileName);
            Scanner scan = new Scanner(f);
            this.content = scan.nextLine();
            this.blockId = Character.getNumericValue(content.charAt(2));
            this.records = this.content.split("\\.", 100);
            for(int i = 0; i < records.length-1; i++){
                records[i] += ".";
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }
    }

    /**
     * Creates an empty frame, which is already
     * must be blockId = -1
     */
    Frame(){
        this.records = null;
    }

    /**
     * returns the record based off the index in the array of records
     * index is essentially the recordid, edge case taken into account
     * @param i
     * @return
     */
    String record(int i) {
        if (i % 100 == 0)
            i = 100;
        else
            i %= 100;

        return records[i-1];
    }

    /**
     * Writes to the file with the current data held in the frame
     * joins the record array into single string and writes 
     * that string to a new copy file so as not to overwrite original data
     */
    void write(){
        String data = String.join("",this.records);
        try {
            FileWriter f  = new FileWriter("src/Project1/F"+this.blockId+".txt");
            f.write(data);
            f.close();
            System.out.println("File " + this.blockId + " was written to");
        } catch (Exception e) {
            System.out.println("File not written to");
            e.printStackTrace();
        }        
    }
}
