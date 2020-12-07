package Grid.Cell;

import java.util.HashMap;
import java.util.Set;

public class CellEmpty extends Cell{
    HashMap<Integer, CellValue> possibilities;
    Integer indexPossibility = -1;
    Set<Integer> keys;

    public CellEmpty(CellValue cellValue) {
        super(cellValue);
        this.possibilities = new HashMap<>();
        for (CellValue value : CellValue.values()) {
            this.possibilities.put(value.ordinal(), value);
        }
    }

    public void removePossibility(final CellValue cellValue) {
        this.possibilities.remove(cellValue.ordinal());
    }

    public void setCellValue(CellValue cellValue) {
        this.cellValue = cellValue;
    }

    public void setValueIncrement() throws Exception {
        int valueIncrement;
        this.initKeys();
        if ((valueIncrement = this.isValueIncremental()) != 1) {
            throw new Exception("InterfaceCellValue index cannot be higher than" + InterfaceCell.CELL_VALUE_SIZE + " valueIncrement = " + valueIncrement);
        }
        Integer key = (Integer) this.keys.toArray()[++this.indexPossibility];
        this.setCellValue(CellValue.values()[key]);
    }

    @Override
    public int isValueIncremental() {
        this.initKeys();
        return  (this.keys.size() <= this.indexPossibility + 1) ? 0 : 1;
    }

    public void setAsEmpty() {
        this.setCellValue(CellValue.EMPTY);
        this.indexPossibility = -1;
    }

    @Override
    public String toString() {
        return "\033[0;91m" + super.toString() + "\033[0m";
    }

    private void initKeys() {
        if (this.keys == null) {
            this.keys = this.possibilities.keySet();
        }
    }
}
