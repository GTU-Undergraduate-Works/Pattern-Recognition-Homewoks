import java.util.Random;

public class TreeNode  {


    protected NodeType nodeType;

    protected TreeNode left;

    protected TreeNode right;

    public TreeNode(NodeType nodeType) {
        this.nodeType = nodeType;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return nodeType.toString();
    }

    public static TreeNode generateRandomTerminalNode() {
        Random random = new Random();
        int rand = random.nextInt(3);
        if (rand == 0) return new TreeNode(TerminalType.MOVE_FORWARD);
        else if (rand == 1) return new TreeNode(TerminalType.TURN_LEFT);
        return new TreeNode(TerminalType.TURN_RIGHT);
    }

    public static TreeNode generateRandomFunctionNode() {
        Random random = new Random();
        int rand = random.nextInt(5);
        if (rand == 0) return new TreeNode(FunctionType.IF_FOOD_AHEAD);
        else if (rand == 1) return new TreeNode(FunctionType.IF_DANGER_AHEAD);
        else if (rand == 2) return new TreeNode(FunctionType.IF_DANGER_LEFT);
        else if (rand == 3) return new TreeNode(FunctionType.IF_DANGER_RIGHT);
        return new TreeNode(FunctionType.PROGN2);
    }

    public static TreeNode generateRandomNode() {
        Random random = new Random();
        int rand = random.nextInt(8);
        if (rand == 0) return new TreeNode(TerminalType.MOVE_FORWARD);
        else if (rand == 1) return new TreeNode(TerminalType.TURN_LEFT);
        else if (rand == 2) return new TreeNode(TerminalType.TURN_RIGHT);
        else if (rand == 3) return new TreeNode(FunctionType.IF_FOOD_AHEAD);
        else if (rand == 4) return new TreeNode(FunctionType.IF_DANGER_AHEAD);
        else if (rand == 5) return new TreeNode(FunctionType.IF_DANGER_LEFT);
        else if (rand == 6) return new TreeNode(FunctionType.IF_DANGER_RIGHT);
        return new TreeNode(FunctionType.PROGN2);
    }

    public boolean isTerminal() {
        return (this.nodeType instanceof TerminalType);
    }

    public boolean isFunction() {
        return (this.nodeType instanceof FunctionType);
    }
}

