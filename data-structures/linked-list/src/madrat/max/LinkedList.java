package madrat.max;

import java.util.ArrayList;

public class LinkedList {

    private Object data;
    private LinkedList next;
    private LinkedList head;

    public LinkedList(Object data){
        this.data = data;
        this.head = this;
    }

    public LinkedList head(){
        return this.head;
    }

    public LinkedList last(){
        var end = this.head;
        while (end.next != null){
            end = end.next;
        }
        return end;
    }

    public int length(){
        var length = 0;
        var n = this.head();

        while (n != null){
            length++;
            n = n.next;
        }
        return length;
    }

    public Object data(){
        return this.head.data;
    }

    public boolean isEmpty(){
        return this.head == null;
    }

    public LinkedList add(Object data){
        var end = new LinkedList(data);
        var n = this;

        if(this.head == null){
            this.head = new LinkedList(data);
        }

        while (n.next != null){
            n = n.next;
        }
        n.next = end;

        return this.head;
    }

    public Object indexOf(Object data){
        var count = 0;
        var n = this.head;
        while (n != null){
            if(n.data.equals(data)){
                return count;
            }
            count++;
            n = n.next;
        }
        return null;
    }

    public ArrayList<Object> getItems(){
        var n = this.head;
        ArrayList<Object> array = new ArrayList<Object>();
        while (n != null){
            array.add(n.data);
            n = n.next;
        }
        return array;
    }

    public int remove(Object data){
        LinkedList current = this.head;
        LinkedList prev = null;
        int index = 0;

        if(this.head == null){
            throw new RuntimeException("list is empty");
        }

        if(this.head.data.equals(data)){
            this.head = this.head.next;
            return index;
        }

        while (current != null && !current.head.data.equals(data)){
            prev = current;
            current = current.next;
            index++;
        }

        if(current == null){
            throw new RuntimeException("cannot delete " + data + ". element not found");
        }

        prev.next = current.head.next;
        return index;
    }

    public int clear(){
        int counter = 0;

        for (var item: this.getItems()
        ) {
            this.remove(item);
            counter++;
        }
        return counter;
    }
}