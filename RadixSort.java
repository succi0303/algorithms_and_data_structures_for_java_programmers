public class RadixSort {
    static final int M = 15;
    static final int MASK = 0xf;

    static void sort(RadixSortData[] a)
    {
        final int N = a.length;
        int pass = 1;

        RadixSortData[] b = new RadixSortData[N];

        int[] count = new int[M + 1];

        for (int bit = 0; bit < 16; bit += 4) {
            for (int i = 0; i < M; i++) {
                count[i] = 0;
            }

            for (int i = 0; i < N; i++) {
                count[(a[i].getKey() >> bit) & MASK]++;
            }

            for (int i = 0; i < M; i++) {
                count[i + 1] += count[i];
            }

            for (int i = N - 1; i >= 0; i--) {
                b[--count[(a[i].getKey() >> bit) & MASK]] = a[i];
            }

            System.arraycopy(b, 0, a, 0, N);

            System.out.println("Pass " + pass++ + "---------");
            dumpArray(a);
        }
    }

    public static void dumpArray(RadixSortData a[])
    {
        for (int i = 0; i < a.length; i++) {
            System.out.printf("key=%04x data=%s%n", a[i].getKey(), a[i].getData());
        }
    }
}
