package madrat.Algo;

public class Fibo {

    public static void allFib(int n) {
        for (int i=0; i < n; i++) {
            System.out.println(i + ": " + fibo(i));
        }
    }

    private static int fibo(int i) {
        if (i <= 0)
            return 0;
        else if (i == 1)
            return 1;

        return fibo(i -1) + fibo(i - 2);
    }
}
