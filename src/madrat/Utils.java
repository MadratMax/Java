package madrat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {

    public static void printArray(int[] ar) {
        for (int i: ar) {
            System.out.println(i);
        }
    }

    public static void printArrayWithIndex(int[] ar) {
        for (int i = 0; i< ar.length; i++) {
            System.out.println(i + " | " + ar[i]);
        }
    }

    public static List<Integer> generateRandomArrayList(int n, int threshold){
        ArrayList<Integer> list = new ArrayList<Integer>(n);
        Random random = new Random();

        for (int i = 0; i < n; i++)
        {
            int nextInt = random.nextInt(threshold);
            if (!list.contains(nextInt)) {
                list.add(nextInt);
            }
        }

        System.out.println("generated int array: " + Arrays.toString(list.toArray()));
        System.out.println("----------------------");
        return list;
    }

    public static int[] generateRandomIntArray(int n, int threshold){
        ArrayList<Integer> list = new ArrayList<Integer>(n);
        Random random = new Random();

        for (int i = 0; i < n; i++)
        {
            int nextInt = random.nextInt(threshold);
            if (!list.contains(nextInt)) {
                list.add(nextInt);
            }
        }

        System.out.println("generated int array: " + Arrays.toString(list.toArray()));
        System.out.println("----------------------");

        int[] intArray = new int[list.size()];

        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = list.get(i);
        }

        return intArray;
    }

    public static void printString(String str) {
        System.out.println(str);
    }

    public static void printObject(Object str) {
        System.out.println(str);
    }

    public static void printInt(int num) {
        System.out.println(num);
    }

    public static void printBool(boolean res) {
        System.out.println(res);
    }
}
