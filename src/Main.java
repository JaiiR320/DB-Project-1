/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        BufferPool p = new BufferPool(Integer.parseInt(args[1]));
        Frame f = new Frame("data/F1.txt");
        String record = f.record(10);
        System.out.println(record);
    }
}

