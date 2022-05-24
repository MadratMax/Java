package madrat.DataStructures;

public class StackNode {

    private final int data;
    private StackNode prev;

    public StackNode(int data, StackNode prev) {
        this.data = data;
        this.prev = prev;
    }

    public int getData() {
        return data;
    }

    public StackNode getPrev() {
        return prev;
    }
}
