package br.com.seuprojeto.sudoku;

public class SudokuGame {
    private SudokuBoard board;

    public SudokuGame() {
        this.board = new SudokuBoard();
    }

    public void startNewGame(Difficulty difficulty) {
        SudokuGenerator generator = new SudokuGenerator();
        this.board = generator.generate(difficulty);
    }

    public SudokuBoard getBoard() {
        return board;
    }
}
