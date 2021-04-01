package Main;

import Ball.Ball;
import Brick.Brick;
import Paddle.Paddle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;

public class Panel extends JPanel {

    private javax.swing.Timer timer;
    private Ball ball;
    private Paddle paddle;
    private Player player;
    private boolean gameStarted = false;
    private ArrayList<Brick> bricks;
    private static final int numBrickRows = 10;
    private static final int numBrickCols = 30;



    public Panel() {
        ball = new Ball(Color.BLUE, this);
        paddle = new Paddle(Color.RED, this);
        bricks = new ArrayList<>();
        timer = new javax.swing.Timer(10, new TimeListener());
        player = new Player();
        createBricks();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                String s = KeyEvent.getKeyText(e.getKeyCode());
                if (e.getKeyCode()==KeyEvent.VK_LEFT){
                    paddle.moveLeft();
                }
                if (s.equals("Right")){
                    paddle.moveRight();
                }
                repaint();
            }
        });
        setFocusable(true);

        addMouseMotionListener(new MouseMotionAdapter() {
            boolean firstTime = true;
            int oldX;

            public void mouseMoved(MouseEvent e){
                if(firstTime) {
                    oldX = e.getX();
                    firstTime = false;
                }
                paddle.move(e.getX()-oldX);
                oldX=e.getX();
                repaint();
            }
        });

    }
    private Color getRandomColor(){
        Color color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
        if (getBackground().equals(color)){
            return Color.RED;
        }
        return color;
    }

    private void createBricks(){
        for (int row = 0; row < numBrickRows; row++){
            for (int col = 0; col < numBrickCols; col++){
                bricks.add(new Brick(row, col, getRandomColor()));
            }
        }
    }

    public void start(){
        gameStarted=true;


        if (timer != null){
            timer.stop();

        }
        if (!player.isAlive()) {
            player = new Player();
            ball = new Ball(Color.BLUE, this);
            createBricks();

        }
            timer = new javax.swing.Timer(BallSpeed.FAST.speed(), new  Panel.TimeListener());
            timer.start();
            repaint();
    }

    public void pause() {
        if(timer==null){
            return;
        }
        timer.stop();
    }

    public void changeBallColor(BallColor color) {
        ball.changeColor(color.color());
        repaint();
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (bricks.size()== 0 && ! gameStarted){
            showMessage("Congrats You Win!" ,g2);
            gameStarted= false;
        }
        else if (!player.isAlive()){
            showMessage("Game Over" ,g2);
            gameStarted = false;
        } else {
            ball.draw(g2);
            paddle.draw(g2);
            for (Brick brick : bricks){
                brick.draw(g2);
            }
        }
        if (gameStarted){
            player.draw(g2);
        }
    }

    public void changeBallSpeed(int speed){
        timer.setDelay(speed);
    }

    public void showMessage(String s, Graphics2D g2) {
        Font myFont = new Font("SansSerif" ,Font.BOLD + Font.ITALIC, 40);
        g2.setFont(myFont);
        g2.setColor(Color.ORANGE);
        Rectangle2D textBox = myFont.getStringBounds(s, g2.getFontRenderContext());
        g2.drawString(s, (int) (getWidth() / 2 - textBox.getWidth() / 2), (int) (getHeight() / 2 - textBox.getHeight()));
    }

    class TimeListener implements ActionListener{

        public void bonceBall(Ball ball, Brick brick){
            if (ball.below(brick)){
                ball.goDown();
            }
            if (ball.above(brick)){
                ball.goUp();
            }
            if (ball.leftOf(brick)){
                ball.goLeft();
            }
            if (ball.rightOf(brick)) {
                ball.goRight();
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            start();
        }
    }

    public void bounceBall(Ball ball, ArrayList<Brick> bricks){

        if (bricks.size()==0){
            return;
        }
        if (bricks.size()==1){
            bounceBall(ball, new ArrayList<Brick>(Collections.singleton(bricks.get(0))));
        }
        ArrayList<Brick> combinedBrick = bricks.get(0).add(bricks.get(1));
        bounceBall(ball, combinedBrick);
    }

    public void actionPerformed(ActionEvent e){
        Ball newBall = ball.getVirtualBall();
        ArrayList<Brick> bricksToBeDeleted = new ArrayList<Brick>();
        for (Brick brick : bricks){
            if (brick.intersects(newBall)){
                bricksToBeDeleted.add(brick);
            }
            bounceBall(ball, bricksToBeDeleted);
            }
        for (Brick brick : bricksToBeDeleted){
            brick.remove(brick);
        }
        if (newBall.intersects(paddle)){
            ball.goUp();
        }
        if (newBall.getX() + newBall.getWidth() / 2 < paddle.getX() + paddle.getWidth() / 2){
            ball.goLeft();
        } else {
            ball.goRight();
        }  if (ball.getY() > paddle.getY() - paddle.getHeight()){
            player.killPlayer();
            ball.goUp();
        } ball.move();
        repaint();

    }

}