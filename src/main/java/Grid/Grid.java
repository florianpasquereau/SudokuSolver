package Grid;

import Grid.Cell.Cell;
import Grid.Cell.CellEmpty;
import Grid.Cell.InterfaceCell;

public class Grid {
    public static final int WIDTH = 9;
    public static final int HEIGHT = 9;

    InterfaceCell[][] cells = new Cell[Grid.HEIGHT][Grid.WIDTH];

    public void addCell(int y, int x, InterfaceCell.CellValue value) throws Exception {
        this.addCellCheck(y, x);
        this.cells[y][x] = new Cell(value);
    }

    public void addEmptyCell(int y, int x) throws Exception {
        this.addCellCheck(y, x);
        this.cells[y][x] = new CellEmpty(InterfaceCell.CellValue.EMPTY);
    }

    private void addCellCheck(int y, int x) throws Exception {
        if (!(y >= 0 && y < Grid.HEIGHT) || !(x >= 0 && x < Grid.WIDTH)) {
            throw new Exception("Coordinate : y[" + y + "]x[" + x + "] is not valid");
        } else if (this.cells[y][x] != null) {
            throw new Exception("Coordinate : y[" + y + "]x[" + x + "] is not empty");
        }
    }

    public Grid gridIncrementCell(int y, int x) throws Exception {
        if (!(this.emptyCell(y, x))) {
            throw new Exception("The cell[" + y + "][" + x + "] in not a " + CellEmpty.class + " object");
        } else if (this.getCell(y,x).isValueIncremental() != 1) {
            throw new Exception("The cell[" + y + "][" + x + "] in not incremental");
        }
        ((CellEmpty)this.getCell(y,x)).setValueIncrement();
        return this;
    }

    public void gridSetAsEmptyCell(int y, int x) throws Exception {
        if (!(this.emptyCell(y, x))) {
            throw new Exception("The cell[" + y + "][" + x + "] in not a " + CellEmpty.class + " object");
        }
        ((CellEmpty)this.getCell(y,x)).setAsEmpty();
    }

    public InterfaceCell getCell(int y, int x) throws Exception {
        if (y < 0 || y > Grid.HEIGHT || x < 0 || x > Grid.WIDTH) {
            throw new Exception("Grid[" + y + "][" + x + "] in not accessible");
        }
        return this.cells[y][x];
    }



    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        int x;
        for(int y = 0; y < Grid.HEIGHT; y++) {
            if (y != 0 && (y % 3) == 0) {
                ret.append("---------------------\n");
            }
            for (x = 0; x < Grid.WIDTH; x++) {
                if (x != 0 && (x % 3) == 0) {
                    ret.append("| ");
                }
                ret.append(this.cells[y][x]);

            }
            ret.append("\n");
        }
        return ret.toString();
    }

    public boolean emptyCell(int y, int x) throws Exception {
        return this.getCell(y,x) instanceof CellEmpty;
    }
}
