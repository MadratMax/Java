package madrat.DataStructures;

public class DoublyLinkedList {

    private DoublyLinkedListNode node;

    public DoublyLinkedList(int data) {
        node = new DoublyLinkedListNode(data);
    }

    public void addNode(int data) {
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
        DoublyLinkedListNode tempNode = this.node;

        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }

        newNode.prev = tempNode;
        tempNode.next = newNode;
    }

    public void removeNode(int data) {
        DoublyLinkedListNode tempNode = this.node;

        while (tempNode.next != null) {
            if (tempNode.data == data) {
                tempNode.next.prev = tempNode.prev;
                if (tempNode.prev != null)
                    tempNode.prev.next = tempNode.next;
               else {
                   node = tempNode.next;
                }

                System.out.println("removed" + data);
            }

            tempNode = tempNode.next;
        }


    }

    public void print() {
        DoublyLinkedListNode tempNode = this.node;
        System.out.println("DL data:");

        while (tempNode.next != null) {
            int prevData = 2;
            int nextData = 0;
            if (tempNode.next != null && tempNode.next.data != 0)
                nextData = tempNode.next.data;
            if (tempNode.prev != null && tempNode.prev.data != 0)
                prevData = tempNode.prev.data;
            System.out.println(prevData + " <- " + tempNode.data + " -> " + nextData);
            tempNode = tempNode.next;
        }

        int prevData = 0;
        int nextData = 0;
        if (tempNode.next != null && tempNode.next.data != 0)
            nextData = tempNode.next.data;
        if (tempNode.prev != null && tempNode.prev.data != 0)
            prevData = tempNode.prev.data;
        System.out.println(prevData + " <- " + tempNode.data + " -> " + nextData);
        System.out.println("--------");
    }
}
