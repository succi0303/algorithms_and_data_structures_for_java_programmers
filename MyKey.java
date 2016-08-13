public class MyKey {
    String str;

    public MyKey(String s)
    {
        str = s;
    }

    public boolean equals(Object o)
    {
        if (! (o instanceof MyKey)) {
            return false;
        }
        MyKey k = (MyKey)o;
        return str.equals(k.str);
    }

    public int hashCode()
    {
        int sum;
        for (int i = 0; i < str.length(); i++) {
            sum += (int)str.charAt(i);
        }
        return sum;
    }

    public String toString()
    {
        return str;
    }
}
