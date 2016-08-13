public class NQueenMain {
    private static void abort(String message)
    {
        System.err.println("起動法: java NQueenMain クイーンの数");
        System.err.println(message);
        System.exit(1);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            abort("パラメータの個数が違います。");
        }

        int n = 0;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            abort("クイーンの数には正の整数を指定してください: " + args[0]);
        }
        if (n <= 0) {
            abort("クイーンの数には正の整数を指定してください: " + args[0]);
        }

        NQueen nq = new NQueen(n);
        if (nq.tryQueen(0)) {
            nq.print();
        } else {
            System.out.println("残念ながら、解は存在しません。");
        }
    }
}
