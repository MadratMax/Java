package madrat.Recursion;

public class Recursion {

    public static void countToZero(int num) {
        if (num == 0) {
            System.out.println(0);
            return;
        }

        System.out.println(num);
        if (num < 0)
            countToZero(num+1);
        else
            countToZero(num-1);
    }

    public static String tmp = "";

    public static String reverseString(String str) {

        if (str.length() == 0) {
            return tmp;
        }

        String last = str.substring(str.length() - 1);
        tmp = tmp + last;

        return reverseString(str.substring(0, str.length() - 1));
    }

    public static String reverseString2(String str) {
        if(str.isEmpty()) {
            return str;
        }

        return reverseString2(str.substring(1)) + str.charAt(0);
    }
}
