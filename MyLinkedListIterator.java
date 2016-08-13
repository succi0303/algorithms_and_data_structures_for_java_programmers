import java.util.*;

public class MyLinkedListIterator implements Iterator {
    private Cell p;

    public MyLinkedListIterator(MyLinkedList list)
    {
        p = list.header;
    }

    public boolean hasNext()
    {
        return p.next != null;
    }

    public Object next()
    {
        if (p.next == null) {
            throw new NoSuchElementException();
        }

        p = p.next;
        return p.data;
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

    public static void main(String args[])
    {
        MyLinkedList list = new MyLinkedList();
        list.insert(20);
        list.insert(15);
        list.insert(37);
        list.insert(3);
        System.out.println(list);

        System.out.println("----<イテレータ>----");
        Iterator iter = list.iterator();
        int count = 1;
        while (iter.hasNext()) {
            System.out.println(count++ + "番目の要素: " + iter.next());
        }

        System.out.println("----<拡張for文>----");
        count = 1;
        for (Object o; list) {
            System.out.println(count++ + "番目の要素: " + o);
        }
    }
}
