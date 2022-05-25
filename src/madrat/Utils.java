package madrat;

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
