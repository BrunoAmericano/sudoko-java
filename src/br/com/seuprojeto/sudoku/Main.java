package br.com.seuprojeto.sudoku;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuGUI::new);
    }
}
