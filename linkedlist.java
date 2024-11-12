public class linkedList {
    Node head;

    linkedList(){
        head = null;
    }

    public void add(int data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }
}
