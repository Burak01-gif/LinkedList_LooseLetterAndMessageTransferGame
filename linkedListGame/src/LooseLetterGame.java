import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class LooseLetterGame {
    public CircularNode current;
    static int size = 0;

    public LooseLetterGame() {
        current = null;
    }

    public void insertAfterCurrent(String s) {
        CircularNode circNode = new CircularNode(s);
        if (current == null) {
            current = circNode;
            current.next = current;
            current.previous = current;
        } else {
            circNode.previous = current;
            circNode.next = current.next;
            current.next.previous = circNode;
            current.next = circNode;
        }
        size++;
    }

    @Override
    public String toString() {
        CircularNode temp = current;
        StringBuilder s = new StringBuilder();
        do {
            s.append(temp.data).append(" -> ");
            temp = temp.next;
        } while (temp != current);
        return s.toString();
    }

    public static String randomName(List<String> names) {
        int n = (int) (Math.random() * names.size());
        return names.get(n);
    }

    public CircularNode GetNth(int index) {
        CircularNode a = current;
        for (int count = 0; count < index; count++) {
            a = a.next;
        }
        return a;
    }

    public void removeNode(CircularNode node) {
        if (size == 1) {
            current = null;
        } else {
            node.previous.next = node.next;
            node.next.previous = node.previous;
            if (current == node) {
                current = node.next;
            }
        }
        size--;
    }

    public static void main(String[] args) throws Exception {
        LooseLetterGame cl = new LooseLetterGame();
        Scanner scn = new Scanner(System.in);
        System.out.println("Please enter the number of people:");
        int n = scn.nextInt();
        scn.nextLine(); // Consume newline

        List<String> names = Files.readAllLines(Paths.get("C:\\Users\\ezero\\java_projects\\java_entry\\COMP2102 Data Structure\\class.txt"));
        for (int i = 0; i < n; i++) {
            cl.insertAfterCurrent(randomName(names));
        }

        System.out.println(cl);

        while (cl.size > 1) {
            int x = (int) (Math.random() * cl.size);
            CircularNode selectedNode = cl.GetNth(x);

            selectedNode.data = selectedNode.data.substring(1);
            System.out.println("Removed a letter from: " + selectedNode.data);
            System.out.println(cl);

            if (selectedNode.data.isEmpty()) {
                System.out.println("Removing player at position " + (x + 1));
                cl.removeNode(selectedNode);
            }
        }

        System.out.println("The winner is: " + cl.current.data);
    }
}
