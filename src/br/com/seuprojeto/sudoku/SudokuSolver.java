package br.com.seuprojeto.sudoku;

public class SudokuSolver {

    public boolean solve(SudokuBoard board) {
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++)
                if (board.getCell(row, col).getValue() == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board.getCell(row, col).setValue(num);
                            if (solve(board))
                                return true;
                            board.getCell(row, col).setValue(0);
                        }
                    }
                    return false;
                }
        return true;
    }

    private boolean isValid(SudokuBoard board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board.getCell(row, i).getValue() == num || board.getCell(i, col).getValue() == num)
                return false;
        }
        int boxRowStart = row - row % 3, boxColStart = col - col % 3;
        for (int i = boxRowStart; i < boxRowStart + 3; i++)
            for (int j = boxColStart; j < boxColStart + 3; j++)
                if (board.getCell(i, j).getValue() == num)
                    return false;
        return true;
    }
}
