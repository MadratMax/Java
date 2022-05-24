package madrat.DataStructures;

public class Stack {

    private final int maxSize;
    private int size;
    private StackNode currentNode;

    public Stack(int size) {
        this.maxSize = size;
    }

    public Stack pop() {
        if (!isEmpty())
            currentNode = currentNode.getPrev();

        return this;
    }

    public Stack push(int data) {
        if (size < maxSize) {
            currentNode = new StackNode(data, currentNode);
            size++;
        }

        return this;
    }

    public boolean isEmpty() {
        return currentNode == null;
    }
}
