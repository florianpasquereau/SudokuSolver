package Grid.Cell;

public class CellEmpty extends Cell{
    public CellEmpty(CellValue cellValue) {
        super(cellValue);
    }

    public void setCellValue(CellValue cellValue) {
        this.cellValue = cellValue;
    }

    public void setValueIncrement() throws Exception {
        int valueIncrement;
        if ((valueIncrement = this.isValueIncremental()) != 1) {
            throw new Exception("InterfaceCellValue index cannot be higher than" + InterfaceCell.CELL_VALUE_SIZE + " valueIncrement = " + valueIncrement);
        }
        int index = this.cellValue.ordinal() + 1;
        this.setCellValue(CellValue.values()[index]);
    }

    @Override
    public int isValueIncremental() {
        int index = this.cellValue.ordinal() + 1;
        return (index >= InterfaceCell.CELL_VALUE_SIZE) ? 0 : 1;
    }

    public void setAsEmpty() {
        this.setCellValue(CellValue.EMPTY);
    }

    @Override
    public String toString() {
        return "\033[0;91m" + super.toString() + "\033[0m";
    }
}
