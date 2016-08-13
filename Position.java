public class Position {
    private int x;
    private int y;

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void moveX(int xDelta)
    {
        x += xDelta;
    }

    public void moveY(int yDelta)
    {
        y += yDelta;
    }

    public void moveXY(int xDelta, int yDelta)
    {
        x += xDelta;
        y += yDelta;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}
