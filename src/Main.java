import java.util.Scanner;

/**
 * Main
 * main class that runs the CLI
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BufferPool p = new BufferPool(Integer.valueOf(3));//args[1]));
        System.out.println("Buffer pool initialized with " + Integer.valueOf(3) + " frames\n");
        Test.test1();
        while(true){
            System.out.print("> ");
            String[] com = scan.nextLine().split("\\ ", 3);
            switch (com[0]) {
                case "GET":
                    p.get(Integer.valueOf(com[1]));
                    break;
                case "SET":
                    if(com[2].length() != 42) {
                        System.out.println("Bad input");
                        break;
                    }
                    p.set(Integer.valueOf(com[1]), com[2].substring(1, 41));
                    break;
                case "PIN":
                    p.pin(Integer.valueOf(com[1]));
                    break;
                case "UNPIN":
                    p.unpin(Integer.valueOf(com[1]));
                    break;
                case "EXIT":
                    scan.close();
                    return;
                default:
                    break;
            }
        }
    }
}

