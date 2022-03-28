import java.lang.String;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Frame {
    private String content;
    String[] records;
    boolean dirty = false;
    boolean pinned = false;
    int blockId = -1;
    
    Frame(String fileName){
        try{
            File f = new File(fileName);
            Scanner scan = new Scanner(f);
            this.content = scan.nextLine();
            this.blockId = Character.getNumericValue(content.charAt(2));
            this.records = this.content.split("\\.", 100);
            for(int i = 0; i < records.length; i++){
                records[i] += ".";
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }
    }

    Frame(){
        this.records = null;
    }

    String record(int i) {//throws Exception {
        if (i % 100 == 0)
            i = 100;
        else
            i %= 100;

        return records[i-1];
    }
}
