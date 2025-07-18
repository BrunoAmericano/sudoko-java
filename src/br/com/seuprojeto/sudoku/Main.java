package br.com.seuprojeto.sudoku;

public class Main {

    public static void main(String[] args) {
        GameProgression progression = new GameProgression();
        SudokuGenerator generator = new SudokuGenerator();
        SudokuSolver solver = new SudokuSolver();

        for (int i = 0; i < 4; i++) {  // testa EASY, MEDIUM, HARD + tentativa extra
            Difficulty current = progression.getCurrentDifficulty();
            System.out.println("\n🕹️ Gerando Sudoku nível: " + current);

            SudokuBoard board = generator.generate(current);
            printBoard(board);

            boolean solved = solver.solve(board);
            if (solved) {
                System.out.println("\n✅ Sudoku resolvido:");
                printBoard(board);
            } else {
                System.out.println("\n❌ Não foi possível resolver o Sudoku.");
            }

            progression.advanceLevel();
        }
    }

    public static void printBoard(SudokuBoard board) {
        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("------+-------+------");
            }
            for (int col = 0; col < 9; col++) {
                if (col % 3 == 0 && col != 0) System.out.print("| ");
                int val = board.getCell(row, col).getValue();
                System.out.print((val == 0 ? "." : val) + " ");
            }
            System.out.println();
        }
    }
}
