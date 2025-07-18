package br.com.seuprojeto.sudoku;

import java.util.*;

public class SudokuGenerator {

    private Random random = new Random();

    public SudokuBoard generate(Difficulty difficulty) {
        SudokuBoard board = new SudokuBoard();

        fillBoard(board);

        removeNumbers(board, difficulty);

        return board;
    }

    private boolean fillBoard(SudokuBoard board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.getCell(row, col).getValue() == 0) {
                    List<Integer> numbers = getShuffledNumbers();
                    for (int num : numbers) {
                        if (isValid(board, row, col, num)) {
                            board.getCell(row, col).setValue(num);
                            if (fillBoard(board)) {
                                return true;
                            } else {
                                board.getCell(row, col).setValue(0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private List<Integer> getShuffledNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, random);
        return numbers;
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

    private void removeNumbers(SudokuBoard board, Difficulty difficulty) {
        int clues = difficulty.getClues();
        int cellsToRemove = 81 - clues;

        while (cellsToRemove > 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            if (board.getCell(row, col).getValue() != 0) {
                int backup = board.getCell(row, col).getValue();
                board.getCell(row, col).setValue(0);

                SudokuBoard copy = new SudokuBoard(board);
                SudokuSolver solver = new SudokuSolver();
                if (!solver.solve(copy)) {
                    board.getCell(row, col).setValue(backup);
                } else {
                    cellsToRemove--;
                }
            }
        }
    }
}
