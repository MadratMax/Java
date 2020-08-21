package madrat;

import java.util.ArrayList;

public class DoubledLinkedList {

    private Object data;
    private DoubledLinkedList prev;
    private DoubledLinkedList next;
    private DoubledLinkedList head;

    public DoubledLinkedList(Object data){
        this.data = data;
        this.head = this;
    }
}