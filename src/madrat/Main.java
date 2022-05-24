package madrat;

import madrat.Tasks.AmazonStore;
import madrat.Algo.*;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        //printArrayWithIndex(Sort.bubbleSort(new int[] {5,4,1,9,2,3,6,8,7}));
    }

    private static void printArray(int[] ar) {
        for (int i: ar) {
            System.out.println(i);
        }
    }

    private static void printArrayWithIndex(int[] ar) {
        for (int i = 0; i< ar.length; i++) {
            System.out.println(i + " | " + ar[i]);
        }
    }

    private static void printString(String str) {
        System.out.println(str);
    }

    private static void printInt(int num) {
        System.out.println(num);
    }

    private static void printBool(boolean res) {
        System.out.println(res);
    }
}
