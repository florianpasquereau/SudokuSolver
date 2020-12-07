package Grid;

import Grid.Cell.InterfaceCell;

abstract public class GridActions {

    protected boolean checkBlock(final Grid grid, final int y, final int x, final InterfaceCell currentValue) throws Exception {
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

    protected boolean checkLine(final Grid grid, final int y, final int x, final InterfaceCell currentValue) throws Exception {
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

    protected boolean checkColumn(final Grid grid, final int y, final int x, final InterfaceCell currentValue) throws Exception {
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
