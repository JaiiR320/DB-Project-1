import java.sql.Blob;
import java.util.ArrayList;

public class BufferPool {
    ArrayList<Frame> frames = new ArrayList<>();
    String message = "";
    int max;
    BufferPool(int max){
        this.max = max;
        for (int i = 0; i < max; i++) {
            frames.add(new Frame());
        }
    }

    /** HELPER
     * Returns the index of the frame that is requested, sets the frame if
     * space is avaliable, returns -1 if not avaliable to be set
     * @param frameID
     * @return
     */
    private int getFrame(int blockId) {
        this.message = "";
        //case if the frame is in the buffer already
        for (Frame frame : frames) {
            if (frame.blockId == blockId) {
                int out = frames.indexOf(frame);
                message += "File " + blockId + " already in memory; located in frame " + (out+1);
                return out;
            }
        }
        //else bring block into buffer
        for (Frame frame : frames) {
            if ((frame.pinned == false && frame.dirty == false) || frame.blockId == -1) {
                int index = frames.indexOf(frame);
                frames.set(frames.indexOf(frame), new Frame("data/F" + blockId + ".txt"));
                message += "brought file " + blockId + " from disk; placed in frame " + (index+1);
                return index;
            }
        }
        return -1;
    }

    String get(int recordId){
        int blockId = 0;
        if(recordId % 100 == 0)
            blockId = recordId / 100;
        else
            blockId = (recordId / 100) + 1;

        int bufferIndex = this.getFrame(blockId);
        if(bufferIndex != -1){
            String out = frames.get(bufferIndex).record(recordId);
            System.out.println(out + "; " + this.message);
            return frames.get(bufferIndex).record(recordId);
        }
        return "";
    }

    void set(int recordId, String data){
        int blockId = 0;
        if(recordId % 100 == 0)
            blockId = recordId;
        else
            blockId = (recordId / 100) + 1;

        int bufferIndex = getFrame(blockId);
        if(bufferIndex == -1) {
            System.out.println("error; " + this.message);
            return;
        }
        Frame f = frames.get(bufferIndex);
        f.records[(recordId % 100) - 1] = data;
        f.dirty = true;
        System.out.println("write was successful; " + this.message);
    }

    void pin(int blockId){
        for (Frame frame : frames) {
            if (frame.blockId == blockId) {
                frame.pinned = true;
            }
        }
    }
    void unpin(int blockId){
        for (Frame frame : frames) {
            if (frame.blockId == blockId) {
                frame.pinned = false;
            }
        }
    }
}
