public class BinaryTree {

    protected TreeNode root;

    private static final int MAX_TREE_DEPTH = 10;


    public BinaryTree(TreeNode treeNode) {
        this.root = treeNode;
    }

    public BinaryTree(TreeNode root, BinaryTree left, BinaryTree right) {
        this.root = root;
        if (left != null)
            root.left = left.root;
        else
            root.left = null;

        if (right != null)
            root.right = right.root;
        else
            root.right = null;
    }



    public static TreeNode generateTreeWithGrowMethod(int depth) {
        TreeNode node = null;
        if (depth < MAX_TREE_DEPTH) {
            node = TreeNode.generateRandomNode();
            if (node.isFunction()) {
                node.left = generateTreeWithGrowMethod(depth+1);
                node.right = generateTreeWithGrowMethod(depth+1);
            }
        } else node = TreeNode.generateRandomTerminalNode();
        return node;
    }

    public static TreeNode generateTreeWithFullMethod(int depth) {
        TreeNode node = null;
        if (depth < MAX_TREE_DEPTH) {
            node = TreeNode.generateRandomFunctionNode();
            node.left = generateTreeWithFullMethod(depth+1);
            node.right = generateTreeWithFullMethod(depth+1);
        } else node = TreeNode.generateRandomTerminalNode();
        return node;
    }

    public BinaryTree getLeftSubtree() {
        if (root != null && root.left != null)
            return new BinaryTree(root.left);
        else
            return null;
    }

    public BinaryTree getRightSubtree() {
        if (root != null && root.right != null)
            return new BinaryTree((root.right));
        else
            return null;
    }

    public TreeNode getRoot() {
        return root;
    }

    public boolean isLeaf() {
        return (root == null || (root.left == null && root.right == null));
    }

}
