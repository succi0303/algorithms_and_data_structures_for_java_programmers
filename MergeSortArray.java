class MergeSortArray {
    private static int[] b;

    private static void mergeSortArray(int[] a, int low, int high)
    {
        if (low >= high) {
            return;
        }

        int mid = (low + high) / 2;
        mergeSortArray(a, low, mid);
        mergeSortArray(a, mid + 1, high);

        System.arraycopy(a, low, b, low, mid - low + 1);
        for (int i = mid + 1, j = high; i <= high; i++, j++) {
            b[i] = a[j];
        }

        int i = low;
        int j = high;
        for (int k = low; k <= high; k++) {
            if (b[i] <= b[j]) {
                a[k] = b[i++];
            } else {
                a[k] = b[j--];
            }
        }
    }

    public static void sort(int[] a)
    {
        b = new int[a.length];
        mergeSortArray(a, 0, a.length - 1);
        b = null;
    }
}
