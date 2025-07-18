package br.com.seuprojeto.sudoku;

public class SudokuBoard {
    private SudokuCell[][] board;

    public SudokuBoard() {
        board = new SudokuCell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuCell();
            }
        }
    }

    // Construtor de cópia
    public SudokuBoard(SudokuBoard other) {
        board = new SudokuCell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuCell(other.getCell(i, j).getValue());
            }
        }
    }

    public SudokuCell getCell(int row, int col) {
        return board[row][col];
    }
}
