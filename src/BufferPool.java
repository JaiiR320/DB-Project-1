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

    void record(int index){
        
    }
}
