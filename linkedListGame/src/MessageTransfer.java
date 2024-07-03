import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class MessageTransfer {
    DoubleNode first;
    DoubleNode last;
    int size = 0;

    public MessageTransfer() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static String randomName() throws Exception {
        int n = (int) (((Math.random() * 118) + 1));
        return Files.readAllLines(Paths.get("C:\\Users\\ezero\\java_projects\\java_entry\\COMP2102 Data Structure\\class.txt")).get(n - 1);
    }

    void insertLast(String a) {
        DoubleNode newNode = new DoubleNode(a);
        if (first == null) {
            first = newNode;
        } else {
            last.next = newNode;
            newNode.previous = last;
        }
        last = newNode;
        size++;
    }

    public void print() {
        DoubleNode tmp = first;
        while (tmp != null) {
            System.out.print(tmp.data + "<->");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public DoubleNode search(String value) {
        DoubleNode tmp = first;
        while (tmp != null) {
            if (tmp.data.equals(value)) {
                return tmp;
            }
            tmp = tmp.next;
        }
        return null;
    }

    public DoubleNode GetNth(int index) {
        DoubleNode x = first;
        for (int count = 0; count < index; count++) {
            x = x.next;
        }
        return x;
    }

    public static void main(String[] args) throws Exception {
        MessageTransfer m1 = new MessageTransfer();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of hubs:");
        int hubs = input.nextInt();
        System.out.println("Creating a linked list of 30 students, " + hubs + " are hubs.");
        System.out.println("Enter the number of students to pass the message:");
        int pass = input.nextInt();

        for (int i = 0; i < 30; i++) {
            String name = randomName();
            if (m1.search(name) == null) {
                m1.insertLast(name);
            } else {
                i--;
            }
        }

        for (int j = 0; j < hubs; j++) {
            int hubsIndex = (int) (Math.random() * 30);
            if (!m1.GetNth(hubsIndex).data.endsWith("*")) {
                m1.GetNth(hubsIndex).data = m1.GetNth(hubsIndex).data + "*";
            } else {
                j--;
            }
        }

        m1.print();
        System.out.println("Enter the message: ");
        String msg = input.next();
        System.out.println("Enter the source student:");
        String source = input.next();

        String hubsS = source + "*";
        int frwdOrbckwrd = (int) ((Math.random() * 2) + 1);
        String direction = frwdOrbckwrd == 1 ? "forward" : "backward";
        System.out.println(source + " passes message " + msg + " in " + direction + " direction:");

        DoubleNode tmp;
        for (int k = 0; k < 30; k++) {
            if (frwdOrbckwrd == 2) {
                if (m1.GetNth(k).data.equals(hubsS)) {
                    tmp = m1.GetNth(k);
                    for (int i = 0; i < pass; i++) {
                        System.out.print(tmp.data + ", ");
                        tmp = tmp.previous;
                    }
                } else if (m1.GetNth(k).data.equals(source)) {
                    tmp = m1.GetNth(k);
                    System.out.println(tmp.data);
                    tmp = tmp.previous;
                    System.out.println(tmp.data);
                }
            } else if (frwdOrbckwrd == 1) {
                if (m1.GetNth(k).data.equals(hubsS)) {
                    tmp = m1.GetNth(k);
                    for (int i = 0; i < pass; i++) {
                        System.out.print(tmp.data + ", ");
                        tmp = tmp.next;
                    }
                } else if (m1.GetNth(k).data.equals(source)) {
                    tmp = m1.GetNth(k);
                    System.out.println(tmp.data);
                    tmp = tmp.next;
                    System.out.println(tmp.data);
                }
            }
        }
    }
}
