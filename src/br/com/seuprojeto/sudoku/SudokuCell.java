package br.com.seuprojeto.sudoku;

public class SudokuCell {
    private int value;

    public SudokuCell() {
        this.value = 0;
    }

    public SudokuCell(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
