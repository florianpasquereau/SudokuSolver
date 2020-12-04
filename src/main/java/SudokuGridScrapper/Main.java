package SudokuGridScrapper;

import Grid.Grid;
import Grid.GridManager;
import Grid.GridSolver;

import java.time.Duration;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] argv) throws Exception {
        GridManager gridManager = new GridManager();
        Grid grid;
        GridSolver gridSolver = new GridSolver();
        LocalDateTime start;
        Duration period;
        Long average = 0L;

        for (int i = 0; i < 5; i++) {
            start = LocalDateTime.now();
            grid = gridSolver.solver(gridManager.scrapGrid());
            period = Duration.between(start, LocalDateTime.now());
            average += period.toMillis();
            gridManager.putGrid(grid, period, i);
            gridManager.refreshPage();
        }
        gridManager.close();
        System.out.println("Average time : " + (average / 100) + "ms");
    }
}
