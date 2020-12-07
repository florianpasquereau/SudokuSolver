package Grid;

import Grid.Cell.Cell;
import Grid.Cell.CellEmpty;
import Grid.Cell.InterfaceCell;
import Grid.Cell.InterfaceCell.CellValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.ResourceBundle;

public class GridManager extends GridActions {
    private final WebDriver driver;

    public GridManager() {
        this.driver = new FirefoxDriver();
        ResourceBundle bundle = ResourceBundle.getBundle("GridManager");
        driver.get(bundle.getString("grid_source_url"));
    }

    public Grid scrapGrid() throws Exception {
        Grid ret = new Grid();
        WebElement element;
        String value;
        CellValue[] values = CellValue.values();
        int y;
        int x;

        for (int idCell = 0; idCell <= 80; idCell++) {
            element = this.getElementById("c" + idCell);
            y = idCell / Grid.WIDTH;
            x = idCell % Grid.HEIGHT;
            value = element.getText();
            if (!value.isEmpty()) {
                ret.addCell(y, x, values[Integer.parseInt(value)]);
            } else {
                ret.addEmptyCell(y, x);
            }
        }
        return this.gridPurged(ret);
    }

    public void putGrid(final Grid grid, final Duration period, final int index) throws Exception {
        int y;
        int x;
        WebElement element;
        System.out.println("Time[" + index + "] : " + period.toMillis() + "ms");
        System.out.println(grid);
        InterfaceCell cell;

        for (int idCell = 0; idCell <= 80; idCell++) {
            element = this.getElementById("c" + idCell);
            y = idCell / Grid.WIDTH;
            x = idCell % Grid.HEIGHT;
            if ((cell = grid.getCell(y, x)) instanceof CellEmpty) {
                element.sendKeys(Integer.toString(cell.getCellValue().ordinal()));
            }
        }
    }

    public void refreshPage() throws InterruptedException {
        WebElement element = this.getElementById("newpuzzle");
        element.click();
        Thread.sleep(1000);
    }

    public void close() throws InterruptedException {
        Thread.sleep(2000);
        this.driver.close();
    }

    private Grid gridPurged(Grid grid) throws Exception {
        int x;
        InterfaceCell cell;
        CellValue value;

        for(int y = 0; y < Grid.HEIGHT; y++) {
            for (x = 0; x < Grid.WIDTH; x++) {
                if ((cell = grid.getCell(y, x)) instanceof Cell) {
                    value = cell.getCellValue();
                    this.gridPurgedLine(grid, value, y);
                    this.gridPurgedColumn(grid, value, x);
                    this.gridPurgedBlock(grid, value, y, x);
                }
            }
        }
        return grid;
    }

    private void gridPurgedColumn(Grid grid, final CellValue value, final int x) throws Exception {
        for (int y = 0; y < Grid.HEIGHT; y++) {
            this.gridPurgeCell(grid, value, y, x);
        }
    }

    private void gridPurgedLine(Grid grid, final CellValue value, final int y) throws Exception {
        for (int x = 0; x < Grid.HEIGHT; x++) {
            this.gridPurgeCell(grid, value, y, x);
        }
    }

    private void gridPurgedBlock(Grid grid, final CellValue value, final int y, final int x) throws Exception {
        int yCheckEnd = y - y % 3 + 3;
        int xCheckEnd = x - x % 3 + 3;
        int xCheckBase = x - x % 3;
        int xCurs;

        for (int yCurs = y - y % 3; yCurs < yCheckEnd; yCurs++) {
            for (xCurs = xCheckBase; xCurs < xCheckEnd; xCurs++) {
                this.gridPurgeCell(grid, value, yCurs, xCurs);
            }
        }
    }

    private void gridPurgeCell(Grid grid, final CellValue value, final int y, final int x) throws Exception {
        InterfaceCell cell = grid.getCell(y, x);
        if (cell instanceof CellEmpty) {
            ((CellEmpty)cell).removePossibility(value);
        }
    }


    private WebElement getElementById(String id) {
        return this.driver.findElement(By.id(id));
    }
}
