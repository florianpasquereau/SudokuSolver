package SudokuGridScrapper;

import Grid.Grid;
import Grid.GridManager;
import Grid.GridSolver;

import java.time.Duration;
import java.time.LocalDateTime;

public class Main {

    private static void helper() {
        System.out.println("./SudokuSolver <number loop>");
        System.out.println("<number loop> must be positive");
        System.exit(-1);
    }

    private static void loop(int numberLoop) throws Exception {
        GridManager gridManager = new GridManager();
        Grid grid;
        GridSolver gridSolver = new GridSolver();
        LocalDateTime start;
        Duration period;
        Long average = 0L;

        for (int i = 0; i < numberLoop; i++) {
            start = LocalDateTime.now();
            grid = gridSolver.solver(gridManager.scrapGrid());
            period = Duration.between(start, LocalDateTime.now());
            average += period.toMillis();
            gridManager.putGrid(grid, period, i);
            gridManager.refreshPage();
        }
        gridManager.close();
        System.out.println("Average time : " + (average / numberLoop) + "ms");
    }

    public static void main(String[] argv) throws Exception {
        int numberLoop = 0;

        if (argv.length <= 0 || (numberLoop = Integer.parseInt(argv[0])) <= 0) {
            Main.helper();
        }
        Main.loop(numberLoop);
    }
}
