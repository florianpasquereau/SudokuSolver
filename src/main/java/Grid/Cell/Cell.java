package Grid.Cell;

public class Cell implements InterfaceCell{
    protected CellValue cellValue;


    public Cell(CellValue cellValue) {
        this.cellValue = cellValue;
    }

    public CellValue getCellValue() {
        return cellValue;
    }

    public int isValueIncremental() {
        return -1;
    }

    @Override
    public boolean isEqual(InterfaceCell cell) {
        return this.getCellValue() == cell.getCellValue();
    }

    @Override
    public boolean isEmpty() {
        return this.getCellValue() == CellValue.EMPTY;
    }

    @Override
    public String toString() {
        return this.getCellValue().ordinal() + " ";
    }
}
