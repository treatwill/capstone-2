package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Player {

    private static int initialNumLives = 3;
    private static int imgYposition = 450;
    private static int imgHGap = 5;
    private static int numLives;

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
            Image image = ImageIO.read(new File("player.gif"));
            for (int x = 0; x < numLives; x++) {
                g2.drawImage(image, x * (image.getWidth(null) + imgHGap), imgYposition, null);
            }
        } catch (Exception newException) {
        }
    }

    }
