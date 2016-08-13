public class BinSort {
    public static void sort(BinSortData[] a)
    {
        final int N = a.length;
        final int M = BinSortData.M;

        BinSortData[] bin = new BinSortData[M + 1];

        for (int i = 0; i < N; i++) {
            int key = a[i].getKey();

            if (bin[key] != null) {
                throw new IllegalArgumentException("キー" + key + "が重複しています。");
            }
            
            bin[key] = a[i];
        }

        int j = 0;
        for (int i = 0; i <= M; i++) {
            if (bin[i] != null) {
                a[j++] = bin[i];
            }
        }
    }
}
