package Main;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Paddle extends Shapes{

    private static final int StartX = 200;
    private static final int StartY = 430;
    private static final int Width = 50;
    private static final int Height = 10;
    private static final int Speed = 10;

    private Panel panel;

    public Paddle(Color color, Panel panel) {
        super(new Rectangle2D.Double(StartX, Paddle.StartY, Paddle.Width, Paddle.Height), color, true);
        this.panel = panel;
    }

    public void move(int dx) {
        if ((getX() + dx >= 0) && (getX() + dx + Width <= panel.getWidth())){
            move(dx,0);
        }
    }

    public void moveRight() {
        move(Speed);
    }

    public void moveLeft() {
        move(-Speed);
    }

}