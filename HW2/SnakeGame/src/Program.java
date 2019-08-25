import java.util.Random;

public class Program {


    private final int MAX_ITER = 500;
    private Board board;
    private int fitness = 0;
    protected BinaryTree binaryTree;
    private int currentIter = 0;



    public Program() {
        board = new Board();
        binaryTree = new BinaryTree(BinaryTree.generateTreeWithFullMethod(0));

    }


    public Program(BinaryTree binaryTree, Board board) {
        this.binaryTree = binaryTree;
        this.board = board;
    }

    public Program(Board board) {
        this.board = board;
        binaryTree = new BinaryTree(BinaryTree.generateTreeWithFullMethod(0));
    }



    public Program crossover(Program other) {
        TreeNode temp = this.binaryTree.root.left;
        this.binaryTree.root.left = other.binaryTree.root.right;
        other.binaryTree.root.right = temp;
        Random random = new Random();
        int rand = random.nextInt(2);
        if (rand == 0) return this;
        return other;
    }

    public void mutaion() {
        Random random = new Random();
        int rand = random.nextInt(2);
        if (rand == 0) binaryTree.root.left = BinaryTree.generateTreeWithFullMethod(0);
        else  binaryTree.root.left = BinaryTree.generateTreeWithFullMethod(0);
    }

    public void executeProgram() {
        board.reStartBoard();
        currentIter = 0;
        while (currentIter < MAX_ITER && board.inGame) execute(binaryTree);
        fitness = board.score;
    }


    private void execute(BinaryTree binaryTree) {

        if (binaryTree.root == null)
            return;

        if (binaryTree.root.isTerminal()) {
            executeCommand((TerminalType) binaryTree.root.nodeType);
            currentIter++;
        } else {

            if (binaryTree.root.nodeType == FunctionType.IF_FOOD_AHEAD) {


                if (board.ifFoodAhead())
                    execute(binaryTree.getLeftSubtree());
                else
                    execute(binaryTree.getRightSubtree());

            } else if (binaryTree.root.nodeType == FunctionType.IF_DANGER_AHEAD) {

                if (board.ifDangerAhead())
                    execute(binaryTree.getLeftSubtree());
                else
                    execute(binaryTree.getRightSubtree());

            }  else if (binaryTree.root.nodeType == FunctionType.IF_DANGER_LEFT) {

                if (board.ifDangerleft())
                    execute(binaryTree.getLeftSubtree());
                else
                    execute(binaryTree.getRightSubtree());


            } else if (binaryTree.root.nodeType == FunctionType.IF_DANGER_RIGHT) {

                if (board.ifDengerRight())
                    execute(binaryTree.getLeftSubtree());
                else
                    execute(binaryTree.getRightSubtree());

            } else {
                execute(binaryTree.getLeftSubtree());
                execute(binaryTree.getRightSubtree());
            }
        }
    }


    private void executeCommand(TerminalType command) {

        board.executeCommand(command);
        if (board.inGame) {
            board.checkApple();
            board.checkCollision();
            board.move();
            currentIter++;
        }

    }

    public int getFitness() {
        return fitness;
    }
}
