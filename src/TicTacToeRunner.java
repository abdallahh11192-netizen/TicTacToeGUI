import javax.swing.*;

public class TicTacToeRunner {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            TicTacToeFrame frame = new TicTacToeFrame();
            frame.setVisible(true);
        });

    }
}