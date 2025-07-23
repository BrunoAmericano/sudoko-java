package br.com.seuprojeto.sudoku;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SudokuGUI extends JFrame {
    private SudokuBoard currentBoard;
    private SudokuSolver solver;
    private JTextField[][] cells;
    private Timer gameTimer;
    private int secondsElapsed = 0;
    private JLabel timerLabel;

    public SudokuGUI() {
        super("Sudoku Java - Fase 6");

        currentBoard = new SudokuBoard();
        solver = new SudokuSolver();
        cells = new JTextField[9][9];

        generateBoard(); // método para gerar números iniciais aleatórios

        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        Font cellFont = new Font("Arial", Font.BOLD, 20);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(cellFont);

                // Borda destacando blocos 3x3
                int top = (row % 3 == 0) ? 2 : 1;
                int left = (col % 3 == 0) ? 2 : 1;
                int bottom = (row == 8) ? 2 : 1;
                int right = (col == 8) ? 2 : 1;
                cell.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                if (currentBoard.getCell(row, col).getValue() != 0) {
                    cell.setText(String.valueOf(currentBoard.getCell(row, col).getValue()));
                    cell.setEditable(false);
                    cell.setBackground(new Color(220, 220, 220));
                }

                cells[row][col] = cell;
                gridPanel.add(cell);
            }
        }

        JButton btnSolve = new JButton("Resolver");
        btnSolve.addActionListener(e -> solveBoard());

        JButton btnCheck = new JButton("Verificar");
        btnCheck.addActionListener(e -> checkSolution());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnSolve);
        buttonPanel.add(btnCheck);

        timerLabel = new JLabel("Tempo: 0s");
        setupTimer();

        setLayout(new BorderLayout());
        add(timerLabel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setVisible(true);
    }

    private void setupTimer() {
        gameTimer = new Timer(1000, e -> {
            secondsElapsed++;
            timerLabel.setText("Tempo: " + secondsElapsed + "s");
        });
        gameTimer.start();
    }

    private void generateBoard() {
        // Geração simples de teste – insere aleatórios apenas no primeiro bloco
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                currentBoard.getCell(i, j).setValue((i * 3 + j + 1));
    }

    private void solveBoard() {
        solver.solve(currentBoard);
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++) {
                cells[row][col].setText(String.valueOf(currentBoard.getCell(row, col).getValue()));
                cells[row][col].setEditable(false);
                cells[row][col].setBackground(new Color(200, 255, 200));
            }
        gameTimer.stop();
        JOptionPane.showMessageDialog(this, "Solução exibida!", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void checkSolution() {
        // Verifica se todas as células estão preenchidas (modo simples)
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++)
                if (cells[row][col].getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ainda há células vazias.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
        gameTimer.stop();
        JOptionPane.showMessageDialog(this, "Parabéns! Sudoku completo.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
