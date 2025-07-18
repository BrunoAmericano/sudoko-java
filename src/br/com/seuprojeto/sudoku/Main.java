package br.com.seuprojeto.sudoku;

public class Main {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard();

        // Exemplo de preenchimento manual para teste
        board.getCell(0,0).setValue(5);
        board.getCell(0,1).setValue(3);
        board.getCell(0,4).setValue(7);
        // ... preencha mais células se quiser testar

        SudokuSolver solver = new SudokuSolver();

        if (solver.solve(board)) {
            System.out.println("✅ Sudoku resolvido com sucesso!");
            printBoard(board);
        } else {
            System.out.println("❌ Não foi possível resolver o Sudoku.");
        }
    }

    public static void printBoard(SudokuBoard board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(board.getCell(row, col).getValue() + " ");
            }
            System.out.println();
        }
    }
}
