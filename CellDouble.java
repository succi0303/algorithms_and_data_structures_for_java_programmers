class CellDouble {
    CellDouble prev;
    CellDouble next;
    Object data;

    CellDouble(Object data)
    {
        prev = next = null;
        this.data = data;
    }
}
