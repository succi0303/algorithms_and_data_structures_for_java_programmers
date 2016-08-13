import java.util.*;

public class MyStack2 {
    private MyDoublyLinkedList stack;
    private int nElements;

    public MyStack2()
    {
        stack = new MyDoublyLinkedList();
        nElements = 0;
    }

    public void clear()
    {
        stack = new MyDoublyLinkedList();
        nElements = 0;
    }

    public void push(Object x)
    {
        stack.insertLast(x);
        nElements++;
    }

    public Object pop()
    {
        if (nElements-- <= 0) {
            throw new EmptyStackException();
        }
        return stack.removeLast();
    }

    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    public String toString()
    {
        return "要素数: " + nElements + " " + stack.toString();
    }
}
