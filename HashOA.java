public class HashOA {
    private static class Bucket {
        MyKey key;
        Object data;

        private Bucket(MyKey key, Object data)
        {
            this.key = key;
            this.data = data;
        }
    }

    Bucket[] table;
    int bucketSize;
    int nElements;

    static final MyKey DELETED = new MyKey(null);
    static final MyKey EMPTY = new MyKey(null);
    static final int DEFAULT_BUCKET_SIZE = 53;

    public HashOA()
    {
        this(DEFAULT_BUCKET_SIZE);
    }

    public HashOA(int bucketSize)
    {
        table = new Bucket[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            table[i] = new Bucket(EMPTY, null);
        }
        this.bucketSize = bucketSize;
        nElements = 0;
    }

    private int hash(MyKey key)
    {
        return key.hashCode() % bucketSize;
    }

    private int rehash(int h)
    {
        return (h + 1) % bucketSize;
    }

    public Object find(MyKey key)
    {
        int count = 0;
        int h = hash(key);

        MyKey k;
        while ((k = table[h].key) != EMPTY) {
            if (k != DELETED && key.equals(k)) {
                return table[h].data;
            }
            if (++count > bucketSize) {
                return null;
            }
            h = rehash(h);
        }
        return null;
    }

    public boolean insert(MyKey key, Object data)
    {
        int count = 0;
        int h = hash(key);

        MyKey k;
        while ((k = table[h].key) != EMPTY && k != DELETED) {
            if (key.equals(k)) {
                return false;
            }
            if (++count > bucketSize) {
                throw new IllegalStateException("ハッシュ表にこれ以上データを登録できません。");
            }
            h = rehash(h);
        }
        table[h].key = key;
        table[h].data = data;
        nElements++;
        return true;
    }

    public boolean delete(MyKey key)
    {
        int count = 0;
        int h = hash(key);

        MyKey k;
        while ((k = table[h].key) != EMPTY) {
            if (k != DELETED && key.equals(k)) {
                table[h].key = DELETED;
                table[h].data = null;
                nElements--;
                return true;
            }
            if (++count > bucketSize) {
                return false;
            }
            h = rehash(h);
        }
        return false;
    }

    public String toString()
    {
        String s = "";
        for (int i = 0; i < table.length; i++) {
            s += "Bucket " + i + ": ";
            MyKey k = table[i].key;
            if (k == EMPTY) {
                s += "空\n";
            } else if (k == DELETED) {
                s += "削除済み\n";
            } else {
                s += "key=[" + k + "] data=[" + table[i].data + "]\n";
            }
        }
        s += "Size: " + nElements + "\n";
        return s;
    }
}
