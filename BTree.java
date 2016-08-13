import java.io.*;

public class BTree {
    private static class Node {
        int serial;
    }

    private class InternalNode extends Node
    {
        int nChilds;
        Node[] child;
        Integer[] low;

        private InternalNode()
        {
            serial = serialNumber++;
            nChilds = 0;
            child = new Node[MAX_CHILD];
            low = new Integer[MAX_CHILD];
        }

        private int locateSubtree(Integer key)
        {
            for (int i = nChilds - 1; i > 0; i--) {
                if (key.compareTo(low[i]) >= 0) {
                    return i;
                }
            }
            return 0;
        }
    }

    private class Leaf extends Node {
        Integer key;
        Object data;

        private Leaf(Integer key, Object data)
        {
            serial = serialNumber++;
            this.key = key;
            this.data = data;
        }
    }

    private Node root;
    private int serialNumber = 0;

    private Leaf currentLeaf;

    final private static int MAX_CHILD = 5;
    final private static int HALF_CHILD = ((MAX_CHILD + 1) / 2);

    public BTree()
    {
        root = null;
    }

    public boolean search(Integer key)
    {
        currentLeaf = null;

        if (root == null) {
            return false;
        } else {
            Node p = root;
            while (p instanceof InternalNode) {
                InternalNode node = (InternalNode)p;
                p = node.child[node.locateSubtree(key)];
            }
            Leaf leaf = (Leaf)p;
            if (key.compareTo(leaf.key) == 0) {
                currentLeaf = leaf;
                return true;
            } else {
                return false;
            }
        }
    }

    public Object getData()
    {
        if (currentLeaf == null) {
            return null;
        } else {
            return currentLeaf.data;
        }
    }

    public boolean setData(Object data)
    {
        if (currentLeaf == null) {
            return false;
        } else {
            currentLeaf.data = data;
            return true;
        }
    }

    public static class InsertAuxResult {
        Node newNode;
        Integer lowest;

        private InsertAuxResult(Node newNode, Integer lowest)
        {
            this.newNode = newNode;
            this.lowest = lowest;
        }
    }

    private InsertAuxResult insertAux(InternalNode pnode, int nth, Integer key, Object data)
    {
        Node thisNode;
        if (pnode == null) {
            thisNode = root;
        } else {
            thisNode = pnode.child[nth];
        }

        if (thisNode instanceof Leaf) {
            Leaf leaf = (Leaf)thisNode;

            if (leaf.key.compareTo(key) == 0) {
                return null;
            } else {
                Leaf newLeaf = new Leaf(key, data);

                if (key.compareTo(leaf.key) < 0) {
                    if (pnode == null) {
                        root = newLeaf;
                    } else {
                        pnode.child[nth] = newLeaf;
                    }
                    return new InsertAuxResult(leaf, leaf.key);
                } else {
                    return new InsertAuxResult(newLeaf, key);
                }
            }
        } else {
            InternalNode node = (InternalNode)thisNode;
            int pos = node.locateSubtree(key);
            InsertAuxResult result = insertAux(node, pos, key, data);

            if (result == null || result.newNode == null) {
                return result;
            }

            if (node.nChilds < MAX_CHILD) {
                for (int i = node.nChilds - 1; i > pos; i--) {
                    node.child[i+1] = node.child[i];
                    node.low[i+1] = node.low[i];
                }
                node.child[pos+1] = result.newNode;
                node.low[pos+1] = result.lowest;
                node.nChilds++;
                return new InsertAuxResult(null, null);
            } else {
                InternalNode newNode = new InternalNode();

                if (pos < HALF_CHILD - 1) {
                    for (int i = HALF_CHILD - 1, j = 0; i < MAX_CHILD; i++, j++) {
                        newNode.child[j] = node.child[i];
                        newNode.low[j] = node.low[i];
                    }
                    for (int i = HALF_CHILD - 2; i > pos; i--) {
                        node.child[i + 1] = node.child[i];
                        node.low[i + 1] = node.low[i];
                    }
                    node.child[pos + 1] = result.newNode;
                    node.low[pos + 1] = result.lowest;
                } else {
                    int j = MAX_CHILD - HALF_CHILD;
                    for (int i = MAX_CHILD - 1; i >= HALF_CHILD; i--) {
                        if (i == pos) {
                            newNode.child[j] = result.newNode;
                            newNode.low[j--] = result.lowest;
                        }
                        newNode.child[j] = node.child[i];
                        newNode.low[j--] = node.low[i];
                    }
                    if (pos < HALF_CHILD) {
                        newNode.child[0] = result.newNode;
                        newNode.low[0] = result.lowest;
                    }
                }
                node.nChilds = HALF_CHILD;
                newNode.nChilds = (MAX_CHILD + 1) - HALF_CHILD;

                return new InsertAuxResult(newNode, newNode.low[0]);
            }
        }
    }

    public boolean insert(Integer key, Object data)
    {
        currentLeaf = null;

        if (root == null) {
            root = new Leaf(key, data);
            return true;
        } else {
            InsertAuxResult result = insertAux(null, -1, key, data);

            if (result == null) {
                return false;
            }

            if (result.newNode != null) {
                InternalNode newNode = new InternalNode();
                newNode.nChilds = 2;
                newNode.child[0] = root;
                newNode.child[1] = result.newNode;
                newNode.low[1] = result.lowest;
                root = newNode;
            }
            return true;
        }
    }

