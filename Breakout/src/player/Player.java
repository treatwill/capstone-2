package player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Player {
    private static int initialNumLives = 1;
    private static int imageYPosition = 450;
    private static int imageGap = 5;
    private int numLives;


    public Player() {
        this.numLives = initialNumLives;
    }

    public void killPlayer() {
        numLives--;
    }

    public boolean isAlive(){
        return (numLives > 0);
    }

    public void draw(Graphics2D g2) {
        try {
            Image image = ImageIO.read(new File("Breakout/src/ball.png"));
            for (int x = 0; x < numLives; x++) {
                g2.drawImage(image, x * (image.getWidth(null) + imageGap), imageYPosition, null);
            }
        } catch (Exception newException) {
        }
    }








}
