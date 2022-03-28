import java.util.ArrayList;

public class BufferPool {
    ArrayList<Frame> frames;
    int max;
    BufferPool(int max){
        this.max = max;
        for (int i = 0; i < max; i++) {
            frames.add(new Frame());
        }
    }

    void get(Frame f){
        for (int i = 0; i < max; i++) {
            if(frames.get(i).blockId == -1 || frames.get(i).pinned == false){
                frames.set(i, f);
                return;
            }
        }
        System.out.println("No space in buffer pool");
    }

    void set(String data){

    }

    //returns the record from the associated index
    String record(int index){
        int block = index / 100;
        //block is in the buffer
        for (Frame frame : frames) {
            if (frame.blockId == block) {
                return frame.record(index % 100);
            }
        }
        //else bring block into buffer
        for (Frame frame : frames) {
            if (frame.pinned == false || frame.blockId == -1) {
                frames.set(frames.indexOf(frame), new Frame("data/F" + block + ".txt"));
                return frame.record(index % 100);
            }
        }
        System.out.println("Block not able to be brought into buffer");
        return "";
    }
}
