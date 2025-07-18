package br.com.seuprojeto.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class SudokuGUI extends JFrame {

    private JTextField[][] fields = new JTextField[9][9];
    private SudokuGame game;

    public SudokuGUI() {
        this.game = new SudokuGame();
        this.game.startNewGame(Difficulty.MEDIUM);

        setTitle("Sudoku Java");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(9, 9));
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        Font font = new Font("Monospaced", Font.BOLD, 20);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField tf = new JTextField();
                tf.setHorizontalAlignment(JTextField.CENTER);
                tf.setFont(font);
                int value = game.getBoard().getCell(row, col).getValue();
                if (value != 0) {
                    tf.setText(String.valueOf(value));
                    tf.setEditable(false);
                    tf.setBackground(Color.LIGHT_GRAY);
                }
                fields[row][col] = tf;
                boardPanel.add(tf);
            }
        }

        pane.add(boardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton checkButton = new JButton("Verificar");
        checkButton.addActionListener(e -> checkBoard());

        JButton resetButton = new JButton("Novo Jogo");
        resetButton.addActionListener(e -> resetGame());

        buttonPanel.add(checkButton);
        buttonPanel.add(resetButton);

        pane.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void checkBoard() {
        SudokuBoard board = game.getBoard();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String text = fields[row][col].getText();
                int val = 0;
                try {
                    val = Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Digite números de 1 a 9 apenas!");
                    return;
                }
                board.getCell(row, col).setValue(val);
            }
        }
        SudokuSolver solver = new SudokuSolver();
        if (solver.solve(new SudokuBoardCopy(game.getBoard()))) {
            JOptionPane.showMessageDialog(this, "Parabéns! Sudoku resolvido corretamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Existem erros no Sudoku, tente novamente.");
        }
    }

    private void resetGame() {
        game.startNewGame(Difficulty.MEDIUM);
        SudokuBoard board = game.getBoard();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int val = board.getCell(row, col).getValue();
                fields[row][col].setText(val == 0 ? "" : String.valueOf(val));
                fields[row][col].setEditable(val == 0);
                fields[row][col].setBackground(val == 0 ? Color.WHITE : Color.LIGHT_GRAY);
            }
        }
    }

    // Helper class para criar cópia do board para validar sem alterar o original
    private static class SudokuBoardCopy extends SudokuBoard {
        public SudokuBoardCopy(SudokuBoard original) {
            super();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    this.getCell(i, j).setValue(original.getCell(i, j).getValue());
                }
            }
        }
    }
}
