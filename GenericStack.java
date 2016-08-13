import java.util.*;

public class GenericStack<E> {
    private E stack[];
    private int stackSize;
    private int sp;

    private static final int DEFAULT_STACK_SIZE = 100;

    public GenericStack()
    {
        this(DEFAULT_STACK_SIZE);
    }

    @SupporessWarnings("unchecked")
    public GenericStack(int size)
    {
        stack = (E[]) new Object[size];
        stackSize = size;
        sp = 0;
    }

    public void clear()
    {
        Arrays.fill(stack, 0, sp, null);
        sp = 0;
    }

    public void push(E x)
    {
        if (sp >= stackSize) {
            throw new IllegalStateException("Stack overflow");
        }
        stack[sp++] = x;
    }

    public E pop()
    {
        if (sp <= 0) {
            throw new EmptyStackException();
        }
        E value = stack[--sp];
        stack[sp] = null;
        return value;
    }

    public boolean isEmpty()
    {
        return sp == 0;
    }

    public String toString()
    {
        String s = "GenericStack=[";
        for (int i = 0; i < sp; i++) {
            s = s + stack[i];
            if (i < sp - 1) {
                s = s + ",";
            }
        }
        s = s + "]";
        return s;
    }
}
