public class BinSortMain {
    public static void main(String[] args)
    {
        int keys[] = {13, 24, 15, 5, 98, 44, 35, 96, 82, 73};
        BinSortData[] array = new BinSortData[keys.length];
        for (int i = 0; i < keys.length; i++) {
            array[i] = new BinSortData(keys[i], "要素" + i);
        }

        BinSort.sort(array);

        for (int i = 0; i < array.length; i++) {
            System.out.println("keys=" + array[i].getKey() + " data=" + array[i].getData());
        }
    }
}
