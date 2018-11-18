/**
 * Any question, feel free to contact Xingyu Zhang.
 * Since we need only one instance of Game, here we use "singleton-pattern"
 */
public class Game {

    // the cells are used to hold the blocks
    private boolean[][] cells;
    private int rows;
    private int columns;

    // current moving block
    private ZBlock currentBlock;
    // the global position of the cells[0][0] of currentBlock
    private int posRow;
    private int posColumn;

    // create an instance of class Game
    private static Game game = new Game();


    /**
     * "private" makes sure that instance cannot be created by invoking constructor,
     * meaning that the "game" above will become the single instance.
     */
    private Game() {
    }


    /**
     * get the single instance
     * @return the single instance of class Game.
     */
    public static Game getInstance() {
        return game;
    }

    /**
     * initialize the cells with height and width, and set all cells to false.
     * YOU NEED TO:
     * assign rows and columns with height and width
     * initialize the cells and set all cells elements to false.
     * @param height: the rows of cells
     * @param width: the columns of cells
     */
    public void initial(int height, int width) {
        rows = height;
        columns = width;
        cells = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = false;
            }
        }
    }


    /**
     * focus on next block, invoked when the game started or the last block fell.
     * YOU NEED TO:
     * set posRow and posColumn to the position of 0 and columns / 2.
     * assign currentBlock with a new instance of ZBlock.
     */
    public void nextBlock() {
        posRow = 0;
        posColumn = columns / 2;
        currentBlock = new ZBlock();
    }

    /**
     * since posRow, posColumn, currentBlock will never collide with game cells except when nextBlock appeared,
     * we can invoke the method below to check whether the game is over.
     * @return whether the game is over
     */
    public boolean isOver() {
        return collide(posRow, posColumn, currentBlock.getCells());
    }

    /***
     * print the game UI
     */
    public void print() {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            stringBuffer.append("| ");
            for (int j = 0; j < columns; j++) {
                if (cells[i][j] ||
                        (i >= posRow && i < posRow + currentBlock.getRows() &&
                                j >= posColumn && j < posColumn + currentBlock.getColumns() &&
                                currentBlock.getCells()[i - posRow][j - posColumn])) {
                    stringBuffer.append("*");
                } else {
                    stringBuffer.append(" ");
                }
                stringBuffer.append(" ");
            }
            stringBuffer.append('|');
            stringBuffer.append("\n");
        }

        stringBuffer.append(" -");
        for (int j = 0; j < columns; j++) {
            stringBuffer.append("--");
        }
        stringBuffer.append('\n');

        stringBuffer.append("w: 旋转\ts: 下移\na: 左移\td: 右移\nq: 退出\n请输入你的指令:\n");

        System.out.println(stringBuffer.toString());
    }

    /***
     * execute next command, you may need to invoke collide() to check collision before rotate or move.
     * when the command is "s" and the block has moved to the bottom(collide with the state of row + 1),
     * loadBlock(), eliminate(), nextBlock() will be invoked sequentially.
     * @param command the next command {"w","s","a","d"}
     */
    public void executeCommand(String command) {
        switch (command) {
            case "w":
                if (!collide(posRow, posColumn, currentBlock.nextRotatedCells())) {
                    currentBlock.rotate();
                }
                break;
            case "a":
                if (!collide(posRow, posColumn - 1, currentBlock.getCells())) {
                    posColumn--;
                }
                break;
            case "d":
                if (!collide(posRow, posColumn + 1, currentBlock.getCells())) {
                    posColumn++;
                }
                break;
            case "s":
                if (!collide(posRow + 1, posColumn, currentBlock.getCells())) {
                    posRow++;
                } else {
                    loadBlock();
                    eliminate();
                    nextBlock();
                }
                break;
        }

    }

    /**
     * judge whether blockCells at position(nextRow, nextColumn) collide with game cells.
     * @param nextPosRow equal to posRow except when moving the block down.
     * @param nextPosColumn equal to posColumn except when moving the block left or right.
     * @param nextBlockCells equal to currentBlockCells except when rotate the block.
     * @return true for collied while false for not.
     */
    private boolean collide(int nextPosRow, int nextPosColumn, boolean[][] nextBlockCells) {
        for (int i = 0; i < nextBlockCells.length; i++) {
            for (int j = 0; j < nextBlockCells[0].length; j++) {
                if (nextBlockCells[i][j] &&
                        (nextPosRow + i >= rows || nextPosColumn + j < 0 || nextPosColumn + j >= columns ||
                                (nextPosRow + i >= 0 && cells[nextPosRow + i][nextPosColumn + j]))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * set the game cells occupied by currentBlock to "true"
     */
    private void loadBlock() {
        for (int i = 0; i < currentBlock.getRows(); i++) {
            for (int j = 0; j < currentBlock.getColumns(); j++) {
                if (currentBlock.getCells()[i][j] &&
                        posRow + i >= 0 && posRow + i < rows &&
                        posColumn + j >= 0 && posColumn + j < columns) {
                    cells[posRow + i][posColumn + j] = true;
                }
            }
        }
    }

    /***
     * eliminate all the "full" rows, in which full of "true".
     */
    private void eliminate() {
        for (int i = 0; i < rows; i++) {
            boolean rowElimate = true;
            for (int j = 0; j < columns; j++) {
                if (!cells[i][j]) {
                    rowElimate = false;
                    break;
                }
            }
            if (rowElimate) {
                eliminateOneRow(i);
            }
        }
    }

    /***
     * Force to eliminate one row, all the cells above will drop by one row.
     * @param row the row index of the eliminated
     */
    private void eliminateOneRow(int row) {
        for (int i = row; i >= 0; i--) {
            for (int j = 0; i < columns; j++) {
                if (i == 0) {
                    cells[i][j] = false;
                } else {
                    cells[i][j] = cells[i - 1][j];
                }
            }
        }
    }















}
