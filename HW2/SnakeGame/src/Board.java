public class Board {

    protected final int B_WIDTH = 300;
    protected final int B_HEIGHT = 300;
    protected final int DOT_SIZE = 10;
    protected final int ALL_DOTS = 900;
    protected final int RAND_POS = 29;


    protected final int x[] = new int[ALL_DOTS];
    protected final int y[] = new int[ALL_DOTS];

    protected int dots;
    protected int score = 0;
    protected int apple_x;
    protected int apple_y;

    protected boolean leftDirection = false;
    protected boolean rightDirection = true;
    protected boolean upDirection = false;
    protected boolean downDirection = false;
    protected boolean inGame = true;



    public Board() {}


    protected void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();

    }


    protected void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            score++;
            dots++;
            locateApple();
        }
    }

    protected void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    protected void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }

    }

    protected void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    protected void reStartBoard() {
        leftDirection = false;
        rightDirection = true;
        upDirection = false;
        downDirection = false;
        score = 0;
        inGame = true;
        initGame();
    }


    public boolean ifFoodAhead() {

        if (apple_x == x[0] && apple_y == y[0]) return true;

        if (apple_x == x[0] ) {
            if (upDirection) return apple_y <= y[0];
            else if (downDirection) return apple_y >= y[0];

        } else if (apple_y == y[0]) {
            if (rightDirection) return apple_x >= x[0];
            else if (leftDirection) return apple_x <= x[0];
        }
        return false;
    }

    public boolean ifDangerAhead() {

        for (int z = dots; z > 0; z--) {
            if (z > 4) {
                if (upDirection) {
                    return (y[0] == y[z] || (y[0] - DOT_SIZE) == y[z]);
                } else if (downDirection) {
                    return (y[0] == y[z] || (y[0] + DOT_SIZE) == y[z]);
                } else if (leftDirection) {
                    return  (x[0] == x[z] || (x[0] - DOT_SIZE) == x[z]);
                } else {
                    if (x[0] == x[z] || (x[0] + DOT_SIZE) == x[z]) return true;
                }
            }
        }
        if (upDirection) {
            return  (y[0] < 0 || (y[0] - DOT_SIZE) < 0) ;
        }

        else if (downDirection) {
            return  (y[0] >= B_HEIGHT || (y[0] + DOT_SIZE) >= B_HEIGHT);

        } else if (rightDirection) {
            return  (x[0] >= B_WIDTH || (x[0] + DOT_SIZE) >= B_WIDTH);

        } else {
            return  (x[0] < 0 || (x[0] - DOT_SIZE) < 0);
        }

    }

    public boolean ifDangerleft() {

        for (int z = dots; z > 0; z--) {
            if (z > 4) {
                if (upDirection) {
                    return (x[0] - DOT_SIZE) == x[z];
                } else if (downDirection) {
                    return (x[0] + DOT_SIZE) == x[z];
                } else if (leftDirection) {
                    return (y[0] + DOT_SIZE) == y[z];
                } else {
                    if ((y[0] - DOT_SIZE) == y[z]) return true;
                }


            }
        }
        if (upDirection) {
            return (x[0] < 0 || (x[0] - DOT_SIZE) < 0);
        } else if (downDirection) {
            return (x[0] >= B_WIDTH || (x[0] + DOT_SIZE) >= B_WIDTH);
        } else if (leftDirection) {
            return (y[0] >= B_HEIGHT ||(y[0] + DOT_SIZE) >= B_HEIGHT);
        } else {
            return (y[0] < 0 || (y[0] - DOT_SIZE) < 0);
        }
    }

    public boolean ifDengerRight() {

        for (int z = dots; z > 0; z--) {
            if (z > 4) {
                if (upDirection) {
                    return (x[0] + DOT_SIZE) == x[z];
                } else if (downDirection) {
                    return (x[0] - DOT_SIZE) == x[z];
                } else if (leftDirection) {
                    return (y[0] - DOT_SIZE) == y[z];
                } else {
                    if ((y[0] + DOT_SIZE) == y[z]) return true;
                }


            }
        }
        if (downDirection) {
            return (x[0] < 0 || (x[0] - DOT_SIZE) < 0);
        } else if (upDirection) {
            return (x[0] >= B_WIDTH || (x[0] + DOT_SIZE) >= B_WIDTH);
        } else if (rightDirection) {
            return (y[0] >= B_HEIGHT ||(y[0] + DOT_SIZE) >= B_HEIGHT);
        } else {
            return (y[0] < 0 || (y[0] - DOT_SIZE) < 0);
        }
    }


    public void executeCommand(TerminalType command) {
        if (command == TerminalType.MOVE_FORWARD) return;
        if (leftDirection) {
            leftDirection = false;
            if (command == TerminalType.TURN_LEFT) downDirection = true;
            else upDirection = true;
        } else if (rightDirection) {
            rightDirection = false;
            if (command == TerminalType.TURN_LEFT) upDirection = true;
            else downDirection = true;
        } else if (upDirection) {
            upDirection = false;
            if (command == TerminalType.TURN_LEFT) leftDirection = true;
            else rightDirection = true;
        } else {
            downDirection = false;
            if (command == TerminalType.TURN_LEFT) rightDirection = true;
            else leftDirection = true;
        }
    }
}
