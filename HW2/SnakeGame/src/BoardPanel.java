import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {


    private final int DELAY = 140;

    private final int POPULATION_SIZE = 100;

    private int generation = 0;
    private final int MAX_ITER = 500;
    private int currentIter = 0;

    private Image ball;
    private Image apple;
    private Image head;

    private Board board;


    public BoardPanel() {
        board = new Board();
        initBoard();
    }

    private void initBoard() {

        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(board.B_WIDTH, board.B_HEIGHT));
        loadImages();
        initGame();




    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("resources/dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("resources/apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("resources/head.png");
        head = iih.getImage();
    }

    private void initGame() {


        board.initGame();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Program[] population = new Program[POPULATION_SIZE];
                for (int i = 0; i < POPULATION_SIZE; ++i)
                    population[i] = new Program();


                for (; ;) {

                    for (int i = 0; i < POPULATION_SIZE; ++i)
                        population[i].executeProgram();



                    sortProgramsByFitness(population, 0, POPULATION_SIZE-1);


                    int s =  (10*POPULATION_SIZE)/100;
                    Program[] newGeneration = new Program[POPULATION_SIZE];

                    for(int i = 0;i<s;i++)
                        newGeneration[i] = population[i];


                    for(int i = s;i < POPULATION_SIZE;i++)  {

                        Random random = new Random();
                        int r = random.nextInt(50);
                        Program parent1 = population[r];
                        r = random.nextInt(50);
                        Program parent2 = population[r];
                        Program offspring = parent1.crossover(parent2);
                        offspring.mutaion();
                        newGeneration[i] = offspring;
                    }
                    population = newGeneration;
                    executeProgram(population[0]);
                    restart();

                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }



            }
        }).start();
    }



    public int getGeneration() {
        return generation;
    }

    public int getScore() {
        return board.score;
    }

    private void sortProgramsByFitness(Program[] population, int l, int r) {


        if (l < r)
        {

            int m = (l+r)/2;

            sortProgramsByFitness(population, l, m);
            sortProgramsByFitness(population , m+1, r);
            merge(population, l, m, r);
        }
    }

    private void merge(Program[] population, int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        Program[] L = new Program[n1];
        Program[] R = new Program[n2];



        for (int i = 0; i < n1; ++i)
            L[i] = population[l + i];

        for (int j = 0; j < n2; ++j)
            R[j] = population[m + 1+ j];

        int i = 0, j = 0;

        int k = l;

        while (i < n1 && j < n2)
        {
            if (L[i].getFitness() >= R[j].getFitness())
            {
                population[k] = L[i];
                i++;
            }
            else
            {
                population[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            population[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            population[k] = R[j];
            j++;
            k++;
        }


    }

    private void restart() {
        board.reStartBoard();
        repaint();
    }



    private void execute(BinaryTree binaryTree) {

        if (binaryTree.root == null)
            return;

        if (binaryTree.root.isTerminal()) {
            if (!executeCommand((TerminalType) binaryTree.root.nodeType)) return;
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



    private void executeProgram(Program program) {
        while (currentIter < MAX_ITER && board.inGame) execute(program.binaryTree);
        currentIter = 0;
        System.out.println(generation);
        generation++;
        repaint();
        restart();
    }

    private boolean executeCommand(TerminalType commad) {

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        board.executeCommand(commad);

        if (board.inGame) {
            board.checkApple();
            board.checkCollision();
            board.move();
            currentIter++;
            repaint();
            return true;
        }
        repaint();
        return false;


    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        if (board.inGame) {

            g.drawImage(apple, board.apple_x, board.apple_y, this);

            for (int z = 0; z < board.dots; z++) {
                if (z == 0) {
                    g.drawImage(head, board.x[z], board.y[z], this);
                } else {
                    g.drawImage(ball, board.x[z], board.y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        }
    }


}