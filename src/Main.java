import java.util.Scanner;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BufferPool p = new BufferPool(Integer.valueOf(3));//args[1]));
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