    private static boolean mergeNodes(InternalNode p, int x)
    {
        InternalNode a = (InternalNode)p.child[x];
        InternalNode b = (InternalNode)p.child[x + 1];
        b.low[0] = p.low[x + 1];

        int an = a.nChilds;
        int bn = b.nChilds;

        if (an + bn <= MAX_CHILD) {
            for (int i = 0; i < bn; i++) {
                a.child[i + an] = b.child[i];
                a.child[i] = null;
                a.low[i + an] = b.low[i];
            }
            a.nChilds += bn;
            return true;
        } else {
            int move;
            int n = (an + bn) / 2;
            if (an > n) {
                move = an - n;
                for (int i = bn - 1; i >= 0; i--) {
                    b.child[i + move] = b.child[i];
                    b.low[i + move] = b.low[i];
                }
                for (int i = 0; i < move; i++) {
                    b.child[i] = a.child[i + n];
                    a.child[i + n] = null;
                    b.low[i] = a.low[i + n];
                }
            } else {
                move = n - an;
                for (int i = 0; i < move; i++) {
                    a.child[i + an] = b.child[i];
                    a.low[i + an] = b.low[i];
                }
                for (int i = 0; i < bn - move; i++) {
                    b.child[i] = b.child[i + move];
                    b.child[i + move] = null;
                    b.low[i] = b.low[i + move];
                }
            }
            a.nChilds = n;
            b.nChilds = an + bn - n;
            p.low[x + 1] = b.low[0];
            return false;
        }
    }

    private enum Status {
        OK,
        OK_REMOVED,
        OK_NEED_REORG,
        NOT_FOUND
    }

    private static Status deleteAux(Node thisNode, Integer key)
    {
        if (thisNode instanceof Leaf) {
            Leaf leaf = (Leaf)thisNode;

            if (leaf.key.compareTo(key) == 0) {
                return Status.OK_REMOVED;
            } else {
                return Status.NOT_FOUND;
            }
        } else {
            InternalNode node = (InternalNode)thisNode;

            boolean joined = false;
            int pos = node.locateSubtree(key);
            Status result = deleteAux(node.child[pos], key);
            if (result == Status.NOT_FOUND || result == Status.OK) {
                return result;
            }

            if (result == Status.OK_NEED_REORG) {
                int sub = (pos == 0) ? 0 : pos - 1;
                joined = mergeNodes(node, sub);
                if (joined) {
                    pos = sub + 1;
                }
            }
            Status myResult = Status.OK;

            if (result == Status.OK_REMOVED || joined) {
                for (int i = pos; i < node.nChilds - 1; i++) {
                    node.child[i] = node.child[i + 1];
                    node.low[i] = node.low[i + 1];
                }
                node.child[node.nChilds - 1] = null;
                if (--node.nChilds < HALF_CHILD) {
                    myResult = Status.OK_NEED_REORG;
                }
            }
            return myResult;
        }
    }

    public boolean delete(Integer key)
    {
        currentLeaf = null;

        if (root == null) {
            return false;
        } else {
            Status result = deleteAux(root, key);

            if (result == Status.NOT_FOUND) {
                return false;
            }

            if (result == Status.OK_REMOVED) {
                root = null;
            } else if (result == Status.OK_NEED_REORG && ((InternalNode)root).nChilds == 1) {
                root = ((InternalNode)root).child[0];
            }
            return true;
        }
    }

    private static String toStringAux(Node p)
    {
        if (p instanceof Leaf) {
            Leaf l = (Leaf)p;
            return "Leaf #" + l.serial + " key=" + l.key;
        } else {
            InternalNode n = (InternalNode)p;
            String s = "Node #" + n.serial + " (" + n.nChilds + " childs): ";
            s += "#" + n.child[0].serial + " ";
            for (int i = 1; i < n.nChilds; i++) {
                s += "[" + n.low[i] + "] #" + n.child[i].serial + " ";
            }
            s += "\n";
            for (int i = 0; i < n.nChilds; i++) {
                s += toStringAux(n.child[i]) + "\n";
            }
            return s;
        }
    }

    public String toString()
    {
        if (root == null) {
            return "木は空です。";
        } else {
            return toStringAux(root);
        }
    }

    public static void main(String[] args) throws IOException
    {
        BTree tree = new BTree();

        int[] data = {1, 100, 27, 45, 3, 135, 13};
        for (int x: data) {
            tree.insert(x, "[" + x + "]");
        }

        System.out.print(">");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while ((str = input.readLine()) != null) {
            if (str.length() == 0) {
                System.out.print(">");
                continue;
            }

            char command = str.charAt(0);
            String arg = str.substring(1).trim();
            if (command == 'q') {
                break;
            } else if (command == 'p') {
                System.out.println(tree);
            } else if (command == '=') {
                if (tree.setData(arg)) {
                    System.out.println("値" + arg + "の設定に成功しました。");
                } else {
                    System.out.println("値" + arg + "の設定に失敗しました。");
                }
            } else if (command == '+' || command == '-' || command == '/') {
                int num = 0;
                try {
                    num = Integer.parseInt(arg);
                } catch (NumberFormatException e) {
                    System.err.println("整数以外の者が指定されました: " + arg);
                    continue;
                }

                if (command == '+') {
                    if (tree.insert(num, "[" + num + "]")) {
                        System.out.println(num + "の挿入に成功しました。");
                    } else {
                        System.out.println(num + "の挿入に失敗しました。");
                    }
                } else if (command == '-') {
                    if (tree.delete(num)) {
                        System.out.println(num + "の削除に成功しました。");
                    } else {
                        System.out.println(num + "の削除に失敗しました。");
                    }
                } else if (command == '/') {
                    if (tree.search(num)) {
                        System.out.println(num + "が見つかりました。値=" + tree.getData());
                    } else {
                        System.out.println(num + "が見つかりませんでした。");
                    }
                }
            } else {
                System.out.println("コマンド" + command + "はサポートされていません。");
            }
            System.out.print(">");
        }
    }
}
