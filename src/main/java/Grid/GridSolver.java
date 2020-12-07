package Grid;

import Grid.Cell.InterfaceCell;

public class GridSolver extends GridActions{

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
}
