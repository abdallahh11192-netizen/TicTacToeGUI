import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {

    private JButton[][] buttons = new JButton[3][3];
    private JButton quitButton = new JButton("Quit");

    private String currentPlayer = "X";
    private int moveCount = 0;

    public TicTacToeFrame() {

        setTitle("Tic Tac Toe");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));

        // One listener is used for all 9 board buttons
        ActionListener buttonListener = e -> {

            JButton clickedButton = (JButton) e.getSource();

            // Do not allow a player to use an occupied square
            if (!clickedButton.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "This square is already taken. Choose another square."
                );
                return;
            }

            clickedButton.setText(currentPlayer);
            moveCount++;

            // A win is only possible starting with move 5
            if (moveCount >= 5 && checkWin()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Player " + currentPlayer + " wins!"
                );

                playAgain();
                return;
            }

            // Full-board tie OR not-full-board tie
            if (moveCount == 9 || noPossibleWin()) {

                JOptionPane.showMessageDialog(
                        this,
                        "The game is a tie!"
                );

                playAgain();
                return;
            }

            // Switch players
            if (currentPlayer.equals("X")) {
                currentPlayer = "O";
            } else {
                currentPlayer = "X";
            }
        };

        // Create the 3 x 3 board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                buttons[row][col] = new JButton("");

                buttons[row][col].setFont(
                        new Font("Arial", Font.BOLD, 60)
                );

                buttons[row][col].addActionListener(buttonListener);

                boardPanel.add(buttons[row][col]);
            }
        }

        // Quit button
        quitButton.addActionListener(e -> {

            int answer = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to quit?",
                    "Quit",
                    JOptionPane.YES_NO_OPTION
            );

            if (answer == JOptionPane.YES_OPTION) {

                JOptionPane.showMessageDialog(
                        this,
                        "Thanks for playing!"
                );

                System.exit(0);
            }
        });

        add(boardPanel, BorderLayout.CENTER);
        add(quitButton, BorderLayout.SOUTH);
    }


    private boolean checkWin() {

        // Check rows
        for (int row = 0; row < 3; row++) {

            if (!buttons[row][0].getText().isEmpty()
                    && buttons[row][0].getText()
                    .equals(buttons[row][1].getText())
                    && buttons[row][1].getText()
                    .equals(buttons[row][2].getText())) {

                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {

            if (!buttons[0][col].getText().isEmpty()
                    && buttons[0][col].getText()
                    .equals(buttons[1][col].getText())
                    && buttons[1][col].getText()
                    .equals(buttons[2][col].getText())) {

                return true;
            }
        }

        // Check first diagonal
        if (!buttons[0][0].getText().isEmpty()
                && buttons[0][0].getText()
                .equals(buttons[1][1].getText())
                && buttons[1][1].getText()
                .equals(buttons[2][2].getText())) {

            return true;
        }

        // Check second diagonal
        if (!buttons[0][2].getText().isEmpty()
                && buttons[0][2].getText()
                .equals(buttons[1][1].getText())
                && buttons[1][1].getText()
                .equals(buttons[2][0].getText())) {

            return true;
        }

        return false;
    }


    // Checks if there are no possible winning lines left
    private boolean noPossibleWin() {

        // Check rows
        for (int row = 0; row < 3; row++) {

            if (lineCanStillWin(
                    buttons[row][0].getText(),
                    buttons[row][1].getText(),
                    buttons[row][2].getText())) {

                return false;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {

            if (lineCanStillWin(
                    buttons[0][col].getText(),
                    buttons[1][col].getText(),
                    buttons[2][col].getText())) {

                return false;
            }
        }

        // Check first diagonal
        if (lineCanStillWin(
                buttons[0][0].getText(),
                buttons[1][1].getText(),
                buttons[2][2].getText())) {

            return false;
        }

        // Check second diagonal
        if (lineCanStillWin(
                buttons[0][2].getText(),
                buttons[1][1].getText(),
                buttons[2][0].getText())) {

            return false;
        }

        return true;
    }


    // A line can still be won if it does not contain both X and O
    private boolean lineCanStillWin(String a, String b, String c) {

        boolean hasX =
                a.equals("X") ||
                        b.equals("X") ||
                        c.equals("X");

        boolean hasO =
                a.equals("O") ||
                        b.equals("O") ||
                        c.equals("O");

        return !(hasX && hasO);
    }


    private void playAgain() {

        int answer = JOptionPane.showConfirmDialog(
                this,
                "Would you like to play again?",
                "Play Again",
                JOptionPane.YES_NO_OPTION
        );

        if (answer == JOptionPane.YES_OPTION) {

            resetGame();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Thanks for playing!"
            );

            System.exit(0);
        }
    }


    private void resetGame() {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                buttons[row][col].setText("");
            }
        }

        currentPlayer = "X";
        moveCount = 0;
    }
}