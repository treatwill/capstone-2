package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BreakoutPanel extends JPanel {

    private javax.swing.Timer timer;
    private Ball ball;
    private Paddle paddle;
    private Player player = new Player();
    private ArrayList<Brick> bricks;
    private static final int numBrickRows = 10;
    private static final int numBrickCols = 30;


    class TimeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            Ball newBall = ball.getVirtualBall();
            if (newBall.intersects(paddle)){
                ball.goUp();
            }
            if (newBall.getX() + newBall.getWidth() / 2 < paddle.getX() + paddle.getWidth() / 2){
                ball.goLeft();
            } else  {
                ball.goRight();
            }  if (ball.getY() > paddle.getY() - paddle.getHeight()) {
                player.killPlayer();
            }ball.move();
            repaint();
        }
    }

    public BreakoutPanel() {
        ball = new Ball(Color.BLUE, this);
        paddle = new Paddle(Color.RED, this);
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
        setFocusable(true);



    }

    private void createBricks(){
        for (int row = 0; row < numBrickRows; row++){
            for (int col = 0; col < numBrickCols; col++){
                bricks.add(new Brick(row, col, Color.black));
            }
        }
    }

    

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (!player.isAlive()){
            showMessage("Game Over" ,g2);
            return;
        }
        player.draw(g2);
        ball.draw(g2);
        paddle.draw(g2);
    }

    public void showMessage(String s, Graphics2D g2) {
        Font myFont = new Font("SansSerif" ,Font.BOLD + Font.ITALIC, 40);
        g2.setFont(myFont);
        g2.setColor(Color.ORANGE);
        Rectangle2D textBox = myFont.getStringBounds(s, g2.getFontRenderContext());
        g2.drawString(s, (int) (getWidth() / 2 - textBox.getWidth() / 2), (int) (getHeight() / 2 - textBox.getHeight()));
    }

}
