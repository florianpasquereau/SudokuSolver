package Grid.Cell;

public interface InterfaceCell {
    public static CellValue DEFAULT_VALUE = CellValue.EMPTY;
    public static int CELL_VALUE_SIZE = CellValue.values().length;

    public enum CellValue {
        EMPTY,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        HEIGHT,
        NINE
    }

    public static CellValue getFromIndex(int index) {
        return (index < 0 || index > InterfaceCell.CELL_VALUE_SIZE) ? InterfaceCell.DEFAULT_VALUE : CellValue.values()[index];
    }

    public int isValueIncremental();

    public CellValue getCellValue();

    public boolean isEqual(InterfaceCell cell);

    public boolean isEmpty();
}
