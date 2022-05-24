package madrat.Tasks;

public class Repetitions {

    public static int[] findRepetitionsAndRemove(int [] arr, int start, int end) {

        for (int j = 0; j < arr.length - 1; j++) {
            if (arr[j] == arr[j + 1]) {
                arr[j] = 0;
            }
        }
        return arr;
    }
}
