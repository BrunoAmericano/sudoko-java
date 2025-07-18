package br.com.seuprojeto.sudoku;

public enum Difficulty {
    EASY(40),
    MEDIUM(30),
    HARD(20);

    private final int clues;

    Difficulty(int clues) {
        this.clues = clues;
    }

    public int getClues() {
        return clues;
    }
}
