public class CircularNode {
    public String data;
    public CircularNode next;
    public CircularNode previous;

    public CircularNode(String data){
        this.data=data;
        next=null;
        previous=null;
    }
}
