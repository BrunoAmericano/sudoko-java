package br.com.seuprojeto.sudoku;

public class GameProgression {

    private Difficulty currentDifficulty;

    public GameProgression() {
        this.currentDifficulty = Difficulty.EASY;  // começa no fácil
    }

    public Difficulty getCurrentDifficulty() {
        return currentDifficulty;
    }

    public void advanceLevel() {
        switch (currentDifficulty) {
            case EASY -> currentDifficulty = Difficulty.MEDIUM;
            case MEDIUM -> currentDifficulty = Difficulty.HARD;
            case HARD -> System.out.println("Você já está no nível máximo!");
        }
    }

    public boolean isMaxLevel() {
        return currentDifficulty == Difficulty.HARD;
    }
}
