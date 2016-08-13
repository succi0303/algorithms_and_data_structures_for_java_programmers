class HeapSort0 {
    public static void sort(int[] a)
    {
        int n = a.length;

        Heap heap = new Heap(n);

        for (int i = 0; i < n; i++) {
            heap.insert(a[i]);
        }

        for (int i = 0; i < n; i++) {
            a[i] = heap.deleteMin();
        }
    }
}
