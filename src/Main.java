import java.util.Scanner;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BufferPool p = new BufferPool(Integer.valueOf(3));//args[1]));

        p.set(430, "F05-Rec450, Jane Do, 10 Hill Rd, age020.");
        //Output: Write was successful; Brought File 5 from disk; Placed in Frame 1
        p.get(430);
        //Output: F05-Rec450, Jane Do, 10 Hill Rd, age020.; File 5 already in memory; Located in Frame 1
        p.get(20);
        //Output: F01-Rec020, Name020, address020, age020.; Brought file 1 from disk; Placed in Frame 2
        p.set(430, "F05-Rec450, John Do, 23 Lake Ln, age056.");
        //Output: Write was successful; File 5 already in memory; Located in Frame 1
        p.pin(5);
        //Output: File 5 pinned in Frame 1; Not already pinned
        p.unpin(3);
        //Output: The corresponding block 3 cannot be unpinned because it is not in memory.
        p.get(430);
        //Output: F05-Rec450, John Do, 23 Lake Ln, age056.; File 5 already in memory; Located in Frame 1
        p.pin(5);
        //Output: File 5 pinned in Frame 1; Already pinned
        p.get(646);
        //Output: F07-Rec646, Name646, address646, age646.; Brought file 7 from disk; Placed in Frame 3
        p.pin(3);
        //Output: File 3 pinned in Frame 2; Not already pinned; Evicted file 1 from Frame 2 
        p.set(10, "F01-Rec010, Tim Boe, 09 Deer Dr, age009.");
        //Output: Write was successful; Brought File 1 from disk; Placed in Frame 3; Evicted file 7 from Frame 3
        p.unpin(1);
        //Output: File 1 in frame 3 is unpinned; Frame was already unpinned
        p.get(355);
        //Output: F04-Rec355, Name355, address355, age355.; Brought file 4 from disk; Placed in Frame 3; Evicted file 1 from frame 3
        p.pin(2);
        //Output: File 2 is pinned in Frame 3; Frame 3 was not already pinned; Evicted file 4 from frame 3
        p.get(156);
        //Output: F02-Rec156, Name156, address156, age156.; File 2 already in memory; Located in Frame 3
        p.set(10, "F01-Rec010, No Work, 31 Hill St, age100.");
        //Output: The corresponding block #1 cannot be  accessed from disk because the memory buffers are full; Write was unsuccessful 
        p.pin(7);
        //Output: The corresponding block 7 cannot be pinned because the memory buffers are full
        p.get(10);
        //Output: The corresponding block #1 cannot be accessed from disk because the memory buffers are full
        p.unpin(3);
        //Output: File 3 is unpinned in frame 2; Frame 2 was not already unpinned
        p.unpin(2);
        //Output: File 2 is unpinned in frame 3; Frame 3 was not already unpinned
        p.get(10);
        //Output: F01-Rec010, Tim Boe, 09 Deer Dr, age009.; Brought file 1 from disk; Placed in Frame 2; Evicted file 3 from frame 2
        p.pin(6);
        //Output: File 6 pinned in Frame 3; Not already pinned; Evicted file 2 from frame 3
        while(true){
            String[] com = scan.nextLine().split("\\ ", 3);
            switch (com[0]) {
                case "Get":
                    p.get(Integer.valueOf(com[1]));
                    break;
                case "Set":
                    p.set(Integer.valueOf(com[1]), com[2].substring(1, 41));
                    break;
                case "Pin":
                    p.pin(Integer.valueOf(com[1]));
                    break;
                case "Unpin":
                    p.unpin(Integer.valueOf(com[1]));
                    break;
                default:
                    break;
            }
        }
    }
}

