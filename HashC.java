public class HashC {
    private static class Cell {
        MyKey key;
        Object data;
        Cell next;

        private Cell(MyKey key, Object data)
        {
            this.key = key;
            this.data = data;
        }
    }

    Cell[] table;
    int bucketSize;
    int nElements;

    static final int DEFAULT_BUCKET_SIZE = 50;

    public HashC(int bucketSize)
    {
        table = new Cell[bucketSize];
        this.bucketSize = bucketSize;
        nElements = 0;
    }

    private int hash(MyKey key)
    {
        return key.hashCode() % bucketSize;
    }

    static Object find(MyKey key)
    {
        for (Cell p = table[hash(key)]; p != null; p = p.next) {
            if (key.equals(p.key)) {
                return p.data;
            }
        }
        return null;
    }

    public boolean insert(MyKey key, Object data)
    {
        if (find(key) != null) {
            return false;
        }
        Cell p = new Cell(key, data);
        int h = hash(key);
        p.next = table[h];
        table[h] = p;
        nElements++;
        return true;
    }

    public boolean delete(MyKey key)
    {
        int h = hash(key);

        if (table[h] == null) {
            return false;
        }

        if (key.equals(table[h].key)) {
            Cell p = table[h];
            table[h] = p.next;
            nElements--;
            return true;
        }

        Cell p;
        Cell q;
        for (q = table[h], p = q.next; p != null; q= p, p = p.next) {
            if (key.equals(p.key)) {
                q.next = p.next;
                nElements--;
                return true;
            }
        }
    }

    public String toString()
    {
        String s = "";

        for (int i = 0; i < table.length; i++) {
            s += "Bucket: " + i + ":";
            for (Cell p = table[i]; p != null; p = p.next) {
                s += "[" + p.key + ":" + p.data + "]";
            }
            s += "\n";
        }
        s += "Size: " + nElements + "\n";
        return s
    }
}
