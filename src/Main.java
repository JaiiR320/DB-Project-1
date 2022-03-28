/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        BufferPool p = new BufferPool(3);
        p.set("F01-Rec001, wazoooo, address001, age001.");
        System.out.println(p.get(1));
    }
}

