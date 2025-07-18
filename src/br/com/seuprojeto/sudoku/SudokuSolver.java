package br.com.seuprojeto.sudoku;

public class SudokuSolver {

    public boolean solve(SudokuBoard board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                SudokuCell cell = board.getCell(row, col);
                
                if (cell.getValue() == 0) { // se a célula está vazia
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            cell.setValue(num);

                            if (solve(board)) {
                                return true; // solução encontrada
                            } else {
                                cell.setValue(0); // backtrack
                            }
                        }
                    }
                    return false; // sem número válido
                }
            }
        }
        return true; // tabuleiro resolvido
    }

    private boolean isValid(SudokuBoard board, int row, int col, int num) {
        // Verifica a linha
        for (int i = 0; i < 9; i++) {
            if (board.getCell(row, i).getValue() == num) {
                return false;
            }
        }

        // Verifica a coluna
        for (int i = 0; i < 9; i++) {
            if (board.getCell(i, col).getValue() == num) {
                return false;
            }
        }

        // Verifica o bloco 3x3
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board.getCell(i, j).getValue() == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
