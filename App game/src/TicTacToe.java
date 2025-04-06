import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JPanel {
    private boolean xTurn = true;
    private boolean gameOver = false;
    private char[][] board = new char[3][3];

    public TicTacToe() {
        setPreferredSize(new Dimension(300, 300));
        setBackground(Color.BLACK);
        setFocusable(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!gameOver) {
                    int row = e.getY() / 100;
                    int col = e.getX() / 100;
                    if (board[row][col] == 0) {
                        if (xTurn) {
                            board[row][col] = 'X';
                        } else {
                            board[row][col] = 'O';
                        }
                        xTurn = !xTurn;
                        checkWin();
                    }
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    private void drawBoard(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < 3; i++) {
            g.drawLine(0, i * 100, 300, i * 100);
            g.drawLine(i * 100, 0, i * 100, 300);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') {
                    g.drawString("X", j * 100 + 40, i * 100 + 60);
                } else if (board[i][j] == 'O') {
                    g.drawString("O", j * 100 + 40, i * 100 + 60);
                }
            }
        }
    }

    private void checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != 0) {
                gameOver = true;
                return;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != 0) {
                gameOver = true;
                return;
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0) {
            gameOver = true;
            return;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != 0) {
            gameOver = true;
            return;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TicTacToe());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}