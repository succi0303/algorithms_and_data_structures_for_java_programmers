import java.util.*;

public class BoyerMoore {
    public static int search(String text, String pattern)
    {
        int patLen = pattern.length();
        int textLen = text.length();

        int[] skip = new int[65536];

        Arrays.fill(skip, patLen);
        for (int x = 0; x < patLen - 1; x++) {
            skip[pattern.charAt(x)] = patLen - x - 1;
        }

        int i = patLen - 1;

        while (i < textLen) {
            int j = patLen - 1;
            while (text.charAt(i) == pattern.charAt(j)) {
                if (j == 0) {
                    return i;
                }
                i--;
                j--;
            }
            i = i + Math.max(skip[text.charAt(i)], patLen - j);
        }
        return -1;
    }
}
