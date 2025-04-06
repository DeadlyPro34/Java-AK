import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import java.util.ArrayList;
// import java.util.List;

public class Snake extends JPanel implements ActionListener {

    private final int BOARD_WIDTH = 300;
    private final int BOARD_HEIGHT = 300;
    private final int SQUARE_SIZE = 10;
    private final int DELAY = 100;

    private Timer timer;
    private int x = 0;
    private int y = 0;
    private int snakeX[] = new int[BOARD_WIDTH / SQUARE_SIZE];
    private int snakeY[] = new int[BOARD_HEIGHT / SQUARE_SIZE];
    private int foodX;
    private int foodY;
    private int score = 0;
    private boolean gameOver = false;
    private Direction direction = Direction.RIGHT;

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Snake() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_UP && direction != Direction.DOWN) {
                    direction = Direction.UP;
                } else if (key == KeyEvent.VK_DOWN && direction != Direction.UP) {
                    direction = Direction.DOWN;
                } else if (key == KeyEvent.VK_LEFT && direction != Direction.RIGHT) {
                    direction = Direction.LEFT;
                } else if (key == KeyEvent.VK_RIGHT && direction != Direction.LEFT) {
                    direction = Direction.RIGHT;
                }
            }
        });

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);

        startGame();
    }

    private void startGame() {
        timer = new Timer(DELAY, this);
        timer.start();
        generateFood();
    }

    private void generateFood() {
        foodX = (int) (Math.random() * (BOARD_WIDTH / SQUARE_SIZE));
        foodY = (int) (Math.random() * (BOARD_HEIGHT / SQUARE_SIZE));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (gameOver) {
            gameOver(g);
        } else {
            drawSnake(g);
            drawFood(g);
            drawScore(g);
        }
    }

    private void drawSnake(Graphics g) {
        for (int i = 0; i < snakeX.length; i++) {
            g.setColor(Color.GREEN);
            g.fillRect(snakeX[i] * SQUARE_SIZE, snakeY[i] * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }

    private void drawFood(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(foodX * SQUARE_SIZE, foodY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Game Over! Score: " + score, 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveSnake();
        checkCollision();
        repaint();
    }

    private void moveSnake() {
        for (int i = snakeX.length - 1; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        switch (direction) {
            case UP:
                y -= SQUARE_SIZE;
                break;
            case DOWN:
                y += SQUARE_SIZE;
                break;
            case LEFT:
                x -= SQUARE_SIZE;
                break;
            case RIGHT:
                x += SQUARE_SIZE;
                break;
        }

        snakeX[0] = x;
        snakeY[0] = y;
    }

    private void checkCollision() {
        if (x < 0 || x > BOARD_WIDTH - SQUARE_SIZE || y < 0 || y > BOARD_HEIGHT - SQUARE_SIZE) {
            gameOver = true;
        }

        for (int i = 1; i < snakeX.length; i++) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                gameOver = true;
            }
        }

        if (x == foodX * SQUARE_SIZE && y == foodY * SQUARE_SIZE) {
            score++;
            generateFood();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Snake());
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}