package Main;

import java.awt.*;
import java.awt.geom.Rectangle2D;


public class Brick extends Shapes{

    private static final int Height = 10;
    private static final int Width = 30;
    private static final int brickHGap = 2;
    private static final int brickVGap = 2;

    public Brick(int row, int col, Color color){
        super(new Rectangle2D.Double(brickHGap + row * (brickHGap + Brick.Width), brickVGap + col * (brickVGap + Brick.Height), Width,Height), color, true);
    }

    private Brick(Rectangle2D rectangle, Color color) {
        super(rectangle, color, true);
    }
/*
    public ArrayList<Brick> add(Brick other) {
        Rectangle2D rectangleOne = super.getBounds();
        Rectangle2D rectangleTwo = other.getBounds();
        rectangleOne.add(rectangleTwo);
        return new ArrayList<Brick>(rectangleOne, super.getColor());
    }

 */
}