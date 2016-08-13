import java.util.*;

public class NQueen {
    private enum Status {
        FREE,
        NOT_FREE
    }

    private final int N;
    private int[] pos;
    private Status[] col;
    private Status[] down;
    private Status[] up;

    public NQueen(int numberOfQueens)
    {
        N = numberOfQueens;
        pos = new int[N];
        col = new Status[N];
        down = new Status[2 * N - 1];
        up = new Status[2 * N - 1];

        Arrays.fill(pos, -1);
        Arrays.fill(col, Status.FREE);
        Arrays.fill(down, Status.FREE);
        Arrays.fill(up, Status.FREE);
    }

    public boolean tryQueen(int a)
    {
        for (int b = 0; b < N; b++) {
            if (col[b] == Status.FREE && up[a + b] == Status.FREE && down[a - b- + (N - 1)] == Status.FREE) {
                pos[a] = b;
                col[b] = Status.NOT_FREE;
                up[a + b] = Status.NOT_FREE;
                down[a - b + (N - 1)] = Status.NOT_FREE;

                if (a + 1 >= N) {
                    return true;
                } else {
                    if (tryQueen(a + 1)) {
                        return true;
                    } else {
                        pos[a] = -1;
                        col[b] = Status.FREE;
                        up[a + b] = Status.FREE;
                        down[a - b + (N - 1)] = Status.FREE;
                    }
                }
            }
        }
        return false;
    }

    public void print()
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pos[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
