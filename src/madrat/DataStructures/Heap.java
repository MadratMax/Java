package madrat.DataStructures;

public class Heap {

    private HeapNode currentNode = null;

    public Heap(int data) {
        this.currentNode = new HeapNode(data);
    }

    public HeapNode insert(int data) {

        HeapNode node = new HeapNode(data);

        walkAndInsert(data);

        return currentNode;

        //restore();
    }

    private void walkAndInsert(int data) {
        HeapNode node = new HeapNode(data);
        HeapNode temp = currentNode;

        while (currentNode != null) {
            if (currentNode.leftChild == null) {
                currentNode.leftChild = node;
                currentNode.leftChild.parent = currentNode;
            } else if (currentNode.rightChild == null) {
                currentNode.rightChild = node;
                currentNode.rightChild.parent = currentNode;
            }

            currentNode = currentNode.parent;
        }
    }

    private void restore() {

        HeapNode node = currentNode;
    }

    private void swap(HeapNode sibling, HeapNode parent) {

    }
}

class HeapNode {
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
