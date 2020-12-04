package Grid;

import Grid.Cell.CellEmpty;
import Grid.Cell.InterfaceCell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class GridManager {
    private final WebDriver driver;

    public GridManager() {
        this.driver = new FirefoxDriver();
        driver.get("https://sudoku9x9.com/?level=2");
    }

    public Grid scrapGrid() throws Exception {
        Grid ret = new Grid();
        WebElement element;
        String value;
        InterfaceCell.CellValue[] values = InterfaceCell.CellValue.values();
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
        return ret;
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

    private WebElement getElementById(String id) {
        return this.driver.findElement(By.id(id));
    }
}
