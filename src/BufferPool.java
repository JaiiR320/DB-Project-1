import java.util.ArrayList;

public class BufferPool {
    ArrayList<Frame> frames = new ArrayList<>();
    int max;
    private int lastEvicted = -1;
    
    /**
     * BufferPool constructor, takes in maximum number of frames
     * @param max
     */
    BufferPool(int max){
        this.max = max;
        for (int i = 0; i < max; i++) {
            frames.add(new Frame());
        }
    }

    /** 
     * Returns the index of the frame that is requested, sets the frame if
     * space is avaliable, evicts unpinned frames when necessary
     * returns -1 if not avaliable to be set
     * @param frameID
     * @return
     */
    private int getFrame(int blockId) {
        //case if the frame is in the buffer already
        for (Frame frame : frames) {
            if (frame.blockId == blockId) {
                int index = frames.indexOf(frame);
                System.out.println("File " + blockId + " already in memory; located in frame " + (index+1));
                return index;
            }
        }

        //else bring block into buffer if there is empty space
        for (Frame frame : frames) {
            if (frame.blockId == -1) {
                int index = frames.indexOf(frame);
                frames.set(frames.indexOf(frame), new Frame("src/Project1/F" + blockId + ".txt"));
                System.out.println("brought file " + blockId + " from disk; placed in frame " + (index+1));
                return index;
            }
        }

        //there is not empty space, must evict
        for (int i = lastEvicted+1; i < max; i++) {
            Frame f = frames.get(i);
            if(!f.pinned) { // only evict if not pinned
                if (f.dirty) f.write(); // write if dirty
                int prev = f.blockId;
                int index = frames.indexOf(f);
                frames.set(frames.indexOf(f), new Frame("src/Project1/F" + blockId + ".txt"));
                System.out.println("evicted file " + prev + " from frame " + (index+1));
                System.out.println("placed file " + blockId + " in frame " + (index+1));
                this.lastEvicted = index;
                return index;
            }
            if(i == lastEvicted) return -1; //if we hit last evicted again
            if(i == max-1) i = 0;
        }

        //evict candidate to make space
        for (Frame frame : frames) {
            if (frame.pinned == false){
                if(frame.dirty) frame.write();
                int prev = frame.blockId;
                int index = frames.indexOf(frame);
                frames.set(frames.indexOf(frame), new Frame("src/Project1/F" + blockId + ".txt"));
                System.out.println("evicted file " + prev + " from frame " + (index+1));
                System.out.println("placed file " + blockId + " in frame " + (index+1));
                this.lastEvicted = index;
                return index;
            }
        }
        //cannot put in system
        return -1;
    }

    /**
     * Get the requested record, based off it's record ID
     * @param recordId
     * @return
     */
    void get(int recordId){
        System.out.println("getting " + recordId);
        int blockId = 0;
        //calculate block and record id
        if(recordId % 100 == 0)
            blockId = recordId / 100;
        else
            blockId = (recordId / 100) + 1;

        int bufferIndex = this.getFrame(blockId);
        if(bufferIndex == -1){ // frame not able to be placed in pool
            System.out.println("Process blocked, no space in buffer\n");
            return;
        }

        String out = frames.get(bufferIndex).record(recordId);
        System.out.println(out + '\n');
    }

    /**
     * Set the data of the corresponding record, set frame as dirty
     * similar methodology to get
     * @param recordId
     * @param data
     */
    void set(int recordId, String data){
        System.out.println("setting " + recordId);
        int blockId = 0;
        int index = -1;
        if(recordId % 100 == 0){
            blockId = recordId / 100;
            index = 100;
        }
        else {
            index = recordId % 100;
            blockId = (recordId / 100) + 1;
        }

        int bufferIndex = getFrame(blockId);
        if(bufferIndex == -1) { // frame not able to be placed in pool
            System.out.println("Process blocked, no space in buffer\n");
            return;
        }
        Frame f = frames.get(bufferIndex);
        f.records[index - 1] = data;
        f.dirty = true;
        System.out.println("write was successful\n");
    }

    /**
     * Pins the corresponding file in memory
     * If the block is not in memory, attempt to bring it in before pinning
     * @param blockId
     */
    void pin(int blockId){
        System.out.println("pinning " + blockId);
        int index = getFrame(blockId);

        if(index == -1){
            System.out.println("Process blocked, no space in buffer\n");
            return;
        }

        String out = "File " + blockId + " pinned in Frame " + (index+1);
        if (frames.get(index).pinned) 
            out += "; Already pinned";
        else
            out += "; Not already pinned";

        frames.get(index).pinned = true;
        System.out.println(out + "\n");
    }

    /**
     * Unpin the corresponding block in the buffer pool
     * if the block is not in memory, do nothing
     * @param blockId
     */
    void unpin(int blockId){
        System.out.println("unpinning " + blockId);
        for (Frame frame : frames) {
            if (frame.blockId == blockId) {
                String out = "File " + blockId + " in Frame " + (frames.indexOf(frame)+1) + " unpinned; ";
                if(frame.pinned)
                    out += "Frame was not already unpinned";
                else
                    out += "Frame was already unpinned";
                System.out.println(out+"\n");
                frame.pinned = false;
                return;
            }
        }
        System.out.println("block " + blockId + " cannot be unpinned because it is not in memory\n");
    }
}
