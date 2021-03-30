package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BreakoutPanel extends JPanel {

    private javax.swing.Timer timer;
    private Ball ball;


    public BreakoutPanel() {
        ball = new Ball(Color.BLUE, this);
        timer = new javax.swing.Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ball.move();
                repaint();
            }
        });
        timer.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        ball.draw(g2);
    }

}
