class QuickSort1 {
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

    private static void quickSort(int[] a, int l, int r)
    {
        if (l >= r)
            return;

        int v = partition(a, l, r);
        quickSort(a, l, v - 1);
        quickSort(a, v + 1, r);
    }

    private static void sort(int[] a)
    {
        quickSort(a, 0, a.length - 1);
    }
}
