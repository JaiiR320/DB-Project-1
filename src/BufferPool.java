import java.util.ArrayList;

public class BufferPool {
    ArrayList<Frame> frames = new ArrayList<>();
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
        //case if the frame is in the buffer already
        for (Frame frame : frames) {
            if (frame.blockId == blockId) {
                return frames.indexOf(frame);
            }
        }
        //else bring block into buffer
        for (Frame frame : frames) {
            if ((frame.pinned == false && frame.dirty == false) || frame.blockId == -1) {
                int index = frames.indexOf(frame);
                frames.set(frames.indexOf(frame), new Frame("data/F" + blockId + ".txt"));
                return index;
            }
        }
        return -1;
    }

    String get(int recordId){
        int blockId = (recordId / 100) + 1;
        int bufferIndex = this.getFrame(blockId);
        if(bufferIndex != -1){
            return frames.get(bufferIndex).record(recordId);
        }
        return "";
    }

    void set(String data){
        String rstring = "" + data.charAt(7) + data.charAt(8) + data.charAt(9);
        int recordId = Integer.parseInt(rstring);

        String bstring = "" + data.charAt(1) + data.charAt(2);
        int blockId = Integer.parseInt(bstring);

        int bufferIndex = getFrame(blockId);
        Frame f = frames.get(bufferIndex);
        f.records[(recordId % 100) - 1] = data;
        f.dirty = true;
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
