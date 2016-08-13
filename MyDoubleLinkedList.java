import java.util.*;

public class MyDoubleLinkedList {
    final CellDouble head;

    public MyDoubleLinkedList()
    {
        head = new CellDouble("***List Head**");
        head.prev = head.next = head;
    }

    private void insertAfter(CellDouble p, Object data)
    {
        CellDouble x = new CellDouble(data);
        x.prev = p;
        x.next = p.next;
        p.next.prev = x;
        p.next = x;
    }

    public void insertFirst(Object data)
    {
        insertAfter(head, data);
    }

    public void insertLast(Object data)
    {
        insertAfter(head.prev, data);
    }

    private void removeCell(CellDouble p)
    {
        p.prev.next = p.next;
        p.next.prev = p.prev;
    }

    public Object removeFirst()
    {
        if (isEmpty()) {
            throw new NoSuchElementException("双方向リストが空です。");
        }

        CellDouble cell = head.next;
        removeCell(cell);
        return cell.data;
    }

    public Object removeLast()
    {
        if (isEmpty()) {
            throw new NoSuchElementException("双方向リストが空です。");
        }
        CellDouble cell = head.prev;
        removeCell(cell);
        return cell.data;
    }

    public boolean isEmpty()
    {
        return head.next == head;
    }

    public String toString()
    {
        String s = "[";
        for (CellDouble p = head.next; p != head; p = p.next) {
            s += p.data + " ";
        }
        s += "]";
        return s;
    }
}
