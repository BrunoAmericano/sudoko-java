package br.com.seuprojeto.sudoku;

public class SudokuCell {
    private int value;
    private boolean isFixed;

    public SudokuCell() {
        this.value = 0;
        this.isFixed = false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }
}
