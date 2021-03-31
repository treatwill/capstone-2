package Main;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Brick extends BreakoutShape{

    private static final int Height = 10;
    private static final int Width = 30;
    private static final int brickHGap = 2;
    private static final int brickVGap = 3;

    public Brick(int row, int col, Color color){
        super(color, true, new Rectangle2D.Double(brickHGap + row * (brickHGap + Brick.Width), brickVGap + col * (brickVGap + Brick.Height), Width,Height));
    }

    private Brick(Rectangle2D rectangle, Color color) {
        super(color, true, rectangle);
    }

    public Brick add(Brick other) {
        Rectangle2D rectangleOne = super.getBounds();
        Rectangle2D rectangleTwo = other.getBounds();
        rectangleOne.add(rectangleTwo);
        return new Brick(rectangleOne, super.getColor());
    }
}
