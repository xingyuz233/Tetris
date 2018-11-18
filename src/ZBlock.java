/**
 * class ZBlock, owns 3*3 cells in this lab.
 */
public class ZBlock {
    // use cells to represent the shape of ZBlock
    private boolean cells[][];
    // rows of cells
    private int rows;
    // columns of cells
    private int columns;

    // get and set methods

    public boolean[][] getCells() {
        return cells;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setCells(boolean[][] cells) {
        this.cells = cells;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Constructor of ZBlock, initialize cells with a shape of "Z".
     */
    public ZBlock() {
        rows = 3;
        columns = 3;
        cells = new boolean[][]{
                {true, true, false},
                {false, true, true},
                {false, false, false}
        };
    }

    /**
     * Get the next cells rotated 90 degrees from current cells.
     * This method will not change the property "cells".
     * @return the rotated cells of current cells
     */
    public boolean[][] nextRotatedCells() {
        boolean[][] nextCells = new boolean[rows][columns];
        int centerRow = (rows - 1) / 2;
        int centerColumn = (columns - 1) / 2;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                nextCells[i][j] = cells[j - centerRow + centerColumn][centerRow + centerColumn - i];
            }
        }
        return nextCells;
    }

    /**
     * rotate cells for 90 degrees.
     */
    public void rotate() {
        cells = nextRotatedCells();
    }



}
