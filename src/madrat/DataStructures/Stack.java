package madrat.DataStructures;

public class Stack {

    private final int maxSize;
    private int size;
    private StackNode currentNode;

    public Stack(int size) {
        this.maxSize = size;
    }

    public Object pop() {
        Object data = null;
        if (!isEmpty()) {
            data = currentNode.getData();
            currentNode = currentNode.getPrev();
        }


        return data;
    }

    public Stack push(Object data) {
        if (size < maxSize) {
            currentNode = new StackNode(data, currentNode);
            size++;
        }

        return this;
    }

    public boolean isEmpty() {
        return currentNode == null;
    }

    public void print() {
        StackNode temp = currentNode;
        System.out.println("Stack data:");

        if (temp == null) {
            System.out.println("empty");
            return;
        }

        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getPrev();
        }

        System.out.println("-----");
    }
}

class StackNode {
    private final Object data;
    private StackNode prev;

    public StackNode(Object data, StackNode prev) {
        this.data = data;
        this.prev = prev;
    }

    public Object getData() {
        return data;
    }

    public StackNode getPrev() {
        return prev;
    }
}
