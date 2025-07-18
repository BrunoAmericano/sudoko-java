package br.com.seuprojeto.sudoku;

import javax.swing.*;
import java.awt.*;

public class SudokuGUI extends JFrame {

    private final JTextField[][] cells = new JTextField[9][9];
    private final GameProgression progression = new GameProgression();
    private SudokuBoard currentBoard;
    private final SudokuGenerator generator = new SudokuGenerator();
    private final SudokuSolver solver = new SudokuSolver();

    public SudokuGUI() {
        super("Ctrl+Alt+Delirium - Sudoku");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 700);
        setLocationRelativeTo(null);

        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        Font cellFont = new Font("Monospaced", Font.BOLD, 20);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(cellFont);
                cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                cells[row][col] = cell;
                gridPanel.add(cell);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton btnCheck = new JButton("Verificar");
        btnCheck.addActionListener(e -> checkSolution());

        JButton btnNew = new JButton("Novo Jogo");
        btnNew.addActionListener(e -> loadBoard(progression.getCurrentDifficulty()));

        JButton btnNext = new JButton("Avançar Nível");
        btnNext.addActionListener(e -> advanceLevel());

        buttonPanel.add(btnCheck);
        buttonPanel.add(btnNew);
        buttonPanel.add(btnNext);
        add(buttonPanel, BorderLayout.SOUTH);

        loadBoard(progression.getCurrentDifficulty());
        setVisible(true);
    }

    private void loadBoard(Difficulty difficulty) {
        currentBoard = generator.generate(difficulty);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int val = currentBoard.getCell(row, col).getValue();
                JTextField cell = cells[row][col];
                if (val != 0) {
                    cell.setText(String.valueOf(val));
                    cell.setEditable(false);
                    cell.setBackground(new Color(220, 220, 220));
                } else {
                    cell.setText("");
                    cell.setEditable(true);
                    cell.setBackground(Color.WHITE);
                }
            }
        }
    }

    private void checkSolution() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField cell = cells[row][col];
                String text = cell.getText().trim();
                if (text.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todas as células!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    int val = Integer.parseInt(text);
                    if (val < 1 || val > 9) {
                        JOptionPane.showMessageDialog(this, "Valores devem estar entre 1 e 9!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    currentBoard.getCell(row, col).setValue(val);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Digite apenas números!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }

        if (solver.solve(new SudokuBoard(currentBoard))) {
            JOptionPane.showMessageDialog(this, "Parabéns! Sudoku resolvido corretamente!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Solução incorreta, tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void advanceLevel() {
        progression.advanceLevel();
        Difficulty current = progression.getCurrentDifficulty();
        if (progression.isMaxLevel()) {
            JOptionPane.showMessageDialog(this, "Você já está no nível máximo!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Avançando para o nível: " + current, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        loadBoard(current);
    }
}
