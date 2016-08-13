class QuickSort2 {
    private static int partition(int[] a, int l, int r)
    {
        int i = l - 1;
        int j = r;

        int pivot = a[r];

        while (true) {
            while (a[++i] < pivot)
                ;

            while (i < --j && pivot < a[j])
                ;

            if (i >= j)
                break;

            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        int temp = a[i];
        a[i] = a[r];
        a[r] = temp;
        return i;
    }

    public static void sort(int[] a)
    {
        int n = a.length;
        int[] low = new int[30];
        int[] high = new int [30];
        int sp;

        low[0] = 0;
        high[0] = n - 1;
        sp = 1;

        while (sp > 0) {
            int l = low[--sp];
            int r = high[sp];

            if (l >= r) {
                ;
            } else {
                int v = partition(a, l, r);
                low[sp] = v + 1;
                high[sp++] = r;
                low[sp] = 1;
                high[sp++] = v - 1;
            }
        }

    }
}
