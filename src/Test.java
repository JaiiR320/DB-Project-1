/**
 * Test class manually runs commands
 */
public class Test {
    static void test1(){
        BufferPool p = new BufferPool(3);
        p.set(430, "F05-Rec450, Jane Do, 10 Hill Rd, age020.");
        System.out.println("Output: Write was successful; Brought File 5 from disk; Placed in Frame 1\n");
        p.get(430);
        System.out.println("Output: F05-Rec450, Jane Do, 10 Hill Rd, age020.; File 5 already in memory; Located in Frame 1\n");
        p.get(20);
        System.out.println("Output: F01-Rec020, Name020, address020, age020.; Brought file 1 from disk; Placed in Frame 2\n");
        p.set(430, "F05-Rec450, John Do, 23 Lake Ln, age056.");
        System.out.println("Output: Write was successful; File 5 already in memory; Located in Frame 1\n");
        p.pin(5);
        System.out.println("Output: File 5 pinned in Frame 1; Not already pinned\n");
        p.unpin(3);
        System.out.println("Output: The corresponding block 3 cannot be unpinned because it is not in memory.\n");
        p.get(430);
        System.out.println("Output: F05-Rec450, John Do, 23 Lake Ln, age056.; File 5 already in memory; Located in Frame 1\n");
        p.pin(5);
        System.out.println("Output: File 5 pinned in Frame 1; Already pinned\n");
        p.get(646);
        System.out.println("Output: F07-Rec646, Name646, address646, age646.; Brought file 7 from disk; Placed in Frame 3\n");
        p.pin(3);
        System.out.println("Output: File 3 pinned in Frame 2; Not already pinned; Evicted file 1 from Frame 2\n");
        p.set(10, "F01-Rec010, Tim Boe, 09 Deer Dr, age009.");
        System.out.println("Output: Write was successful; Brought File 1 from disk; Placed in Frame 3; Evicted file 7 from Frame 3\n");
        p.unpin(1);
        System.out.println("Output: File 1 in frame 3 is unpinned; Frame was already unpinned\n");
        p.get(355);
        System.out.println("Output: F04-Rec355, Name355, address355, age355.; Brought file 4 from disk; Placed in Frame 3; Evicted file 1 from frame 3\n");
        p.pin(2);
        System.out.println("Output: File 2 is pinned in Frame 3; Frame 3 was not already pinned; Evicted file 4 from frame 3\n");
        p.get(156);
        System.out.println("Output: F02-Rec156, Name156, address156, age156.; File 2 already in memory; Located in Frame 3\n");
        p.set(10, "F01-Rec010, No Work, 31 Hill St, age100.");
        System.out.println("Output: The corresponding block #1 cannot be  accessed from disk because the memory buffers are full; Write was unsuccessful\n");
        p.pin(7);
        System.out.println("Output: The corresponding block 7 cannot be pinned because the memory buffers are full\n");
        p.get(10);
        System.out.println("Output: The corresponding block #1 cannot be accessed from disk because the memory buffers are full\n");
        p.unpin(3);
        System.out.println("Output: File 3 is unpinned in frame 2; Frame 2 was not already unpinned\n");
        p.unpin(2);
        System.out.println("Output: File 2 is unpinned in frame 3; Frame 3 was not already unpinned\n");
        p.get(10);
        System.out.println("Output: F01-Rec010, Tim Boe, 09 Deer Dr, age009.; Brought file 1 from disk; Placed in Frame 2; Evicted file 3 from frame 2\n");
        p.pin(6);
        System.out.println("Output: File 6 pinned in Frame 3; Not already pinned; Evicted file 2 from frame 3\n");
    }

    static void test2(){
        BufferPool p = new BufferPool(4);

        p.get(1);
        p.pin(2);
        p.get(300);
        p.set(300, "F01-Rec010, No Work, 31 Hill St, age100.");
        p.pin(1);
        p.get(580);
        p.get(450);
        p.unpin(1);
        p.get(300);
        p.pin(1);
        p.pin(1);
        p.pin(4);
        p.pin(3);
        p.get(200);
        p.get(700);
        p.set(700, "F01-Rec010, No Work, 31 Hill St, age100.");
        p.unpin(7);
        p.unpin(2);
        p.unpin(3);
        p.get(400);
        p.get(401);
        p.unpin(1);
        p.get(501);
    }

    static void test3(){
        BufferPool bp = new BufferPool(4);

        bp.get(1);
        bp.pin(2);
        bp.get(300);
        bp.pin(1);
        bp.get(580);
        bp.get(450);
        bp.get(300);
        bp.pin(4);
        bp.pin(3);
        bp.get(200);
        bp.get(700);
        bp.unpin(2);
        bp.unpin(3);
        bp.get(401);
        bp.unpin(1);
        bp.get(501);
    }
}
