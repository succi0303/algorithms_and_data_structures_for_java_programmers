class BinaryTreeNode {
    String label;
    BinaryTreeNode left;
    BinaryTreeNode right;

    BinaryTreeNode(String label, BinaryTreeNode left, BinaryTreeNode right)
    {
        this.left = left;
        this.right = right;
        this.label = label;
    }

    static void traversePreorder(BinaryTreeNode p)
    {
        if (p == null) {
            return;
        }
        System.out.println(p.label);
        traversePreorder(p.left);
        traversePreorder(p.right);
    }

    static void traverseInorder(BinaryTreeNode p)
    {
        if (p == null) {
            return;
        }
        traverseInorder(p.left);
        System.out.println(p.label);
        traverseInorder(p.right);
    }

    static void traversePostorder(BinaryTreeNode p)
    {
        if (p == null) {
            return;
        }
        traversePostorder(p.left);
        traversePostorder(p.right);
        System.out.println(p.label);
    }

    public static void main(String args[])
    {
        BinaryTreeNode tree = new BinaryTreeNode(
                "a",
                new BinaryTreeNode("b",
                    new BinaryTreeNode("c", null, null),
                    null),
                new BinaryTreeNode("d", null, null)
                );

        System.out.println("preorder");
        traversePreorder(tree);

        System.out.println("inorder");
        traverseInorder(tree);

        System.out.println("postorder");
        traversePostorder(tree);
    }
}
