package Grid;

import Grid.Cell.InterfaceCell;

public class GridSolver {

    public Grid solver(Grid grid) throws Exception {
        int x;

        for (int y = 0; y < Grid.HEIGHT; y++) {
            for (x = 0; x < Grid.WIDTH; x++) {
                if (grid.getCell(y, x).isValueIncremental() == 1) {
                    return this.solverMovingForward(grid.gridIncrementCell(y, x), y, x);
                }
            }
        }
        return grid;
    }

    private Grid solverMovingForward(final Grid grid, final int yEmpty, final int xEmpty) throws Exception {
        if (this.checkIfValid(grid, yEmpty, xEmpty)) {
            int x = 0;
            int y;

            for (y = yEmpty; y < Grid.HEIGHT; y++) {
                for (x = y == yEmpty ? xEmpty + 1 : 0; x < Grid.WIDTH; x++) {
                    if (grid.getCell(y, x).isValueIncremental() == 1) {
                        return this.solverMovingForward(grid.gridIncrementCell(y, x), y, x);
                    }
                }
            }
            if (y == Grid.HEIGHT && x == Grid.WIDTH) {
                return grid;
            }
        }
        return this.solverMoveBack(grid, yEmpty, xEmpty);
    }

    private Grid solverMoveBack(final Grid grid, final int yEmpty, final int xEmpty) throws Exception {
        InterfaceCell currentValue = grid.getCell(yEmpty, xEmpty);
        if (currentValue.isValueIncremental() == 1) {
            return this.solverMovingForward(grid.gridIncrementCell(yEmpty, xEmpty), yEmpty, xEmpty);
        }
        grid.gridSetAsEmptyCell(yEmpty, xEmpty);
        int x;
        int incrementValue;

        for (int y = yEmpty; y >= 0; y--) {
            for (x = y == yEmpty ? xEmpty : Grid.WIDTH - 1; x >= 0; x--) {
                if (y == yEmpty && x == xEmpty) {
                    continue;
                }
                if ((incrementValue = grid.getCell(y, x).isValueIncremental()) == 1) {
                    return this.solverMovingForward(grid.gridIncrementCell(y, x), y, x);
                } else if (incrementValue == 0) {
                    return this.solverMoveBack(grid, y, x);
                }
            }
        }
        return grid;
    }

    private boolean checkIfValid(final Grid grid, final int yEmpty, final int xEmpty) throws Exception {
        InterfaceCell currentValue = grid.getCell(yEmpty, xEmpty);
        return (this.checkBlock(grid, yEmpty, xEmpty, currentValue) &&
                this.checkColumn(grid, yEmpty, xEmpty, currentValue) &&
                this.checkLine(grid, yEmpty, xEmpty, currentValue));
    }

    private boolean checkBlock(final Grid grid, final int y, final int x, final InterfaceCell currentValue) throws Exception {
        int yCheckEnd = y - y % 3 + 3;
        int xCheckEnd = x - x % 3 + 3;
        int xCheckBase = x - x % 3;
        int xCurs;

        for (int yCurs = y - y % 3; yCurs < yCheckEnd; yCurs++) {
            for (xCurs = xCheckBase; xCurs < xCheckEnd; xCurs++) {
                if (yCurs == y && xCurs == x) {
                    continue;
                }
                if (currentValue.isEqual(grid.getCell(yCurs, xCurs))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkLine(final Grid grid, final int y, final int x, final InterfaceCell currentValue) throws Exception {
        for (int xCurs = 0; xCurs < Grid.WIDTH; xCurs++) {
            if (xCurs == x) {
                continue;
            }
            if (currentValue.isEqual(grid.getCell(y, xCurs))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(final Grid grid, final int y, final int x, final InterfaceCell currentValue) throws Exception {
        for (int yCurs = 0; yCurs < Grid.HEIGHT; yCurs++) {
            if (yCurs == y) {
                continue;
            }
            if (currentValue.isEqual(grid.getCell(yCurs, x))) {
                return false;
            }
        }
        return true;
    }
}
