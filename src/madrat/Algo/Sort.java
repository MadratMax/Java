package madrat.Algo;

import java.util.Arrays;

public class Sort {

    public static int[] bubbleSort(int[] ar) {

        for (int k = 0; k < ar.length - 1; k++) {
            for (int j = 0; j < ar.length - 1; j++) {
                int left = j;
                int right = j + 1;

                if (ar[right] < ar[left]) {
                    int temp = ar[left];
                    ar[left] = ar[right];
                    ar[right] = temp;
                }
            }
        }

        return ar;
    }

    public static int[] recursiveBubbleSort(int[] ar, int ar_length) {

        if (ar_length == 1)
            return ar;

        for (int k = 0; k < ar.length - 1; k++) {
            if (ar[k + 1] < ar[k]) {
                int temp = ar[k];
                ar[k] = ar[k + 1];
                System.out.println("replaced " + ar[k + 1] + " with " + temp);
                ar[k + 1] = temp;
            }

            recursiveBubbleSort(ar, ar_length - 1);
        }

        return ar;
    }

    public static int [] mergeSort(int [] ar) {
        if (ar.length < 2)
            return ar;

        int [] left = Arrays.copyOfRange(ar, 0, ar.length/2);
        int [] right = Arrays.copyOfRange(ar, ar.length/2, ar.length - 1);

        return merge(mergeSort(left), mergeSort(right));
    }

    private static int [] merge(int[] left, int[] right) {
        int [] resArr = new int [left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;

        while(leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                resArr[leftIndex] = left[leftIndex];
                leftIndex++;
            } else {
                resArr[rightIndex] = right[rightIndex];
                rightIndex++;
            }
        }

        return resArr;
    }
}
