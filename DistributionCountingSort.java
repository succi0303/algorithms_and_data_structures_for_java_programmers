public class DistributionCountingSort {
    static void sort(BinSortData[] a)
    {
        final int N = a.length;
        final int M = BinSortData.M;

        int[] count = new int[M + 1];

        for (int i = 0; i < N; i++) {
            count[a[i].getKey()]++;
        }

        BinSortData[] b = new BinSortData[N];
        for (int i = N - 1; i >= 0; i--) {
            b[--count[a[i].getKey()]] = a[i];
        }

        System.arraycopy(b, 0, a, 0, N);
    }
}
