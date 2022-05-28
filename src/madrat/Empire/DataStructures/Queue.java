package madrat.Empire.DataStructures;

import java.util.LinkedList;

public class Queue {

    private final LinkedList<Object> queue;
    private int size;
    private StackNode currentNode;

    public Queue() {
        this.queue = new LinkedList<Object>();
    }

    public Object remove() {
        return queue.remove();
    }

    public Queue add(Object data) {
        queue.add(data);
        return this;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int getSize() { return queue.size(); }
}
