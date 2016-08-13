public class BinSortData {
    static final int M = 100;
    private int key;
    private Object data;

    BinSortData(int key, Object data)
    {
        if (key < 0 || key > M) {
            throw new IllegalArgumentException("キー" + key + "が範囲外です。");
        }
        this.key = key;
        this.data = data;
    }

    public int getKey()
    {
        return key;
    }

    public Object getData()
    {
        return data;
    }
}
