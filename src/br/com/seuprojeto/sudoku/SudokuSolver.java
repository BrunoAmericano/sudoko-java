package br.com.seuprojeto.sudoku;

public class SudokuSolver {

    public boolean solve(SudokuBoard board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                SudokuCell cell = board.getCell(row, col);
                if (cell.getValue() == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            cell.setValue(num);
                            if (solve(board)) {
                                return true;
                            } else {
                                cell.setValue(0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(SudokuBoard board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board.getCell(row, i).getValue() == num) {
                return false;
            }
            if (board.getCell(i, col).getValue() == num) {
                return false;
            }
        }
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board.getCell(r, c).getValue() == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
