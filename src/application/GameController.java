package application;

import javafx.scene.shape.*;

public class GameController {

    // Get numbers and grid from ClassicEasyMain class
    public static final int MOVE = ClassicEasyMain.MOVE;
    public static final int SIZE = ClassicEasyMain.SIZE;
    public static final int XMAX = ClassicEasyMain.XMAX;
    public static final int YMAX = ClassicEasyMain.YMAX;
    public static int[][] grid = ClassicEasyMain.grid;

    // Creating the squares/blocks that compose the tetromino
    public static Tetromino makeRect() {

        // Pick random color between 0-100
        int randNum = (int) (Math.random() * 100);
        String pieceName;

        Rectangle[] blocks = new Rectangle[4];
        for (int i = 0; i < blocks.length; i++) {
            // Subtract 1 from size to create border/whitespace between each square so each individual square can be seen in tetromino
            blocks[i] = new Rectangle(SIZE - 1, SIZE - 1);
        }

        // If randomly generated number is less than 15, generate "j" piece
        if (randNum < 15) {
            pieceName = "j";
            blocks[0].setX(XMAX / 2 - SIZE);
            blocks[1].setX(XMAX / 2 - SIZE);
            blocks[1].setY(SIZE);
            blocks[2].setX(XMAX / 2);
            blocks[2].setY(SIZE);
            blocks[3].setX(XMAX / 2 + SIZE);
            blocks[3].setY(SIZE);

        } else if (randNum < 30) {
            pieceName = "l";
            blocks[0].setX(XMAX / 2 + SIZE);
            blocks[1].setX(XMAX / 2 - SIZE);
            blocks[1].setY(SIZE);
            blocks[2].setX(XMAX / 2);
            blocks[2].setY(SIZE);
            blocks[3].setX(XMAX / 2 + SIZE);
            blocks[3].setY(SIZE);

        } else if (randNum < 45) {
            pieceName = "o";
            blocks[0].setX(XMAX / 2 - SIZE);
            blocks[1].setX(XMAX / 2);
            blocks[2].setX(XMAX / 2 - SIZE);
            blocks[2].setY(SIZE);
            blocks[3].setX(XMAX / 2);
            blocks[3].setY(SIZE);

        } else if (randNum < 60) {
            pieceName = "s";
            blocks[0].setX(XMAX / 2 + SIZE);
            blocks[1].setX(XMAX / 2);
            blocks[2].setX(XMAX / 2);
            blocks[2].setY(SIZE);
            blocks[3].setX(XMAX / 2 - SIZE);
            blocks[3].setY(SIZE);

        } else if (randNum < 75) {
            pieceName = "t";
            blocks[0].setX(XMAX / 2 - SIZE);
            blocks[1].setX(XMAX / 2);
            blocks[2].setX(XMAX / 2);
            blocks[2].setY(SIZE);
            blocks[3].setX(XMAX / 2 + SIZE);

        } else if (randNum < 90) {
            pieceName = "z";
            blocks[0].setX(XMAX / 2 + SIZE);
            blocks[1].setX(XMAX / 2);
            blocks[2].setX(XMAX / 2 + SIZE);
            blocks[2].setY(SIZE);
            blocks[3].setX(XMAX / 2 + SIZE + SIZE);
            blocks[3].setY(SIZE);

        } else {
            pieceName = "i";
            blocks[0].setX(XMAX / 2 - SIZE - SIZE);
            blocks[1].setX(XMAX / 2 - SIZE);
            blocks[2].setX(XMAX / 2);
            blocks[3].setX(XMAX / 2 + SIZE);

        }

        // Calls Tetromino constructor to create tetromino object
        return new Tetromino(blocks, pieceName);
    }

    public static boolean isOutside(Rectangle[] blocks) {
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].getX() + MOVE > XMAX - SIZE) {
                return true;
            }
        }
        return false;
    }

    public static boolean isGridCellEmpty(int moveLocation) {
        if (moveLocation == 0) {
            return true;
        }
        return false;
    }

    // Moving the blocks
    public static void move(Tetromino tetromino, String direction) {
        // Checks to see if space tetromino is going to move to is outside of limit, if not then proceed
        if (isOutside(tetromino.blocks)) {
            boolean moveable = true;
            int xLocation = 0;
            // Check to see if spot to move in the grid is empty (0) or occupied (1)
            for (int i = 0; i < tetromino.blocks.length; i++) {
                if (direction == "right") {
                    xLocation = ((int) tetromino.blocks[i].getX() / SIZE) + 1;
                } else if (direction == "left") {
                    xLocation = ((int) tetromino.blocks[i].getX() / SIZE) - 1;
                }
                if (grid[xLocation][((int) tetromino.blocks[i].getY() / SIZE)] == 1) {
                    moveable = false;
                    break;
                }
            }

            // If spot is empty, move all blocks
            if (moveable) {
                double moveLocation = 0;

                for (int i = 0; i < tetromino.blocks.length; i++) {
                    if (direction == "right") {
                        moveLocation = tetromino.blocks[i].getX() + MOVE;
                    } else if (direction == "left") {
                        moveLocation = tetromino.blocks[i].getX() - MOVE;
                    }
                    tetromino.blocks[i].setX(moveLocation);
                }
            }
        }
    }

}
