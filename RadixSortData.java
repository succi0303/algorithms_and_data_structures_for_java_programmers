public class RadixSortData {
    final static int KEY_MAX = 0xffff;
    private int key;
    private Object data;

    RadixSortData(int key, Object data)
    {
        if (key < 0 || key > KEY_MAX) {
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
