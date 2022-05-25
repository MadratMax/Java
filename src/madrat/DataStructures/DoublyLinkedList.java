package madrat.DataStructures;

public class DoublyLinkedList {

    private DoublyLinkedListNode node;

    public DoublyLinkedList(Object data) {
        node = new DoublyLinkedListNode(data);
    }

    public void addNode(Object data) {
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
        DoublyLinkedListNode tempNode = this.node;

        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }

        newNode.prev = tempNode;
        tempNode.next = newNode;
    }

    public void removeNode(Object data) {
        DoublyLinkedListNode tempNode = this.node;

        while (tempNode != null) {
            if (tempNode.data == data) {
                if (tempNode.next != null)
                    tempNode.next.prev = tempNode.prev;
                if (tempNode.prev != null)
                    tempNode.prev.next = tempNode.next;
                else
                    this.node = tempNode.next;

                System.out.println("removed: " + data);
            }

            tempNode = tempNode.next;
        }
    }

    public void print() {
        DoublyLinkedListNode tempNode = this.node;
        System.out.println("DL data:");

        if (this.node == null) {
            System.out.println("DL is empty:");
            return;
        }

        while (tempNode != null) {
            String prevData = "null";
            String nextData = "null";
            if (tempNode.next != null && tempNode.next.data != null) {
                nextData = String.valueOf(tempNode.next.data);
            }
            if (tempNode.prev != null && tempNode.prev.data != null) {
                prevData = String.valueOf(tempNode.prev.data);
            }

            System.out.println(prevData + " <- " + tempNode.data + " -> " + nextData);
            tempNode = tempNode.next;
        }

        System.out.println("--------");
    }
}

class DoublyLinkedListNode {

    public final Object data;
    public DoublyLinkedListNode prev;
    public DoublyLinkedListNode next;

    public DoublyLinkedListNode(Object data) {
        this.data = data;
    }
}
