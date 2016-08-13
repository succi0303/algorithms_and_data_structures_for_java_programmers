class MergeSortList {
    private static Cell mergeList(Cell a, Cell b)
    {
        Cell head = new Cell(0);
        Cell p = head;

        while (a != null & b != null) {
            if (a.data <= b.data) {
                p.next = a;
                p = a;
                a = a.next;
            } else {
                p.next = b;
                p = b;
                b = b.next;
            }
        }

        if (a == null) {
            p.next = b;
        } else {
            p.next = a;
        }

        return head.next;
    }

    public static Cell mergeSortList(Cell x)
    {
        if (x == null || x.next == null) {
            return x;
        }

        Cell a = x;
        Cell b = x.next;
        if (b != null) {
            b= b.next;
        }

        while (b != null) {
            a = a.next;
            b = b.next;
            if (b != null) {
                b = b.next;
            }
        }

        Cell p = a.next;
        a.next = null;

        return mergeList(mergeSortList(x), mergeSortList(p));
    }
}
