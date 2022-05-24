package madrat.DataStructures;

public class HeapNode {

    public HeapNode parent;
    public HeapNode leftChild;
    public HeapNode rightChild;
    public int data;
    private boolean top;

    public HeapNode(int data) {
            this.data = data;
    }

    public boolean hasParent() {
        return parent != null;
    }
}
