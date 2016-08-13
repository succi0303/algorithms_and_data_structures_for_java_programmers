import java.util.*;

public class Knapsack {
    int [] size;
    int [] value;
    int N;

    public Knapsack(int[] size, int[] value) {
        if (size.length != value.length) {
            throw new IllegalArgumentException("sizeとvalueの要素数が一致していません。");
        }

        this.N = size.length;
        this.size = (int[])size.clone();
        this.value = (int[])value.clone();
    }

    public void solve(int m) {
        int[] total = new int[m + 1];

        int[] choice = new int[m + 1];
        Arrays.fill(choice, -1);

        int repackTotal;

        System.out.printf("ナップザックの大きさは%d%n", m);

        for (int i = 0; i < N; i++) {
            for (int j = size[i]; j <= m; j++) {
                repackTotal = total[j - size[i]] + value[i];

                if (repackTotal > total[j]) {
                    total[j] = repackTotal;
                    choice[j] = i;
                }
            }
            System.out.printf("i = %d%n", i);
            System.out.printf("total = ");
            for (int j = 0; j <= m; j++) {
                System.out.printf("%4d", total[j]);
            }
            System.out.printf("%nchoice = ");
            for (int j = 0; j <= m; j++) {
                System.out.printf("%4d", choice[j]);
            }
            System.out.printf("\n");
        }

        for (int i = m; choice[i] >= 0; i -= size[choice[i]]) {
            System.out.printf("品物 %d (価値 %d)を詰め込む %n", choice[i], value[choice[i]]);
        }
    }

    private static void abort(String message)
    {
        System.err.printf("起動法: java Knapsack 大きさ%n");
        System.err.printf("%s%n", message);
        System.exit(1);
    }

    public static void main(String[] args)
    {
        Knapsack knapsack = new Knapsack(
                new int[] { 2, 3, 5, 7, 9},
                new int[] { 2, 4, 7, 11, 14}
                );

        int size = 0;
        if (args.length != 1) {
            abort("パラメータの個数が違います。");
        }
        try {
            size = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            abort("大きさには正の整数を指定してください。");
        }
        if (size <= 0) {
            abort("大きさには正の整数を指定してください。");
        }

        knapsack.solve(size);
    }
}
