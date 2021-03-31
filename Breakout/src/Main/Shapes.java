package Main;

import java.awt.*;
import java.awt.geom.RectangularShape;

public class Shapes {

    private Color color;
    private boolean fill;
    private RectangularShape shape;

    public Shapes(RectangularShape shape, Color color, boolean fill) {
        this.color = color;
        this.fill = fill;
        this.shape = shape;
    }


    public Color getColor() {
        return color;
    }

    protected Rectangle getBounds(){
        return shape.getBounds();
    }

    public void changeColor(Color color) {
        this.color = color;
    }

    public void draw(Graphics2D g2) {
        g2.setPaint(color);
        g2.draw(shape);

        if (fill) {
            g2.fill(shape);
        }
    }

    public double getX() {
        return shape.getX();
    }

    public double getY() {
        return shape.getY();
    }

    public double getHeight() {
        return shape.getHeight();
    }

    public double getWidth() {
        return shape.getWidth();
    }

    public boolean intersects(Shapes other) {
        return shape.intersects(other.shape.getBounds());
    }

    public boolean below(Shapes other) {
        return getY() >= other.getY() + other.getX();
    }


    public boolean above(Shapes other) {
        return getY() + getHeight() <= other.getX();
    }


    public boolean leftOf(Shapes other) {
        return getX() + getWidth() <= other.getX();
    }


    public boolean rightOf(Shapes other) {
        return getX() >= other.getX() + other.getWidth();
    }


    public void move(int dx, int dy) {
        shape.setFrame(getX() + dx, getY() + dy, getWidth(), getHeight());
    }


    public void remove(Brick brick) {
    }
}
