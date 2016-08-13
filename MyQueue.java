import java.util.*;

public class MyQueue {
    private Object queue[];
    private int queueSize;
    private int front;
    private int rear;

    private static final int DEFAULT_QUEUE_SIZE = 100;

    public MyQueue()
    {
        this(DEFAULT_QUEUE_SIZE);
    }

    public MyQueue(int size)
    {
        queueSize = size;
        queue = new Object[size];
        front = rear = 0;
    }

    private int next(int a)
    {
        return (a + 1) % queueSize;
    }

    public void clear()
    {
        front = rear = 0;
        Arrays.fill(queue, 0, queueSize, null);
    }

    public void enqueue(Object x)
    {
        if (next(rear) == front) {
            throw new IllegalStateException("これ以上、待ち行列に要素を追加できません。");
        }
        queue[rear] = x;
        rear = next(rear);
    }

    public Object dequeue()
    {
        if (front == rear) {
            throw new NoSuchElementException("待ち行列が空なので要素を取り出せません。");
        }
        Object x = queue[front];
        queue[front] = null;
        front = next(front);
        return x;
    }

    public boolean isEmpty()
    {
        return front == rear;
    }

    public String toString()
    {
        String s = "MyQueue=[";
        for (int i = front; i != rear; i = next(i)) {
            s += queue[i] + " ";
        }
        s += "] front=" + front + " rear=" + rear;
        return s;
    }

    public static void main(String args[])
    {
        MyQueue q = new MyQueue(5);

        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        System.out.println(q);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q);
        q.enqueue("d");
        q.enqueue("e");
        System.out.println(q.dequeue());
        System.out.println(q);
        q.enqueue("f");
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q);
        q.clear();
        System.out.println(q);
        q.enqueue("g");
        q.enqueue("h");
        System.out.println(q);
    }
}
