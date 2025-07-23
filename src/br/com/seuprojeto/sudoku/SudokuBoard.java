package br.com.seuprojeto.sudoku;

public class SudokuBoard {
    private SudokuCell[][] board;

    // Construtor padrão
    public SudokuBoard() {
        board = new SudokuCell[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                board[i][j] = new SudokuCell();
    }

    // 🔥 Construtor de cópia
    public SudokuBoard(SudokuBoard other) {
        board = new SudokuCell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuCell();
                board[i][j].setValue(other.getCell(i, j).getValue());
                board[i][j].setFixed(other.getCell(i, j).isFixed());
            }
        }
    }

    public SudokuCell getCell(int row, int col) {
        return board[row][col];
    }
}
