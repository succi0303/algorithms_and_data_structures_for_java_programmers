class HeapSort {
    private static void downHeap(int[] a, int from, int to)
    {
        int value = a[from];

        int i = from;
        while (i <= to / 2) {
            int j = i * 2;
            if (j + 1 <= to && a[j] > a[j + 1]) {
                j++;
            }

            if (value <= a[j]) {
                break;
            }

            a[i] = a[j];
            i = j;
        }

        a[i] = value;
    }

    public static void sort(int[] a)
    {
        int n = a.length - 1;

        for (int i = n; i >= 1; i--) {
            downHeap(a, i, n);
        }

        for (int i = n; i >= 2; i--) {
            int tmp = a[1];
            a[1] = a[i];
            a[i] = tmp;
            downHeap(a, 1, i - 1);
        }
    }
}
