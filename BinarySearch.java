public class BinarySearch {
    static private class Entry {
        int key;
        Object data;

        private Entry(int key, Object data)
        {
            this.key = key;
            this.data = data;
        }
    }

    final static int MAX = 100;
    Entry[] table = new Entry[MAX];
    int n = 0;

    public Object search(int key)
    {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int middle = (low + high) / 2;
            if (key == table[middle].key) {
                return table[middle].data;
            } else if (key < table[middle].key) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }

        return null;
    }
}
