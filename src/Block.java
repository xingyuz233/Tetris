/**
 * class Block, owns 3*3 cells in this lab.
 */
public abstract class Block {
    // use cells to represent the shape of ZBlock
    protected boolean cells[][];
    // rows of cells
    protected int rows;
    // columns of cells
    protected int columns;

    // rowIndex of rotateCenter
    protected float rotateCenterRow;
    // columnIndex of rotateCenterColumn
    protected float rotateCenterColumn;

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
    public Block(int rows, int columns, boolean[][] cells, float rotateCenterRow, float rotateCenterColumn) {
        this.rows = rows;
        this.columns = columns;
        this.cells = cells;
        this.rotateCenterRow = rotateCenterRow;
        this.rotateCenterColumn = rotateCenterColumn;
    }

    /**
     * Get the next cells rotated 90 degrees from current cells.
     * This method will not change the property "cells".
     * @return the rotated cells of current cells
     */
    public boolean[][] nextRotatedCells() {

        boolean[][] nextCells = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                nextCells[i][j] = cells[(int)(j - rotateCenterRow + rotateCenterColumn)][(int)(rotateCenterRow + rotateCenterColumn - i)];
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
