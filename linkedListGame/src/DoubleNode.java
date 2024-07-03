
import java.nio.file.Files;
import java.nio.file.Paths;

public class DoubleNode {
    String data;
    DoubleNode next;
    DoubleNode previous;

    public DoubleNode(String data) {
        this.data = data;
        next = null;
        previous = null;
    }


}
