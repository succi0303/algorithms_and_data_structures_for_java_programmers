class Heap {
    int [] a;
    int n;
    
    public Heap(int size)
    {
        a = new int[size + 1];
        n = 0;
    }

    private void upHeap(int x)
    {
        int value = a[x];

        while (x > 1 && a[x / 2] > value) {
            a[x] = a[x / 2];
            x /= 2;
        }

        a[x] = value;
    }

    public void insert(int elem)
    {
        if (n >= a.length) {
            throw new IllegalStateException("これ以上ヒープに要素を登録できません。");
        }

        a[++n] = elem;
        upHeap(n);
    }

    private void downHeap()
    {
        int value = a[1];

        int i = 1;
        while (i <= n / 2) {
            int j = i * 2;
            if (j + 1 <= n && a[j] > a[j + 1]) {
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

    public int deleteMin()
    {
        if (n < 1) {
            throw new IllegalStateException("ヒープが空です。");
        }

        int value = a[1];

        a[1] = a[n--];
        downHeap();

        return value;
    }
}
