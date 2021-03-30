package Main;


import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball extends BreakoutShape{
    private static final int SIZE = 10;
    private static final int START_X = 200;
    private static final int START_Y = 400;

    private int dx = 1;
    private int dy = -1;
    private BreakoutPanel panel;

    public Ball(Color color, BreakoutPanel panel) {
        super(color, true, new Ellipse2D.Double(START_X, START_Y, SIZE, SIZE));
        this.panel = panel;
    }

    public void move() {
        if (getX() + dx < 0) {
            dx = 1;
        }
        if (getX() + getWidth() + dx > panel.getWidth()) {
            dx = -1;
        }
        if (getY() + dy < 0) {
            dy = 1;
        }
        if (getY() + getHeight() + dy > panel.getHeight()) {
            dy = -1;
            super.move(dx,dy);
        }
    }




}
