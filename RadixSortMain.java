public class RadixSortMain {
    public static void main(String[] args)
    {
        int data[] = {
            0x2f38, 0x2fb7, 0x9328, 0xa400, 0x000f,
            0x0002, 0x0844, 0xef85, 0x289a, 0x2f30,
        };
        RadixSortData[] array = new RadixSortData[data.length];
        for (int i = 0; i < data.length; i++) {
            array[i] = new RadixSortData(data[i], "要素" + i);
        }

        System.out.println("ソート前");
        RadixSort.dumpArray(array);

        RadixSort.sort(array);
    }
}
