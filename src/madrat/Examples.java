package madrat;

import madrat.Algo.Sort;
import madrat.DataStructures.BinSearchTree;
import madrat.DataStructures.DoublyLinkedList;
import madrat.DataStructures.Stack;
import madrat.Utils.*;

import java.util.ArrayList;

import static madrat.Utils.printArrayWithIndex;
import static madrat.Utils.printObject;

public class Examples {

    public static void usingList() {
        DoublyLinkedList dl = new DoublyLinkedList(1);
        //dl.addNode(2);
        //dl.addNode(3);
        //dl.addNode(4);
        //dl.addNode(5);
        dl.addNode("6");
        dl.addNode(new int[] {1,2,3});
        dl.print();
        dl.removeNode(5);
        dl.print();
        dl.removeNode(2);
        dl.removeNode("6");
        dl.removeNode(1);
        dl.print();
        dl.removeNode(4);
        dl.removeNode(3);
        dl.print();
    }

    public static void usingBubbleSort() {
        printArrayWithIndex(Sort.bubbleSort(new int[] {5,4,1,9,2,3,6,8,7}));
    }

    public static void usingStack() {
        Stack stack = new Stack(5);
        stack.push("one");
        stack.push(2);
        stack.push(new ArrayList<>());
        stack.push(4);
        stack.push(5);

        stack.push(6);

        stack.print();

        printObject(stack.pop());
        printObject(stack.pop());

        stack.print();

        printObject(stack.pop());
        printObject(stack.pop());
        printObject(stack.pop());
        printObject(stack.pop());

        stack.print();
    }

    public static void usingBinSearchTree() {
        BinSearchTree tree = new BinSearchTree();
        tree.add(1);
        tree.add(18);
        tree.add(3);
        tree.add(2);
        tree.add(11);
        tree.add(5);
        tree.add(6);
        tree.add(4);
        tree.add(10);
    }
}
