package madrat.Algo;

public class Search {

    public static int binSearch(int[] list, int search) {
        int left = 0;
        int right = list.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            if (search < list[middle]) {
                right = middle - 1;
            } else if (search > list[middle]) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public static boolean binSearchRecursive(int[] list, int start, int end, int targer) {
        int middle = (start + end) / 2;

        if (start > end)
            return false;

        if (targer == list[middle]) {
            return true;
        }

        if (targer > list[middle]) {
            return binSearchRecursive(list, middle + 1, end, targer);
        } else {
            return binSearchRecursive(list, start, middle - 1, targer);
        }
    }
}
