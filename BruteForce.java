public class BruteForce {
    public static int search(String text, String pattern)
    {
        int patLen = pattern.length();
        int textLen = text.length();
        int i = 0;
        int j = 0;

        while (i < textLen && j < patLen) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        return (j == patLen) ? (i - j) : -1;
    }
}
