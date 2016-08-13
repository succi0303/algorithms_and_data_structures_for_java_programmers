public class MyLinkedList implements Iterable {
    final Cell header;

    public MyLinkedList()
    {
        header = new Cell("**List Head**");
    }

    public void insert(int num)
    {
        Cell p = header.next;
        Cell q = header;
        while (p != null && num > (Integer)p.data) {
            q = p;
            p = p.next;
        }

        Cell newCell = new Cell(num);
        newCell.next = p;
        q.next = newCell;
    }

    public MyLinkedListIterator iterator()
    {
        return new MyLinkedListIterator(this);
    }

    public String toString()
    {
        String s = "[";
        for (Cell p = header.next; p != null; p = p.next) {
            s += p.data + " ";
        }
        s += "]";
        return s;
    }

    public static void main(String args[])
    {
        MyLinkedList list = new MyLinkedList();

        System.out.println(list);
        list.insert(5);
        list.insert(7);
        list.insert(2);
        list.insert(12);
        list.insert(4);
        System.out.println(list);
    }
}
